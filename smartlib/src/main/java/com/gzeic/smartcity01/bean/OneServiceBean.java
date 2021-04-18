package com.gzeic.smartcity01.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneServiceBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":"admin","createTime":"2020-10-12 13:09:01","updateBy":null,"updateTime":null,"remark":null,"params":{},"dictCode":29,"dictSort":0,"dictLabel":"生活服务","dictValue":"1","dictType":"sys_service","cssClass":null,"listClass":null,"isDefault":"N","status":"0","default":false},{"searchValue":null,"createBy":"admin","createTime":"2020-10-12 13:09:08","updateBy":null,"updateTime":null,"remark":null,"params":{},"dictCode":30,"dictSort":0,"dictLabel":"便民服务","dictValue":"2","dictType":"sys_service","cssClass":null,"listClass":null,"isDefault":"N","status":"0","default":false},{"searchValue":null,"createBy":"admin","createTime":"2020-10-12 18:16:47","updateBy":null,"updateTime":null,"remark":null,"params":{},"dictCode":44,"dictSort":3,"dictLabel":"车主服务","dictValue":"3","dictType":"sys_service","cssClass":null,"listClass":null,"isDefault":"N","status":"0","default":false}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2020-10-12 13:09:01
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * dictCode : 29
         * dictSort : 0
         * dictLabel : 生活服务
         * dictValue : 1
         * dictType : sys_service
         * cssClass : null
         * listClass : null
         * isDefault : N
         * status : 0
         * default : false
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int dictCode;
        private int dictSort;
        private String dictLabel;
        private String dictValue;
        private String dictType;
        private Object cssClass;
        private Object listClass;
        private String isDefault;
        private String status;
        @SerializedName("default")
        private boolean defaultX;

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

        public int getDictCode() {
            return dictCode;
        }

        public void setDictCode(int dictCode) {
            this.dictCode = dictCode;
        }

        public int getDictSort() {
            return dictSort;
        }

        public void setDictSort(int dictSort) {
            this.dictSort = dictSort;
        }

        public String getDictLabel() {
            return dictLabel;
        }

        public void setDictLabel(String dictLabel) {
            this.dictLabel = dictLabel;
        }

        public String getDictValue() {
            return dictValue;
        }

        public void setDictValue(String dictValue) {
            this.dictValue = dictValue;
        }

        public String getDictType() {
            return dictType;
        }

        public void setDictType(String dictType) {
            this.dictType = dictType;
        }

        public Object getCssClass() {
            return cssClass;
        }

        public void setCssClass(Object cssClass) {
            this.cssClass = cssClass;
        }

        public Object getListClass() {
            return listClass;
        }

        public void setListClass(Object listClass) {
            this.listClass = listClass;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isDefaultX() {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }

        public static class ParamsBean {
        }
    }
}
