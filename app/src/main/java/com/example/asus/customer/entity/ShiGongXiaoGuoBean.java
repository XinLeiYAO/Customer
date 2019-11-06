package com.example.asus.customer.entity;

import java.util.List;

public class ShiGongXiaoGuoBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557901788ZjMpR4mJTB6G04YTdSCh8ea2.jpg","orderNo":"15-225141-D-1","imgId":1,"rwdId":"15-225141","createTime":"2019-05-15 14:30:12","classificationName":"电器布线图","imgType_code":"sdt","id":5888,"imgType":"水电图"},{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557901794NEnKTp49bbpfPGGM7yMh9rSf.jpg","orderNo":"15-225141-D-1","imgId":2,"rwdId":"15-225141","createTime":"2019-05-15 14:30:12","classificationName":"电器系统图","imgType_code":"sdt","id":5889,"imgType":"水电图"},{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/15579017977nXdb5EdREhp68rADJasyMDf.jpg","orderNo":"15-225141-D-1","imgId":3,"rwdId":"15-225141","createTime":"2019-05-15 14:30:12","classificationName":"强电布线图","imgType_code":"sdt","id":5890,"imgType":"水电图"},{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557901802F4BxFGfP4WGz1xHNy4nmDxJP.jpg","orderNo":"15-225141-D-1","imgId":5,"rwdId":"15-225141","createTime":"2019-05-15 14:30:12","classificationName":"应急灯具图","imgType_code":"sdt","id":5891,"imgType":"水电图"},{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557901805tbhAK4Kmdin3HNsjyF7jNwNt.jpg","orderNo":"15-225141-D-1","imgId":6,"rwdId":"15-225141","createTime":"2019-05-15 14:30:12","classificationName":"安全疏散图","imgType_code":"sdt","id":5892,"imgType":"水电图"}]
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
         * imgUrl : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557901788ZjMpR4mJTB6G04YTdSCh8ea2.jpg
         * orderNo : 15-225141-D-1
         * imgId : 1
         * rwdId : 15-225141
         * createTime : 2019-05-15 14:30:12
         * classificationName : 电器布线图
         * imgType_code : sdt
         * id : 5888
         * imgType : 水电图
         */

        private String imgUrl;
        private String orderNo;
        private int imgId;
        private String rwdId;
        private String createTime;
        private String classificationName;
        private String imgType_code;
        private int id;
        private String imgType;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public String getRwdId() {
            return rwdId;
        }

        public void setRwdId(String rwdId) {
            this.rwdId = rwdId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getClassificationName() {
            return classificationName;
        }

        public void setClassificationName(String classificationName) {
            this.classificationName = classificationName;
        }

        public String getImgType_code() {
            return imgType_code;
        }

        public void setImgType_code(String imgType_code) {
            this.imgType_code = imgType_code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgType() {
            return imgType;
        }

        public void setImgType(String imgType) {
            this.imgType = imgType;
        }
    }
}
