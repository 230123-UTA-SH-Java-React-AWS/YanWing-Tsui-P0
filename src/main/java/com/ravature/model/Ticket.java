package com.ravature.model;

public class Ticket {
  // id for ticket
  private int ticketId;

  // employee's id who hold the ticket
  private int empId;
  // the status of accept or deny registration ticket
  private String status;

  public int getTicketId() {
    return ticketId;
  }

  public void setTicketId(int ticketId) {
    this.ticketId = ticketId;
  }

  public int getEmpId() {
    return empId;
  }

  public void setEmpId(int empId) {
    this.empId = empId;
  }

  public String isStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
