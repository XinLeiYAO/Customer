package com.example.asus.customer.mvp.model;



import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.MessageDetailsContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by hjh on 2018/3/9.
 */

public class MessageDetailsModel implements MessageDetailsContract.Model{
    @Override
    public Observable<String> getInfoMessageDList(String Id) {
        return ApiEngine.getInstance().getRsApiService()
                .getInformDetailsMessage(Id)
                .compose(RxSchedulers.<String>switchThread());
    }
}
