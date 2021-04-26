package com.gzeic.smartcity01.bean;

public class ZgzQzxxBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"mostEducation":"博士","education":"本科","address":"北京","experience":"1年","individualResume":"性格有点内向、乐观上进、有爱心并善于施教并行;对待工作认真负责，善于沟通、协调有较强的组织能力与团队精神;上进心强、勤于学习能不断进步自身的能力与综合素质。在未来的工作中，我将以充沛的精力，刻苦钻研的精神来努力工作，稳定地进步自己的工作能力，与公司同步发展。","money":"7000","positionId":"2","userId":1,"files":"/profile/2020/11/04/aa1cf589-43d4-4a5c-aa40-8b598b7a6368.docx","file":null}
     */

    private String msg;
    private int code;
    private DataDTO data;

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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * mostEducation : 博士
         * education : 本科
         * address : 北京
         * experience : 1年
         * individualResume : 性格有点内向、乐观上进、有爱心并善于施教并行;对待工作认真负责，善于沟通、协调有较强的组织能力与团队精神;上进心强、勤于学习能不断进步自身的能力与综合素质。在未来的工作中，我将以充沛的精力，刻苦钻研的精神来努力工作，稳定地进步自己的工作能力，与公司同步发展。
         * money : 7000
         * positionId : 2
         * userId : 1
         * files : /profile/2020/11/04/aa1cf589-43d4-4a5c-aa40-8b598b7a6368.docx
         * file : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String mostEducation;
        private String education;
        private String address;
        private String experience;
        private String individualResume;
        private String money;
        private String positionId;
        private int userId;
        private String files;
        private Object file;

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

        public String getMostEducation() {
            return mostEducation;
        }

        public void setMostEducation(String mostEducation) {
            this.mostEducation = mostEducation;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getIndividualResume() {
            return individualResume;
        }

        public void setIndividualResume(String individualResume) {
            this.individualResume = individualResume;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }

        public Object getFile() {
            return file;
        }

        public void setFile(Object file) {
            this.file = file;
        }

        public static class ParamsDTO {
        }
    }
}
