package edu.ahpu.boke.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.ahpu.boke.dao.VideoDao;
import edu.ahpu.boke.domain.Channel;
import edu.ahpu.boke.domain.Video;
import edu.ahpu.boke.util.Const;
import edu.ahpu.boke.util.PageBean;

@Service
public class PageServiceImpl implements PageService {
	@Resource
	public VideoDao videoDao;

	@Override
	public PageBean getVideoPageBean(int channelId, int orderId,
			Integer page, int pageSize, int pageButtonSize) {
		// TODO Auto-generated method stub
		//根据视频所属所属频道以及视频状态查询
		String whereHql="and o.channelId=? and o.status=? ";
		
		//正式应用中，转码后的视频还需人工审核，故视频状态应为Const.VIDEO_STATUS_CONVERTED
		Object[] params=new Object[]{channelId,Const.VIDEO_STATUS_CONVERTED};
		
		//按排序字段名降顺序排列
		
		Map<String,String> orderBy=new LinkedHashMap<String,String>();
		
		orderBy.put("o."+Const.VIDEO_ORDER_FIELDS[orderId],Const.ORDER_DESC );
		int rowCount=videoDao.getRowCount(whereHql, params);//总记录数
		PageBean pageBean=new PageBean(rowCount,page,pageSize,pageButtonSize);
		List<Video> list=videoDao.findByConditionWithPaging(whereHql, params, orderBy,pageBean.getOffset(), pageSize);
		
		
		
		return pageBean;
	}

}
