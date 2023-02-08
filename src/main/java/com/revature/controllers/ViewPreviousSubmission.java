// package com.revature.controllers;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.io.Reader;
// import java.nio.charset.Charset;
// import java.nio.charset.StandardCharsets;

// import com.revature.service.EmployeeService;
// import com.revature.service.TicketService;
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;

// public class ViewPreviousSubmission implements HttpHandler {

//   private final EmployeeService empService = new EmployeeService();

//   @Override
//   public void handle(HttpExchange exchange) throws IOException {
//     // TODO Auto-generated method stub
//     String verb = exchange.getRequestMethod();
//     String response = "";
//     switch (verb) {
      
//       case "GET":
//         postRequestViewPreviousSubmission(exchange);
//         break;

//       default:
//         getRequestViewPreviousSubmission(exchange);
//         break;
//     }

//     OutputStream os = exchange.getResponseBody();
//     os.write(response.getBytes());
//     os.close();

//   }


//   private void postRequestViewPreviousSubmission(HttpExchange exchange) throws IOException {
//     // InputStream has a bunch of bytes
//     InputStream is = exchange.getRequestBody();

//     StringBuilder texBuilder = new StringBuilder();

//     try (
//         Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
//       int c = 0;
      
//       while ((c = reader.read()) != -1) {
//         // adds the letter to your text
//         texBuilder.append((char) c);
//       }
//     }

//     String response = TicketService.SearchByID(texBuilder.toString());

//     exchange.sendResponseHeaders(200, result.getBytes().length);

//     OutputStream os = exchange.getResponseBody();
//     os.write(result.getBytes());
//     os.close();

//   }


//   private void getRequestViewPreviousSubmission(HttpExchange exchange) throws IOException {
//     TicketService ticketService = new TicketService();
//     String jsonCurrentList = ticketService.SearchByID(exchange.getRequestBody());

//     exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

//     OutputStream os = exchange.getResponseBody();
//     os.write(jsonCurrentList.getBytes());
//     os.close();
//   }

//   }

// }
