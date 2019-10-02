package ru.stqa.pft.mantis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

//@XStreamAlias("user")
@Entity
@Table(name = "mantis_user_table")

public class UserData {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  public int getId() {
    return id;
  }

  public String getUserName() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUserName(String username) {
    this.username = username;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withPassword(String footer) {
    this.password = password;
    return this;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData groupData = (UserData) o;
    return id == groupData.id &&
            Objects.equals(username, groupData.username) &&
            Objects.equals(email, groupData.email) &&
            Objects.equals(password, groupData.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password);
  }
}