package com.fux.auth.util;

import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * Created by fuxiaoj on 2018/04/02 11:08
 */
public class TableSplitResult {

    private Collection<?> rows;
    private long total;

    public TableSplitResult() {

    }
    public TableSplitResult(Page<?> page) {
        this.rows = page.getContent();
        this.total = page.getTotalElements();
    }
    public Collection<?> getRows() {
        return rows;
    }

    public void setRows(Collection<?> rows) {
        this.rows = rows;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
}
