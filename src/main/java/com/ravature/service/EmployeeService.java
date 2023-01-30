package com.ravature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ravature.model.Employee;
import com.ravature.repository.EmployeeRepository;

public class EmployeeService {
  public void saveToEmpList(String EmpJson) {
    EmployeeRepository repo = new EmployeeRepository();
    ObjectMapper mapper = new ObjectMapper();

    try {
      Employee newEmployee = mapper.readValue(EmpJson, Employee.class);

      repo.save(newEmployee);

    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
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
