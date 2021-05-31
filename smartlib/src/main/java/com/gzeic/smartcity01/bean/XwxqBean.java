package com.gzeic.smartcity01.bean;

public class XwxqBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"1","createTime":"2021-04-01 12:12:45","updateBy":"1","updateTime":"2021-05-17 12:27:14","remark":null,"params":{},"id":6,"appType":"movie","cover":"/prod-api/profile/upload/image/2021/05/06/c4cf86a6-6f45-440d-823c-050aace5dc9f.jpg","title":"寻汉计","subTitle":null,"content":"<p>导演: 唐大年<\/p><p>编剧: 赵赵<\/p><p>主演: 任素汐 / 李保田 / 王子川 / 张本煜 / 李勤勤 / 更多...<\/p><p>类型: 喜剧<\/p><p>制片国家/地区: 中国大陆 / 中国香港<\/p><p>语言: 汉语普通话<\/p><p>上映日期: 2021-05-01(中国大陆)<\/p><p>片长: 106分钟<\/p><p>又名: 生不由己<\/p><p>&nbsp;<\/p><p>寻汉计的剧情简介<\/p><p>· · · · · ·<\/p><p>&nbsp;2016年伊始，大龄女青年王招（任素汐 饰）离婚后发现自己怀了前夫的孩子，然而此时非婚生子女尚不能登记户口，千方百计想留下这个孩子的她，在姥爷（李保田饰）的热心\u201c帮助\u201d下，开启了一段阴差阳错的\u201c寻汉\u201d之旅\u2026\u2026<\/p>","status":"Y","publishDate":"2021-05-06","tags":null,"commentNum":null,"likeNum":306,"readNum":1542,"type":"2","top":"Y","hot":"Y"}
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
         * createBy : 1
         * createTime : 2021-04-01 12:12:45
         * updateBy : 1
         * updateTime : 2021-05-17 12:27:14
         * remark : null
         * params : {}
         * id : 6
         * appType : movie
         * cover : /prod-api/profile/upload/image/2021/05/06/c4cf86a6-6f45-440d-823c-050aace5dc9f.jpg
         * title : 寻汉计
         * subTitle : null
         * content : <p>导演: 唐大年</p><p>编剧: 赵赵</p><p>主演: 任素汐 / 李保田 / 王子川 / 张本煜 / 李勤勤 / 更多...</p><p>类型: 喜剧</p><p>制片国家/地区: 中国大陆 / 中国香港</p><p>语言: 汉语普通话</p><p>上映日期: 2021-05-01(中国大陆)</p><p>片长: 106分钟</p><p>又名: 生不由己</p><p>&nbsp;</p><p>寻汉计的剧情简介</p><p>· · · · · ·</p><p>&nbsp;2016年伊始，大龄女青年王招（任素汐 饰）离婚后发现自己怀了前夫的孩子，然而此时非婚生子女尚不能登记户口，千方百计想留下这个孩子的她，在姥爷（李保田饰）的热心“帮助”下，开启了一段阴差阳错的“寻汉”之旅……</p>
         * status : Y
         * publishDate : 2021-05-06
         * tags : null
         * commentNum : null
         * likeNum : 306
         * readNum : 1542
         * type : 2
         * top : Y
         * hot : Y
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsDTO params;
        private int id;
        private String appType;
        private String cover;
        private String title;
        private Object subTitle;
        private String content;
        private String status;
        private String publishDate;
        private Object tags;
        private Object commentNum;
        private int likeNum;
        private int readNum;
        private String type;
        private String top;
        private String hot;

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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
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

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(Object subTitle) {
            this.subTitle = subTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public static class ParamsDTO {
        }
    }
}
