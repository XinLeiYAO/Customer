package com.example.asus.customer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.PrefUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DevelopmentDetialActivity extends BaseActivity {

    @Bind(R.id.web_development_detial)
    WebView webDevelopmentDetial;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int getLayout() {
        return R.layout.development_detial_item;
    }

    @Override
    public void initData() {
        tvTitle.setText("项目详情");
        Intent intent = getIntent();
        String orderid = intent.getStringExtra("orderid");
        String url = "http://i.rxjy.com/CustomerPage/appIndex/fanganyusuan_page?appid=" + PrefUtils.getValue(this, Constants.APPID) + "&rwdId=" + orderid;
        //webview7.0以上需加以下代码
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            webDevelopmentDetial.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webDevelopmentDetial.loadUrl(url);
        Log.e("webView————————", url);

        WebSettings settings = webDevelopmentDetial.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(2);
        }
//解除数据阻止
        settings.setBlockNetworkImage(false);

        settings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webDevelopmentDetial.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissLoading();
            }
        });
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
}
