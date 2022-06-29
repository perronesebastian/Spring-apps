package com.example.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.users.entity.Address;
import com.example.users.entity.Profile;
import com.example.users.repository.AddressRepository;
import com.example.users.repository.ProfileRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public List<Address> findAddressesByUserIdAndProfileId(Integer userID, Integer profileID) {
		return addressRepository.findByProfileId(userID, profileID);
	}

	public Address create(Integer userID, Integer profileID, Address address) {
		Optional<Profile> result = profileRepository.getByUserIdAndProfileId(userID, profileID);
		if (result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile %d and user %d not found", profileID, userID));
		}
	}

}
