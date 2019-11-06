package com.example.asus.customer.entity;

import java.util.List;

public class ShejiShiListBean {

    /**
     * Body : [{"bigType":"办公,教育","completionProjectCount":"12","image":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1566443022ibH64cpJJcjf0iJ8yfnR1Fzr.jpg","name":"黄磊","style":"工业风格","workYear":"1","ysGrade":"瑞祥佳艺·首席专家设计师"},{"bigType":"餐饮","completionProjectCount":"7","image":"https://imginvest.rxjy.com/rs/2273.jpg","name":"魏金玲","style":"时尚前卫","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"},{"bigType":"办公","completionProjectCount":"1","image":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1565079776FxkPWZQ4SZddFhBTGBisrGy3.jpg","name":"高亚芳","style":"现代简约","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"},{"bigType":"办公","completionProjectCount":"9","image":"https://imginvest.rxjy.com/rs/201901140957475693094096762.jpg","name":"陈鑫","style":"后现代","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"},{"bigType":"办公","completionProjectCount":"7","image":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1566268481QrJzXFdzQPcR53mk1y84A6Ax.jpg","name":"陈金雄","style":"现代简约","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"},{"bigType":"商业","completionProjectCount":"4","image":"https://imginvest.rxjy.com/rs/201908300940100083518822462.jpg","name":"陈祺","style":"现代简约","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"},{"bigType":"办公","completionProjectCount":"9","image":"https://imginvest.rxjy.com/rs/20190109175339239842372221.jpg","name":"陈留洋","style":"现代简约","workYear":"3","ysGrade":"瑞祥佳艺·专家设计师"},{"completionProjectCount":"2","image":"https://imginvest.rxjy.com/rs/1566007945965.jpg","name":"陈清鹏","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"},{"completionProjectCount":"0","image":"https://imginvest.rxjy.com/rs/201910231755479652742202762.jpg","name":"陈林","workYear":4,"ysGrade":"瑞祥佳艺·专家设计师"},{"bigType":"教育","completionProjectCount":"5","image":"https://imginvest.rxjy.com/rs/1558415660GJxRxGSKXF.jpg","name":"陈晶","style":"现代简约","workYear":4,"ysGrade":"瑞祥佳艺·高级专家设计师"}]
     * StatusCode : 1
     * StatusMsg : 获取成功
     */

    private String StatusCode;
    private String StatusMsg;
    private List<BodyBean> Body;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
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
         * bigType : 办公,教育
         * completionProjectCount : 12
         * image : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1566443022ibH64cpJJcjf0iJ8yfnR1Fzr.jpg
         * name : 黄磊
         * style : 工业风格
         * workYear : 1
         * ysGrade : 瑞祥佳艺·首席专家设计师
         */

        private String bigType;
        private String completionProjectCount;
        private String image;
        private String name;
        private String style;
        private String workYear;
        private String ysGrade;

        public String getBigType() {
            return bigType;
        }

        public void setBigType(String bigType) {
            this.bigType = bigType;
        }

        public String getCompletionProjectCount() {
            return completionProjectCount;
        }

        public void setCompletionProjectCount(String completionProjectCount) {
            this.completionProjectCount = completionProjectCount;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getWorkYear() {
            return workYear;
        }

        public void setWorkYear(String workYear) {
            this.workYear = workYear;
        }

        public String getYsGrade() {
            return ysGrade;
        }

        public void setYsGrade(String ysGrade) {
            this.ysGrade = ysGrade;
        }
    }
}
