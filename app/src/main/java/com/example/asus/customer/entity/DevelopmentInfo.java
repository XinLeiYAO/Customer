package com.example.asus.customer.entity;

import java.util.List;

public class DevelopmentInfo {


    /**
     * StatusCode : 1
     * StatusMsg : 成功
     * Body : [{"PN_Onumber":"22-120825","HS_mianji":"270.00","C_Identity":"老板","C_Name":"兰总","E_Operate":"软件开发","B_Name":"南谊大厦办公装修...","ZipPath":"http://i.rxjy.com/Areas/CustomerPage/content/images/NoPicture.png","projectName":"南谊大厦办公装修工程"}]
     */

    private int StatusCode;
    private String StatusMsg;
    private List<BodyBean> Body;

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

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * PN_Onumber : 22-120825
         * HS_mianji : 270.00
         * C_Identity : 老板
         * C_Name : 兰总
         * E_Operate : 软件开发
         * B_Name : 南谊大厦办公装修...
         * ZipPath : http://i.rxjy.com/Areas/CustomerPage/content/images/NoPicture.png
         * projectName : 南谊大厦办公装修工程
         */

        private String PN_Onumber;
        private String HS_mianji;
        private String C_Identity;
        private String C_Name;
        private String E_Operate;
        private String B_Name;
        private String ZipPath;
        private String projectName;

        public String getPN_Onumber() {
            return PN_Onumber;
        }

        public void setPN_Onumber(String PN_Onumber) {
            this.PN_Onumber = PN_Onumber;
        }

        public String getHS_mianji() {
            return HS_mianji;
        }

        public void setHS_mianji(String HS_mianji) {
            this.HS_mianji = HS_mianji;
        }

        public String getC_Identity() {
            return C_Identity;
        }

        public void setC_Identity(String C_Identity) {
            this.C_Identity = C_Identity;
        }

        public String getC_Name() {
            return C_Name;
        }

        public void setC_Name(String C_Name) {
            this.C_Name = C_Name;
        }

        public String getE_Operate() {
            return E_Operate;
        }

        public void setE_Operate(String E_Operate) {
            this.E_Operate = E_Operate;
        }

        public String getB_Name() {
            return B_Name;
        }

        public void setB_Name(String B_Name) {
            this.B_Name = B_Name;
        }

        public String getZipPath() {
            return ZipPath;
        }

        public void setZipPath(String ZipPath) {
            this.ZipPath = ZipPath;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }
}
