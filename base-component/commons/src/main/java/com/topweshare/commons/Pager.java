package com.topweshare.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 7681166772410198804L;

	/**
	 *  默认当前页码
	 */
	private int pageNum;

	/**
	 *  默认每页显示记录条数
	 */
	private int pageSize;

	/**
	 *  总记录数
	 */
	private int recordCount;
	
	/**
	 * 页数
	 */
	private int pageCount;

	/**
	 * 数据列表
	 */
	private List<T> recordList ;

	public Pager() {
		this.pageNum = 1;
		this.pageSize = 5;
		recordList = new ArrayList<T>();
	}

	public Pager(int recordCount) {
		this.recordCount = recordCount;
	}

	public Pager(int recordCount, int pageSize) {
		this.recordCount = recordCount;
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		int pageCount = recordCount / pageSize;
		if (recordCount % pageSize > 0) {
			pageCount++;
		}
		if (pageCount == 0) {
			pageCount = 1;
		}
		this.pageCount = pageCount;
		return this.pageCount;
	}

	public int getStartRecord() {
		int startRecord = pageSize * (pageNum - 1);
		return startRecord;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum < 1) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	@Override
	public String toString() {
		return "Pager [pageNum=" + pageNum + ", pageSize=" + pageSize + ", recordCount=" + recordCount + ", pageCount="
				+ pageCount + ", recordList=" + recordList + "]";
	}
	
}
