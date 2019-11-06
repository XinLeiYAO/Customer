package com.example.asus.customer.entity;

import java.util.List;

public class OrderprocessInfoBean {

    /**
     * Body : {"CreatorCard":"01012694","imageList":"https://imgcdn.wenes.cn/ProjectBusiness/Images/157136444971MTjCXkbpN8Riyw94jema1f.jpg","jdTime":"","oList":["量房资料已经提交完成，设计师已于2019-10-18 09:06:13选择设计院null执行","设计师已经选择：null执行。图纸张数0完成时间：2019-10-18 10:07:30","设计院已经分派绘图员:纪臣举绘图，绘图员已于2019-10-18 10:07:30提交图纸至质检中心","订单：11-241055-CP-1已于2019-10-18 10:09:05完成质检，质检结果合格","订单：11-241055-CP-1已于2019-10-18 10:09:05完成质检，请您验收！"],"spTime":"2019-10-18 10:09:05","tjTime":"2019-10-18 10:07:30","xdTime":"2019-10-18 09:06:13","yjspTime":"2019-10-18 22:07:30","ysTime":""}
     * StatusCode : 0
     * StatusMsg : 获取成功
     */

    private BodyBean Body;
    private int StatusCode;
    private String StatusMsg;

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public static class BodyBean {
        /**
         * CreatorCard : 01012694
         * imageList : https://imgcdn.wenes.cn/ProjectBusiness/Images/157136444971MTjCXkbpN8Riyw94jema1f.jpg
         * jdTime :
         * oList : ["量房资料已经提交完成，设计师已于2019-10-18 09:06:13选择设计院null执行","设计师已经选择：null执行。图纸张数0完成时间：2019-10-18 10:07:30","设计院已经分派绘图员:纪臣举绘图，绘图员已于2019-10-18 10:07:30提交图纸至质检中心","订单：11-241055-CP-1已于2019-10-18 10:09:05完成质检，质检结果合格","订单：11-241055-CP-1已于2019-10-18 10:09:05完成质检，请您验收！"]
         * spTime : 2019-10-18 10:09:05
         * tjTime : 2019-10-18 10:07:30
         * xdTime : 2019-10-18 09:06:13
         * yjspTime : 2019-10-18 22:07:30
         * ysTime :
         */

        private String CreatorCard;
        private String imageList;
        private String jdTime;
        private String spTime;
        private String tjTime;
        private String xdTime;
        private String yjspTime;
        private String ysTime;
        private List<String> oList;

        public String getCreatorCard() {
            return CreatorCard;
        }

        public void setCreatorCard(String CreatorCard) {
            this.CreatorCard = CreatorCard;
        }

        public String getImageList() {
            return imageList;
        }

        public void setImageList(String imageList) {
            this.imageList = imageList;
        }

        public String getJdTime() {
            return jdTime;
        }

        public void setJdTime(String jdTime) {
            this.jdTime = jdTime;
        }

        public String getSpTime() {
            return spTime;
        }

        public void setSpTime(String spTime) {
            this.spTime = spTime;
        }

        public String getTjTime() {
            return tjTime;
        }

        public void setTjTime(String tjTime) {
            this.tjTime = tjTime;
        }

        public String getXdTime() {
            return xdTime;
        }

        public void setXdTime(String xdTime) {
            this.xdTime = xdTime;
        }

        public String getYjspTime() {
            return yjspTime;
        }

        public void setYjspTime(String yjspTime) {
            this.yjspTime = yjspTime;
        }

        public String getYsTime() {
            return ysTime;
        }

        public void setYsTime(String ysTime) {
            this.ysTime = ysTime;
        }

        public List<String> getOList() {
            return oList;
        }

        public void setOList(List<String> oList) {
            this.oList = oList;
        }
    }
}
