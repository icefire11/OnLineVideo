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
     <s:if test="#session.logined_user == null">
       <a href="register_init.do">注册</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;  
       <a href="login_init.do">登录</a>  
     </s:if>
     <s:else>
     <a href="login_logout.do">注销</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="upload_init.do">上传视频</a>
     </s:else>
   </div>
   
   <div id="channels">
    <h3>视频分类</h3>
    <s:iterator value="all_channels" var="c">
      <h4 id="channel_${c.id }">
        <A href="main.do?channelId=${c.id }&orderId=${orderId }">${c.name}</A>
              </h4>
    
    </s:iterator>
   </div>
   ${channelName } 频道
   <ul>
     <li id="order_1">
      <a href="main.do?channelId=${channelId }&orderId=1">最新发布</a>
     </li>
     
     <li id="order_2">
      <a href="main.do?channelId=${channelId }&orderId=2">最多播放</a>
     </li>
     
     <li id="order_3">
      <a href="main.do?channelId=${channelId }&orderId=3">最多评论</a>
     </li>
     
     <li id="order_4">
      <a href="main.do?channelId=${channelId }&orderId=4">最多好评</a>
     </li>  
   </ul>
   
   发布时间：
   <select name="period">
   <option value="0">全部</option>
   <option value="0">本日</option>
   <option value="0">本周</option>
   <option value="0">本月</option>
   </select>
  
  <div>
    <s:iterator value="#page_bean.contens" var="video" status="st">
      <s:set name="duration" value="#video.duration"></s:set>
      <s:set name="minute" value="@edu.ahpu.boke.util.CommonUtils@toInt(duration/60)"></s:set>
      <s:set name="second" value="@edu.ahpu.boke.util.CommonUtils@toInt(duration%60)"></s:set>
      
      <dl <s:if test="#st.index%5==0">clss="nomar"</s:if>>
        <dt>
         <a href="player_init.do?videoId=${video.id }" target=_blank><!-- 在新的窗口中打开超链接 -->
         <img alt="${video.tittle }" src="<s:property value="@edu.ahpu.boke.util.Const@UPLOAD_REAL_PATH" />
                /${video.picFileName }.jpg" />
               <span>${minute }:${second }</span>
         </a>
        </dt>
        <dd> 
        <h3>
        <a href="player_init.do?videoId=${video.id }">${video.tittle}</a>
        </h3>  
        </dd>  
        
        <dd>
                         发布:<s:date name="#video.uploadTime" format="YYYY-MM-DD" />
        </dd>  
        
        <dd>
                         播放：${video.playCount }
        </dd>  
        
        <dd>
                         评论：${video.commentCount }
        </dd>  
      </dl> 
    </s:iterator>
  </div>
  
  <div>
     <s:if test="#page_bean.currentPage==1">
       <span>首页</span>
       <span>上一页</span>
     
     </s:if>
     <s:else>
       <a href="main.do?channelId="${channelId }&orderId=${orderId }&page=1">首页</a>      
       <a href="main.do?channelId="${channelId }&orderId=${orderId }&page=${page_bean.currentPage-1}">上一页</a>   
     </s:else>  
     <s:iterator begin="#page_bean.startPage" end="#page_bean.endPage" var="p">
       <s:if test="#p==#page_bean.currentPage">
         <span>${p }</span>      
       </s:if>
       <s:else>
         <a href="main.do?channelId="${channelId }&orderId=${orderId }&page=${p}">${p }</a>
       </s:else>
     </s:iterator>    
     <s:if test="#page_bean.currentPage==#page_bean.pageCount">
       <span>下一页</span>
       <span>末页</span>
     </s:if>
     <s:else>
      <a href="main.do?channelId="${channelId }&orderId=${orderId }&page=${page_bean.currentPage+1}">下一页</a>      
       <a href="main.do?channelId="${channelId }&orderId=${orderId }&page=${page_bean.pageCount}">末页</a>
     </s:else>   
  </div>
  
  <div>
    <p>Copyright@2022-2088-3000</p>
    <p>版权所有，欢迎盗版</p> 
  </div>
 </s:form>    
  </body>
</html>
