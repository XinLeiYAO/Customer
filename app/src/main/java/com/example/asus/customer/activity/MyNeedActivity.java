package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.NoMessagBean;
import com.example.asus.customer.entity.UserMessageBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyNeedActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.my_need)
    TextView mNeed;
    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.title_relative)
    RelativeLayout mTitleRelative;
    @Bind(R.id.show_popup)
    LinearLayout mShowPopup;
    private String type;
    private String title;


    @Override
    public int getLayout() {
        return R.layout.activity_my_need;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText(title + "-我需要");
        mIvAdd.setVisibility(View.VISIBLE);
        mIvAdd.setImageResource(R.mipmap.liaotian);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyNeedActivity.this, ProIMWebActivity.class));
                //startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
        initMessage();
        mNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });

    }

    private void initMessage() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("type", title);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/mainCase/getMiaoShuService", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NoMessagBean noMessagBean = JSONUtils.toObject(string, NoMessagBean.class);
                        if (noMessagBean.getStatusMsg().contains("成功")) {
                            message.setText(noMessagBean.getBody().getMiaoshu());
                        }
                    }
                });
            }
        });
    }

    private void commit() {
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        JSONObject object = new JSONObject();
        try {
            object.put("rwdid", value);
            object.put("type_code", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rwdid", value);
        map.put("type_code", type);
        OkhttpUtils.doPost(ApiEngine.GZS_HOST + "/feedback/saveCustomerNeed", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(string);
                            String statusMsg = object.getString("StatusMsg");
                            if (statusMsg.contains("成功")) {
                                finish();
                            }
                            ToastUtil.getInstance().toastCentent(statusMsg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getImMessage();
    }

    private void getImMessage() {
        Map map = new HashMap();
        String value = PrefUtils.getValue(MyNeedActivity.this, Constants.PN_Onumber);
        //map.put("userName", value);
        OkhttpUtils.doGet("https://chat.wenes.cn/index/getMsgCountFromRedis?userName=" + value, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0) {
                                UserMessageBean bean = JSONUtils.toObject(string, UserMessageBean.class);
                                int count = bean.getBody().getCount();
                                if (count != 0) {
                                    mIvMessage.setVisibility(View.VISIBLE);
                                    mIvMessage.setText(count + "");
                                } else {
                                    mIvMessage.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
