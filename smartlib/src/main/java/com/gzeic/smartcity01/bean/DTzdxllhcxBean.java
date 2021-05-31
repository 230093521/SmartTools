package com.gzeic.smartcity01.bean;

import java.util.List;

public class DTzdxllhcxBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"stepInfo":{"name":"建国门","crowd":1},"lineList":[{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 14:53:43","remark":null,"params":{},"id":31,"name":"2号线","first":"内环(积水潭)","end":"积水潭","startTime":"05:10","endTime":"22:20","cityId":1,"cityName":"北京市"}]}
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
         * stepInfo : {"name":"建国门","crowd":1}
         * lineList : [{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 14:53:43","remark":null,"params":{},"id":31,"name":"2号线","first":"内环(积水潭)","end":"积水潭","startTime":"05:10","endTime":"22:20","cityId":1,"cityName":"北京市"}]
         */

        private StepInfoDTO stepInfo;
        private List<LineListDTO> lineList;

        public StepInfoDTO getStepInfo() {
            return stepInfo;
        }

        public void setStepInfo(StepInfoDTO stepInfo) {
            this.stepInfo = stepInfo;
        }

        public List<LineListDTO> getLineList() {
            return lineList;
        }

        public void setLineList(List<LineListDTO> lineList) {
            this.lineList = lineList;
        }

        public static class StepInfoDTO {
            /**
             * name : 建国门
             * crowd : 1
             */

            private String name;
            private int crowd;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCrowd() {
                return crowd;
            }

            public void setCrowd(int crowd) {
                this.crowd = crowd;
            }
        }

        public static class LineListDTO {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2018-07-23 02:28:40
             * updateBy : null
             * updateTime : 2021-04-05 14:53:43
             * remark : null
             * params : {}
             * id : 31
             * name : 2号线
             * first : 内环(积水潭)
             * end : 积水潭
             * startTime : 05:10
             * endTime : 22:20
             * cityId : 1
             * cityName : 北京市
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsDTO params;
            private int id;
            private String name;
            private String first;
            private String end;
            private String startTime;
            private String endTime;
            private int cityId;
            private String cityName;

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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst() {
                return first;
            }

            public void setFirst(String first) {
                this.first = first;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public static class ParamsDTO {
            }
        }
    }
}
