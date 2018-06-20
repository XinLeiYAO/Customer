package com.example.asus.customer.entity;

/**
 * Created by asus on 2018/4/23.
 */

public class OrderNoInfo {

    /**
     * event : CustomerRegister
     * parameter : {"orderNo":"xx-xxxxxx"}
     */

    private String event;
    private ParameterBean parameter;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public ParameterBean getParameter() {
        return parameter;
    }

    public void setParameter(ParameterBean parameter) {
        this.parameter = parameter;
    }

    public static class ParameterBean {
        /**
         * orderNo : xx-xxxxxx
         */

        private String orderNo;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
    }
}
