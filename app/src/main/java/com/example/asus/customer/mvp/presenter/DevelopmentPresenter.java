package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.mvp.contract.DevelopmentContract;
import com.example.asus.customer.mvp.contract.ProcessContract;
import com.example.asus.customer.mvp.model.DevelopmentModel;
import com.example.asus.customer.mvp.model.ProcessModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/6/4.
 */

public class DevelopmentPresenter extends DevelopmentContract.Presenter {
    private String TAG="ProcessPresenter";

    public  DevelopmentPresenter(DevelopmentContract.View mView) {
        this.mView = mView;
        mModel = new DevelopmentModel();
    }

    @Override
    public void getProgssData(String rwdId) {
        Subscription subscribe = mModel.getProgssData(rwdId)
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
                        mView.showMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        FindInfo picture = JSONUtils.toObject(s, FindInfo.class);
                        if(picture.getStatusCode()==0){
                            mView.getProgssData(picture.getBody());
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
