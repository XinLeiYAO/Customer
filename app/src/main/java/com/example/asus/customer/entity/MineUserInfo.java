package com.example.asus.customer.entity;

/**
 * Created by asus on 2018/4/19.
 */

public class MineUserInfo {

    /**
     * StatusMsg : 成功
     * StatusCode : 200
     * Body : {"name":"王婷婷","mavatar":"http://img0.wenes.cn/Upload/Cust/Ico/Image/2016/07/29/16452146173939.jpg","mstar":"5","ddeclaration":"设计源于生活，生活因设计而改变！","goodType":"餐饮","xueli":"大专","hobbys":"音乐"}
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
         * name : 王婷婷
         * mavatar : http://img0.wenes.cn/Upload/Cust/Ico/Image/2016/07/29/16452146173939.jpg
         * mstar : 5
         * ddeclaration : 设计源于生活，生活因设计而改变！
         * goodType : 餐饮
         * xueli : 大专
         * hobbys : 音乐
         */

        private String name;
        private String mavatar;
        private String mstar;
        private String ddeclaration;
        private String goodType;
        private String xueli;
        private String hobbys;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMavatar() {
            return mavatar;
        }

        public void setMavatar(String mavatar) {
            this.mavatar = mavatar;
        }

        public String getMstar() {
            return mstar;
        }

        public void setMstar(String mstar) {
            this.mstar = mstar;
        }

        public String getDdeclaration() {
            return ddeclaration;
        }

        public void setDdeclaration(String ddeclaration) {
            this.ddeclaration = ddeclaration;
        }

        public String getGoodType() {
            return goodType;
        }

        public void setGoodType(String goodType) {
            this.goodType = goodType;
        }

        public String getXueli() {
            return xueli;
        }

        public void setXueli(String xueli) {
            this.xueli = xueli;
        }

        public String getHobbys() {
            return hobbys;
        }

        public void setHobbys(String hobbys) {
            this.hobbys = hobbys;
        }
    }
}
