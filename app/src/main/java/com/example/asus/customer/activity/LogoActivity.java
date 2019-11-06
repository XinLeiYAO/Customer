package com.example.asus.customer.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.Authority;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.DownLoadApkUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.NetUtil;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.GuangGaoBean;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.VersionInfo;
import com.example.asus.customer.mvp.contract.LogoContract;
import com.example.asus.customer.mvp.presenter.LogoPresenter;
import com.example.asus.customer.weight.StartUtils;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/20.
 * 首页   logo 页面
 */

public class LogoActivity extends BaseActivity<LogoPresenter> implements LogoContract.View {

    @Bind(R.id.show_image)
    ImageView showImage;
    @Bind(R.id.ad_image)
    ImageView adImage;
    @Bind(R.id.ad_time)
    TextView adTime;
    @Bind(R.id.ad)
    LinearLayout ad;
    private String token;
    private String cardNo;
    private android.app.AlertDialog dialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_logo;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        App.is_home = true;
        Authority.CallMobile(LogoActivity.this);
    }

    /**
     * 摄像头权限和存储权限 请求的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10000) {
            int isClose = 0;
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.CAMERA) || permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE) || permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[i] == -1) {
                        isClose = 1;
                        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(LogoActivity.this);
                        builder.setMessage("摄像头权限和存储权限为必要的权限。\n由于您拒绝了，这导致您无法正常使用该应用。");
                        builder.setCancelable(false);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                LogoActivity.this.finish();
                            }
                        });
                        builder.show();
                    }
                }
            }
            if (isClose == 0) {
                mPresenter.getVersionInfo(Integer.parseInt(App.getVersionCode()));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected LogoPresenter onCreatePresenter() {
        return new LogoPresenter(this);
    }

    /**
     * 获取版本失败 判断状态
     */
    public void isWhere() {
        adTime.setText("正在进入");
        adImage.setClickable(false);
        Timer mTimer = new Timer();
        boolean isLogin = PrefUtils.getBooleanValue(this, Constants.IS_LOGIN);
        boolean isEnter = PrefUtils.getBooleanValue(this, Constants.IS_ENTER);
        if (!NetUtil.isConnected(this)) {
            showDialogTip(1);
        } else {
            if (!isEnter) {
                StartUtils.getInstance().startActivityChangeListener(this, 3);
//                Intent intent = new Intent(LogoActivity.this, GuidancePager.class);
//                startActivity(intent);
//                finish();
            } else {
                if (!isLogin) {
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            StartUtils.getInstance().startActivityChangeListener(LogoActivity.this, 2);

//                            Intent intent = new Intent(LogoActivity.this, TextLoginActivity.class);
//                            startActivity(intent);
//                            finish();
                        }
                    }, 500);
                } else {

                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            cardNo = PrefUtils.getValue(LogoActivity.this, Constants.PHOME);
                            token = PrefUtils.getValue(LogoActivity.this, Constants.PASSWORD);
                            //       mPresenter.tokenLogin(phone, pawwword, "", "4");
                            mPresenter.landLogin(cardNo, token);
                        }
                    }, 500);
                }
            }

        }
    }

    /**
     * 显示网络状态dialog
     *
     * @param type
     */
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

    /**
     * 登录失败
     *
     * @param msg
     */
    @Override
    public void responseLoginError(String msg) {

        ShowUtils.Toastshort(LogoActivity.this, msg);
        StartUtils.getInstance().startActivityChangeListener(this, 2);
//        startActivity(new Intent(this, TextLoginActivity.class)
//                .putExtra("type", 1)
//                .putExtra("msg", msg));
//        finish();
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    /**
     * 成功 保存用户信息
     *
     * @param bodyBean
     */
    @Override
    public void toLandingSuccess(NewUserInfoBean.BodyBean bodyBean) {
        App.body = bodyBean;
        String appid = PrefUtils.getValue(LogoActivity.this, Constants.APPID);
        MySharedPreferences.getInstance().setName(bodyBean.getU_name());
        TokenInfo.BodyBean tokeninfo = new TokenInfo.BodyBean(cardNo, bodyBean.getPhone(), token, appid);
        App.tokenInfo = tokeninfo;
        mPresenter.getOddNumData2(appid);
        App.body = bodyBean;

    }

    @Override
    public void getTokenInfoData(TokenInfo.BodyBean bodyBean) {


    }

    @Override
    public void getOddNumData(OddNumbersBean.BodyBean body) {
        mPresenter.getOddNumData2(body.getApp_id());
    }

    @Override
    public void getOddNumData2(String body) {

        App.PN_Onumber = body;
        PrefUtils.putValue(this, Constants.PN_Onumber, body);
        App.loginStarts = 1;
        StartUtils.getInstance().startActivityChangeListener(this, 2);
//        startActivity(new Intent(this, NjjActivity.class));
//        finish();
    }

    @Override
    public void loadTextLogin() {
        StartUtils.getInstance().startActivityChangeListener(this, 2);
//        startActivity(new Intent(this, NjjActivity.class));
//        finish();
    }

    /**
     * 获取当前版本  成功
     *
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void responseVersionData(final VersionInfo.Version data) {
        if (data.getVersionNo() > Integer.parseInt(App.getVersionCode())) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.newPassword);
            builder.setCancelable(false);// 设置点击屏幕Dialog不消失
            View inflate = getLayoutInflater().inflate(R.layout.upgrade_layout, null);
            AutoUtils.setSize(this, false, 720, 1280);
            AutoUtils.auto(inflate);
            final TextView confirmupgrade = (TextView) inflate.findViewById(R.id.confirm_upgrade);
            TextView updatecontent = (TextView) inflate.findViewById(R.id.update_content);
            final TextView colse = (TextView) inflate.findViewById(R.id.close);
            final TextView anzhuang = (TextView) inflate.findViewById(R.id.anzhuang);
            final ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.progress_xiazai);
            final View xian = (View) inflate.findViewById(R.id.xian);
            builder.setCancelable(false);
            if (data.getForce() == 1) {// 设置点击屏幕Dialog不消失
                colse.setVisibility(View.GONE);
                xian.setVisibility(View.GONE);
            }
            String content = data.getContent();
            if (!TextUtils.isEmpty(content)) {
                updatecontent.setText(content);
            }
            confirmupgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File file = Environment.getExternalStorageDirectory();
                    File apkFile = new File(file, "/gc.apk");
                    if (apkFile.exists()) {
                        apkFile.delete();
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    colse.setVisibility(View.GONE);
                    confirmupgrade.setText("0%");
                    confirmupgrade.setEnabled(false);
                    xian.setVisibility(View.GONE);
                    DownLoadApkUtils.DownLoadFile(file.getAbsolutePath(),
                            "gc.apk",
                            data.getVersionUrl(),
                            LogoActivity.this,
                            confirmupgrade,
                            colse,
                            progressBar,
                            dialog,
                            0,
                            data.getForce(),
                            anzhuang,
                            xian);

                }
            });
            colse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mPresenter.getAdData(App.APP_ID, cardNo = PrefUtils.getValue(LogoActivity.this, Constants.PHOME));
//                    isWhere();
                }
            });
            builder.setView(inflate);
            dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void responseVersionDataError(String msg) {
        mPresenter.getAdData(App.APP_ID, cardNo = PrefUtils.getValue(this, Constants.PHOME));
//        isWhere();
    }


    @OnClick(R.id.ad_time)
    public void onViewClicked() {
        timeHandler.removeMessages(10);
        isWhere();
    }

    @Override
    public void getAdDataErro(String erro) {
        isWhere();
    }


    @Override
    protected void onResume() {
        super.onResume();
        StartUtils.getInstance().isAd = 0;
    }

    @Override
    public void getAdData(GuangGaoBean guangGaoBean) {
        if (guangGaoBean.getBody() != null && guangGaoBean.getBody().size() > 0) {
            ad.setVisibility(View.VISIBLE);
            List<GuangGaoBean.BodyBean> body = guangGaoBean.getBody();
            if (body.size() != 0) {
                GuangGaoBean.BodyBean bodyBean = body.get(0);
                final String ad_html = bodyBean.getAd_html();
                String ad_img = bodyBean.getAd_img();
                final int ad_id = bodyBean.getAd_id();
                final String ad_title = bodyBean.getAd_title();
                final String ad_content = bodyBean.getAd_content()+"";
                final String status = bodyBean.getRead_visit()+"";
                Glide.with(this).load(ad_img).into(adImage);

                adImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //todo
                        StartUtils.getInstance().isAd = 1;
                        Intent intent = new Intent(LogoActivity.this, News_Wab_Activity.class);
                        intent.putExtra("html", ad_html);
                        intent.putExtra("id", ad_id);
                        intent.putExtra("title", ad_title);
                        intent.putExtra("content", ad_content);
                        intent.putExtra("status", status);
                        startActivity(intent);
                    }
                });
                Message message = new Message();
                message.obj = 3;
                message.what = 10;
                timeHandler.sendMessageDelayed(message, 1000);
            }
        }else{
            isWhere();
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                int time = (int) msg.obj;
                adTime.setText(--time + " 跳过");
                if (time == 0) {
                    isWhere();
                    return;
                }
                Message message = new Message();
                message.obj = time;
                message.what = 10;
                sendMessageDelayed(message, 1000);
            }
        }
    };
}
