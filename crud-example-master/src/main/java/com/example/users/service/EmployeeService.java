package com.example.users.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.users.dto.EmployeeDto;
import com.example.users.entity.EmployeeEntity;
import com.example.users.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void create(EmployeeDto request) {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setEmail(request.getEmail());
		employeeRepository.save(entity);
	}
	
	public EmployeeDto get(Long id) {
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
		if (employeeEntity.isPresent()) {
			EmployeeEntity result = employeeEntity.get();
			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setEmail(result.getEmail());
			employeeDto.setFirstName(result.getFirstName());
			employeeDto.setLastName(result.getLastName());
			return employeeDto;
		}
		return null;
	}

	public void update(Long id, EmployeeDto employee) {
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
		if (employeeEntity.isPresent()) {
			EmployeeEntity result = employeeEntity.get();
			result.setFirstName(employee.getFirstName());
			result.setLastName(employee.getLastName());
			result.setEmail(employee.getEmail());
			employeeRepository.save(result);
		}
	}

	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}
}

//DTO -> CONTROLLER -> SERVICE -> REPOSITORY -> ENTITY