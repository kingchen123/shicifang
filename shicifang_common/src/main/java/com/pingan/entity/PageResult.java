package com.pingan.entity;

import java.util.List;

/**
 * 这个实体类,用于返回分页结果
 * @param <T>
 */
public class PageResult<T> {
    private Long total;//总数据条数
    private List<T> rows;//每页数据集合

    public PageResult(Long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
