package com.example.asus.customer.entity;

public class QuerysiteBean {


    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : {"userPhone":"17610246953","siteId":37,"siteText":"昌平区沙河高教园","siteRemark":"1区","userName":"姬玉华"}
     */

    private String StatusMsg;
    private int StatusCode;
    private BodyBean Body;

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

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * userPhone : 17610246953
         * siteId : 37
         * siteText : 昌平区沙河高教园
         * siteRemark : 1区
         * userName : 姬玉华
         */

        private String userPhone;
        private int siteId;
        private String siteText;
        private String siteRemark;
        private String userName;

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
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
