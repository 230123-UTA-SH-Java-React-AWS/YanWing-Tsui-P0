package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.model.Ticket;
import com.revature.utils.ConnectionUtil;

public class TicketRepository {
  
  public String SubmitTicket(Ticket ticket) throws SQLException{

    //sql code that input information to the ticket table
    String sql = "insert into ticket (empId, ticketAmount, ticketDescription, ticketStatus) values ( ?, ?, ?, ?) ";

    //Connect to the database
    try(Connection con = ConnectionUtil.getConnection()){
      try {
        // preparedstatement is to let the database run the sql code we provide with no values which mean we have to provide the values
        PreparedStatement prstmt = con.prepareStatement(sql);

        //get all values in ticket
        prstmt.setInt(1, ticket.getEmpId());
        prstmt.setFloat(2, ticket.getTicketAmount());
        prstmt.setString(3,ticket.getTicketDescription());
        prstmt.setString(4,ticket.getTicketStatus());

        // execute to save the register information to the connection of database
        prstmt.execute();

      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return "Something wrong with your SQL";
      }
    }
    return "Ticket Submitted, Waiting for approval";
  }
  
}
