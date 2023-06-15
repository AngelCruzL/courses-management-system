package dev.angelcruzl.courseapp.web;

import dev.angelcruzl.courseapp.dto.CourseDTO;
import dev.angelcruzl.courseapp.dto.InstructorDTO;
import dev.angelcruzl.courseapp.entity.User;
import dev.angelcruzl.courseapp.service.CourseService;
import dev.angelcruzl.courseapp.service.InstructorService;
import dev.angelcruzl.courseapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorRestController {
  private final InstructorService instructorService;
  private final UserService userService;
  private final CourseService courseService;

  public InstructorRestController(
    InstructorService instructorService,
    UserService userService,
    CourseService courseService
  ) {
    this.instructorService = instructorService;
    this.userService = userService;
    this.courseService = courseService;
  }

  @GetMapping
  public Page<InstructorDTO> searchInstructors(
    @RequestParam(name = "keyword", defaultValue = "") String keyword,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size
  ) {
    return instructorService.findInstructorsByName(keyword, page, size);
  }

  @PostMapping
  public InstructorDTO createInstructor(@RequestBody InstructorDTO instructorDTO) {
    User user = userService.loadUserByEmail(instructorDTO.getUser().getEmail());
    if (user != null) throw new RuntimeException("Email already exists");

    return instructorService.createInstructor(instructorDTO);
  }

  @GetMapping("/all")
  public List<InstructorDTO> findAllInstructors() {
    return instructorService.fetchInstructors();
  }

  @GetMapping("/find")
  public InstructorDTO findInstructorByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
    return instructorService.loadInstructorByEmail(email);
  }

  @PutMapping("/{instructorId}")
  public InstructorDTO updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorDTO instructorDTO) {
    instructorDTO.setId(instructorId);
    return instructorService.updateInstructor(instructorDTO);
  }

  @DeleteMapping("/{instructorId}")
  public void deleteInstructor(@PathVariable Long instructorId) {
    instructorService.deleteInstructor(instructorId);
  }

  @GetMapping("/{instructorId}/courses")
  public Page<CourseDTO> findCoursesByInstructorId(
    @PathVariable Long instructorId,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @RequestParam(name = "size", defaultValue = "5") int size
  ) {
    return courseService.fetchCoursesForInstructor(instructorId, page, size);
  }
}
