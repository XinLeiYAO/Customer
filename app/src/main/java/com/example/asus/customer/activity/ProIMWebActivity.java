package com.example.asus.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AndroidBug5497Workaround;
import com.example.asus.customer.commons.utils.PrefUtils;


import butterknife.Bind;

public class ProIMWebActivity extends BaseActivity {
    @Bind(R.id.pr_web_im_id)
    WebView webView;
    private String ordErNo;
    private int uid;

    @Override
    public int getLayout() {
        return R.layout.activity_pro_imweb;
    }

    @Override
    public void initData() {
        AndroidBug5497Workaround.assistActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        fullScreen(this);
        final String value = PrefUtils.getValue(ProIMWebActivity.this, Constants.PN_Onumber);
        //WebViewClearUtils(webView);
        Log.e("motejia", "initData: ==============="+"https://chat.wenes.cn/mobile/mobile.html?cardNo=&rwdId="+ value );
        webView.loadUrl("https://chat.wenes.cn/mobile/mobile.html?cardNo=&rwdId="+ value);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());

        //initWeb();

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        webView.destroy();
        finish();
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.indexOf("nm.wenes.cn") != -1){
                webView.destroy();
                finish();
                //KehuActivity.this.finish();
                //MySharedPreferences.getInstance().setPushMessage("0");
                return true;
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            if(webView.getUrl().equals("https://chat.wenes.cn/mobile/mobile.html?cardNo="+uid+"&rwdId="+ordErNo)){
                super.onBackPressed();
            }else{
                webView.goBack();
            }
        }else{
            super.onBackPressed();
        }
    }
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏为透明，否则在部分手机上会呈现系统默认的浅灰色
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以考虑设置为透明色
                //window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                //attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }
}
