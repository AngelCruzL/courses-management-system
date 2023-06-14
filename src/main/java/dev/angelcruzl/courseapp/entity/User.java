package dev.angelcruzl.courseapp.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
  private Long id;
  private String email;
  private String password;
  private Set<Role> roles = new HashSet<>();
  private Student student;
  private Instructor instructor;

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password);
  }
}
