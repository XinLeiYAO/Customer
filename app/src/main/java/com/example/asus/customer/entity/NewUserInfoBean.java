package com.example.asus.customer.entity;

import java.util.List;

/**
 * Created by 阿禹 on 2018/7/13.
 */

public class NewUserInfoBean {

    /**
     * StatusCode : 0
     * StatusMsg : 获取成功
     * Body : [{"sum_count":1,"rn":1,"app_id":"cc6b3b4f-516f-4eac-8bc6-43bd348a206f","parent_app_id":null,"card_no":"z00000018","a_account":"13716256776","a_password":"******","u_name":"冯先生","nick_name":null,"a_really_password":"******","token":null,"image":"https://api.dcwzg.com:9191/Content/image/default_img/default_man.png","ewm":"https://api.dcwzg.com:9191/Content/image//myself_ewm/cc6b3b4f-516f-4eac-8bc6-43bd348a206f.png","imageName":null,"weixin":"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ","region_id":0,"region_name":"北分","sex":"男","age":0,"birthday":"2018-05-31T00:00:00","birthday_txt":"2018-05-31","phone":"13716256776","email":"","id_card":null,"shen_fen_data_state":null,"bank_card":null,"bank_name":null,"bank_user_name":null,"bank_image_heads":null,"bank_image_tails":null,"native_place":null,"marital_status":null,"living_place":null,"education":null,"major":null,"post":3,"post_id":10001,"post_name":"客户","vip":0,"data_sources":0,"id_card_image_heads":null,"id_card_image_tails":null,"id_card_name":null,"id_card_address":null,"order_id":null,"isbin":"1","issign":"0","u_isjingyan":null,"u_jyshijian":null,"u_jingli":null,"u_qudao":null,"u_qudaoinput":null,"u_laiyuan":null,"u_laiyuaninput":null,"u_sheng":null,"u_shengId":null,"u_shi":null,"u_shiId":null,"u_xian":null,"u_xianId":null,"u_ywy":null,"u_ywyid":null,"depart_id":3,"is_blacklist":0,"die_out_reason":null,"activation_date":"2018/5/28 10:31:56","id":15380,"flag":0,"create_date":"2018-05-31T16:10:29.167","update_date":"2018-05-31T16:10:29.167"}]
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
         * sum_count : 1
         * rn : 1
         * app_id : cc6b3b4f-516f-4eac-8bc6-43bd348a206f
         * parent_app_id : null
         * card_no : z00000018
         * a_account : 13716256776
         * a_password : ******
         * u_name : 冯先生
         * nick_name : null
         * a_really_password : ******
         * token : null
         * image : https://api.dcwzg.com:9191/Content/image/default_img/default_man.png
         * ewm : https://api.dcwzg.com:9191/Content/image//myself_ewm/cc6b3b4f-516f-4eac-8bc6-43bd348a206f.png
         * imageName : null
         * weixin :
         * region_id : 0
         * region_name : 北分
         * sex : 男
         * age : 0
         * birthday : 2018-05-31T00:00:00
         * birthday_txt : 2018-05-31
         * phone : 13716256776
         * email :
         * id_card : null
         * shen_fen_data_state : null
         * bank_card : null
         * bank_name : null
         * bank_user_name : null
         * bank_image_heads : null
         * bank_image_tails : null
         * native_place : null
         * marital_status : null
         * living_place : null
         * education : null
         * major : null
         * post : 3
         * post_id : 10001
         * post_name : 客户
         * vip : 0
         * data_sources : 0
         * id_card_image_heads : null
         * id_card_image_tails : null
         * id_card_name : null
         * id_card_address : null
         * order_id : null
         * isbin : 1
         * issign : 0
         * u_isjingyan : null
         * u_jyshijian : null
         * u_jingli : null
         * u_qudao : null
         * u_qudaoinput : null
         * u_laiyuan : null
         * u_laiyuaninput : null
         * u_sheng : null
         * u_shengId : null
         * u_shi : null
         * u_shiId : null
         * u_xian : null
         * u_xianId : null
         * u_ywy : null
         * u_ywyid : null
         * depart_id : 3
         * is_blacklist : 0
         * die_out_reason : null
         * activation_date : 2018/5/28 10:31:56
         * id : 15380
         * flag : 0
         * create_date : 2018-05-31T16:10:29.167
         * update_date : 2018-05-31T16:10:29.167
         */

