package com.example.asus.customer.entity;

public class LoginInfoNew {

    /**
     * StatusCode : 0
     * StatusMsg : 登陆成功！
     * Body : {"cardNo":"z00004657","account":"18888319696","phone":"18888319696","Token":"A5C307D5A148ADBDDE4F03C83F48BF07","appId":"c2079938-5a77-4d77-bc74-74cc2bb19b68","is_group":"2","image":"https://holding01.oss-cn-beijing.aliyuncs.com/2019/07/201907021552121412821561101.jpg","region_id":0,"post_id":10001,"name":"杨先生","post_name":"客户","depart_id":3,"level_id":"","level_name":"","person_sign":null}
     */

    private int StatusCode;
    private String StatusMsg;
    private BodyBean Body;

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

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * cardNo : z00004657
         * account : 18888319696
         * phone : 18888319696
         * Token : A5C307D5A148ADBDDE4F03C83F48BF07
         * appId : c2079938-5a77-4d77-bc74-74cc2bb19b68
         * is_group : 2
         * image : https://holding01.oss-cn-beijing.aliyuncs.com/2019/07/201907021552121412821561101.jpg
         * region_id : 0
         * post_id : 10001
         * name : 杨先生
         * post_name : 客户
         * depart_id : 3
         * level_id :
         * level_name :
         * person_sign : null
         */

        private String cardNo;
        private String account;
        private String phone;
        private String Token;
        private String appId;
        private String is_group;
        private String image;
        private int region_id;
        private int post_id;
        private String name;
        private String post_name;
        private int depart_id;
        private String level_id;
        private String level_name;
        private Object person_sign;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getIs_group() {
            return is_group;
        }

        public void setIs_group(String is_group) {
            this.is_group = is_group;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public int getPost_id() {
            return post_id;
        }

        public void setPost_id(int post_id) {
            this.post_id = post_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPost_name() {
            return post_name;
        }

        public void setPost_name(String post_name) {
            this.post_name = post_name;
        }

        public int getDepart_id() {
            return depart_id;
        }

        public void setDepart_id(int depart_id) {
            this.depart_id = depart_id;
        }

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public Object getPerson_sign() {
            return person_sign;
        }

        public void setPerson_sign(Object person_sign) {
            this.person_sign = person_sign;
        }
    }
}
