package com.revature;

import java.net.InetSocketAddress;

import com.revature.controllers.ProcessController;
import com.revature.controllers.SubmitTicketController;
import com.revature.controllers.UserLoginController;
import com.revature.controllers.UserRegisterController;
import com.revature.controllers.ViewAllUserController;
import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        //User
        server.createContext("/Register", new UserRegisterController());
        server.createContext("/Login", new UserLoginController());
        server.createContext("/ViewAllUser", new ViewAllUserController());
        server.createContext("/EmployeeVeiw&Promote", new UserLoginController());


        //Ticket
        server.createContext("/TicketSubmission", new SubmitTicketController());
        server.createContext("/ViewPreviousSubmission", new SubmitTicketController());
        server.createContext("/PeddingTicketView&Process", new ProcessController());


        server.setExecutor(null);
        server.start();

    }
}
