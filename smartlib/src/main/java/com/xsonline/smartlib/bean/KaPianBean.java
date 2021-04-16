package com.xsonline.smartlib.bean;

import java.util.List;

public class KaPianBean {

    /**
     * total : 20
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"name":"李小溪","cardId":"210345876987764567","tel":"13456765476","sex":1,"birthday":"2020-10-13","adders":"大连市高新区","userId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"name":"李四","cardId":"210345876987764567","tel":"13456765476","sex":1,"birthday":"2020-10-23","adders":"大连市高新区","userId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"name":"张三","cardId":"25011199312163265","tel":"13502164251","sex":1,"birthday":"2012-06-12","adders":"大连市","userId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"name":"李四","cardId":"2350199012034125","tel":"13875264515","sex":1,"birthday":"2012-06-12","adders":"大连市","userId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"name":"小张","cardId":"112222222233333333","tel":"15633778899","sex":1,"birthday":"2012-06-12","adders":"大连市","userId":12},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"name":"貂蝉","cardId":"231248173514131","tel":"13502164251","sex":1,"birthday":"2019-06-12","adders":"大连市","userId":11},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"name":"吕布","cardId":"03471327193857819","tel":"13502164251","sex":1,"birthday":"2020-06-12","adders":"大连市","userId":11},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"name":"张飞","cardId":"2341354151351351","tel":"12298738282","sex":0,"birthday":"2020-09-01","adders":"辽宁省沈阳市","userId":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"name":"陈萍萍","cardId":"2383714173817418","tel":"13457482243","sex":0,"birthday":"2020-04-01","adders":"鞍山市","userId":4},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"name":"范闲","cardId":"2343124314141241","tel":"19823727237","sex":0,"birthday":"2020-05-11","adders":"哈尔滨市","userId":5},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"name":"李逵","cardId":"3411141241324131","tel":"15422222222","sex":1,"birthday":"2020-09-29","adders":"沈阳市","userId":6},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":14,"name":"李沁","cardId":"34311414124321441","tel":"16633411244","sex":0,"birthday":"2020-07-09","adders":"庄河","userId":7},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":15,"name":"翟肆","cardId":"2341414141234123","tel":"17787987343","sex":0,"birthday":"2020-09-02","adders":"富锦","userId":8},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":16,"name":"刘阳","cardId":"212318199707186789","tel":"13012345678","sex":1,"birthday":"2020-10-27","adders":"北京海淀区","userId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"name":"王东","cardId":"230888777646463728","tel":"13898776509","sex":1,"birthday":"2020-10-29","adders":"台湾省","userId":13},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":18,"name":"李丽","cardId":"218282477349371","tel":"15577788890","sex":2,"birthday":"2020-10-30","adders":"北京","userId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":19,"name":"李丽","cardId":"218282477349372","tel":"15577788890","sex":2,"birthday":"2020-10-30","adders":"北京","userId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":20,"name":"李丽","cardId":"218282477349372","tel":"15577788890","sex":2,"birthday":"2020-10-30","adders":"北京","userId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":21,"name":"李四AS","cardId":"297777883948379094","tel":"13586958631","sex":1,"birthday":"2020-11-04","adders":"北京","userId":16},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":22,"name":"李东","cardId":"245687569842365789","tel":"13896574220","sex":1,"birthday":"2020-11-05","adders":"北京","userId":14}]
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
         * name : 李小溪
         * cardId : 210345876987764567
         * tel : 13456765476
         * sex : 1
         * birthday : 2020-10-13
         * adders : 大连市高新区
         * userId : 1
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private String cardId;
        private String tel;
        private int sex;
        private String birthday;
        private String adders;
        private int userId;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAdders() {
            return adders;
        }

        public void setAdders(String adders) {
            this.adders = adders;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class ParamsBean {
        }
    }
}
