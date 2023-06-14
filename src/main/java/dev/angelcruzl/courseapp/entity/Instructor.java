package dev.angelcruzl.courseapp.entity;

import java.util.Objects;

public class Instructor {
  private Long id;
  private String firstName;
  private String lastName;
  private String summary;

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
