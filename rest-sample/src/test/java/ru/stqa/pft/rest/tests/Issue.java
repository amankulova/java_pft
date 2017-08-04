package ru.stqa.pft.rest.tests;

public class Issue {

  private int id;
  private String subject;
  private String description;
  private String state_name;

  public int getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public String getDescription() {
    return description;
  }

  public String getStatus() {
    return state_name;
  }


  public Issue whitId(int id) {
    this.id = id;
    return this;
  }

  public Issue whitStatus(String state_name) {
    this.state_name = state_name;
    return this;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }


  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Issue issue = (Issue) o;

    if (id != issue.id) return false;
    if (!subject.equals(issue.subject)) return false;
    return description.equals(issue.description);

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + subject.hashCode();
    result = 31 * result + description.hashCode();
    return result;
  }
}