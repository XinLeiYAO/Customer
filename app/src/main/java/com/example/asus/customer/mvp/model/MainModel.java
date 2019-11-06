package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.MainContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/10/20.
 */

public class MainModel implements MainContract.Model {
    @Override
    public Observable<String> getVersionInfo(int version) {
        return ApiEngine.getInstance().getRsApiService()
                .getVersionInfo(version,4)
                .compose(RxSchedulers.<String>switchThread());
    }
}
