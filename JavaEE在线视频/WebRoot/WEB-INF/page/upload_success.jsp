<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head> 
    <title>找不到页面</title>
    <script language="javascript" type="text/javascript">
    var second;
    
    function go(){
      second=document.getElementById("totalSecond").innerText;
      setInterval("redirect()",1000);
      }
      function redirect(){
      if(second<=0){
       location.href="main.do";
       }else{
        document.getElementById("totalSecond").innerText=--second;
        }
    }	
  </script>
  </head>
  
  <body onload="go();">
    <h1>
      视频上传成功，等待系统对其转码<span id="totalSecond">3</span>秒后返回首页
    </h1>
  </body>
</html>
