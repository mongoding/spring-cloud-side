package com.topweshare.utils.web;

import java.util.List;

/**
 * 存放查询结果信息的类,从这个类中可以获取结果集,分页信息
 *
 * @param
 * @author hc.tang
 */
public class DefaultGridResult implements GridResult {
    private List<?> list;
    private int total = 0;
    private int start = 0;
    private int pageIndex = 1;
    private int pageSize = 10;
    private int pageCount = 0;

    public DefaultGridResult() {
        super();
    }

    public DefaultGridResult(List<?> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            this.total = list.size();
        }
    }

    public DefaultGridResult(List<?> list, int total, int pageIndex,
                             int pageSize) {
        this.list = list;
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;

        // 计算总页数
        if (total == 0) {
            this.pageCount = 0;
        } else {
            this.pageCount = calcPageCount(total, pageSize);
        }
    }

    /**
     * 分页数算法:页数 = (总记录数  +  每页记录数  - 1) / 每页记录数
     *
     * @param total
     * @param pageSize
     * @return
     */
    public static int calcPageCount(int total, int pageSize) {
        return pageSize == 0 ? 1 : (total + pageSize - 1) / pageSize;
    }

    public int getCurrentPageIndex() {
        return pageIndex;
    }

    /**
     * 上一页
     *
     * @return
     */
    public int getPrePageIndex() {
        return (pageIndex - 1 <= 0) ? 1 : pageIndex - 1;
    }

    /**
     * 下一页
     *
     * @return
     */
    public int getNextPageIndex() {
        return (pageIndex + 1 > pageCount) ? pageCount : pageIndex + 1;
    }

    /**
     * 首页
     *
     * @return
     */
    public int getFirstPageIndex() {
        return 1;
    }

    /**
     * 最后一页
     *
     * @return
     */
    public int getLastPageIndex() {
        return pageCount;
    }

    /**
     * 结果集
     *
     * @return
     */
    public List<?> getRows() {
        return list;
    }

    /**
     * 总记录数
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * 当前页索引,等同于getCurrentPageIndex()
     *
     * @return
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 每页显示几条记录
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 共几页
     *
     * @return
     */
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStart() {
        return start;
    }

    @Override
    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public void setList(List<?> list) {
        this.list = list;
    }

}
