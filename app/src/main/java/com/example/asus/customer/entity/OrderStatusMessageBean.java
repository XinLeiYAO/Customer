package com.example.asus.customer.entity;

public class OrderStatusMessageBean {
    private String statusName;
    private String statusTime;
    private String order;
    private String yanshouren;
    private String orderNum;
    private String yujiTime;
    public void setStatusName(String statusName){
        this.statusName = statusName;
    }
    public String getStatusName(){
        return statusName;
    }
    public void setStatusTime(String statusTime){
        this.statusTime = statusTime;
    }
    public String getStatusTime(){
        return statusTime;
    }
    public void setOrder(String order){
        this.order = order;
    }
    public String getOrder(){
        return order;
    }
    public void setYanshouren(String yanshouren){
        this.yanshouren = yanshouren;
    }
    public String getYanshouren(){
        return yanshouren;
    }
    public void setOrderNum(String orderNum){
        this.orderNum = orderNum;
    }
    public String getOrderNum(){
        return orderNum;
    }
    public void setYujiTime(String yujiTime){
        this.yujiTime = yujiTime;
    }
    public String getYujiTime(){
        return yujiTime;
    }
}
