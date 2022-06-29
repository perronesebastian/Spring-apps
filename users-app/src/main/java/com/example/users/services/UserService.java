package com.example.users.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.users.entity.User;
import com.example.users.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	public Page<User> getUser(int page, int size) {

		return 	repository.findAll(PageRequest.of(page, size));
	}

	public User getUserById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", id)));
	}
	
	@Cacheable("users")
	public User getUserByUsername(String username) {
		log.info("Getting product by username {}", username);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return repository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", username)));
	}

	public Page<String> getUsernames(int page, int size) {
		return repository.findUsername(PageRequest.of(page, size));

	}
	
	@CacheEvict("users")
	public void deleteUser(String username) {
		User user = getUserByUsername(username);
		repository.delete(user);
	}
}
