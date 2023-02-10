package com.revature.service;

import java.io.IOException;

import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.repository.EmployeeRepository;

public class EmployeeService {

  private final EmployeeRepository empRepo = new EmployeeRepository();
  private final ObjectMapper mapper = new ObjectMapper();

  public String saveToEmployeeRepository(String empJson) {
    String reuslt = "";
    try {
      // Create new object for new employee to read by Json as Employee class
      Employee newEmployee = mapper.readValue(empJson, Employee.class);

      // save the new employee to repo
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

  public String getAllUser() {
    List<String> listOfUser = empRepo.getAllUser();

    String jsonString = "";
    try {
      jsonString = mapper.writeValueAsString(listOfUser);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return jsonString;
  }

  // public static Boolean alterUserRole(int length){
  // String jsoString = "";
  // try{
  // Employee newEmployee = mapper.readValue(length, Employee.class);
  // String role = newEmployee.getUserRole();

  // String x = length.toString();

  // JsonNode JN = ObjectMapper.readTree(x);
  // JsonNode usenameNode = JN.get("username");
  // JsonNode passwordNode = JN.get("userPassword");
  // JsonNode otherUserNode = JN.get("otherUsername");

  // String username = usenameNode.asText();
  // String password = passwordNode.asText();
  // String otherUsername = otherUserNode.asText();

  // jsoString = empRepo.alterUserRole(username, password, otherUsername, role);
  // }catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  public String promateUser(String jsonString) {
    try {
      JsonNode treeNode = mapper.readTree(jsonString);
      if(empRepo.checkManager(treeNode.get("firstUsername").asText())){
        if(empRepo.getAllUser().contains(treeNode.get("secondUsername").asText())){
          empRepo.promtion(treeNode.get("secondUsername").asText());
          return "Promote Sucessfully!";
        }else{
          return "Account not exsit!";
        }
      }else{
        return "Only Manager are allowed to promote!";
      }

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
    return "Gone Wrong!!";
  }

}