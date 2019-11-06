package com.example.asus.customer.entity;

/**
 * Created by 阿禹 on 2018/7/13.
 */

public class SaoMaLoginBean {
    /**
     * event : 扫码免登陆
     * parameter : {"id":"5"}
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
         * id : 5
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
