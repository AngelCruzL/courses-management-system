package dev.angelcruzl.courseapp.web;

import dev.angelcruzl.courseapp.dto.CourseDTO;
import dev.angelcruzl.courseapp.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CourseRestController {
  private final CourseService courseService;

  public CourseRestController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public Page<CourseDTO> searchCourses(
    @RequestParam(name = "keyword", defaultValue = "") String keyword,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size
  ) {
    return courseService.findCoursesByCourseTitle(keyword, page, size);
  }

  @PostMapping
  public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
    return courseService.createCourse(courseDTO);
  }

  @PutMapping("/{courseId}")
  public CourseDTO updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO) {
    courseDTO.setId(courseId);
    return courseService.updateCourse(courseDTO);
  }

  @DeleteMapping("/{courseId}")
  public void deleteCourse(@PathVariable Long courseId) {
    courseService.deleteCourse(courseId);
  }

  @PostMapping("/{courseId}/enroll/students/{studentId}")
  public void enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
    courseService.assignStudentToCourse(courseId, studentId);
  }
}
