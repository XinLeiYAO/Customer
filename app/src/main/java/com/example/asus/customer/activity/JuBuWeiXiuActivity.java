package com.example.asus.customer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.JvBuWeiXiuAdapter;
import com.example.asus.customer.adapter.ServiceAdapter;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.GetYuYueMessageBean;
import com.example.asus.customer.entity.GongzhangListBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.entity.WeixiuTypeBean;
import com.example.asus.customer.entity.WeixiuTypeListBean;
import com.example.asus.customer.fragment.ServiceFragment;
import com.example.asus.customer.mvp.contract.JiaoLiuContract;
import com.example.asus.customer.mvp.presenter.JiaoLiuTouSuPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.asus.customer.api.ApiEngine.BUCKETNAME2;
import static com.example.asus.customer.api.ApiEngine.ENDPOINT;

public class JuBuWeiXiuActivity extends BaseActivity<JiaoLiuTouSuPresenter> implements JiaoLiuContract.JiaoLiuTouSuView {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.beizhuMessage)
    EditText etBeiZhuMessage;
    @Bind(R.id.beizhuRecycleView)
    RecyclerView beizhuRecycleView;
    @Bind(R.id.callPhone)
    RelativeLayout ivPhone;
    @Bind(R.id.lianxiKeFu)
    RelativeLayout ivKefu;
    @Bind(R.id.fabu)
    Button btFabu;
    @Bind(R.id.beizhuLayout)
    LinearLayout beizhuLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private TouSuRecyclerAdapter touSuRecyclerAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> getMessageList = new ArrayList<>();
    private int tagType = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                dismissLoading();