        private int sum_count;
        private int rn;
        private String app_id;
        private Object parent_app_id;
        private String card_no;
        private String a_account;
        private String a_password;
        private String u_name;
        private Object nick_name;
        private String a_really_password;
        private Object token;
        private String image;
        private String ewm;
        private Object imageName;
        private String weixin;
        private int region_id;
        private String region_name;
        private String sex;
        private int age;
        private String birthday;
        private String birthday_txt;
        private String phone;
        private String email;
        private Object id_card;
        private Object shen_fen_data_state;
        private Object bank_card;
        private Object bank_name;
        private Object bank_user_name;
        private Object bank_image_heads;
        private Object bank_image_tails;
        private Object native_place;
        private Object marital_status;
        private Object living_place;
        private Object education;
        private Object major;
        private int post;
        private int post_id;
        private String post_name;
        private int vip;
        private int data_sources;
        private Object id_card_image_heads;
        private Object id_card_image_tails;
        private Object id_card_name;
        private Object id_card_address;
        private Object order_id;
        private String isbin;
        private String issign;
        private Object u_isjingyan;
        private Object u_jyshijian;
        private Object u_jingli;
        private Object u_qudao;
        private Object u_qudaoinput;
        private Object u_laiyuan;
        private Object u_laiyuaninput;
        private Object u_sheng;
        private Object u_shengId;
        private Object u_shi;
        private Object u_shiId;
        private Object u_xian;
        private Object u_xianId;
        private Object u_ywy;
        private Object u_ywyid;
        private int depart_id;
        private int is_blacklist;
        private Object die_out_reason;
        private String activation_date;
        private int id;
        private int flag;
        private String create_date;
        private String update_date;

        public int getSum_count() {
            return sum_count;
        }

        public void setSum_count(int sum_count) {
            this.sum_count = sum_count;
        }

        public int getRn() {
            return rn;
        }

