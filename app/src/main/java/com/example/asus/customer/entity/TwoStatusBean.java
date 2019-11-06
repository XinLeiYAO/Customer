package com.example.asus.customer.entity;

import java.util.List;

//
// 2019/6/14.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class TwoStatusBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : {"stage":3,"data":[{"updateState":0,"catalogId":"57","rwdId":"12-202079","createTime":"2018-07-03 15:23:12","imageUrl":"http://wenes01.oss-cn-beijing.aliyuncs.com/Project/Image/2018/05/21/20280127604879.jpg","detailId":"472777","updateTime":null,"id":89401,"isdel":0,"catalog_code":"jgt"}]}
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
         * stage : 3
         * data : [{"updateState":0,"catalogId":"57","rwdId":"12-202079","createTime":"2018-07-03 15:23:12","imageUrl":"http://wenes01.oss-cn-beijing.aliyuncs.com/Project/Image/2018/05/21/20280127604879.jpg","detailId":"472777","updateTime":null,"id":89401,"isdel":0,"catalog_code":"jgt"}]
         */

        private int stage;
        private List<DataBean> data;

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * updateState : 0
             * catalogId : 57
             * rwdId : 12-202079
             * createTime : 2018-07-03 15:23:12
             * imageUrl : http://wenes01.oss-cn-beijing.aliyuncs.com/Project/Image/2018/05/21/20280127604879.jpg
             * detailId : 472777
             * updateTime : null
             * id : 89401
             * isdel : 0
             * catalog_code : jgt
             */

            private int updateState;
            private String catalogId;
            private String rwdId;
            private String createTime;
            private String imageUrl;
            private String detailId;
            private Object updateTime;
            private int id;
            private int isdel;
            private String catalog_code;

            public int getUpdateState() {
                return updateState;
            }

            public void setUpdateState(int updateState) {
                this.updateState = updateState;
            }

            public String getCatalogId() {
                return catalogId;
            }

            public void setCatalogId(String catalogId) {
                this.catalogId = catalogId;
            }

            public String getRwdId() {
                return rwdId;
            }

            public void setRwdId(String rwdId) {
                this.rwdId = rwdId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getDetailId() {
                return detailId;
            }

            public void setDetailId(String detailId) {
                this.detailId = detailId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsdel() {
                return isdel;
            }

            public void setIsdel(int isdel) {
                this.isdel = isdel;
            }

            public String getCatalog_code() {
                return catalog_code;
            }

            public void setCatalog_code(String catalog_code) {
                this.catalog_code = catalog_code;
            }
        }
    }
}
