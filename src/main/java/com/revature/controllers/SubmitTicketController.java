package com.revature.controllers;

import java.io.IOException;

import javax.xml.ws.spi.http.HttpExchange;

import com.sun.net.httpserver.HttpHandler;

public class SubmitTicketController extends javax.xml.ws.spi.http.HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // TODO Auto-generated method stub
    String verb = exchange.getRequestMethod();

    switch (verb) {
      case "POST":
        postRequestSubmitTicket(exchange);
        break;
    
      default:
        break;
    }
    
  }

  private void postRequestSubmitTicket(HttpExchange exchange) {
    

  }
  
}
