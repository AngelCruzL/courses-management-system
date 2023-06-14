package dev.angelcruzl.courseapp.utility;

import dev.angelcruzl.courseapp.entity.Role;
import dev.angelcruzl.courseapp.entity.User;
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
}
