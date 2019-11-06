package com.example.asus.customer.entity;

import java.util.List;

//
// 2019/6/14.
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
public class MoreLeftBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"oneName":"量房","oneCode":"lf","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:51:25","flag":0},{"oneName":"方案","oneCode":"fa","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:52:39","flag":0},{"oneName":"预算","oneCode":"ys","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:52:54","flag":0},{"oneName":"签约","oneCode":"qy","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:53:05","flag":0},{"oneName":"审计","oneCode":"sj","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:53:29","flag":0},{"oneName":"准备","oneCode":"zb","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:53:39","flag":0},{"oneName":"再施","oneCode":"zs","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:53:51","flag":0},{"oneName":"竣工","oneCode":"jg","twoName":null,"twoCode":null,"url":"https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png","createTime":"2019-06-14 09:54:03","flag":0}]
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
         * oneName : 量房
         * oneCode : lf
         * twoName : null
         * twoCode : null
         * url : https://wenes01.oss-cn-beijing.aliyuncs.com/DesignInstitute/Images/156050498868F9DCt2JytKp8kdbmWydKnJ.png
         * createTime : 2019-06-14 09:51:25
         * flag : 0
         */

        private String oneName;
        private String oneCode;
        private Object twoName;
        private Object twoCode;
        private String url;
        private String createTime;
        private int flag;

        public String getOneName() {
            return oneName;
        }

        public void setOneName(String oneName) {
            this.oneName = oneName;
        }

        public String getOneCode() {
            return oneCode;
        }

        public void setOneCode(String oneCode) {
            this.oneCode = oneCode;
        }

        public Object getTwoName() {
            return twoName;
        }

        public void setTwoName(Object twoName) {
            this.twoName = twoName;
        }

        public Object getTwoCode() {
            return twoCode;
        }

        public void setTwoCode(Object twoCode) {
            this.twoCode = twoCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
