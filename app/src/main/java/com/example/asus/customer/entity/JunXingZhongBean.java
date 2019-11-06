package com.example.asus.customer.entity;

public class JunXingZhongBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"stage":2,"data":{"completion":0,"orderNo":null,"flag":0,"rwdid":"12-202079","orderMoney":null,"expectTime":"2019-06-19 13:28:11","expectDay":48,"remark":null,"designer":null,"type":"彩平图","completionTime":null,"number":null,"designInstitute":null,"stage":1,"createTime":"2019-06-17 13:28:11","statusName":null,"id":25982,"serviceid":null,"type_code":"cpt"}}
     */

    private String StatusMsg;
    private int StatusCode;
    private BodyBean Body;

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

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * stage : 2
         * data : {"completion":0,"orderNo":null,"flag":0,"rwdid":"12-202079","orderMoney":null,"expectTime":"2019-06-19 13:28:11","expectDay":48,"remark":null,"designer":null,"type":"彩平图","completionTime":null,"number":null,"designInstitute":null,"stage":1,"createTime":"2019-06-17 13:28:11","statusName":null,"id":25982,"serviceid":null,"type_code":"cpt"}
         */

        private int stage;
        private DataBean data;

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * completion : 0
             * orderNo : null
             * flag : 0
             * rwdid : 12-202079
             * orderMoney : null
             * expectTime : 2019-06-19 13:28:11
             * expectDay : 48
             * remark : null
             * designer : null
             * type : 彩平图
             * completionTime : null
             * number : null
             * designInstitute : null
             * stage : 1
             * createTime : 2019-06-17 13:28:11
             * statusName : null
             * id : 25982
             * serviceid : null
             * type_code : cpt
             */

            private int completion;
            private Object orderNo;
            private int flag;
            private String rwdid;
            private Object orderMoney;
            private String expectTime;
            private int expectDay;
            private Object remark;
            private Object designer;
            private String type;
            private Object completionTime;
            private Object number;
            private Object designInstitute;
            private int stage;
            private String createTime;
            private Object statusName;
            private int id;
            private Object serviceid;
            private String type_code;

            public int getCompletion() {
                return completion;
            }

            public void setCompletion(int completion) {
                this.completion = completion;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
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

            public void setOrderMoney(Object orderMoney) {
                this.orderMoney = orderMoney;
            }

            public String getExpectTime() {
                return expectTime;
            }

            public void setExpectTime(String expectTime) {
                this.expectTime = expectTime;
            }

            public int getExpectDay() {
                return expectDay;
            }

            public void setExpectDay(int expectDay) {
                this.expectDay = expectDay;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getDesigner() {
                return designer;
            }

            public void setDesigner(Object designer) {
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

            public Object getNumber() {
                return number;
            }

            public void setNumber(Object number) {
                this.number = number;
            }

            public Object getDesignInstitute() {
                return designInstitute;
            }

            public void setDesignInstitute(Object designInstitute) {
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

            public Object getStatusName() {
                return statusName;
            }

            public void setStatusName(Object statusName) {
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
}
