package dev.angelcruzl.courseapp.service.impl;

import dev.angelcruzl.courseapp.dto.CourseDTO;
import dev.angelcruzl.courseapp.entity.Course;
import dev.angelcruzl.courseapp.entity.Instructor;
import dev.angelcruzl.courseapp.entity.Student;
import dev.angelcruzl.courseapp.mapper.CourseMapper;
import dev.angelcruzl.courseapp.repository.CourseRepository;
import dev.angelcruzl.courseapp.repository.InstructorRepository;
import dev.angelcruzl.courseapp.repository.StudentRepository;
import dev.angelcruzl.courseapp.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;
  private final InstructorRepository instructorRepository;
  private final StudentRepository studentRepository;

  public CourseServiceImpl(
    CourseRepository courseRepository,
    CourseMapper courseMapper,
    InstructorRepository instructorRepository,
    StudentRepository studentRepository
  ) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
    this.instructorRepository = instructorRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public Course loadCourseById(Long courseId) {
    return courseRepository
      .findById(courseId)
      .orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseId + " not found"));
  }

  @Override
  public CourseDTO createCourse(CourseDTO courseDTO) {
    Course course = courseMapper.fromCourseDTO(courseDTO);
    Instructor instructor = instructorRepository
      .findById(courseDTO.getInstructor().getId())
      .orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseDTO.getInstructor().getId() + " not found"));
    course.setInstructor(instructor);
    Course savedCourse = courseRepository.save(course);

    return courseMapper.fromCourse(savedCourse);
  }

  @Override
  public CourseDTO updateCourse(CourseDTO courseDTO) {
    Course loadedCourse = loadCourseById(courseDTO.getId());
    Instructor instructor = instructorRepository
      .findById(courseDTO.getInstructor().getId())
      .orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseDTO.getInstructor().getId() + " not found"));
    Course course = courseMapper.fromCourseDTO(courseDTO);
    course.setInstructor(instructor);
    course.setStudents(loadedCourse.getStudents());
    Course updatedCourse = courseRepository.save(course);

    return courseMapper.fromCourse(updatedCourse);
  }

  @Override
  public Page<CourseDTO> findCoursesByCourseTitle(String keyword, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Course> coursesPage = courseRepository.findCoursesByTitleContains(keyword, pageRequest);

    return new PageImpl<>(
      coursesPage
        .getContent()
        .stream()
        .map(course -> courseMapper.fromCourse(course))
        .collect(Collectors.toList()),
      pageRequest,
      coursesPage.getTotalElements()
    );
  }

  @Override
  public void assignStudentToCourse(Long courseId, Long studentId) {
    Student student = studentRepository
      .findById(studentId)
      .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));
    Course course = loadCourseById(courseId);
    course.assignStudentToCourse(student);
  }

  @Override
  public Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Course> studentCoursesPage = courseRepository.getCoursesByStudentId(studentId, pageRequest);
    return new PageImpl<>(
      studentCoursesPage
        .getContent()
        .stream()
        .map(course -> courseMapper.fromCourse(course))
        .collect(Collectors.toList()),
      pageRequest,
      studentCoursesPage.getTotalElements()
    );
  }

  @Override
  public Page<CourseDTO> fetchNonEnrolledCoursesForStudent(Long studentId, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Course> nonEnrolledInCoursesPage = courseRepository.getNonEnrolledInCoursesByStudentId(studentId, pageRequest);
    return new PageImpl<>(
      nonEnrolledInCoursesPage
        .getContent()
        .stream()
        .map(course -> courseMapper.fromCourse(course))
        .collect(Collectors.toList()),
      pageRequest,
      nonEnrolledInCoursesPage.getTotalElements()
    );
  }

  @Override
  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }

  @Override
  public Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Course> instructorCoursesPage = courseRepository.getCoursesByInstructorId(instructorId, pageRequest);
    return new PageImpl<>(
      instructorCoursesPage
        .getContent()
        .stream()
        .map(course -> courseMapper.fromCourse(course))
        .collect(Collectors.toList()),
      pageRequest,
      instructorCoursesPage.getTotalElements()
    );
  }
}
