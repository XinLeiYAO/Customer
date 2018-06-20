package com.example.asus.customer.entity;

import java.util.List;

/**
 * Created by asus on 2018/5/29.
 */

public class OddNumbersBean {


    /**
     * StatusCode : 0
     * StatusMsg : 获取成功
     * Body : [{"PN_Onumber":"12-203582"}]
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
         * PN_Onumber : 12-203582
         */

        private String PN_Onumber;

        public String getPN_Onumber() {
            return PN_Onumber;
        }

        public void setPN_Onumber(String PN_Onumber) {
            this.PN_Onumber = PN_Onumber;
        }
    }
}
