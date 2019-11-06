package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.GuangGaoBean;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.entity.VersionInfo;

import rx.Observable;

/**
 * Created by asus on 2018/4/20.
 */

public interface LogoContract {
    interface View extends BaseView {


        void responseLoginError(String msg);

        void showDialog();

        void hideDialog();

        void toLandingSuccess(NewUserInfoBean.BodyBean bodyBean);

        void getTokenInfoData(TokenInfo.BodyBean bodyBean);

        void getOddNumData(OddNumbersBean.BodyBean body);

        void getOddNumData2(String body);

        void loadTextLogin();

        void responseVersionData(VersionInfo.Version data);

        void responseVersionDataError(String msg);

        void getAdData(GuangGaoBean guangGaoBean);

        void getAdDataErro(String erro);
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

        Observable<String> getVersionInfo(
                int version
        );

        Observable<String> getOddNumData2(
                String appid
        );

        Observable<String> getAdData(
                String id,
                String cardNo
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

        public abstract void getVersionInfo(
                int version
        );

        public abstract void getOddNumData2(

                String appid
        );

        public abstract void getAdData(
                String id,
                String cardNo
        );
    }
}
