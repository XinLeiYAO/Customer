package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.LogoContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/4/20.
 */

public class LogoModel implements LogoContract.Model {


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