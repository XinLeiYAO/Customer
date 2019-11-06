package com.example.asus.customer.entity;

import java.util.List;

public class GetYuYueMessageBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"id":3,"phone":"188883319696","customerName":"啊哈哈哈","address":"顺时针","area":"100","budget":"","cityName":"西安","cityId":25,"type":"家装","leiXingYi":"8","leiXingEr":"347","visitTime":"2019-10-16 13:35:33","remarks":"啦啦啦德玛西亚","drawing":"https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159375.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159631.jpg","stage":null,"huXing":342,"leiXingYiName":"家装","leiXingErName":"新房","huXingName":"一居","rwdId":null,"xuQiuName":null,"huXingImg":null,"huanPaiImg":null,"yuanDing":null,"zhuLiang":null,"ciLiang":null,"dianXiangImg":null,"shuiGuanImg":null,"mode":"上门量房","juTiBuJu":null,"juTiBuJuYi":null,"juTiBuJuEr":null,"cengGao":null,"yuanShiImg":null,"wuDingImg":null,"qiangMian":null,"diMian":null,"dingMian":null,"loggedPersonPhone":"18888319696","createTime":"2019-10-16 13:36:04","updateTime":null,"drawings":["https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159375.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159631.jpg"],"huXingImgs":null,"huanPaiImgs":null,"dianXiangImgs":null,"shuiGuanImgs":null,"yuanShiImgs":null,"wuDingImgs":null,"xuQiuNames":null}
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
         * id : 3
         * phone : 188883319696
         * customerName : 啊哈哈哈
         * address : 顺时针
         * area : 100
         * budget :
         * cityName : 西安
         * cityId : 25
         * type : 家装
         * leiXingYi : 8
         * leiXingEr : 347
         * visitTime : 2019-10-16 13:35:33
         * remarks : 啦啦啦德玛西亚
         * drawing : https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159375.jpg,https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159631.jpg
         * stage : null
         * huXing : 342
         * leiXingYiName : 家装
         * leiXingErName : 新房
         * huXingName : 一居
         * rwdId : null
         * xuQiuName : null
         * huXingImg : null
         * huanPaiImg : null
         * yuanDing : null
         * zhuLiang : null
         * ciLiang : null
         * dianXiangImg : null
         * shuiGuanImg : null
         * mode : 上门量房
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
         * createTime : 2019-10-16 13:36:04
         * updateTime : null
         * drawings : ["https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159375.jpg","https://wenes01.oss-cn-beijing.aliyuncs.com/1571204159631.jpg"]
         * huXingImgs : null
         * huanPaiImgs : null
         * dianXiangImgs : null
         * shuiGuanImgs : null
         * yuanShiImgs : null
         * wuDingImgs : null
         * xuQiuNames : null
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
        private String drawing;
        private Object stage;
        private int huXing;
        private String leiXingYiName;
        private String leiXingErName;
        private String huXingName;
        private Object rwdId;
        private Object xuQiuName;
        private Object huXingImg;
        private Object huanPaiImg;
        private Object yuanDing;
        private Object zhuLiang;
        private Object ciLiang;
        private Object dianXiangImg;
        private Object shuiGuanImg;
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
        private String createTime;
        private String repairType;
        private Object updateTime;
        private Object huXingImgs;
        private Object huanPaiImgs;
        private Object dianXiangImgs;
        private Object shuiGuanImgs;
        private Object yuanShiImgs;
        private Object wuDingImgs;
        private Object xuQiuNames;
        private List<String> drawings;
        private List<String> repairPics;

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

        public String getDrawing() {
            return drawing;
        }

        public void setDrawing(String drawing) {
            this.drawing = drawing;
        }

        public Object getStage() {
            return stage;
        }

        public void setStage(Object stage) {
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

        public Object getXuQiuName() {
            return xuQiuName;
        }

        public void setXuQiuName(Object xuQiuName) {
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

        public List<String> getDrawings() {
            return drawings;
        }

        public void setDrawings(List<String> drawings) {
            this.drawings = drawings;
        }

        public String getRepairType() {
            return repairType;
        }

        public void setRepairType(String repairType) {
            this.repairType = repairType;
        }

        public List<String> getRepairPics() {
            return repairPics;
        }

        public void setRepairPics(List<String> repairPics) {
            this.repairPics = repairPics;
        }
    }
}
