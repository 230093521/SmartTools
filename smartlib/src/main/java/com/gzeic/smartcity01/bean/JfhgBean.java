package com.gzeic.smartcity01.bean;

import java.util.List;

public class JfhgBean {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":"1","createTime":"2021-04-12 09:34:16","updateBy":"1","updateTime":"2021-05-08 12:54:04","remark":null,"params":{},"id":1,"name":"500元加油卡","price":500,"score":300,"cover":"","total":500,"saleQuantity":100,"description":"<p><br><\/p>"},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 12:55:27","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"name":"100元加油卡","price":100,"score":50,"cover":null,"total":1000,"saleQuantity":500,"description":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 12:55:59","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"name":"200元加油卡","price":200,"score":150,"cover":null,"total":800,"saleQuantity":200,"description":null}]
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
         * createBy : 1
         * createTime : 2021-04-12 09:34:16
         * updateBy : 1
         * updateTime : 2021-05-08 12:54:04
         * remark : null
         * params : {}
         * id : 1
         * name : 500元加油卡
         * price : 500
         * score : 300
         * cover :
         * total : 500
         * saleQuantity : 100
         * description : <p><br></p>
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String name;
        private int price;
        private int score;
        private String cover;
        private int total;
        private int saleQuantity;
        private String description;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static class ParamsDTO {
        }
    }
}
