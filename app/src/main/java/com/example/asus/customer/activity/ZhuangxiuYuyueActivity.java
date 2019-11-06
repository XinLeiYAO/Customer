package com.example.asus.customer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MyGPS.GPSLocationListener;
import com.example.asus.customer.commons.utils.MyGPS.GPSLocationManager;
import com.example.asus.customer.commons.utils.MyGPS.GPSProviderStatus;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.CityNewBean;
import com.example.asus.customer.entity.GetYuYueMessageBean;
import com.example.asus.customer.entity.HuXingBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.entity.ZhuangXiuLeixingBean;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.asus.customer.api.ApiEngine.BUCKETNAME2;
import static com.example.asus.customer.api.ApiEngine.ENDPOINT;

public class ZhuangxiuYuyueActivity extends BaseActivity<JiaoLiuTouSuPresenter> implements JiaoLiuContract.JiaoLiuTouSuView {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.ziji_liang)
    TextView tvZijiLiang;
    @Bind(R.id.shangmen_liang)
    TextView tvShangmenLiang;
    @Bind(R.id.shengMoney)
    TextView shengMoney;
    @Bind(R.id.yusuan_money)
    EditText etYuanSuanMoney;
    @Bind(R.id.mianji)
    EditText etMianji;
    @Bind(R.id.addressMessage)
    EditText etaddressMessage;
    @Bind(R.id.getAddress)
    TextView tvGetAddress;
    @Bind(R.id.changTime)
    TextView tvChangTime;
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
    @Bind(R.id.tvLeixing)
    TextView tvLeixing;
    @Bind(R.id.tvHuxing)
    TextView tvHuxing;
    @Bind(R.id.Changcity)
    TextView tvChangCity;
    @Bind(R.id.addressLayout)
    RelativeLayout addressLayout;
    @Bind(R.id.beizhuLayout)
    LinearLayout beizhuLayout;
    @Bind(R.id.bottom)
    LinearLayout bottom;
    private String nexttime;
    private Calendar startDate;
    private Calendar endDate;
    private TouSuRecyclerAdapter touSuRecyclerAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> getMessageList = new ArrayList<>();
    private int tagType = 1;
    private String FuID = "";
    private String ZiID = "";
    private String HuXingId = "";
    private String CityId = "";
    private String CityName = "";
    private ArrayList<String> FuList = new ArrayList<>();
    private ArrayList<String> HuXingList = new ArrayList<>();
    private ArrayList<String> CityList = new ArrayList<>();
    private ArrayList<List<String>> ZiList = new ArrayList<>();
    private OptionsPickerView leixingOptionsPickerView;
    private OptionsPickerView huxingOptionsPickerView;
    private OptionsPickerView cityOptionsPickerView;
    private OssManager ossManager;

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
                    ShowUtils.Toastshort(ZhuangxiuYuyueActivity.this, "请选择图片");
                }
                dismissLoading();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(ZhuangxiuYuyueActivity.this, "上传失败");
            }

        }
    };
    Handler gpsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // 要做的事情
            super.handleMessage(msg);
            Geocoder gc = new Geocoder(ZhuangxiuYuyueActivity.this, Locale.getDefault());
            List<Address> locationList = null;
            try {
                locationList = gc.getFromLocation(lat, log, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (locationList.size() > 0) {
                Address address = locationList.get(0);//得到Address实例
                String locality = address.getLocality();//得到城市名称，比如：北京市
                String addressLine = address.getAddressLine(0);//得到周边信息，包括街道等，i=0，得到街道名称
                Log.e("motejia", "handleMessage: ==============" + locality + "=========" + addressLine);
                etaddressMessage.setText("" + addressLine);
            }
        }
    };

    private TextView tv_sure;
    private String tag;//0为添加，1为查询
    private List<HuXingBean.BodyBean> huXingbody;
    private List<ZhuangXiuLeixingBean.BodyBean> leiXingBody;
    private List<CityNewBean.BodyBean> cityBody;
    private String messaegPhone = "";
    private String customerName = "";
    private String leiXingYi = "";
    private String leiXingEr = "";
    private int huXing;
    private int cityId;
    private String id = " ";
    private String url = "";
    private String title = "";
    //GPS
    private GPSLocationManager gpsManager;
    double log, lat;
    boolean GPS_FIRST_FIX = true;
    private TextView tvSitetext;
    String userPhone =null;
    String userName =null;
    String siteText =null;
    String siteid =null;

    @Override
    public int getLayout() {
        return R.layout.activity_zhuangxiu_yuyue;
    }

    @Override
    public void initData() {
        addActivity(this);
        mPresenter.getOssData();
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        endDate.set(2090, 1, 1);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tag = intent.getStringExtra("tag");
        if (tag.equals("1")) {
            id = getIntent().getStringExtra("id");
            String type1 = getIntent().getStringExtra("type1");
            leiXingYi = type1;
            String type2 = getIntent().getStringExtra("type2");
            leiXingEr = type2;
            tvLeixing.setText(type1 + " - " + type2);

            getYuYueMessage(id);
        }
        siteText = getIntent().getStringExtra("siteText");
        siteid = getIntent().getStringExtra("siteId");
        tvTitle.setText(title + "");
        switch (title) {
            case "家装装修":
                tvType.setText("家装类型");
                tagType = 2;
                break;
            case "办公装修":
                tvHuxing.setVisibility(View.INVISIBLE);
                tvType.setText("公装类型");
                tagType = 1;
                break;
            case "局部维修":
                tvType.setText("维修类型");
                tagType = 2;
                break;
        }

        if (title.equals("局部维修")) {
            tvZijiLiang.setText("预算金额");
            shengMoney.setVisibility(View.GONE);
            etYuanSuanMoney.setVisibility(View.VISIBLE);
        }

        //上门时间
        tvChangTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMianji.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etaddressMessage.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etBeiZhuMessage.getWindowToken(), 0);
                changTime();
            }
        });

        //获取位置
        addressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsManager = GPSLocationManager.getInstances(ZhuangxiuYuyueActivity.this);
                gpsManager.setHandler(gpsHandler);
                //开启定位
                GPS_FIRST_FIX = true;
                gpsManager.start(new MyGpsListener(), true);
            }
        });
        //类型选择
        tvLeixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMianji.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etaddressMessage.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etBeiZhuMessage.getWindowToken(), 0);
                if (leixingOptionsPickerView != null) {
                    leixingOptionsPickerView.show();
                }
            }
        });

        //户型
        tvHuxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMianji.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etaddressMessage.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etBeiZhuMessage.getWindowToken(), 0);
                if (huxingOptionsPickerView != null) {
                    huxingOptionsPickerView.show();
                }
            }
        });

        //城市
        tvChangCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etMianji.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etaddressMessage.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(etBeiZhuMessage.getWindowToken(), 0);
                if (cityOptionsPickerView != null) {
                    cityOptionsPickerView.show();
                }
            }
        });

        //自己量房
        tvZijiLiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLeixing.getText().toString().equals("请选择类型")) {
                    toastShow("请选择装修类型");
                    return;
                }
                if (tagType == 2) {
                    if (tvHuxing.getText().toString().equals("请选择户型")) {
                        toastShow("请选择户型");
                        return;
                    }
                }

                if (TextUtils.isEmpty(etMianji.getText().toString())) {
                    toastShow("请输入面积");
                    return;
                }

