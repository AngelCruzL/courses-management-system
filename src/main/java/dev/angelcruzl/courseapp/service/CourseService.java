package dev.angelcruzl.courseapp.service;

import dev.angelcruzl.courseapp.dto.CourseDTO;
import dev.angelcruzl.courseapp.entity.Course;
import org.springframework.data.domain.Page;

public interface CourseService {
  Course loadCourseById(Long id);

  CourseDTO createCourse(CourseDTO courseDTO);

  CourseDTO updateCourse(CourseDTO courseDTO);

  Page<CourseDTO> findCoursesByCourseTitle(String keyword, int page, int size);

  void assignStudentToCourse(Long courseId, Long studentId);

  Page<CourseDTO> fetchCoursesForStudent(Long studentId, int page, int size);

  Page<CourseDTO> fetchNonEnrolledCoursesForStudent(Long studentId, int page, int size);

  void deleteCourse(Long id);

  Page<CourseDTO> fetchCoursesForInstructor(Long instructorId, int page, int size);
}
