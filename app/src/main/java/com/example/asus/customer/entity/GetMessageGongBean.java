package com.example.asus.customer.entity;

import java.util.List;

public class GetMessageGongBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"id":1,"phone":"18888319696","customerName":"孟先森","address":"中国科学院软件园区","area":"1300","budget":"","cityName":"北京","cityId":12,"type":"公装","leiXingYi":"1","leiXingEr":"243","visitTime":"2019-10-16 18:07:07","remarks":"","drawing":null,"stage":"1","huXing":0,"leiXingYiName":"办公","leiXingErName":"网络公司","huXingName":null,"rwdId":null,"fengGe":"后现代","xuQiuName":"休闲/娱乐区,会议/接待区,茶水间","huXingImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484589.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484865.jpg","huanPaiImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485007.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485259.jpg","yuanDing":"15000","zhuLiang":"1500","ciLiang":"150","dianXiangImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485340.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485407.jpg","shuiGuanImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485828.jpg","mode":"自己量房","juTiBuJu":null,"juTiBuJuYi":null,"juTiBuJuEr":null,"cengGao":null,"yuanShiImg":null,"wuDingImg":null,"qiangMian":null,"diMian":null,"dingMian":null,"loggedPersonPhone":"18888319696","jurisdiction":"有效","createTime":"2019-10-16 18:08:14","updateTime":null,"drawings":null,"huXingImgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484589.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484865.jpg"],"huanPaiImgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485007.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485259.jpg"],"dianXiangImgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485340.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485407.jpg"],"shuiGuanImgs":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485828.jpg"],"yuanShiImgs":null,"wuDingImgs":null,"xuQiuNames":["休闲/娱乐区","会议/接待区","茶水间"],"state":null,"longTime":null}
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
         * id : 1
         * phone : 18888319696
         * customerName : 孟先森
         * address : 中国科学院软件园区
         * area : 1300
         * budget :
         * cityName : 北京
         * cityId : 12
         * type : 公装
         * leiXingYi : 1
         * leiXingEr : 243
         * visitTime : 2019-10-16 18:07:07
         * remarks :
         * drawing : null
         * stage : 1
         * huXing : 0
         * leiXingYiName : 办公
         * leiXingErName : 网络公司
         * huXingName : null
         * rwdId : null
         * fengGe : 后现代
         * xuQiuName : 休闲/娱乐区,会议/接待区,茶水间
         * huXingImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484589.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484865.jpg
         * huanPaiImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485007.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485259.jpg
         * yuanDing : 15000
         * zhuLiang : 1500
         * ciLiang : 150
         * dianXiangImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485340.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485407.jpg
         * shuiGuanImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485828.jpg
         * mode : 自己量房
         * juTiBuJu : null
         * juTiBuJuYi : null
         * juTiBuJuEr : null
         * cengGao : null
         * yuanShiImg : null
         * wuDingImg : null
         * qiangMian : null
         * diMian : null
         * dingMian : null
         * loggedPersonPhone : 18888319696
         * jurisdiction : 有效
         * createTime : 2019-10-16 18:08:14
         * updateTime : null
         * drawings : null
         * huXingImgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484589.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571220484865.jpg"]
         * huanPaiImgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485007.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485259.jpg"]
         * dianXiangImgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485340.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485407.jpg"]
         * shuiGuanImgs : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571220485828.jpg"]
         * yuanShiImgs : null
         * wuDingImgs : null
         * xuQiuNames : ["休闲/娱乐区","会议/接待区","茶水间"]
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
        private Object huXingName;
        private Object rwdId;
        private String fengGe;
        private String xuQiuName;
        private String huXingImg;
        private String huanPaiImg;
        private String yuanDing;
        private String zhuLiang;
        private String ciLiang;
        private String dianXiangImg;
        private String shuiGuanImg;
        private String mode;
        private Object juTiBuJu;
        private Object juTiBuJuYi;
        private Object juTiBuJuEr;
        private Object cengGao;
        private Object yuanShiImg;
        private Object wuDingImg;
        private Object qiangMian;
        private Object diMian;
        private Object dingMian;
        private String loggedPersonPhone;
        private String jurisdiction;
        private String createTime;
        private Object updateTime;
        private Object drawings;
        private Object yuanShiImgs;
        private Object wuDingImgs;
        private Object state;
        private Object longTime;
        private List<String> huXingImgs;
        private List<String> huanPaiImgs;
        private List<String> dianXiangImgs;
        private List<String> shuiGuanImgs;
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

        public Object getHuXingName() {
            return huXingName;
        }

        public void setHuXingName(Object huXingName) {
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

        public String getHuXingImg() {
            return huXingImg;
        }

        public void setHuXingImg(String huXingImg) {
            this.huXingImg = huXingImg;
        }

        public String getHuanPaiImg() {
            return huanPaiImg;
        }

        public void setHuanPaiImg(String huanPaiImg) {
            this.huanPaiImg = huanPaiImg;
        }

        public String getYuanDing() {
            return yuanDing;
        }

        public void setYuanDing(String yuanDing) {
            this.yuanDing = yuanDing;
        }

        public String getZhuLiang() {
            return zhuLiang;
        }

        public void setZhuLiang(String zhuLiang) {
            this.zhuLiang = zhuLiang;
        }

        public String getCiLiang() {
            return ciLiang;
        }

        public void setCiLiang(String ciLiang) {
            this.ciLiang = ciLiang;
        }

        public String getDianXiangImg() {
            return dianXiangImg;
        }

        public void setDianXiangImg(String dianXiangImg) {
            this.dianXiangImg = dianXiangImg;
        }

        public String getShuiGuanImg() {
            return shuiGuanImg;
        }

        public void setShuiGuanImg(String shuiGuanImg) {
            this.shuiGuanImg = shuiGuanImg;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public Object getJuTiBuJu() {
            return juTiBuJu;
        }

        public void setJuTiBuJu(Object juTiBuJu) {
            this.juTiBuJu = juTiBuJu;
        }

        public Object getJuTiBuJuYi() {
            return juTiBuJuYi;
        }

        public void setJuTiBuJuYi(Object juTiBuJuYi) {
            this.juTiBuJuYi = juTiBuJuYi;
        }

        public Object getJuTiBuJuEr() {
            return juTiBuJuEr;
        }

        public void setJuTiBuJuEr(Object juTiBuJuEr) {
            this.juTiBuJuEr = juTiBuJuEr;
        }

        public Object getCengGao() {
            return cengGao;
        }

        public void setCengGao(Object cengGao) {
            this.cengGao = cengGao;
        }

        public Object getYuanShiImg() {
            return yuanShiImg;
        }

        public void setYuanShiImg(Object yuanShiImg) {
            this.yuanShiImg = yuanShiImg;
        }

        public Object getWuDingImg() {
            return wuDingImg;
        }

        public void setWuDingImg(Object wuDingImg) {
            this.wuDingImg = wuDingImg;
        }

        public Object getQiangMian() {
            return qiangMian;
        }

        public void setQiangMian(Object qiangMian) {
            this.qiangMian = qiangMian;
        }

        public Object getDiMian() {
            return diMian;
        }

        public void setDiMian(Object diMian) {
            this.diMian = diMian;
        }

        public Object getDingMian() {
            return dingMian;
        }

        public void setDingMian(Object dingMian) {
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

        public Object getYuanShiImgs() {
            return yuanShiImgs;
        }

        public void setYuanShiImgs(Object yuanShiImgs) {
            this.yuanShiImgs = yuanShiImgs;
        }

        public Object getWuDingImgs() {
            return wuDingImgs;
        }

        public void setWuDingImgs(Object wuDingImgs) {
            this.wuDingImgs = wuDingImgs;
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

        public List<String> getHuXingImgs() {
            return huXingImgs;
        }

        public void setHuXingImgs(List<String> huXingImgs) {
            this.huXingImgs = huXingImgs;
        }

        public List<String> getHuanPaiImgs() {
            return huanPaiImgs;
        }

        public void setHuanPaiImgs(List<String> huanPaiImgs) {
            this.huanPaiImgs = huanPaiImgs;
        }

        public List<String> getDianXiangImgs() {
            return dianXiangImgs;
        }

        public void setDianXiangImgs(List<String> dianXiangImgs) {
            this.dianXiangImgs = dianXiangImgs;
        }

        public List<String> getShuiGuanImgs() {
            return shuiGuanImgs;
        }

        public void setShuiGuanImgs(List<String> shuiGuanImgs) {
            this.shuiGuanImgs = shuiGuanImgs;
        }

        public List<String> getXuQiuNames() {
            return xuQiuNames;
        }

        public void setXuQiuNames(List<String> xuQiuNames) {
            this.xuQiuNames = xuQiuNames;
        }
    }
}