//                if (tvChangCity.getText().toString().equals("请选择城市")) {
//                    toastShow("请选择城市");
//                    return;
//                }

//                if (TextUtils.isEmpty(etaddressMessage.getText().toString())) {
//                    toastShow("请输入地址");
//                    return;
//                }


//                if (tvChangTime.getText().toString().equals("请选择上门时间")) {
//                    toastShow("请选择上门时间");
//                    return;
//                }
                beizhuLayout.setVisibility(View.GONE);
                bottom.setVisibility(View.GONE);
                tvShangmenLiang.setBackgroundResource(R.drawable.border_unselect_blue);
                tvShangmenLiang.setTextColor(getResources().getColor(R.color.colorcjs));
                tvZijiLiang.setBackgroundResource(R.drawable.border_blue);
                tvZijiLiang.setTextColor(getResources().getColor(R.color.colorWhite));
                switch (tvTitle.getText().toString().trim()) {
                        case "家装装修":
                        startActivity(new Intent(ZhuangxiuYuyueActivity.this, HomeZijiActivity.class)
                                .putExtra("type", FuID)
                                .putExtra("typeEr", ZiID)
                                .putExtra("typeName", leiXingYi)
                                .putExtra("typeNameEr", leiXingEr)
                                .putExtra("huxing", HuXingId)
                                .putExtra("huxingName", tvHuxing.getText().toString())
                                .putExtra("area", etMianji.getText().toString())
                                .putExtra("city", CityId)
                                .putExtra("cityName", tvChangCity.getText().toString())
                                .putExtra("time", tvChangTime.getText().toString())
                                .putExtra("address", etaddressMessage.getText().toString())
                                .putExtra("tag", tag)
                                .putExtra("id", id)
                                .putExtra("title", title)
                                .putExtra("siteText", siteText)
                                .putExtra("siteid", siteid)
                                .putExtra("Leixing", tvLeixing.getText().toString())
                                .putExtra("Huxing", tvHuxing.getText().toString())
                        );
                        break;
                    case "公装装修":
                    case "办公装修":
                        startActivity(new Intent(ZhuangxiuYuyueActivity.this, WorkZijiActivity.class)
                                .putExtra("type", FuID)
                                .putExtra("typeEr", ZiID)
                                .putExtra("typeName", leiXingYi)
                                .putExtra("typeNameEr", leiXingEr)
                                .putExtra("huxing", HuXingId)
                                .putExtra("huxingName", tvHuxing.getText().toString())
                                .putExtra("area", etMianji.getText().toString())
                                .putExtra("city", CityId)
                                .putExtra("cityName", tvChangCity.getText().toString())
                                .putExtra("time", tvChangTime.getText().toString())
                                .putExtra("address", etaddressMessage.getText().toString())
                                .putExtra("tag", tag)
                                .putExtra("id", id)
                                .putExtra("title", title)
                                .putExtra("siteText", siteText)
                                .putExtra("siteid", siteid)
                                .putExtra("Leixing", tvLeixing.getText().toString())
                                .putExtra("Huxing", tvHuxing.getText().toString())
                        );
                        break;
                    case "局部维修":

                        break;
                }
            }
        });

        tvShangmenLiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beizhuLayout.setVisibility(View.VISIBLE);
                bottom.setVisibility(View.VISIBLE);
                tvShangmenLiang.setBackgroundResource(R.drawable.border_blue);
                tvShangmenLiang.setTextColor(getResources().getColor(R.color.colorWhite));
                tvZijiLiang.setBackgroundResource(R.drawable.border_unselect_blue);
                tvZijiLiang.setTextColor(getResources().getColor(R.color.colorcjs));
            }
        });

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
                    PictureSelector.create(ZhuangxiuYuyueActivity.this)
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
        getLeixing();
        getHomeStyle();
