package com.example.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.users.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	@Query("SELECT p FROM Profile p WHERE p.user.id=?1 AND p.id=?2")
	Optional<Profile>getByUserIdAndProfileId(Integer userID, Integer profileID);
}
