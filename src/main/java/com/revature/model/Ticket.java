package com.revature.model;

import java.math.BigDecimal;

public class Ticket {
  // id for ticket
  private BigDecimal ticketAmount;
  private String ticketDescription;
  private String username;
  private int ticketID;
  private String status = "PENDING";



  public void setTicketAmount(BigDecimal ticketAmount) {
    this.ticketAmount = ticketAmount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigDecimal getTicketAmount() {
    return ticketAmount;
  }

  public void setTicketAount(BigDecimal ticketAmount) {
    this.ticketAmount = ticketAmount;
  }

  public String getTicketDescription() {
    return ticketDescription;
  }

  public void setTicketDescription(String ticketDescription) {
    this.ticketDescription = ticketDescription;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getTicketID() {
    return ticketID;
  }

  public void setTicketID(int ticketID) {
    this.ticketID = ticketID;
  }

  

}
