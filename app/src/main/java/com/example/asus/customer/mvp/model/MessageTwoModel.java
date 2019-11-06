package com.example.asus.customer.mvp.model;


import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.MessageTwoContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by hjh on 2018/3/9.
 */

public class MessageTwoModel implements MessageTwoContract.Model{
    @Override
    public Observable<String> getInfoMessageTwoList(String cardNo, String Group) {
        return ApiEngine.getInstance().getRsApiService()
                .getInformListMessage(cardNo,Group)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> toSeeDetails(String id) {
        return ApiEngine.getInstance().getRsApiService()
                .getToSeeDetails(id)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> setMessage(String CardNo, String Group) {
        return ApiEngine.getInstance().getRsApiService()
                .setMessage(CardNo, Group)
                .compose(RxSchedulers.<String>switchThread());
    }
}
