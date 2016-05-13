package edu.ahpu.boke.service;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import edu.ahpu.boke.dao.ChannelDao;
import edu.ahpu.boke.dao.VideoDao;
import edu.ahpu.boke.domain.Video;
import edu.ahpu.boke.util.Const;
import edu.ahpu.boke.util.VideoConverter;


@Service
public class VideoServiceImpl implements VideoService {
	
	@Resource
    private ChannelDao channelDao;
	@Resource
	private VideoDao videoDao;
	@Override
	public void addVideo(Integer userId, int channelId, String title, String tags,
			String description, File videoFile, String fileNameOnClient)
					throws Exception {
		// TODO Auto-generated method stub
		if(videoFile!=null){
			Encoder encoder=new Encoder();
			long duration=0;
			MultimediaInfo m;
			try{
				m=encoder.getInfo(videoFile);
				if(m==null||m.getVideo()==null){
					throw new Exception("不能识别编码格式");//
					
				}
				duration=m.getDuration()/1000;//获取播放时长
			}catch(InputFormatException e){
				throw e;
			}catch(EncoderException e){
				throw e;
			}
			//随机产生一个UUID作为视频在服务端的名称
			String fileNameOnServer=UUID.randomUUID().toString();
			File fileOnServer=new File(Const.UPLOAD_REAL_PATH,fileNameOnServer);
			try{
				FileUtils.copyFile(videoFile, fileOnServer);//复制文件
			}catch(IOException e){
				throw new Exception("复制文件时发生错误");
			}
			
			Video video=new Video();
			video.setChannelId(channelDao.findById(channelId).getId());
			video.setUserId(userId);
			video.setClientFileName(fileNameOnClient);
			video.setServerFileName(fileNameOnServer);
			video.setPicFileName(fileNameOnServer);
			video.setTitle(title);
			video.setTags(tags);
			video.setDescription(description);
			video.setPlayCount(0);
			video.setGoodCommentCount(0);
			video.setBadCommentCount(0);
			video.setDuration((int) duration);
			video.setUploadTime(new Timestamp(System.currentTimeMillis()));
			video.setStatus(Const.VIEDO_STATUS_UPLOADED);
			videoDao.save(video);
			
			VideoConverter converter=VideoConverter.getInstance();
			if(converter.getVideoDao()==null){
				//设置VideoConverter类的videoDao字段
				converter.setVideoDao(videoDao);
				
			}
			converter.add(video);//将视频添加到转码队列
			converter.startConvertJob();
			
			
			
			
			
		}

	}

}
