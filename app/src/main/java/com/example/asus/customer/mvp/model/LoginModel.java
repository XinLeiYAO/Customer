package com.example.asus.customer.mvp.model;

import android.util.Log;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/4/18.
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<String> getCheckUserInfo(String Phone, String AppId) {
        return ApiEngine.getInstance().getRsApiService()
                .getCheckUserInfo(Phone,AppId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getTokenByCode(String phone, String AppId) {
        return ApiEngine.getInstance().getRsApiService()
                .getInsideVcodeLanding(phone,AppId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> landLogin(String cardNo, String password, String vCode, String appId) {
        return ApiEngine.getInstance().getRsApiService()
                .getAppLogin(cardNo,password,vCode,appId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> tokenLogin(String cardNo, String token) {
        return ApiEngine.getInstance().getRsApiService()
                .getLoginUserInfo(cardNo,token)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getOddNumData(String appid) {
        return ApiEngine.getInstance().getRsApiService()
                .getCustomerProjectNoByAppId(appid)
                .compose(RxSchedulers.<String>switchThread());
    }
}
