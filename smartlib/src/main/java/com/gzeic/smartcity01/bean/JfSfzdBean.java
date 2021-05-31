package com.gzeic.smartcity01.bean;

public class JfSfzdBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":4,"billNo":"202104240820935","amount":100,"chargeUnit":"大连市水务集团有限公司","paymentNo":"15674939","categoryId":2,"payStatus":"0","address":null}
     */

    private String msg;
    private int code;
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * id : 4
         * billNo : 202104240820935
         * amount : 100
         * chargeUnit : 大连市水务集团有限公司
         * paymentNo : 15674939
         * categoryId : 2
         * payStatus : 0
         * address : null
         */

        private int id;
        private String billNo;
        private int amount;
        private String chargeUnit;
        private String paymentNo;
        private int categoryId;
        private String payStatus;
        private Object address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getChargeUnit() {
            return chargeUnit;
        }

        public void setChargeUnit(String chargeUnit) {
            this.chargeUnit = chargeUnit;
        }

        public String getPaymentNo() {
            return paymentNo;
        }

        public void setPaymentNo(String paymentNo) {
            this.paymentNo = paymentNo;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }
    }
}
