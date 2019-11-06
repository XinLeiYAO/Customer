package com.example.asus.customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.DownLoadApkUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.VersionInfo;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/4/17.
 * 设置
 */

public class SettingsActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    //个人资料
    @Bind(R.id.rl_upd_password)
    RelativeLayout rlUpdPassword;
    @Bind(R.id.rl_help_center)
    RelativeLayout rlHelpCenter;
    @Bind(R.id.rl_reward_punish_record)
    RelativeLayout rlRewardPunishRecord;
    @Bind(R.id.rl_vision)
    RelativeLayout rlVision;
    @Bind(R.id.btn_quit)
    RelativeLayout btnQuit;
    @Bind(R.id.tv_vision_name)
    TextView tvVisionName;
    @Bind(R.id.mReplacePhone)
    RelativeLayout mReplacePhone;
    @Bind(R.id.gengxin)
    RelativeLayout gengxin;
    @Bind(R.id.upDate_password)
    RelativeLayout upDatePassword;
    @Bind(R.id.zhuangxiuAddressLayout)
    RelativeLayout zhuangxiuAddressLayout;
    @Bind(R.id.tianjiaZhanghuLayout)
    RelativeLayout tianjiaZhanghuLayout;
    @Bind(R.id.guanyuLayout)
    RelativeLayout guanyuLayout;
    @Bind(R.id.tuijianFriendLayout)
    RelativeLayout tuijianFriendLayout;
    @Bind(R.id.qingchuhuancunLayout)
    RelativeLayout qingchuhuancunLayout;
    private String cardNo = "";
    private Dialog bottomDialog;
    private String phone;
    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
        tvVisionName.setText(App.getVersionName());
        phone = PrefUtils.getValue(this, Constants.PHONENUM);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick({R.id.upDate_password, R.id.iv_back, R.id.show_popup,
            R.id.rl_upd_password, R.id.rl_help_center, R.id.rl_reward_punish_record,
            R.id.rl_vision, R.id.mReplacePhone, R.id.gengxin, R.id.zhuangxiuAddressLayout,
            R.id.guanyuLayout, R.id.tianjiaZhanghuLayout, R.id.tuijianFriendLayout, R.id.qingchuhuancunLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upDate_password:
                Intent intent = new Intent(this, ForgetPwdActivity.class);
                String value = PrefUtils.getValue(this, Constants.PHONENUM);
                intent.putExtra("phone", value);
                intent.putExtra("title", "修改密码");
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.show_popup:
                break;
            case R.id.rl_upd_password:
                startActivity(new Intent(SettingsActivity.this, UserInfoActivity.class));
                break;
            case R.id.rl_help_center:
                startActivity(new Intent(this, TouSuActivity.class));
                break;
            case R.id.rl_reward_punish_record:
                startActivity(new Intent(SettingsActivity.this, ComplaintActivity.class));
                break;
            case R.id.rl_vision:
                break;
            case R.id.mReplacePhone:
                startActivity(new Intent(SettingsActivity.this, ReplaceActivity.class));
                break;
            case R.id.gengxin:
                jianChaBanBen();
                break;
            case R.id.zhuangxiuAddressLayout:
                startActivity(new Intent(SettingsActivity.this, FuwuAddressActivity.class));
                break;
            case R.id.guanyuLayout:
                startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
                break;
            case R.id.tianjiaZhanghuLayout:
                startActivity(new Intent(SettingsActivity.this, AddZhanghuActivity.class));
                break;
            case R.id.tuijianFriendLayout:
                clickPop3();
                break;

            case R.id.qingchuhuancunLayout:
                clickPop3();
                break;

        }
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ShowUtils.Toastshort(SettingsActivity.this, "分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ShowUtils.Toastshort(SettingsActivity.this, t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ShowUtils.Toastshort(SettingsActivity.this, "分享取消");
        }
    };
    private void clickPop3() {
        bottomDialog = new Dialog(this, R.style.newPassword);
        //绘制dialog  UI视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.fenxiang_layout, null);
        AutoUtils.setSize(this, false, 750, 1334);
        AutoUtils.auto(contentView);
        //给dialog添加view
        bottomDialog.setContentView(contentView);
        //为绘制的view设置参数
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        //设置为全屏的宽
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        //设置dialog位置
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        //添加进出场动画
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        //允许点击外部退出dialog
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show();
        Button close = (Button) contentView.findViewById(R.id.close);
        LinearLayout weixin = (LinearLayout) contentView.findViewById(R.id.weixin);
        LinearLayout pengyouquan = (LinearLayout) contentView.findViewById(R.id.pengyouquan);
        LinearLayout qq = (LinearLayout) contentView.findViewById(R.id.qq);
        LinearLayout kongjian = (LinearLayout) contentView.findViewById(R.id.kongjian);
//        close.setOnClickListener(this);
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareWeixin();
            }
        });
        pengyouquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePenyouquan();
            }
        });
