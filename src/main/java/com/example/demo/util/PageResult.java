package com.example.demo.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页对象
 * @author ：yangjin.
 * @Date ：Created in 20:52 2019/11/27
 */
@ApiModel(value="分页对象", description="分页对象")
public class PageResult<T>  implements IPage<T> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总条数-查询时不需要传递参数")
    private long total;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private long size;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private long current;
    @ApiModelProperty("当前页数量")
    private Integer pageSize = 10;
    @ApiModelProperty("当前页")
    private Integer pageNo = 1;
    @ApiModelProperty("列表数据-查询时不需要传递参数")
    private List<T> records;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String[] ascs;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String[] descs;

    @Override
    public String[] descs() {
        return this.descs;
    }

    @Override
    public String[] ascs() {
        return this.ascs;
    }

    public PageResult<T> setDescs(List<String> descs) {
        if (CollectionUtils.isNotEmpty(descs)) {
            this.descs = (String[])descs.toArray(new String[0]);
        }
        return this;
    }

    public PageResult<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    public PageResult(Integer pageSize, Integer pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.size =pageSize;
        this.current = pageNo;
    }

    public PageResult() {
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public PageResult<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public PageResult<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public PageResult<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public PageResult<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        setSize(pageSize);
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        setCurrent(pageNo);
        this.pageNo = pageNo;
    }
}
