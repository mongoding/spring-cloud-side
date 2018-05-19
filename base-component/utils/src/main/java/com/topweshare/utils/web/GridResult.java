package com.topweshare.utils.web;

import java.util.List;

public interface GridResult {
    /**
     * 设置结果集
     */
    void setList(List<?> list);

    /**
     * 设置记录总数
     */
    void setTotal(int total);

    /**
     * 设置第一条记录起始位置
     */
    void setStart(int start);

    /**
     * 设置当前页索引
     */
    void setPageIndex(int pageIndex);

    /**
     * 设置每页记录数
     */
    void setPageSize(int pageSize);

    /**
     * 设置总页数
     */
    void setPageCount(int pageCount);
}
