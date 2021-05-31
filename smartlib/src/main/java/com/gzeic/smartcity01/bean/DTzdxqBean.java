package com.gzeic.smartcity01.bean;

import java.util.List;

public class DTzdxqBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":1,"name":"16号线","first":"西苑","end":"北安河","startTime":"05:25","endTime":"23:00","cityId":1,"stationsNumber":null,"km":10,"runStationsName":"农大南路","metroStepList":[{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":1,"name":"西苑","seq":0,"lineId":1,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":2,"name":"农大南路","seq":0,"lineId":1,"firstCh":"N"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":3,"name":"马连洼","seq":0,"lineId":1,"firstCh":"M"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":4,"name":"西北旺","seq":0,"lineId":1,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":5,"name":"永丰南","seq":0,"lineId":1,"firstCh":"Y"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":6,"name":"永丰","seq":0,"lineId":1,"firstCh":"Y"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":7,"name":"屯佃","seq":0,"lineId":1,"firstCh":"T"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:39","remark":null,"params":{},"id":8,"name":"稻香湖路","seq":0,"lineId":1,"firstCh":"D"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:39","remark":null,"params":{},"id":9,"name":"温阳路","seq":0,"lineId":1,"firstCh":"W"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:39","remark":null,"params":{},"id":10,"name":"北安河","seq":0,"lineId":1,"firstCh":"B"}],"remainingTime":2}
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
         * id : 1
         * name : 16号线
         * first : 西苑
         * end : 北安河
         * startTime : 05:25
         * endTime : 23:00
         * cityId : 1
         * stationsNumber : null
         * km : 10
         * runStationsName : 农大南路
         * metroStepList : [{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":1,"name":"西苑","seq":0,"lineId":1,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":2,"name":"农大南路","seq":0,"lineId":1,"firstCh":"N"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":3,"name":"马连洼","seq":0,"lineId":1,"firstCh":"M"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":4,"name":"西北旺","seq":0,"lineId":1,"firstCh":"X"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":5,"name":"永丰南","seq":0,"lineId":1,"firstCh":"Y"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":6,"name":"永丰","seq":0,"lineId":1,"firstCh":"Y"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":7,"name":"屯佃","seq":0,"lineId":1,"firstCh":"T"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:39","remark":null,"params":{},"id":8,"name":"稻香湖路","seq":0,"lineId":1,"firstCh":"D"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:39","remark":null,"params":{},"id":9,"name":"温阳路","seq":0,"lineId":1,"firstCh":"W"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:39","remark":null,"params":{},"id":10,"name":"北安河","seq":0,"lineId":1,"firstCh":"B"}]
         * remainingTime : 2
         */

        private int id;
        private String name;
        private String first;
        private String end;
        private String startTime;
        private String endTime;
        private int cityId;
        private Object stationsNumber;
        private int km;
        private String runStationsName;
        private int remainingTime;
        private List<MetroStepListDTO> metroStepList;

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

        public Object getStationsNumber() {
            return stationsNumber;
        }

        public void setStationsNumber(Object stationsNumber) {
            this.stationsNumber = stationsNumber;
        }

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public String getRunStationsName() {
            return runStationsName;
        }

        public void setRunStationsName(String runStationsName) {
            this.runStationsName = runStationsName;
        }

        public int getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(int remainingTime) {
            this.remainingTime = remainingTime;
        }

        public List<MetroStepListDTO> getMetroStepList() {
            return metroStepList;
        }

        public void setMetroStepList(List<MetroStepListDTO> metroStepList) {
            this.metroStepList = metroStepList;
        }

        public static class MetroStepListDTO {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2018-07-23 02:28:36
             * updateBy : null
             * updateTime : 2021-04-05 15:46:38
             * remark : null
             * params : {}
             * id : 1
             * name : 西苑
             * seq : 0
             * lineId : 1
             * firstCh : X
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
            private int seq;
            private int lineId;
            private String firstCh;

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

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getFirstCh() {
                return firstCh;
            }

            public void setFirstCh(String firstCh) {
                this.firstCh = firstCh;
            }

            public static class ParamsDTO {
            }
        }
    }
}
