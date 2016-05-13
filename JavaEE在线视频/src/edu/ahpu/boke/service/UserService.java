package edu.ahpu.boke.service;

import edu.ahpu.boke.domain.User;

public interface UserService {
	
	//�ж�������û����Ƿ����
	public boolean isUserNameExist(String userName);
		
	//����û���Ϣ
	public void addUser(String userName,String password,int faceId);
	
	public User findUser(String userName,String password);
	
	public void updateLastLoginTime(User user);
	
	

}
