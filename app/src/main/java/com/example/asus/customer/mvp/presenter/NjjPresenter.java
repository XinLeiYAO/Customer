package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.ProjectBean;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.mvp.contract.NewsListContract;
import com.example.asus.customer.mvp.contract.NjjContract;
import com.example.asus.customer.mvp.model.NewsListModel;
import com.example.asus.customer.mvp.model.NjjModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/5/28.
 */

public class NjjPresenter extends NjjContract.Presenter {
    public static final String TAG = "NjjPresenter";

    public NjjPresenter(NjjContract.View mView) {
        this.mView = mView;
        mModel = new NjjModel();
    }
    @Override
    public void getProjectStatus(String rwdid) {
        Subscription subscribe = mModel.getProjectStatus(rwdid)
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
                        ProjectBean userInfo = JSONUtils.toObject(s, ProjectBean.class);
                        if(userInfo.getStatusCode()==0){
                            mView.getProjectStatus(userInfo);
                        }else {
                            mView.showMessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
