package com.example.asus.customer.entity;

public class NoMessagBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"miaoshu":"了解房屋详细尺寸数据。通过量房准确的了解房屋内各房间的长、宽、高以及门、窗、空调、暖气等的位置，然后做出具体的设计方案。很多装修公司为了节省成本，不给客户量房根据户型图就做方案，导致方案在施工过程中和实际情况有出入，需要调整，甚至出现方案需要彻底改变的情况","createTime":"2019-05-25 09:09:51","type":"量房"}
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
         * miaoshu : 了解房屋详细尺寸数据。通过量房准确的了解房屋内各房间的长、宽、高以及门、窗、空调、暖气等的位置，然后做出具体的设计方案。很多装修公司为了节省成本，不给客户量房根据户型图就做方案，导致方案在施工过程中和实际情况有出入，需要调整，甚至出现方案需要彻底改变的情况
         * createTime : 2019-05-25 09:09:51
         * type : 量房
         */

        private String miaoshu;
        private String createTime;
        private String type;

        public String getMiaoshu() {
            return miaoshu;
        }

        public void setMiaoshu(String miaoshu) {
            this.miaoshu = miaoshu;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
