package com.gzeic.smartcity01.bean;

import java.util.List;

public class JFphBean {

    /**
     * total : 8
     * rows : [{"searchValue":null,"createBy":"test01","createTime":"2021-05-16 12:20:21","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111122,"deptId":null,"userName":"test01","nickName":"弄玉","email":"","phonenumber":"18623323323","sex":"1","avatar":"/profile/upload/2021/05/16/5e9134ed-e1cd-4e2e-8dc2-290aada53bc4.png","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":"123456789000000001","balance":269,"score":1000,"dept":null,"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"admin","createTime":"2021-05-18 14:48:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111129,"deptId":103,"userName":"stu01","nickName":"学生01","email":"","phonenumber":"","sex":"0","avatar":"/profile/avatar/2021/05/19/d4e372df-c39d-4412-b833-219a581682fb.jpeg","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":null,"balance":1000000,"score":1000,"dept":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":103,"parentId":null,"ancestors":null,"deptName":"学习部","orderNum":null,"leader":"教师","phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"children":[]},"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"11111123","createTime":"2021-05-21 15:41:54","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111130,"deptId":null,"userName":"11111123","nickName":"122222222","email":"1111111111111@qq.c11","phonenumber":"11111111112","sex":"0","avatar":"","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":"111111111111111111","balance":1000,"score":1000,"dept":null,"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"admin123","createTime":"2021-05-21 15:43:44","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111131,"deptId":null,"userName":"admin123","nickName":"admin123","email":"111111@qq.com","phonenumber":"11111111122","sex":"0","avatar":"","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":"222222222222222222","balance":1000,"score":1000,"dept":null,"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"123123","createTime":"2021-05-21 15:44:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111132,"deptId":103,"userName":"123123","nickName":"123123","email":"1111111111@qq.com","phonenumber":"18888888888","sex":"0","avatar":"","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":"555555555555555555","balance":1000,"score":1000,"dept":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":103,"parentId":null,"ancestors":null,"deptName":"学习部","orderNum":null,"leader":"教师","phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"children":[]},"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"123456","createTime":"2021-05-21 15:48:27","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111133,"deptId":null,"userName":"123456","nickName":"123456","email":"123456@qq.c","phonenumber":"11111111145","sex":"0","avatar":"","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":"115555555555555555","balance":1000,"score":1000,"dept":null,"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"1112312","createTime":"2021-05-21 16:56:31","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":1111134,"deptId":null,"userName":"1112312","nickName":"1112312","email":"ddccc@ss.com","phonenumber":"11111111188","sex":"1","avatar":"","salt":null,"status":"0","delFlag":"0","loginIp":"","loginDate":null,"idCard":"465461616161166161","balance":1000,"score":1000,"dept":null,"roles":[],"roleIds":null,"postIds":null,"admin":false},{"searchValue":null,"createBy":"admin","createTime":"2021-03-17 16:25:49","updateBy":null,"updateTime":null,"remark":"管理员","params":{},"userId":1,"deptId":103,"userName":"admin","nickName":"超级管理员","email":"","phonenumber":"13888888888","sex":"1","avatar":"/profile/avatar/2021/05/19/4322aea8-b521-47d7-979b-fbf245b3a5fd.jpeg","salt":null,"status":"0","delFlag":"0","loginIp":"127.0.0.1","loginDate":"2021-03-17T16:25:49.000+0800","idCard":null,"balance":690,"score":820,"dept":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":103,"parentId":null,"ancestors":null,"deptName":"学习部","orderNum":null,"leader":"教师","phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"children":[]},"roles":[],"roleIds":null,"postIds":null,"admin":true}]
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
         * createBy : test01
         * createTime : 2021-05-16 12:20:21
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * userId : 1111122
         * deptId : null
         * userName : test01
         * nickName : 弄玉
         * email :
         * phonenumber : 18623323323
         * sex : 1
         * avatar : /profile/upload/2021/05/16/5e9134ed-e1cd-4e2e-8dc2-290aada53bc4.png
         * salt : null
         * status : 0
         * delFlag : 0
         * loginIp :
         * loginDate : null
         * idCard : 123456789000000001
         * balance : 269
         * score : 1000
         * dept : null
         * roles : []
         * roleIds : null
         * postIds : null
         * admin : false
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int userId;
        private Object deptId;
        private String userName;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private Object salt;
        private String status;
        private String delFlag;
        private String loginIp;
        private Object loginDate;
        private String idCard;
        private int balance;
        private int score;
        private Object dept;
        private Object roleIds;
        private Object postIds;
        private boolean admin;
        private List<?> roles;

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

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId = deptId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getSalt() {
            return salt;
        }

        public void setSalt(Object salt) {
            this.salt = salt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public Object getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(Object loginDate) {
            this.loginDate = loginDate;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Object getDept() {
            return dept;
        }

        public void setDept(Object dept) {
            this.dept = dept;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getPostIds() {
            return postIds;
        }

        public void setPostIds(Object postIds) {
            this.postIds = postIds;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public static class ParamsDTO {
        }
    }
}
