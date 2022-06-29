package com.example.users.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.users.entity.Role;
import com.example.users.entity.User;
import com.example.users.models.AuditDetails;
import com.example.users.models.SecurityRule;
import com.example.users.repository.RoleRepository;
import com.example.users.repository.UserInRoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final Logger log = LoggerFactory.getLogger(RoleService.class);
	
	public List<Role> getRoles() {
		return repository.findAll();
	}
	
	public Role getRoleById(Integer id) {
		Optional<Role> role = repository.findById(id);
		if (role.isPresent()) {
			return role.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %d does't exist ", id));
		}
	}
	
//	@Secured({"ROLE_ADMIN"}) //cuando uso securedEnabled (SecurityJavaConfig)
//	@RolesAllowed({"ROLE_ADMIN"}) //cuando uso jsr250Enabled
//	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //PERMITE SOLO INVOCAR EL METODO A LOS QUE CUMPLAN LA CONDICION
//	@PostAuthorize("hasRole('ROLE_ADMIN')") //PERMITE TENER UNA RESPUESTA A LOS QUE CUMPLAN LA CONDICION
	@SecurityRule //Anotacion creada por mi que contiene Post/Pre Authorize para no repetirlas en todos lados
	public List<User> getUsersByRole(String roleName) {
		log.info("Getting roles");
		return userInRoleRepository.findUsersByRole(roleName);
	}
	
	public List<Role> getRolesByUsername(String username) {
		return userInRoleRepository.findRolesByUsername(username);
	}

	public Role createRole(Role role) {
		Role roleCreated = repository.save(role);
		
		AuditDetails details = new AuditDetails(SecurityContextHolder.getContext().getAuthentication().getName(), role.getName());
		try {
			kafkaTemplate.send("devs4j-topic", mapper.writeValueAsString(details) );
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error parsing the message");
		}
		return roleCreated;
	}

	public Role updateRole(Integer id, Role role) {
		Optional<Role> result = repository.findById(id);
		if (result.isPresent()) {
			return repository.save(role);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", id));
		}
	}

	public void deleteRole(Integer id) {
		repository.deleteById(id);
	}
	
	
	

}
