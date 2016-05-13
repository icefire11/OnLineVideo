<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>

    
    <title>登录</title>
    <script type="text/javascript">
     function changeVerificationCode(){
     var image = document.getElementById("verificationCodeImage");
       image.src="verification_code.do?random=" + new Date().getTime();
     }
     
     function checkSubmit(){
     document.forms[0].submit();
     }
    
    
    </script>
    


  </head>
  
  <body>
  <ul>
    <s:form method="post" action="login_login.do">
      登录后才能上传视频和评论，若没有账号请<a href="register_init.do">单击注册</a>
      
      <li>用户名：
      <s:textfield name="userName" /></li>
      <s:fielderror fieldName="invalid_user_error"></s:fielderror>
      
     <li>密&nbsp;&nbsp;码:
     <s:password name="password" /></li>
     
     <li>验证码:
     <s:textfield name="verificationCode" />
    
     <img id="verificationCodeImage" src="verification_code.do" title="看不清，换一张" onclick="changeVerificationCode()"/></li>
     <s:fielderror fieldName="verification_code_error"></s:fielderror>
     
      <li><input id="botton" type="button"  value="登录" onclick="checkSubmit()" style="cursor:hand" /></li>
      </ul>
    
    
    
    </s:form>
  </body>
</html>
