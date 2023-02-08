package com.revature.model;

public class Ticket {
  // id for ticket
  private int manId;
  private int ticketId;
  private int empId;
  private float ticketAmount;
  private String ticketDescription;
  private String ticketStatus;

  public Ticket() {
    ticketStatus = "PENDDING";
  }

  public int getTicketId() {
    return ticketId;
  }

  public void setTicketId(int ticketId) {
    this.ticketId = ticketId;
  }

  public int getManId() {
    return manId;
  }

  public void setManId(int manId) {
    this.manId = manId;
  }

  public int getEmpId() {
    return empId;
  }

  public void setEmpId(int empId) {
    this.empId = empId;
  }

  public float getTicketAmount() {
    return ticketAmount;
  }

  public void setTicketAmount(float ticketAmount) {
    this.ticketAmount = ticketAmount;
  }

  public String getTicketDescription() {
    return ticketDescription;
  }

  public void setTicketDescription(String ticketDescription) {
    this.ticketDescription = ticketDescription;
  }

  public String getTicketStatus() {
    return ticketStatus;
  }

  public void setTicketStatus(String ticketStatus) {
    this.ticketStatus = ticketStatus;
  }

}
