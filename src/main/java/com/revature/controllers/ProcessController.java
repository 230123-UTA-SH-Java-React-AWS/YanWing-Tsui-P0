package com.revature.controllers;

import java.io.IOException;
import java.io.OutputStream;

import com.revature.service.TicketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProcessController implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // TODO Auto-generated method stub
    String verb = exchange.getRequestMethod();

    switch (verb) {
      case "GET":
        viewAllPeddingEmployee(exchange);
        break;
    
      default:
        break;
    }
    
  }

  private void viewAllPeddingEmployee(HttpExchange exchange) throws IOException {
    TicketService ticketServer = new TicketService();
    String jsonCurrentList = ticketServer.getAllPedding();

    exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

    OutputStream os = exchange.getResponseBody();
    os.write(jsonCurrentList.getBytes());
    os.close();
  }
  
}
