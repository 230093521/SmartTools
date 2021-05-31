package com.gzeic.smartcity01.bean;

import java.util.List;

public class ZcLcxxBean {

    /**
     * total : 11
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"plateNo":"辽B12345","travelDate":"2021-02-01","travelDistance":100,"gasFilling":50,"amount":600,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"plateNo":"辽B12345","travelDate":"2021-05-01","travelDistance":200,"gasFilling":100,"amount":1200,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"plateNo":"辽B12345","travelDate":"2021-05-02","travelDistance":400,"gasFilling":200,"amount":2400,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"plateNo":"辽B12345","travelDate":"2021-04-02","travelDistance":50,"gasFilling":25,"amount":300,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"plateNo":"辽B12345","travelDate":"2021-04-03","travelDistance":800,"gasFilling":400,"amount":4800,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"plateNo":"辽B12345","travelDate":"2021-04-04","travelDistance":100,"gasFilling":50,"amount":300,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"plateNo":"辽B12345","travelDate":"2021-04-05","travelDistance":100,"gasFilling":50,"amount":300,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"plateNo":"辽B12345","travelDate":"2021-04-06","travelDistance":150,"gasFilling":75,"amount":450,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"plateNo":"辽B12345","travelDate":"2021-04-07","travelDistance":150,"gasFilling":75,"amount":450,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"plateNo":"辽B12345","travelDate":"2021-04-08","travelDistance":100,"gasFilling":50,"amount":300,"userId":2,"userName":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"plateNo":"辽B12345","travelDate":"2021-04-09","travelDistance":150,"gasFilling":75,"amount":300,"userId":2,"userName":null}]
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
         * id : 2
         * plateNo : 辽B12345
         * travelDate : 2021-02-01
         * travelDistance : 100
         * gasFilling : 50
         * amount : 600
         * userId : 2
         * userName : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String plateNo;
        private String travelDate;
        private int travelDistance;
        private int gasFilling;
        private int amount;
        private int userId;
        private Object userName;

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

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getTravelDate() {
            return travelDate;
        }

        public void setTravelDate(String travelDate) {
            this.travelDate = travelDate;
        }

        public int getTravelDistance() {
            return travelDistance;
        }

        public void setTravelDistance(int travelDistance) {
            this.travelDistance = travelDistance;
        }

        public int getGasFilling() {
            return gasFilling;
        }

        public void setGasFilling(int gasFilling) {
            this.gasFilling = gasFilling;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public static class ParamsDTO {
        }
    }
}
