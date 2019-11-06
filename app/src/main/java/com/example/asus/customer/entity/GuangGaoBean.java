package com.example.asus.customer.entity;


import java.util.List;

public class GuangGaoBean {

    /**
     * StatusCode : 0
     * StatusMsg : Success
     * Body : [{"ad_id":47379,"ad_rn":47379,"is_app":0,"appname":null,"ad_title":"测试","ad_content":null,"ad_img":"https://piapi.rxjy.com/20190516130917.jpg","ad_html":"https://tsharp.rxjy.com/view/news.html?id=47379&cardNo=02700985","read_visit":"0","id":15,"flag":0,"create_date":"2019/05/16 15:25","update_date":"2019/06/17 17:17"}]
     */

    private int StatusCode;
    private String StatusMsg;
    private List<BodyBean> Body;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
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
         * ad_id : 47379
         * ad_rn : 47379
         * is_app : 0
         * appname : null
         * ad_title : 测试
         * ad_content : null
         * ad_img : https://piapi.rxjy.com/20190516130917.jpg
         * ad_html : https://tsharp.rxjy.com/view/news.html?id=47379&cardNo=02700985
         * read_visit : 0
         * id : 15
         * flag : 0
         * create_date : 2019/05/16 15:25
         * update_date : 2019/06/17 17:17
         */

        private int ad_id;
        private int ad_rn;
        private int is_app;
        private Object appname;
        private String ad_title;
        private Object ad_content;
        private String ad_img;
        private String ad_html;
        private String read_visit;
        private int id;
        private int flag;
        private String create_date;
        private String update_date;

        public int getAd_id() {
            return ad_id;
        }

        public void setAd_id(int ad_id) {
            this.ad_id = ad_id;
        }

        public int getAd_rn() {
            return ad_rn;
        }

        public void setAd_rn(int ad_rn) {
            this.ad_rn = ad_rn;
        }

        public int getIs_app() {
            return is_app;
        }

        public void setIs_app(int is_app) {
            this.is_app = is_app;
        }

        public Object getAppname() {
            return appname;
        }

        public void setAppname(Object appname) {
            this.appname = appname;
        }

        public String getAd_title() {
            return ad_title;
        }

        public void setAd_title(String ad_title) {
            this.ad_title = ad_title;
        }

        public Object getAd_content() {
            return ad_content;
        }

        public void setAd_content(Object ad_content) {
            this.ad_content = ad_content;
        }

        public String getAd_img() {
            return ad_img;
        }

        public void setAd_img(String ad_img) {
            this.ad_img = ad_img;
        }

        public String getAd_html() {
            return ad_html;
        }

        public void setAd_html(String ad_html) {
            this.ad_html = ad_html;
        }

        public String getRead_visit() {
            return read_visit;
        }

        public void setRead_visit(String read_visit) {
            this.read_visit = read_visit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }
    }
}

