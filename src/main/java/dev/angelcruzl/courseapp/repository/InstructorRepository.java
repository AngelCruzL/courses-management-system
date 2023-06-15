package dev.angelcruzl.courseapp.repository;

import dev.angelcruzl.courseapp.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
  @Query(value = "SELECT i FROM Instructor AS i WHERE i.firstName LIKE %:name% OR i.lastName LIKE %:name%")
  Page<Instructor> findInstructorsByName(@Param("name") String name, PageRequest pageRequest);

  @Query(value = "SELECT i FROM Instructor AS i WHERE i.user.email =:email")
  Instructor findInstructorByEmail(@Param("email") String email);
}
