package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.OptimizationBean;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.ProcessContract;
import com.example.asus.customer.mvp.model.ProcessModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/17.
 */

public class ProcessPresenter extends ProcessContract.Presenter {

    private String TAG="ProcessPresenter";

    public  ProcessPresenter(ProcessContract.View mView) {
        this.mView = mView;
        mModel = new ProcessModel();
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
                        Log.e("ssss",s);
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
                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG,s);
                        MineUserInfo mineUserInfo = JSONUtils.toObject(s, MineUserInfo.class);
                        if(mineUserInfo.getStatusCode()==200){
                            MineUserInfo.BodyBean body = mineUserInfo.getBody();
                            mView.loadCustomerInformation(body);
                        }else {
                            mView.showMessage(mineUserInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void loadHomedata(String rwdId) {
        Subscription subscribe = mModel.loadHomedata(rwdId)
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
                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG,s);
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
                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("tag",s);
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
                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("bug",s);
                        OptimizationBean picture = JSONUtils.toObject(s, OptimizationBean.class);
                        if(picture.getStatusCode()==0){
                            picture.getBody();
                           // mView.getOptimizationBean(OptimizationBean);
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void volumeRoomPhooto(String rwdId) {
        Subscription subscribe = mModel.volumeRoomPhooto(rwdId)
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
                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {

                        Log.e("tag",s);
                        List<PhotoDetailsBean> mlist= new Gson().fromJson(s, new TypeToken<List<PhotoDetailsBean>>() {
                        }.getType());
                        mView.volumeRoomPhooto(mlist);
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getOptimizationBean() {
        Subscription subscribe = mModel.getOptimizationBean()
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
                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {

                        OptimizationBean picture = JSONUtils.toObject(s, OptimizationBean.class);
                        if(picture.getStatusCode()==0){
                            mView.getOptimizationBean(picture);
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getFindData(String tb_diqu, String ci_Type, String ca_IntentionalStyle, String ci_Area, String tb_ca_DecBudgetPrice) {
        Subscription subscribe = mModel.getFindData(tb_diqu,ci_Type,ca_IntentionalStyle,ci_Area,tb_ca_DecBudgetPrice)
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
                        //  Log.e("find",s);
                        Log.e("find",s);
                        FindInformationBean picture = JSONUtils.toObject(s, FindInformationBean.class);



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
                        Log.e("find",s);
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
