package dev.angelcruzl.courseapp.service;

import dev.angelcruzl.courseapp.dto.StudentDTO;
import dev.angelcruzl.courseapp.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
  Student loadStudentById(Long studentId);

  Page<StudentDTO> loadStudentsByName(String name, int page, int size);

  StudentDTO loadStudentByEmail(String email);

  StudentDTO createStudent(StudentDTO studentDTO);

  StudentDTO updateStudent(StudentDTO studentDTO);

  void deleteStudent(Long studentId);
}
