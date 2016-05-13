package edu.ahpu.boke.service;

import edu.ahpu.boke.domain.User;

public interface UserService {
	
	//判断输入的用户名是否存在
	public boolean isUserNameExist(String userName);
		
	//添加用户信息
	public void addUser(String userName,String password,int faceId);
	
	public User findUser(String userName,String password);
	
	public void updateLastLoginTime(User user);
	
	

}
