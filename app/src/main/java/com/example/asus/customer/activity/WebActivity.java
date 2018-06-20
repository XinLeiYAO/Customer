package com.example.asus.customer.activity;

import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

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
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);


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
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
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
        //http://www.wenes.cn/caseDetails/caseDetails?projectId=01012431004
        //cd.wenes.cn?id
        if(title.equals("现场")){
            url="http://i.rxjy.com/kh/AppIndex/one_page?";
        }else if(title.equals("元素")){
            url="http://i.rxjy.com/kh/AppIndex/two_page?";
        }else if(title.equals("方案")){
            url="http://i.rxjy.com/kh/AppIndex/three_page?";
        }else if(title.equals("预算")){
            url="http://i.rxjy.com/kh/AppIndex/four_page?";
        }else if(title.equals("合作")){
            url="http://i.rxjy.com/kh/AppIndex/five_page?";
        }else if(title.equals("案例")){
            url="http://i.rxjy.com/kh/appindex/ten_page?";
        }
        webView.loadUrl(url+"card="+ App.tokenInfo.getCardNo()+"&appid="+App.tokenInfo.getAppId());
    }
}
