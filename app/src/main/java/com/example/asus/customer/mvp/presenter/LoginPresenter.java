package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.mvp.model.LoginModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/18.
 */

public class LoginPresenter extends LoginContract.Presenter {
    private String TAG = "LoginPresenter";

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        mModel = new LoginModel();
    }


    @Override
    public void getCheckUserInfo(String Phone, String AppId) {
        Subscription subscribe = mModel.getCheckUserInfo(Phone, AppId)
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
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        CheckInfo userInfo = JSONUtils.toObject(s, CheckInfo.class);
                        mView.getCheckInfo(userInfo);
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getTokenByCode(String Phone, String AppId) {
        Subscription subscribe = mModel.getTokenByCode(Phone, AppId)
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
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        CheckInfo userInfo = JSONUtils.toObject(s, CheckInfo.class);
                        mView.getTokenByCodeData(userInfo);

                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void tokenLogin(String cardNo, String password, String vCode, String appId) {
        Subscription subscribe = mModel.landLogin(cardNo, password, vCode, appId)
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
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        TokenInfo userInfo = JSONUtils.toObject(s, TokenInfo.class);
                        if (userInfo.getStatusCode() == 0) {
                            mView.getTokenInfoData(userInfo.getBody());
                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }


                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void landLogin(String cardNo, String token) {
        Subscription subscribe = mModel.tokenLogin(cardNo, token)
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
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        UserInfo userInfo = JSONUtils.toObject(s, UserInfo.class);
                        if (userInfo.getStatusCode() == 0) {
                            mView.toLandingSuccess(userInfo.getBody());
                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getOddNumData(String appid) {
        Subscription subscribe = mModel.getOddNumData(appid)
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
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("aa", s);
                        OddNumbersBean userInfo = JSONUtils.toObject(s, OddNumbersBean.class);

                        if (userInfo.getStatusCode() == 0) {
                            List<OddNumbersBean.BodyBean> body = userInfo.getBody();
                            if(body.size()==0){
                                mView.showmessage("您没有项目，无法登陆");
                            }else {
                                mView.getOddNumData(body.get(0).getPN_Onumber());
                            }
                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
