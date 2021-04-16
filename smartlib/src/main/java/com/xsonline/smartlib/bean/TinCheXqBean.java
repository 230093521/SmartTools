package com.xsonline.smartlib.bean;

import java.util.List;

public class TinCheXqBean
{
    /**
     * total : 24
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"parkName":"国际大厦停车场","vacancy":"30","priceCaps":"30","imgUrl":"/profile/p1.jpg","rates":"5","address":"大连市国际大厦F1楼","distance":"20","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"parkName":"金辉大厦停车场","vacancy":"60","priceCaps":"60","imgUrl":"/profile/p2.jpg","rates":"6","address":"金辉大厦","distance":"15","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"parkName":"广达停车场","vacancy":"30","priceCaps":"30","imgUrl":"/profile/p3.jpg","rates":"5","address":"广达","distance":"20","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"parkName":"天津市邦仓储威物流停车场","vacancy":"60","priceCaps":"60","imgUrl":"/profile/p4.jpg","rates":"6","address":"天津市邦仓储威物流","distance":"15","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"parkName":"蓬莱义利水泥磨粉有限公司停车场","vacancy":"30","priceCaps":"30","imgUrl":"/profile/p5.jpg","rates":"5","address":"蓬莱义利水泥磨粉有限公司","distance":"20","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"parkName":"武鸣县海成停车场","vacancy":"60","priceCaps":"60","imgUrl":"/profile/2020/10/27/h1.jpg","rates":"6","address":"武鸣县海成","distance":"15","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"parkName":"遵化如意停车场","vacancy":"30","priceCaps":"30","imgUrl":"/profile/2020/10/27/h3.jpg","rates":"5","address":"遵化如意","distance":"20","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"parkName":"红运停车场","vacancy":"60","priceCaps":"60","imgUrl":"/profile/2020/10/26/h2.jpg","rates":"6","address":"红运","distance":"15","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"parkName":"晋州市高速口停车场","vacancy":"30","priceCaps":"30","imgUrl":"/profile/p1.jpg","rates":"5","address":"晋州市高速口","distance":"20","allPark":"90"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"parkName":"公主岭市光大物流停车场","vacancy":"60","priceCaps":"60","imgUrl":"/profile/p4.jpg","rates":"6","address":"公主岭市光大物流","distance":"15","allPark":"90"}]
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
         * parkName : 国际大厦停车场
         * vacancy : 30
         * priceCaps : 30
         * imgUrl : /profile/p1.jpg
         * rates : 5
         * address : 大连市国际大厦F1楼
         * distance : 20
         * allPark : 90
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String parkName;
        private String vacancy;
        private String priceCaps;
        private String imgUrl;
        private String rates;
        private String address;
        private String distance;
        private String allPark;

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

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public String getVacancy() {
            return vacancy;
        }

        public void setVacancy(String vacancy) {
            this.vacancy = vacancy;
        }

        public String getPriceCaps() {
            return priceCaps;
        }

        public void setPriceCaps(String priceCaps) {
            this.priceCaps = priceCaps;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRates() {
            return rates;
        }

        public void setRates(String rates) {
            this.rates = rates;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAllPark() {
            return allPark;
        }

        public void setAllPark(String allPark) {
            this.allPark = allPark;
        }

        public static class ParamsBean {
        }
    }
}
