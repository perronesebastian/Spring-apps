package com.example.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.users.dto.EmployeeDto;
import com.example.users.entity.EmployeeEntity;
import com.example.users.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> get(@PathVariable("id") Long id) {
        EmployeeDto dto = employeeService.get(id);
        return new ResponseEntity<EmployeeDto>(dto, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeDto employee) {
        employeeService.create(employee);
        return new ResponseEntity<EmployeeEntity>(HttpStatus.OK);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable("id")Long id, @RequestBody EmployeeDto employee) {
        employeeService.update(id, employee);
        return new ResponseEntity<EmployeeEntity>(HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id) {
        employeeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

//POSTMAN/CLIENTE/FRONTEND -> CONTROLLER -> SERVICE -> REPOSITORY -> ENTITY