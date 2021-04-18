package com.gzeic.smartcity01.bean;

import java.util.List;

public class YuYueBean {
    /**
     * total : 11
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"orderNo":"1603537663212","patientName":"张三","divisionId":null,"typesId":"1","moeny":"5","startime":"2020-10-24 18:38","reservedStatus":null,"categoryName":"胸外科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"orderNo":"1603537611111","patientName":"陈萍萍","divisionId":null,"typesId":"3","moeny":"532","startime":"2020-10-24 18:38","reservedStatus":null,"categoryName":"肛肠科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":15,"orderNo":"1602323232331","patientName":"张","divisionId":null,"typesId":"1","moeny":"5","startime":"2020-10-24 18:38","reservedStatus":null,"categoryName":"胸外科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":16,"orderNo":"1656674584761","patientName":"李四","divisionId":null,"typesId":"2","moeny":"5","startime":"2020-10-27 12:01","reservedStatus":null,"categoryName":"儿科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"orderNo":"1656342556761","patientName":"李四","divisionId":null,"typesId":"2","moeny":"37","startime":"2020-10-27 12:01","reservedStatus":null,"categoryName":"消化内科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":18,"orderNo":"1603801496731","patientName":"李四","divisionId":null,"typesId":"1","moeny":"6","startime":"2020-10-27 12:01","reservedStatus":null,"categoryName":"神经内科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":24,"orderNo":"1608280995382","patientName":"李小溪","divisionId":null,"typesId":"1","moeny":"6","startime":"2020-12-18 12:01","reservedStatus":null,"categoryName":"神经内科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":25,"orderNo":"1608378262104","patientName":"李小溪","divisionId":null,"typesId":"1","moeny":"6","startime":"2020-12-19 12:01","reservedStatus":null,"categoryName":"神经内科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":26,"orderNo":"1608379873501","patientName":"李小溪","divisionId":null,"typesId":"2","moeny":"5","startime":"2020-12-19 12:01","reservedStatus":null,"categoryName":"骨科","userId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":27,"orderNo":"1608381735194","patientName":"李小溪","divisionId":null,"typesId":"1","moeny":"6","startime":"2020-12-18 12:01","reservedStatus":null,"categoryName":"神经内科","userId":null}]
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
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * orderNo : 1603537663212
         * patientName : 张三
         * divisionId : null
         * typesId : 1
         * moeny : 5
         * startime : 2020-10-24 18:38
         * reservedStatus : null
         * categoryName : 胸外科
         * userId : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String orderNo;
        private String patientName;
        private Object divisionId;
        private String typesId;
        private String moeny;
        private String startime;
        private Object reservedStatus;
        private String categoryName;
        private Object userId;

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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public Object getDivisionId() {
            return divisionId;
        }

        public void setDivisionId(Object divisionId) {
            this.divisionId = divisionId;
        }

        public String getTypesId() {
            return typesId;
        }

        public void setTypesId(String typesId) {
            this.typesId = typesId;
        }

        public String getMoeny() {
            return moeny;
        }

        public void setMoeny(String moeny) {
            this.moeny = moeny;
        }

        public String getStartime() {
            return startime;
        }

        public void setStartime(String startime) {
            this.startime = startime;
        }

        public Object getReservedStatus() {
            return reservedStatus;
        }

        public void setReservedStatus(Object reservedStatus) {
            this.reservedStatus = reservedStatus;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public static class ParamsBean {
        }
    }
}
