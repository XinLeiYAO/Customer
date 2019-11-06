package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.CodeLoginBean;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.TokenInfo;

import rx.Observable;

/**
 * Created by asus on 2018/4/18.
 */

public interface LoginContract {

    interface View extends BaseView {

        void showDialog();

        void hideDialog();


        void toLandingSuccess(NewUserInfoBean.BodyBean bodyBean);

        void showmessage(String message);

        void getCheckInfo(CheckInfo checkInfo);

        void getTokenByCodeData(CheckInfo checkInfo);

        void getTokenInfoData(TokenInfo.BodyBean bodyBean);

        void getOddNumData(OddNumbersBean.BodyBean body);

        void getOddNumData2(String body);

        void getLoginCode(String statusMsg);//获取登录所用验证码

        void getLoginCodeErro(String statusMsg);

        void getCode_Login(CodeLoginBean codeLoginBean);//验证码登录

        void getCode_LoginErro(String erro);

        void showLoadMessage(String msg);
    }

    interface Model extends BaseModel {
        //GET /actionapi/AppLogin/
        Observable<String> getCheckUserInfo(
                String Phone,
                String AppId
        );

        Observable<String> getLoginCode(
                String phone,
                String postId
        );

        Observable<String> getCode_Login(
                String cardNo,//手机号或卡号
                String password,//密码
                String vCode,//验证码
                String appId,//2 牛经济  3 瑞祥平台 4 瑞祥客户 5瑞祥施工 6瑞祥材料 7工人联盟 8瑞祥设计 9瑞祥
                String isLogin//使用验证码登录时传1，否则不传或传空
        );

        Observable<String> getTokenByCode(
                String phone,
                String AppId

        );

        Observable<String> landLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );

        Observable<String> tokenLogin(
                String cardNo,
                String token

        );

        Observable<String> getOddNumData(
                String appid
        );

        Observable<String> getOddNumData2(
                String appid
        );
    }

    abstract class Presenter extends BasePresenter<LoginContract.View, LoginContract.Model> {
        public abstract void getCheckUserInfo(
                String Phone,
                String AppId
        );

        public abstract void getLoginCode(
                String phone,
                String postId
        );

        public abstract void getCode_Login(
                String cardNo,//手机号或卡号
                String password,//密码
                String vCode,//验证码
                String appId,//2 牛经济  3 瑞祥平台 4 瑞祥客户 5瑞祥施工 6瑞祥材料 7工人联盟 8瑞祥设计 9瑞祥
                String isLogin//使用验证码登录时传1，否则不传或传空
        );

        public abstract void getTokenByCode(
                String Phone,
                String AppId

        );

        public abstract void tokenLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );

        public abstract void landLogin(
                String cardNo,
                String token
        );

        public abstract void getOddNumData(

                String appid
        );

        public abstract void getOddNumData2(

                String appid
        );
    }

}
