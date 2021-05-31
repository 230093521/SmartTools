package com.gzeic.smartcity01.bean;

import java.util.List;
import java.util.Objects;

public class DiTieZdxxBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"name":"建国门","lines":[{"lineId":31,"lineName":"2号线"},{"lineId":33,"lineName":"1号线"},{"lineId":35,"lineName":"1号线"},{"lineId":38,"lineName":"2号线"}]}]
     */

    private String msg;
    private int code;
    private List<DataDTO> data;

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

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * name : 建国门
         * lines : [{"lineId":31,"lineName":"2号线"},{"lineId":33,"lineName":"1号线"},{"lineId":35,"lineName":"1号线"},{"lineId":38,"lineName":"2号线"}]
         */

        private String name;
        private List<LinesDTO> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesDTO> getLines() {
            return lines;
        }

        public void setLines(List<LinesDTO> lines) {
            this.lines = lines;
        }

        public static class LinesDTO {
            /**
             * lineId : 31
             * lineName : 2号线
             */

            private int lineId;
            private String lineName;

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }


            public boolean equals(Object o) {
                LinesDTO o1 = (LinesDTO) o;
                return lineName.equals(o1.lineName);
            }


            public int hashCode() {
                String in = lineName;
                return in.hashCode();
            }
        }
    }
}
