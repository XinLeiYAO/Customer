package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.InfoMessageContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by hjh on 2017/11/22.
 */

public class InfoMessageModel implements InfoMessageContract.Model{
    @Override
    public Observable<String> getInfoMessageList(String cardNo) {
        return ApiEngine.getInstance().getRsApiService()
                .getInformMessage(cardNo,4)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> toSeeDetails(String id) {
        return ApiEngine.getInstance().getRsApiService()
                .getToSeeDetails(id)
                .compose(RxSchedulers.<String>switchThread());
    }
}
