package dev.angelcruzl.courseapp.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Course {
  private Long id;
  private String title;
  private String description;
  private String duration;
  private Instructor instructor;
  private Set<Student> students = new HashSet<>();

  public Course(
    String title,
    String description,
    String duration,
    Instructor instructor
  ) {
    this.title = title;
    this.description = description;
    this.duration = duration;
    this.instructor = instructor;
  }

  public Course() {
  }

  @Override
  public String toString() {
    return "Course{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", duration='" + duration + '\'' +
      '}';
  }

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

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

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
