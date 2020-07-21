package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from t_user where name=#{name}")
	public User getUserByName(String name) throws Exception;
	
	@Select("select * from t_user")
	public List<User> getAllUsers() throws Exception;
	
	@Insert("insert into t_user(name,password)values(#{name},#{pwd})")
	public int addUser(String name,String pwd)throws Exception;
}
