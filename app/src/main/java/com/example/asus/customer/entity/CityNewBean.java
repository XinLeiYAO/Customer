package com.example.asus.customer.entity;

import java.util.List;

public class CityNewBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"CityID":2,"FengGongSiID":12,"CityName":"北京"},{"CityID":3,"FengGongSiID":15,"CityName":"南京"},{"CityID":15,"FengGongSiID":38,"CityName":"上海"},{"CityID":6,"FengGongSiID":19,"CityName":"天津"},{"CityID":8,"FengGongSiID":21,"CityName":"重庆"},{"CityID":9,"FengGongSiID":22,"CityName":"成都"},{"CityID":5,"FengGongSiID":18,"CityName":"武汉"},{"CityID":11,"FengGongSiID":26,"CityName":"杭州"},{"CityID":14,"FengGongSiID":36,"CityName":"宁波"},{"CityID":4,"FengGongSiID":16,"CityName":"合肥"},{"CityID":7,"FengGongSiID":20,"CityName":"郑州"},{"CityID":12,"FengGongSiID":30,"CityName":"济南"},{"CityID":10,"FengGongSiID":25,"CityName":"西安"},{"CityID":13,"FengGongSiID":31,"CityName":"石家庄"},{"CityID":13,"FengGongSiID":68,"CityName":"唐山"},{"CityID":3,"FengGongSiID":62,"CityName":"江宁"},{"CityID":2,"FengGongSiID":11,"CityName":"R6"},{"CityID":2,"FengGongSiID":55,"CityName":"CBD"},{"CityID":2,"FengGongSiID":49,"CityName":"望京"},{"CityID":5,"FengGongSiID":61,"CityName":"光谷"},{"CityID":5,"FengGongSiID":75,"CityName":"汉阳"},{"CityID":5,"FengGongSiID":76,"CityName":"青山"},{"CityID":10,"FengGongSiID":65,"CityName":"雁塔"},{"CityID":2,"FengGongSiID":69,"CityName":"朝阳"},{"CityID":4,"FengGongSiID":80,"CityName":"安徽"},{"CityID":15,"FengGongSiID":70,"CityName":"普陀"},{"CityID":7,"FengGongSiID":78,"CityName":"河南"},{"CityID":3,"FengGongSiID":94,"CityName":"常州"},{"CityID":11,"FengGongSiID":72,"CityName":"下城"},{"CityID":12,"FengGongSiID":73,"CityName":"烟台"},{"CityID":7,"FengGongSiID":74,"CityName":"焦作"},{"CityID":126,"FengGongSiID":77,"CityName":"张家口"},{"CityID":5,"FengGongSiID":86,"CityName":"武昌"},{"CityID":3,"FengGongSiID":88,"CityName":"龙江"},{"CityID":5,"FengGongSiID":89,"CityName":"汉口"},{"CityID":3,"FengGongSiID":93,"CityName":"苏州"},{"CityID":6,"FengGongSiID":95,"CityName":"西青"},{"CityID":13,"FengGongSiID":97,"CityName":"裕华"},{"CityID":10,"FengGongSiID":98,"CityName":"长安"},{"CityID":8,"FengGongSiID":103,"CityName":"川渝"},{"CityID":12,"FengGongSiID":105,"CityName":"槐荫"},{"CityID":23,"FengGongSiID":87,"CityName":"岳麓"},{"CityID":16,"FengGongSiID":104,"CityName":"辽宁"},{"CityID":11,"FengGongSiID":108,"CityName":"滨江"},{"CityID":11,"FengGongSiID":107,"CityName":"金城"},{"CityID":14,"FengGongSiID":109,"CityName":"鄞州"},{"CityID":17,"FengGongSiID":114,"CityName":"深圳"},{"CityID":7,"FengGongSiID":111,"CityName":"金水"},{"CityID":4,"FengGongSiID":118,"CityName":"包河"},{"CityID":6,"FengGongSiID":122,"CityName":"河西"},{"CityID":6,"FengGongSiID":117,"CityName":"北辰"},{"CityID":2,"FengGongSiID":120,"CityName":"R8"},{"CityID":10,"FengGongSiID":110,"CityName":"高新"},{"CityID":3,"FengGongSiID":119,"CityName":"金陵"},{"CityID":9,"FengGongSiID":129,"CityName":"川一"},{"CityID":10,"FengGongSiID":99,"CityName":"未央"},{"CityID":9,"FengGongSiID":100,"CityName":"温江"},{"CityID":4,"FengGongSiID":121,"CityName":"蜀山"},{"CityID":15,"FengGongSiID":133,"CityName":"静安"},{"CityID":10,"FengGongSiID":137,"CityName":"华清"},{"CityID":9,"FengGongSiID":139,"CityName":"川二"},{"CityID":11,"FengGongSiID":138,"CityName":"萧山"},{"CityID":2,"FengGongSiID":140,"CityName":"通州"},{"CityID":2,"FengGongSiID":141,"CityName":"瑞物"},{"CityID":8,"FengGongSiID":142,"CityName":"江北"},{"CityID":12,"FengGongSiID":143,"CityName":"R1"}]
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
         * CityID : 2
         * FengGongSiID : 12
         * CityName : 北京
         */

        private int CityID;
        private int FengGongSiID;
        private String CityName;

        public int getCityID() {
            return CityID;
        }

        public void setCityID(int CityID) {
            this.CityID = CityID;
        }

        public int getFengGongSiID() {
            return FengGongSiID;
        }

        public void setFengGongSiID(int FengGongSiID) {
            this.FengGongSiID = FengGongSiID;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }
    }
}
