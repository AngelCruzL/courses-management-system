package dev.angelcruzl.courseapp.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Basic
  @Column(name = "first_name", nullable = false, length = 50)
  private String firstName;

  @Basic
  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Basic
  @Column(name = "level", nullable = false, length = 64)
  private String level;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
  private Set<Course> courses = new HashSet<>();

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  public Student(
    String firstName,
    String lastName,
    String level,
    User user
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.level = level;
    this.user = user;
  }

  public Student() {
  }

  @Override
  public String toString() {
    return "Student{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", level='" + level + '\'' +
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

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
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
    Student student = (Student) o;
    return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(level, student.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, level);
  }
}
