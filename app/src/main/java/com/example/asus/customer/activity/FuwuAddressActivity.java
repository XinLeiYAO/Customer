package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.FuwuAddressAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.AddsiteBean;
import com.example.asus.customer.entity.QuerysiteBean;
import com.example.asus.customer.entity.QuerysiteListBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FuwuAddressActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.addnewadress)
    TextView addnewadress;
    List<QuerysiteListBean.BodyBean> body = new ArrayList<>();
    private FuwuAddressAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_fuwu_address;
    }

    @Override
    public void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("选择服务地址");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new FuwuAddressAdapter(this,body);
        adapter.setOnItemClickListener(new FuwuAddressAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent =getIntent();
                //这里使用bundle绷带来传输数据
                Bundle bundle =new Bundle();
                //传输的内容仍然是键值对的形式
                bundle.putString("userPhone",body.get(position).getUserPhone());//回发的消息
                bundle.putString("userName",body.get(position).getUserName());
                bundle.putString("siteremark",body.get(position).getSiteRemark());
                bundle.putString("siteid",body.get(position).getSiteId());
                bundle.putString("siteText",body.get(position).getSiteText());
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        adapter.setOnItemButtonClickListener(new FuwuAddressAdapter.ItemButtonClickListener() {
            @Override
            public void onItemButtonClick(int position) {
                startActivity(new Intent(FuwuAddressActivity.this,AddNewAdressActivity.class).putExtra("type",1).putExtra("siteid",body.get(position).getSiteId()));

            }
        });
        recyclerView.setAdapter(adapter);

        addnewadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FuwuAddressActivity.this,AddNewAdressActivity.class).putExtra("type",0));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        querysiteList();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    private void querysiteList() {
        Map<String, Object> map = new HashMap<>();
        map.put("zuserPhone", MySharedPreferences.getInstance().getUserPhone());
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "site/obtain/querysiteList", map, new Callback() {
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
                            QuerysiteListBean bean = JSONUtils.toObject(string, QuerysiteListBean.class);
                            if(bean.getStatusCode() == 0){
                                body.clear();
                                body.addAll(bean.getBody());
                                adapter.notifyDataSetChanged();
                            }else{
                                ToastUtil.getInstance().toastCentent(bean.getStatusMsg());
                            }
                        }
                    });

            }
        });
    }
}
