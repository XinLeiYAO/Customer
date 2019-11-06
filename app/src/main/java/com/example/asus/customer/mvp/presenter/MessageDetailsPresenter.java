package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.entity.MessageDetailsBean;
import com.example.asus.customer.mvp.contract.MessageDetailsContract;
import com.example.asus.customer.mvp.model.MessageDetailsModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by hjh on 2018/3/9.
 */

public class MessageDetailsPresenter extends MessageDetailsContract.Presenter {

    public MessageDetailsPresenter(MessageDetailsContract.View view) {
        this.mView = view;
        mModel = new MessageDetailsModel();
    }

    @Override
    public void getInfoMessageDList(String Id) {
        Subscription subscribe = mModel.getInfoMessageDList(Id)
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
//                        Log.e("", "获取通知数据sss失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        MessageDetailsBean info = JSONUtils.toObject(s, MessageDetailsBean.class);
                        if (info.getStatusCode() == 0) {
                            if(!StringUtils.isEmpty(info.getBody())){
                                mView.responseInfoMessageD(info);
                            }else{
//                                Log.e("暂无数据。。。","!!!!!");
                            }
                        } else {
                            mView.responseInfoMessageDError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
