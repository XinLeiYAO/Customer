package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.MyDingDanAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.NewBothBean;
import com.example.asus.customer.entity.QuanBuBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewBothActivity extends BaseActivity {

    private String typeCode;
    private ListView listView;
    List<QuanBuBean.BodyBean> list = new ArrayList<>();
    private MyDingDanAdapter adapter;
    private QuanBuBean bean;

    @Override
    public int getLayout() {
        return R.layout.activity_new_both;
    }

    @Override
    public void initData() {
        typeCode = getIntent().getStringExtra("typeCode");
        find();
        //initMessage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMessage();
    }

    private void initMessage() {
        showLoading();
        list.clear();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(NewBothActivity.this, Constants.PN_Onumber);
        map.put("rwdid", value);
        if (!TextUtils.isEmpty(typeCode)) {
            map.put("typeCode", typeCode);
        }

        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getTuZhi", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/mainCase/getTreeTwo?code=lf}
                //Response{protocol=h2, code=404, message=,
                // Response{protocol=http/1.1, code=200, message=, url=http://10.10.13.44:7900/feedback/getTuZhi?rwdId=12-202079&typeCode=sgt}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bean = JSONUtils.toObject(string, QuanBuBean.class);
                        List<QuanBuBean.BodyBean> body = bean.getBody();
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
        final ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("全部订单");
        ListView listView = (ListView) findViewById(R.id.listView);
//        adapter = new MyDingDanAdapter(NewBothActivity.this,list);
//        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(bean != null && bean.getBody() != null){
//                    QuanBuBean.BodyBean body = bean.getBody().get(position);
//                    int stage = body.getStage();
//                    if(stage == 0){
//                        //进行中
//                        startActivity(new Intent(NewBothActivity.this, DingDanIngActivity.class)
//                                .putExtra("orderNo","1")
//                                .putExtra("danhao",body.getOrderNo())
//                                .putExtra("stage","0"));
//                    }else if(stage == 1){
//                        //待确认
//                        startActivity(new Intent(NewBothActivity.this, MyDaiQueRenActivity.class)
//                                .putExtra("orderNo","666")
//                                .putExtra("danhao",body.getOrderNo())
//                                .putExtra("stage","2")
//                                .putExtra("orderno",body.getOrderNo())
//                                .putExtra("type",body.getType_code())
//                                .putExtra("typeName",body.getType())
//                                .putExtra("time",body.getExpectTime())
//                        );
//                    }else if(stage == 2){
//                        //待评价
//                        startActivity(new Intent(NewBothActivity.this, FaBiaoMessageActivity.class)
//                                .putExtra("orderno", body.getOrderNo())
//                                .putExtra("type", body.getType())
//                                .putExtra("typeCode", body.getType_code())
//                        );
//                    }else{
//                        //订单详情
//                        startActivity(new Intent(NewBothActivity.this, DingDanXiangQingActivity.class)
//                                .putExtra("orderNo","1")
//                                .putExtra("stage","4")
//                                .putExtra("danhao",body.getOrderNo())
//                        );
//                    }
//                }

            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
