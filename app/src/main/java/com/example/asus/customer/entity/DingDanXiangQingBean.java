package com.example.asus.customer.entity;

import java.util.List;

//
// 2019/6/13.
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
public class DingDanXiangQingBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 1
     * Body : [{"imgUrl":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557464996c0671YwZ5ipYAh8d3n9sPP7a.jpg","orderNo":"89-1363-X-1","imgId":9,"rwdId":"89-1363","createTime":"2019-05-10 13:40:56","classificationName":"门头/外立面","imgType_code":"xgt","id":15,"imgType":"效果图"}]
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
         * imgUrl : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1557464996c0671YwZ5ipYAh8d3n9sPP7a.jpg
         * orderNo : 89-1363-X-1
         * imgId : 9
         * rwdId : 89-1363
         * createTime : 2019-05-10 13:40:56
         * classificationName : 门头/外立面
         * imgType_code : xgt
         * id : 15
         * imgType : 效果图
         */

        private String imgUrl;
        private String orderNo;
        private int imgId;
        private String rwdId;
        private String createTime;
        private String classificationName;
        private String imgType_code;
        private int id;
        private String imgType;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
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

        public String getClassificationName() {
            return classificationName;
        }

        public void setClassificationName(String classificationName) {
            this.classificationName = classificationName;
        }

        public String getImgType_code() {
            return imgType_code;
        }

        public void setImgType_code(String imgType_code) {
            this.imgType_code = imgType_code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgType() {
            return imgType;
        }

        public void setImgType(String imgType) {
            this.imgType = imgType;
        }
    }
}
