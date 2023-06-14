package dev.angelcruzl.courseapp.repository;

import dev.angelcruzl.courseapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
