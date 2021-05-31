package com.gzeic.smartcity01.bean;

import java.util.List;

public class WzjszxxBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":1,"userId":2,"licenseNo":"210211199909090014","licenseLevel":"C1","idCard":"210211199909090014","score":6,"applyDate":"2020-02-12","verifyDate":"10 年","timeout":"N","userName":null,"fileNo":"210211199909090014","auditOffice":"大连市沙河口区交警队","contact":"13800000000"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * userId : 2
         * licenseNo : 210211199909090014
         * licenseLevel : C1
         * idCard : 210211199909090014
         * score : 6
         * applyDate : 2020-02-12
         * verifyDate : 10 年
         * timeout : N
         * userName : null
         * fileNo : 210211199909090014
         * auditOffice : 大连市沙河口区交警队
         * contact : 13800000000
         */

        private int id;
        private int userId;
        private String licenseNo;
        private String licenseLevel;
        private String idCard;
        private int score;
        private String applyDate;
        private String verifyDate;
        private String timeout;
        private Object userName;
        private String fileNo;
        private String auditOffice;
        private String contact;

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

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getLicenseLevel() {
            return licenseLevel;
        }

        public void setLicenseLevel(String licenseLevel) {
            this.licenseLevel = licenseLevel;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getVerifyDate() {
            return verifyDate;
        }

        public void setVerifyDate(String verifyDate) {
            this.verifyDate = verifyDate;
        }

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public String getFileNo() {
            return fileNo;
        }

        public void setFileNo(String fileNo) {
            this.fileNo = fileNo;
        }

        public String getAuditOffice() {
            return auditOffice;
        }

        public void setAuditOffice(String auditOffice) {
            this.auditOffice = auditOffice;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }
}
