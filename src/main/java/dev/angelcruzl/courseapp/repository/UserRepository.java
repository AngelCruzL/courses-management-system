package dev.angelcruzl.courseapp.repository;

import dev.angelcruzl.courseapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
  User findByEmail(String email);
}
