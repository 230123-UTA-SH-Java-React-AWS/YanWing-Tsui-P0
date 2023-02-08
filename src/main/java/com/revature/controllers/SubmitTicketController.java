package com.revature.controllers;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import com.revature.service.TicketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SubmitTicketController implements HttpHandler {

  private final TicketService ticketService = new TicketService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // TODO Auto-generated method stub
    String verb = exchange.getRequestMethod();
    String response = " ";

    switch (verb) {
      case "POST":
        postRequestSubmitTicket(exchange);
        break;

      case "GET":
        response = "Please, submit your new reimbursement tickets";
        exchange.sendResponseHeaders(200, response.getBytes().length);

      default:
        response = "Wrong HTTP Verb";
        exchange.sendResponseHeaders(404,response.getBytes().length);
        break;
    }

    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
    
  }

  private void postRequestSubmitTicket(HttpExchange exchange) throws IOException {
    InputStream is = exchange.getRequestBody();

    StringBuilder textBuilder = new StringBuilder();

    try(
      Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){

        int c = 0;

        while ((c = reader.read()) != -1) {
          textBuilder.append((char) c);
        }
    } 

    String response = ticketService.submitTicketToRepository(textBuilder.toString());

    exchange.sendResponseHeaders(200, response.getBytes().length);

    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }




  
}
