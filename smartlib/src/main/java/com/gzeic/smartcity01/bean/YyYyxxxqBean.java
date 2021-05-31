package com.gzeic.smartcity01.bean;

public class YyYyxxxqBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:35:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"name":"奥斯卡国际影城","cover":"http://118.190.154.52:7777/profile/upload/image/2021/05/08/46ab771d-ca19-4758-8d87-59615ecda2cd.jpg","province":"辽宁省","city":"大连市","area":"甘井子区","address":"高新园区锦辉购物广场5楼","score":93,"tags":null,"brand":null,"distance":"2500米","status":"Y","movieId":null,"timesId":null}
     */

    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : 1
         * createTime : 2021-05-08 22:35:12
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 4
         * name : 奥斯卡国际影城
         * cover : http://118.190.154.52:7777/profile/upload/image/2021/05/08/46ab771d-ca19-4758-8d87-59615ecda2cd.jpg
         * province : 辽宁省
         * city : 大连市
         * area : 甘井子区
         * address : 高新园区锦辉购物广场5楼
         * score : 93
         * tags : null
         * brand : null
         * distance : 2500米
         * status : Y
         * movieId : null
         * timesId : null
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private String cover;
        private String province;
        private String city;
        private String area;
        private String address;
        private int score;
        private Object tags;
        private Object brand;
        private String distance;
        private String status;
        private Object movieId;
        private Object timesId;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Object getBrand() {
            return brand;
        }

        public void setBrand(Object brand) {
            this.brand = brand;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getMovieId() {
            return movieId;
        }

        public void setMovieId(Object movieId) {
            this.movieId = movieId;
        }

        public Object getTimesId() {
            return timesId;
        }

        public void setTimesId(Object timesId) {
            this.timesId = timesId;
        }

        public static class ParamsBean {
        }
    }
}
