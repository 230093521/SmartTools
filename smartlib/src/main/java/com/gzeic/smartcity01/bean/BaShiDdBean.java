package com.gzeic.smartcity01.bean;

import java.util.List;

public class BaShiDdBean {
    /**
     * total : 20
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2020-10-24 19:23:31","updateBy":null,"updateTime":"2020-12-03 15:56:33","remark":null,"params":{},"orderNum":"60353861","id":1,"path":"一号线","start":"泰德大厦","end":"大连北站","price":8,"userName":"张三","userTel":"12345611","userId":1,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-24 19:25:36","updateBy":null,"updateTime":"2020-12-03 15:56:35","remark":null,"params":{},"orderNum":"60353873","id":2,"path":"一号线","start":"泰德大厦","end":"大连北站","price":8,"userName":"张三","userTel":"12345611","userId":1,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-24 19:26:13","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60353877","id":3,"path":"一号线","start":"泰德大厦","end":"大连北站","price":8,"userName":"张三","userTel":"12345611","userId":1,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 09:45:23","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60376312","id":4,"path":"一号线","start":"泰德大厦","end":"大连北站","price":8,"userName":"张三","userTel":"12345611","userId":1,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 10:25:05","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60376550","id":5,"path":"一号线","start":"泰德大厦","end":"大连北站","price":8,"userName":"张三","userTel":"12345611","userId":1,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 13:34:34","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60377687","id":6,"path":"一号线","start":"西安路","end":"万达广场","price":8,"userName":"吴霄12","userTel":"17767746537","userId":2,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 13:35:22","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60377692","id":7,"path":"一号线","start":"西安路","end":"万达广场","price":8,"userName":"吴霄12","userTel":"17767746537","userId":2,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 13:35:35","updateBy":null,"updateTime":"2020-10-27 15:27:59","remark":null,"params":{},"orderNum":"60377693","id":8,"path":"一号线","start":"西安路","end":"万达广场","price":8,"userName":"吴霄12","userTel":"17767746537","userId":2,"status":1},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 13:50:42","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60377784","id":10,"path":"一号线","start":"解放路","end":"南湖大厦","price":8,"userName":"吴霄12","userTel":"17767746537","userId":2,"status":0},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 13:55:46","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderNum":"60377814","id":11,"path":"二号线","start":"西安路","end":"万达广场","price":8,"userName":"吴霄12","userTel":"17767746537","userId":2,"status":0}]
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
         * createBy : null
         * createTime : 2020-10-24 19:23:31
         * updateBy : null
         * updateTime : 2020-12-03 15:56:33
         * remark : null
         * params : {}
         * orderNum : 60353861
         * id : 1
         * path : 一号线
         * start : 泰德大厦
         * end : 大连北站
         * price : 8.0
         * userName : 张三
         * userTel : 12345611
         * userId : 1
         * status : 1
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private String orderNum;
        private int id;
        private String path;
        private String start;
        private String end;
        private double price;
        private String userName;
        private String userTel;
        private int userId;
        private int status;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
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

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public static class ParamsBean {
        }
    }
}
