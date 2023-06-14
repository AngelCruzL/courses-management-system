package dev.angelcruzl.courseapp.repository;

import dev.angelcruzl.courseapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
  @Query(value = "SELECT s FROM Student AS s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
  List<Student> findStudentsByName(@Param("name") String name);

  @Query(value = "SELECT s FROM Student AS s WHERE s.user.email =:email")
  Student findStudentByEmail(String email);
}
