package dev.angelcruzl.courseapp.service;

import dev.angelcruzl.courseapp.entity.User;

public interface UserService {
  User loadUserByEmail(String email);

  User createUser(String email, String password);

  void assignRoleToUser(String email, String roleName);
}
