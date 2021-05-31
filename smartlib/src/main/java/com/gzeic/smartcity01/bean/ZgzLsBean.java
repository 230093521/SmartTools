package com.gzeic.smartcity01.bean;

import java.util.List;

public class ZgzLsBean {


    /**
     * total : 12
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"userId":101,"postId":null,"companyId":3,"postName":"软件开发","companyName":"虎鱼科技","money":"5000","satrTime":"2020-11-04","userName":"user01"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"userId":2,"postId":null,"companyId":2,"postName":"五险一金+6k英语老师","companyName":"左培互联网有限公司","money":"5000","satrTime":"2020-11-04","userName":"user1"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"userId":1,"postId":null,"companyId":3,"postName":"软件开发","companyName":"虎鱼科技","money":"10000","satrTime":"2021-05-01","userName":"admin"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"userId":2,"postId":null,"companyId":10,"postName":"按摩师","companyName":"泰普提","money":"5000","satrTime":"2021-05-02","userName":"user1"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"userId":1,"postId":null,"companyId":5,"postName":"医生助理五险一金双休","companyName":"牙大夫","money":"6000","satrTime":"2021-05-02","userName":"admin"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"userId":1111114,"postId":null,"companyId":6,"postName":"工程师","companyName":"大连美俊达铝业有限公司","money":"8000","satrTime":"2021-05-03","userName":"app"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"userId":2,"postId":null,"companyId":7,"postName":"前台经理","companyName":"宜客宜家","money":"5000","satrTime":"2021-05-03","userName":"user1"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"userId":1,"postId":null,"companyId":13,"postName":"海德堡机长助手","companyName":"鑫元印刷","money":"4000","satrTime":"2021-05-04","userName":"admin"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"userId":2,"postId":null,"companyId":12,"postName":"校园菜鸟驿站工作人员","companyName":"满溢商贸","money":"3800","satrTime":"2021-05-05","userName":"user1"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"userId":1,"postId":null,"companyId":11,"postName":"寝室长","companyName":"有家舒舍","money":"5000","satrTime":"2021-05-05","userName":"admin"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":14,"userId":2,"postId":null,"companyId":9,"postName":"快递员","companyName":"大连伊帆货运代理有限公司","money":"3500","satrTime":"2021-05-05","userName":"user1"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":15,"userId":2,"postId":null,"companyId":15,"postName":"快递员","companyName":"久恒经济","money":"5000","satrTime":"2021-05-07","userName":"user1"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsDTO> rows;

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

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * userId : 101
         * postId : null
         * companyId : 3
         * postName : 软件开发
         * companyName : 虎鱼科技
         * money : 5000
         * satrTime : 2020-11-04
         * userName : user01
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private int userId;
        private Object postId;
        private int companyId;
        private String postName;
        private String companyName;
        private String money;
        private String satrTime;
        private String userName;

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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getPostId() {
            return postId;
        }

        public void setPostId(Object postId) {
            this.postId = postId;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getSatrTime() {
            return satrTime;
        }

        public void setSatrTime(String satrTime) {
            this.satrTime = satrTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public static class ParamsDTO {
        }
    }
}
