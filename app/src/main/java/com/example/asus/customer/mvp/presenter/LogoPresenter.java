package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.LogUtils;
import com.example.asus.customer.entity.GuangGaoBean;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.OddNumbersBean2;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.entity.VersionInfo;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.mvp.contract.LogoContract;
import com.example.asus.customer.mvp.model.LoginModel;
import com.example.asus.customer.mvp.model.LogoModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/20.
 */

public class LogoPresenter extends LogoContract.Presenter {

    private String TAG = "LogoPresenter";

    public LogoPresenter(LogoContract.View mView) {
        this.mView = mView;
        mModel = new LogoModel();
    }

    @Override
    public void landLogin(String cardNo, String token) {
        Subscription subscribe = mModel.tokenLogin(cardNo, token)
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
                        mView.loadTextLogin();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        NewUserInfoBean userInfo = JSONUtils.toObject(s, NewUserInfoBean.class);
                        if (userInfo.getStatusCode() == 0) {
                            mView.toLandingSuccess(userInfo.getBody().get(0));
                        } else {
                            mView.responseLoginError(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void tokenLogin(String cardNo, String password, String vCode, String appId) {
        Subscription subscribe = mModel.landLogin(cardNo, password, vCode, appId)
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
                        mView.loadTextLogin();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        TokenInfo userInfo = JSONUtils.toObject(s, TokenInfo.class);

                        if (userInfo.getStatusCode() == 0) {
                            mView.getTokenInfoData(userInfo.getBody());
                        } else {
                            mView.responseLoginError(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getOddNumData2(String appid) {
        Subscription subscribe = mModel.getOddNumData2(appid)
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
                        mView.responseLoginError("获取任务单号失败");
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("aa", s);
                        OddNumbersBean2 userInfo = JSONUtils.toObject(s, OddNumbersBean2.class);

                        if (userInfo.getStatusCode() == 0) {
                            List<OddNumbersBean2.BodyBean> body1 = userInfo.getBody();
                            if (body1.size() != 0) {
                                mView.getOddNumData2(body1.get(0).getPN_Onumber());
                            }
                        } else {
                            mView.responseLoginError("获取任务单号失败");
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getOddNumData(String appid) {
        Subscription subscribe = mModel.getOddNumData(appid)
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
                        mView.responseLoginError("你没有项目，无法登陆");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        OddNumbersBean userInfo = JSONUtils.toObject(s, OddNumbersBean.class);

                        if (userInfo.getStatusCode() == 0) {
                            OddNumbersBean.BodyBean body = userInfo.getBody();
                            if (userInfo.getStatusCode() == 0) {
                                mView.getOddNumData(body);
                            } else {
                                mView.responseLoginError("你没有项目，无法登陆");
                            }

                        } else {
                            mView.responseLoginError(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getVersionInfo(int version) {
        Subscription subscribe = mModel.getVersionInfo(version)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "获取版本信息败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("hjhvesonsssss", s);
                        VersionInfo info = JSONUtils.toObject(s, VersionInfo.class);
                        if (info.getStatusCode() == 1 && info.getBody() != null) {
                            VersionInfo.Version data = info.getBody();
                            mView.responseVersionData(data);
                        } else {
                            mView.responseVersionDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getAdData(String id, String cardNo) {
        Subscription subscribe = mModel.getAdData(id, cardNo)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "获取开屏广告数据失败 = " + e.toString());
                        onCompleted();
                        mView.getAdDataErro("");
                    }

                    @Override
                    public void onNext(String s) {
                        //LogUtils.d("获取开屏广告数据失败", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int statusCode = jsonObject.getInt("StatusCode");
                            String statusMsg = jsonObject.getString("StatusMsg");
                            if (statusCode == 0) {
                                GuangGaoBean guangGaoBean = JSONUtils.toObject(s, GuangGaoBean.class);
                                if (guangGaoBean != null) {
                                    mView.getAdData(guangGaoBean);
                                } else {
                                    mView.getAdDataErro(statusMsg);
                                }
                            } else {
                                mView.getAdDataErro(statusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        addSubscribe(subscribe);
    }


}
