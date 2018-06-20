package com.example.asus.customer.mvp.model;


import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.ForgetPwdContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by AAA on 2017/7/27.
 */

public class ForgetPwdModel implements ForgetPwdContract.Model {
    @Override
    public Observable<String> updatePassword(String phoneNum, String newPassword, String vCode) {
        return ApiEngine.getInstance().getRsApiService()
                .updatePasswordByForget(phoneNum, newPassword, vCode)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getVCode(String phoneNum) {
        return ApiEngine.getInstance().getRsApiService()
                .getVCodeForgetPassword(phoneNum)
                .compose(RxSchedulers.<String>switchThread());
    }
}
