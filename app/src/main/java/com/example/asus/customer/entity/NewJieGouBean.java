package com.example.asus.customer.entity;

import java.util.List;

public class NewJieGouBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"updateState":0,"catalogId":"57","rwdId":"105-293","createTime":"2019-04-28 18:11:28","imageUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1556446289pp6D4kj3zBpm2hap3iy0pAHh.jpg","detailId":"727007","updateTime":null,"id":106295,"isdel":0,"catalog_code":"jgt"}]
     */

    private String StatusMsg;
    private int StatusCode;
    private List<BodyBean> Body;

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * updateState : 0
         * catalogId : 57
         * rwdId : 105-293
         * createTime : 2019-04-28 18:11:28
         * imageUrl : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1556446289pp6D4kj3zBpm2hap3iy0pAHh.jpg
         * detailId : 727007
         * updateTime : null
         * id : 106295
         * isdel : 0
         * catalog_code : jgt
         */

        private int updateState;
        private String catalogId;
        private String rwdId;
        private String createTime;
        private String imageUrl;
        private String detailId;
        private Object updateTime;
        private int id;
        private int isdel;
        private String catalog_code;

        public int getUpdateState() {
            return updateState;
        }

        public void setUpdateState(int updateState) {
            this.updateState = updateState;
        }

        public String getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(String catalogId) {
            this.catalogId = catalogId;
        }

        public String getRwdId() {
            return rwdId;
        }

        public void setRwdId(String rwdId) {
            this.rwdId = rwdId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getCatalog_code() {
            return catalog_code;
        }

        public void setCatalog_code(String catalog_code) {
            this.catalog_code = catalog_code;
        }
    }
}
