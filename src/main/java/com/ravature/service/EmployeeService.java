package com.ravature.service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ravature.model.Employee;
import com.ravature.repository.EmployeeRepository;

public class EmployeeService {

  private final EmployeeRepository empRepo = new EmployeeRepository();
  private final ObjectMapper mapper = new ObjectMapper();

  public String saveToEmployeeRepository(String empJson) {
    String reuslt = "";
    try {
      // Create new object for new employee to read by Json as Employee class
      Employee newEmployee = mapper.readValue(empJson, Employee.class);

      //save the new employee to repo
      reuslt = empRepo.saveEmployeeToDB(newEmployee);
      } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return reuslt;
    
  }

}