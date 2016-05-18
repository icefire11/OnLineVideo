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
	
	private static boolean isRunning=false;//标识转码线程是否在运行
	private boolean stopFlag=false;//控制线程结束的标志
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
	
	//添加视频到队列
	public void add(Video v){
		queue.offer(v);
	}
	public void stopConvertJob(){
		stopFlag=true;
	}
	//开始转码
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
						convert(v);//转码
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
	//对视频进行转码和截图
	
	public void convert(Video v){
		String filename=v.getServerFileName();
		System.out.println("getServerName"+v.getServerFileName());
		long duration =v.getDuration();
		
		//得到转码视频的完整路径
		
		String oldFileFullName=Const.UPLOAD_REAL_PATH+"/"+filename;
		System.out.println("oldFileFullName"+oldFileFullName);
		
		//转码得到AVI文件的完整路径
		String aviFileFullName=oldFileFullName+".avi";
		
		//转码得到FLV文件的完整路径
		String flvFileFullName=oldFileFullName+".flv";
		
		//转码得到的截图文件的完整路径
		String picFileFullName=oldFileFullName+".jpg";
		
		File oldFile=new File(oldFileFullName);
		File aviFile=new File(aviFileFullName);
		
		//拼接mencoder转码命令（转为avi）
		StringBuffer cmd=new StringBuffer(Const.MENCODER_EXE);
		cmd.append(" ");
		cmd.append(oldFileFullName);
		cmd.append(" -oac mp3lame -lameopts preset=64 -ovc xvid -xvidencopts bitrate=600 -of avi -o ");
		cmd.append(aviFileFullName);
		cmd.append("\r\n");
		
		//拼接ffmpeg转码命令（转为flv）
		cmd.append(Const.FFMPEG_EXE);
		cmd.append(" -i ");
		cmd.append(aviFileFullName);
		cmd.append(" -ab 128 -acodec libmp3lame -ac 1 -ar 22050 -r 29.97 -qscale 6 -y ");
		cmd.append(flvFileFullName);
		cmd.append("\r\n");
		
		//拼接ffmeg截图命令
		cmd.append(Const.FFMPEG_EXE);
		cmd.append(" -i ");
		cmd.append(flvFileFullName);
		//在视频播放时长的中间截图
		cmd.append(" -y -f image2 -ss "+(duration/2)+" -t 0.001 -s 120x90 ");
		cmd.append(picFileFullName);
		cmd.append("\r\n");
		cmd.append("exit");//退出windows命令窗口的命令
		
		try{
			//将上述若干命令串写入批处理文件
			File batchFile=new File(Const.UPLOAD_REAL_PATH+"/conver.bat");
			System.out.println("UPLOAD_REAL_PATH:"+Const.UPLOAD_REAL_PATH);
			FileWriter fw =new FileWriter(batchFile);
			fw.write(cmd.toString());
			fw.flush();fw.close();
			
			System.out.println("转码开始。。。");
			//调用本地cmd命令执行批处理文件
			Runtime rt=Runtime.getRuntime();
			Process proc = rt.exec("cmd.exe /C start " + batchFile.getAbsolutePath());
			//下面的代码主要使得proc与当前线程同步，与转码业务无关。
			InputStream stderr =proc.getErrorStream();
			InputStreamReader isr=new InputStreamReader(stderr);
			BufferedReader br=new BufferedReader(isr);
			while (br.readLine()!=null){
				//readline为阻塞方法，不做任何处理
			}
			proc.waitFor();
			br.close();
			batchFile.delete();//转码完毕后删除批处理文件
			
			System.out.println("转码完毕");	
		}catch(IOException e){
			System.out.println("文件读写失败。。。");
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		oldFile.delete();//转码完毕删除转码前视频文件
		aviFile.delete();//删除转码的中间文件
		
		queue.remove(v);
		v.setStatus(Const.VIDEO_STATUS_CONVERTED);
		videoDao.update(v);
		
		
		
		
	}
	
	
	

}
