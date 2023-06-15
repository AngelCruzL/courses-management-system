package dev.angelcruzl.courseapp;

import dev.angelcruzl.courseapp.repository.*;
import dev.angelcruzl.courseapp.utility.OperationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseAppApplication implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private InstructorRepository instructorRepository;
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private RoleRepository roleRepository;

  public static void main(String[] args) {
    SpringApplication.run(CourseAppApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    OperationUtility.usersOperations(userRepository);
    OperationUtility.rolesOperations(roleRepository, userRepository);
//    OperationUtility.assignRolesToUsers(roleRepository, userRepository);
  }
}
