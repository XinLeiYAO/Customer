package com.example.asus.customer.entity;

import java.util.List;

public class PlanCollectionBean {

    /**
     * Body : [{"drwingType":6,"groupId":"269","groupName":"郑州第一概念设计院","imgName":"彩平图","orderNo":"71-240679-CP-1","orderTime":"2019-10-29 13:23:50","rwdId":"71-240679","status":"DPJ","statusName":"待评价"},{"drwingType":5,"groupId":"269","groupName":"郑州第一概念设计院","imgName":"平面草图","orderNo":"71-240679-CT-1","orderTime":"2019-10-29 13:23:50","rwdId":"71-240679","status":"DJD","statusName":"待接单"},{"drwingType":2,"groupId":"269","groupName":"郑州第一概念设计院","imgName":"分区构图","orderNo":"71-240679-FQ-1","orderTime":"2019-10-29 13:23:50","rwdId":"71-240679","status":"DSP","statusName":"待审批"},{"drwingType":3,"groupId":"269","groupName":"郑州第一概念设计院","imgName":"功能构图","orderNo":"71-240679-GN-1","orderTime":"2019-10-29 13:23:50","rwdId":"71-240679","status":"DYS","statusName":"客待验"},{"drwingType":4,"groupId":"269","groupName":"郑州第一概念设计院","imgName":"色彩构图","orderNo":"71-240679-SC-1","orderTime":"2019-10-29 13:23:50","rwdId":"71-240679","status":"DJD","statusName":"待接单"},{"drwingType":1,"groupId":"269","groupName":"郑州第一概念设计院","imgName":"原始构图","orderNo":"71-240679-YS-1","orderTime":"2019-10-29 13:23:50","rwdId":"71-240679","status":"HTZ","statusName":"绘图中"}]
     * StatusCode : 0
     * StatusMsg : 获取成功
     */

    private int StatusCode;
    private String StatusMsg;
    private List<BodyBean> Body;

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

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * drwingType : 6
         * groupId : 269
         * groupName : 郑州第一概念设计院
         * imgName : 彩平图
         * orderNo : 71-240679-CP-1
         * orderTime : 2019-10-29 13:23:50
         * rwdId : 71-240679
         * status : DPJ
         * statusName : 待评价
         */

        private int drwingType;
        private String groupId;
        private String groupName;
        private String imgName;
        private String orderNo;
        private String orderTime;
        private String rwdId;
        private String status;
        private String statusName;

        public int getDrwingType() {
            return drwingType;
        }

        public void setDrwingType(int drwingType) {
            this.drwingType = drwingType;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getImgName() {
            return imgName;
        }

        public void setImgName(String imgName) {
            this.imgName = imgName;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getRwdId() {
            return rwdId;
        }

        public void setRwdId(String rwdId) {
            this.rwdId = rwdId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
