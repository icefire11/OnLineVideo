<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    
    
    <title>上传视频文件</title>
    <script type="text/javascript">
    function upload(){
     document.forms[0].submit();
    }
    
    
    </script>


  </head>
  
  <body>
  <ul>
    <h3>上传视频</h3>
    <s:form action="upload_upload.do" method="post" enctype="multipart/form-data">
    <li>文件：</li>
    <s:file name="video"/>
    <li>标题：</li>
    <s:textfield name="tittle"/>
    <li>简介：</li>
    <s:textarea name="description" />
   <li> 频道：</li>
    <s:select name="channelId" list="all_channels" listKey="id" listValue="name" />
    <li>标签：</li>
    <s:textfield name="tags"/>
    <li><img  src="images/upload.png" onclick="upload()"/></li>
    </s:form>
    </ul>
  </body>
</html>
