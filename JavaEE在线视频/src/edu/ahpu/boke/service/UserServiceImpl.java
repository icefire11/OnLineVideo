package edu.ahpu.boke.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.ahpu.boke.dao.FaceDao;
import edu.ahpu.boke.dao.UserDao;
import edu.ahpu.boke.domain.User;


@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	@Resource
	private FaceDao faceDao;

	@Override
	public boolean isUserNameExist(String userName) {
		// TODO Auto-generated method stub
		return userDao.findFirstByCondition("and o.name=?", new Object[]{userName}, false)!=null;
	}

	@Override
	public void addUser(String userName, String password, int faceId) {
		// TODO Auto-generated method stub
		
		User user=new User();
		user.setName(userName);
		user.setPassword(password);
		user.setFacePicId(faceId);
		user.setVisitCount(0);
		user.setTotalPlayCount(0);
		userDao.save(user);
		

	}
	
	public User findUser(String userName,String password){
		return userDao.findFirstByCondition("and o.name=? and o.password=?", new Object[] {userName,password}, false);
	}
	
	public void updateLastLoginTime(User user) {
		userDao.update(user);
		
	}

}
