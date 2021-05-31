package com.gzeic.smartcity01.bean;

import java.util.List;

public class DtSwzlBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"publishDate":"2021-04-06","metroFoundList":[{"id":4,"imgUrl":"/dev-api/profile/upload/image/2021/04/07/a86d0ef d-4ffb-488b-8fe0-36a84d343066.png","foundTime":"2021-04-06","foundPlace":"北京站","claimPlace":"北京站服务中心"}]}]
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
         * publishDate : 2021-04-06
         * metroFoundList : [{"id":4,"imgUrl":"/dev-api/profile/upload/image/2021/04/07/a86d0ef d-4ffb-488b-8fe0-36a84d343066.png","foundTime":"2021-04-06","foundPlace":"北京站","claimPlace":"北京站服务中心"}]
         */

        private String publishDate;
        private List<MetroFoundListDTO> metroFoundList;

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public List<MetroFoundListDTO> getMetroFoundList() {
            return metroFoundList;
        }

        public void setMetroFoundList(List<MetroFoundListDTO> metroFoundList) {
            this.metroFoundList = metroFoundList;
        }

        public static class MetroFoundListDTO {
            /**
             * id : 4
             * imgUrl : /dev-api/profile/upload/image/2021/04/07/a86d0ef d-4ffb-488b-8fe0-36a84d343066.png
             * foundTime : 2021-04-06
             * foundPlace : 北京站
             * claimPlace : 北京站服务中心
             */

            private int id;
            private String imgUrl;
            private String foundTime;
            private String foundPlace;
            private String claimPlace;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getFoundTime() {
                return foundTime;
            }

            public void setFoundTime(String foundTime) {
                this.foundTime = foundTime;
            }

            public String getFoundPlace() {
                return foundPlace;
            }

            public void setFoundPlace(String foundPlace) {
                this.foundPlace = foundPlace;
            }

            public String getClaimPlace() {
                return claimPlace;
            }

            public void setClaimPlace(String claimPlace) {
                this.claimPlace = claimPlace;
            }
        }
    }
}
