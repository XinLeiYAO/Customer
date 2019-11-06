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
public class RightBean {
    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"oneName":"1","oneCode":"1","twoName":"水电","twoCode":"dss","url":"https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1571374909nAdp4NxnKJak5TYwsTWcTbGA.png","remarks":"","createTime":"2019-10-18 13:02:45","flag":0}]
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
         * oneName : 1
         * oneCode : 1
         * twoName : 水电
         * twoCode : dss
         * url : https://rxjy-test.oss-cn-beijing.aliyuncs.com/customer/Images/1571374909nAdp4NxnKJak5TYwsTWcTbGA.png
         * remarks :
         * createTime : 2019-10-18 13:02:45
         * flag : 0
         */

        private String oneName;
        private String oneCode;
        private String twoName;
        private String twoCode;
        private String url;
        private String remarks;
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

        public String getTwoName() {
            return twoName;
        }

        public void setTwoName(String twoName) {
            this.twoName = twoName;
        }

        public String getTwoCode() {
            return twoCode;
        }

        public void setTwoCode(String twoCode) {
            this.twoCode = twoCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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
