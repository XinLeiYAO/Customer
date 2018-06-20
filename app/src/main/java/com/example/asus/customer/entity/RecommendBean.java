package com.example.asus.customer.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus on 2018/5/29.
 */

public class RecommendBean implements Serializable {


    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : [{"ProjectName":"重庆江湖菜设计装修工程","MainUrl":"http://img0.wenes.cn/Upload/WenesImg/Image/2018/05/28/11185691997684.jpg","ProjectId":"00485518-FD3A-479A-9EE2-0F8EAF403105"}]
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

    public static class BodyBean implements Serializable {
        /**
         * ProjectName : 重庆江湖菜设计装修工程
         * MainUrl : http://img0.wenes.cn/Upload/WenesImg/Image/2018/05/28/11185691997684.jpg
         * ProjectId : 00485518-FD3A-479A-9EE2-0F8EAF403105
         */

        private String ProjectName;
        private String MainUrl;
        private String ProjectId;

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getMainUrl() {
            return MainUrl;
        }

        public void setMainUrl(String MainUrl) {
            this.MainUrl = MainUrl;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
        }
    }
}