//                fabuClick();
                initDialog();
            } else if (msg.what == 11) {
                upLoad();
            } else if (msg.what == 12) {
                if (tag.equals("1")) {
                    initDialog();
                } else {
                    ShowUtils.Toastshort(JuBuWeiXiuActivity.this, "请选择图片");
                }
                dismissLoading();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(JuBuWeiXiuActivity.this, "上传失败");
            }

        }
    };


    private TextView tv_sure;
    private String tag;//0为添加，1为查询
    private String id = " ";
    private String url = "";
    private String title = "";
    private TextView tvSitetext;
    String userPhone = null;
    String userName = null;
    String siteText = null;
    String siteid = null;

    List<WeixiuTypeListBean.BodyBean> listData = new ArrayList<>();
    private String types = "";
    private JvBuWeiXiuAdapter weiXiuAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_jubu_weixiu;
    }

    @Override
    public void initData() {
        addActivity(this);
        mPresenter.getOssData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tag = intent.getStringExtra("tag");
        tvTitle.setText(title);
        if (tag.equals("1")) {
            id = getIntent().getStringExtra("id");
            types = getIntent().getStringExtra("repairType");
            getYuYueMessage(id);
        }
        siteText = getIntent().getStringExtra("siteText");
        siteid = getIntent().getStringExtra("siteId");


        btFabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabuClick();
            }
        });
        touSuRecyclerAdapter = new TouSuRecyclerAdapter(this, arrayList);
        beizhuRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        beizhuRecycleView.setAdapter(touSuRecyclerAdapter);
        touSuRecyclerAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (arrayList.size() == position) {
                    PictureSelector.create(JuBuWeiXiuActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
                } else {
//                    if (arrayList.size() != 0 && position != arrayList.size()) {
//                        JSONArray jsonArray = new JSONArray();
//                        for (int i = 0; i < arrayList.size(); i++) {
//                            String filePath = arrayList.get(i);
//                            jsonArray.put(filePath);
//                        }
//                        Intent intent = new Intent(ZhuangxiuYuyueActivity.this, ShowImageActivity.class);
//                        intent.putExtra("title", "投诉建议");
//                        intent.putExtra("context", "投诉建议");
//                        intent.putExtra("json", jsonArray.toString());
//                        startActivity(intent);
//                    }
                }
            }

            @Override
            public void setOnClick(int position) {
                if (arrayList.size() != 0 && position != arrayList.size()) {
                    arrayList.remove(position);
                    if (tag.equals("1") && position <= getMessageList.size() - 1) {
                        getMessageList.remove(position);
                    }
                } else {
                    arrayList.clear();
                }
                touSuRecyclerAdapter.notifyDataSetChanged();

            }
        });

        initTypeData();

    }

    private void initTypeData() {


        getRepairOrderTypeByCondition();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        weiXiuAdapter = new JvBuWeiXiuAdapter(listData);
        weiXiuAdapter.setOnItemButtonClickListener(new JvBuWeiXiuAdapter.ItemButtonClickListener() {
            @Override
            public void onItemButtonClick(CheckBox cb, int position) {
                if (cb.isChecked()) {
                    String s = listData.get(position).getTypeValue();
                    if (TextUtils.isEmpty(types)) {
                        types += s;
                    } else {
                        types += "," + s;
                    }
                }
            }
        });
        recyclerView.setAdapter(weiXiuAdapter);
    }


    private void fabuClick() {
        if (tag.equals("1")) {
            for (int i = 0; i < getMessageList.size(); i++) {
                String imgString = getMessageList.get(i);
                for (int j = 0; j < arrayList.size(); j++) {
                    if (imgString.equals(arrayList.get(j))) {
                        arrayList.remove(j);
                    }
                }
            }
        }
        upLoad();


    }

    public void toastShow(String str) {
        ToastUtil.getInstance().toastCentent(str);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia localMedia : localMedias) {
                        String path = localMedia.getPath();
                        arrayList.add(path);
                    }
                    touSuRecyclerAdapter.notifyDataSetChanged();
                    break;
                case 5:
                    Bundle bundle = data.getExtras();
                    String siteremark = null;
                    if (bundle != null) {
                        userPhone = bundle.getString("userPhone");
                        userName = bundle.getString("userName");
                        siteremark = bundle.getString("siteremark");
                        siteid = bundle.getString("siteid");
                        siteText = bundle.getString("siteText");
                        tvSitetext.setText(siteText);
                    }
                    break;
            }
        }
    }

    //递归所用
    int index = 0;
    //    用于拼接imageUrl
    private String ImageUrls = "";
    private String ImageUrls1 = "";

    private void upLoad() {
        showLoading();
        if (arrayList.size() <= 0) {//集合里没有图片
            handler.sendEmptyMessage(12);
            return;
        }

        if (index == arrayList.size()) {//递归上传完毕
            handler.sendEmptyMessage(10);
            return;
        }
        String filePath = arrayList.get(index);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压lica
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME, imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask<PutObjectResult> task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.OSS_UPURL + imageName + ".jpg";
                //上传成功 index加1
                index += 1;
                if (index == arrayList.size()) {//递归上传完毕
                    ImageUrls += name;
                } else {
                    ImageUrls += name + ",";
                }
                handler.sendEmptyMessage(11);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                index += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (index + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private void initDialog() {
        if (TextUtils.isEmpty(types)) {
            toastShow("请选择维修类型");
            return;
        }
        View inflate = getLayoutInflater().inflate(R.layout.new_yuyue_layout, null);
        AutoUtils.auto(inflate);
        ImageView close = (ImageView) inflate.findViewById(R.id.close_img);
        TextView address = (TextView) inflate.findViewById(R.id.textAddress);
        address.setText(types);
        RelativeLayout ll_youhui = inflate.findViewById(R.id.ll_youhui);
        ll_youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().toastCentent("暂未开放优惠券功能");
            }
        });
        tvSitetext = (TextView) inflate.findViewById(R.id.tvSitetext);
        if (!TextUtils.isEmpty(siteText)) {
            tvSitetext.setText(siteText);
        }
        TextView tvSaveCustomerAppUser = (TextView) inflate.findViewById(R.id.tvSaveCustomerAppUser);
        RelativeLayout addressLayout = (RelativeLayout) inflate.findViewById(R.id.addressLayout);
        final PopupWindow window = new PopupWindow(this);
        window.setContentView(inflate);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置高度
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setFocusable(true);
        //设置弹出窗体需要软键盘，
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        //再设置模式，和Activity的一样，覆盖。
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        window.setBackgroundDrawable(dw);

        window.setAnimationStyle(R.style.popwin_anim_style);
        window.showAtLocation(tvTitle, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        addressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(JuBuWeiXiuActivity.this, FuwuAddressActivity.class), 5);
            }
        });
        tvSaveCustomerAppUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSitetext.getText().toString().equals("请选择服务地址")) {
                    toastShow("请选择服务地址");
                    return;
                }
                saveMessage();
            }
        });
    }

    private void saveMessage() {
        if (tag.equals("1")) {
            url = "/customerApp/updateCustomerAppUser";
        } else {
            url = "/customerApp/saveCustomerAppUser";
        }
        Map<String, Object> map = new HashMap<>();
        if (tag.equals("1")) {
            map.put("id", id);

        }
        map.put("repairType", types);
        map.put("userPhone", userPhone);
        map.put("userName", userName);
        map.put("siteText", siteText);
        map.put("siteId", siteid);
        //维修，暂无用
//        map.put("budget", "");
//        map.put("cityName", tvChangCity.getText().toString());
//        map.put("cityId", CityId);
        switch (title) {
            case "家装装修":
                map.put("type", "家装");
                break;
            case "办公装修":
                map.put("type", "公装");
                break;
            case "局部维修":
                map.put("type", "维修");
                break;
        }
//        map.put("visitTime", nexttime);
        map.put("remarks", etBeiZhuMessage.getText().toString());
//        if (tag.equals("1")) {
            map.put("stage", 1);
//        }
        if (tag.equals("1")) {
            index = 0;
            for (int i = 0; i < getMessageList.size(); i++) {
                index += 1;
                if (index == getMessageList.size()) {
                    ImageUrls1 += getMessageList.get(i);
                } else {
                    ImageUrls1 += getMessageList.get(i) + ",";
                }
            }
            if (arrayList.size() == 0) {
                map.put("repairPic", ImageUrls1);
            } else {
                map.put("repairPic", ImageUrls + "," + ImageUrls1);

            }
        } else {
            map.put("repairPic", ImageUrls);
        }
        map.put("mode", "上门量房");
        map.put("loggedPersonPhone", MySharedPreferences.getInstance().getUserPhone());
        Log.e("motejia", "saveMessage: ==============" + ImageUrls + "===========" + ImageUrls1);
        OkhttpUtils.doPost(ApiEngine.GZS_HOST + url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toastShow("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CheckInfo checkInfo = JSONUtils.toObject(string, CheckInfo.class);

                            if (checkInfo.getStatusCode() == 1) {
                                toastShow("预约成功");
                                finish();
                            } else {
                                toastShow("" + checkInfo.getStatusMsg());
                                tv_sure.setEnabled(true);
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
    }

    private void getYuYueMessage(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getCustomerAppUser", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.getInstance().toastCentent("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GetYuYueMessageBean bean = JSONUtils.toObject(string, GetYuYueMessageBean.class);
                            GetYuYueMessageBean.BodyBean body = bean.getBody();
                            //标题
                            String type = body.getType();
                            tvTitle.setText(type + "装修");
                            switch (tvTitle.getText().toString()) {
                                case "家装装修":
                                    tvType.setText("家装类型");
                                    tagType = 2;
                                    break;
                                case "公装装修":
                                case "办公装修":
                                    tvType.setText("公装类型");
                                    tagType = 1;
                                    break;
                                case "局部维修":
                                    tvType.setText("维修类型");
                                    tagType = 2;
                                    break;
                            }

                            List<String> repairPics = body.getRepairPics();
                            if (repairPics != null && repairPics.size() > 0) {
                                arrayList.addAll(repairPics);
                                getMessageList.addAll(repairPics);
                                touSuRecyclerAdapter.notifyDataSetChanged();
                            }
                            //备注
                            String remarks = body.getRemarks();
                            etBeiZhuMessage.setText("" + remarks);
                        }
                    });
            }
        });
    }
    @Override
    protected JiaoLiuTouSuPresenter onCreatePresenter() {
        return new JiaoLiuTouSuPresenter(this);
    }

    @Override
    public void getTouSuData() {

    }

    @Override
    public void getTouSuDataErro(String erro) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void OssData(OSSBean ossBean) {
        OssManager.getInstance().init(this, ENDPOINT, BUCKETNAME2,
                ossBean.getAccessKeyId(),
                ossBean.getAccessKeySecret(),
                ossBean.getSecurityToken(), new OssManager.picResultCallback() {
                    @Override
                    public void getPicData(boolean b, String eTag, String serverCallbackReturnBody) {
//                        //todo
//                        //进行请求，把url传给后台
//                        mPresenter.upHeaderPicture("1", App.cardNo, imaUrl + imageName + ".jpg");
                    }
                });
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    private void getRepairOrderTypeByCondition() {
        Map<String, Object> map = new HashMap<>();
        OkhttpUtils.doGet("https://gngrc.rxjy.com/repair/repairOrder/getRepairOrderTypeByCondition", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        WeixiuTypeListBean bean = JSONUtils.toObject(string, WeixiuTypeListBean.class);
                        List<WeixiuTypeListBean.BodyBean> body = bean.getBody();
                        listData.addAll(body);
                        if (!TextUtils.isEmpty(types)) {
                            String[] strSplit = types.split(",");
                            for (int i = 0; i < listData.size(); i++) {
                                String string = listData.get(i).getTypeValue();
                                for (int j = 0; j < strSplit.length; j++) {
                                    if (string.equals(strSplit[j])) {
                                        listData.get(j).setIs(1);
                                        System.out.println("下标是：" + i);
                                    }
                                }

                            }
                        }

                        weiXiuAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}