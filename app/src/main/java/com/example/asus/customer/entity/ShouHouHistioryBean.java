package com.example.asus.customer.entity;

import java.util.List;

//
// 2019/5/31.
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
public class ShouHouHistioryBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1559267411305.jpg","replyTime":null,"imgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1559267411305.jpg"],"rwdid":"117-206","createTime":"2019-05-31 09:50:13","id":2,"replyContent":null,"reply":null,"feedbackType":"SH","content":"[{\"name\":\"服务商的态度，沟通\",\"content\":\"测试\"}]"}]
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
         * imgUrl : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1559267411305.jpg
         * replyTime : null
         * imgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1559267411305.jpg"]
         * rwdid : 117-206
         * createTime : 2019-05-31 09:50:13
         * id : 2
         * replyContent : null
         * reply : null
         * feedbackType : SH
         * content : [{"name":"服务商的态度，沟通","content":"测试"}]
         */

        private String imgUrl;
        private Object replyTime;
        private String rwdid;
        private String createTime;
        private int id;
        private Object replyContent;
        private Object reply;
        private String feedbackType;
        private String content;
        private List<String> imgs;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Object getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(Object replyTime) {
            this.replyTime = replyTime;
        }

        public String getRwdid() {
            return rwdid;
        }

        public void setRwdid(String rwdid) {
            this.rwdid = rwdid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(Object replyContent) {
            this.replyContent = replyContent;
        }

        public Object getReply() {
            return reply;
        }

        public void setReply(Object reply) {
            this.reply = reply;
        }

        public String getFeedbackType() {
            return feedbackType;
        }

        public void setFeedbackType(String feedbackType) {
            this.feedbackType = feedbackType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
