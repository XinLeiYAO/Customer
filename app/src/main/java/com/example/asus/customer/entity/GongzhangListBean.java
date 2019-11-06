package com.example.asus.customer.entity;

import java.util.List;

public class GongzhangListBean {

    /**
     * StatusMsg : success
     * StatusCode : 1
     * Body : [{"uid":4995,"userName":"戴天赐","dicname":"办公","grade":"5.0","gradeNum":2,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1569388004cPdRDE54sH.jpg","level":4},{"uid":4990,"userName":"潘路军","dicname":"办公","grade":"5.0","gradeNum":3,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1569314713yjK7X4PNy2.jpg","level":4},{"uid":4989,"userName":"杨德志","dicname":"办公","grade":"5.0","gradeNum":0,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/15692896962AAfF5imzd.jpg","level":4},{"uid":4987,"userName":"聂盈辉","dicname":"办公","grade":"5.0","gradeNum":3,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1569225317tmD8MnjAYb.jpg","level":4},{"uid":4986,"userName":"李佳才","dicname":"餐饮","grade":"5.0","gradeNum":5,"image":"https://holding01.oss-cn-beijing.aliyuncs.com/2019924173129741137186.jpg","level":4},{"uid":4985,"userName":"李双亮","dicname":"办公","grade":"5.0","gradeNum":2,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1568780571XiMDjhAPGG.jpg","level":4},{"uid":4984,"userName":"张国平","dicname":"餐饮","grade":"5.0","gradeNum":3,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1568698980cw4WZhMbXP.jpg","level":4},{"uid":4983,"userName":"赵俊","dicname":"办公","grade":"5.0","gradeNum":4,"image":"https://holding01.oss-cn-beijing.aliyuncs.com/2019919183716857218736.jpg","level":4},{"uid":4981,"userName":"罗同祖","dicname":"办公","grade":"5.0","gradeNum":2,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1568170855C43BWeHC6X.jpg","level":4},{"uid":4975,"userName":"毛法中","dicname":"办公","grade":"5.0","gradeNum":3,"image":"https://proj01.oss-cn-beijing.aliyuncs.com/common/1568097670crERR7S2tn.jpg","level":4}]
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
         * uid : 4995
         * userName : 戴天赐
         * dicname : 办公
         * grade : 5.0
         * gradeNum : 2
         * image : https://proj01.oss-cn-beijing.aliyuncs.com/common/1569388004cPdRDE54sH.jpg
         * level : 4
         */

        private int uid;
        private String userName;
        private String dicname;
        private String grade;
        private int gradeNum;
        private String image;
        private int level;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDicname() {
            return dicname;
        }

        public void setDicname(String dicname) {
            this.dicname = dicname;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getGradeNum() {
            return gradeNum;
        }

        public void setGradeNum(int gradeNum) {
            this.gradeNum = gradeNum;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
