package edu.ahpu.boke.action;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import edu.ahpu.boke.domain.User;
import edu.ahpu.boke.service.UserService;
import edu.ahpu.boke.util.SessionUtils;


@SuppressWarnings("serial")
@Controller
public class LoginAction extends BaseAction {
	
	@Resource
	private UserService userService;
	
	private String userName;
	private String password;
	private String verificationCode;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String init(){
		return "login";
	}
	
	public String login(){
		
		if(!SessionUtils.isCodeMath(request)){
			this.addFieldError("verification_code_error", "验证码错误");
			return "login";
					
		}
		
		User u=userService.findUser(userName, password);
		
		if(u==null){
			this.addFieldError("invalid_user_error", "用户名或密码错误");
			return "login";
			
		}else{
			u.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			userService.updateLastLoginTime(u);
			
			SessionUtils.setUserToSession(request, u);
		}
		return "back_to_main";
		
		
	}
	
	public String  logout(){
		SessionUtils.removeUserFormSession(request);
		return "back_to_main";
	}
	
	

}
