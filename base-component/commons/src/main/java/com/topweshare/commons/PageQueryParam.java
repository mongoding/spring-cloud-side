package com.topweshare.commons;

import java.io.Serializable;

/**
 * 分页查询基本参数
 * 
 * @author mongoding
 *
 */
public class PageQueryParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页码
	 */
	private int pageNum;

	/**
	 * 每页记录数
	 */
	private int pageSize;
	
	public PageQueryParam() {
		this(1, 10);
	}

	public PageQueryParam(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRecord() {
		return pageSize * (this.pageNum - 1);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageQueryParam [pageNum=" + pageNum + ", pageSize=" + pageSize + ", getStartRecord()=" + getStartRecord() + "]";
	}

}
