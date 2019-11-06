package com.example.asus.customer.entity;

public class OrderMessageNewBean {

    /**
     * StatusMsg : 获取成功
     * StatusCode : 0
     * Body : {"submitter":"主案设计师","orderNo":"20-207105-S-2","rwdId":"20-207105","spTime":"2019-10-17 15:56:51","acceptor":null,"DO_Money":78.5,"approval":null,"groupId":249,"drawNum":0,"yjspTime":"2019-10-10 01:45:09","edition":"1571298955764","StatusName":"待审批","Creator":"史慧颖","tjTime":"2019-10-09 13:45:09","jdTime":"2019-09-29 12:59:10","DO_DrawingType":10,"ysTime":"","groupName":"JT施工设计所","DesignerName":"袁媛","xdTime":"2019-09-29 09:25:34","CreatorCard":"03710723","imageList":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282595CMWWKWsWSb225PphXGce42A7.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282597CFCQWF1QXSdcxkmXjF2RAb8n.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282600GJFDthGDS47W9W1yH0PtYf0H.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282604KZT4JB3d1FRwsYCZ0d964Hxf.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/15712826075MmE1fX3kfsCWNN1F5WkCnHT.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282595CMWWKWsWSb225PphXGce42A7.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282597CFCQWF1QXSdcxkmXjF2RAb8n.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282600GJFDthGDS47W9W1yH0PtYf0H.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282604KZT4JB3d1FRwsYCZ0d964Hxf.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/15712826075MmE1fX3kfsCWNN1F5WkCnHT.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282595CMWWKWsWSb225PphXGce42A7.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282597CFCQWF1QXSdcxkmXjF2RAb8n.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282600GJFDthGDS47W9W1yH0PtYf0H.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282604KZT4JB3d1FRwsYCZ0d964Hxf.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/15712826075MmE1fX3kfsCWNN1F5WkCnHT.jpg"}
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
         * submitter : 主案设计师
         * orderNo : 20-207105-S-2
         * rwdId : 20-207105
         * spTime : 2019-10-17 15:56:51
         * acceptor : null
         * DO_Money : 78.5
         * approval : null
         * groupId : 249
         * drawNum : 0
         * yjspTime : 2019-10-10 01:45:09
         * edition : 1571298955764
         * StatusName : 待审批
         * Creator : 史慧颖
         * tjTime : 2019-10-09 13:45:09
         * jdTime : 2019-09-29 12:59:10
         * DO_DrawingType : 10
         * ysTime :
         * groupName : JT施工设计所
         * DesignerName : 袁媛
         * xdTime : 2019-09-29 09:25:34
         * CreatorCard : 03710723
         * imageList : https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282595CMWWKWsWSb225PphXGce42A7.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282597CFCQWF1QXSdcxkmXjF2RAb8n.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282600GJFDthGDS47W9W1yH0PtYf0H.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282604KZT4JB3d1FRwsYCZ0d964Hxf.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/15712826075MmE1fX3kfsCWNN1F5WkCnHT.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282595CMWWKWsWSb225PphXGce42A7.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282597CFCQWF1QXSdcxkmXjF2RAb8n.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282600GJFDthGDS47W9W1yH0PtYf0H.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282604KZT4JB3d1FRwsYCZ0d964Hxf.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/15712826075MmE1fX3kfsCWNN1F5WkCnHT.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282595CMWWKWsWSb225PphXGce42A7.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282597CFCQWF1QXSdcxkmXjF2RAb8n.jpg,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282600GJFDthGDS47W9W1yH0PtYf0H.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/1571282604KZT4JB3d1FRwsYCZ0d964Hxf.png,https://rxjy-test.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/15712826075MmE1fX3kfsCWNN1F5WkCnHT.jpg
         */

        private String submitter;
        private String orderNo;
        private String rwdId;
        private String spTime;
        private Object acceptor;
        private double DO_Money;
        private Object approval;
        private int groupId;
        private int drawNum;
        private String yjspTime;
        private String edition;
        private String StatusName;
        private String Creator;
        private String tjTime;
        private String jdTime;
        private int DO_DrawingType;
        private String ysTime;
        private String groupName;
        private String DesignerName;
        private String xdTime;
        private String CreatorCard;
        private String imageList;

        public String getSubmitter() {
            return submitter;
        }

        public void setSubmitter(String submitter) {
            this.submitter = submitter;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getRwdId() {
            return rwdId;
        }

        public void setRwdId(String rwdId) {
            this.rwdId = rwdId;
        }

        public String getSpTime() {
            return spTime;
        }

        public void setSpTime(String spTime) {
            this.spTime = spTime;
        }

        public Object getAcceptor() {
            return acceptor;
        }

        public void setAcceptor(Object acceptor) {
            this.acceptor = acceptor;
        }

        public double getDO_Money() {
            return DO_Money;
        }

        public void setDO_Money(double DO_Money) {
            this.DO_Money = DO_Money;
        }

        public Object getApproval() {
            return approval;
        }

        public void setApproval(Object approval) {
            this.approval = approval;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getDrawNum() {
            return drawNum;
        }

        public void setDrawNum(int drawNum) {
            this.drawNum = drawNum;
        }

        public String getYjspTime() {
            return yjspTime;
        }

        public void setYjspTime(String yjspTime) {
            this.yjspTime = yjspTime;
        }

        public String getEdition() {
            return edition;
        }

        public void setEdition(String edition) {
            this.edition = edition;
        }

        public String getStatusName() {
            return StatusName;
        }

        public void setStatusName(String StatusName) {
            this.StatusName = StatusName;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getTjTime() {
            return tjTime;
        }

        public void setTjTime(String tjTime) {
            this.tjTime = tjTime;
        }

        public String getJdTime() {
            return jdTime;
        }

        public void setJdTime(String jdTime) {
            this.jdTime = jdTime;
        }

        public int getDO_DrawingType() {
            return DO_DrawingType;
        }

        public void setDO_DrawingType(int DO_DrawingType) {
            this.DO_DrawingType = DO_DrawingType;
        }

        public String getYsTime() {
            return ysTime;
        }

        public void setYsTime(String ysTime) {
            this.ysTime = ysTime;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getDesignerName() {
            return DesignerName;
        }

        public void setDesignerName(String DesignerName) {
            this.DesignerName = DesignerName;
        }

        public String getXdTime() {
            return xdTime;
        }

        public void setXdTime(String xdTime) {
            this.xdTime = xdTime;
        }

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
    }
}
