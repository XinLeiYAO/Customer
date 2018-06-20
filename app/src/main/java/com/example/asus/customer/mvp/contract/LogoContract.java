package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;

import rx.Observable;

/**
 * Created by asus on 2018/4/20.
 */

public interface LogoContract {
    interface View extends BaseView {


        void responseLoginError(String msg);

        void showDialog();

        void hideDialog();

        void toLandingSuccess(UserInfo.BodyBean bodyBean);

        void getTokenInfoData(TokenInfo.BodyBean bodyBean);
        void getOddNumData(String appid);

    }

    interface Model extends BaseModel {

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

    abstract class Presenter extends BasePresenter<LogoContract.View, LogoContract.Model> {
        public abstract void landLogin(
                String cardNo,
                String token

        );

        public abstract void tokenLogin(
                String cardNo,
                String password,
                String vCode,
                String appId
        );
        public abstract void getOddNumData(

                String appid
        );
    }
}
