package com.hh.aws.web.dto;

/**
 * 分页
 */
public class PageDto {
    private int page = 1;
    private int size = 20;
//    private String sortName;
//    private String sortType;

    public PageDto(int page) {
        this.page = page;
    }

    public PageDto(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
