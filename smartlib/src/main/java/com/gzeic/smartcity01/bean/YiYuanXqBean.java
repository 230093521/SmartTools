package com.gzeic.smartcity01.bean;

public class YiYuanXqBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"hospitalName":"大连市儿童医院","brief":"大连市儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务。2013年2月6日，签约成为大连医科大学附属大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿科、儿外科住院医师规范化培训基地。医院设有36个学科，大连市快速提升医疗软实力建设项目小儿心脏病诊疗基地1个，大连市一级医学重点学科6个。","level":"3","imgUrl":"/profile/2020/10/27/h1.jpg"}
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
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * hospitalName : 大连市儿童医院
         * brief : 大连市儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务。2013年2月6日，签约成为大连医科大学附属大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿科、儿外科住院医师规范化培训基地。医院设有36个学科，大连市快速提升医疗软实力建设项目小儿心脏病诊疗基地1个，大连市一级医学重点学科6个。
         * level : 3
         * imgUrl : /profile/2020/10/27/h1.jpg
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String hospitalName;
        private String brief;
        private String level;
        private String imgUrl;

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

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public static class ParamsBean {
        }
    }
}