package dev.angelcruzl.courseapp.entity;

import java.util.Objects;

public class Course {
  private Long id;
  private String title;
  private String description;
  private String duration;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(description, course.description) && Objects.equals(duration, course.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, duration);
  }
}
