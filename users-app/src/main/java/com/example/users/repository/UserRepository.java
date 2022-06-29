package com.example.users.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);
	
	/**
	 * Esto NO es SQL, se llama JPQL
	 */
	@Query("SELECT u.username FROM User u WHERE u.username like '%s' ")
	public Page<String> findUsername(Pageable pageable);
	
}
