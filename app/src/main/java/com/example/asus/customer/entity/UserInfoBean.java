package com.example.asus.customer.entity;

import java.util.List;

/**
 * Created by asus on 2018/6/13.
 */

public class UserInfoBean {

    /**
     * StatusCode : 0
     * StatusMsg : 获取成功
     * Body : [{"app_id":"93ef0f14-0ecc-4599-9ede-ddf394b4b1ff","parent_app_id":"","card_no":"s00004141","a_account":"13988888888","a_password":"******","u_name":"噢噢噢","nick_name":"噢噢噢","a_really_password":"******","token":null,"image":"001EwKgBtFodH1WAZnMkAAH__vTGMfI1560481","ewm":"https://api.dcwzg.com:9191/Content/image//myself_ewm/93ef0f14-0ecc-4599-9ede-ddf394b4b1ff.png","imageName":"","weixin":"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ","region_id":0,"sex":"男         ","age":0,"birthday":"1900-01-01T00:00:00","birthday_txt":"1900-01-01","phone":"13988888888","email":"zdfsdfs","id_card":"","bank_card":"6258856688472589","bank_name":"中国建设银行                        ","bank_user_name":"欧束","bank_image_heads":"                                                                                                    ","bank_image_tails":"                                                                                                    ","native_place":"","marital_status":"          ","living_place":"                                                                                                    ","education":"          ","major":"               ","post":1,"post_id":100,"post_name":"牛经纪","vip":0,"data_sources":0,"id_card_image_heads":"","id_card_image_tails":"","id_card_name":"","order_id":"","isbin":"1         ","issign":"0         ","u_isjingyan":"          ","u_jyshijian":"","u_jingli":"","u_qudao":"          ","u_qudaoinput":"","u_laiyuan":"          ","u_laiyuaninput":"新版招统一同步","u_sheng":"","u_shengId":"          ","u_shi":"","u_shiId":"          ","u_xian":"","u_xianId":"          ","u_ywy":"","u_ywyid":"          ","depart_id":2,"id":8522,"flag":0,"create_date":"2018-05-25T14:35:37.243","update_date":"2018-05-25T14:35:37.243"}]
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
         * app_id : 93ef0f14-0ecc-4599-9ede-ddf394b4b1ff
         * parent_app_id :
         * card_no : s00004141
         * a_account : 13988888888
         * a_password : ******
         * u_name : 噢噢噢
         * nick_name : 噢噢噢
         * a_really_password : ******
         * token : null
         * image : 001EwKgBtFodH1WAZnMkAAH__vTGMfI1560481
         * ewm : https://api.dcwzg.com:9191/Content/image//myself_ewm/93ef0f14-0ecc-4599-9ede-ddf394b4b1ff.png
         * imageName :
         * weixin :
         * region_id : 0
         * sex : 男
         * age : 0
         * birthday : 1900-01-01T00:00:00
         * birthday_txt : 1900-01-01
         * phone : 13988888888
         * email : zdfsdfs
         * id_card :
         * bank_card : 6258856688472589
         * bank_name : 中国建设银行
         * bank_user_name : 欧束
         * bank_image_heads :
         * bank_image_tails :
         * native_place :
         * marital_status :
         * living_place :
         * education :
         * major :
         * post : 1
         * post_id : 100
         * post_name : 牛经纪
         * vip : 0
         * data_sources : 0
         * id_card_image_heads :
         * id_card_image_tails :
         * id_card_name :
         * order_id :
         * isbin : 1
         * issign : 0
         * u_isjingyan :
         * u_jyshijian :
         * u_jingli :
         * u_qudao :
         * u_qudaoinput :
         * u_laiyuan :
         * u_laiyuaninput : 新版招统一同步
         * u_sheng :
         * u_shengId :
         * u_shi :
         * u_shiId :
         * u_xian :
         * u_xianId :
         * u_ywy :
         * u_ywyid :
         * depart_id : 2
         * id : 8522
         * flag : 0
         * create_date : 2018-05-25T14:35:37.243
         * update_date : 2018-05-25T14:35:37.243
         */

        private String app_id;
        private String parent_app_id;
        private String card_no;
        private String a_account;
        private String a_password;
        private String u_name;
        private String nick_name;
        private String a_really_password;
        private String token;
        private String image;
        private String ewm;
        private String imageName;
        private String weixin;
        private int region_id;
        private String sex;
        private int age;
        private String birthday;
        private String birthday_txt;
        private String phone;
        private String email;
        private String id_card;
        private String bank_card;
        private String bank_name;
        private String bank_user_name;
        private String bank_image_heads;
        private String bank_image_tails;
        private String native_place;
        private String marital_status;
        private String living_place;
        private String education;
        private String major;
        private int post;
        private int post_id;
        private String post_name;
        private int vip;
        private int data_sources;
        private String id_card_image_heads;
        private String id_card_image_tails;
        private String id_card_name;
        private String order_id;
        private String isbin;
        private String issign;
        private String u_isjingyan;
        private String u_jyshijian;
        private String u_jingli;
        private String u_qudao;
        private String u_qudaoinput;
        private String u_laiyuan;
        private String u_laiyuaninput;
        private String u_sheng;
        private String u_shengId;
        private String u_shi;
        private String u_shiId;
        private String u_xian;
        private String u_xianId;
        private String u_ywy;
        private String u_ywyid;
        private int depart_id;
        private int id;
        private int flag;
        private String create_date;
        private String update_date;

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getParent_app_id() {
            return parent_app_id;
        }

