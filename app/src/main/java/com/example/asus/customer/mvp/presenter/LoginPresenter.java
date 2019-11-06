package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.CodeLoginBean;
import com.example.asus.customer.entity.LoginInfoNew;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.OddNumbersBean2;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.mvp.model.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/18.
 */

public class LoginPresenter extends LoginContract.Presenter {
    private String TAG = "LoginPresenter";

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        mModel = new LoginModel();
    }


    @Override
    public void getCheckUserInfo(String Phone, String AppId) {
        Subscription subscribe = mModel.getCheckUserInfo(Phone, AppId)
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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        CheckInfo userInfo = JSONUtils.toObject(s, CheckInfo.class);
                        mView.getCheckInfo(userInfo);
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getLoginCode(String phone, String postId) {//获取登录验证码
        Subscription subscribe = mModel.getLoginCode(phone, postId)
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
                        mView.getCode_LoginErro("验证码发送失败");
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int statusCode = jsonObject.getInt("StatusCode");
                            String statusMsg = jsonObject.getString("StatusMsg");
                            if (0 == statusCode) {//验证码 发送成功
                                mView.getLoginCode(statusMsg);
                            } else {
                                mView.getCode_LoginErro(statusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getCode_Login(String cardNo, String password, String vCode, String appId, String isLogin) {//验证码登录
        Subscription subscribe = mModel.getCode_Login(cardNo, password, vCode, appId, isLogin)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
//                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int statusCode = jsonObject.getInt("StatusCode");
                            String statusMsg = jsonObject.getString("StatusMsg");
                            if (0 == statusCode) {
                                CodeLoginBean codeLoginBean = JSONUtils.toObject(s, CodeLoginBean.class);
                                mView.getCode_Login(codeLoginBean);


                                Map dandainmap = new HashMap();
//                    Map dandainmap2=new HashMap();
                                dandainmap.put("AlertText", "");
                                dandainmap.put("CardNo", codeLoginBean.getBody().getCardNo());
                                dandainmap.put("Iphone", codeLoginBean.getBody().getAccount());
                                dandainmap.put("AlertTitle", "");
                                dandainmap.put("Type", "104");
                                dandainmap.put("Group", "1");
                                dandainmap.put("IsApp", "4");
                                dandainmap.put("IsApnsProduction", "true");
                                dandainmap.put("SendId", codeLoginBean.getBody().getToken());
                                dandainmap.put("EquipmentName", android.os.Build.MODEL);
                                OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "/actionapi/JiGuang/SendPostBack", dandainmap, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        Log.e("aaaaaaa", "单点登录=" + response.body().string());
                                    }
                                });
                            } else {
                                mView.getCode_LoginErro(statusMsg);
                            }
                        } catch (
                                JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

        addSubscribe(subscribe);

    }

    @Override
    public void getTokenByCode(String Phone, String AppId) {
        Subscription subscribe = mModel.getTokenByCode(Phone, AppId)
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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        CheckInfo userInfo = JSONUtils.toObject(s, CheckInfo.class);
                        mView.getTokenByCodeData(userInfo);

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
//                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        TokenInfo userInfo = JSONUtils.toObject(s, TokenInfo.class);
                        LoginInfoNew loginInfo = JSONUtils.toObject(s, LoginInfoNew.class);
                        if (userInfo.getStatusCode() == 0) {
                            MySharedPreferences.getInstance().setName(loginInfo.getBody().getName());
                            mView.getTokenInfoData(userInfo.getBody());

                            Map dandainmap = new HashMap();
//                    Map dandainmap2=new HashMap();
                            dandainmap.put("AlertText", "");
                            dandainmap.put("CardNo", userInfo.getBody().getCardNo());
                            dandainmap.put("Iphone", userInfo.getBody().getAccount());
                            dandainmap.put("AlertTitle", "");
                            dandainmap.put("Type", "104");
                            dandainmap.put("Group", "1");
                            dandainmap.put("IsApp", "4");
                            dandainmap.put("IsApnsProduction", "true");
                            dandainmap.put("SendId", userInfo.getBody().getToken());
                            dandainmap.put("EquipmentName", android.os.Build.MODEL);
                            OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "/actionapi/JiGuang/SendPostBack", dandainmap, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    Log.e("aaaaaaa", "单点登录=" + response.body().string());
                                }
                            });

                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }


                    }
                });
        addSubscribe(subscribe);
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
                        Log.e("登陆失败", e.getMessage());
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, s);
                        NewUserInfoBean userInfo = JSONUtils.toObject(s, NewUserInfoBean.class);
                        if (userInfo.getStatusCode() == 0) {
                            mView.toLandingSuccess(userInfo.getBody().get(0));
                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    //二维码登录用的请求
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
                        mView.showLoadMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("aa", s);
                        OddNumbersBean userInfo = JSONUtils.toObject(s, OddNumbersBean.class);

                        if (userInfo.getStatusCode() == 0) {
                            OddNumbersBean.BodyBean body = userInfo.getBody();
                            mView.getOddNumData(body);
                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    //密码登录用的请求
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
//                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        mView.showLoadMessage(e.getMessage());
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
                            }else{
                                mView.showLoadMessage("无项目");
                            }
                        } else {
                            mView.showmessage(userInfo.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
