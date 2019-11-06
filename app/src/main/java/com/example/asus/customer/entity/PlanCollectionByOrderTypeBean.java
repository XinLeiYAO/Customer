package com.example.asus.customer.entity;

import java.util.List;

public class PlanCollectionByOrderTypeBean {

    /**
     * StatusMsg : 获取成功
     * StatusCode : 0
     * Body : [{"imgUrl":"","imgName":"水电图","groupName":"邢工施工设计工作室","orderNo":"18-234088-D-1","statusName":"待分单","projectName":"水电图","drwingType":"11","status":"s_dfd"}]
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
         * imgUrl :
         * imgName : 水电图
         * groupName : 邢工施工设计工作室
         * orderNo : 18-234088-D-1
         * statusName : 待分单
         * projectName : 水电图
         * drwingType : 11
         * status : s_dfd
         */

        private String imgUrl;
        private String imgName;
        private String groupName;
        private String orderNo;
        private String statusName;
        private String projectName;
        private String drwingType;
        private String status;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgName() {
            return imgName;
        }

        public void setImgName(String imgName) {
            this.imgName = imgName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getDrwingType() {
            return drwingType;
        }

        public void setDrwingType(String drwingType) {
            this.drwingType = drwingType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
