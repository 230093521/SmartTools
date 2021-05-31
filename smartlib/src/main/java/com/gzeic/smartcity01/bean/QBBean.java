package com.gzeic.smartcity01.bean;

import java.util.List;

public class QBBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":4,"userId":2,"money":200,"rechargeDate":"2021-05-08 12:48:42","payType":"支付宝"}]
     * total : 1
     */

    private int code;
    private String msg;
    private String total;
    private List<RowsDTO> rows;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        /**
         * id : 4
         * userId : 2
         * money : 200
         * rechargeDate : 2021-05-08 12:48:42
         * payType : 支付宝
         */

        private int id;
        private int userId;
        private int money;
        private String rechargeDate;
        private String payType;

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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getRechargeDate() {
            return rechargeDate;
        }

        public void setRechargeDate(String rechargeDate) {
            this.rechargeDate = rechargeDate;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }
}
