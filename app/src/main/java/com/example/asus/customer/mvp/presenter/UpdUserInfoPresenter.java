package com.example.asus.customer.mvp.presenter;

import android.util.Log;


import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.ImgBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.entity.SubInfo;
import com.example.asus.customer.mvp.contract.UpdUserInfoContract;
import com.example.asus.customer.mvp.model.UpdUserInfoModel;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AAA on 2017/7/28.
 */

public class UpdUserInfoPresenter extends UpdUserInfoContract.Presenter {

    public static final String TAG = "UpdUserInfoPresenter";

    public UpdUserInfoPresenter(UpdUserInfoContract.View mView) {
        this.mView = mView;
        mModel = new UpdUserInfoModel();
    }

    @Override
    public void updateUserInfo(String token, String cardNo, String key, String value) {
        Subscription subscribe = mModel.updateUserInfo(token, cardNo, key, value)
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
                    }

                    @Override
                    public void onNext(String s) {
                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
                        if (info.getStatusCode() == 0) {
                            mView.responseUpdateData();
                        } else if (info.getStatusCode() == 104) {
                            mView.reLogin(info.getStatusMsg());
                        } else {
                            mView.responseUpdateDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    //上传头像
    @Override
    public void upHeaderPicture(String type, String cardNo, String imgUrl) {

        Log.d("TAG", "Image_KeHu头像: -->Url"+imgUrl);
        mModel.upHeaderPicture(type, cardNo, imgUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.e(TAG, "上传头像失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        ImgBean info = JSONUtils.toObject(s, ImgBean.class);
                        if (info.getStatusCode() == 0) {
                            mView.imageIconData(info);
                        }/* else if (info.getStatusCode() == 104) {
                            mView.reLogin(info.getStatusMsg());
                        }*/ else {
                            mView.responseUpPictureError(info.getStatusMsg());
                        }
                    }


                });

    }

    @Override
    public void getOssData() {
        Subscription subscribe = mModel.initOss()
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
                        //todo
//                        mView.responseUpdateMessageError("上传失败");
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        //todo
                        OSSBean ossBean = JSONUtils.toObject(s, OSSBean.class);
                        if(ossBean!=null && ossBean.getStatusCode().equals("200")){
                            mView.OssData(ossBean);
                        }else{
//                            mView.responseMsgError(ossBean.getStatusCode()); 待处理
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
