package com.gzeic.smartcity01.bean;

import java.util.List;

public class JfJlBean {

    /**
     * code :
     * msg :
     * rows : [{"id":2,"userId":1,"event":"绑定车辆","score":"10","changeDate":"2021-04-15 15:17:36","type":"获取"}]
     * total : 1
     */

    private int code;
    private String msg;
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
         * id : 2
         * userId : 1
         * event : 绑定车辆
         * score : 10
         * changeDate : 2021-04-15 15:17:36
         * type : 获取
         */

        private int id;
        private int userId;
        private String event;
        private String score;
        private String changeDate;
        private String type;

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

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getChangeDate() {
            return changeDate;
        }

        public void setChangeDate(String changeDate) {
            this.changeDate = changeDate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
