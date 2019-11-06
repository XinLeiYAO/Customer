package com.example.asus.customer.entity;

import java.util.List;

public class NewQiYeBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : [{"industryTypeName":"美容整形","preview":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1549937050Ctc75nBQaY.jpg?x-oss-process=image/resize,l_300","rwdId":"61-1122","zipUrl":"error","choose":false,"caseUrl":"https://zaapp.rxjy.com/Customer/CaseDetail?projectId=223893453c544fa3aaa193bb71c1260f","url":"https://www.wenes.cn/case/11185.html","projectArea":120,"stage":"1","projectTypeName":"商业","projectCost":"8000.00","designStyleName":"时尚前卫","createTime":"2018-11-23 17:46:38.0","diquName":"光谷","clickNum":"0","projectName":"希比美妆定制工作室美容院设计","projectId":"223893453c544fa3aaa193bb71c1260f","favorite":"","mainUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1549937050Ctc75nBQaY.jpg","lookDuration":"0"},{"industryTypeName":"展厅展区","preview":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1547697500aSNzmH7aJX.jpg?x-oss-process=image/resize,l_300","rwdId":"22-202547","zipUrl":"http://img0.wenes.cn/Upload/Resource/File/file/File/2018/08/02/09040139702254.rar","choose":false,"caseUrl":"https://zaapp.rxjy.com/Customer/CaseDetail?projectId=fe0405abfcf14f5a917bd4bfbce3d0ec","url":"https://www.wenes.cn/case/111317.html","projectArea":190,"stage":"1","projectTypeName":"商业","projectCost":"177000.00","designStyleName":"田园风格","createTime":"2018-11-23 17:46:38.0","diquName":"成都","clickNum":"0","projectName":"领地公司 地产类项目体验馆","projectId":"fe0405abfcf14f5a917bd4bfbce3d0ec","favorite":"","mainUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1547697500aSNzmH7aJX.jpg","lookDuration":"0"},{"industryTypeName":"商业店铺","preview":"https://imgcdn.wenes.cn/WenesImg/Image/2018/07/17/16173484810770.jpg?x-oss-process=image/resize,l_300","choose":false,"caseUrl":"https://zaapp.rxjy.com/Customer/CaseDetail?projectId=97090fd1-82cb-462d-96f2-13ddc27d591e","projectArea":210,"stage":"1","projectTypeName":"商业","projectCost":"0.00","designStyleName":"时尚前卫","createTime":"2018-11-23 17:46:38.0","diquName":"北京","clickNum":"0","projectName":"decopatch金源燕莎店","projectId":"97090fd1-82cb-462d-96f2-13ddc27d591e","favorite":"","mainUrl":"https://imgcdn.wenes.cn/WenesImg/Image/2018/07/17/16173484810770.jpg","lookDuration":"0"},{"industryTypeName":"健身馆","preview":"https://imgcdn.wenes.cn/wenesImg/1541752049jT2AtE2ReQ.jpg?x-oss-process=image/resize,l_300","rwdId":"18-202932","zipUrl":"http://img0.wenes.cn/Upload/Resource/File/file/File/2018/08/13/09045026725767.zip","choose":false,"caseUrl":"https://zaapp.rxjy.com/Customer/CaseDetail?projectId=eb13ccd61a6e4088b846f82d57cb9d14","url":"https://www.wenes.cn/case/111313.html","projectArea":200,"stage":"1","projectTypeName":"商业","projectCost":"150000.00","designStyleName":"时尚前卫","createTime":"2018-11-23 17:46:38.0","diquName":"武汉","clickNum":"0","projectName":"200㎡时尚前卫健身房设计 better me 健身房装修设计工程","projectId":"eb13ccd61a6e4088b846f82d57cb9d14","favorite":"","mainUrl":"https://imgcdn.wenes.cn/wenesImg/1541752049jT2AtE2ReQ.jpg","lookDuration":"0"},{"industryTypeName":"茶楼/茶馆","preview":"http://wenes01.oss-cn-beijing.aliyuncs.com/WenesImg/Image/2018/08/30/15350644728392.jpg?x-oss-process=image/resize,l_300","rwdId":"22-203244","zipUrl":"http://img0.wenes.cn/Upload/Resource/File/file/File/2018/09/11/13434257358215.zip","choose":false,"caseUrl":"https://zaapp.rxjy.com/Customer/CaseDetail?projectId=c91d13e8f18041208a1f56b58b0547ae","url":"https://www.wenes.cn/case/11937.html","projectArea":336,"stage":"1","projectTypeName":"商业","projectCost":"150000.00","designStyleName":"现代简约","createTime":"2018-11-23 17:46:38.0","diquName":"成都","clickNum":"0","projectName":"消磨时间咖啡店","projectId":"c91d13e8f18041208a1f56b58b0547ae","favorite":"","mainUrl":"http://wenes01.oss-cn-beijing.aliyuncs.com/WenesImg/Image/2018/08/30/15350644728392.jpg","lookDuration":"0"},{"industryTypeName":"瑜伽馆","preview":"http://wenes01.oss-cn-beijing.aliyuncs.com/WenesImg/Image/2018/08/15/14170461997092.jpg?x-oss-process=image/resize,l_300","rwdId":"18-202777","zipUrl":"http://img0.wenes.cn/Upload/Resource/File/file/File/2018/08/10/09421857543052.rar","choose":false,"caseUrl":"https://zaapp.rxjy.com/Customer/CaseDetail?projectId=488959dbdbb444809679f2a3bd6746a5","url":"https://www.wenes.cn/case/11366.html","projectArea":200,"stage":"1","projectTypeName":"商业","projectCost":"233000.00","designStyleName":"新中式","createTime":"2018-11-23 17:46:38.0","diquName":"武汉","clickNum":"0","projectName":"汉阳四新纽宾凯树瑜伽生活馆","projectId":"488959dbdbb444809679f2a3bd6746a5","favorite":"","mainUrl":"http://wenes01.oss-cn-beijing.aliyuncs.com/WenesImg/Image/2018/08/15/14170461997092.jpg","lookDuration":"0"}]
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
         * industryTypeName : 美容整形
         * preview : https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1549937050Ctc75nBQaY.jpg?x-oss-process=image/resize,l_300
         * rwdId : 61-1122
         * zipUrl : error
         * choose : false
         * caseUrl : https://zaapp.rxjy.com/Customer/CaseDetail?projectId=223893453c544fa3aaa193bb71c1260f
         * url : https://www.wenes.cn/case/11185.html
         * projectArea : 120.0
         * stage : 1
         * projectTypeName : 商业
         * projectCost : 8000.00
         * designStyleName : 时尚前卫
         * createTime : 2018-11-23 17:46:38.0
         * diquName : 光谷
         * clickNum : 0
         * projectName : 希比美妆定制工作室美容院设计
         * projectId : 223893453c544fa3aaa193bb71c1260f
         * favorite :
         * mainUrl : https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1549937050Ctc75nBQaY.jpg
         * lookDuration : 0
         */

        private String industryTypeName;
        private String preview;
        private String rwdId;
        private String zipUrl;
        private boolean choose;
        private String caseUrl;
        private String url;
        private double projectArea;
        private String stage;
        private String projectTypeName;
        private String projectCost;
        private String designStyleName;
        private String createTime;
        private String diquName;
        private String clickNum;
        private String projectName;
        private String projectId;
        private String favorite;
        private String mainUrl;
        private String lookDuration;

        public String getIndustryTypeName() {
            return industryTypeName;
        }

        public void setIndustryTypeName(String industryTypeName) {
            this.industryTypeName = industryTypeName;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public String getRwdId() {
            return rwdId;
        }

        public void setRwdId(String rwdId) {
            this.rwdId = rwdId;
        }

        public String getZipUrl() {
            return zipUrl;
        }

        public void setZipUrl(String zipUrl) {
            this.zipUrl = zipUrl;
        }

        public boolean isChoose() {
            return choose;
        }

        public void setChoose(boolean choose) {
            this.choose = choose;
        }

        public String getCaseUrl() {
            return caseUrl;
        }

        public void setCaseUrl(String caseUrl) {
            this.caseUrl = caseUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public double getProjectArea() {
            return projectArea;
        }

        public void setProjectArea(double projectArea) {
            this.projectArea = projectArea;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getProjectCost() {
            return projectCost;
        }

        public void setProjectCost(String projectCost) {
            this.projectCost = projectCost;
        }

        public String getDesignStyleName() {
            return designStyleName;
        }

        public void setDesignStyleName(String designStyleName) {
            this.designStyleName = designStyleName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDiquName() {
            return diquName;
        }

        public void setDiquName(String diquName) {
            this.diquName = diquName;
        }

        public String getClickNum() {
            return clickNum;
        }

        public void setClickNum(String clickNum) {
            this.clickNum = clickNum;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getMainUrl() {
            return mainUrl;
        }

        public void setMainUrl(String mainUrl) {
            this.mainUrl = mainUrl;
        }

        public String getLookDuration() {
            return lookDuration;
        }

        public void setLookDuration(String lookDuration) {
            this.lookDuration = lookDuration;
        }
    }
}
