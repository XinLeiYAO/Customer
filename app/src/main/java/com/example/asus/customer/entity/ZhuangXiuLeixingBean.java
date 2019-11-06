package com.example.asus.customer.entity;

import java.util.List;

public class ZhuangXiuLeixingBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"MingCheng":"办公","ziji":[{"MingCheng":"网络公司","ID":243,"FuID":"1"},{"MingCheng":"科技公司","ID":244,"FuID":"1"},{"MingCheng":"金融投资","ID":245,"FuID":"1"},{"MingCheng":"贸易公司","ID":246,"FuID":"1"},{"MingCheng":"政府机构","ID":247,"FuID":"1"},{"MingCheng":"房地产","ID":248,"FuID":"1"},{"MingCheng":"工业","ID":249,"FuID":"1"},{"MingCheng":"广告传媒","ID":250,"FuID":"1"},{"MingCheng":"咨询服务","ID":251,"FuID":"1"},{"MingCheng":"生物医药","ID":252,"FuID":"1"}],"ID":1,"FuID":"0"},{"MingCheng":"餐饮","ziji":[{"MingCheng":"中餐厅","ID":19,"FuID":"2"},{"MingCheng":"西餐厅","ID":20,"FuID":"2"},{"MingCheng":"快餐厅","ID":21,"FuID":"2"},{"MingCheng":"主题餐厅","ID":22,"FuID":"2"},{"MingCheng":"茶餐厅","ID":23,"FuID":"2"},{"MingCheng":"火锅店","ID":24,"FuID":"2"},{"MingCheng":"自助餐厅","ID":25,"FuID":"2"},{"MingCheng":"烧烤店","ID":26,"FuID":"2"},{"MingCheng":"咖啡厅","ID":83,"FuID":"2"},{"MingCheng":"日式料理","ID":84,"FuID":"2"},{"MingCheng":"韩式料理","ID":253,"FuID":"2"},{"MingCheng":"泰式餐厅","ID":254,"FuID":"2"}],"ID":2,"FuID":"0"},{"MingCheng":"商业","ziji":[{"MingCheng":"美容整形","ID":27,"FuID":"3"},{"MingCheng":"商业店铺","ID":28,"FuID":"3"},{"MingCheng":"网咖","ID":31,"FuID":"3"},{"MingCheng":"展厅展区","ID":85,"FuID":"3"},{"MingCheng":"摄影","ID":87,"FuID":"3"},{"MingCheng":"超市","ID":189,"FuID":"3"},{"MingCheng":"茶楼","ID":191,"FuID":"3"},{"MingCheng":"宠物店","ID":220,"FuID":"3"},{"MingCheng":"书店","ID":255,"FuID":"3"},{"MingCheng":"健身馆","ID":256,"FuID":"3"},{"MingCheng":"瑜伽馆","ID":257,"FuID":"3"},{"MingCheng":"俱乐部","ID":258,"FuID":"3"},{"MingCheng":"轰趴馆","ID":259,"FuID":"3"},{"MingCheng":"4S店","ID":260,"FuID":"3"},{"MingCheng":"美发","ID":261,"FuID":"3"},{"MingCheng":"门诊","ID":262,"FuID":"3"}],"ID":3,"FuID":"0"},{"MingCheng":"酒店","ziji":[{"MingCheng":"商务酒店","ID":34,"FuID":"4"},{"MingCheng":"主题酒店","ID":37,"FuID":"4"},{"MingCheng":"快捷酒店","ID":38,"FuID":"4"},{"MingCheng":"公寓酒店","ID":41,"FuID":"4"}],"ID":4,"FuID":"0"},{"MingCheng":"教育","ziji":[{"MingCheng":"中小学机构","ID":263,"FuID":"6"},{"MingCheng":"机器人培训","ID":264,"FuID":"6"},{"MingCheng":"网络教育","ID":265,"FuID":"6"},{"MingCheng":"英语培训","ID":266,"FuID":"6"},{"MingCheng":"艺术培训","ID":267,"FuID":"6"},{"MingCheng":"技能培训","ID":268,"FuID":"6"},{"MingCheng":"留学机构","ID":269,"FuID":"6"},{"MingCheng":"早教机构","ID":270,"FuID":"6"},{"MingCheng":"幼儿园","ID":271,"FuID":"6"}],"ID":6,"FuID":"0"},{"MingCheng":"会所","ziji":[{"MingCheng":"私人会所","ID":272,"FuID":"7"},{"MingCheng":"足浴会所","ID":273,"FuID":"7"},{"MingCheng":"养生会所","ID":274,"FuID":"7"},{"MingCheng":"月子会所","ID":275,"FuID":"7"},{"MingCheng":"私人影吧","ID":276,"FuID":"7"}],"ID":7,"FuID":"0"}]
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
         * MingCheng : 办公
         * ziji : [{"MingCheng":"网络公司","ID":243,"FuID":"1"},{"MingCheng":"科技公司","ID":244,"FuID":"1"},{"MingCheng":"金融投资","ID":245,"FuID":"1"},{"MingCheng":"贸易公司","ID":246,"FuID":"1"},{"MingCheng":"政府机构","ID":247,"FuID":"1"},{"MingCheng":"房地产","ID":248,"FuID":"1"},{"MingCheng":"工业","ID":249,"FuID":"1"},{"MingCheng":"广告传媒","ID":250,"FuID":"1"},{"MingCheng":"咨询服务","ID":251,"FuID":"1"},{"MingCheng":"生物医药","ID":252,"FuID":"1"}]
         * ID : 1
         * FuID : 0
         */

        private String MingCheng;
        private int ID;
        private String FuID;
        private List<ZijiBean> ziji;

        public String getMingCheng() {
            return MingCheng;
        }

        public void setMingCheng(String MingCheng) {
            this.MingCheng = MingCheng;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getFuID() {
            return FuID;
        }

        public void setFuID(String FuID) {
            this.FuID = FuID;
        }

        public List<ZijiBean> getZiji() {
            return ziji;
        }

        public void setZiji(List<ZijiBean> ziji) {
            this.ziji = ziji;
        }

        public static class ZijiBean {
            /**
             * MingCheng : 网络公司
             * ID : 243
             * FuID : 1
             */

            private String MingCheng;
            private int ID;
            private String FuID;

            public String getMingCheng() {
                return MingCheng;
            }

            public void setMingCheng(String MingCheng) {
                this.MingCheng = MingCheng;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getFuID() {
                return FuID;
            }

            public void setFuID(String FuID) {
                this.FuID = FuID;
            }
        }
    }
}
