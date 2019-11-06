package com.example.asus.customer.entity;

import java.util.List;

public class xvqiuMessageBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : ["门头/外立面","吧台/收银","大厅","卫生间","走廊/楼梯间","包间","散座区","其它"]
     */

    private String StatusMsg;
    private int StatusCode;
    private List<String> Body;

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

    public List<String> getBody() {
        return Body;
    }

    public void setBody(List<String> Body) {
        this.Body = Body;
    }
}
