package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.FanKuiHistoryAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.DingDanXiangQingBean;
import com.example.asus.customer.entity.NewHistoryBean;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewHistoryActivity extends BaseActivity {

    private String typeName;
    private String typeCode;
    private Intent intent;
    private String orderNo;
    List<NewHistoryBean.BodyBean> list = new ArrayList<>();
    private FanKuiHistoryAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_new_history;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        typeName = intent.getStringExtra("typeName");
        typeCode = intent.getStringExtra("type");
        orderNo = intent.getStringExtra("orderno");
        find();
        initMessage();
    }

    private void initMessage() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        if (typeName.equals("效果图") || typeName.equals("施工图") || typeName.equals("水电图")) {
            map.put("orderNo", orderNo);
        }else{
            String value = PrefUtils.getValue(NewHistoryActivity.this, Constants.PN_Onumber);
            map.put("rwdId", value);
        }
        map.put("type", typeCode);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getAcceptanceRecordByRwdId", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
                // Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getAcceptanceRecordByRwdId?rwdId=12-202079&type=cpt}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NewHistoryBean bean = JSONUtils.toObject(string, NewHistoryBean.class);
                        List<NewHistoryBean.BodyBean> body = bean.getBody();
                        if (body.size() > 0 && body != null) {
                            list.addAll(body);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    private void find() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("历史记录");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new FanKuiHistoryAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
