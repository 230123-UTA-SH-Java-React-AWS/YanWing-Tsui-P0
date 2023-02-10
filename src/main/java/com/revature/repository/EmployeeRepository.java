package com.revature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.utils.ConnectionUtil;

public class EmployeeRepository {

    // create a method in EmployeeRepository for interacting with a database and
    // sending or receiving information form the database

    // this method is to save register input to the database
    public String saveEmployeeToDB(Employee employee) {

        // call existingEmail method
        String username = employee.getUsername();
        String password = employee.getUserPassword();
        if (getEmployeeByUsername(username) != null) {
            return "Email have been used! Please use another email";
        }
        if (username == null) {
            return "username is missing";
        }

        if (password == null) {
            return "password is missing";
        }

        // set sql code to sql and will call it to run it in sql
        String sql = "insert into employee  (empUsername, empPassword, empRole) values (?, ?, ?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            // preparedstatement is to let the database run the sql code we provide with no
            // values which mean we have to provide the values
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, employee.getUsername());
            prstmt.setString(2, employee.getUserPassword());
            prstmt.setString(3, employee.getUserRole());

            // execute() method does not expect to return anthing from the statement
            // excutQuery() method does expect something to result after executing the
            // statement
            prstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Register Sucessfully! Waiting for Approval!!";
    }

    public Employee getEmployeeByUsername(String username) {
        // creat list to save the employee list you want
        List<Employee> listOfEmp = new ArrayList<>();
        // set employee to null in order to get email does not exit in the table
        Employee employee = null;

        // SQL code for looking employee by email
        String sql = "select * from employee where empUsername = ?";

        // Connection
        try (Connection con = ConnectionUtil.getConnection()) {
            // preparedstatement is to let the database run the sql code we provide with no
            // values which mean we have to provide the values
            PreparedStatement prstmt = con.prepareStatement(sql);

            // get the email from the user
            prstmt.setString(1, username);

            // execute to save the register information to the connection of database and
            // set it to resultSet for get the data
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                Employee newEmployee = new Employee();

                newEmployee.setUserId(rs.getInt(1));
                newEmployee.setUsername(rs.getString(2));
                newEmployee.setUserPassword(rs.getString(3));
                listOfEmp.add(newEmployee);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        if (listOfEmp.size() > 0) {
            employee = listOfEmp.get(0);
        }

        return employee;
    }

    public int getEmployeeById(String username) {
        if (username == null) {
            return -1;
        }
        List<Integer> ListOfUserId = new ArrayList<>();
        int userId = -1;

        String sql = "SELECT empid FROM employee WHERE username = ?";

        try (Connection con = ConnectionUtil.getConnection()) {
            // Create the querying object
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, username);
            // Execute the query
            ResultSet rs = prstmt.executeQuery();
            // Mapping information from a table to our data structure
            while (rs.next()) {
                ListOfUserId.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        if (ListOfUserId.size() > 0)
            userId = ListOfUserId.get(0);
        return userId;
    }

    public List<String> getAllUser() {

        String sql = "select * from employee";
        List<String> listOfUser = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                listOfUser.add(rs.getString(2));
                listOfUser.add(rs.getString(3));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfUser;

    }

    public boolean checkManager(String username) {
        String sql = "select emprole from employee where empUsername = ?";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, username);
            ResultSet rs = prstmt.executeQuery();
            rs.next();
            String empRole = rs.getString(1);

            if (empRole.toUpperCase().equals("MANAGER")) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void promtion(String usernameString) {

        String sql = "UPDATE employee SET emprole = ? WHERE empUsername = ?";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, "MANAGER");
            prstmt.setString(2, usernameString);
            // Execute the query
            prstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
