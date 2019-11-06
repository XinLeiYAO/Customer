package com.example.asus.customer.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.PrefUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.wv_news_detail)
    WebView wvNewsDetail;

    public static final String TITLE = "新闻详情";
    @Bind(R.id.iv_back)
    ImageView ivBack;

    private int ID;

    @Override
    public int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData() {
        initIntentData();
        initTitle();
        initNewsDetail();
    }

    private void initIntentData() {
        ivBack.setVisibility(View.VISIBLE);
        ID = getIntent().getIntExtra(Constants.ACTION_TO_NEWS_DETAIL_NEWS_ID, 0);
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initNewsDetail() {
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);

        String url = ApiEngine.RUIYAN+"front/app_details.html?id=" + ID + "&cardNo=" + cardNo;
        wvNewsDetail.loadUrl(url);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wvNewsDetail.getSettings().setMixedContentMode(2);
        }
//解除数据阻止
        wvNewsDetail.getSettings().setBlockNetworkImage(false);
        wvNewsDetail.getSettings().setJavaScriptEnabled(true);
        // 为图片添加放大缩小功能
        wvNewsDetail.getSettings().setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wvNewsDetail.getSettings().setMixedContentMode(2);
        }
//解除数据阻止
        wvNewsDetail.getSettings().setBlockNetworkImage(false);
        wvNewsDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && wvNewsDetail.canGoBack()) {
//            wvNewsDetail.goBack();// 返回前一个页面
//            return true;
//        }
        finish();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
