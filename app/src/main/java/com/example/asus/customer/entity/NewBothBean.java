package com.example.asus.customer.entity;

import java.util.List;

public class NewBothBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"completion":0,"orderNo":"126-164-X-1","flag":0,"rwdid":"126-164","orderMoney":275,"expectTime":"2019-05-30 00:00:00","expectDay":null,"remark":null,"designer":"付咏林","type":"效果图","completionTime":null,"number":1,"designInstitute":"武汉设计院","stage":0,"createTime":"2019-05-29 18:56:47","statusName":"下单","id":25250,"serviceid":null,"type_code":"xgt"},{"completion":0,"orderNo":"126-164-X-2","flag":0,"rwdid":"126-164","orderMoney":275,"expectTime":"2019-05-30 00:00:00","expectDay":null,"remark":null,"designer":"付咏林","type":"效果图","completionTime":null,"number":1,"designInstitute":"武汉设计院","stage":0,"createTime":"2019-05-29 18:57:39","statusName":"下单","id":25251,"serviceid":null,"type_code":"xgt"},{"completion":0,"orderNo":"126-164-X-3","flag":0,"rwdid":"126-164","orderMoney":275,"expectTime":"2019-05-30 00:00:00","expectDay":null,"remark":null,"designer":"付咏林","type":"效果图","completionTime":null,"number":1,"designInstitute":"武汉设计院","stage":0,"createTime":"2019-05-29 18:57:56","statusName":"下单","id":25252,"serviceid":null,"type_code":"xgt"}]
     */

    private String StatusMsg;
    private int StatusCode;
    private List<BodyBean> Body;

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

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * completion : 0
         * orderNo : 126-164-X-1
         * flag : 0
         * rwdid : 126-164
         * orderMoney : 275
         * expectTime : 2019-05-30 00:00:00
         * expectDay : null
         * remark : null
         * designer : 付咏林
         * type : 效果图
         * completionTime : null
         * number : 1
         * designInstitute : 武汉设计院
         * stage : 0
         * createTime : 2019-05-29 18:56:47
         * statusName : 下单
         * id : 25250
         * serviceid : null
         * type_code : xgt
         */

        private int completion;
        private String orderNo;
        private int flag;
        private String rwdid;
        private Object orderMoney;
        private String expectTime;
        private Object expectDay;
        private Object remark;
        private String designer;
        private String type;
        private Object completionTime;
        private int number;
        private String designInstitute;
        private int stage;
        private String createTime;
        private String statusName;
        private int id;
        private Object serviceid;
        private String type_code;

        public int getCompletion() {
            return completion;
        }

        public void setCompletion(int completion) {
            this.completion = completion;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getRwdid() {
            return rwdid;
        }

        public void setRwdid(String rwdid) {
            this.rwdid = rwdid;
        }

        public Object getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(int orderMoney) {
            this.orderMoney = orderMoney;
        }

        public String getExpectTime() {
            return expectTime;
        }

        public void setExpectTime(String expectTime) {
            this.expectTime = expectTime;
        }

        public Object getExpectDay() {
            return expectDay;
        }

        public void setExpectDay(Object expectDay) {
            this.expectDay = expectDay;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getDesigner() {
            return designer;
        }

        public void setDesigner(String designer) {
            this.designer = designer;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getCompletionTime() {
            return completionTime;
        }

        public void setCompletionTime(Object completionTime) {
            this.completionTime = completionTime;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getDesignInstitute() {
            return designInstitute;
        }

        public void setDesignInstitute(String designInstitute) {
            this.designInstitute = designInstitute;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getServiceid() {
            return serviceid;
        }

        public void setServiceid(Object serviceid) {
            this.serviceid = serviceid;
        }

        public String getType_code() {
            return type_code;
        }

        public void setType_code(String type_code) {
            this.type_code = type_code;
        }
    }
}
