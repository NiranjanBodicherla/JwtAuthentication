package com.Jwt.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jwt.demo.model.Role;

public interface RolesDao extends JpaRepository<Role, Integer>{
	

}
