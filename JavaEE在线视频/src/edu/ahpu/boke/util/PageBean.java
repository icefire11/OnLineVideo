package edu.ahpu.boke.util;

import java.util.List;

public class PageBean {
	
	private int rowCount;//总记录数
	private int currentPage;//当前页
	private int pageSize;//每页记录数
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

	private int pageCount;//总页数
	private int offset;//当前首条记录位置
    private int startPage;//分页导航按钮的起始页号
    private int endPage;//分页导航按钮的结束页号
    private List<?> contents;//当前页的内容

	public PageBean(int rowCount,Integer page,int pageSize,int pageButtonSize) {
		this.rowCount=rowCount;
		this.pageSize=pageSize;
		
		//计算总页数
		if(rowCount==0){
			this.pageCount=1;
		}else if(rowCount % pageSize==0){
			this.pageCount=rowCount/pageSize;
		}else{
			this.pageCount=rowCount/pageSize+1;	
		}
		
		//计算当前页面
		if(page<0||page==null){
			this.currentPage=1;
		}else if(page>pageCount){
			this.currentPage=pageCount;
		}else{
			this.currentPage=page;
		}
		
		//计算当前首条记录的位置
	    this.offset=pageSize*(this.currentPage-1);
	    
	    //单击了页面中的最后一个分页导航按钮，如第30页(射每页10个分页按钮)
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
