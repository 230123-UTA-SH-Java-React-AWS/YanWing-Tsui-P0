package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//this class is responsible for connecting to our database

public class ConnectionUtil {

  // We want only one connection to the database the entire time
  private static Connection con;

  // Private constructor to pervent anyone from many an object
  private ConnectionUtil() {
    con = null;
  }

  // Method that will gives us a connection to DB
  // Or it will give the existing connection
  public static Connection getConnection() {
    // Determine if we already have connection and if so give the current connection
    try {
      if (con != null && !con.isClosed()) {
        return con;
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    String url, user, pass;

    // get the local environment variable
    url = System.getenv("url");
    user = System.getenv("user");
    pass = System.getenv("pass");

    try {
      con = DriverManager.getConnection(url, user, pass);

    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println("YOU PROBABLY GAVE THE WRONG PASSWORD OR URL!");
    }

    return con;

  }
}
