package com.example.asus.customer.mvp.model;

import android.util.Log;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public class CurrentModel implements CurrentContract.Model {
    @Override
    public Observable<String> loadHomedata(String orderNo) {

        return ApiEngine.getInstance().getEsApiService()
                .loadHomeData(orderNo)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> loadDesignDescription(String rwdId) {
        return ApiEngine.getInstance().getEsApiService()
                .loadDesignData(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> loadCurreerPhotoData(String orderNo) {
        return ApiEngine.getInstance().getEsApiService()
                .getCurreerPhotoData(orderNo)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getProgssData(String rwdId) {
        return ApiEngine.getInstance().getEsApiService()
                .getProgssData(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getRecommendData(String rwdId) {
        return ApiEngine.getInstance().getFsApiService()
                .getRecommendData(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }
}
