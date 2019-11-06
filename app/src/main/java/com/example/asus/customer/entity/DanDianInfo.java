package com.example.asus.customer.entity;

public class DanDianInfo {

    /**
     * ID : 0
     * SendToken : 3035841862516460D4D033C378AD65E7
     * StatusMsg : 尊敬的用户,您的账号于2018/09/27 09:37在LLD-AL20手机上登陆，若非您本人登陆，请及时修改密码
     * Type : 104
     * Type_title : 后台推送
     */

    private int ID;
    private String SendToken;
    private String StatusMsg;
    private String Type;
    private String Type_title;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSendToken() {
        return SendToken;
    }

    public void setSendToken(String SendToken) {
        this.SendToken = SendToken;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getType_title() {
        return Type_title;
    }

    public void setType_title(String Type_title) {
        this.Type_title = Type_title;
    }
}
