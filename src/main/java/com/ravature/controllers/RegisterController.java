package com.ravature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.ravature.service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RegisterController implements HttpHandler {

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

    exchange.sendResponseHeaders(200, texBuilder.toString().getBytes().length);

    EmployeeService empService = new EmployeeService();
    empService.saveToEmpList(texBuilder.toString());

    OutputStream os = exchange.getResponseBody();
    os.write(texBuilder.toString().getBytes());
    os.close();

  }

}
