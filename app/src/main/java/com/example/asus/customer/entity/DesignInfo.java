package com.example.asus.customer.entity;

/**
 * Created by asus on 2018/4/24.
 */

public class DesignInfo {

    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : {"ProjectBrief":"本项目是做链条及齿轮的办公项目，最大的问题就是把他们的储物给合理的储存起来，办公空间进行合理的划分，二层的住宅要完全的分离。首先我把办公区域做了一个太高处理，因为层高比较高，下边1.8米的位置做储物，二层做办公，有效的利用空间，卫生间和库房单独分开独立，并设立的独立的会议室，每个空间各司其职又相互关联。","NegotiationFocus":null}
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
         * ProjectBrief : 本项目是做链条及齿轮的办公项目，最大的问题就是把他们的储物给合理的储存起来，办公空间进行合理的划分，二层的住宅要完全的分离。首先我把办公区域做了一个太高处理，因为层高比较高，下边1.8米的位置做储物，二层做办公，有效的利用空间，卫生间和库房单独分开独立，并设立的独立的会议室，每个空间各司其职又相互关联。
         * NegotiationFocus : null
         */

        private String ProjectBrief;
        private Object NegotiationFocus;

        public String getProjectBrief() {
            return ProjectBrief;
        }

        public void setProjectBrief(String ProjectBrief) {
            this.ProjectBrief = ProjectBrief;
        }

        public Object getNegotiationFocus() {
            return NegotiationFocus;
        }

        public void setNegotiationFocus(Object NegotiationFocus) {
            this.NegotiationFocus = NegotiationFocus;
        }
    }
}
