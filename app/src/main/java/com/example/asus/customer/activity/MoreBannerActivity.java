package com.example.asus.customer.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.asus.customer.R;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hjh on 2017/11/22.
 */

public class MoreBannerActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.web_view)
    WebView wbRxsongs;

    @Override
    public int getLayout() {
        return R.layout.activity_find_more;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("瑞祥装饰");
        wbRxsongs.getSettings().setJavaScriptEnabled(true);
        wbRxsongs.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wbRxsongs.getSettings().setDomStorageEnabled(true);
        wbRxsongs.getSettings().setMediaPlaybackRequiresUserGesture(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wbRxsongs.getSettings().setMixedContentMode(2);
        }
//解除数据阻止
        wbRxsongs.getSettings().setBlockNetworkImage(false);
        wbRxsongs.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                return false;
            }
        });
        wbRxsongs.loadUrl(Constants.WEBURL_MOREBANNER);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
