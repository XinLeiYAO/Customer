package com.example.asus.customer.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus on 2018/5/28.
 */

public class OptimizationBean implements Serializable {


    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : [{"projecttypename":"办公","projectname":"远盟康健","DiquName":"R6","industrytypename":"网络公司","projectarea":600,"menuName":"现代简约","projectid":"01010792001","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/25/14381796936896.jpg"},{"projecttypename":"办公","projectname":"阿奇帕米尔国际贸易有限公司","DiquName":"R6","industrytypename":"贸易公司","projectarea":650,"menuName":"现代简约","projectid":"01010792002","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/26/17193526684504.jpg"},{"projecttypename":"餐饮","projectname":"重庆江湖菜设计装修工程","DiquName":"成都","industrytypename":"主题餐厅","projectarea":180,"menuName":"新中式","projectid":"00485518-FD3A-479A-9EE2-0F8EAF403105","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/22/14122194444719.jpg"},{"projecttypename":"餐饮","projectname":"北京东清宫朝鲜料理店","DiquName":"R6","industrytypename":"日式料理","projectarea":1400,"menuName":"新中式","projectid":"01012365008","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/27/16554281224081.jpg"},{"projecttypename":"商业","projectname":"木易百年","DiquName":"上海","industrytypename":"美容整形","projectarea":500,"menuName":"时尚前卫","projectid":"02100922005","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2018/01/26/10023429831732.jpg"},{"projecttypename":"商业","projectname":"IL SALONE 美发","DiquName":"上海","industrytypename":"美容整形","projectarea":400,"menuName":"现代简约","projectid":"02200421002","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2018/01/26/10575281819240.jpg"},{"projecttypename":"酒店","projectname":"嘉伦集团酒店会所","DiquName":"R6","industrytypename":"商务酒店","projectarea":1500,"menuName":"LOFT","projectid":"01010954001","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/01/12103667161633.png"},{"projecttypename":"教育","projectname":"鲱鱼宝宝","DiquName":"上海","industrytypename":"早教机构","projectarea":500,"menuName":"现代简约","projectid":"01012431004","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2018/01/18/15544089425427.jpg"},{"projecttypename":"教育","projectname":"EQ教育培训","DiquName":"R6","industrytypename":"营销企业","projectarea":330,"menuName":"现代简约","projectid":"22605482-D1FF-43E8-8D9B-48A776C5136","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2018/02/07/14125056185179.jpg"},{"projecttypename":"会所","projectname":"研美姿养生会所","DiquName":"R6","industrytypename":"私人会所","projectarea":350,"menuName":"LOFT","projectid":"373BCEEE-C909-4519-BF3D-BA43066961B","mainurl":"http://img0.wenes.cn//Upload/WenesImg/Image/2018/01/03/11375994450702.jpg"}]
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

    public static class BodyBean implements Serializable {
        /**
         * projecttypename : 办公
         * projectname : 远盟康健
         * DiquName : R6
         * industrytypename : 网络公司
         * projectarea : 600
         * menuName : 现代简约
         * projectid : 01010792001
         * mainurl : http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/25/14381796936896.jpg
         */

        private String projecttypename;
        private String projectname;
        private String DiquName;
        private String industrytypename;
        private int projectarea;
        private String menuName;
        private String projectid;
        private String mainurl;

        public String getProjecttypename() {
            return projecttypename;
        }

        public void setProjecttypename(String projecttypename) {
            this.projecttypename = projecttypename;
        }

        public String getProjectname() {
            return projectname;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public String getDiquName() {
            return DiquName;
        }

        public void setDiquName(String DiquName) {
            this.DiquName = DiquName;
        }

        public String getIndustrytypename() {
            return industrytypename;
        }

        public void setIndustrytypename(String industrytypename) {
            this.industrytypename = industrytypename;
        }

        public int getProjectarea() {
            return projectarea;
        }

        public void setProjectarea(int projectarea) {
            this.projectarea = projectarea;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public String getProjectid() {
            return projectid;
        }

        public void setProjectid(String projectid) {
            this.projectid = projectid;
        }

        public String getMainurl() {
            return mainurl;
        }

        public void setMainurl(String mainurl) {
            this.mainurl = mainurl;
        }
    }
}
