package com.example.asus.customer.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.NetUtil;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.OrderNoInfo;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.example.asus.customer.mvp.contract.LogoContract;
import com.example.asus.customer.mvp.presenter.LogoPresenter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by asus on 2018/4/20.
 */

public class LogoActivity extends BaseActivity<LogoPresenter> implements LogoContract.View {
    @Override
    public int getLayout() {
        return R.layout.activity_logo;
    }

    @Override
    public void initData() {

    }

    @Override
    protected LogoPresenter onCreatePresenter() {
        return new LogoPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timer mTimer = new Timer();
        boolean isLogin = PrefUtils.getBooleanValue(this, Constants.IS_LOGIN);
        Log.e("boo",isLogin+"");
        if (!NetUtil.isConnected(this)) {
            showDialogTip(1);
        } else {
            if (!isLogin) {
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LogoActivity.this, TextLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
            else {

                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        String phone = PrefUtils.getValue(LogoActivity.this, Constants.PHOME);
                        String pawwword = PrefUtils.getValue(LogoActivity.this, Constants.PASSWORD);
                        mPresenter.tokenLogin(phone,pawwword,"","4");

                    }
                }, 2000);
            }
        }
    }

    private void showDialogTip(int type) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (type) {
            case 1:
                builder.setTitle("提示");
                builder.setMessage("没有网络，请链接网络");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(wifiSettingsIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
                break;
            case 2:
                builder.setTitle("提示");
                builder.setMessage("网络超时，请检查网络");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(wifiSettingsIntent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    public void responseLoginError(String msg) {
       showToast(msg);
    }



    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void toLandingSuccess(UserInfo.BodyBean bodyBean) {
        App.body = bodyBean;
        startActivity(new Intent(this, NjjActivity.class));
        finish();
    }

    @Override
    public void getTokenInfoData(TokenInfo.BodyBean bodyBean) {
        App.tokenInfo=bodyBean;
        mPresenter.getOddNumData(bodyBean.getAppId());
    }

    @Override
    public void getOddNumData(String appid) {
        App.orderNo=appid;
        mPresenter.landLogin( App.tokenInfo.getCardNo(), App.tokenInfo.getToken());

    }

}
