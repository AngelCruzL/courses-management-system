package dev.angelcruzl.courseapp.service.impl;

import dev.angelcruzl.courseapp.dto.InstructorDTO;
import dev.angelcruzl.courseapp.entity.Course;
import dev.angelcruzl.courseapp.entity.Instructor;
import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.mapper.InstructorMapper;
import dev.angelcruzl.courseapp.repository.InstructorRepository;
import dev.angelcruzl.courseapp.service.CourseService;
import dev.angelcruzl.courseapp.service.InstructorService;
import dev.angelcruzl.courseapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public class InstructorServiceImpl implements InstructorService {
  private final InstructorRepository instructorRepository;
  private final InstructorMapper instructorMapper;
  private final UserService userService;
  private final CourseService courseService;

  public InstructorServiceImpl(
    InstructorRepository instructorRepository,
    InstructorMapper instructorMapper,
    UserService userService,
    CourseService courseService
  ) {
    this.instructorRepository = instructorRepository;
    this.instructorMapper = instructorMapper;
    this.userService = userService;
    this.courseService = courseService;
  }

  @Override
  public Instructor loadInstructorById(Long instructorId) {
    return instructorRepository.findById(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor with ID " + instructorId + " not found"));
  }

  @Override
  public Page<InstructorDTO> findInstructorsByName(String name, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Instructor> instructorsPage = instructorRepository.findInstructorsByName(name, pageRequest);
    return new PageImpl<>(
      instructorsPage.
        getContent()
        .stream()
        .map(instructor -> instructorMapper.fromInstructor(instructor))
        .collect(Collectors.toList()),
      pageRequest,
      instructorsPage.getTotalElements()
    );
  }

  @Override
  public InstructorDTO loadInstructorByEmail(String email) {
    return instructorMapper.fromInstructor(instructorRepository.findInstructorByEmail(email));
  }

  @Override
  public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
    User user = userService.createUser(instructorDTO.getUser().getEmail(), instructorDTO.getUser().getPassword());
    userService.assignRoleToUser(user.getEmail(), "INSTRUCTOR");
    Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
    instructor.setUser(user);
    Instructor savedInstructor = instructorRepository.save(instructor);

    return instructorMapper.fromInstructor(savedInstructor);
  }

  @Override
  public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
    Instructor loadedInstructor = loadInstructorById(instructorDTO.getId());
    Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
    instructor.setUser(loadedInstructor.getUser());
    instructor.setCourses(loadedInstructor.getCourses());
    Instructor updatedInstructor = instructorRepository.save(instructor);

    return instructorMapper.fromInstructor(updatedInstructor);
  }

  @Override
  public List<InstructorDTO> fetchInstructors() {
    return instructorRepository
      .findAll()
      .stream()
      .map(instructor -> instructorMapper.fromInstructor(instructor))
      .collect(Collectors.toList());
  }

  @Override
  public void deleteInstructor(Long instructorId) {
    Instructor instructor = loadInstructorById(instructorId);

    for (Course course : instructor.getCourses())
      courseService.deleteCourse(course.getId());

    instructorRepository.deleteById(instructorId);
  }
}
