package com.example.asus.customer.entity;

import java.util.List;

public class GongrenBean {

    /**
     * StatusMsg : 查询成功
     * StatusCode : 1
     * Body : [{"score":0,"workerId":11,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/mmexport1571372777566.jpg","cityName":"成都","regionId":22,"workerTypeName":"电工","regionName":"成都","laborCost":200,"cityId":22,"workYear":3,"workerName":"盛君星","workerLevel":4},{"score":0,"workerId":48,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/1557912112TC75WXM86W.jpg","cityName":"南京","regionId":15,"workerTypeName":"电工","regionName":"南京","laborCost":260,"cityId":15,"workYear":2,"workerName":"唐波","workerLevel":4},{"score":0,"workerId":49,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/1557985500Tbk6p7BnCF.png","cityName":"南京","regionId":15,"workerTypeName":"电工","regionName":"南京","laborCost":260,"cityId":15,"workYear":2,"workerName":"张美松","workerLevel":4},{"score":0,"workerId":63,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/1557986035AQySy8f2SE.png","cityName":"南京","regionId":15,"workerTypeName":"电工","regionName":"南京","laborCost":260,"cityId":15,"workYear":2,"workerName":"郭林涛","workerLevel":4},{"score":0,"workerId":64,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/1557986074jPPDyF75NA.png","cityName":"南京","regionId":15,"workerTypeName":"木工","regionName":"南京","laborCost":260,"cityId":15,"workYear":2,"workerName":"汪有福","workerLevel":4},{"score":0,"workerId":135,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/1557989193kmpKKJWAX3.png","cityName":"南京","regionId":15,"workerTypeName":"瓦工","regionName":"南京","laborCost":300,"cityId":15,"workYear":2,"workerName":"仇飞","workerLevel":4},{"score":0,"workerId":136,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/1557989331ha2ixcAEbT.png","cityName":"南京","regionId":15,"workerTypeName":"木工","regionName":"南京","laborCost":260,"cityId":15,"workYear":2,"workerName":"潘国庆","workerLevel":4},{"score":0,"workerId":178,"personImg":"","cityName":"成都","regionId":22,"workerTypeName":"油工","regionName":"成都","laborCost":260,"cityId":22,"workYear":2,"workerName":"周建惠","workerLevel":4},{"score":0,"workerId":197,"personImg":"","cityName":"成都","regionId":22,"workerTypeName":"瓦工","regionName":"成都","laborCost":300,"cityId":22,"workYear":2,"workerName":"邓金河","workerLevel":4},{"score":9,"workerId":238,"personImg":"https://proj01.oss-cn-beijing.aliyuncs.com/worker/IMG_20190929_193904.jpg","cityName":"成都","regionId":22,"workerTypeName":"木工","regionName":"成都","laborCost":268,"cityId":22,"workYear":2,"workerName":"周平","workerLevel":5}]
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
         * score : 0
         * workerId : 11
         * personImg : https://proj01.oss-cn-beijing.aliyuncs.com/worker/mmexport1571372777566.jpg
         * cityName : 成都
         * regionId : 22
         * workerTypeName : 电工
         * regionName : 成都
         * laborCost : 200.0
         * cityId : 22
         * workYear : 3
         * workerName : 盛君星
         * workerLevel : 4
         */

        private int score;
        private int workerId;
        private String personImg;
        private String cityName;
        private int regionId;
        private String workerTypeName;
        private String regionName;
        private double laborCost;
        private int cityId;
        private int workYear;
        private String workerName;
        private int workerLevel;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getWorkerId() {
            return workerId;
        }

        public void setWorkerId(int workerId) {
            this.workerId = workerId;
        }

        public String getPersonImg() {
            return personImg;
        }

        public void setPersonImg(String personImg) {
            this.personImg = personImg;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getWorkerTypeName() {
            return workerTypeName;
        }

        public void setWorkerTypeName(String workerTypeName) {
            this.workerTypeName = workerTypeName;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public double getLaborCost() {
            return laborCost;
        }

        public void setLaborCost(double laborCost) {
            this.laborCost = laborCost;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getWorkYear() {
            return workYear;
        }

        public void setWorkYear(int workYear) {
            this.workYear = workYear;
        }

        public String getWorkerName() {
            return workerName;
        }

        public void setWorkerName(String workerName) {
            this.workerName = workerName;
        }

        public int getWorkerLevel() {
            return workerLevel;
        }

        public void setWorkerLevel(int workerLevel) {
            this.workerLevel = workerLevel;
        }
    }
}
