package edu.ahpu.boke.util;

public class Const {
	//标示session中的验证码
	public static final String KEY_VERIFICATION_CODE="verification_code";
	//标识session中登录用户对象
	public static final String KEY_LOGINED_USER="logined_user";
	
	//注册时默认用户头像的id
	public static final int DEFAULT_CHANNEL_ID=1;
	//config表中代表"默认用户头像id"的配置项的名称
	public static final String CONFIG_NAME_DEFAULT_FACE_ID="default_face_id";
	
	//存放视频及截图文件的文件夹名称
	public static final String UPLOAD_FOLDER="video";
	
	//UPLOAD_FLODER的绝对路径
	public static String UPLOAD_REAL_PATH="D:/ProgramTool/Upload";
	//上传文件的最大大小（200M)
    public static final long MAX_UPLOAD_FILE_SIZE=200*1024*1024;
    
    //视频状态
    public static final String VIEDO_STATUS_UPLOADED="U"; //已经上传
    public static final String VIDEO_STATUS_CONVERTED="C";//已转码
    public static final String VIDEO_STATUS_APPROVED="A";//已审核
    
    //转码工具mencoder和ffmpeg所在的路径
    public static final String MENCODER_EXE="E:/mplayer/mencoder.exe";
    public static final String FFMPEG_EXE="E:/mplayer/ffmepg/bin/ffmpeg.exe";
    
    //用于排序的常量
    
    public static final String ORDER_ASC="asc";
    public static final String ORDER_DESC="desc";
    
    //对视频排序时基于的字段名称
    public static final String[] VIDEO_ORDER_FIELDS={"uploadTime","playCount","commentCount","goodCommentCount"};	
    
    //用于分页的常量
    
    public static final int VIDEO_SIZE_PER_PAGE=10;//每页的视频数
    public static final int PAGE_BUTTON_SIZE_PER_PAGE=10;//每页的分页按钮个数
}
