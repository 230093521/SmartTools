package com.gzeic.smartcity01.bean;

import java.util.List;

public class TinCheJiLuBean {
    /**
     * total : 24
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"entryTime":"2020-10-27 09:57","outTime":"2020-10-27 10:57","plateNumber":"京1234","monetary":"5","parkName":"国际大厦停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"entryTime":"2020-10-26 09:57","outTime":"2020-10-26 10:57","plateNumber":"京1234","monetary":"6","parkName":"金辉大厦停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"entryTime":"2020-10-12 16:06","outTime":"2020-10-12 16:06","plateNumber":"京12399","monetary":"1","parkName":"广达停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"entryTime":"2020-10-12 17:56","outTime":"2020-10-12 17:56","plateNumber":"京1234","monetary":"2","parkName":"天津市邦仓储威物流停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"entryTime":"2020-10-13 16:11","outTime":"2020-10-13 16:11","plateNumber":"京123353","monetary":"3","parkName":"蓬莱义利水泥磨粉有限公司停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"entryTime":"2020-10-26 16:35","outTime":"2020-10-26 16:35","plateNumber":"京1234343","monetary":"4","parkName":"武鸣县海成停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"entryTime":"2020-10-27 15:20","outTime":"2020-10-27 15:20","plateNumber":"京123","monetary":"5","parkName":"遵化如意停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"entryTime":"2020-10-27 15:39","outTime":"2020-10-27 15:39","plateNumber":"京1234","monetary":"6","parkName":"红运停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"entryTime":"2020-10-27 15:39","outTime":"2020-10-27 15:39","plateNumber":"京123353","monetary":"7","parkName":"晋州市高速口停车场"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"entryTime":"2020-10-27 15:39","outTime":"2020-10-27 15:39","plateNumber":"京1234343","monetary":"8","parkName":"公主岭市光大物流停车场"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * entryTime : 2020-10-27 09:57
         * outTime : 2020-10-27 10:57
         * plateNumber : 京1234
         * monetary : 5
         * parkName : 国际大厦停车场
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String entryTime;
        private String outTime;
        private String plateNumber;
        private String monetary;
        private String parkName;

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

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public String getMonetary() {
            return monetary;
        }

        public void setMonetary(String monetary) {
            this.monetary = monetary;
        }

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public static class ParamsBean {
        }
    }
}
