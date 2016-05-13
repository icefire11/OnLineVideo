<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <script type="text/javascript">
     function changeVerificationCode(){
       var image = document.getElementById("verificationCodeImage");
       image.src="verification_code.do?random=" + new Date().getTime();
       }
       
       function checkSubmit(){
       document.forms[0].submit();
       }
       function changeFace(facePic,faceId){
       var image2= document.getElementById("myFace");
       document.getElementById("myFace").src="images/face/" + facePic;
       document.getElementById("faceId").value=faceId;
       }
    </script>
  </head>
  
  <body>
     <s:form method="post" action="register_register.do">
     <ul>
    <li> <input type="hidden" name="faceId" value="${default_face.id }" /></li>
     请填写注册信息，若已有账号请<a href="login_login.do">点击登录</a>。
     <li>用户名：
      <s:textfield name="userName" /></li>
      <s:fielderror fieldName="user_name_exit_error"></s:fielderror>
      
     <li>密&nbsp;&nbsp;码:
     <s:password name="password" /></li>
     
      <li>确认密码:
     <s:password name="password2" /></li>
     
     <li>验证码:
     <s:textfield name="verificationCode" />
    
     <img id="verificationCodeImage" src="verification_code.do" title="看不清，换一张" onclick="changeVerificationCode()"/></li>
     <s:fielderror fieldName="verification_code_error"></s:fielderror>
     
    <li>头&nbsp;&nbsp;像:
    <img id="myFace" src="images/face/${default_face.picFileName}" /></li>
     
    <li><input id="botton" type="button"  value="注册" onclick="checkSubmit()" style="cursor:hand" /></li>
    
    <li><div>
       <s:iterator value="all_faces" var="face">
       <img src="images/face/${face.picFileName}"  onclick="changeFace('${face.picFileName}',${face.id})" />
       </s:iterator>
         
         
    </div></li>
    </ul>
    
    
     
     
     
     
     </s:form>
     
  </body>
</html>
