package com.gzeic.smartcity01.bean;

import java.util.List;
import java.util.Objects;

public class JfSjjlBean {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":19,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":50,"paymentAmount":50,"paymentType":"电子支付","rechargeTime":"2021-05-18 12:13:45","userId":1111122},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":18,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":100,"paymentAmount":100,"paymentType":"电子支付","rechargeTime":"2021-05-18 08:56:58","userId":1111122},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":200,"paymentAmount":200,"paymentType":"电子支付","rechargeTime":"2021-05-18 08:54:31","userId":1111122},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":16,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":50,"paymentAmount":49,"paymentType":"电子支付","rechargeTime":"2021-05-18 08:54:10","userId":1111122}]
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
         * id : 19
         * title : 话费充值
         * phonenumber : 13800000000
         * rechargeAmount : 50
         * paymentAmount : 50
         * paymentType : 电子支付
         * rechargeTime : 2021-05-18 12:13:45
         * userId : 1111122
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String title;
        private String phonenumber;
        private int rechargeAmount;
        private int paymentAmount;
        private String paymentType;
        private String rechargeTime;
        private int userId;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public int getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(int rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public int getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(int paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getRechargeTime() {
            return rechargeTime;
        }

        public void setRechargeTime(String rechargeTime) {
            this.rechargeTime = rechargeTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class ParamsDTO {
        }

        public boolean equals(Object o) {
            JfSjjlBean.RowsDTO o1 = (JfSjjlBean.RowsDTO) o;
            return phonenumber.equals(o1.phonenumber);
        }

        public int hashCode() {
            String in = phonenumber;
            return in.hashCode();
        }
    }
}
