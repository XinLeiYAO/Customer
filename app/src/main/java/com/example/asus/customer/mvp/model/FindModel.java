package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.FindContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public class FindModel implements FindContract.Model {

    @Override
    public Observable<String> getProgssData(String rwdId) {
        return ApiEngine.getInstance().getEsApiService()
                .getFindData(rwdId, 0)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getFindData(String tb_diqu, String ci_Type, String ca_IntentionalStyle, String ci_Area, String tb_ca_DecBudgetPrice) {
        return ApiEngine.getInstance().getFsApiService()
                .getLoAddData(tb_diqu, ci_Type,ca_IntentionalStyle,ci_Area,tb_ca_DecBudgetPrice)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getContractData(String rwdId, String ca) {
        return ApiEngine.getInstance().getFsApiService()
                .getContractData(rwdId, ca)
                .compose(RxSchedulers.<String>switchThread());
    }


}
