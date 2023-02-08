package com.revature;

import java.net.InetSocketAddress;

import com.revature.controllers.SubmitTicketController;
import com.revature.controllers.UserLoginController;
import com.revature.controllers.UserRegisterController;
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

        server.createContext("/Register", new UserRegisterController());

        server.createContext("/Login", new UserLoginController());

        server.createContext("/TicketSubmition", new SubmitTicketController());


        server.setExecutor(null);
        server.start();

    }
}
