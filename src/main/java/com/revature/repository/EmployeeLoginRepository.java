package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.utils.ConnectionUtil;

public class EmployeeLoginRepository {

  public String login(User employee) {
    String username = employee.getUsername();
    String pass = employee.getUserPassword();
    List<User> empList = new ArrayList<>();

    if (FindUser(username, pass) != null) {
      System.out.println();
      return "Login Successfully!";
    } else
      return "Wrong email or password!";
  }

  public User FindUser(String username, String password) {
    List<User> empList = new ArrayList<>();

    User employee = null;

    String sql = "select * from employee where empusername = ? AND emppassword = ?";

    try (Connection con = ConnectionUtil.getConnection()) {
      // preparedstatement is to let the database run the sql code we provide with no
      // values which mean we have to provide the values
      PreparedStatement prstmt = con.prepareStatement(sql);

      // get the email from the user
      prstmt.setString(1, username);
      prstmt.setString(2, password);

      // execute to save the register information to the connection of database and
      // set it to resultSet for get the data
      ResultSet rs = prstmt.executeQuery();

      while (rs.next()) {
        Employee newEmployee = new Employee();

        newEmployee.setUserId(rs.getInt(1));
        newEmployee.setUserFName(rs.getString(2));
        newEmployee.setUserLName(rs.getString(3));
        newEmployee.setUserEmail(rs.getString(4));
        newEmployee.setUsername(rs.getString(5));
        newEmployee.setUserPassword(rs.getString(6));
        newEmployee.setUserRole(rs.getString(7));

        empList.add(newEmployee);
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    if (empList.size() > 0) {
      employee = empList.get(0);
    }

    return employee;
  }
}

