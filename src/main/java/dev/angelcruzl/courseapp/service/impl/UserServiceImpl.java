package dev.angelcruzl.courseapp.service.impl;

import dev.angelcruzl.courseapp.entity.Role;
import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.repository.RoleRepository;
import dev.angelcruzl.courseapp.repository.UserRepository;
import dev.angelcruzl.courseapp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public User loadUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User createUser(String email, String password) {
    return userRepository.save(new User(email, password));
  }

  @Override
  public void assignRoleToUser(String email, String roleName) {
    User user = loadUserByEmail(email);
    Role role = roleRepository.findByName(roleName);
    user.assignRoleToUser(role);
  }
}
