package dev.angelcruzl.courseapp.utility;

import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

public class OperationUtility {
  public static void usersOperations(UserRepository userRepository) {
    createUsers(userRepository);
    updateUser(userRepository);
    deleteUser(userRepository);
    fetchUsers(userRepository);
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
}
