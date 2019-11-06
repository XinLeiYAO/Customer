package com.example.asus.customer.entity;

import java.util.List;

public class NewHistoryBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"reason":"测试","orderNo":"","handContent":null,"rwdid":"12-202079","createTime":"2019-06-11 20:04:47","handTime":null,"id":164,"type":"cpt","reasonType":"哈哈","opinion":"yes","hand":0}]
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
         * reason : 测试
         * orderNo :
         * handContent : null
         * rwdid : 12-202079
         * createTime : 2019-06-11 20:04:47
         * handTime : null
         * id : 164
         * type : cpt
         * reasonType : 哈哈
         * opinion : yes
         * hand : 0
         */

        private String reason;
        private String orderNo;
        private Object handContent;
        private String rwdid;
        private String createTime;
        private Object handTime;
        private int id;
        private String type;
        private String reasonType;
        private String opinion;
        private int hand;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public Object getHandContent() {
            return handContent;
        }

        public void setHandContent(Object handContent) {
            this.handContent = handContent;
        }

        public String getRwdid() {
            return rwdid;
        }

        public void setRwdid(String rwdid) {
            this.rwdid = rwdid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getHandTime() {
            return handTime;
        }

        public void setHandTime(Object handTime) {
            this.handTime = handTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReasonType() {
            return reasonType;
        }

        public void setReasonType(String reasonType) {
            this.reasonType = reasonType;
        }

        public String getOpinion() {
            return opinion;
        }

        public void setOpinion(String opinion) {
            this.opinion = opinion;
        }

        public int getHand() {
            return hand;
        }

        public void setHand(int hand) {
            this.hand = hand;
        }
    }
}
