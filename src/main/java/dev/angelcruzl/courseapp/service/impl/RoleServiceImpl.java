package dev.angelcruzl.courseapp.service.impl;

import dev.angelcruzl.courseapp.entity.Role;
import dev.angelcruzl.courseapp.repository.RoleRepository;
import dev.angelcruzl.courseapp.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Role createRole(String roleName) {
    return roleRepository.save(new Role(roleName));
  }
}
