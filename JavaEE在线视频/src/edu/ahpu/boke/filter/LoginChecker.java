package edu.ahpu.boke.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahpu.boke.domain.User;
import edu.ahpu.boke.util.SessionUtils;

public class LoginChecker implements Filter {
	
	//�����Ҫ��¼���ܷ��ʵ���ַ
	private List<String> pathNeedLogin;
	
	
     
	
	
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//��Ӹ�����Ҫ��¼���ܷ��ʵ�����ַ
		pathNeedLogin=new ArrayList<String>();
		
		pathNeedLogin.add("/login_logout.do");
		pathNeedLogin.add("/upload_init.do");
		pathNeedLogin.add("/upload_upload.do");
		pathNeedLogin.add("/player_comment.do");

	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		String path=request.getServletPath();
		
		if(!pathNeedLogin.contains(path)){
			chain.doFilter(request, response);//����
			return;
		}
		
		User user=SessionUtils.getUserFormSession(request);//ÿ��request����Ӧ��session��
		
		if(user !=null){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/login_init.do");
		}
		

	}

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}


}
