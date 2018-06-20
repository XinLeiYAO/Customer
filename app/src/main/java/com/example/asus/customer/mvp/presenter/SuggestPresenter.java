package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.mvp.contract.SuggestContract;
import com.example.asus.customer.mvp.model.SuggestModel;
import com.luck.picture.lib.entity.LocalMedia;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by AAA on 2017/9/22.
 */

public class SuggestPresenter extends SuggestContract.Presenter {

    public static final String TAG = "SuggestPresenter";

    public SuggestPresenter(SuggestContract.View mView) {
        this.mView = mView;
        mModel = new SuggestModel();
    }

    @Override
    public void subSuggestInfo(String the_name, String contact, String service_description, int complaints_type,String contract_no, List<LocalMedia> imgList) {
        Subscription subscribe = mModel.subSuggestInfo(the_name, contact, service_description, complaints_type, contract_no,imgList)
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
                        Log.e(TAG, "投诉失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                       // SubInfo info = JSONUtils.toObject(s,SubInfo.class);
                        Log.e("tag",s);
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0){
                                mView.responseSuggestData();
                            }else {
                                String StatusMsg = jsonObject.getString("StatusMsg");
                                mView.responseSuggestDataError(StatusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        addSubscribe(subscribe);
    }
}
