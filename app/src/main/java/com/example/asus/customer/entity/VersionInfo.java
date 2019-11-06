package com.example.asus.customer.entity;

/**
 * Created by AAA on 2017/8/9.
 */

public class VersionInfo {


    /**
     * StatusCode : 1
     * StatusMsg : 版本更新
     * Body : {"VersionName":"1","VersionNo":1,"VersionUrl":"http://i.rxjy.com/upload/rx_gongRen.apk","Content":"11111\n233adfa\n3:afadsfa\n4:fasdfafd","Force":0}
     */

    private int StatusCode;
    private String StatusMsg;
    private Version Body;

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

    public Version getBody() {
        return Body;
    }

    public void setBody(Version Body) {
        this.Body = Body;
    }

    public static class Version {
        /**
         * VersionName : 1
         * VersionNo : 1
         * VersionUrl : http://i.rxjy.com/upload/rx_gongRen.apk
         * Content : 11111
         233adfa
         3:afadsfa
         4:fasdfafd
         * Force : 0
         */

        private String VersionName;
        private int VersionNo;
        private String VersionUrl;
        private String Content;
        private int Force;

        public String getVersionName() {
            return VersionName;
        }

        public void setVersionName(String VersionName) {
            this.VersionName = VersionName;
        }

        public int getVersionNo() {
            return VersionNo;
        }

        public void setVersionNo(int VersionNo) {
            this.VersionNo = VersionNo;
        }

        public String getVersionUrl() {
            return VersionUrl;
        }

        public void setVersionUrl(String VersionUrl) {
            this.VersionUrl = VersionUrl;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getForce() {
            return Force;
        }

        public void setForce(int Force) {
            this.Force = Force;
        }
    }
}
