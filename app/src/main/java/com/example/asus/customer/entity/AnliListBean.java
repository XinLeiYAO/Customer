package com.example.asus.customer.entity;

import java.util.List;

public class AnliListBean {

    /**
     * StatusCode : 1
     * StatusMsg : 获取成功
     * Body : [{"title":"瑞丁英语培训学校室内装饰设计工程","hy":"英语培训","lx":"教育空间","thumb":"https://imgcdn.wenes.cn/wenesImg/1540367811hb7PPfraXG.jpg"},{"title":"津南艺术培训班教育室内装饰工程","hy":"艺术培训","lx":"教育空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1547443435AQsTtdzCS5.jpg"},{"title":"180平米重厨江湖川菜馆中餐厅设计装修","hy":"中餐厅","lx":"餐饮空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1556446417bBK8eSnKka.jpg"},{"title":"1220平米星云健身房室内设计项目","hy":"健身馆","lx":"商业空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1556160787xGwb7X8jEi.jpg"},{"title":"215平米成都123干锅店快餐厅设计装修工程","hy":"中餐厅","lx":"餐饮空间","thumb":"http://wenes01.oss-cn-beijing.aliyuncs.com/WenesImg/Image/2018/08/24/13191189870223.png"},{"title":"汽车租赁办公室装修设计工程","hy":"贸易公司","lx":"办公空间","thumb":"https://imgcdn.wenes.cn/Project/Image/2016/09/02/15481313104565.jpg"},{"title":"嘉伦集团商务酒店装修设计","hy":"商务酒店","lx":"酒店空间","thumb":"https://imgcdn.wenes.cn/WenesImg/Image/2018/06/01/18331986560049.jpg"},{"title":"金融公司办公室装修设计工程","hy":"科技公司","lx":"办公空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1550127071MmiRCHyxJ7.png"},{"title":"【南京装修】400㎡BBU幼儿早教机构装修设计项目","hy":"早教机构","lx":"教育空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1557206100fPJnTPjzJ4.jpg"},{"title":"一安社区综合门诊部商业装修设计项目","hy":"门诊","lx":"商业空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1557483230Ke4DJ7p2kE.jpg"},{"title":"青山泉镇展厅设计商业展厅装修设计项目","hy":"展厅展区","lx":"商业空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/15474548203PmpRwDCsz.png"},{"title":"优尼智能科技公司1200平商业展厅装修设计","hy":"展厅展区","lx":"商业空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1556157915NzrSyksWi5.jpg"},{"title":"易慧家科技有限公司办公室室内装饰设计工程","hy":"网络公司","lx":"办公空间","thumb":"https://imgcdn.wenes.cn/wenesImg/1539942908SfJxJakB7z.jpg"},{"title":"亚星酒吧火锅店餐厅装修室内装饰工程","hy":"火锅店","lx":"餐饮空间","thumb":"https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1550651765yTeX8WDzSc.jpg"},{"title":"金融投资办公室内装修工程","hy":"金融投资","lx":"办公空间","thumb":"https://imgcdn.wenes.cn/WenesImg/Image/2018/02/06/15041775266009.jpg"}]
     */

    private String StatusCode;
    private String StatusMsg;
    private List<BodyBean> Body;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String StatusMsg) {
        this.StatusMsg = StatusMsg;
    }

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * title : 瑞丁英语培训学校室内装饰设计工程
         * hy : 英语培训
         * lx : 教育空间
         * thumb : https://imgcdn.wenes.cn/wenesImg/1540367811hb7PPfraXG.jpg
         */

        private String title;
        private String hy;
        private String lx;
        private String thumb;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHy() {
            return hy;
        }

        public void setHy(String hy) {
            this.hy = hy;
        }

        public String getLx() {
            return lx;
        }

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }
}
