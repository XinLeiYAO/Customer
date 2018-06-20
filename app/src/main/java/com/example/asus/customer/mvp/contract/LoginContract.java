package com.example.asus.customer.mvp.contract;

import android.icu.text.UnicodeSet;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;

import rx.Observable;

/**
 * Created by asus on 2018/4/18.
 */

public interface LoginContract  {

    interface  View extends BaseView{

        void showDialog();

        void hideDialog();


        void toLandingSuccess(UserInfo.BodyBean bodyBean);

        void showmessage(String message);
        void getCheckInfo(CheckInfo checkInfo);

        void getTokenByCodeData(CheckInfo checkInfo);

        void getTokenInfoData(TokenInfo.BodyBean bodyBean);

        void getOddNumData(String appid);

        void showLoadMessage(String msg);
    }
    interface Model extends BaseModel{
        //GET /actionapi/AppLogin/
        Observable<String> getCheckUserInfo(
                String Phone,
                String AppId
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
    }
    abstract class Presenter extends BasePresenter<LoginContract.View, LoginContract.Model> {
        public abstract void getCheckUserInfo(
                String Phone,
                String AppId
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
    }

}
