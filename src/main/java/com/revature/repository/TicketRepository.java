package com.revature.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

 

  public List<Ticket> FindPreviousSubmission(int userId){

    List<Ticket> listOfTicket = new ArrayList<>();

    String sql = "select * from ticket where empid = ?";

    try(Connection con = ConnectionUtil.getConnection()){

      PreparedStatement prstmt = con.prepareStatement(sql);

      prstmt.setInt(1, userId);

      ResultSet rs = prstmt.executeQuery();

      while (rs.next()) {
        Ticket newTicket = new Ticket();

        newTicket.setEmpId(rs.getInt(1));
        newTicket.setTicketId(rs.getInt(2));
        newTicket.setTicketAmount(rs.getFloat(3));
        newTicket.setTicketDescription(rs.getString(4));
        newTicket.setTicketStatus(rs.getString(5));

        listOfTicket.add(newTicket);
      }
    }catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return listOfTicket;
  }

  public List<Ticket> GetAllPedding(){
    String sql = "select * from ticket where ticketstatus = 'PENDDING'";

        List<Ticket> listOfTickets = new ArrayList<Ticket>();

        try(Connection con = ConnectionUtil.getConnection()){

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Ticket newTicket = new Ticket();
                
                newTicket.setTicketId(rs.getInt(1));
                newTicket.setEmpId(rs.getInt(2));
                newTicket.setTicketAmount(rs.getFloat(3));
                newTicket.setTicketDescription(rs.getString(4));
                newTicket.setTicketStatus(rs.getString(5));

                listOfTickets.add(newTicket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfTickets;
    }
  }
  
