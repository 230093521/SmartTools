package com.gzeic.smartcity01.bean;

import java.util.List;

public class ZwSsBean {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"companyId":"3","contacts":"张先生","name":"软件开发","obligation":"负责软件的设计开发测试以及上线","address":"大连市万达广场","salary":"5000","professionId":"1","company":"虎鱼科技","need":"工作经验1-2年"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"companyId":"5","contacts":"王女士","name":"医生助理五险一金双休","obligation":"辅助医生参与患者治疗和管理","address":"大连市沙河口区中山路","salary":"5000","professionId":"5","company":"牙大夫","need":"工作经验1-2年"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"companyId":"4","contacts":"李先生","name":"平面设计师（包吃住）","obligation":"能够高效的完成平面设计，排版设计，图文海报设计，宣传单名片设计，画册设计，要求具有良好的审美能力，一次成稿率较高，视觉传达专业毕业优先。公司下设设计部，工作职责是：为客户提供设计服务。","address":"大连 - 高新园区 - 黄浦路","salary":"6000","professionId":"2","company":"恒但科技公司","need":"工作经验1-2年"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"companyId":"2","contacts":"张先生","name":"五险一金+6k英语老师","obligation":"外语教师:一、从事3到12岁少儿英语教学，以游戏活动方式进行英文式授课，授课英语课程对话单词歌曲等，\r\n二、负责将学生的学习情况与家长保持积极的沟通，并给予耐心的指导，三、配合学校定期举办的各项活动，","address":"大连 - 中山 - 迎宾路","salary":"8000","professionId":"3","company":"左培互联网有限公司","need":"工作经验1-2年"}]
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
         * companyId : 3
         * contacts : 张先生
         * name : 软件开发
         * obligation : 负责软件的设计开发测试以及上线
         * address : 大连市万达广场
         * salary : 5000
         * professionId : 1
         * company : 虎鱼科技
         * need : 工作经验1-2年
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String companyId;
        private String contacts;
        private String name;
        private String obligation;
        private String address;
        private String salary;
        private String professionId;
        private String company;
        private String need;

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

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObligation() {
            return obligation;
        }

        public void setObligation(String obligation) {
            this.obligation = obligation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getProfessionId() {
            return professionId;
        }

        public void setProfessionId(String professionId) {
            this.professionId = professionId;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getNeed() {
            return need;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public static class ParamsDTO {
        }
    }
}
