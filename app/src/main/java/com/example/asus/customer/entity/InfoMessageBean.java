package com.example.asus.customer.entity;

import java.util.List;

/**
 * Created by asus on 2018/7/23.
 */

public class InfoMessageBean {


    /**
     * StatusCode : 0
     * StatusMsg : null
     * Body : [{"id":0,"cardno":null,"title":null,"txt":null,"state":null,"flag":null,"create_date":"01.01","update_date":"0001-01-01T00:00:00","type":null,"group":"21","group_name":"活动","image":"http://i.rxjy.com/Content/appContent/images/notice/huoDong.png","group_id":"1","image_http":null,"image_type":null,"initiator_card":null,"initiator_name":null,"reward_money":null,"notice_count":null,"notice_countSum":null,"task_balance":null,"task_end_time":"0001/01/01","task_name":null,"task_num":null,"task_count_down":null},{"id":0,"cardno":null,"title":null,"txt":null,"state":null,"flag":null,"create_date":"01.01","update_date":"0001-01-01T00:00:00","type":null,"group":"22","group_name":"事件","image":"http://i.rxjy.com/Content/appContent/images/notice/shiJian.png","group_id":"2","image_http":null,"image_type":null,"initiator_card":null,"initiator_name":null,"reward_money":null,"notice_count":null,"notice_countSum":null,"task_balance":null,"task_end_time":"0001/01/01","task_name":null,"task_num":null,"task_count_down":null},{"id":0,"cardno":null,"title":null,"txt":null,"state":null,"flag":null,"create_date":"01.01","update_date":"0001-01-01T00:00:00","type":null,"group":"23","group_name":"业务","image":"http://i.rxjy.com/Content/appContent/images/notice/yeWu.png","group_id":"3","image_http":null,"image_type":null,"initiator_card":null,"initiator_name":null,"reward_money":null,"notice_count":null,"notice_countSum":null,"task_balance":null,"task_end_time":"0001/01/01","task_name":null,"task_num":null,"task_count_down":null},{"id":0,"cardno":null,"title":null,"txt":null,"state":null,"flag":null,"create_date":"01.01","update_date":"0001-01-01T00:00:00","type":null,"group":"24","group_name":"其它","image":"http://i.rxjy.com/Content/appContent/images/notice/qiTa.png","group_id":"4","image_http":null,"image_type":null,"initiator_card":null,"initiator_name":null,"reward_money":null,"notice_count":null,"notice_countSum":null,"task_balance":null,"task_end_time":"0001/01/01","task_name":null,"task_num":null,"task_count_down":null},{"id":0,"cardno":null,"title":null,"txt":null,"state":null,"flag":null,"create_date":"01.01","update_date":"0001-01-01T00:00:00","type":null,"group":"25","group_name":"红包","image":"http://i.rxjy.com/Content/appContent/images/notice/hongBao.png","group_id":"4","image_http":null,"image_type":null,"initiator_card":null,"initiator_name":null,"reward_money":null,"notice_count":null,"notice_countSum":null,"task_balance":null,"task_end_time":"0001/01/01","task_name":null,"task_num":null,"task_count_down":null}]
     */

    private int StatusCode;
    private Object StatusMsg;
    private List<BodyBean> Body;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public Object getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(Object StatusMsg) {
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
         * id : 0
         * cardno : null
         * title : null
         * txt : null
         * state : null
         * flag : null
         * create_date : 01.01
         * update_date : 0001-01-01T00:00:00
         * type : null
         * group : 21
         * group_name : 活动
         * image : http://i.rxjy.com/Content/appContent/images/notice/huoDong.png
         * group_id : 1
         * image_http : null
         * image_type : null
         * initiator_card : null
         * initiator_name : null
         * reward_money : null
         * notice_count : null
         * notice_countSum : null
         * task_balance : null
         * task_end_time : 0001/01/01
         * task_name : null
         * task_num : null
         * task_count_down : null
         */

        private int id;
        private Object cardno;
        private Object title;
        private Object txt;
        private Object state;
        private Object flag;
        private String create_date;
        private String update_date;
        private Object type;
        private String group;
        private String group_name;
        private String image;
        private String group_id;
        private Object image_http;
        private Object image_type;
        private Object initiator_card;
        private Object initiator_name;
        private Object reward_money;
        private Object notice_count;
        private Object notice_countSum;
        private Object task_balance;
        private String task_end_time;
        private Object task_name;
        private Object task_num;
        private Object task_count_down;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getCardno() {
            return cardno;
        }

        public void setCardno(Object cardno) {
            this.cardno = cardno;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getTxt() {
            return txt;
        }

        public void setTxt(Object txt) {
            this.txt = txt;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getFlag() {
            return flag;
        }

        public void setFlag(Object flag) {
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

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public Object getImage_http() {
            return image_http;
        }

        public void setImage_http(Object image_http) {
            this.image_http = image_http;
        }

        public Object getImage_type() {
            return image_type;
        }

        public void setImage_type(Object image_type) {
            this.image_type = image_type;
        }

        public Object getInitiator_card() {
            return initiator_card;
        }

        public void setInitiator_card(Object initiator_card) {
            this.initiator_card = initiator_card;
        }

        public Object getInitiator_name() {
            return initiator_name;
        }

        public void setInitiator_name(Object initiator_name) {
            this.initiator_name = initiator_name;
        }

        public Object getReward_money() {
            return reward_money;
        }

        public void setReward_money(Object reward_money) {
            this.reward_money = reward_money;
        }

        public Object getNotice_count() {
            return notice_count;
        }

        public void setNotice_count(Object notice_count) {
            this.notice_count = notice_count;
        }

        public Object getNotice_countSum() {
            return notice_countSum;
        }

        public void setNotice_countSum(Object notice_countSum) {
            this.notice_countSum = notice_countSum;
        }

        public Object getTask_balance() {
            return task_balance;
        }

        public void setTask_balance(Object task_balance) {
            this.task_balance = task_balance;
        }

        public String getTask_end_time() {
            return task_end_time;
        }

        public void setTask_end_time(String task_end_time) {
            this.task_end_time = task_end_time;
        }

        public Object getTask_name() {
            return task_name;
        }

        public void setTask_name(Object task_name) {
            this.task_name = task_name;
        }

        public Object getTask_num() {
            return task_num;
        }

        public void setTask_num(Object task_num) {
            this.task_num = task_num;
        }

        public Object getTask_count_down() {
            return task_count_down;
        }

        public void setTask_count_down(Object task_count_down) {
            this.task_count_down = task_count_down;
        }
    }
}
