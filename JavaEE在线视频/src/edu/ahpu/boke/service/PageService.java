package edu.ahpu.boke.service;

import edu.ahpu.boke.domain.Channel;
import edu.ahpu.boke.util.PageBean;

public interface PageService {
	public PageBean getVideoPageBean(int channelId,int orderId,Integer page,int pageSize,int pageButtonSize);

}
