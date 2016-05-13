package edu.ahpu.boke.service;

import java.io.File;

import edu.ahpu.boke.domain.User;

public interface VideoService {
	
	public void addVideo(Integer userId,int channelId,String title,String tags,String description,File videoFile,String fileNameOnClient)throws Exception;

}
