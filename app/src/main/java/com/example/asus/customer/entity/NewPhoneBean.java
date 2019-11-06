package com.example.asus.customer.entity;

/**
 * Created by liujiale on 2018/9/19.
 */

public class NewPhoneBean {

        /**
         * StatusCode : 0
         * StatusMsg : 修改成功！
         * Body : {"Token":"2495F4A1951C34F5DD29718234511702"}
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
             * Token : 2495F4A1951C34F5DD29718234511702
             */

            private String Token;

            public String getToken() {
                return Token;
            }

            public void setToken(String Token) {
                this.Token = Token;
            }
    }
}
