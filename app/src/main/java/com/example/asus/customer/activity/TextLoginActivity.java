package com.example.asus.customer.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.mvp.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/5/29.
 */

public class TextLoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @Bind(R.id.login_prompt)
    TextView loginPrompt;
    private String TAG = "LoginActivity";

    @Bind(R.id.ed_phone)
    EditText ed_phone;
    @Bind(R.id.ed_vitifycode)
    EditText ed_vitifycode;
    @Bind(R.id.ed_pwd)
    EditText ed_pwd;

    @Bind(R.id.tv_vitifycodeget)
    TextView tv_vitifycodeget;
    @Bind(R.id.tv_forgetpwd)
    TextView tv_forgetpwd;

    @Bind(R.id.btn_next)
    Button btn_next;

    @Bind(R.id.rl_veritycode)
    RelativeLayout rl_veritycode;
    @Bind(R.id.rl_phone)
    RelativeLayout rl_phone;
    @Bind(R.id.rl_pwd)
    RelativeLayout rl_pwd;
    @Bind(R.id.ll_toforget)
    LinearLayout ll_toforget;


    String phonenum, veritycode, pwdnum;
    int logintype;//0：验证码登  1：密码登录

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        rl_veritycode.setVisibility(View.GONE);
        btn_next.setVisibility(View.GONE);
        rl_pwd.setVisibility(View.GONE);
        tv_forgetpwd.setVisibility(View.GONE);
        ll_toforget.setVisibility(View.GONE);
        ed_phone.addTextChangedListener(new MyEditListener(ed_phone));
    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void toLandingSuccess(UserInfo.BodyBean bodyBean) {
        loginPrompt.setVisibility(View.GONE);
        App.body = bodyBean;
        PrefUtils.putBooleanValue(this, Constants.IS_LOGIN, true);
        startActivity(new Intent(this, NjjActivity.class));
        finish();
    }

    @Override
    public void showmessage(String message) {

       loginPrompt.setText(message);
       loginPrompt.setVisibility(View.VISIBLE);

    }

    @Override
    public void getCheckInfo(CheckInfo checkInfo) {
        switch (checkInfo.getStatusCode()) {
            case 0://用户存在
                //    getphone = data.getBody().getPhone();
                btn_next.setText("登录");
                logintype = 0;
                btn_next.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.VISIBLE);
                tv_forgetpwd.setVisibility(View.VISIBLE);
                ll_toforget.setVisibility(View.VISIBLE);
                rl_veritycode.setVisibility(View.GONE);
                loginPrompt.setVisibility(View.GONE);
                break;
            case 1://用户不存在,获取验证码注册
                logintype = 1;
                rl_veritycode.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.VISIBLE);
                tv_forgetpwd.setVisibility(View.GONE);
                ll_toforget.setVisibility(View.GONE);
                loginPrompt.setVisibility(View.GONE);
                break;
            default:
                loginPrompt.setText(checkInfo.getStatusMsg());
                loginPrompt.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.btn_next, R.id.tv_vitifycodeget, R.id.tv_forgetpwd, R.id.ll_toforget})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
//                startActivity(new Intent(this, SupervisionMainActivity.class));
                veritycode = ed_vitifycode.getText().toString();
                pwdnum = ed_pwd.getText().toString();
                switch (logintype) {
                    case 1:
                        if (!phonenum.isEmpty() && !veritycode.isEmpty() && !pwdnum.isEmpty()) {
                            //请求登录
                            mPresenter.tokenLogin(phonenum, pwdnum, veritycode, "4");
                        } else if (phonenum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入手机号！");
                        } else if (veritycode.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入验证码！");
                        } else if (pwdnum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入密码！");
                        }
                        break;
                    case 0:
                        if (!phonenum.isEmpty() && !pwdnum.isEmpty()) {
                            //请求登录
                            mPresenter.tokenLogin(phonenum, pwdnum, "", "4");
                        } else if (phonenum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入手机号！");
                        } else if (pwdnum.isEmpty()) {
                            ShowUtils.Toastshort(this, "请输入密码！");
                        }
                        break;
                }
                break;
            case R.id.tv_vitifycodeget:
                mPresenter.getTokenByCode(phonenum, "4");
                break;
            case R.id.tv_forgetpwd:
                //  startActivity(new Intent(this,ForgetPwdActivity.class).putExtra("phone",getphone));
                break;
            case R.id.ll_toforget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;

        }
    }

    @Override
    public void getTokenByCodeData(CheckInfo checkInfo) {
        switch (checkInfo.getStatusCode()) {
            case 0://发送成功，，，倒计时。。。
                updateTime();
                Toast.makeText(this, checkInfo.getStatusMsg(), Toast.LENGTH_SHORT).show();
                break;
            default://发送失败
                Toast.makeText(this, checkInfo.getStatusMsg(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //倒计时
    private void updateTime() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 60; i >= 0; --i) {
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    SystemClock.sleep(1000);     //  1s
                }
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int time = msg.arg1;
            if (tv_vitifycodeget != null) {
                tv_vitifycodeget.setText(time + "s后重发");
                tv_vitifycodeget.setEnabled(false);
                tv_vitifycodeget.setBackgroundColor(getResources().getColor(R.color.colorline));
                if (time == 0) {
                    tv_vitifycodeget.setText("获取验证码");
                    tv_vitifycodeget.setEnabled(true);
                    tv_vitifycodeget.setBackgroundColor(getResources().getColor(R.color.colorred));
                }
            }
        }
    };

    @Override
    public void getTokenInfoData(TokenInfo.BodyBean bodyBean) {
        loginPrompt.setVisibility(View.GONE);
        PrefUtils.putValue(this, Constants.PHOME, phonenum);
        PrefUtils.putValue(this, Constants.PASSWORD, pwdnum);
        App.tokenInfo = bodyBean;
        mPresenter.getOddNumData(bodyBean.getAppId());
    }

    @Override
    public void getOddNumData(String appid) {
        loginPrompt.setVisibility(View.GONE);
        App.orderNo = appid;
        mPresenter.landLogin(App.tokenInfo.getCardNo(), App.tokenInfo.getToken());
    }

    @Override
    public void showLoadMessage(String msg) {
        if(msg.contains("JSONException")){
            loginPrompt.setText("返回数据错误等  系统错误，正在处理中!!!");
        }else {
            loginPrompt.setText("网络未连接、不稳定 ：网络连接失败，请检查网络设置");
        }

        loginPrompt.setVisibility(View.VISIBLE);
    }

    private class MyEditListener implements TextWatcher {

        private EditText edittext;

        public MyEditListener(EditText edittext) {
            super();
            this.edittext = edittext;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            int lengths = arg0.length();
            switch (edittext.getId()) {
                case R.id.ed_phone:
                    phonenum = ed_phone.getText().toString();
                    if (lengths == 11) {//11手机号位请求判断
                        //mPresenter.getCheckIsVerity(phonenum);
                        mPresenter.getCheckUserInfo(phonenum, "4");
                    }


            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
