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
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.TokenInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 更换手机号
 */
public class ReplaceActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_tool)
    RelativeLayout rlTool;
    @Bind(R.id.mEtPhone)
    TextView mEtPhone;
    @Bind(R.id.mEtPassWord)
    EditText mEtPassWord;
    @Bind(R.id.mBtnNewPhone)
    Button mBtnNewPhone;

    @Override
    public int getLayout() {
        return R.layout.activity_replace;
    }

    @Override
    public void initData() {
        String account = PrefUtils.getValue(this, Constants.PHONENUM);
//        App.cardNo
        mEtPhone.setText(account);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("更换手机号");
        mBtnNewPhone.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBtnNewPhone:

                String phoneNum = mEtPhone.getText().toString();
                String password = mEtPassWord.getText().toString();


                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }

                Map<String, Object> map = new HashMap<>();
                map.put("cardNo", phoneNum);
                map.put("password", password);
                map.put("vCode", "");
                map.put("appId", "4");

                OkhttpUtils.doPost(ApiEngine.LOGINURL, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
//                        Log.e("ERROR",e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String s = response.body().string();
                        try {
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    TokenInfo info = JSONUtils.toObject(s,TokenInfo.class);
                                    if (info.getStatusCode() == 0){
                                        Intent intent = new Intent(ReplaceActivity.this,NewPhoneActivity.class);
                                        startActivity(intent);
                                    }else{
                                        showToast(info.getStatusMsg());
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


}
