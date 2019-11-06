package com.example.asus.customer.entity;

import java.util.List;

public class GetBranchCompanyInfoBean {

    /**
     * Body : [{"companyAdress":"北京市海淀区永定河路388号","companyName":"北京瑞祥佳艺建筑装饰工程有限公司","companyPhone":"18911573782","companyTel":"010-56288812","regionId":11,"regionName":"R6"},{"companyAdress":"石景山区城兴街25号院1号楼中海大B座301室","companyName":"北京瑞祥佳艺建筑装饰工程有限公司","companyPhone":"15001231232","companyTel":"010-57231261","regionId":12,"regionName":"北京"},{"companyAdress":"南京市玄武区新街口广场中山东路9号天时国际商贸中心13楼（电梯按15B）                                        ","companyPhone":"18136576422","companyTel":"025-57920231","regionId":15,"regionName":"南京"},{"companyAdress":"庐阳区北一环濉溪路中段金鼎国际广场A座18楼","companyPhone":"15375459514","companyTel":"0551-62589070","regionId":16,"regionName":"合肥"},{"companyAdress":"武汉市汉口江汉区解放大道686号世界贸易厦56层","companyPhone":"18971335377","companyTel":"18971335377","regionId":18,"regionName":"武汉"}]
     * StatusCode : 0
     * StatusMsg : 获取成功
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
         * companyAdress : 北京市海淀区永定河路388号
         * companyName : 北京瑞祥佳艺建筑装饰工程有限公司
         * companyPhone : 18911573782
         * companyTel : 010-56288812
         * regionId : 11
         * regionName : R6
         */

        private String companyAdress;
        private String companyName;
        private String companyPhone;
        private String companyTel;
        private int regionId;
        private String regionName;

        public String getCompanyAdress() {
            return companyAdress;
        }

        public void setCompanyAdress(String companyAdress) {
            this.companyAdress = companyAdress;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getCompanyTel() {
            return companyTel;
        }

        public void setCompanyTel(String companyTel) {
            this.companyTel = companyTel;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }
    }
}
