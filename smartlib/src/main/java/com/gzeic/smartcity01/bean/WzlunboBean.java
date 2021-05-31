package com.gzeic.smartcity01.bean;

import java.util.List;

public class WzlunboBean {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:35:36","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":21,"appType":"traffic","status":"1","sort":1,"advTitle":"新闻","advImg":"/prod-api/profile/upload/image/2021/05/05/ff133289-6f6d-47c7-bf55-9bf6b43c1a48.jpeg","servModule":"news","targetId":null,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:35:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":22,"appType":"traffic","status":"1","sort":2,"advTitle":"新闻","advImg":"/prod-api/profile/upload/image/2021/05/05/0b83479e-72d9-4342-93e8-750fea1a053d.jpeg","servModule":"news","targetId":null,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:45:44","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":23,"appType":"traffic","status":"1","sort":3,"advTitle":"新闻","advImg":"/prod-api/profile/upload/image/2021/05/05/003adf91-6721-4854-a48e-95fe1cd496f6.jpeg","servModule":"news","targetId":null,"type":"2"}]
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
         * createTime : 2021-05-05 12:35:36
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 21
         * appType : traffic
         * status : 1
         * sort : 1
         * advTitle : 新闻
         * advImg : /prod-api/profile/upload/image/2021/05/05/ff133289-6f6d-47c7-bf55-9bf6b43c1a48.jpeg
         * servModule : news
         * targetId : null
         * type : 2
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String appType;
        private String status;
        private int sort;
        private String advTitle;
        private String advImg;
        private String servModule;
        private Object targetId;
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

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
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

        public Object getTargetId() {
            return targetId;
        }

        public void setTargetId(Object targetId) {
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
