package com.example.asus.customer.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewShouGaoBean {

    /**
     * StatusMsg : 成功
     * StatusCode : 0
     * Body : {"123":[{"ProjectBrief":"","CustomerState":"","DetailID":"803147","filePath":"https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1565861192Zi3Xm3YMApjY0Q5F4hKXhTYn.jpg","CreateTime":"2019-08-15 17:26:35","CatalogName":"量房手绘","CatalogID":"123"}]}
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
        @SerializedName("123")
        private List<_$123Bean> _$123;

        public List<_$123Bean> get_$123() {
            return _$123;
        }

        public void set_$123(List<_$123Bean> _$123) {
            this._$123 = _$123;
        }

        public static class _$123Bean {
            /**
             * ProjectBrief :
             * CustomerState :
             * DetailID : 803147
             * filePath : https://wenes01.oss-cn-beijing.aliyuncs.com/ProjectBusiness/Images/1565861192Zi3Xm3YMApjY0Q5F4hKXhTYn.jpg
             * CreateTime : 2019-08-15 17:26:35
             * CatalogName : 量房手绘
             * CatalogID : 123
             */

            private String ProjectBrief;
            private String CustomerState;
            private String DetailID;
            private String filePath;
            private String CreateTime;
            private String CatalogName;
            private String CatalogID;

            public String getProjectBrief() {
                return ProjectBrief;
            }

            public void setProjectBrief(String ProjectBrief) {
                this.ProjectBrief = ProjectBrief;
            }

            public String getCustomerState() {
                return CustomerState;
            }

            public void setCustomerState(String CustomerState) {
                this.CustomerState = CustomerState;
            }

            public String getDetailID() {
                return DetailID;
            }

            public void setDetailID(String DetailID) {
                this.DetailID = DetailID;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getCatalogName() {
                return CatalogName;
            }

            public void setCatalogName(String CatalogName) {
                this.CatalogName = CatalogName;
            }

            public String getCatalogID() {
                return CatalogID;
            }

            public void setCatalogID(String CatalogID) {
                this.CatalogID = CatalogID;
            }
        }
    }
}
