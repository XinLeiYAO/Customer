package com.example.asus.customer.entity;

import java.util.List;

public class BannerImgListBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572264303MrKip3pxcR3XCBP5E0HFxzQT.jpg","href":"wwww","picId":5}]
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
         * picUrl : https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572264303MrKip3pxcR3XCBP5E0HFxzQT.jpg
         * href : wwww
         * picId : 5
         */

        private String picUrl;
        private String href;
        private int picId;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getPicId() {
            return picId;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }
    }
}
