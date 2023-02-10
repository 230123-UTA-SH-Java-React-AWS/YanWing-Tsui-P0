package com.revature.repository;

import java.math.BigDecimal;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.codehaus.jackson.JsonNode;

import com.revature.model.Ticket;
import com.revature.utils.ConnectionUtil;

public class TicketRepository {

  public void SubmitTicket(Ticket ticket) throws SQLException {

    // sql code that input information to the ticket table
    String sql = "insert into ticket (ticketAmount, ticketDescription, status, username) values ( ?, ?, ?, ?) ";

    // Connect to the database
    try (Connection con = ConnectionUtil.getConnection()) {
      try {
        // preparedstatement is to let the database run the sql code we provide with no
        // values which mean we have to provide the values
        PreparedStatement prstmt = con.prepareStatement(sql);

        // get all values in ticket
        prstmt.setBigDecimal(1, ticket.getTicketAmount());
        prstmt.setString(2, ticket.getTicketDescription());
        prstmt.setString(3, ticket.getStatus().toUpperCase());
        prstmt.setString(4, ticket.getUsername());

        // execute to save the register information to the connection of database
        prstmt.execute();

      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public ArrayList<Ticket> getPendingTicket() {
    String sql = "select * from ticket where status = ?";
    ArrayList<Ticket> listOfTicket = new ArrayList<Ticket>();

    try (Connection con = ConnectionUtil.getConnection()) {
      PreparedStatement prstmt = con.prepareStatement(sql);
      prstmt.setString(1, "PENDING");
      ResultSet rs = prstmt.executeQuery();

      while (rs.next()) {
        Ticket newTicket = new Ticket();
        newTicket.setTicketID(rs.getInt(1));
        newTicket.setTicketAount(rs.getBigDecimal(2));
        newTicket.setTicketDescription(rs.getString(3));
        newTicket.setStatus(rs.getString(4));
        newTicket.setUsername(rs.getString(5));

        listOfTicket.add(newTicket);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listOfTicket;
  }

  public ArrayList<Ticket> getMyTicket(JsonNode JN) {
    String sql = "select * from ticket where username = ?";
    ArrayList<Ticket> listOfTicket = new ArrayList<Ticket>();

    try (Connection con = ConnectionUtil.getConnection()) {
      PreparedStatement prstmt = con.prepareStatement(sql);
      prstmt.setString(1, JN.get("username").asText());
      ResultSet rs = prstmt.executeQuery();

      while (rs.next()) {
        Ticket newTicket = new Ticket();
        newTicket.setTicketID(rs.getInt(1));
        newTicket.setTicketAount(rs.getBigDecimal(2));
        newTicket.setTicketDescription(rs.getString(3));
        newTicket.setStatus(rs.getString(4));
        newTicket.setUsername(rs.getString(5));

        listOfTicket.add(newTicket);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listOfTicket;
  }

  public ArrayList<Ticket> getChoiceTicket(JsonNode JN) {
    String sql = "select * from ticket where status = ? and username = ?";
    ArrayList<Ticket> listOfTicket = new ArrayList<>();

    try (Connection con = ConnectionUtil.getConnection()) {
      PreparedStatement prstmt = con.prepareStatement(sql);

      prstmt.setString(1, JN.get("status").asText());
      prstmt.setString(2, JN.get("username").asText());

      ResultSet rs = prstmt.executeQuery();
      while (rs.next()) {
        Ticket newTicket = new Ticket();
        newTicket.setTicketID(rs.getInt(1));
        newTicket.setTicketAount(rs.getBigDecimal(2));
        newTicket.setTicketDescription(rs.getString(3));
        newTicket.setStatus(rs.getString(4));
        newTicket.setUsername(rs.getString(5));

        listOfTicket.add(newTicket);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listOfTicket;
  }

  public boolean Update(JsonNode JN) {
    String sql = "update ticket set status = ? where ticketid = ?";
    try (Connection con = ConnectionUtil.getConnection()) {

      PreparedStatement prstmt = con.prepareStatement(sql);


      prstmt.setString(1, JN.get("status").asText().toUpperCase());
      prstmt.setInt(2, JN.get("ticketid").asInt());

      prstmt.execute();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return true;
  }

  public boolean getStatusbyId(JsonNode JN) throws SQLException {
    String sql = "select state from ticket where ticketid = ?";

    try (Connection con = ConnectionUtil.getConnection()) {
      String status = "";
      PreparedStatement prstmt = con.prepareStatement(sql);

      ArrayList<Ticket> listOfTicket = new ArrayList<>();

      prstmt.setInt(1, JN.get("ticketid").asInt());

      ResultSet rs = prstmt.executeQuery();

      while (rs.next()) {
        Ticket newTicket = new Ticket();
        newTicket.setStatus(rs.getString(4));

        listOfTicket.add(newTicket);
      }

      status = listOfTicket.toString().toUpperCase();
      if (status == "PENDING") {
        return true;
      }
    }
    return false;

    // public void Update1(int TicketId, String newStatus){
    // int x;

    // }
    // }

    // public void Update1(int asInt, String asText) {
    // }

    // public String Ticketfinalize(int ticketId, Ticket.ticketStatus newStatus) {
    // if (newStatus == null) {
    // return "TicketStatus cannot be null!";
    // }
    // if (newStatus == Ticket.ticketStatus.PENDING) {
    // return "Tickets is already finalize!";
    // }

    // Ticket listOfTicket = getTicketByID(ticketId);
    // if (listOfTicket == null) {
    // return "No ticket for this employee";
    // }
    // if (listOfTicket.ticketStatus != Ticket.ticketStatus.PENDING) {
    // return "Ticket has been finalized!";
    // }

    // String sql = "update ticket set ticketStatus = ? where ticketId = ?";
    // try (Connection con = ConnectionUtil.getConnection()) {
    // PreparedStatement prst = con.prepareStatement(sql);
    // prst.setString(1, newStatus.toString());
    // prst.setInt(2, ticketId);
    // prst.execute();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // return "Done Wrong";
    // }
    // return "Ticket updated!";

    // }

    // public Ticket getTicketByID(int ticketId) {
    // List<Ticket> listOfList = new ArrayList<>();
    // Ticket myTicket = null;

    // String sql = "select * from ticket where ticketid = ?";

    // try (Connection con = ConnectionUtil.getConnection()) {
    // PreparedStatement prst = con.prepareStatement(sql);
    // prst.setInt(1, ticketId);
    // ResultSet rs = prst.executeQuery();
    // Ticket.ticketStatus ticketStatus;

    // while (rs.next()) {
    // String statusResult = rs.getString(2);
    // if (statusResult.equalsIgnoreCase("pending")) {
    // ticketStatus = Ticket.ticketStatus.PENDING;
    // } else if (statusResult.equalsIgnoreCase("approved")) {
    // ticketStatus = Ticket.ticketStatus.APPROVED;
    // } else if (statusResult.equalsIgnoreCase("denied")) {
    // ticketStatus = Ticket.ticketStatus.DECLINED;
    // }

    // Ticket listTicket = new Ticket();

    // listTicket.setTicketAount(rs.getBigDecimal(3));
    // listTicket.setTicketDescription(rs.getString(4));
    // listTicket.setUsername(rs.getString(5));

    // listOfList.add(listTicket);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // return null;
    // }
    // if (listOfList.size() > 0)
    // myTicket = listOfList.get(0);
    // return myTicket;
    // }
    // public List<Ticket> FindPreviousSubmission(int userId) {

    // List<Ticket> listOfTicket = new ArrayList<>();

    // String sql = "select * from ticket where empid = ?";

    // try (Connection con = ConnectionUtil.getConnection()) {

    // PreparedStatement prstmt = con.prepareStatement(sql);

    // prstmt.setInt(1, userId);

    // ResultSet rs = prstmt.executeQuery();

    // while (rs.next()) {
    // Ticket newTicket = new Ticket();

    // newTicket.setEmpId(rs.getInt(1));
    // newTicket.setTicketId(rs.getInt(2));
    // newTicket.setTicketAmount(rs.getFloat(3));
    // newTicket.setTicketDescription(rs.getString(4));
    // newTicket.setTicketStatus(rs.getString(5));

    // listOfTicket.add(newTicket);
    // }
    // } catch (SQLException e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // }

    // return listOfTicket;
    // }

    // public List<Ticket> GetAllPedding() {
    // String sql = "select * from ticket where ticketstatus = 'PENDDING'";

    // List<Ticket> listOfTickets = new ArrayList<Ticket>();

    // try (Connection con = ConnectionUtil.getConnection()) {

    // Statement stmt = con.createStatement();

    // ResultSet rs = stmt.executeQuery(sql);

    // while (rs.next()) {
    // Ticket newTicket = new Ticket();

    // newTicket.setTicketId(rs.getInt(1));
    // newTicket.setEmpId(rs.getInt(2));
    // newTicket.setTicketAmount(rs.getFloat(3));
    // newTicket.setTicketDescription(rs.getString(4));
    // newTicket.setTicketStatus(rs.getString(5));

    // listOfTickets.add(newTicket);
    // }

    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // return listOfTickets;
    // }
  }
}
