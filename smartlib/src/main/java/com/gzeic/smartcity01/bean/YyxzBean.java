package com.gzeic.smartcity01.bean;

public class YyxzBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"notice":"<p style=\\\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\\\"><span style=\\\"font-size:16px;\\\">1.<\/span><span style=\\\"font-size:16px;\\\">至少提前两周预约<\/span><span style=\\\"font-size:16px;\\\">，<span style=\\\"font-size:100%;\\\">预<\/span>约确定以支付定金为准，定金20元(不可退)；拍照排期以收到定金顺序为准。<\/span><\/p><span style=\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:none;\\\"><\/span><p style=\\\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\\\"><span style=\\\"font-size:16px;\\\">2.检车余款于拍照当日结清：支付宝／微信／现金均可。<\/span><\/p><span style=\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:none;\\\"> <\/span><p style=\\\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\\\"><span style=\\\"font-size:16px;\\\">3.如遇个人原因临时变更，请于原定拍摄时间提前<span style=\\\"font-size:100%;\\\">至少48小时<\/span>与我联系更改，感谢理解；预约当天无特殊理由取消，订单作废，再次预约重付20元定金。<\/span><\/p>"}
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
         * notice : <p style=\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\"><span style=\"font-size:16px;\">1.</span><span style=\"font-size:16px;\">至少提前两周预约</span><span style=\"font-size:16px;\">，<span style=\"font-size:100%;\">预</span>约确定以支付定金为准，定金20元(不可退)；拍照排期以收到定金顺序为准。</span></p><span style=\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:none;\"></span><p style=\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\"><span style=\"font-size:16px;\">2.检车余款于拍照当日结清：支付宝／微信／现金均可。</span></p><span style=\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:none;\"> </span><p style=\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\"><span style=\"font-size:16px;\">3.如遇个人原因临时变更，请于原定拍摄时间提前<span style=\"font-size:100%;\">至少48小时</span>与我联系更改，感谢理解；预约当天无特殊理由取消，订单作废，再次预约重付20元定金。</span></p>
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String notice;

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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public static class ParamsDTO {
        }
    }
}
