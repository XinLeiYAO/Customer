package com.example.asus.customer.entity;

import java.util.List;

public class WeixiuTypeListBean {

    /**
     * StatusMsg : 查询成功！
     * StatusCode : 1
     * Body : [{"id":489,"parentType":9,"childType":1,"typeValue":"防水补漏","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":490,"parentType":9,"childType":2,"typeValue":"墙面翻新","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":491,"parentType":9,"childType":3,"typeValue":"水电维修","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":492,"parentType":9,"childType":4,"typeValue":"瓷砖铺贴","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":493,"parentType":9,"childType":5,"typeValue":"管道疏通","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":494,"parentType":9,"childType":6,"typeValue":"门窗维修","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":495,"parentType":9,"childType":7,"typeValue":"专业打孔","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":496,"parentType":9,"childType":8,"typeValue":"家具维修","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":497,"parentType":9,"childType":9,"typeValue":"地板翻新","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":498,"parentType":9,"childType":10,"typeValue":"局部改造","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":499,"parentType":9,"childType":11,"typeValue":"整体翻新","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":500,"parentType":9,"childType":12,"typeValue":"装修拆除","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""},{"id":501,"parentType":9,"childType":13,"typeValue":"小修小补","delFlag":0,"createDate":"2019-11-01T08:29:14.000+0000","updateDate":"2019-11-01T08:29:14.000+0000","otherId":""}]
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
         * id : 489
         * parentType : 9
         * childType : 1
         * typeValue : 防水补漏
         * delFlag : 0
         * createDate : 2019-11-01T08:29:14.000+0000
         * updateDate : 2019-11-01T08:29:14.000+0000
         * otherId :
         */

        private int id;
        private int parentType;
        private int childType;
        private String typeValue;
        private int delFlag;
        private String createDate;
        private String updateDate;
        private String otherId;
        private int is;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentType() {
            return parentType;
        }

        public void setParentType(int parentType) {
            this.parentType = parentType;
        }

        public int getChildType() {
            return childType;
        }

        public void setChildType(int childType) {
            this.childType = childType;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getOtherId() {
            return otherId;
        }

        public void setOtherId(String otherId) {
            this.otherId = otherId;
        }

        public int getIs() {
            return is;
        }

        public void setIs(int is) {
            this.is = is;
        }
    }
}
