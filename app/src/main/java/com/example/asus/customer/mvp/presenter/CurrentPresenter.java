package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.mvp.model.CurrentModel;
import com.example.asus.customer.mvp.model.LoginModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/17.
 */

public class CurrentPresenter  extends CurrentContract.Presenter{

    private String TAG="CurrentPresenter";

    public  CurrentPresenter(CurrentContract.View mView) {
        this.mView = mView;
        mModel = new CurrentModel();
    }


    @Override
    public void loadHomedata(String orderNo) {
        Subscription subscribe = mModel.loadHomedata(orderNo)
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
//                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Picture picture = JSONUtils.toObject(s, Picture.class);
                        if(picture.getStatusCode()==0){
                            mView.getCurrentData(picture.getBody());
                        }else {
                             mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void loadDesignDescription(String rwdId) {
        Subscription subscribe = mModel.loadDesignDescription(rwdId)
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
//                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        DesignInfo picture = JSONUtils.toObject(s, DesignInfo.class);
                        if(picture.getStatusCode()==0){
                            mView.loadDesignDescription(picture.getBody());
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void loadCurreerPhotoData(String orderNo) {
        Subscription subscribe = mModel.loadCurreerPhotoData(orderNo)
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
                        CurreerPhotoDataBean picture = JSONUtils.toObject(s, CurreerPhotoDataBean.class);
                        if(picture.getStatusCode()==0){
                            mView.loadCurreerPhotoData(picture.getBody());
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
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
//                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        ProgssInfo picture = JSONUtils.toObject(s, ProgssInfo.class);
                        if(picture.getStatusCode()==0){
                            mView.getProgssData(picture.getBody());
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getRecommendData(String rwdId) {
        Subscription subscribe = mModel.getRecommendData(rwdId)
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
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {

                        RecommendBean picture = JSONUtils.toObject(s, RecommendBean.class);
                        if(picture.getStatusCode()==0){
                            mView.getRecommendData(picture);
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
