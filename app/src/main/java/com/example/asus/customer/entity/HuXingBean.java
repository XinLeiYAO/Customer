package com.example.asus.customer.entity;

import java.util.List;

public class HuXingBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"MingCheng":"一居","ID":342},{"MingCheng":"二居","ID":343},{"MingCheng":"三居","ID":344},{"MingCheng":"四居","ID":345},{"MingCheng":"五居及五居以上","ID":346}]
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
         * MingCheng : 一居
         * ID : 342
         */

        private String MingCheng;
        private int ID;

        public String getMingCheng() {
            return MingCheng;
        }

        public void setMingCheng(String MingCheng) {
            this.MingCheng = MingCheng;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }
}
