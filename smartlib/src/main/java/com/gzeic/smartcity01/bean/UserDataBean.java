package com.gzeic.smartcity01.bean;

import java.util.List;

public class UserDataBean {

    /**
     * msg : 操作成功
     * code : 200
     * permissions : ["*:*:*"]
     * roles : ["admin"]
     * user : {"searchValue":null,"createBy":"admin","createTime":"2018-03-16 11:33:00","updateBy":null,"updateTime":null,"remark":"管理员","params":{},"userId":1,"deptId":103,"userName":"admin","oldPwd":null,"nickName":"宋哥","email":"songge@163.com","phonenumber":"15888888888","sex":"0","avatar":"/profile/2020/10/26/27e7fd58-0972-4dbf-941c-c16a341558c7.png","salt":null,"status":"0","delFlag":"0","loginIp":"127.0.0.1","loginDate":"2018-03-16T11:33:00.000+0800","dept":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":103,"parentId":102,"ancestors":null,"deptName":"研发部门","orderNum":"1","leader":"张三","phone":null,"email":null,"status":"0","delFlag":null,"parentName":null,"children":[]},"roles":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"roleId":1,"roleName":"超级管理员","roleKey":"admin","roleSort":"1","dataScope":"1","status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null,"admin":true}],"roleIds":null,"postIds":null,"idCard":"211224199506053265","file":null,"admin":true}
     */

    private String msg;
    private int code;
    private UserDTO user;
    private List<String> permissions;
    private List<String> roles;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static class UserDTO {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2018-03-16 11:33:00
         * updateBy : null
         * updateTime : null
         * remark : 管理员
         * params : {}
         * userId : 1
         * deptId : 103
         * userName : admin
         * oldPwd : null
         * nickName : 宋哥
         * email : songge@163.com
         * phonenumber : 15888888888
         * sex : 0
         * avatar : /profile/2020/10/26/27e7fd58-0972-4dbf-941c-c16a341558c7.png
         * salt : null
         * status : 0
         * delFlag : 0
         * loginIp : 127.0.0.1
         * loginDate : 2018-03-16T11:33:00.000+0800
         * dept : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"deptId":103,"parentId":102,"ancestors":null,"deptName":"研发部门","orderNum":"1","leader":"张三","phone":null,"email":null,"status":"0","delFlag":null,"parentName":null,"children":[]}
         * roles : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"roleId":1,"roleName":"超级管理员","roleKey":"admin","roleSort":"1","dataScope":"1","status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null,"admin":true}]
         * roleIds : null
         * postIds : null
         * idCard : 211224199506053265
         * file : null
         * admin : true
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private String remark;
        private ParamsDTO params;
        private int userId;
        private int deptId;
        private String userName;
        private Object oldPwd;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private Object salt;
        private String status;
        private String delFlag;
        private String loginIp;
        private String loginDate;
        private DeptDTO dept;
        private Object roleIds;
        private Object postIds;
        private String idCard;
        private Object file;
        private boolean admin;
        private List<RolesDTO> roles;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getOldPwd() {
            return oldPwd;
        }

        public void setOldPwd(Object oldPwd) {
            this.oldPwd = oldPwd;
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

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public DeptDTO getDept() {
            return dept;
        }

        public void setDept(DeptDTO dept) {
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

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Object getFile() {
            return file;
        }

        public void setFile(Object file) {
            this.file = file;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<RolesDTO> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesDTO> roles) {
            this.roles = roles;
        }

        public static class ParamsDTO {
        }

        public static class DeptDTO {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * deptId : 103
             * parentId : 102
             * ancestors : null
             * deptName : 研发部门
             * orderNum : 1
             * leader : 张三
             * phone : null
             * email : null
             * status : 0
             * delFlag : null
             * parentName : null
             * children : []
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsDTOX params;
            private int deptId;
            private int parentId;
            private Object ancestors;
            private String deptName;
            private String orderNum;
            private String leader;
            private Object phone;
            private Object email;
            private String status;
            private Object delFlag;
            private Object parentName;
            private List<?> children;

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

            public ParamsDTOX getParams() {
                return params;
            }

            public void setParams(ParamsDTOX params) {
                this.params = params;
            }

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public Object getAncestors() {
                return ancestors;
            }

            public void setAncestors(Object ancestors) {
                this.ancestors = ancestors;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }

            public static class ParamsDTOX {
            }
        }

        public static class RolesDTO {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * roleId : 1
             * roleName : 超级管理员
             * roleKey : admin
             * roleSort : 1
             * dataScope : 1
             * status : 0
             * delFlag : null
             * flag : false
             * menuIds : null
             * deptIds : null
             * admin : true
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsDTOXX params;
            private int roleId;
            private String roleName;
            private String roleKey;
            private String roleSort;
            private String dataScope;
            private String status;
            private Object delFlag;
            private boolean flag;
            private Object menuIds;
            private Object deptIds;
            private boolean admin;

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

            public ParamsDTOXX getParams() {
                return params;
            }

            public void setParams(ParamsDTOXX params) {
                this.params = params;
            }

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleKey() {
                return roleKey;
            }

            public void setRoleKey(String roleKey) {
                this.roleKey = roleKey;
            }

            public String getRoleSort() {
                return roleSort;
            }

            public void setRoleSort(String roleSort) {
                this.roleSort = roleSort;
            }

            public String getDataScope() {
                return dataScope;
            }

            public void setDataScope(String dataScope) {
                this.dataScope = dataScope;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public Object getMenuIds() {
                return menuIds;
            }

            public void setMenuIds(Object menuIds) {
                this.menuIds = menuIds;
            }

            public Object getDeptIds() {
                return deptIds;
            }

            public void setDeptIds(Object deptIds) {
                this.deptIds = deptIds;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public static class ParamsDTOXX {
            }
        }
    }
}
