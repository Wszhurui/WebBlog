package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	
	public User login(String name,String password) throws Exception;
	
	public String register(String name,String password) throws Exception;
	
	public List<User> getAllUsers() throws Exception;
}
