package com.example.asus.customer.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
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
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.PrefUtils;

import butterknife.Bind;

/**
 * Created by asus on 2018/6/5.
 */

public class WebActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.web_view)
    WebView webView;
    private String title;
    private String url;

    @Override
    public int getLayout() {
        return R.layout.activity_find_more;
    }

    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private ValueCallback<Uri> uploadMessage;

    @Override
    public void initData() {
        // tvTitle.setText("详情");
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(2);
        }
        //解除数据阻止
        webView.getSettings().setBlockNetworkImage(false);

        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(2);
        }
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setCheckImg();
        //http://www.wenes.cn/caseDetails/caseDetails?projectId=01012431004
        //cd.wenes.cn?id
//        if (title.equals("现场")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/one_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("供应")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/two_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("方案")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/three_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("预算")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/four_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("合作")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/five_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("案例")) {
//            url = ApiEngine.KEHU + "kh/appindex/ten_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        }
//        } else if (title.equals("材料")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/six_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("工艺")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/seven_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else if (title.equals("施工")) {
//            url = ApiEngine.KEHU + "kh/AppIndex/eight_page?";
//            webView.loadUrl(url + "card=" + App.tokenInfo.getCardNo() + "&appid=" + App.tokenInfo.getAppId());
//        } else
        String appid = PrefUtils.getValue(this, Constants.APPID);
        String phone = PrefUtils.getValue(this, Constants.PHONENUM);

        if (title.equals("设计师")) {

            webView.loadUrl(ApiEngine.KEHU + "kh/AppIndex/shejishi_page?appid=" + appid);
        } else if (title.equals("服务")) {
            webView.loadUrl(ApiEngine.KEHU + "kh/AppIndex/Serve_page?appid=" + appid);
        } else if (title.equals("活动")) {
//            webView.loadUrl();

        } else if (title.equals("邀请记录")) {
            webView.loadUrl(ApiEngine.webUrl + "h5/page/yqjl.html?phone=" + phone);

        }else if (title.equals("分享提现")) {
            webView.loadUrl(ApiEngine.webUrl + "h5/page/txyhkTwo.html?phone=" + phone);

        }else if (title.equals("提现")) {
            webView.loadUrl(ApiEngine.webUrl + "h5/page/txyhk.html?phone=" + phone);

        }else if (title.equals("我的收益")) {
            webView.loadUrl(ApiEngine.webUrl + "h5/page/qianbao.html?phone=" + phone);

        }else if (title.equals("浏览")) {
            String href = getIntent().getStringExtra("href");
            webView.loadUrl(href);

        }

    }

    private void setCheckImg() {
        webView.setWebChromeClient(new WebChromeClient() {
            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }
        });
    }

    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }
}