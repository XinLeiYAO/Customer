package com.example.asus.customer.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.CheckPasswordUtil;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.mvp.contract.ForgetPwdContract;
import com.example.asus.customer.mvp.presenter.ForgetPwdPresenter;
import com.example.asus.customer.weight.DownTimerButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresenter> implements ForgetPwdContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.tv_old_password_line)
    TextView tvPhoneLine;
    @Bind(R.id.btn_verification_code)
    Button btnVerificationCode;
    @Bind(R.id.et_verification_code)
    EditText etVerificationCode;
    @Bind(R.id.tv_verification_code_line)
    TextView tvVerificationCodeLine;
    @Bind(R.id.et_new_password)
    EditText etNewPassword;
    @Bind(R.id.tv_new_password_line)
    TextView tvNewPasswordLine;
    @Bind(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @Bind(R.id.tv_confirm_password_line)
    TextView tvConfirmPasswordLine;

    public static String TITLE = "找回密码";
    @Bind(R.id.iv_back)
    ImageView ivBack;

    private int type;
    private String phone;
    private String title;

    @Override
    public int getLayout() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        title = intent.getStringExtra("title");
        if(title != null){
            TITLE = title;
        }
        if (!TextUtils.isEmpty(phone))
            etPhoneNum.setText(phone);

        initIntent();
        initTitle();
        initLine();
        ivBack.setVisibility(View.VISIBLE);
    }

    private void initIntent() {
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initLine() {
        etPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvPhoneLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvPhoneLine.setEnabled(false);
                }
            }
        });
        etVerificationCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvVerificationCodeLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvVerificationCodeLine.setEnabled(false);
                }
            }
        });
        etNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvNewPasswordLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvNewPasswordLine.setEnabled(false);
                }
            }
        });
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    tvConfirmPasswordLine.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    tvConfirmPasswordLine.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected ForgetPwdPresenter onCreatePresenter() {
        return new ForgetPwdPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_verification_code, R.id.btn_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_verification_code:
                String phone = etPhoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入手机号");
                    return;
                }
                if (phone.length() != 11) {
                    showToast("请输入正确的11位手机号");
                    return;
                }
                btnVerificationCode.setBackgroundResource(R.drawable.shape_btn_sub_un_enabled);
                btnVerificationCode.setClickable(false);
                btnVerificationCode.setEnabled(false);
                handler.sendEmptyMessage(10);
                mPresenter.getVCode(phone);
                break;
            case R.id.btn_forget_password:
                String phoneNum = etPhoneNum.getText().toString().trim();
                String verificationCode = etVerificationCode.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
//                String confirmPassword = etConfirmPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    showToast("请输入手机号");
                    return;
                }

                if (TextUtils.isEmpty(verificationCode)) {
                    showToast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(newPassword)) {
                    showToast("请输入新密码");
                    return;
                }

                //修改密码
                mPresenter.updatePassword(phoneNum, newPassword, verificationCode);
                break;
        }
    }

    int time = 60;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==10){
                if(time>=0){
                    btnVerificationCode.setText(time--+"");
                    handler.sendEmptyMessageDelayed(10,1000);
                }else{
                    btnVerificationCode.setBackgroundResource(R.drawable.selector_btn_sub);
                    btnVerificationCode.setText("获取验证码");
                    btnVerificationCode.setClickable(true);
                    btnVerificationCode.setEnabled(true);
                    time = 60;
                }
            }

        }
    };

    @Override
    public void responseUpdatePassword() {
        showToast("修改成功");
        finish();
    }

    @Override
    public void responseUpdatePasswordError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseVCodeError(String msg) {

        btnVerificationCode.setBackgroundResource(R.drawable.selector_btn_sub);
        btnVerificationCode.setText("获取验证码");
        btnVerificationCode.setClickable(true);
        btnVerificationCode.setEnabled(true);
        showToast(msg);
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

}
