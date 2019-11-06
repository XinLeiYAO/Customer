package com.example.asus.customer.commons.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.TextLoginActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.utils.AutoUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    public App application;

    public Handler mHandler;

    public Toast toast;

    protected P mPresenter;
    private Thread.UncaughtExceptionHandler sUncaughtExceptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局，并且适配
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        View view = View.inflate(this, getLayout(), null);
        AutoUtils.setSize(this, false, 720, 1280);
        AutoUtils.auto(view);
        setContentView(view);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //禁止键盘挤压布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //绑定插件
        //沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorcjs));
//            }
//        }


        ButterKnife.bind(this);
        mHandler = new myhandle(this);
        application = App.getApp();
        toast = new Toast(this);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        App.getApp().addActivity(this);
        initData();

        //上线前解开
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //主线程异常拦截
                while (true) {
                    try {
                        Looper.loop();//主线程的异常会从这里抛出
                    } catch (Throwable e) {
                    }
                }
            }
        });
        sUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //所有线程异常拦截，由于主线程的异常都被我们catch住了，所以下面的代码拦截到的都是子线程的异常
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });
    }

    //加载布局
    public abstract int getLayout();

    //加载数据
    public abstract void initData();


    public static class myhandle extends Handler {
        //使用弱引用防止内存泄漏
        WeakReference<BaseActivity> activityWeakReference;

        public myhandle(BaseActivity activityWeakReference) {
            this.activityWeakReference = new WeakReference<BaseActivity>(activityWeakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activityWeakReference.get() != null) {
                activityWeakReference.get().handlerMeaasg(msg);
            }
        }
    }

    public static int MIN_DELAY_TIME = 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    //子类可以同时重写这个方法实现Handler传输
    public void handlerMeaasg(Message msg) {
    }

    // 内存紧张时回收图片资源
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    // 内存紧张时回收图片资源 API4.0
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(this).trimMemory(level);
    }

    //Acitiy销毁时反注册插件,并且移除活动
    @Override
    protected void onDestroy() {
        application.removeActivity(this);
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        super.onDestroy();
    }

    public void showToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private ProgressDialog dialog;

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading));
        dialog.show();
//        handler.sendEmptyMessageDelayed(10,3000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 10) {
                dismissLoading();
            }


        }
    };

    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    //输入框下划线效果
    public void lineSelector(EditText[] etArray, final TextView[] tvArray) {
        for (int i = 0; i < etArray.length; i++) {
            final int position = i;
            etArray[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        //此处为得到焦点时
                        tvArray[position].setEnabled(true);
                    } else {
                        //此处为失去焦点时
                        tvArray[position].setEnabled(false);
                    }
                }
            });
        }
    }

//    /**
//     * 照片预览
//     */
//    public void photoPreview(String url) {
//        com.luck.picture.lib.entity.LocalMedia localMedia = new com.luck.picture.lib.entity.LocalMedia();
//        localMedia.setPath(url);
//        List<com.luck.picture.lib.entity.LocalMedia> list = new ArrayList<>();
//        list.add(localMedia);
//        PictureSelector.create(this).externalPicturePreview(0, list);
//    }

    protected abstract P onCreatePresenter();

    /**
     * 清楚webview 缓存
     *
     * @param wv webview
     */
    public static void WebViewClearUtils(WebView wv) {
        wv.setWebChromeClient(null);
        wv.setWebViewClient(null);
        wv.getSettings().setJavaScriptEnabled(false);
        wv.clearCache(true);
    }

    public void addActivity(Activity activity){
        Constants.activityList.add(activity);
    }

    public void finishActivity(){
        for(int i = 0 ; i < Constants.activityList.size() ; i++){
            Constants.activityList.get(i).finish();
        }
    }
}