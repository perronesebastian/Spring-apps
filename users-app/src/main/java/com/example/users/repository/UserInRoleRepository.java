package com.example.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.users.entity.Role;
import com.example.users.entity.User;
import com.example.users.entity.UserInRol;

@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRol, Integer> {
	
	@Query("SELECT r.role FROM UserInRol r WHERE r.user.username=?1")
	public List<Role> findRolesByUsername(String username);
	
	@Query("SELECT r.user FROM UserInRol r WHERE r.role.name=?1")
	public List<User> findUsersByRole(String roleName);
	
	public List<UserInRol> findByUser(User user);
}
