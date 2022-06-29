package com.example.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.users.entity.Profile;
import com.example.users.entity.User;
import com.example.users.repository.ProfileRepository;
import com.example.users.repository.UserRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;

	public Profile create(Integer userID, Profile profile) {
		Optional<User> result = userRepository.findById(userID);
		if (result.isPresent()) {
			profile.setUser(result.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userID));
		}
	}

	public Profile getByUserIdAndProfileId(Integer userID, Integer profileID) {
		return profileRepository.getByUserIdAndProfileId(userID, profileID)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile not found for user %d and profile %d", userID, profileID)));
	}

}
