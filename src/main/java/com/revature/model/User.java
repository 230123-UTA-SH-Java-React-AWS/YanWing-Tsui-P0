package com.revature.model;

public class User {
  private int userId;
  private String userFName;
  private String userLName;
  private String userEmail;
  private String username;
  private String userPassword;
  protected String userRole;

  public User() {
    userRole = "EMPLOYEE";
  }

  // Getter and Setter

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

// {"userFName":"YanWnig","userLName":"Tsui","userEmail":"YWT1@gmail.com","username":
// "Yanwingtsui018", "userPassword": "123456789", "userRole": "MANAGER"}
