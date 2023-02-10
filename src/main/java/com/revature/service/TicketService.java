package com.revature.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.repository.EmployeeRepository;
import com.revature.repository.TicketRepository;

public class TicketService {
  public final EmployeeRepository empRpo = new EmployeeRepository();
  public final TicketRepository ticketrepo = new TicketRepository();

  public final ObjectMapper mapper = new ObjectMapper();

  public String submitTicketToRepository(String ticketJson) {

    try {
      Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);

      if (empRpo.getAllUser().contains(newTicket.getUsername())) {
        ticketrepo.SubmitTicket(newTicket);
        return "Ticket Submited! Waiting for approval!";
      } else {
        return "Account is not existed! Please register!";
      }

    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO: handle exception
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return "Gone Wrong";

  }

  public String viewTicket(String TicketJson) {
    String response = "";

    try {
      JsonNode treeNode = mapper.readTree(TicketJson);
      String usernameNode = treeNode.get("username").asText();

      if (empRpo.checkManager(usernameNode)) {
        if (empRpo.getAllUser().contains(usernameNode)) {
          ArrayList<Ticket> listOfTicket = new ArrayList<>();
          listOfTicket = ticketrepo.getPendingTicket();
          response = mapper.writeValueAsString(listOfTicket);
        } else {
          return "You are not our employee or manager!";
        }
      } else {
        return "Only manager can view ticket table!";
      }
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return response;
  }

  public String viewMyTicket(String ticketString) {
    String response = "";

    try {
      JsonNode myNode = mapper.readTree(ticketString);
      if (myNode.findValue("status") == null) {
        ArrayList<Ticket> listOfTicket = ticketrepo.getMyTicket(myNode);
        response = mapper.writeValueAsString(listOfTicket);
        return response;
      } else {
        ArrayList<Ticket> listOfTicket = ticketrepo.getChoiceTicket(myNode);
        response = mapper.writeValueAsString(listOfTicket);
        return response;
      }
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return response;
  }

  public String ticketProcess(String ticketString) {
    try {
      JsonNode myNode = mapper.readTree(ticketString);
      String userNode = myNode.get("username").asText();
      
      boolean result = false;
      
      JsonNode idNode = myNode.get("ticketid");
      int s = idNode.getIntValue();
      result = getStatusbyId(s);
      JsonNode newStateNode = myNode.get
      ("status");

      String x = newStateNode.getTextValue().toUpperCase();
      String y = "PENDING";
      if(x == y){
        result = true;
      }
      
      if (empRpo.checkManager(userNode)) {
        if (result == true) {
          result = ticketrepo.Update(myNode);
          return "Ticket has been updated";
        } else {
          return "Cannot change the status finalized.";
        }
      } else {
        return "You are not Manager! You cannot process Ticket!";
      }

    } catch (

    JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "Done Wrong";
  }

  private boolean getStatusbyId(int s) {
    return false;
  }

}

// // public String processTicket(String TicketString){
// try {
// JsonNode myNode = mapper.readTree(TicketString);
// if(empRpo.getAllUser().contains(myNode.get("username").asText())){
// if(empRpo.checkManager(myNode.get("username").asText())){
// ticketrepo.Update1(myNode.get("TicketId").asInt(),
// myNode.get("newStatus").asText());
// return "Update Sucessfully!";
// }else{
// return "Only Manager are allowed to access";
// }
// }
// else{
// return "Account not exsit!";
// }

// } catch (JsonProcessingException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return "Gone Wrong";
// }

// public static String finlize(String JsonString){
// JsonNode JN;
// try{
// ObjectMapper OM = new ObjectMapper();
// JN = OM.readTree(JsonString);
// } catch(IOException e){
// e.printStackTrace();
// }
// JsonNode usernameNode = JN.get("username");
// JsonNode passNode = JN.get("userPassword");
// JsonNode ticketIDNode = JN.get("ticketId");
// JsonNode newStatusNode = JN.get("newStatus");

// String username = usernameNode.asText();
// String password = passNode.asText();
// int ticketId = ticketIDNode.asInt();
// Ticket.ticketStatus newStatus = switch(newStatusNode.asText().toLowerCase()){
// case: "approved"
// Ticket.ticketStatus.APPROVED;
// break;
// case:
// }
// }

// public final finalizeTicket(String TicketString){
// JsonNode JN;

// try {
// JN = mapper.readTree(TicketString);
// } catch (Exception e) {
// // TODO: handle exception
// }

// JsonNode usernameNode = JN.get("username");
// JsonNode passwordNode = JN.get("ticketPassword");
// JsonNode ticketStatusNode = JN.get("newStatus");
// JsonNode ticketIdNode = JN.get("ticketId");
// if(usernameNode == null|| passwordNode == null || ticketStatusNode == null ||
// ticketIdNode == null){
// return "Cannot be blank in username, password, ticketId and newStatus";
// }
// String username = usernameNode.asText();
// String ticketPassword = passwordNode.asText();
// int ticketId = ticketIdNode.asInt();
// Ticket.ticketStatus newStatus;
// if(ticketStatusNode.asText().equalsIgnoreCase("denclined")){
// newStatus = Ticket.ticketStatus.DECLINED;
// }else if(ticketStatusNode.asText().equalsIgnoreCase("approved")){
// newStatus = Ticket.ticketStatus.APPROVED;
// }else{
// return "Cannot change Pending to Pending";
// }

// User newuser = new User();
// Employee me = empRpo.getEmployeeByUsername(username);
// if(me == null){
// return "";
// }
// if(! me.getUsername().equals(username)|| !
// me.getUserPassword().equals(ticketPassword)){

// return " ";
// }

// TicketRepository ticketRepo = new TicketRepository();
// return ticketRepo.Ticketfinalize(ticketId, newStatus)

// }
// public String SearchByID(String ticketJson) {

// List<Ticket> response;
// String re1 ="";
// try {
// Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);

// response = ticketrepo.FindPreviousSubmission(newTicket.getEmpId());
// re1 = getPreSub(response);
// } catch (JsonMappingException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (IOException e) {
// // TODO: handle exception
// e.printStackTrace();
// }

// return re1;

// }

// public String getPreSub(List<Ticket> response){
// List<Ticket> listOfTicket = new ArrayList<>();

// String jsonString = "";
// try{
// jsonString = mapper.writeValueAsString(listOfTicket);
// }catch (JsonMappingException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return jsonString;

// }

// public String getAllPedding(){
// List<Ticket> listOfUser = ticketrepo.GetAllPedding();

// String jsonString = "";
// try{
// jsonString = mapper.writeValueAsString(listOfUser);
// }catch (JsonMappingException e){
// e.printStackTrace();
// }catch (JsonGenerationException e){
// e.printStackTrace();
// }catch (IOException e){
// e.printStackTrace();
// }

// return jsonString;
// }
