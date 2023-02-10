package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


import com.revature.service.TicketService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ViewTicketController implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // TODO Auto-generated method stub
    String verb = exchange.getRequestMethod();

    switch (verb) {
      case "POST":
        postRequest(exchange);

        break;

      default:
        break;
    }

  }

  private void postRequest(HttpExchange exchange) throws IOException {

    InputStream is = exchange.getRequestBody();
    String response;
    StringBuilder textbuilder = new StringBuilder();

    try (
        Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
      int c = 0;

      while ((c = reader.read()) != -1) {
        textbuilder.append((char) c);
      }
    }

    TicketService ticketService = new TicketService();

    response = ticketService.viewTicket(textbuilder.toString());

    exchange.sendResponseHeaders(200, response.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}