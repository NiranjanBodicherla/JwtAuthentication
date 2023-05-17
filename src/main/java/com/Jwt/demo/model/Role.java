package com.Jwt.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Role {
	
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer Id;
    @Column(name = "role_name")
    private String name;
    
    
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Role(Integer id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    

}
