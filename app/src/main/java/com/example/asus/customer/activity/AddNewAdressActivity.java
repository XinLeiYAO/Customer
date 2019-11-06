package com.example.asus.customer.activity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MyGPS.GPSLocationListener;
import com.example.asus.customer.commons.utils.MyGPS.GPSLocationManager;
import com.example.asus.customer.commons.utils.MyGPS.GPSProviderStatus;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.AddsiteBean;
import com.example.asus.customer.entity.QuerysiteBean;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddNewAdressActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.addressLayout)
    RelativeLayout addressLayout;
    @Bind(R.id.tv_address)
    EditText tv_address;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etUserPhone)
    EditText etUserPhone;
    @Bind(R.id.etSiteRemark)
    EditText etSiteRemark;
    @Bind(R.id.btnAdd)
    Button btnAdd;
    @Bind(R.id.btnDeleteSite)
    Button btnDeleteSite;
    //GPS
    private GPSLocationManager gpsManager;
    double log, lat;
    boolean GPS_FIRST_FIX = true;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // 要做的事情
            super.handleMessage(msg);
            Geocoder gc = new Geocoder(AddNewAdressActivity.this, Locale.getDefault());
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
                tv_address.setText("" + addressLine);
            }
        }
    };
    private String siteid;


    @Override
    public int getLayout() {
        return R.layout.activity_add_new_adress;
    }

    @Override
    public void initData() {
        final int type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            btnAdd.setText("修改");
            btnDeleteSite.setVisibility(View.VISIBLE);
            siteid = getIntent().getStringExtra("siteid");
            querysiteBean(siteid);
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("新增地址");
        addressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsManager = GPSLocationManager.getInstances(AddNewAdressActivity.this);
                gpsManager.setHandler(handler);
                //开启定位
                GPS_FIRST_FIX = true;
                gpsManager.start(new MyGpsListener(), true);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type==1){
                    updatesite(siteid);
                }else{
                    addSite();
                }
            }
        });


        btnDeleteSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSite(siteid);
            }
        });


    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
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

    private void addSite() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", etUserName.getText().toString());
        map.put("siteText", tv_address.getText().toString());
        map.put("userPhone", etUserPhone.getText().toString());
        map.put("siteRemark", etSiteRemark.getText().toString());
        map.put("zuserPhone", MySharedPreferences.getInstance().getUserPhone());
        OkhttpUtils.doPost(ApiEngine.GZS_HOST + "/site/obtain/addsite", map, new Callback() {
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
                        AddsiteBean bean = JSONUtils.toObject(string, AddsiteBean.class);
                        if (bean.getStatusCode() == 0) {
                            finish();
                        }
                        ToastUtil.getInstance().toastCentent(bean.getStatusMsg());
                    }
                });
            }
        });
    }

    private void querysiteBean(String siteid) {
        Map<String, Object> map = new HashMap<>();
        map.put("siteId", siteid);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/site/obtain/querysiteBean", map, new Callback() {
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
                        QuerysiteBean bean = JSONUtils.toObject(string, QuerysiteBean.class);
                        if (bean.getStatusCode() == 0) {
                            QuerysiteBean.BodyBean body = bean.getBody();
                            String siteremark = body.getSiteRemark();
                            String sitetext = body.getSiteText();
                            String username = body.getUserName();
                            String userphone = body.getUserPhone();
                            etUserName.setText(username);
                            etSiteRemark.setText(siteremark);
                            etUserPhone.setText(userphone);
                            tv_address.setText(sitetext);
                        } else {
                            ToastUtil.getInstance().toastCentent(bean.getStatusMsg());
                        }
                    }
                });

            }
        });
    }


    private void deleteSite(String siteid) {
        Map<String, Object> map = new HashMap<>();
        map.put("siteId", siteid);
        OkhttpUtils.doPost(ApiEngine.GZS_HOST + "/site/obtain/deleteSite", map, new Callback() {
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
                        QuerysiteBean bean = JSONUtils.toObject(string, QuerysiteBean.class);
                        if (bean.getStatusCode() == 0) {
                            finish();
                        }
                        ToastUtil.getInstance().toastCentent(bean.getStatusMsg());
                    }
                });

            }
        });
    }

    private void updatesite(String siteid) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", etUserName.getText().toString());
        map.put("siteText", tv_address.getText().toString());
        map.put("userPhone", etUserPhone.getText().toString());
        map.put("siteRemark", etSiteRemark.getText().toString());
        map.put("zuserPhone", MySharedPreferences.getInstance().getUserPhone());
        map.put("siteId", siteid);
        OkhttpUtils.doPost(ApiEngine.GZS_HOST + "/site/obtain/updatesite", map, new Callback() {
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
                        QuerysiteBean bean = JSONUtils.toObject(string, QuerysiteBean.class);
                        if (bean.getStatusCode() == 0) {
                            finish();
                        }
                        ToastUtil.getInstance().toastCentent(bean.getStatusMsg());
                    }
                });

            }
        });
    }
}
