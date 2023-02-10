package com.revature.model;

public class User {
  private int userId;
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
