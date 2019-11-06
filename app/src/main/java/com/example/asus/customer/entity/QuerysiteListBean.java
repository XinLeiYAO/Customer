package com.example.asus.customer.entity;

import java.util.List;

public class QuerysiteListBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : [{"userPhone":"16601162151","siteId":29,"siteText":"北京市永定河路322号","siteRemark":"好好好","userName":"王晓银"},{"userPhone":"15846465484","siteId":30,"siteText":"广东深圳宝安机场","siteRemark":"这是详细地址","userName":"刘金龙"}]
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
         * userPhone : 16601162151
         * siteId : 29
         * siteText : 北京市永定河路322号
         * siteRemark : 好好好
         * userName : 王晓银
         */

        private String userPhone;
        private String siteId;
        private String siteText;
        private String siteRemark;
        private String userName;

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getSiteText() {
            return siteText;
        }

        public void setSiteText(String siteText) {
            this.siteText = siteText;
        }

        public String getSiteRemark() {
            return siteRemark;
        }

        public void setSiteRemark(String siteRemark) {
            this.siteRemark = siteRemark;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