//        qq.setOnClickListener(this);
//        kongjian.setOnClickListener(this);
    }

    private void sharePenyouquan() {
        UMImage thumb = new UMImage(this, R.mipmap.kh_icon);
//                UMWeb web1 = new UMWeb(ApiEngine.RX_API_KEHU + "AppGroup/AppForeign/AppNewsIfream?" + "id=" + id + "&card_no=" + cardNo);
        UMWeb web = new UMWeb(ApiEngine.webUrl + "five/invitation/"+phone+".html");
        web.setTitle(getString(R.string.shareTitle));
        web.setThumb(thumb);
        web.setDescription(getString(R.string.shareContent));
        new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
//                        .withText("测试数据")
                .withMedia(web)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private void shareWeixin() {
        UMImage thumb = new UMImage(this, R.mipmap.kh_icon);
        UMWeb web = new UMWeb(ApiEngine.webUrl + "five/invitation/"+phone+".html");
        web.setTitle(getString(R.string.shareTitle));
        web.setThumb(thumb);
        web.setDescription(getString(R.string.shareContent));
        new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
//                        .withText("测试数据")
                .withMedia(web)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private AlertDialog dialog;

    /**
     * 检查版本信息  下载安装
     */
    private void jianChaBanBen() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Version", App.getVersionCode());
        map.put("AppId", 4);
        OkhttpUtils.doGet(ApiEngine.RS_API_HOST + "actionapi/AppCurrencyHome/IsAndroidUpdated", map, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final VersionInfo versionInfo = JSONUtils.toObject(string, VersionInfo.class);
                if (versionInfo.getStatusCode() == 1) {
                    final VersionInfo.Version data = versionInfo.getBody();
                    if (data != null) {
                        if (data.getVersionNo() > Integer.parseInt(App.getVersionCode())) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    View inflate = getLayoutInflater().inflate(R.layout.upgrade_layout, null);
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this, R.style.newPassword);
                                    builder.setCancelable(false);// 设置点击屏幕Dialog不消失
                                    builder.setView(inflate);


                                    AutoUtils.setSize(SettingsActivity.this, false, 720, 1280);
                                    AutoUtils.auto(inflate);
                                    final TextView confirmupgrade = (TextView) inflate.findViewById(R.id.confirm_upgrade);
                                    TextView updatecontent = (TextView) inflate.findViewById(R.id.update_content);
                                    final TextView colse = (TextView) inflate.findViewById(R.id.close);
                                    final ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.progress_xiazai);
                                    final TextView anzhuang = (TextView) inflate.findViewById(R.id.anzhuang);
                                    final View xian = (View) inflate.findViewById(R.id.xian);

                                    if (data.getForce() == 1) {
                                        builder.setCancelable(false);// 设置点击屏幕Dialog不消失
                                        colse.setVisibility(View.GONE);
                                    } else {
                                        builder.setCancelable(true);// 设置点击屏幕Dialog不消失
                                    }
                                    String content = data.getContent();
                                    if (!TextUtils.isEmpty(content)) {
                                        updatecontent.setText(content);
                                    }
                                    dialog = builder.create();
                                    dialog.show();
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
                                            DownLoadApkUtils.DownLoadFile(
                                                    file.getAbsolutePath(),
                                                    "gc.apk",
                                                    data.getVersionUrl(),
                                                    SettingsActivity.this,
                                                    confirmupgrade,
                                                    colse,
                                                    progressBar,
                                                    dialog,
                                                    2,
                                                    data.getForce(), anzhuang, xian);
                                        }
                                    });
                                    colse.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });
                        }
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ShowUtils.Toastshort(SettingsActivity.this, versionInfo.getStatusMsg());
                        }
                    });
                }
            }
        });
    }


    /**
     * 退出登录时 清除之前数据
     */
    @OnClick(R.id.btn_quit)
    public void onViewClicked() {
        showMissingPermissionDialog();
    }

    private void tuichuApp() {

        Map map = new HashMap();
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);

        map.put("cardNo", cardNo);
        OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "actionapi/AppHome/OfflineApp", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
            }
        });
        MySharedPreferences.getInstance().setCardNo("");
        MySharedPreferences.getInstance().setUserPhone("");
        App.cardNo = "";
        //App.getApp().exitApp();
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
        PrefUtils.RemoveValue(this, Constants.PHOME);
        PrefUtils.RemoveValue(this, Constants.PASSWORD);
        PrefUtils.RemoveValue(this, Constants.PN_Onumber);
//        startActivity(new Intent(this, TextLoginActivity.class));
        JPushInterface.setAlias(this, "", null);
        finish();
    }

    /**
     * * 显示提示信息
     * *
     * * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("退出后不会删除历史数据，是否退出？");
        // 拒绝, 关闭
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tuichuApp();
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
