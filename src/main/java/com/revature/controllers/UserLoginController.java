package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.revature.service.EmployeeLoginService;
import com.revature.service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserLoginController implements HttpHandler {

    // create object to EmployeeService
    private final EmployeeLoginService empService = new EmployeeLoginService();

    @Override
    public void handle(HttpExchange exchange) throws IOException{
      // TODO Auto-generated method stub
      // set verb to the HTTP verb
      String verb = exchange.getRequestMethod();
      String response = "";
      // set a switch statement to get reponse for each HTTP verb
      switch (verb) {
        case "POST":
          // a method for POST reponse
          postRequestLogin(exchange);
          break;
        case "GET":
          response = "This is Login Page!!";
          exchange.sendResponseHeaders(200, response.getBytes().length);
        default:
          response = "Wrong HTTP Verb";
          exchange.sendResponseHeaders(404,response.getBytes().length);
          break;
      }

      OutputStream os =exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  
    private void postRequestLogin(HttpExchange exchange) throws IOException {
      // InputStream has a bunch of bytes
      InputStream is = exchange.getRequestBody();
  
      // Convert inputStream into String
      // So we use StringBuilder which is like mutable version of String
      StringBuilder textBuilder = new StringBuilder();
  
      // Conerts our binary into letters
      try (
          Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
        int c = 0;
        // read() method from BufferReader will give a -1 once there is no more letter
        // left
  
        // loop for add all char to textBuilder
        while ((c = reader.read()) != -1) {
          // adds the letter to your text
          textBuilder.append((char) c);
        }
      }

      // call method with String from database saveRegInforTolist in EmployeeService
      // class
      String result;
      result = empService.login(textBuilder.toString());
  
      exchange.sendResponseHeaders(200, result.getBytes().length);
  
  
      OutputStream os = exchange.getResponseBody();
      os.write(result.getBytes());
      os.close();
    }
  
}
