package com.example.asus.customer.entity;

import java.util.List;

public class GetMessageHomeBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"id":2,"phone":"18888888888","customerName":"孟先森","address":"碧桂园","area":"168","budget":"","cityName":"江北","cityId":142,"type":"家装","leiXingYi":"8","leiXingEr":"347","visitTime":"2019-10-16 18:58:44","remarks":"","drawing":null,"stage":"1","huXing":342,"leiXingYiName":"家装","leiXingErName":"新房","huXingName":"一居","rwdId":null,"fengGe":"现代简约","xuQiuName":"开放式厨房,次卧,餐厅,衣帽间","huXingImg":null,"huanPaiImg":null,"yuanDing":null,"zhuLiang":null,"ciLiang":null,"dianXiangImg":null,"shuiGuanImg":null,"mode":"自己量房","juTiBuJu":null,"juTiBuJuYi":null,"juTiBuJuEr":null,"cengGao":null,"yuanShiImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594441.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594781.jpg","wuDingImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595156.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595279.jpg","qiangMian":"乳胶漆","diMian":"木地板","dingMian":"是","loggedPersonPhone":"18888319696","jurisdiction":"有效","createTime":"2019-10-16 19:00:10","updateTime":null,"drawings":null,"huXingImgs":null,"huanPaiImgs":null,"dianXiangImgs":null,"shuiGuanImgs":null,"yuanShiImgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594441.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594781.jpg"],"wuDingImgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595156.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595279.jpg"],"xuQiuNames":["开放式厨房","次卧","餐厅","衣帽间"],"state":null,"longTime":null}
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
         * id : 2
         * phone : 18888888888
         * customerName : 孟先森
         * address : 碧桂园
         * area : 168
         * budget :
         * cityName : 江北
         * cityId : 142
         * type : 家装
         * leiXingYi : 8
         * leiXingEr : 347
         * visitTime : 2019-10-16 18:58:44
         * remarks :
         * drawing : null
         * stage : 1
         * huXing : 342
         * leiXingYiName : 家装
         * leiXingErName : 新房
         * huXingName : 一居
         * rwdId : null
         * fengGe : 现代简约
         * xuQiuName : 开放式厨房,次卧,餐厅,衣帽间
         * huXingImg : null
         * huanPaiImg : null
         * yuanDing : null
         * zhuLiang : null
         * ciLiang : null
         * dianXiangImg : null
         * shuiGuanImg : null
         * mode : 自己量房
         * juTiBuJu : null
         * juTiBuJuYi : null
         * juTiBuJuEr : null
         * cengGao : null
         * yuanShiImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594441.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594781.jpg
         * wuDingImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595156.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595279.jpg
         * qiangMian : 乳胶漆
         * diMian : 木地板
         * dingMian : 是
         * loggedPersonPhone : 18888319696
         * jurisdiction : 有效
         * createTime : 2019-10-16 19:00:10
         * updateTime : null
         * drawings : null
         * huXingImgs : null
         * huanPaiImgs : null
         * dianXiangImgs : null
         * shuiGuanImgs : null
         * yuanShiImgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594441.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571223594781.jpg"]
         * wuDingImgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595156.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571223595279.jpg"]
         * xuQiuNames : ["开放式厨房","次卧","餐厅","衣帽间"]
         * state : null
         * longTime : null
         */

        private int id;
        private String phone;
        private String customerName;
        private String address;
        private String area;
        private String budget;
        private String cityName;
        private int cityId;
        private String type;
        private String leiXingYi;
        private String leiXingEr;
        private String visitTime;
        private String remarks;
        private Object drawing;
        private String stage;
        private int huXing;
        private String leiXingYiName;
        private String leiXingErName;
        private String huXingName;
        private Object rwdId;
        private String fengGe;
        private String xuQiuName;
        private Object huXingImg;
        private Object huanPaiImg;
        private Object yuanDing;
        private Object zhuLiang;
        private Object ciLiang;
        private Object dianXiangImg;
        private Object shuiGuanImg;
        private String mode;
        private String juTiBuJu;
        private String juTiBuJuYi;
        private String juTiBuJuEr;
        private String cengGao;
        private String yuanShiImg;
        private String wuDingImg;
        private String qiangMian;
        private String diMian;
        private String dingMian;
        private String loggedPersonPhone;
        private String jurisdiction;
        private String createTime;
        private Object updateTime;
        private Object drawings;
        private Object huXingImgs;
        private Object huanPaiImgs;
        private Object dianXiangImgs;
        private Object shuiGuanImgs;
        private Object state;
        private Object longTime;
        private List<String> yuanShiImgs;
        private List<String> wuDingImgs;
        private List<String> xuQiuNames;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBudget() {
            return budget;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLeiXingYi() {
            return leiXingYi;
        }

        public void setLeiXingYi(String leiXingYi) {
            this.leiXingYi = leiXingYi;
        }

        public String getLeiXingEr() {
            return leiXingEr;
        }

        public void setLeiXingEr(String leiXingEr) {
            this.leiXingEr = leiXingEr;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Object getDrawing() {
            return drawing;
        }

        public void setDrawing(Object drawing) {
            this.drawing = drawing;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public int getHuXing() {
            return huXing;
        }

        public void setHuXing(int huXing) {
            this.huXing = huXing;
        }

        public String getLeiXingYiName() {
            return leiXingYiName;
        }

        public void setLeiXingYiName(String leiXingYiName) {
            this.leiXingYiName = leiXingYiName;
        }

        public String getLeiXingErName() {
            return leiXingErName;
        }

        public void setLeiXingErName(String leiXingErName) {
            this.leiXingErName = leiXingErName;
        }

        public String getHuXingName() {
            return huXingName;
        }

        public void setHuXingName(String huXingName) {
            this.huXingName = huXingName;
        }

        public Object getRwdId() {
            return rwdId;
        }

        public void setRwdId(Object rwdId) {
            this.rwdId = rwdId;
        }

        public String getFengGe() {
            return fengGe;
        }

        public void setFengGe(String fengGe) {
            this.fengGe = fengGe;
        }

        public String getXuQiuName() {
            return xuQiuName;
        }

        public void setXuQiuName(String xuQiuName) {
            this.xuQiuName = xuQiuName;
        }

        public Object getHuXingImg() {
            return huXingImg;
        }

        public void setHuXingImg(Object huXingImg) {
            this.huXingImg = huXingImg;
        }

        public Object getHuanPaiImg() {
            return huanPaiImg;
        }

        public void setHuanPaiImg(Object huanPaiImg) {
            this.huanPaiImg = huanPaiImg;
        }

        public Object getYuanDing() {
            return yuanDing;
        }

        public void setYuanDing(Object yuanDing) {
            this.yuanDing = yuanDing;
        }

        public Object getZhuLiang() {
            return zhuLiang;
        }

        public void setZhuLiang(Object zhuLiang) {
            this.zhuLiang = zhuLiang;
        }

        public Object getCiLiang() {
            return ciLiang;
        }

        public void setCiLiang(Object ciLiang) {
            this.ciLiang = ciLiang;
        }

        public Object getDianXiangImg() {
            return dianXiangImg;
        }

        public void setDianXiangImg(Object dianXiangImg) {
            this.dianXiangImg = dianXiangImg;
        }

        public Object getShuiGuanImg() {
            return shuiGuanImg;
        }

        public void setShuiGuanImg(Object shuiGuanImg) {
            this.shuiGuanImg = shuiGuanImg;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getJuTiBuJu() {
            return juTiBuJu;
        }

        public void setJuTiBuJu(String juTiBuJu) {
            this.juTiBuJu = juTiBuJu;
        }

        public String getJuTiBuJuYi() {
            return juTiBuJuYi;
        }

        public void setJuTiBuJuYi(String juTiBuJuYi) {
            this.juTiBuJuYi = juTiBuJuYi;
        }

        public String getJuTiBuJuEr() {
            return juTiBuJuEr;
        }

        public void setJuTiBuJuEr(String juTiBuJuEr) {
            this.juTiBuJuEr = juTiBuJuEr;
        }

        public String getCengGao() {
            return cengGao;
        }

        public void setCengGao(String cengGao) {
            this.cengGao = cengGao;
        }

        public String getYuanShiImg() {
            return yuanShiImg;
        }

        public void setYuanShiImg(String yuanShiImg) {
            this.yuanShiImg = yuanShiImg;
        }

        public String getWuDingImg() {
            return wuDingImg;
        }

        public void setWuDingImg(String wuDingImg) {
            this.wuDingImg = wuDingImg;
        }

        public String getQiangMian() {
            return qiangMian;
        }

        public void setQiangMian(String qiangMian) {
            this.qiangMian = qiangMian;
        }

        public String getDiMian() {
            return diMian;
        }

        public void setDiMian(String diMian) {
            this.diMian = diMian;
        }

        public String getDingMian() {
            return dingMian;
        }

        public void setDingMian(String dingMian) {
            this.dingMian = dingMian;
        }

        public String getLoggedPersonPhone() {
            return loggedPersonPhone;
        }

        public void setLoggedPersonPhone(String loggedPersonPhone) {
            this.loggedPersonPhone = loggedPersonPhone;
        }

        public String getJurisdiction() {
            return jurisdiction;
        }

        public void setJurisdiction(String jurisdiction) {
            this.jurisdiction = jurisdiction;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getDrawings() {
            return drawings;
        }

        public void setDrawings(Object drawings) {
            this.drawings = drawings;
        }

        public Object getHuXingImgs() {
            return huXingImgs;
        }

        public void setHuXingImgs(Object huXingImgs) {
            this.huXingImgs = huXingImgs;
        }

        public Object getHuanPaiImgs() {
            return huanPaiImgs;
        }

        public void setHuanPaiImgs(Object huanPaiImgs) {
            this.huanPaiImgs = huanPaiImgs;
        }

        public Object getDianXiangImgs() {
            return dianXiangImgs;
        }

        public void setDianXiangImgs(Object dianXiangImgs) {
            this.dianXiangImgs = dianXiangImgs;
        }

        public Object getShuiGuanImgs() {
            return shuiGuanImgs;
        }

        public void setShuiGuanImgs(Object shuiGuanImgs) {
            this.shuiGuanImgs = shuiGuanImgs;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getLongTime() {
            return longTime;
        }

        public void setLongTime(Object longTime) {
            this.longTime = longTime;
        }

        public List<String> getYuanShiImgs() {
            return yuanShiImgs;
        }

        public void setYuanShiImgs(List<String> yuanShiImgs) {
            this.yuanShiImgs = yuanShiImgs;
        }

        public List<String> getWuDingImgs() {
            return wuDingImgs;
        }

        public void setWuDingImgs(List<String> wuDingImgs) {
            this.wuDingImgs = wuDingImgs;
        }

        public List<String> getXuQiuNames() {
            return xuQiuNames;
        }

        public void setXuQiuNames(List<String> xuQiuNames) {
            this.xuQiuNames = xuQiuNames;
        }
    }
}
