package com.topweshare.cache.util;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据
	 */
	private List<T> data;

	/**
	 * 总数量
	 */
	private long totalNum;

	/**
	 * 总页数
	 */
	private int totalPages;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
