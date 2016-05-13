<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    
    
    <title>播客首页</title>
    <script type="text/javascript">
    //根据提交的表单项改变页面中的当前频道和排序标准的css属性，使其呈现为选中
    function selectChannelAndOrder(){
      var channelId="${channelId}";
      var channels=document.getElementById("channels").childNodes;
					for (i = 0; i < channels.length; i++) {
							if (channels[i].id == "channel_" + channelId) {
								channels[i].className = "cur";
							} else {
								channels[i].className = "";
							}
						}
						var orderId = "${orderId}";
						if (orderId != 1 && orderId != 2 && orderId != 3
								&& orderId != 4) {
							orderId = 1;
						}
						for (i = 1; i < 4; i++) {
							if (i = orderId) {
								document.getElementById("order_" + i).className = "cur";
							} else {
								document.getElementById("order_" + i).className = "";
							}
						}
					}
				</script>
  </head>
  
  <body onload="selectChannelAndOrder()">
 <s:form action="main.do" method="post">
   <div>
     <s: if test=""
   
   
   
   </div>
 
 
 
 
 
 
 
 
 
 </s:form>
     
  </body>
</html>
