package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;

	@Override
	public User login(String name, String password) throws Exception {
		// TODO Auto-generated method stub
		User user=null;
		if(password==null||password.length()==0)
		{
			return null;
		}
		if((user=mapper.getUserByName(name))==null)
		{
			return null;
		}
		if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			return user;
		}else
			return null;
	}

	@Override
	public String register(String name, String password) throws Exception {
		// TODO Auto-generated method stub
		if(mapper.getUserByName(name)!=null)
		{
			return "该用户已被注册";
		}
		String pwd=DigestUtils.md5DigestAsHex(password.getBytes());
		int success=mapper.addUser(name, pwd);
		if(success==1)
			return "success";
		else
			return "注册失败";
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAllUsers();
	}


}
