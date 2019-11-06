package com.example.asus.customer.commons;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAA on 2017/7/25.
 */

public class Constants {


    public static final String ORDERNUMBER = "ordernumber";
    public static final String PICTURELIST = "picturelist";
    public static final String IS_SETALIAS = "setalias";//是否设置别名
    public static String IS_LOGIN = "is_login";
    public static String IS_ENTER = "is_enter";
    public static String jpushname = "IsSetAlias" + App.cardNo;
    public static String PHOME = "card_no"; // 卡号
    public static String PN_Onumber = "pn_onumber"; // 卡号
    public static String UnreadData = "UnreadData"; // 卡号
    public static String MessageNum = "Message";
    public static String Is_YinDao = "yindao"; // 是否顯示引導頁
    public static String ci_clientName = "ci_clientName"; // 项目名称
    public static String ci_designerCard = "ci_designerCard"; // 设计师卡号
    public static String ci_designerName = "ci_designerName"; // 设计师名称
    public static String ci_stage = "ci_stage";
    public static String PASSWORD = "token";
    public static String PHONENUM = "phonenum";//手机号
    public static String designerPhone = "designerPhone";//設計師手机号
    public static final String JUMPLIST = "Jump";
    public static final String INDEXES = "indexes";
    public static final String TITLE = "title";
    public static List<Activity> activityList = new ArrayList<>();
    //向量房传递详情
    public static final String VectorRoomTransferDetails = "vectorroomtransferdetails";

    //向忘记密码界面传递type
    public static final String ACTION_TO_FORGET_PWD_TYPE = "action_to_forget_pwd_type";
    //    public static final String WEBURL_MOREBANNER = "http://m.rxjy.com/";
    public static final String WEBURL_MOREBANNER = "http://www.rxjy.com/";//更多公司官网

    //向修改信息界面传递KeyValue
    public static final String ACTION_TO_UPD_USER_INFO_KEY_VALUE = "action_to_upd_user_info_key_value";
    //向修改信息界面传递Key
    public static final String ACTION_TO_UPD_USER_INFO_KEY = "action_to_upd_user_info_key";
    //向修改信息界面传递Value
    public static final String ACTION_TO_UPD_USER_INFO_VALUE = "action_to_upd_user_info_value";

    //向新闻详情界面传递id
    public static final String ACTION_TO_NEWS_DETAIL_NEWS_ID = "action_to_news_detail_news_id";

    public static final String APPID = "APPID";
    public static final String MIMA = "MIMA";
    public static final int BASE_CODE = 1000;
    //设置别名标记
    public static final int MSG_SET_ALIAS = BASE_CODE + 1;
}
