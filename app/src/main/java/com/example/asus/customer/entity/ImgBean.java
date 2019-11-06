package com.example.asus.customer.entity;

/**
 * Created by liujiale on 2018/10/25.
 */

public class ImgBean {

    /**
     * StatusCode : 0
     * StatusMsg : success
     * Body : {"url":"https://common-rxjy.oss-cn-beijing.aliyuncs.com/1540525038120.jpg"}
     */

    private int StatusCode;
    private String StatusMsg;
    private BodyBean Body;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * url : https://common-rxjy.oss-cn-beijing.aliyuncs.com/1540525038120.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
