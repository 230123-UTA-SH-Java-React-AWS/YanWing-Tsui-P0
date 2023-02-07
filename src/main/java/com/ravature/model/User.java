package com.ravature.model;

public class User {
  private String userFName;
  private String userLName;
  private String userEmail;
  private String userPassword;
  protected String userRole;

  protected User() {
    userRole = "EMPLOYEE";
  }

  // Getter and Setter
  public String getUserFName() {
    return userFName;
  }

  public void setUserFName(String userFName) {
    this.userFName = userFName;
  }

  public String getUserLName() {
    return userLName;
  }

  public void setUserLName(String userLName) {
    this.userLName = userLName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

}
