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
  
  //this method is to save register input to the database
  public String saveEmployeeToDB(Employee employee) {

    // call existingEmail method
    String username = employee.getUsername();
    if (getEmployeeByUsername(username) != null) {
      return "Email have been used! Please use another email";
    }

    //set sql code to sql and will call it to run it in sql
    String sql = "insert into employee  (empFName , empLName , empEmail , empUsername, empPassword, empRole) values (?, ?, ?, ?, ?, ?)";


    try (Connection con = ConnectionUtil.getConnection()) {
      // preparedstatement is to let the database run the sql code we provide with no
      // values which mean we have to provide the values
      PreparedStatement prstmt = con.prepareStatement(sql);

      prstmt.setString(1, employee.getUserFName());
      prstmt.setString(2, employee.getUserLName());
      prstmt.setString(3, employee.getUserEmail());
      prstmt.setString(4, employee.getUsername());
      prstmt.setString(5, employee.getUserPassword());
      prstmt.setString(6, employee.getUserRole());

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
        newEmployee.setUserFName(rs.getString(2));
        newEmployee.setUserLName(rs.getString(3));
        newEmployee.setUsername(rs.getString(4));
        newEmployee.setUserEmail(rs.getString(5));
        newEmployee.setUserPassword(rs.getString(6));
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

  public List<User> getAllUser(){

    String sql = "select * from employee";
    List<User> listOfUser = new ArrayList<User>();

    try(Connection con = ConnectionUtil.getConnection()){
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        User newUser = new User();

        newUser.setUserId(rs.getInt(1));
        newUser.setUserFName(rs.getString(2));
        newUser.setUserLName(rs.getString(3));
        newUser.setUserEmail(rs.getString(4));
        newUser.setUsername(rs.getString(5));
        newUser.setUserPassword(rs.getString(6));
        newUser.setUserRole(rs.getString(7));

        listOfUser.add(newUser);

      }
    }catch (Exception e){
      e.printStackTrace();
    }

    return listOfUser;
    
  }
}
