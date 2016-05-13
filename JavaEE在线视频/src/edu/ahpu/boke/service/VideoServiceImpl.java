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
					throw new Exception("����ʶ������ʽ");//
					
				}
				duration=m.getDuration()/1000;//��ȡ����ʱ��
			}catch(InputFormatException e){
				throw e;
			}catch(EncoderException e){
				throw e;
			}
			//�������һ��UUID��Ϊ��Ƶ�ڷ���˵�����
			String fileNameOnServer=UUID.randomUUID().toString();
			File fileOnServer=new File(Const.UPLOAD_REAL_PATH,fileNameOnServer);
			try{
				FileUtils.copyFile(videoFile, fileOnServer);//�����ļ�
			}catch(IOException e){
				throw new Exception("�����ļ�ʱ��������");
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
				//����VideoConverter���videoDao�ֶ�
				converter.setVideoDao(videoDao);
				
			}
			converter.add(video);//����Ƶ��ӵ�ת�����
			converter.startConvertJob();
			
			
			
			
			
		}

	}

}
