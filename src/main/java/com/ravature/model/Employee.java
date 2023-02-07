package com.ravature.model;

public class Employee extends User {

  private int empId;

  public Employee() {
    super();
    userRole = "EMPLOYEE";
  }

  // Setter and Getter
  public int getEmpID() {
    return empId;
  }

  public void setEmpID(int empID) {
    this.empId = empID;
  }

}
