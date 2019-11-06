package com.example.asus.customer.entity;

import java.util.List;

public class HomeDataBean {

    /**
     * Body : {"bottomCarouse":[{"href":"www.tianmao.com","picId":3,"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572312991J0HHpDs84JFWtsAsn74Ew4yC.jpg"}],"pending":0,"processed":0,"topCarouse":[{"href":"www.666.com","picId":6,"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572259769njZ2sPsSkp27HrbZcKMQ4dsD.png"},{"href":"wwww","picId":5,"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572264303MrKip3pxcR3XCBP5E0HFxzQT.jpg"}]}
     * StatusCode : 1
     * StatusMsg : 成功
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
         * bottomCarouse : [{"href":"www.tianmao.com","picId":3,"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572312991J0HHpDs84JFWtsAsn74Ew4yC.jpg"}]
         * pending : 0
         * processed : 0
         * topCarouse : [{"href":"www.666.com","picId":6,"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572259769njZ2sPsSkp27HrbZcKMQ4dsD.png"},{"href":"wwww","picId":5,"picUrl":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572264303MrKip3pxcR3XCBP5E0HFxzQT.jpg"}]
         */

        private int pending;
        private int processed;
        private List<BottomCarouseBean> bottomCarouse;
        private List<TopCarouseBean> topCarouse;

        public int getPending() {
            return pending;
        }

        public void setPending(int pending) {
            this.pending = pending;
        }

        public int getProcessed() {
            return processed;
        }

        public void setProcessed(int processed) {
            this.processed = processed;
        }

        public List<BottomCarouseBean> getBottomCarouse() {
            return bottomCarouse;
        }

        public void setBottomCarouse(List<BottomCarouseBean> bottomCarouse) {
            this.bottomCarouse = bottomCarouse;
        }

        public List<TopCarouseBean> getTopCarouse() {
            return topCarouse;
        }

        public void setTopCarouse(List<TopCarouseBean> topCarouse) {
            this.topCarouse = topCarouse;
        }

        public static class BottomCarouseBean {
            /**
             * href : www.tianmao.com
             * picId : 3
             * picUrl : https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572312991J0HHpDs84JFWtsAsn74Ew4yC.jpg
             */

            private String href;
            private int picId;
            private String picUrl;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getPicId() {
                return picId;
            }

            public void setPicId(int picId) {
                this.picId = picId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }
        }

        public static class TopCarouseBean {
            /**
             * href : www.666.com
             * picId : 6
             * picUrl : https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1572259769njZ2sPsSkp27HrbZcKMQ4dsD.png
             */

            private String href;
            private int picId;
            private String picUrl;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getPicId() {
                return picId;
            }

            public void setPicId(int picId) {
                this.picId = picId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }
        }
    }
}
