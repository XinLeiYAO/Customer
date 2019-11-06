package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.ObjectDataBean;
import com.example.asus.customer.entity.ProjectBean;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.entity.VersionInfo;
import com.example.asus.customer.mvp.contract.NewsListContract;
import com.example.asus.customer.mvp.contract.NjjContract;
import com.example.asus.customer.mvp.model.NewsListModel;
import com.example.asus.customer.mvp.model.NjjModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

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
                        Log.e(TAG, e.getMessage());
                        onCompleted();
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        ProjectBean userInfo = JSONUtils.toObject(s, ProjectBean.class);
                        if (userInfo.getStatusCode() == 0) {
                            mView.getProjectStatus(userInfo);
                        } else {
                            mView.showMessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getLoginTime(String rwdid) {
        Subscription subscribe = mModel.getLoginTime(rwdid)
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
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        try {
                            int statusCode = new JSONObject(s).getInt("StatusCode");
//                            ProjectBean userInfo = JSONUtils.toObject(s, ProjectBean.class);
                            if (statusCode == 1) {
//                                mView.getProjectStatus(userInfo);
                            } else {
//                                mView.showMessage(userInfo.getStatusMsg());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        addSubscribe(subscribe);
    }

//    @Override
//    public void getObjectData(Map<String, String> map) {
//        Subscription subscribe = mModel.getObjectData(map)
//                .subscribe(new Subscriber<String>() {
//
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        onCompleted();
//                        mView.getObjectDataErro("项目名称获取失败...");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
////                        SubInfo info = JSONUtils.toObject(s, SubInfo.class);
//                        try {
//                            int statusCode = new JSONObject(s).getInt("StatusCode");
//                            if (statusCode == 0) {
//                                ObjectDataBean objectDataBean = JSONUtils.toObject(s, ObjectDataBean.class);
//                                mView.getObjectData(objectDataBean);
//                            } else {
//                                mView.getObjectDataErro("项目名称获取失败...");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//        addSubscribe(subscribe);
//    }


}
