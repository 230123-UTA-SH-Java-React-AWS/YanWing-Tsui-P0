package com.revature.model;

public class Ticket {
  // id for ticket
  private int ticketId;
  private int empId;
  private int ticketAmount;
  private String ticketDescription;
  private String ticketStatus;

  public Ticket(){
    ticketStatus = "PENDDING";
  }

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

  public int getTicketAmount() {
    return ticketAmount;
  }

  public void setTicketAmount(int ticketAmount) {
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
