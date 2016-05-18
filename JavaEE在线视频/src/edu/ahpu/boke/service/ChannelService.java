package edu.ahpu.boke.service;

import java.util.List;

import edu.ahpu.boke.domain.Channel;

public interface ChannelService {
	
	public List<Channel> findAllChannels();
	
	public Channel findChannel(int channelId);
	
	
}
