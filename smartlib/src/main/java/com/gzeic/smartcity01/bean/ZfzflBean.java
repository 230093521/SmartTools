package com.gzeic.smartcity01.bean;

import java.util.List;

public class ZfzflBean {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"sourceName":"泉水B6区泉水小学北站附近多层一室一厅得房率高\n","address":"泉水B6区泉水小学北站附近多层一室一厅得房率高","areaSize":52,"price":"16262/㎡","tel":"18546474545","houseType":"1","pic":"/profile/cshi.jpg","desc":"房主现在比较着急 房子如果真看好的话 价格可议 手续这边齐全 房子没有抵押 产权证在手 随时配合过户 房主现在已经搬走了 房子现在是空置状态"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"sourceName":"西安路08年框架电梯房 70年LOFT 一室一厅封闭小区","address":"西安路08年框架电梯房 70年LOFT 一室一厅封闭小区","areaSize":88,"price":"28300/㎡","tel":"18546474547","houseType":"1","pic":"/profile/xweb.jpg","desc":"第五郡经典一室小房好楼层自住保持非常好小区一共36栋楼,8栋小高,28栋洋,小区绿树"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"sourceName":"大华实验双学 区长江路 好楼层 单价超低 看房方便","address":"数码路118号","areaSize":100,"price":"21000/㎡","tel":"18546474549","houseType":"1","pic":"/profile/xwen1.jpg","desc":"我去看过这个房子，位置好，不挡光，中间楼层，精装修赠送全部家具家电，拎包即住，看房方便，照片视频等都是实景拍摄，欢迎随时咨询。"}]
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
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * sourceName : 泉水B6区泉水小学北站附近多层一室一厅得房率高
         * address : 泉水B6区泉水小学北站附近多层一室一厅得房率高
         * areaSize : 52
         * price : 16262/㎡
         * tel : 18546474545
         * houseType : 1
         * pic : /profile/cshi.jpg
         * desc : 房主现在比较着急 房子如果真看好的话 价格可议 手续这边齐全 房子没有抵押 产权证在手 随时配合过户 房主现在已经搬走了 房子现在是空置状态
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String sourceName;
        private String address;
        private int areaSize;
        private String price;
        private String tel;
        private String houseType;
        private String pic;
        private String desc;

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

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAreaSize() {
            return areaSize;
        }

        public void setAreaSize(int areaSize) {
            this.areaSize = areaSize;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static class ParamsDTO {
        }
    }
}
