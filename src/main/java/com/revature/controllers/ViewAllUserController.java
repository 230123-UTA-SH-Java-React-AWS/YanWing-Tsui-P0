package com.revature.controllers;

import java.io.IOException;
import java.io.OutputStream;

import com.revature.service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ViewAllUserController implements HttpHandler{

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // TODO Auto-generated method stub
    String verb = exchange.getRequestMethod();

    switch (verb) {
      case "GET":
        getRequestViewAllUser(exchange);
        break;
    
      default:
        break;
    }
    
  }

  private void getRequestViewAllUser(HttpExchange exchange) throws IOException {
    EmployeeService empServer = new EmployeeService();
    String jsonCurrentList = empServer.getAllUser();

    exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

    OutputStream os = exchange.getResponseBody();
    os.write(jsonCurrentList.getBytes());
    os.close();
  }

  
}