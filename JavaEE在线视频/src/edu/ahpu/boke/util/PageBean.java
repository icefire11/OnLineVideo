package edu.ahpu.boke.util;

import java.util.List;

public class PageBean {
	
	private int rowCount;//�ܼ�¼��
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿҳ��¼��
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<?> getContents() {
		return contents;
	}

	public void setContents(List<?> contents) {
		this.contents = contents;
	}

	private int pageCount;//��ҳ��
	private int offset;//��ǰ������¼λ��
    private int startPage;//��ҳ������ť����ʼҳ��
    private int endPage;//��ҳ������ť�Ľ���ҳ��
    private List<?> contents;//��ǰҳ������

	public PageBean(int rowCount,Integer page,int pageSize,int pageButtonSize) {
		this.rowCount=rowCount;
		this.pageSize=pageSize;
		
		//������ҳ��
		if(rowCount==0){
			this.pageCount=1;
		}else if(rowCount % pageSize==0){
			this.pageCount=rowCount/pageSize;
		}else{
			this.pageCount=rowCount/pageSize+1;	
		}
		
		//���㵱ǰҳ��
		if(page<0||page==null){
			this.currentPage=1;
		}else if(page>pageCount){
			this.currentPage=pageCount;
		}else{
			this.currentPage=page;
		}
		
		//���㵱ǰ������¼��λ��
	    this.offset=pageSize*(this.currentPage-1);
	    
	    //������ҳ���е����һ����ҳ������ť�����30ҳ(��ÿҳ10����ҳ��ť)
	    if (this.currentPage%pageButtonSize==0){
	    	this.startPage=(this.currentPage/pageButtonSize-1)*10+3;
	    	this.endPage=(this.currentPage/pageButtonSize)*10+2;
	    }else{
	    	this.startPage=(this.currentPage/pageButtonSize)*10+1;
	    	this.endPage=(this.currentPage/pageButtonSize+1)*10;
	    }
	    
	    if(this.startPage<1){
	    	this.startPage=1;
	    }
	    if(this.pageCount==0){
	    	this.endPage=1;
	    }else if(endPage>pageCount){
	    	endPage=pageCount;
	    }
	    
	    
		
	}

}
