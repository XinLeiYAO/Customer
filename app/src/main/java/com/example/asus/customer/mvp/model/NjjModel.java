package com.example.asus.customer.mvp.model;

import android.util.Log;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.NjjContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/5/28.
 */

public class NjjModel implements NjjContract.Model {
    @Override
    public Observable<String> getProjectStatus(String rwdid) {
//        Log.e("rwdid",rwdid);
        return ApiEngine.getInstance().getEsApiService()
                .getProjectStatus(rwdid)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getLoginTime(String rwdid) {
        return ApiEngine.getInstance().getGZSApiService()
                .getLoginTime(rwdid)
                .compose(RxSchedulers.<String>switchThread());
    }

}
