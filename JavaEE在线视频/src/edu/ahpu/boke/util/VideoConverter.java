package edu.ahpu.boke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.Resource;

import edu.ahpu.boke.dao.VideoDao;
import edu.ahpu.boke.domain.Video;

public class VideoConverter {
	
	private static boolean isRunning=false;//��ʶת���߳��Ƿ�������
	private boolean stopFlag=false;//�����߳̽����ı�־
	private static Queue<Video> queue;
	
	private VideoDao videoDao;//
	
	private static VideoConverter instance;
	static{
		instance=new VideoConverter();
		queue=new LinkedList<Video>();
	}
	private VideoConverter(){
		
		
	}
	
	public static VideoConverter getInstance(){
		return instance;
	}
	public VideoDao getVideoDao(){
		return videoDao;
		
	}
	public void setVideoDao(VideoDao videoDao){
		this.videoDao=videoDao;
	}
	
	//�����Ƶ������
	public void add(Video v){
		queue.offer(v);
	}
	public void stopConvertJob(){
		stopFlag=true;
	}
	//��ʼת��
	public void startConvertJob(){
		if(isRunning){
			return;
		}
		new Thread(){
			@Override
			public void run(){
				while(!stopFlag){
					Video v=queue.peek();
					if(v!=null){
						convert(v);//ת��
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
		}.start();
		isRunning=true;
	}
	//����Ƶ����ת��ͽ�ͼ
	
	public void convert(Video v){
		String filename=v.getServerFileName();
		System.out.println("getServerName"+v.getServerFileName());
		long duration =v.getDuration();
		
		//�õ�ת����Ƶ������·��
		
		String oldFileFullName=Const.UPLOAD_REAL_PATH+"/"+filename;
		System.out.println("oldFileFullName"+oldFileFullName);
		
		//ת��õ�AVI�ļ�������·��
		String aviFileFullName=oldFileFullName+".avi";
		
		//ת��õ�FLV�ļ�������·��
		String flvFileFullName=oldFileFullName+".flv";
		
		//ת��õ��Ľ�ͼ�ļ�������·��
		String picFileFullName=oldFileFullName+".jpg";
		
		File oldFile=new File(oldFileFullName);
		File aviFile=new File(aviFileFullName);
		
		//ƴ��mencoderת�����תΪavi��
		StringBuffer cmd=new StringBuffer(Const.MENCODER_EXE);
		cmd.append(" ");
		cmd.append(oldFileFullName);
		cmd.append(" -oac mp3lame -lameopts preset=64 -ovc xvid -xvidencopts bitrate=600 -of avi -o ");
		cmd.append(aviFileFullName);
		cmd.append("\r\n");
		
		//ƴ��ffmpegת�����תΪflv��
		cmd.append(Const.FFMPEG_EXE);
		cmd.append(" -i ");
		cmd.append(aviFileFullName);
		cmd.append(" -ab 128 -acodec libmp3lame -ac 1 -ar 22050 -r 29.97 -qscale 6 -y ");
		cmd.append(flvFileFullName);
		cmd.append("\r\n");
		
		//ƴ��ffmeg��ͼ����
		cmd.append(Const.FFMPEG_EXE);
		cmd.append(" -i ");
		cmd.append(flvFileFullName);
		//����Ƶ����ʱ�����м��ͼ
		cmd.append(" -y -f image2 -ss "+(duration/2)+" -t 0.001 -s 120x90 ");
		cmd.append(picFileFullName);
		cmd.append("\r\n");
		cmd.append("exit");//�˳�windows����ڵ�����
		
		try{
			//�������������д���������ļ�
			File batchFile=new File(Const.UPLOAD_REAL_PATH+"/conver.bat");
			System.out.println("UPLOAD_REAL_PATH:"+Const.UPLOAD_REAL_PATH);
			FileWriter fw =new FileWriter(batchFile);
			fw.write(cmd.toString());
			fw.flush();fw.close();
			
			System.out.println("ת�뿪ʼ������");
			//���ñ���cmd����ִ���������ļ�
			Runtime rt=Runtime.getRuntime();
			Process proc = rt.exec("cmd.exe /C start " + batchFile.getAbsolutePath());
			//����Ĵ�����Ҫʹ��proc�뵱ǰ�߳�ͬ������ת��ҵ���޹ء�
			InputStream stderr =proc.getErrorStream();
			InputStreamReader isr=new InputStreamReader(stderr);
			BufferedReader br=new BufferedReader(isr);
			while (br.readLine()!=null){
				//readlineΪ���������������κδ���
			}
			proc.waitFor();
			br.close();
			batchFile.delete();//ת����Ϻ�ɾ���������ļ�
			
			System.out.println("ת�����");	
		}catch(IOException e){
			System.out.println("�ļ���дʧ�ܡ�����");
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		oldFile.delete();//ת�����ɾ��ת��ǰ��Ƶ�ļ�
		aviFile.delete();//ɾ��ת����м��ļ�
		
		queue.remove(v);
		v.setStatus(Const.VIDEO_STATUS_CONVERTED);
		videoDao.update(v);
		
		
		
		
	}
	
	
	

}
