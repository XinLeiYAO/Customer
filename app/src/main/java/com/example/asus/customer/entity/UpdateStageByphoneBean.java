package com.example.asus.customer.entity;

public class UpdateStageByphoneBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : 1
     */

    private String StatusMsg;
    private int StatusCode;
    private int Body;

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

    public int getBody() {
        return Body;
    }

    public void setBody(int Body) {
        this.Body = Body;
    }
}
