package dev.angelcruzl.courseapp.utility;

import dev.angelcruzl.courseapp.entity.Instructor;
import dev.angelcruzl.courseapp.entity.Role;
import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.repository.InstructorRepository;
import dev.angelcruzl.courseapp.repository.RoleRepository;
import dev.angelcruzl.courseapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class OperationUtility {
  public static void usersOperations(UserRepository userRepository) {
    createUsers(userRepository);
    updateUser(userRepository);
    deleteUser(userRepository);
    fetchUsers(userRepository);
  }

  public static void rolesOperations(RoleRepository roleRepository, UserRepository userRepository) {
    createRoles(roleRepository);
    updateRole(roleRepository);
    deleteRole(roleRepository);
    fetchRoles(roleRepository);
    assignRolesToUsers(roleRepository, userRepository);
  }

  public static void instructorsOperations(
    InstructorRepository instructorRepository,
    UserRepository userRepository,
    RoleRepository roleRepository
  ) {
    createInstructors(instructorRepository, userRepository, roleRepository);
    updateInstructor(instructorRepository);
    deleteInstructor(instructorRepository);
    fetchInstructors(instructorRepository);
  }

  private static void createUsers(UserRepository userRepository) {
    User angelCruz = new User("angelcruz@test.com", "secret123");
    userRepository.save(angelCruz);

    User luisLara = new User("luislara@test.com", "secret123");
    userRepository.save(luisLara);

    User juanPerez = new User("juanperez@test.com", "secret123");
    userRepository.save(juanPerez);

    User manuelLopez = new User("manulopez@test.com", "secret123");
    userRepository.save(manuelLopez);
  }

  private static void updateUser(UserRepository userRepository) {
    User userToUpdate = userRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("User not found"));
    userToUpdate.setEmail("jperez@update.com");
    userRepository.save(userToUpdate);
  }

  private static void deleteUser(UserRepository userRepository) {
    User userToDelete = userRepository.findById(4L).orElseThrow(() -> new EntityNotFoundException("User not found"));
    userRepository.delete(userToDelete);
  }

  private static void fetchUsers(UserRepository userRepository) {
    userRepository.findAll().forEach(user -> System.out.println(user.toString()));
  }

  private static void createRoles(RoleRepository roleRepository) {
    Role adminRole = new Role("ADMIN");
    roleRepository.save(adminRole);

    Role instructorRole = new Role("INSTRUCTOR");
    roleRepository.save(instructorRole);

    Role studentRole = new Role("STUDENT");
    roleRepository.save(studentRole);
  }

  private static void updateRole(RoleRepository roleRepository) {
    Role roleToUpdate = roleRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    roleToUpdate.setName("INSTRUCTOR_UPDATED");
    roleRepository.save(roleToUpdate);
  }

  private static void deleteRole(RoleRepository roleRepository) {
    roleRepository.deleteById(3L);
  }

  private static void fetchRoles(RoleRepository roleRepository) {
    roleRepository.findAll().forEach(role -> System.out.println(role.toString()));
  }

  public static void assignRolesToUsers(RoleRepository roleRepository, UserRepository userRepository) {
    Role role = roleRepository.findByName("ADMIN");

    if (role == null) throw new EntityNotFoundException("Role not found");

    List<User> users = userRepository.findAll();
    users.forEach(user -> {
      user.assignRoleToUser(role);
      userRepository.save(user);
    });
  }

  public static void createInstructors(
    InstructorRepository instructorRepository,
    UserRepository userRepository,
    RoleRepository roleRepository
  ) {
    Role instructorRole = roleRepository.findByName("INSTRUCTOR");

    if (instructorRole == null) throw new EntityNotFoundException("Role not found");

    User armandoUser = new User("armando@instructor.com", "secret123");
    armandoUser.assignRoleToUser(instructorRole);
    userRepository.save(armandoUser);
    Instructor armandoInstructor = new Instructor("Armando", "Lopez", "The best instructor ever", armandoUser);
    instructorRepository.save(armandoInstructor);

    User victorUser = new User("victor@instructor.com", "secret123");
    victorUser.assignRoleToUser(instructorRole);
    userRepository.save(victorUser);
    Instructor victorInstructor = new Instructor("Victor", "Lopez", "My favorite instructor ever", victorUser);
    instructorRepository.save(victorInstructor);
  }

  private static void updateInstructor(InstructorRepository instructorRepository) {
    Instructor instructorToUpdate = instructorRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
    instructorToUpdate.setSummary("Recently certified in cloud technologies");
    instructorRepository.save(instructorToUpdate);
  }

  private static void deleteInstructor(InstructorRepository instructorRepository) {
    instructorRepository.deleteById(2L);
  }

  private static void fetchInstructors(InstructorRepository instructorRepository) {
    instructorRepository.findAll().forEach(instructor -> System.out.println(instructor.toString()));
  }
}
