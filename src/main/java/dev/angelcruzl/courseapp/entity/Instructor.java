package dev.angelcruzl.courseapp.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Instructor {
  private Long id;
  private String firstName;
  private String lastName;
  private String summary;
  private Set<Course> courses = new HashSet<>();
  private User user;

  public Instructor(
    String firstName,
    String lastName,
    String summary,
    User user
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.summary = summary;
    this.user = user;
  }

  public Instructor() {
  }

  @Override
  public String toString() {
    return "Instructor{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", summary='" + summary + '\'' +
      '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Set<Course> getCourses() {
    return courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Instructor that = (Instructor) o;
    return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(summary, that.summary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, summary);
  }
}
