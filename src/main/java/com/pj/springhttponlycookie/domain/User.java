package com.pj.springhttponlycookie.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


@Data
@Entity
@Table(name = "`user`")
public class User implements Serializable
{
  private static final long serialVersionUID = -8660096501838844304L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "credentials_non_expired")
  private Boolean credentialsNonExpired;

  @Column(name = "account_non_locked")
  private Boolean accountNonLocked;

  @Column(name = "account_non_expired")
  private Boolean accountNonExpired;

  @Column(name = "password")
  private String password;

  public User()
  {
  }

  public User(String username, String password, String email, String firstName, String lastName, Boolean active)
  {
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.active = active;
    this.accountNonExpired = active;
    this.accountNonLocked = active;
    this.credentialsNonExpired = active;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(super.hashCode(), id, username, active);
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    User user = (User) o;
    return id.equals(user.id) && username.equals(user.username) && Objects.equals(active, user.active);
  }

}
