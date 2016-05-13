package edu.ahpu.boke.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import edu.ahpu.boke.domain.User;

public class SessionUtils {
	//����û��������֤���Ƿ���session�е�һ��
	public static boolean isCodeMath(HttpServletRequest request){
		
		//��ȡsession
		HttpSession session=request.getSession(false);//�����ǰsessionû�оͷ���NULL
		if(session==null){
			return false;
		}
		
		String existCode=(String)session.getAttribute(Const.KEY_VERIFICATION_CODE);
		if(StringUtils.isBlank(existCode)){
			return false;
		}
		
		//��ȡ�û��������֤��
		String inputCode=request.getParameter("verificationCode");
		if(StringUtils.isBlank(inputCode)){
			return false;
		}
		return existCode.equalsIgnoreCase(inputCode);

		
	}
	
	//�û���¼�ɹ��󣬽��û��������session��
	
	public static void setUserToSession(HttpServletRequest request,User user){
		
		HttpSession session=request.getSession();
		if(user==null){
			return;//����������ʱ���Զ�����������
		}
		session.setAttribute(Const.KEY_LOGINED_USER, user);
	}
	
	//�õ�֮ǰ����session�е��û�����
	public static User getUserFormSession(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		if(session==null){
			return null;
		}
		return (User)session.getAttribute(Const.KEY_LOGINED_USER);
	}
	
	//�û�ע����ɾ�����session����
	public static void removeUserFormSession(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		if(session==null){
			return;
		}
		session.removeAttribute(Const.KEY_LOGINED_USER);
	}

}
                                                                                                                                                                                                                                                                                                                       