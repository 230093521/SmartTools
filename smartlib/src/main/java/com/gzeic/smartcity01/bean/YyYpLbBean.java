package com.gzeic.smartcity01.bean;

import java.util.List;

public class YyYpLbBean {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:20:57","updateBy":"1","updateTime":"2021-05-10 10:41:50","remark":null,"params":{},"id":15,"appType":"movie","status":"1","sort":1,"advTitle":"海报","advImg":"/prod-api/profile/upload/image/2021/05/10/16407a1d-123b-499f-84fb-ca290995b101.png","servModule":"影片","targetId":11,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:25:58","updateBy":"1","updateTime":"2021-05-05 12:29:09","remark":null,"params":{},"id":16,"appType":"movie","status":"1","sort":2,"advTitle":"海报","advImg":"/prod-api/profile/upload/image/2021/05/05/d754c43e-02ee-4697-82d4-2239a54025ef.jpeg","servModule":"movie","targetId":null,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:28:53","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"appType":"movie","status":"1","sort":3,"advTitle":"海报","advImg":"/prod-api/profile/upload/image/2021/05/05/3e5edaf4-ef0e-478b-8875-483242d4711a.jpeg","servModule":"movie","targetId":null,"type":"2"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2021-05-05 12:20:57
         * updateBy : 1
         * updateTime : 2021-05-10 10:41:50
         * remark : null
         * params : {}
         * id : 15
         * appType : movie
         * status : 1
         * sort : 1
         * advTitle : 海报
         * advImg : /prod-api/profile/upload/image/2021/05/10/16407a1d-123b-499f-84fb-ca290995b101.png
         * servModule : 影片
         * targetId : 11
         * type : 2
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String appType;
        private String status;
        private int sort;
        private String advTitle;
        private String advImg;
        private String servModule;
        private int targetId;
        private String type;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class ParamsBean {
        }
    }
}