        public void setParent_app_id(String parent_app_id) {
            this.parent_app_id = parent_app_id;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getA_account() {
            return a_account;
        }

        public void setA_account(String a_account) {
            this.a_account = a_account;
        }

        public String getA_password() {
            return a_password;
        }

        public void setA_password(String a_password) {
            this.a_password = a_password;
        }

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getA_really_password() {
            return a_really_password;
        }

        public void setA_really_password(String a_really_password) {
            this.a_really_password = a_really_password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getEwm() {
            return ewm;
        }

        public void setEwm(String ewm) {
            this.ewm = ewm;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
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

        public String getBirthday_txt() {
            return birthday_txt;
        }

        public void setBirthday_txt(String birthday_txt) {
            this.birthday_txt = birthday_txt;
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

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_user_name() {
            return bank_user_name;
        }

        public void setBank_user_name(String bank_user_name) {
            this.bank_user_name = bank_user_name;
        }

        public String getBank_image_heads() {
            return bank_image_heads;
        }

        public void setBank_image_heads(String bank_image_heads) {
            this.bank_image_heads = bank_image_heads;
        }

        public String getBank_image_tails() {
            return bank_image_tails;
        }

        public void setBank_image_tails(String bank_image_tails) {
            this.bank_image_tails = bank_image_tails;
        }

        public String getNative_place() {
            return native_place;
        }

        public void setNative_place(String native_place) {
            this.native_place = native_place;
        }

        public String getMarital_status() {
            return marital_status;
        }

        public void setMarital_status(String marital_status) {
            this.marital_status = marital_status;
        }

        public String getLiving_place() {
            return living_place;
        }

        public void setLiving_place(String living_place) {
            this.living_place = living_place;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public int getPost() {
            return post;
        }

        public void setPost(int post) {
            this.post = post;
        }

        public int getPost_id() {
            return post_id;
        }

        public void setPost_id(int post_id) {
            this.post_id = post_id;
        }

        public String getPost_name() {
            return post_name;
        }

        public void setPost_name(String post_name) {
            this.post_name = post_name;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public int getData_sources() {
            return data_sources;
        }

        public void setData_sources(int data_sources) {
            this.data_sources = data_sources;
        }

        public String getId_card_image_heads() {
            return id_card_image_heads;
        }

        public void setId_card_image_heads(String id_card_image_heads) {
            this.id_card_image_heads = id_card_image_heads;
        }

        public String getId_card_image_tails() {
            return id_card_image_tails;
        }

        public void setId_card_image_tails(String id_card_image_tails) {
            this.id_card_image_tails = id_card_image_tails;
        }

        public String getId_card_name() {
            return id_card_name;
        }

        public void setId_card_name(String id_card_name) {
            this.id_card_name = id_card_name;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getIsbin() {
            return isbin;
        }

        public void setIsbin(String isbin) {
            this.isbin = isbin;
        }

        public String getIssign() {
            return issign;
        }

        public void setIssign(String issign) {
            this.issign = issign;
        }

        public String getU_isjingyan() {
            return u_isjingyan;
        }

        public void setU_isjingyan(String u_isjingyan) {
            this.u_isjingyan = u_isjingyan;
        }

        public String getU_jyshijian() {
            return u_jyshijian;
        }

        public void setU_jyshijian(String u_jyshijian) {
            this.u_jyshijian = u_jyshijian;
        }

        public String getU_jingli() {
            return u_jingli;
        }

        public void setU_jingli(String u_jingli) {
            this.u_jingli = u_jingli;
        }

        public String getU_qudao() {
            return u_qudao;
        }

        public void setU_qudao(String u_qudao) {
            this.u_qudao = u_qudao;
        }

        public String getU_qudaoinput() {
            return u_qudaoinput;
        }

        public void setU_qudaoinput(String u_qudaoinput) {
            this.u_qudaoinput = u_qudaoinput;
        }

        public String getU_laiyuan() {
            return u_laiyuan;
        }

        public void setU_laiyuan(String u_laiyuan) {
            this.u_laiyuan = u_laiyuan;
        }

        public String getU_laiyuaninput() {
            return u_laiyuaninput;
        }

        public void setU_laiyuaninput(String u_laiyuaninput) {
            this.u_laiyuaninput = u_laiyuaninput;
        }

        public String getU_sheng() {
            return u_sheng;
        }

        public void setU_sheng(String u_sheng) {
            this.u_sheng = u_sheng;
        }

        public String getU_shengId() {
            return u_shengId;
        }

        public void setU_shengId(String u_shengId) {
            this.u_shengId = u_shengId;
        }

        public String getU_shi() {
            return u_shi;
        }

        public void setU_shi(String u_shi) {
            this.u_shi = u_shi;
        }

        public String getU_shiId() {
            return u_shiId;
        }

        public void setU_shiId(String u_shiId) {
            this.u_shiId = u_shiId;
        }

        public String getU_xian() {
            return u_xian;
        }

        public void setU_xian(String u_xian) {
            this.u_xian = u_xian;
        }

        public String getU_xianId() {
            return u_xianId;
        }

        public void setU_xianId(String u_xianId) {
            this.u_xianId = u_xianId;
        }

        public String getU_ywy() {
            return u_ywy;
        }

        public void setU_ywy(String u_ywy) {
            this.u_ywy = u_ywy;
        }

        public String getU_ywyid() {
            return u_ywyid;
        }

        public void setU_ywyid(String u_ywyid) {
            this.u_ywyid = u_ywyid;
        }

        public int getDepart_id() {
            return depart_id;
        }

        public void setDepart_id(int depart_id) {
            this.depart_id = depart_id;
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