//        getCity();

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
                try {
                    JSONObject object = new JSONObject(string);
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
                                    tvHuxing.setVisibility(View.INVISIBLE);
                                    tvType.setText("公装类型");
                                    tagType = 1;
                                    break;
                                case "局部维修":
                                    tvType.setText("维修类型");
                                    tagType = 2;
                                    break;
                            }
                            //地址
                            String address = body.getAddress();
                            etaddressMessage.setText(address + "");
                            //面积
                            String area = body.getArea();
                            etMianji.setText(area + "");
                            //装修时间
                            String visitTime = body.getVisitTime();
                            tvChangTime.setText(visitTime + "");
                            //户型
                            huXing = body.getHuXing();
                            if (huXingbody != null && huXingbody.size() > 0) {
                                for (int i = 0; i < huXingbody.size(); i++) {
                                    if (huXingbody.get(i).getID() == huXing) {
                                        tvHuxing.setText(huXingbody.get(i).getMingCheng());
                                    }
                                }
                            }
                            HuXingId = huXing + "";

                            //类型
                            FuID = body.getLeiXingYi();
                            ZiID = body.getLeiXingEr();
//                            getLeixing();
//                            tvLeixing.setText(leiXingYi + "-" + leiXingEr);

                            //城市
                            cityId = body.getCityId();
                            if (cityBody != null && cityBody.size() > 0) {
                                for (int i = 0; i < cityBody.size(); i++) {
                                    int fengGongSiID = cityBody.get(i).getFengGongSiID();
                                    if (fengGongSiID == cityId) {
                                        tvChangCity.setText(cityBody.get(i).getCityName());
                                    }
                                }
                            }
                            CityId = cityId + "";

                            List<String> drawings = body.getDrawings();
                            if (drawings != null && drawings.size() > 0) {
                                arrayList.addAll(drawings);
                                getMessageList.addAll(drawings);
                                touSuRecyclerAdapter.notifyDataSetChanged();
                            }


                            //手机号
                            messaegPhone = body.getPhone();
                            //姓名
                            customerName = body.getCustomerName();

                            String mode = body.getMode();
                            if (mode.equals("自己量房")) {
                                beizhuLayout.setVisibility(View.GONE);
                                bottom.setVisibility(View.GONE);
                                tvShangmenLiang.setBackgroundResource(R.drawable.border_unselect_blue);
                                tvShangmenLiang.setTextColor(getResources().getColor(R.color.colorcjs));
                                tvZijiLiang.setBackgroundResource(R.drawable.border_blue);
                                tvZijiLiang.setTextColor(getResources().getColor(R.color.colorWhite));
                            } else {
                                beizhuLayout.setVisibility(View.VISIBLE);
                                bottom.setVisibility(View.VISIBLE);
                                tvShangmenLiang.setBackgroundResource(R.drawable.border_blue);
                                tvShangmenLiang.setTextColor(getResources().getColor(R.color.colorWhite));
                                tvZijiLiang.setBackgroundResource(R.drawable.border_unselect_blue);
                                tvZijiLiang.setTextColor(getResources().getColor(R.color.colorcjs));
                            }

                            //备注
                            String remarks = body.getRemarks();
                            etBeiZhuMessage.setText("" + remarks);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fabuClick() {
        if (tvLeixing.getText().toString().equals("请选择类型")) {
            toastShow("请选择装修类型");
            return;
        }
        if (tagType == 2) {
            if (tvHuxing.getText().toString().equals("请选择户型")) {
                toastShow("请选择户型");
                return;
            }
        }

        if (TextUtils.isEmpty(etMianji.getText().toString())) {
            toastShow("请输入面积");
            return;
        }
//
//        if (tvChangCity.getText().toString().equals("请选择城市")) {
//            toastShow("请选择城市");
//            return;
//        }
//
//        if (TextUtils.isEmpty(etaddressMessage.getText().toString())) {
//            toastShow("请输入地址");
//            return;
//        }

//
//        if (tvChangTime.getText().toString().equals("请选择上门时间")) {
//            toastShow("请选择上门时间");
//            return;
//        }
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

    private void getCity() {
        Map<String, Object> map = new HashMap<>();
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getCityList", map, new Callback() {
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
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CityNewBean bean = JSONUtils.toObject(string, CityNewBean.class);
                            if (bean.getStatusCode() == 1) {
                                cityBody = bean.getBody();
                                for (CityNewBean.BodyBean bodyBean : cityBody) {
                                    CityList.add(bodyBean.getCityName());
                                    if (tag.equals("1")) {
                                        if (cityId == bodyBean.getFengGongSiID()) {
                                            tvChangCity.setText(bodyBean.getCityName());
                                        }
                                    }
                                }
                                //创建选择器
                                cityOptionsPickerView = new OptionsPickerBuilder(ZhuangxiuYuyueActivity.this, new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        tvChangCity.setText(cityBody.get(options1).getCityName());
                                        CityId = cityBody.get(options1).getFengGongSiID() + "";
                                    }
                                }).build();
                                cityOptionsPickerView.setPicker(CityList);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getHomeStyle() {
        Map<String, Object> map = new HashMap<>();
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getHuXing", map, new Callback() {
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
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            HuXingBean bean = JSONUtils.toObject(string, HuXingBean.class);
                            if (bean.getStatusCode() == 1) {
                                huXingbody = bean.getBody();
                                for (HuXingBean.BodyBean bodyBean : huXingbody) {
                                    HuXingList.add(bodyBean.getMingCheng());
                                    if (tag.equals("1")) {
                                        if (huXing == bodyBean.getID()) {
                                            tvHuxing.setText(bodyBean.getMingCheng());
                                        }
                                    }
                                }
                                //创建选择器
                                huxingOptionsPickerView = new OptionsPickerBuilder(ZhuangxiuYuyueActivity.this, new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        tvHuxing.setText(huXingbody.get(options1).getMingCheng());
                                        HuXingId = huXingbody.get(options1).getID() + "";
                                    }
                                }).build();
                                huxingOptionsPickerView.setPicker(HuXingList);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLeixing() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", tagType);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getLeiXing", map, new Callback() {
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
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ZhuangXiuLeixingBean bean = JSONUtils.toObject(string, ZhuangXiuLeixingBean.class);
                            if (bean.getStatusCode() == 1) {
                                leiXingBody = bean.getBody();
                                for (ZhuangXiuLeixingBean.BodyBean bodyBean : leiXingBody) {
                                    FuList.add(bodyBean.getMingCheng());
                                    List<String> childList = new ArrayList<>();
                                    for (ZhuangXiuLeixingBean.BodyBean.ZijiBean zijiBean : bodyBean.getZiji()) {
                                        childList.add(zijiBean.getMingCheng());
                                    }

                                    ZiList.add(childList);
                                }
                                //创建选择器
                                leixingOptionsPickerView = new OptionsPickerBuilder(ZhuangxiuYuyueActivity.this, new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        leiXingYi = leiXingBody.get(options1).getMingCheng();
                                        leiXingEr = leiXingBody.get(options1).getZiji().get(options2).getMingCheng();
                                        tvLeixing.setText(leiXingBody.get(options1).getMingCheng() + "  " + leiXingBody.get(options1).getZiji().get(options2).getMingCheng());
                                        FuID = leiXingBody.get(options1).getID() + "";
                                        ZiID = leiXingBody.get(options1).getZiji().get(options2).getID() + "";
                                    }
                                }).build();
                                leixingOptionsPickerView.setPicker(FuList, ZiList);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
                    String siteremark =null;
                    if(bundle!=null){
                        userPhone=bundle.getString("userPhone");
                        userName=bundle.getString("userName");
                        siteremark=bundle.getString("siteremark");
                        siteid=bundle.getString("siteid");
                        siteText =bundle.getString("siteText");
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
        View inflate = getLayoutInflater().inflate(R.layout.new_yuyue_layout, null);
        AutoUtils.auto(inflate);
        ImageView close = (ImageView) inflate.findViewById(R.id.close_img);
        TextView address = (TextView) inflate.findViewById(R.id.textAddress);
        RelativeLayout ll_youhui = inflate.findViewById(R.id.ll_youhui);
        ll_youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().toastCentent("暂未开放优惠券功能");
            }
        });
        tvSitetext = (TextView) inflate.findViewById(R.id.tvSitetext);
        if (!TextUtils.isEmpty(siteText)){
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
        switch (title) {
            case "家装装修":
                address.setText(tvLeixing.getText().toString() + " " + tvHuxing.getText().toString());
                break;
            case "办公装修":
                address.setText(tvLeixing.getText().toString());
                break;
            case "局部维修":

                break;
        }
        addressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ZhuangxiuYuyueActivity.this,FuwuAddressActivity.class),5);
            }
        });
        tvSaveCustomerAppUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSitetext.getText().toString().equals("请选择服务地址")){
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
        map.put("userPhone", userPhone);
        map.put("userName", userName);
        map.put("siteText", siteText);
        map.put("siteId", siteid);
        map.put("area", etMianji.getText().toString());
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
        map.put("leiXingYi", FuID);
        map.put("leiXingEr", ZiID);
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
                map.put("drawing", ImageUrls1);
            } else {
                map.put("drawing", ImageUrls + "," + ImageUrls1);

            }
        } else {
            map.put("drawing", ImageUrls);
        }
        map.put("huXing", HuXingId);
        map.put("leiXingYiName", leiXingYi);
        map.put("leiXingErName", leiXingEr);
        map.put("mode", "上门量房");
        map.put("huXingName", tvHuxing.getText().toString());
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

    private void changTime() {
        TimePickerView timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String birth = getDateToString(date, "yyyy-MM-dd HH:mm:ss");
                nexttime = getDateToString(date, "yyyy-MM-dd HH:mm:ss");
                tvChangTime.setText(birth);
            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("上门时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                        .setRangDate()
                .setRangDate(startDate, endDate)
                .isCyclic(true)//是否循环滚动
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                .build();
        timePickerView.show();
    }

    public static String getDateToString(Date date, String pattern) {
        Log.i("tag", "date>>>>>>>>" + date);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
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
        ossManager = OssManager.getInstance().init(this, ENDPOINT, BUCKETNAME2,
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

    class MyGpsListener implements GPSLocationListener {

        @Override
        public void UpdateLocation(Location location) {
            if (location != null) {
                log = location.getLongitude();
                lat = location.getLatitude();
                if (GPS_FIRST_FIX = true) {
                    gpsManager.start_TimerTask();
                    GPS_FIRST_FIX = false;
                }
                Log.e("gps==", "经度：" + location.getLongitude() + "\n纬度：" + location.getLatitude());
            }
        }

        @Override
        public void UpdateStatus(String provider, int status, Bundle extras) {
            if ("gps" == provider) {
                Log.e("UpdateStatus--gps", "定位类型：" + provider);
            }
        }

        @Override
        public void UpdateGPSProviderStatus(int gpsStatus) {
            switch (gpsStatus) {

                case GPSProviderStatus.GPS_ENABLED:
                    //   Toast.makeText(MainActivity.this, "GPS开启", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_DISABLED:
                    //    Toast.makeText(MainActivity.this, "GPS关闭", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_OUT_OF_SERVICE:
                    //  Toast.makeText(MainActivity.this, "GPS不可用", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_TEMPORARILY_UNAVAILABLE:
                    //  Toast.makeText(MainActivity.this, "当前GPS状态为暂停服务状态", Toast.LENGTH_SHORT).show();
                    break;
                case GPSProviderStatus.GPS_AVAILABLE:
                    //     Toast.makeText(MainActivity.this, "GPS可用啦", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gpsManager != null) {
            gpsManager.stop();
        }
    }
}