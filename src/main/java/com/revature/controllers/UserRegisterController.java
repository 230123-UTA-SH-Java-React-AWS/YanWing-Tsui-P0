package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserRegisterController implements HttpHandler {

  private final EmployeeService empService = new EmployeeService();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // TODO Auto-generated method stub
    String verb = exchange.getRequestMethod();
    String response = "";
    switch (verb) {
      case "POST":
        postRequestCreateNewEmployeeAccount(exchange);
        break;
      
      case "GET":
        response = "This is Register Page!";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        break;

      default:
        response = "Wrong HTTP Verb";
        exchange.sendResponseHeaders(404,response.getBytes().length);
        break;
    }

    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();

  }


  private void postRequestCreateNewEmployeeAccount(HttpExchange exchange) throws IOException {
    // InputStream has a bunch of bytes
    InputStream is = exchange.getRequestBody();

    // Convert the inputStream into String
    // StringBuilder is like a mutable version of String
    StringBuilder texBuilder = new StringBuilder();

    // Converts our binary into letters
    try (
        Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
      int c = 0;
      // read() method from BufferReader will give a -1 once there is no more letter
      // left
      // loop
      while ((c = reader.read()) != -1) {
        // adds the letter to your text
        texBuilder.append((char) c);
      }
    }

    String result = empService.saveToEmployeeRepository(texBuilder.toString());

    exchange.sendResponseHeaders(200, result.getBytes().length);

    OutputStream os = exchange.getResponseBody();
    os.write(result.getBytes());
    os.close();

  }

}
