package com.gzeic.smartcity01.bean;

import java.util.List;

public class WzcllbBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":2,"userId":2,"plateNo":"辽 B12345","engineNo":"1212XS123113","type":"小型汽车"}]
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
         * id : 2
         * userId : 2
         * plateNo : 辽 B12345
         * engineNo : 1212XS123113
         * type : 小型汽车
         */

        private int id;
        private int userId;
        private String plateNo;
        private String engineNo;
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

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
