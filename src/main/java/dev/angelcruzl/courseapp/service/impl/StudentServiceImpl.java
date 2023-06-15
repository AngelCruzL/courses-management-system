package dev.angelcruzl.courseapp.service.impl;

import dev.angelcruzl.courseapp.dto.StudentDTO;
import dev.angelcruzl.courseapp.entity.Course;
import dev.angelcruzl.courseapp.entity.Student;
import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.mapper.StudentMapper;
import dev.angelcruzl.courseapp.repository.StudentRepository;
import dev.angelcruzl.courseapp.service.StudentService;
import dev.angelcruzl.courseapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;
  private final UserService userService;

  public StudentServiceImpl(
    StudentRepository studentRepository,
    StudentMapper studentMapper,
    UserService userService
  ) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
    this.userService = userService;
  }

  @Override
  public Student loadStudentById(Long studentId) {
    return studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
  }

  @Override
  public Page<StudentDTO> loadStudentsByName(String name, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Student> studentsPage = studentRepository.findStudentsByName(name, pageRequest);
    return new PageImpl<>(
      studentsPage
        .getContent()
        .stream()
        .map(student -> studentMapper.fromStudent(student))
        .collect(Collectors.toList()),
      pageRequest,
      studentsPage.getTotalElements()
    );
  }

  @Override
  public StudentDTO loadStudentByEmail(String email) {
    return studentMapper.fromStudent(studentRepository.findStudentByEmail(email));
  }

  @Override
  public StudentDTO createStudent(StudentDTO studentDTO) {
    User user = userService.createUser(studentDTO.getUser().getEmail(), studentDTO.getUser().getPassword());
    userService.assignRoleToUser(user.getEmail(), "STUDENT");
    Student student = studentMapper.fromStudentDTO(studentDTO);
    student.setUser(user);
    Student savedStudent = studentRepository.save(student);

    return studentMapper.fromStudent(savedStudent);
  }

  @Override
  public StudentDTO updateStudent(StudentDTO studentDTO) {
    Student loadedStudent = loadStudentById(studentDTO.getId());
    Student student = studentMapper.fromStudentDTO(studentDTO);
    student.setUser(loadedStudent.getUser());
    student.setCourses(loadedStudent.getCourses());
    Student updatedStudent = studentRepository.save(student);

    return studentMapper.fromStudent(updatedStudent);
  }

  @Override
  public void deleteStudent(Long studentId) {
    Student student = loadStudentById(studentId);

    Iterator<Course> courseIterator = student.getCourses().iterator();
    if (courseIterator.hasNext()) {
      Course course = courseIterator.next();
      course.removeStudentFromCourse(student);
    }

    studentRepository.deleteById(studentId);
  }
}
