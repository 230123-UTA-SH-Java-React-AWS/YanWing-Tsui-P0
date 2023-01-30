package com.ravature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ravature.model.Employee;

public class EmployeeRepository {

  // create a method in EmployeeRepository for interacting with a database and
  // sending or receiving information form the database
  public void save(Employee employee) {
    ObjectMapper mapper = new ObjectMapper();
    String jsonObject = "";

    try {
      // Converted the employee object into json
      jsonObject = mapper.writeValueAsString(employee);

      // Save the json into a file
      // File constructor needs a String that holds the path of where you want to save
      // the file
      File empFile = new File(
          "C:\\Users\\yanwi\\Documents\\Revature\\Project\\project0\\src\\main\\java\\com\\ravature\\repository\\employee.json");
      empFile.createNewFile();

      // Writing the file
      FileWriter writer = new FileWriter(
          "C:\\Users\\yanwi\\Documents\\Revature\\Project\\project0\\src\\main\\java\\com\\ravature\\repository\\employee.json");
      writer.write(jsonObject);// Write the String into the file
      writer.close();

    } catch (JsonGenerationException e) {
      // TODO: handle exception
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