        public void setRn(int rn) {
            this.rn = rn;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public Object getParent_app_id() {
            return parent_app_id;
        }

        public void setParent_app_id(Object parent_app_id) {
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

        public Object getNick_name() {
            return nick_name;
        }

        public void setNick_name(Object nick_name) {
            this.nick_name = nick_name;
        }

        public String getA_really_password() {
            return a_really_password;
        }

        public void setA_really_password(String a_really_password) {
            this.a_really_password = a_really_password;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
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

        public Object getImageName() {
            return imageName;
        }

        public void setImageName(Object imageName) {
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

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
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

        public Object getId_card() {
            return id_card;
        }

        public void setId_card(Object id_card) {
            this.id_card = id_card;
        }

        public Object getShen_fen_data_state() {
            return shen_fen_data_state;
        }

        public void setShen_fen_data_state(Object shen_fen_data_state) {
            this.shen_fen_data_state = shen_fen_data_state;
        }

        public Object getBank_card() {
            return bank_card;
        }

        public void setBank_card(Object bank_card) {
            this.bank_card = bank_card;
        }

        public Object getBank_name() {
            return bank_name;
        }

        public void setBank_name(Object bank_name) {
            this.bank_name = bank_name;
        }

        public Object getBank_user_name() {
            return bank_user_name;
        }

        public void setBank_user_name(Object bank_user_name) {
            this.bank_user_name = bank_user_name;
        }

        public Object getBank_image_heads() {
            return bank_image_heads;
        }

        public void setBank_image_heads(Object bank_image_heads) {
            this.bank_image_heads = bank_image_heads;
        }

        public Object getBank_image_tails() {
            return bank_image_tails;
        }

        public void setBank_image_tails(Object bank_image_tails) {
            this.bank_image_tails = bank_image_tails;
        }

        public Object getNative_place() {
            return native_place;
        }

        public void setNative_place(Object native_place) {
            this.native_place = native_place;
        }

        public Object getMarital_status() {
            return marital_status;
        }

        public void setMarital_status(Object marital_status) {
            this.marital_status = marital_status;
        }

        public Object getLiving_place() {
            return living_place;
        }

        public void setLiving_place(Object living_place) {
            this.living_place = living_place;
        }

        public Object getEducation() {
            return education;
        }

        public void setEducation(Object education) {
            this.education = education;
        }

        public Object getMajor() {
            return major;
        }

        public void setMajor(Object major) {
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

        public Object getId_card_image_heads() {
            return id_card_image_heads;
        }

        public void setId_card_image_heads(Object id_card_image_heads) {
            this.id_card_image_heads = id_card_image_heads;
        }

        public Object getId_card_image_tails() {
            return id_card_image_tails;
        }

        public void setId_card_image_tails(Object id_card_image_tails) {
            this.id_card_image_tails = id_card_image_tails;
        }

        public Object getId_card_name() {
            return id_card_name;
        }

        public void setId_card_name(Object id_card_name) {
            this.id_card_name = id_card_name;
        }

        public Object getId_card_address() {
            return id_card_address;
        }

        public void setId_card_address(Object id_card_address) {
            this.id_card_address = id_card_address;
        }

        public Object getOrder_id() {
            return order_id;
        }

        public void setOrder_id(Object order_id) {
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

        public Object getU_isjingyan() {
            return u_isjingyan;
        }

        public void setU_isjingyan(Object u_isjingyan) {
            this.u_isjingyan = u_isjingyan;
        }

        public Object getU_jyshijian() {
            return u_jyshijian;
        }

        public void setU_jyshijian(Object u_jyshijian) {
            this.u_jyshijian = u_jyshijian;
        }

        public Object getU_jingli() {
            return u_jingli;
        }

        public void setU_jingli(Object u_jingli) {
            this.u_jingli = u_jingli;
        }

        public Object getU_qudao() {
            return u_qudao;
        }

        public void setU_qudao(Object u_qudao) {
            this.u_qudao = u_qudao;
        }

        public Object getU_qudaoinput() {
            return u_qudaoinput;
        }

        public void setU_qudaoinput(Object u_qudaoinput) {
            this.u_qudaoinput = u_qudaoinput;
        }

        public Object getU_laiyuan() {
            return u_laiyuan;
        }

        public void setU_laiyuan(Object u_laiyuan) {
            this.u_laiyuan = u_laiyuan;
        }

        public Object getU_laiyuaninput() {
            return u_laiyuaninput;
        }

        public void setU_laiyuaninput(Object u_laiyuaninput) {
            this.u_laiyuaninput = u_laiyuaninput;
        }

        public Object getU_sheng() {
            return u_sheng;
        }

        public void setU_sheng(Object u_sheng) {
            this.u_sheng = u_sheng;
        }

        public Object getU_shengId() {
            return u_shengId;
        }

        public void setU_shengId(Object u_shengId) {
            this.u_shengId = u_shengId;
        }

        public Object getU_shi() {
            return u_shi;
        }

        public void setU_shi(Object u_shi) {
            this.u_shi = u_shi;
        }

        public Object getU_shiId() {
            return u_shiId;
        }

        public void setU_shiId(Object u_shiId) {
            this.u_shiId = u_shiId;
        }

        public Object getU_xian() {
            return u_xian;
        }

        public void setU_xian(Object u_xian) {
            this.u_xian = u_xian;
        }

        public Object getU_xianId() {
            return u_xianId;
        }

        public void setU_xianId(Object u_xianId) {
            this.u_xianId = u_xianId;
        }

        public Object getU_ywy() {
            return u_ywy;
        }

        public void setU_ywy(Object u_ywy) {
            this.u_ywy = u_ywy;
        }

        public Object getU_ywyid() {
            return u_ywyid;
        }

        public void setU_ywyid(Object u_ywyid) {
            this.u_ywyid = u_ywyid;
        }

        public int getDepart_id() {
            return depart_id;
        }

        public void setDepart_id(int depart_id) {
            this.depart_id = depart_id;
        }

        public int getIs_blacklist() {
            return is_blacklist;
        }

        public void setIs_blacklist(int is_blacklist) {
            this.is_blacklist = is_blacklist;
        }

        public Object getDie_out_reason() {
            return die_out_reason;
        }

        public void setDie_out_reason(Object die_out_reason) {
            this.die_out_reason = die_out_reason;
        }

        public String getActivation_date() {
            return activation_date;
        }

        public void setActivation_date(String activation_date) {
            this.activation_date = activation_date;
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
