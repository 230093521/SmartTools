package com.xsonline.smartlib.bean;

import java.util.List;

public class YiYuanBean {

    /**
     * total : 9
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"hospitalName":"大连市儿童医院","brief":"大连市儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务。2013年2月6日，签约成为大连医科大学附属大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿科、儿外科住院医师规范化培训基地。医院设有36个学科，大连市快速提升医疗软实力建设项目小儿心脏病诊疗基地1个，大连市一级医学重点学科6个。","level":"3","imgUrl":"/profile/2020/10/27/h1.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"hospitalName":"大连市中山医院","brief":"大连大学附属中山医院(沈阳铁路局大连医院)创建于1907年,是一所集医疗、教学、科研为一体的综合性大学附属医院。占地面积九万余平方米,建筑面积七万余平方米","level":"5","imgUrl":"/profile/2020/10/27/h2.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"hospitalName":"大连市大一医院","brief":"大连市大一医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"5","imgUrl":"/profile/2020/10/27/h3.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"hospitalName":"大连市人民医院","brief":"大连市人民医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"5","imgUrl":"/profile/2020/10/27/h4.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"hospitalName":"大连市医大二院","brief":"大连市医大二院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"5","imgUrl":"/profile/2020/10/27/h5.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"hospitalName":"大连市妇女儿童医院","brief":"大连市妇女儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"4","imgUrl":"/profile/2020/10/27/h6.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"hospitalName":"大连市眼科医院","brief":"大连市眼科医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"3","imgUrl":"/profile/2020/10/27/h7.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"hospitalName":"大连市肿瘤医院","brief":"大连市肿瘤医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"5","imgUrl":"/profile/2020/10/27/h8.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"hospitalName":"大连市专科医院","brief":"大连市专科创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务","level":"4","imgUrl":"/profile/2020/10/27/h9.jpg"}]
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
