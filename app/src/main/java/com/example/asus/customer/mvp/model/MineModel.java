package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.MineContract;
import com.example.asus.customer.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public class MineModel implements MineContract.Model {
    @Override
    public Observable<String> loadCustomerInformation(String cardNo) {
        return ApiEngine.getInstance().getRsApiService()
                .findVipMsgByCardNo(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getUnreadData(Map<String, String> map) {
        return ApiEngine.getInstance().getFsApiService()
                .getUnreadData(map)
                .compose(RxSchedulers.<String>switchThread());
    }
}
