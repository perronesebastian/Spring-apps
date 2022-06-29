package com.example.users.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.users.entity.User;
import com.example.users.entity.UserInRol;
import com.example.users.repository.UserInRoleRepository;
import com.example.users.repository.UserRepository;

@Service
public class ConfigUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUsername(username);
		if (optional.isPresent()) {
			User user = optional.get();
			List<UserInRol> userInRol = userInRoleRepository.findByUser(user);
			String[] roles = userInRol.stream().map(r -> r.getRole().getName()).toArray(String[]::new);
			return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
					.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
		} else {
			throw new UsernameNotFoundException("Username " + username +" not found");
		}
	}
}
