package com.example.asus.customer.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
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
 * Created by asus on 2018/5/10.
 */

public class FindMoreActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.web_view)
    WebView wbRxsongs;
    private String projectId;

    @Override
    public int getLayout() {
        return R.layout.activity_find_more;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void initData() {
        tvTitle.setText("详情");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initView() {
        projectId = getIntent().getStringExtra("projectId");
        wbRxsongs.getSettings().setJavaScriptEnabled(true);
        wbRxsongs.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wbRxsongs.getSettings().setDomStorageEnabled(true);
        wbRxsongs.getSettings().setMediaPlaybackRequiresUserGesture(false);

        wbRxsongs.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                return false;
            }
        });
        wbRxsongs.loadUrl("http://cd.wenes.cn?id="+ projectId);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


}
