package dev.angelcruzl.courseapp.dto;

public class CourseDTO {
  private Long id;
  private String title;
  private String description;
  private String duration;
  private InstructorDTO instructor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public InstructorDTO getInstructor() {
    return instructor;
  }

  public void setInstructor(InstructorDTO instructor) {
    this.instructor = instructor;
  }
}
