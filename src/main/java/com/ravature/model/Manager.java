package com.ravature.model;

public class Manager extends User {

  private int manaId;

  // deflault contruction
  public Manager() {
    super();
    userRole = "MANAGER";
  }

  // Setter and Getter
  public int getManagerID() {
    return manaId;
  }

  public void setManagerID(int managerID) {
    this.manaId = managerID;
  }

}
