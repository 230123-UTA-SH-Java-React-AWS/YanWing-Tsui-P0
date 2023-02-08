package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.repository.EmployeeLoginRepository;
import com.revature.repository.EmployeeRepository;

public class EmployeeLoginService {

  private final EmployeeLoginRepository empRepo = new EmployeeLoginRepository();
  private final ObjectMapper mapper = new ObjectMapper();

  public String login(String empJson){
    String result = "";
    try {
      // Create new object for new employee to read by Json as Employee class
      Employee newEmployee = mapper.readValue(empJson, Employee.class);

      // save the new employee to repo
      result = empRepo.login(newEmployee);
      

    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }
}
