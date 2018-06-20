package com.example.asus.customer.mvp.model;

import android.util.Log;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.ProcessContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public class ProcessModel implements ProcessContract.Model {
    @Override
    public Observable<String> getProgssData(String rwdId) {
        return ApiEngine.getInstance().getEsApiService()
                .getProgssData(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> loadCustomerInformation(String cardNo) {

        return ApiEngine.getInstance().getGsApiService()
                .findVipMsgByCardNo(cardNo)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> loadHomedata(String rwdId) {
        return ApiEngine.getInstance().getEsApiService()
                .loadHomeData(rwdId)
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
    public Observable<String> volumeRoomPhooto(String rwdId) {
        return ApiEngine.getInstance().getPhApiService()
                .getVolumeRoompicture(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getOptimizationBean() {
        return ApiEngine.getInstance().getFsApiService()
                .getOptimizationsData()
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getFindData(String tb_diqu, String ci_Type, String ca_IntentionalStyle, String ci_Area, String tb_ca_DecBudgetPrice) {
        return ApiEngine.getInstance().getFsApiService()
                .getLoAddData(tb_diqu, ci_Type,ca_IntentionalStyle,ci_Area,tb_ca_DecBudgetPrice)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getRecommendData(String rwdId) {
        Log.e("rwdid",rwdId);
        return ApiEngine.getInstance().getFsApiService()
                .getRecommendData(rwdId)
                .compose(RxSchedulers.<String>switchThread());
    }
}
