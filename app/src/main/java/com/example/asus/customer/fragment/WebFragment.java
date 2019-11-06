package com.example.asus.customer.fragment;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/7/25.
 */

public class WebFragment extends BaseFragment {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.web_view)
    WebView webView;
    private String title;
    private String url;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_find_more;
    }

    @Override
    protected void FragmentInitData() {

        ivBack.setVisibility(View.GONE);

        initView();

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    private void initView() {

        //  projectId = getIntent().getStringExtra("projectId");
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(2);
        }
//解除数据阻止
        webView.getSettings().setBlockNetworkImage(false);

        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

//                if (url.toString().contains("caseDetails")){
//                    view.loadUrl("http://www.wenes.cn/caseDetails/caseDetails?projectId="+projectId);
//                    return true;
//                }
//
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (request.getUrl().toString().contains("caseDetails")){
//                        view.loadUrl("http://www.wenes.cn/caseDetails/caseDetails?projectId="+ projectId);
//                        return true;
//                    }
//                }

                return false;
            }

        });
        String cardNo = PrefUtils.getValue(getActivity(), Constants.PHOME);
        String appid = PrefUtils.getValue(getActivity(), Constants.APPID);


        webView.loadUrl(ApiEngine.KEHU + "kh/appindex/home_page?card" + cardNo + "&appid=" + appid);


    }
}
