package com.gzeic.smartcity01.bean;

import java.util.List;

public class WzCxlbBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"applyDate":"2021-05-08","auditDate":"2021-05-08","auditRemark":"同意","auditor":2,"auditorName":"admin","id":2,"illegalId":2,"no":"2021050818133387542","photo":"http://118.190.154.52:7777/profile/upload/i mage/2021/05/08/53b7cfcf-d41f-4257-95d2-5abc837deccd.jpeg","reason":"避让其他车辆","status":"通过"}]
     * total : 1
     */

    private int code;
    private String msg;
    private String total;
    private List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * applyDate : 2021-05-08
         * auditDate : 2021-05-08
         * auditRemark : 同意
         * auditor : 2
         * auditorName : admin
         * id : 2
         * illegalId : 2
         * no : 2021050818133387542
         * photo : http://118.190.154.52:7777/profile/upload/i mage/2021/05/08/53b7cfcf-d41f-4257-95d2-5abc837deccd.jpeg
         * reason : 避让其他车辆
         * status : 通过
         */

        private String applyDate;
        private String auditDate;
        private String auditRemark;
        private int auditor;
        private String auditorName;
        private int id;
        private int illegalId;
        private String no;
        private String photo;
        private String reason;
        private String status;

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getAuditDate() {
            return auditDate;
        }

        public void setAuditDate(String auditDate) {
            this.auditDate = auditDate;
        }

        public String getAuditRemark() {
            return auditRemark;
        }

        public void setAuditRemark(String auditRemark) {
            this.auditRemark = auditRemark;
        }

        public int getAuditor() {
            return auditor;
        }

        public void setAuditor(int auditor) {
            this.auditor = auditor;
        }

        public String getAuditorName() {
            return auditorName;
        }

        public void setAuditorName(String auditorName) {
            this.auditorName = auditorName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIllegalId() {
            return illegalId;
        }

        public void setIllegalId(int illegalId) {
            this.illegalId = illegalId;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
