package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.DetailedDetailsContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/4/28.
 */

public class DetailedDetailsModel implements DetailedDetailsContract.Model {
    @Override
    public Observable<String> volumeRoomPhooto(String rwdId) {
        return ApiEngine.getInstance().getPhApiService()
                .getVolumeRoompicture(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }
}
