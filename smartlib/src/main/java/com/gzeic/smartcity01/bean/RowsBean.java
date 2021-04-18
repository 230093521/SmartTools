package com.gzeic.smartcity01.bean;

public class RowsBean {
    /**
     * id : 5
     * imgUrl : /profile/1-yingdao.jpg
     * type : 47
     * createTime : 2020-10-12T22:55:17.000+0800
     * sort : 1
     * display : N
     */

    private int id;
    private String imgUrl;
    private String type;
    private String createTime;
    private String sort;
    private String display;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
