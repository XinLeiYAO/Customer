package com.example.asus.customer.entity;

/**
 * Created by liujiale on 2018/9/19.
 */

public class NewPhoneRandomBean {



        private int StatusCode;
        private String StatusMsg;
        private String Body;
        public void setStatusCode(int StatusCode) {
            this.StatusCode = StatusCode;
        }
        public int getStatusCode() {
            return StatusCode;
        }

        public void setStatusMsg(String StatusMsg) {
            this.StatusMsg = StatusMsg;
        }
        public String getStatusMsg() {
            return StatusMsg;
        }

        public void setBody(String Body) {
            this.Body = Body;
        }
        public String getBody() {
            return Body;
        }

}
