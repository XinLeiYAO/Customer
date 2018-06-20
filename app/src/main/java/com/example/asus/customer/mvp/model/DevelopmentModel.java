package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.DevelopmentContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/6/4.
 */

public class DevelopmentModel implements DevelopmentContract.Model {
    @Override
    public Observable<String> getProgssData(String rwdId) {
        return ApiEngine.getInstance().getEsApiService()
                .getFindData(rwdId, 0)
                .compose(RxSchedulers.<String>switchThread());
    }
}
