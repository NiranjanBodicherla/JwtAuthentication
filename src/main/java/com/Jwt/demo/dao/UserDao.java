package com.Jwt.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jwt.demo.model.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	 

}
