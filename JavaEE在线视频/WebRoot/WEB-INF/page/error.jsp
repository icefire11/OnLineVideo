<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>出现错误</title>
    
  </head>
  
  <body>
     <h1>发生错误
     <span style="color:#FF0000">
        <s:property value="%{exception.message}"/>
       </span>
       
       </h1>
       <hr />
       
       <h3>详细信息：</h3>
       
       <p>
       <s:property value="%{exceptionStack }"/>
       </p>
  </body>
</html>
