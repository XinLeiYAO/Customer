package com.example.asus.customer.entity;

import java.util.List;

/**
 * Created by asus on 2018/4/19.
 */

public class UserInfo {


    /**
     * StatusCode : 0
     * StatusMsg : Success
     * Body : {"baseinfo":{"idCardName":null,"idCardImageHeads":null,"idCardImageTails":null,"major":null,"weixin":null,"education":null,"livingPlace":null,"maritalStatus":null,"nativePlace":null,"bankImageTails":null,"bankImageHeads":null,"bankUserName":null,"bankCard":null,"bankName":null,"bankBgImage":null,"idCard":null,"cardNo":null,"nickName":"王总","image":null,"name":null,"sex":null,"age":0,"birthday":"","phone":"15300273163","email":null,"userId":"k00000011"},"personnelInfo":{"area":null,"departId":100,"department":null,"postId":10001,"postName":"客户","statusId":0,"statusName":null,"duty":"客户","workNum":"k00000011","entryTime":"0001/1/1 0:00:00","workYears":null,"level":null,"authority":[]}}
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
         * baseinfo : {"idCardName":null,"idCardImageHeads":null,"idCardImageTails":null,"major":null,"weixin":null,"education":null,"livingPlace":null,"maritalStatus":null,"nativePlace":null,"bankImageTails":null,"bankImageHeads":null,"bankUserName":null,"bankCard":null,"bankName":null,"bankBgImage":null,"idCard":null,"cardNo":null,"nickName":"王总","image":null,"name":null,"sex":null,"age":0,"birthday":"","phone":"15300273163","email":null,"userId":"k00000011"}
         * personnelInfo : {"area":null,"departId":100,"department":null,"postId":10001,"postName":"客户","statusId":0,"statusName":null,"duty":"客户","workNum":"k00000011","entryTime":"0001/1/1 0:00:00","workYears":null,"level":null,"authority":[]}
         */

        private BaseinfoBean baseinfo;
        private PersonnelInfoBean personnelInfo;

        public BaseinfoBean getBaseinfo() {
            return baseinfo;
        }

        public void setBaseinfo(BaseinfoBean baseinfo) {
            this.baseinfo = baseinfo;
        }

        public PersonnelInfoBean getPersonnelInfo() {
            return personnelInfo;
        }

        public void setPersonnelInfo(PersonnelInfoBean personnelInfo) {
            this.personnelInfo = personnelInfo;
        }

        public static class BaseinfoBean {
            /**
             * idCardName : null
             * idCardImageHeads : null
             * idCardImageTails : null
             * major : null
             * weixin : null
             * education : null
             * livingPlace : null
             * maritalStatus : null
             * nativePlace : null
             * bankImageTails : null
             * bankImageHeads : null
             * bankUserName : null
             * bankCard : null
             * bankName : null
             * bankBgImage : null
             * idCard : null
             * cardNo : null
             * nickName : 王总
             * image : null
             * name : null
             * sex : null
             * age : 0
             * birthday :
             * phone : 15300273163
             * email : null
             * userId : k00000011
             */

            private String idCardName;
            private String idCardImageHeads;
            private String idCardImageTails;
            private String major;
            private String weixin;
            private String education;
            private String livingPlace;
            private String maritalStatus;
            private String nativePlace;
            private String bankImageTails;
            private String bankImageHeads;
            private String bankUserName;
            private String bankCard;
            private String bankName;
            private String bankBgImage;
            private String idCard;
            private String cardNo;
            private String nickName;
            private String image;
            private String name;
            private String sex;
            private int age;
            private String birthday;
            private String phone;
            private String email;
            private String userId;

            public String getIdCardName() {
                return idCardName;
            }

            public void setIdCardName(String idCardName) {
                this.idCardName = idCardName;
            }

            public String getIdCardImageHeads() {
                return idCardImageHeads;
            }

            public void setIdCardImageHeads(String idCardImageHeads) {
                this.idCardImageHeads = idCardImageHeads;
            }

            public String getIdCardImageTails() {
                return idCardImageTails;
            }

            public void setIdCardImageTails(String idCardImageTails) {
                this.idCardImageTails = idCardImageTails;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getLivingPlace() {
                return livingPlace;
            }

            public void setLivingPlace(String livingPlace) {
                this.livingPlace = livingPlace;
            }

            public String getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(String maritalStatus) {
                this.maritalStatus = maritalStatus;
            }

            public String getNativePlace() {
                return nativePlace;
            }

            public void setNativePlace(String nativePlace) {
                this.nativePlace = nativePlace;
            }

            public String getBankImageTails() {
                return bankImageTails;
            }

            public void setBankImageTails(String bankImageTails) {
                this.bankImageTails = bankImageTails;
            }

            public String getBankImageHeads() {
                return bankImageHeads;
            }

            public void setBankImageHeads(String bankImageHeads) {
                this.bankImageHeads = bankImageHeads;
            }

            public String getBankUserName() {
                return bankUserName;
            }

            public void setBankUserName(String bankUserName) {
                this.bankUserName = bankUserName;
            }

            public String getBankCard() {
                return bankCard;
            }

            public void setBankCard(String bankCard) {
                this.bankCard = bankCard;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBankBgImage() {
                return bankBgImage;
            }

            public void setBankBgImage(String bankBgImage) {
                this.bankBgImage = bankBgImage;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }

        public static class PersonnelInfoBean {
            /**
             * area : null
             * departId : 100
             * department : null
             * postId : 10001
             * postName : 客户
             * statusId : 0
             * statusName : null
             * duty : 客户
             * workNum : k00000011
             * entryTime : 0001/1/1 0:00:00
             * workYears : null
             * level : null
             * authority : []
             */

            private String area;
            private int departId;
            private String department;
            private int postId;
            private String postName;
            private int statusId;
            private String statusName;
            private String duty;
            private String workNum;
            private String entryTime;
            private String workYears;
            private String level;
            private List<?> authority;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getDepartId() {
                return departId;
            }

            public void setDepartId(int departId) {
                this.departId = departId;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getPostName() {
                return postName;
            }

            public void setPostName(String postName) {
                this.postName = postName;
            }

            public int getStatusId() {
                return statusId;
            }

            public void setStatusId(int statusId) {
                this.statusId = statusId;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getDuty() {
                return duty;
            }

            public void setDuty(String duty) {
                this.duty = duty;
            }

            public String getWorkNum() {
                return workNum;
            }

            public void setWorkNum(String workNum) {
                this.workNum = workNum;
            }

            public String getEntryTime() {
                return entryTime;
            }

            public void setEntryTime(String entryTime) {
                this.entryTime = entryTime;
            }

            public String getWorkYears() {
                return workYears;
            }

            public void setWorkYears(String workYears) {
                this.workYears = workYears;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<?> getAuthority() {
                return authority;
            }

            public void setAuthority(List<?> authority) {
                this.authority = authority;
            }
        }
    }
}
