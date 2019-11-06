package com.example.asus.customer.entity;

import java.util.List;

public class FaxianMessageBean {


    /**
     * StatusMsg : 获取评论列表成功!
     * StatusCode : 1
     * Body : [{"commentId":25,"newsId":37655,"userCard":"01012167","userName":"1114","content":"首先给大家介绍一下我们公司企业文化的相关内容，主要包括四个方面：公司的早午会、《瑞祥之歌》、瑞祥人准则、瑞眼详情。今天我们先来了解一下早午会，你准备好了吗？               ","delFlag":1,"praiseNum":0,"createDate":"2019-06-25","image":"","pushTimeStr":"2019-06-25"},{"commentId":24,"newsId":37655,"userCard":"01012167","userName":"1114","content":"<ul class=\" list-paddingleft-2\">     <li style=\"\">         <p>             <span style=\"text-indent: 24pt;\">&nbsp; &nbsp; &nbsp; 首先给大家介绍一下我们公司企业文化的相关内容，主要包括四个方面：公司的早午会、《瑞祥之歌》、瑞祥人准则、瑞眼详情。今天我们先来了解一下早午会<\/span><span style=\"text-indent: 24pt;\">，<\/span><span style=\"text-indent: 24pt;\">你准备好了吗？<\/span><br/>         <\/p>     <\/li> <\/ul>","delFlag":1,"praiseNum":0,"createDate":"2019-06-25","image":"","pushTimeStr":"2019-06-25"},{"commentId":23,"newsId":37655,"userCard":"01012167","userName":"1114","content":"<p>这是测试数据<\/p>","delFlag":1,"praiseNum":null,"createDate":"2019-06-25","image":"","pushTimeStr":"2019-06-25"},{"commentId":19,"newsId":37655,"userCard":"01012167","userName":"董录录","content":"?? ?? ","delFlag":1,"praiseNum":0,"createDate":"2019-06-14","image":"","pushTimeStr":"2019-06-14"},{"commentId":5,"newsId":37655,"userCard":"","userName":"董录录","content":"1111","delFlag":1,"praiseNum":0,"createDate":"2019-06-13","image":"https://tsharp.rxjy.com/static/images/comment/male_180.png","pushTimeStr":"2019-06-13"},{"commentId":4,"newsId":37655,"userCard":"","userName":"董录录","content":"111","delFlag":1,"praiseNum":0,"createDate":"2019-06-13","image":"https://tsharp.rxjy.com/static/images/comment/male_180.png","pushTimeStr":"2019-06-13"},{"commentId":9,"newsId":37655,"userCard":"01015991","userName":"陈怼怼","content":"测试","delFlag":1,"praiseNum":0,"createDate":"2019-06-13","image":"https://holding01.oss-cn-beijing.aliyuncs.com/2019/04/201904221152246771460032060.jpg","pushTimeStr":"2019-06-13"},{"commentId":8,"newsId":37655,"userCard":"01015991","userName":"陈怼怼","content":"测试","delFlag":1,"praiseNum":0,"createDate":"2019-06-13","image":"https://holding01.oss-cn-beijing.aliyuncs.com/2019/04/201904221152246771460032060.jpg","pushTimeStr":"2019-06-13"}]
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
         * commentId : 25
         * newsId : 37655
         * userCard : 01012167
         * userName : 1114
         * content :                                  首先给大家介绍一下我们公司企业文化的相关内容，主要包括四个方面：公司的早午会、《瑞祥之歌》、瑞祥人准则、瑞眼详情。今天我们先来了解一下早午会，你准备好了吗？
         * delFlag : 1
         * praiseNum : 0
         * createDate : 2019-06-25
         * image :
         * pushTimeStr : 2019-06-25
         */

        private int commentId;
        private int newsId;
        private String userCard;
        private String userName;
        private String content;
        private int delFlag;
        private int praiseNum;
        private String createDate;
        private String image;
        private String pushTimeStr;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public String getUserCard() {
            return userCard;
        }

        public void setUserCard(String userCard) {
            this.userCard = userCard;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPushTimeStr() {
            return pushTimeStr;
        }

        public void setPushTimeStr(String pushTimeStr) {
            this.pushTimeStr = pushTimeStr;
        }
    }
}
