package com.example.asus.customer.entity;

/**
 * Created by asus on 2018/5/29.
 */

public class TokenInfo {


    /**
     * StatusCode : 0
     * StatusMsg : 登陆成功！
     * Body : {"cardNo":"k00000011","account":"15300273163","Token":"1FA02490DDAE796D320B5B4BC6838026","appId":"7d15bed8-253a-4892-bcd2-1fcd0b75a6d1"}
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
         * cardNo : k00000011
         * account : 15300273163
         * Token : 1FA02490DDAE796D320B5B4BC6838026
         * appId : 7d15bed8-253a-4892-bcd2-1fcd0b75a6d1
         */

        private String cardNo;
        private String account;
        private String Token;
        private String appId;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
}
