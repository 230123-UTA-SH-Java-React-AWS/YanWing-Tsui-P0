package com.revature.service;

import java.io.IOException;
import java.sql.SQLException;

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
}
