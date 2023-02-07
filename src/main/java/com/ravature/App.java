package com.ravature;

import java.net.InetSocketAddress;

import com.ravature.controllers.EmployeeRegisterController;
import com.ravature.model.Employee;
import com.ravature.repository.EmployeeRepository;
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

        server.createContext("/employeeRegister", new EmployeeRegisterController());


        server.setExecutor(null);
        server.start();

    }
}
