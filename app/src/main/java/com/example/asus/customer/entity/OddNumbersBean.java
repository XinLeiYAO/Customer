package com.example.asus.customer.entity;

/**
 * Created by asus on 2018/5/29.
 */

public class OddNumbersBean {


    /**
     * StatusCode : 0
     * StatusMsg : 获取成功
     * Body : {"app_id":"193e678a-37e4-4d0b-9cce-81aeea73b99e","card_no":"z00000174","name":"李倩","post_id":10001,"post_name":"客户","token":"06F8CC01BD962FD931EFF675B8D23692"}
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
         * app_id : 193e678a-37e4-4d0b-9cce-81aeea73b99e
         * card_no : z00000174
         * name : 李倩
         * post_id : 10001
         * post_name : 客户
         * token : 06F8CC01BD962FD931EFF675B8D23692
         */

        private String app_id;
        private String card_no;
        private String name;
        private int post_id;
        private String post_name;
        private String token;

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPost_id() {
            return post_id;
        }

        public void setPost_id(int post_id) {
            this.post_id = post_id;
        }

        public String getPost_name() {
            return post_name;
        }

        public void setPost_name(String post_name) {
            this.post_name = post_name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
