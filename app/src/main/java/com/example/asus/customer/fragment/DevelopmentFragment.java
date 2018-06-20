package com.example.asus.customer.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.QrLoginActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.QRresultBean;
import com.example.asus.customer.mvp.contract.DevelopmentContract;
import com.example.asus.customer.mvp.presenter.DevelopmentPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by asus on 2018/5/29.
 */

public class DevelopmentFragment extends BaseFragment<DevelopmentPresenter> implements DevelopmentContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    TextView ivBack;
    @Bind(R.id.web_view)
    WebView webView;
    @Override
    protected int getFragmentLayout() {
        return R.layout.development_fragment;
    }

    @Override
    protected void FragmentInitData() {
        mPresenter.getProgssData(App.orderNo);
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


          String  url="http://i.rxjy.com/kh/AppIndex/four_page?";

        webView.loadUrl(url+"card="+ App.tokenInfo.getCardNo()+"&appid="+App.tokenInfo.getAppId());
    }

    @Override
    protected DevelopmentPresenter onCreatePresenter() {
        return new DevelopmentPresenter(this);
    }



    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void getProgssData(FindInfo.BodyBean bodyBean) {
        //  bodyBean.ge
        //
        App.projectName=bodyBean.getBaseInformation().getCi_ClientName();
        if (bodyBean == null) {
            tvTitle.setText("预算");
        } else {
            String ci_clientName = bodyBean.getBaseInformation().getCi_ClientName();
            if(ci_clientName.length()>4){
                String substring = ci_clientName.substring(0, 4);
                tvTitle.setText(substring +"-"+ "预算");
            }else {
                tvTitle.setText(ci_clientName +"-"+ "预算");
            }

        }
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    /**
     * 扫描二维码
     */
    private static final int REQ_CODE_PERMISSION = 0x1111;

    private void QRCodeScan() {//6.0以上的手机需要处理权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
        } else {
            // Have gotten the permission
            startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data != null) {
                            String result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                            QRresultBean info = JSONUtils.toObject(result, QRresultBean.class);
                            String biaoshi = info.getParameter().getLogin_id();
                            if (biaoshi != null) {
                                startActivity(new Intent(getActivity(), QrLoginActivity.class).putExtra("appid", biaoshi));
                            } else {
                                showToast("请扫描正确的二维码！");
                            }
                        }
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            Log.e("RESULT_CANCELED=====", data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
                break;
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        QRCodeScan();
    }
}
