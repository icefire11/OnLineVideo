package edu.ahpu.boke.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.ahpu.boke.dao.ChannelDao;
import edu.ahpu.boke.dao.UserDao;
import edu.ahpu.boke.dao.VideoDao;
import edu.ahpu.boke.domain.Channel;
import edu.ahpu.boke.domain.User;
import edu.ahpu.boke.domain.Video;
import edu.ahpu.boke.service.ChannelService;
import edu.ahpu.boke.service.PageService;
import edu.ahpu.boke.util.Const;
import edu.ahpu.boke.util.PageBean;

@Controller
public class MainAction extends BaseAction {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private ChannelDao channelDao;
	
	@Resource
	private VideoDao videoDao;
	
	@Resource
	private ChannelService channelService;
	
	@Resource
	private PageService pageService;
	
	private int channelId;
	private int orderId;
	private int page;
	

	@Override
	public String execute() throws Exception {
		//generateData4Test(513);
		// TODO Auto-generated method stub
		if(orderId!=1&&orderId!=2&&orderId!=3&&orderId!=4){
			orderId=1;
		}
		List<Channel> all_channels=channelService.findAllChannels();
		Channel channel=channelService.findChannel(channelId);
		channelId=channel.getId();//传入的频道Id可能不存在
		String channelName=channel.getName();
		
		PageBean videoPageBean=pageService.getVideoPageBean(channelId, orderId-1, page, Const.VIDEO_SIZE_PER_PAGE, Const.PAGE_BUTTON_SIZE_PER_PAGE);
		
		//将各对象写入到Context
		ActionContext.getContext().put("all_channels", all_channels);
		ActionContext.getContext().put("channelId",channelId);
		ActionContext.getContext().put("channelName",channelName);
		ActionContext.getContext().put("page_bean", videoPageBean);
		
		
		
		
		return "main";
	}
	private void generateData4Test(int count){
		Random r=new Random();
		String s="视频";
		User u=userDao.findById(1);
		Channel c=channelDao.findById(1);
		int i=0;
		while(i++<count){
			Video v=new Video();
			v.setChannelId(c.getId());
			v.setUserId(u.getId());
			v.setClientFileName("1.mkv");
			v.setServerFileName("cfec9c8f-32ae-4ff0-91ff-7d71594c7e69");
			v.setPicFileName("cfec9c8f-32ae-4ff0-91ff-7d71594c7e69");
			v.setTitle(s+"标题"+i);
			v.setTags("标签1标签2标签3");
			v.setDescription(s+"描述"+i);
			v.setPlayCount(r.nextInt(10000));
			v.setCommentCount(r.nextInt(10000));
			v.setGoodCommentCount(r.nextInt(1000));
			v.setBadCommentCount(r.nextInt(100));
			v.setDuration(r.nextInt(3000)+10);
			v.setUploadTime(new Timestamp(System.currentTimeMillis()));
			v.setStatus(Const.VIDEO_STATUS_CONVERTED);
			videoDao.save(v);
		}
	}
	
	

}
