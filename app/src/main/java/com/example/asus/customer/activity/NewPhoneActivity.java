package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.entity.NewPhoneBean;
import com.example.asus.customer.entity.NewPhoneRandomBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewPhoneActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_tool)
    RelativeLayout rlTool;
    @Bind(R.id.mEditPhone)
    EditText mEditPhone;
    @Bind(R.id.mEditRandom)
    EditText mEditRandom;
    @Bind(R.id.mTextViewRandom)
    TextView mTextViewRandom;
    @Bind(R.id.mConfirm)
    Button mConfirm;
    private String phone;

    @Override
    public int getLayout() {
        return R.layout.activity_new_phone;

    }

    @Override
    public void initData() {
        tvTitle.setText("新手机号");

        ivBack.setOnClickListener(this);
        mTextViewRandom.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.mTextViewRandom:
                //https://api.dcwzg.com:9191/actionapi/AppHome/GetChangePhoneVcode?CardNo=g00003019&NewPhone=18210425299&api_key=GetUpdatePhone
                phone = mEditPhone.getText().toString();


                if(phone.length()==11){
                   initRandom();
                }else if(phone.length()==0){
                    showToast("手机号不能为空");
                }else{
                    showToast("正输入正确的11位手机号");
                }

                break;
                case R.id.mConfirm:
                    initConfirm();
                    break;
        }
    }

    private void initConfirm() {
        final String phone1 = mEditPhone.getText().toString();
        String random = mEditRandom.getText().toString();
        //https://api.dcwzg.com:9191/actionapi/AppHome/GetUpdatePhone?CardNo=g00003019&NewPhone=18301192124&VCode=092152&api_key=GetUpdatePhone
        String cardNo = MySharedPreferences.getInstance().getCardNo();
        Map<String ,Object> map=new HashMap<>();
        map.put("CardNo",cardNo);
        map.put("NewPhone",phone1);
        map.put("VCode",random);
        OkhttpUtils.doGet(ApiEngine.RS_API_HOST+"actionapi/AppHome/GetUpdatePhone", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        NewPhoneBean newPhoneBean = gson.fromJson(string, NewPhoneBean.class);
                        String statusMsg = newPhoneBean.getStatusMsg().toString();

                        if(newPhoneBean.getStatusCode()==0){
                            App.getApp().exitApp();
//                PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
//                PrefUtils.RemoveValue(this, Constants.CARD_NO);
//                PrefUtils.RemoveValue(this, Constants.TOKEN);
                            MySharedPreferences.getInstance().setCardNo("");
                            MySharedPreferences.getInstance().setToken("");
                            startActivity(new Intent(NewPhoneActivity.this, TextLoginActivity.class));
                            JPushInterface.setAlias(NewPhoneActivity.this,"",null);
                        }else {
                            showToast(newPhoneBean.getStatusMsg().toString());
                        }
                        }
                    });
                //139
            }
        });
    }

    private void initRandom() {
        Map<String,Object> map=new HashMap();

        String cardNo = MySharedPreferences.getInstance().getCardNo();
        map.put("CardNo",cardNo);
        map.put("NewPhone",phone);
        OkhttpUtils.doGet(ApiEngine.RS_API_HOST+"actionapi/AppHome/GetChangePhoneVcode", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        NewPhoneRandomBean newPhoneRandomBean = gson.fromJson(string, NewPhoneRandomBean.class);
                       showToast(newPhoneRandomBean.getStatusMsg());
                    }
                });
            }
        });
    }
}
