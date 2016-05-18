<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    
    
    <title>简化版的首页</title>
    
	

  </head>
  
  <body>
 <div>
     <s:if test="#session.logined_user == null">
       <a href="register_init.do">注册</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;  
       <a href="login_init.do">登录</a>  
     </s:if>
     <s:else>
     <a href="login_logout.do">注销</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="upload_init.do">上传视频</a>
     </s:else>
   </div>
   
     
  </body>
</html>
