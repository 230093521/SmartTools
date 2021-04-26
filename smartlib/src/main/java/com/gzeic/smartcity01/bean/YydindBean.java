package com.gzeic.smartcity01.bean;

import java.util.List;

public class YydindBean {

    /**
     * total : 2
     * rows : [{"id":3,"userId":2,"plateNum":"京AL3333","mainNum":"JD12121991DSX33333","carType":"轿车","mileage":"10000000","carPhone":"139010333333","placeName":"丰台区潘北昊盛汽车检测有限公司","remarks":"周一至周五:上午8:00\u201412:00;下午13:00\u201417:00","address":"北京市丰台区新发地潘家庙518号院","phone":"010-87504440","aptTime":"2020-11-11","success":"1"},{"id":4,"userId":2,"plateNum":"京AL2222","mainNum":"JD12121991DSX22222","carType":"轿车","mileage":"10000000","carPhone":"139010222222","placeName":"丰台区潘北昊盛汽车检测有限公司","remarks":"周一至周五:上午8:00\u201412:00;下午13:00\u201417:00","address":"北京市丰台区新发地潘家庙518号院","phone":"010-87504440","aptTime":"2020-11-04","success":"1"}]
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
         * id : 3
         * userId : 2
         * plateNum : 京AL3333
         * mainNum : JD12121991DSX33333
         * carType : 轿车
         * mileage : 10000000
         * carPhone : 139010333333
         * placeName : 丰台区潘北昊盛汽车检测有限公司
         * remarks : 周一至周五:上午8:00—12:00;下午13:00—17:00
         * address : 北京市丰台区新发地潘家庙518号院
         * phone : 010-87504440
         * aptTime : 2020-11-11
         * success : 1
         */

        private int id;
        private int userId;
        private String plateNum;
        private String mainNum;
        private String carType;
        private String mileage;
        private String carPhone;
        private String placeName;
        private String remarks;
        private String address;
        private String phone;
        private String aptTime;
        private String success;

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

        public String getPlateNum() {
            return plateNum;
        }

        public void setPlateNum(String plateNum) {
            this.plateNum = plateNum;
        }

        public String getMainNum() {
            return mainNum;
        }

        public void setMainNum(String mainNum) {
            this.mainNum = mainNum;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getCarPhone() {
            return carPhone;
        }

        public void setCarPhone(String carPhone) {
            this.carPhone = carPhone;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAptTime() {
            return aptTime;
        }

        public void setAptTime(String aptTime) {
            this.aptTime = aptTime;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }
    }
}
