package com.revature.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Ticket;
import com.revature.repository.TicketRepository;

public class TicketService {

  private final TicketRepository ticketrepo = new TicketRepository();
  private final ObjectMapper mapper = new ObjectMapper();

  public String submitTicketToRepository(String ticketJson) {

    String response = "";

    try {
      Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);

      
        response = ticketrepo.SubmitTicket(newTicket);
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

    return response;

  }


  public String SearchByID(String ticketJson) {

    List<Ticket> response;
    String re1 ="";
    try {
      Ticket newTicket = mapper.readValue(ticketJson, Ticket.class);

      
        response = ticketrepo.FindPreviousSubmission(newTicket.getEmpId());
        re1 = getPreSub(response);
     } catch (JsonMappingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     } catch (IOException e) {
      // TODO: handle exception
      e.printStackTrace();
     }

    return re1;

  }

  public String getPreSub(List<Ticket> response){
    List<Ticket> listOfTicket = new ArrayList<>();

    String jsonString = "";
    try{
      jsonString = mapper.writeValueAsString(listOfTicket);
    }catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jsonString;
    
  }

  public String getAllPedding(){
    List<Ticket> listOfUser = ticketrepo.GetAllPedding();

    String jsonString = "";
    try{
        jsonString = mapper.writeValueAsString(listOfUser);
    }catch (JsonMappingException e){
      e.printStackTrace();
    }catch (JsonGenerationException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }

    return jsonString;
  }
}
