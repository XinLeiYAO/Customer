package com.example.asus.customer.mvp.presenter;

import android.util.Log;


import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.SubInfo;
import com.example.asus.customer.mvp.contract.ForgetPwdContract;
import com.example.asus.customer.mvp.model.ForgetPwdModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/7/27.
 */

public class ForgetPwdPresenter extends ForgetPwdContract.Presenter {

    public static final String TAG = "ForgetPwdPresenter";

    public ForgetPwdPresenter(ForgetPwdContract.View mView) {
        this.mView = mView;
        mModel = new ForgetPwdModel();
    }

    @Override
    public void updatePassword(String phoneNum, String newPassword, String vCode) {
        Subscription subscribe = mModel.updatePassword(phoneNum, newPassword, vCode)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "修改密码失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseUpdatePassword();
                        } else {
                            mView.responseUpdatePasswordError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getVCode(String phoneNum) {
        Subscription subscribe = mModel.getVCode(phoneNum)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取验证码失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {

                        } else {
                            mView.responseVCodeError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
