package com.example.asus.customer.entity;

import java.util.List;

public class YuYueListBean {


    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"id":3,"phone":"18888319696","customerName":"测试","address":"北京市海淀区瑞祥装饰(永定河路南)","area":"1346","budget":"","cityName":"北京","cityId":12,"type":"公装","leiXingYi":"1","leiXingEr":"243","visitTime":"2019-10-18 18:28:59","remarks":"","drawing":null,"stage":"1","huXing":0,"leiXingYiName":"办公","leiXingErName":"网络公司","huXingName":null,"rwdId":null,"fengGe":"现代简约","xuQiuName":"休息/洽谈区,员工办公区经理室/老总室,休息/洽谈区","huXingImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571394608326.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571394608472.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571394555891.jpg","huanPaiImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571394556169.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571394556257.jpg","yuanDing":"1346","zhuLiang":"1616","ciLiang":"1667","dianXiangImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571394567100.jpg","shuiGuanImg":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571394567300.jpg","mode":"自己量房","juTiBuJu":null,"juTiBuJuYi":null,"juTiBuJuEr":null,"cengGao":null,"yuanShiImg":null,"wuDingImg":null,"qiangMian":null,"diMian":null,"dingMian":null,"loggedPersonPhone":"15093906856","jurisdiction":"有效","source":null,"createTime":"2019-10-18 18:29:32","updateTime":"2019-10-18 18:30:08","drawings":null,"huXingImgs":null,"huanPaiImgs":null,"dianXiangImgs":null,"shuiGuanImgs":null,"yuanShiImgs":null,"wuDingImgs":null,"xuQiuNames":null,"state":null,"longTime":null}]
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
         * id : 3
         * phone : 18888319696
         * customerName : 测试
         * address : 北京市海淀区瑞祥装饰(永定河路南)
         * area : 1346
         * budget :
         * cityName : 北京
         * cityId : 12
         * type : 公装
         * leiXingYi : 1
         * leiXingEr : 243
         * visitTime : 2019-10-18 18:28:59
         * remarks :
         * drawing : null
         * stage : 1
         * huXing : 0
         * leiXingYiName : 办公
         * leiXingErName : 网络公司
         * huXingName : null
         * rwdId : null
         * fengGe : 现代简约
         * xuQiuName : 休息/洽谈区,员工办公区经理室/老总室,休息/洽谈区
         * huXingImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571394608326.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571394608472.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571394555891.jpg
         * huanPaiImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571394556169.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571394556257.jpg
         * yuanDing : 1346
         * zhuLiang : 1616
         * ciLiang : 1667
         * dianXiangImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571394567100.jpg
         * shuiGuanImg : https://wenes01.oss-cn-beijing.aliyuncs.com/1571394567300.jpg
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
         * loggedPersonPhone : 15093906856
         * jurisdiction : 有效
         * source : null
         * createTime : 2019-10-18 18:29:32
         * updateTime : 2019-10-18 18:30:08
         * drawings : null
         * huXingImgs : null
         * huanPaiImgs : null
         * dianXiangImgs : null
         * shuiGuanImgs : null
         * yuanShiImgs : null
         * wuDingImgs : null
         * xuQiuNames : null
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
        private Object source;
        private String createTime;
        private String updateTime;
        private Object drawings;
        private Object huXingImgs;
        private Object huanPaiImgs;
        private Object dianXiangImgs;
        private Object shuiGuanImgs;
        private Object yuanShiImgs;
        private Object wuDingImgs;
        private Object xuQiuNames;
        private Object state;
        private Object longTime;
        private String siteText;
        private String siteId;
        private String repairType;
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

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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

        public Object getXuQiuNames() {
            return xuQiuNames;
        }

        public void setXuQiuNames(Object xuQiuNames) {
            this.xuQiuNames = xuQiuNames;
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

        public String getSiteText() {
            return siteText;
        }

        public void setSiteText(String siteText) {
            this.siteText = siteText;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getRepairType() {
            return repairType;
        }

        public void setRepairType(String repairType) {
            this.repairType = repairType;
        }
    }
}
