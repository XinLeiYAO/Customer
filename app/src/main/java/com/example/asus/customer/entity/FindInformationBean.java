package com.example.asus.customer.entity;

import java.util.List;

/**
 * Created by asus on 2018/4/20.
 */

public class FindInformationBean {


    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : [{"industryTypeName":"文化/广告/传媒","projectName":"成都锐拓传媒广告公司","projectId":"0693F27F-AF36-4727-B462-01288F60F1A","mainUrl":"http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/13/18512172090558.jpg","projectStyle":"现代简约"},{"industryTypeName":"金融投资","projectName":"英联国际销售办公室装修","projectId":"1C0FE61C-8495-4AF7-8671-63D4B0ED249","mainUrl":"http://img0.wenes.cn/Upload/WenesImg/Image/2018/04/18/13193693456272.jpg","projectStyle":"现代简约"},{"industryTypeName":"地产/开发","projectName":"枫丹国际楼汉益地产办公室装修","projectId":"c98a054b-42ed-487e-9eb2-1307bb25454e","mainUrl":"http://img0.wenes.cn//Upload/WenesImg/Image/2018/01/29/16053915256871.jpg","projectStyle":"现代简约"}]
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
         * industryTypeName : 文化/广告/传媒
         * projectName : 成都锐拓传媒广告公司
         * projectId : 0693F27F-AF36-4727-B462-01288F60F1A
         * mainUrl : http://img0.wenes.cn//Upload/WenesImg/Image/2017/12/13/18512172090558.jpg
         * projectStyle : 现代简约
         */

        private String industryTypeName;
        private String projectName;
        private String projectId;
        private String mainUrl;
        private String projectStyle;

        public String getIndustryTypeName() {
            return industryTypeName;
        }

        public void setIndustryTypeName(String industryTypeName) {
            this.industryTypeName = industryTypeName;
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

        public String getMainUrl() {
            return mainUrl;
        }

        public void setMainUrl(String mainUrl) {
            this.mainUrl = mainUrl;
        }

        public String getProjectStyle() {
            return projectStyle;
        }

        public void setProjectStyle(String projectStyle) {
            this.projectStyle = projectStyle;
        }
    }
}
