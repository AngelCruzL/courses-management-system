package dev.angelcruzl.courseapp.runner;

import dev.angelcruzl.courseapp.dto.CourseDTO;
import dev.angelcruzl.courseapp.dto.InstructorDTO;
import dev.angelcruzl.courseapp.dto.StudentDTO;
import dev.angelcruzl.courseapp.dto.UserDTO;
import dev.angelcruzl.courseapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {
  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  @Autowired
  private InstructorService instructorService;

  @Autowired
  private CourseService courseService;

  @Autowired
  private StudentService studentService;

  @Override
  public void run(String... args) throws Exception {
    createRoles();
    createAdmin();
    createInstructors();
    createCourses();
    StudentDTO student = createStudent();
    assignCourseToStudent(student);
  }

  private void createRoles() {
    Arrays.asList("ADMIN", "INSTRUCTOR", "STUDENT")
      .forEach(role -> roleService.createRole(role));
  }

  private void createAdmin() {
    String email = "me@angelcruzl.dev";
    userService.createUser(email, "pass1234");
    userService.assignRoleToUser(email, "ADMIN");
  }

  private void createInstructors() {
    for (int i = 0; i < 10; i++) {
      InstructorDTO instructorDTO = new InstructorDTO();
      instructorDTO.setFirstName("Instructor-" + i + " First Name");
      instructorDTO.setLastName("Instructor-" + i + " Last Name");
      instructorDTO.setSummary("Instructor-" + i + " Summary");

      UserDTO userDTO = new UserDTO();
      userDTO.setEmail("instructor" + i + "@gmail.com");
      userDTO.setPassword("pass1234");

      instructorDTO.setUser(userDTO);
      instructorService.createInstructor(instructorDTO);
    }
  }

  private void createCourses() {
    for (int i = 0; i < 20; i++) {
      CourseDTO courseDTO = new CourseDTO();
      courseDTO.setTitle("Course-" + i + " Name");
      courseDTO.setDescription("Course-" + i + " Description");
      courseDTO.setDuration("1" + i + " hours");

      InstructorDTO instructorDTO = new InstructorDTO();
      instructorDTO.setId(1L);

      courseDTO.setInstructor(instructorDTO);
      courseService.createCourse(courseDTO);
    }
  }

  private StudentDTO createStudent() {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setFirstName("Angel");
    studentDTO.setLastName("Cruz");
    studentDTO.setLevel("Beginner");

    UserDTO userDTO = new UserDTO();
    userDTO.setEmail("test@angelcruzl.dev");
    userDTO.setPassword("pass1234");

    studentDTO.setUser(userDTO);
    return studentService.createStudent(studentDTO);
  }

  private void assignCourseToStudent(StudentDTO student) {
    courseService.assignStudentToCourse(1L, student.getId());
  }
}
