package com.example.asus.customer.entity;

import java.util.List;

public class QiYeBean {


    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"ProjectBrief":"乐高的教育理念是做中学，玩中学。就是让孩子们在自己喜欢的活动过程中提高她们的学习兴趣，增强动手能力，培养各方面的技能。在乐高的课堂上孩子们没有拘束，是主动学习者而老师只是一个引导师和顾问的角色，和孩子们一起讨论或者互动，调动起孩子学习的积极性。","CustomerState":1,"DetailID":"618196","filePath":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/201811011739316591423885650.jpg","CreateTime":"2018-11-01 17:36:21","CatalogName":"公司LOGO","CatalogID":"116"},{"ProjectBrief":"乐高的教育理念是做中学，玩中学。就是让孩子们在自己喜欢的活动过程中提高她们的学习兴趣，增强动手能力，培养各方面的技能。在乐高的课堂上孩子们没有拘束，是主动学习者而老师只是一个引导师和顾问的角色，和孩子们一起讨论或者互动，调动起孩子学习的积极性。","CustomerState":1,"DetailID":"618203","filePath":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/201811011740457852922706660.jpg","CreateTime":"2018-11-01 17:37:27","CatalogName":"高层景观","CatalogID":"152"}]
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
         * ProjectBrief : 乐高的教育理念是做中学，玩中学。就是让孩子们在自己喜欢的活动过程中提高她们的学习兴趣，增强动手能力，培养各方面的技能。在乐高的课堂上孩子们没有拘束，是主动学习者而老师只是一个引导师和顾问的角色，和孩子们一起讨论或者互动，调动起孩子学习的积极性。
         * CustomerState : 1
         * DetailID : 618196
         * filePath : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/201811011739316591423885650.jpg
         * CreateTime : 2018-11-01 17:36:21
         * CatalogName : 公司LOGO
         * CatalogID : 116
         */

        private String ProjectBrief;
        private int CustomerState;
        private String DetailID;
        private String filePath;
        private String CreateTime;
        private String CatalogName;
        private String CatalogID;

        public String getProjectBrief() {
            return ProjectBrief;
        }

        public void setProjectBrief(String ProjectBrief) {
            this.ProjectBrief = ProjectBrief;
        }

        public int getCustomerState() {
            return CustomerState;
        }

        public void setCustomerState(int CustomerState) {
            this.CustomerState = CustomerState;
        }

        public String getDetailID() {
            return DetailID;
        }

        public void setDetailID(String DetailID) {
            this.DetailID = DetailID;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCatalogName() {
            return CatalogName;
        }

        public void setCatalogName(String CatalogName) {
            this.CatalogName = CatalogName;
        }

        public String getCatalogID() {
            return CatalogID;
        }

        public void setCatalogID(String CatalogID) {
            this.CatalogID = CatalogID;
        }
    }
}
