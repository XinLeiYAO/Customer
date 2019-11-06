package com.example.asus.customer.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.DevelopmentDetialActivity;
import com.example.asus.customer.activity.QRZhongJianYeActivity;
import com.example.asus.customer.activity.QrLoginActivity;
import com.example.asus.customer.adapter.DevelopmentAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.AntiShake;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.DevelopmentInfo;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.QRresultBean;
import com.example.asus.customer.mvp.contract.DevelopmentContract;
import com.example.asus.customer.mvp.presenter.DevelopmentPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by asus on 2018/5/29.
 */

public class DevelopmentFragment extends BaseFragment<DevelopmentPresenter> implements DevelopmentContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.lv_development)
    ListView lvDevelopment;
    private static DevelopmentFragment developmentFragment;
    private String url;
    private String url1;

    @Override
    protected int getFragmentLayout() {
        showDialog();
        return R.layout.development_fragment;
    }

    private List<DevelopmentInfo.BodyBean> body;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private ValueCallback<Uri> uploadMessage;
    AntiShake antiShake = new AntiShake();

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(getActivity(), QRZhongJianYeActivity.class));
//                QRCodeScan();
                break;
        }
//        QRCodeScan();
    }

    public static DevelopmentFragment getInstance() {
        if (developmentFragment == null) {
            synchronized (DevelopmentFragment.class) {
                if (developmentFragment == null) {
                    developmentFragment = new DevelopmentFragment();
                }
            }
        }
        return developmentFragment;
    }

    public void initQR() {
        QRCodeScan();
    }

//    public static Singleton getInstance() {
//        if (instance == null) {
//            synchronized (Singleton.class) {
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    @Override
    protected void FragmentInitData() {
//        String tag = (String) getArguments().get("tag");
//        if (tag!=null){
//            if (tag.equals("qr")){
//                QRCodeScan();
//            }
//        }
        tvTitle.setText("首页");
        getData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QRZhongJianYeActivity.class));
                getActivity().finish();
            }
        });
    }

    private void getData() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", PrefUtils.getValue(getActivity(), Constants.APPID));
//        Log.e("appid",PrefUtils.getValue(getActivity(),Constants.APPID)+"");
        OkhttpUtils.doGet(ApiEngine.RS_API_HOST + "actionapi/KGManage/GetCustomerProjectList", map, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 1) {
                                DevelopmentInfo developmentInfo = JSONUtils.toObject(string, DevelopmentInfo.class);
                                if (developmentInfo != null) {
                                    body = developmentInfo.getBody();
                                    //直接加载webView
                                    webView.setVisibility(View.VISIBLE);
                                    lvDevelopment.setVisibility(View.GONE);
                                    loadUrl();
                                }
//                                if (body.size() == 1) {
//                                    webView.setVisibility(View.VISIBLE);
//                                    lvDevelopment.setVisibility(View.GONE);
//                                    loadUrl();
//                                } else {
//                                    webView.setVisibility(View.GONE);
//                                    lvDevelopment.setVisibility(View.VISIBLE);
//                                    DevelopmentAdapter adapter = new DevelopmentAdapter(getActivity(), body);
//                                    lvDevelopment.setAdapter(adapter);
//                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        lvDevelopment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), DevelopmentDetialActivity.class).putExtra("orderid", body.get(position).getPN_Onumber()));
            }
        });
    }

    private void loadUrl() {
        String cardNo = PrefUtils.getValue(getActivity(), Constants.PHOME);
        String appid = PrefUtils.getValue(getActivity(), Constants.APPID);
        if (App.tokenInfo != null) {
            url = ApiEngine.KEHU + "/kh/appindex/home_page?card=" + /*App.tokenInfo.getCardNo() */cardNo + "&appid=" + /*App.tokenInfo.getAppId()*/appid;
        } else {
            url = ApiEngine.KEHU + "kh/appindex/home_page?card=-1&appid=0";
        }
        dismissLoading();
        webView.loadUrl(url);
        //如果不设置WebViewClient，请求会跳转系统浏览器
        WebSettings wSet = webView.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wSet.setMixedContentMode(2);
        }
//解除数据阻止
        wSet.setBlockNetworkImage(false);
        wSet.setJavaScriptEnabled(true);
        wSet.setUseWideViewPort(true);
        wSet.setBlockNetworkImage(false);
        wSet.setLoadWithOverviewMode(true);
        wSet.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wSet.setMixedContentMode(2);
        }
//解除数据阻止
        wSet.setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

                view.loadUrl(url);

                return super.shouldOverrideUrlLoading(view, url);
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
//            {
            //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
            //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (request.getUrl().toString().contains("caseDetails")){
//                        view.loadUrl("http://www.wenes.cn/caseDetails/caseDetails?projectId="+ projectId);
//                        return true;
//                    }
//                }

//                return false;
//            }

        });
        setCheckImg();
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
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
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
    protected DevelopmentPresenter onCreatePresenter() {
        return new DevelopmentPresenter(this);
    }


    @Override
    public void showDialog() {
//        showLoading();
    }

    @Override
    public void hideDialog() {
//        dismissLoading();
    }

    @Override
    public void getProgssData(FindInfo.BodyBean bodyBean) {

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
//        Uri data1 = data.getData();
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        QRresultBean info = null;
                        String biaoshi = null;
                        if (data != null) {
                            try {
                                String result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                                JSONObject jsonObject = new JSONObject(result);
                                String event = jsonObject.getString("event");
                                if (event.equals("登陆web")) {
                                    info = JSONUtils.toObject(result, QRresultBean.class);
                                    biaoshi = info.getParameter().getLogin_id();
                                } else {
                                    showToast("请扫描正确的二维码");
                                }
                            } catch (Exception ex) {

                            }

                            if (biaoshi != null) {
                                startActivity(new Intent(getActivity(), QrLoginActivity.class).putExtra("appid", biaoshi));
                            } else {
                                showToast("请扫描正确的二维码！");
                            }
                        }
                        break;

                }
                break;
            case FILE_CHOOSER_RESULT_CODE:
                if (null == uploadMessage && null == uploadMessageAboveL) return;
                Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
                if (uploadMessageAboveL != null) {
                    onActivityResultAboveL(requestCode, resultCode, data);
                } else if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(result);
                    uploadMessage = null;
                }
                break;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
