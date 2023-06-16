package dev.angelcruzl.courseapp.web;

import dev.angelcruzl.courseapp.dto.CourseDTO;
import dev.angelcruzl.courseapp.dto.StudentDTO;
import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.service.CourseService;
import dev.angelcruzl.courseapp.service.StudentService;
import dev.angelcruzl.courseapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentRestController {
  private final StudentService studentService;
  private final UserService userService;
  private final CourseService courseService;

  public StudentRestController(
    StudentService studentService,
    UserService userService,
    CourseService courseService
  ) {
    this.studentService = studentService;
    this.userService = userService;
    this.courseService = courseService;
  }

  @GetMapping
  public Page<StudentDTO> searchStudents(
    @RequestParam(name = "keyword", defaultValue = "") String keyword,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size
  ) {
    return studentService.loadStudentsByName(keyword, page, size);
  }

  @PostMapping
  public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
    User user = userService.loadUserByEmail(studentDTO.getUser().getEmail());
    if (user != null) throw new RuntimeException("Email already exists");

    return studentService.createStudent(studentDTO);
  }

  @GetMapping("/find")
  public StudentDTO findStudentByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
    return studentService.loadStudentByEmail(email);
  }

  @PutMapping("/{studentId}")
  public StudentDTO updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
    studentDTO.setId(studentId);
    return studentService.updateStudent(studentDTO);
  }

  @DeleteMapping("/{studentId}")
  public void deleteStudent(@PathVariable Long studentId) {
    studentService.deleteStudent(studentId);
  }

  @GetMapping("/{studentId}/courses")
  public Page<CourseDTO> getSubscribedCoursesByStudentId(
    @PathVariable Long studentId,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size
  ) {
    return courseService.fetchCoursesForStudent(studentId, page, size);
  }

  @GetMapping("/{studentId}/other-courses")
  public Page<CourseDTO> getNonSubscribedCoursesByStudentId(
    @PathVariable Long studentId,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size
  ) {
    return courseService.fetchNonEnrolledCoursesForStudent(studentId, page, size);
  }
}
