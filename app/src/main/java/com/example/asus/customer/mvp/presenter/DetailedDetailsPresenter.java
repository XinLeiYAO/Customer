package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.mvp.contract.DetailedDetailsContract;
import com.example.asus.customer.mvp.model.CurrentModel;
import com.example.asus.customer.mvp.model.DetailedDetailsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/28.
 */

public class DetailedDetailsPresenter extends DetailedDetailsContract.Presenter {
    private String TAG="CurrentPresenter";

    public DetailedDetailsPresenter(DetailedDetailsContract.View mView) {
        this.mView = mView;
        mModel = new DetailedDetailsModel();
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
//                        Log.e(TAG,e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {

                        List<PhotoDetailsBean> mlist= new Gson().fromJson(s, new TypeToken<List<PhotoDetailsBean>>() {
                        }.getType());
                        mView.volumeRoomPhooto(mlist);
                    }
                });
        addSubscribe(subscribe);
    }
}
