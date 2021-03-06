package com.greatLearning.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatLearning.employeeManagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findUserByUsername(String username);
}
