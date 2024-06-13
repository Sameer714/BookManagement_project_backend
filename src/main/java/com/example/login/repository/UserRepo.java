package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	List<User> findAllByStatus(String status);
	List<User> findAll();

	User findById(long id);
	User findByUserName(String userName);
	User findByEmail(String gmail);
	List<User> findAllByStatusAndRole(String status, String role);
}