package org.exoplatform.addons.portlets.springmvc;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Contact {

  private Long id;

  @NotNull
  @NotEmpty
  @Pattern(regexp="[\\p{IsAlphabetic}\\s]*")
  private String firstname;

  @NotNull
  @NotEmpty
  @Pattern(regexp="[\\p{IsAlphabetic}\\s]*")
  private String lastname;

  @Pattern(regexp="[\\p{IsAlphabetic}\\s]*")
  private String displayName;

  @Email
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
