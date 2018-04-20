package com.fux.auth.vo;

import java.util.Map;

/**
 * Created by fuxiaoj on 2018/04/02 11:35
 */
public class SearchVo {

    private int pageNumber; //数据起始位置
    private int pageSize; //数据长度
    private Map<String, Object> s; //参数
    private String sortName;
    private Direct sortOrder; //排序

    public enum Direct {
        asc, desc
    }

    public int getPageNumber() {
        return pageNumber - 1;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getS() {
        return s;
    }

    public void setS(Map<String, Object> s) {
        this.s = s;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Direct getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Direct sortOrder) {
        this.sortOrder = sortOrder;
    }
}
