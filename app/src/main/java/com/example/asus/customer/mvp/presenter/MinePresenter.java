package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.mvp.contract.MineContract;
import com.example.asus.customer.mvp.model.CurrentModel;
import com.example.asus.customer.mvp.model.MineModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/17.
 */

public class MinePresenter extends MineContract.Presenter {

    private String TAG = "CurrentPresenter";

    public MinePresenter(MineContract.View mView) {
        this.mView = mView;
        mModel = new MineModel();
    }

    @Override
    public void loadCustomerInformation(String cardNo) {
        Subscription subscribe = mModel.loadCustomerInformation(cardNo)
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
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        MineUserInfo mineUserInfo = JSONUtils.toObject(s, MineUserInfo.class);
                        if (mineUserInfo.getStatusCode() == 0) {
                            MineUserInfo.BodyBean body = mineUserInfo.getBody();
                            mView.loadCustomerInformation(body);
                        } else {
                            mView.showMessage(mineUserInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getUnreadData(Map<String, String> map) {
        Subscription subscribe = mModel.getUnreadData(map)
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
                        onCompleted();
//                        mView.getUnreadDataErro("未读消息获失败...");
                    }

                    @Override
                    public void onNext(String s) {
//                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        try {
                            int statusCode = new JSONObject(s).getInt("StatusCode");
                            if (statusCode == 0) {
                                UnreadBean unreadBean = JSONUtils.toObject(s, UnreadBean.class);
                                mView.getUnreadData(unreadBean);
                            } else {
//                                mView.getUnreadDataErro("未读消息获失败...");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        addSubscribe(subscribe);
    }
}
