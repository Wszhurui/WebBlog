package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(
			HttpServletRequest request,
			@RequestParam(name="name")String name,
			@RequestParam(name="password")String password
			)throws Exception
	{
		User user=service.login(name, password);
		if(user!=null)
		{
			request.getSession().setAttribute("user", user);
			return "/Manage";
		}
		else
			return "error";
	}
	
	@RequestMapping("/admin/logout")
	@ResponseBody
	public boolean logout(HttpServletRequest request) throws Exception
	{
		request.getSession().invalidate();
		return true;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(
			@RequestParam(name="name")String name,
			@RequestParam(name="password")String password
			)throws Exception
	{
		return service.register(name, password);
	}
	
	@RequestMapping("/admin/getAllUsers")
	@ResponseBody
	public List<User> getAllUsers()throws Exception
	{
		return service.getAllUsers();
	}
	
	//登录成功后用于前端验证是否登录，未登录前端会路由到登录界面
	@RequestMapping("/admin/getUser")
	@ResponseBody
	public User getUser(HttpServletRequest request) throws Exception
	{
		return (User)request.getSession().getAttribute("user");
	}
}
