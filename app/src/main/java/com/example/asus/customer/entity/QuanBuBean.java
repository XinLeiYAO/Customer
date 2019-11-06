package com.example.asus.customer.entity;

import java.util.List;

//
// 2019/6/13.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class QuanBuBean {

    /**
     * StatusMsg : 获取成功
     * StatusCode : 0
     * Body : [{"imgUrl":"https://imgcdn.wenes.cn/ProjectBusiness/Images/1569721759MYCCwnptR0p5GXNC7f5KCs3Z.png","imgName":"平面草图","groupName":"Janet概念设计院","orderNo":"114-754-CT-1","orderTime":"2019-09-17 09:58:49","statusName":"待审批","projectName":"同学都荟龙城荟室内装饰工程","drwingType":5},{"imgUrl":"https://imgcdn.wenes.cn/ProjectBusiness/Images/15697222946NjpAThNPzyAQRDt3KiWDinM.jpg","imgName":"分区构图","groupName":"Janet概念设计院","orderNo":"114-754-FQ-1","orderTime":"2019-09-17 09:58:10","statusName":"待审批","projectName":"同学都荟龙城荟室内装饰工程","drwingType":2},{"imgUrl":"https://imgcdn.wenes.cn/ProjectBusiness/Images/1569722177HhjK9SPszWp4bMPne5eDTXBi.jpg","imgName":"功能构图","groupName":"Janet概念设计院","orderNo":"114-754-GN-1","orderTime":"2019-09-17 09:58:23","statusName":"待审批","projectName":"同学都荟龙城荟室内装饰工程","drwingType":3},{"imgUrl":"https://imgcdn.wenes.cn/ProjectBusiness/Images/1569722152tyP3yrEy5edG7wShc40R8F1J.jpg","imgName":"色彩构图","groupName":"Janet概念设计院","orderNo":"114-754-SC-1","orderTime":"2019-09-17 09:58:35","statusName":"待审批","projectName":"同学都荟龙城荟室内装饰工程","drwingType":4},{"imgUrl":"https://imgcdn.wenes.cn/ProjectBusiness/Images/1569722501kes9jC3yna7mAFh1Ap1QQpRX.png","imgName":"原始构图","groupName":"Janet概念设计院","orderNo":"114-754-YS-1","orderTime":"2019-09-17 09:57:45","statusName":"待审批","projectName":"同学都荟龙城荟室内装饰工程","drwingType":1}]
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
         * imgUrl : https://imgcdn.wenes.cn/ProjectBusiness/Images/1569721759MYCCwnptR0p5GXNC7f5KCs3Z.png
         * imgName : 平面草图
         * groupName : Janet概念设计院
         * orderNo : 114-754-CT-1
         * orderTime : 2019-09-17 09:58:49
         * statusName : 待审批
         * projectName : 同学都荟龙城荟室内装饰工程
         * drwingType : 5
         */

        private String imgUrl;
        private String imgName;
        private String groupName;
        private String orderNo;
        private String orderTime;
        private String statusName;
        private String projectName;
        private String groupId;
        private String rwdId;
        private int drwingType;

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

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
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

        public String getRwdId() {
            return rwdId;
        }

        public void setRwdId(String rwdId) {
            this.rwdId = rwdId;
        }
    }
}
