package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.KeHuHomeWebViewActivity;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.NewFuYongAdapter;
import com.example.asus.customer.adapter.NewGongYongAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.AnLiBean;
import com.example.asus.customer.entity.DingDanXiangQingBean;
import com.example.asus.customer.entity.NewJieGouBean;
import com.example.asus.customer.entity.NewQiYeBean;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewQiYeActivity extends BaseActivity {
    private List<NewQiYeBean.BodyBean> list = new ArrayList<>();
    private String str_title;
    private NewGongYongAdapter adapter;
    private NewQiYeBean bean;


    @Override
    public int getLayout() {
        return R.layout.activity_new_qi_ye;
    }

    @Override
    public void initData() {
        str_title = getIntent().getStringExtra("title");
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText(str_title);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //initList();
        adapter = new NewGongYongAdapter(this,list);
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new NewGongYongAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    NewQiYeBean.BodyBean bodyBean = list.get(i);
                    String catalogName = bodyBean.getIndustryTypeName();//标题
                    String filePath = bodyBean.getPreview();//图片路径
                    String projectName = bodyBean.getProjectName();//图片详细介绍
                    //图片详细介绍
                    josnText.put(projectName);
                    //图片路径/
                    jsonArray.put(filePath);
                    //标题
                    jsonContent.put(catalogName);
                }

//                Intent intent = new Intent(NewQiYeActivity.this, ShowImageActivity.class);
//                intent.putExtra("title", "详细照片");
//                intent.putExtra("json", jsonArray.toString());
//                intent.putExtra("context", "");
//                intent.putExtra("index", position);
//                intent.putExtra("jsonContent", jsonContent.toString());
//                intent.putExtra("text", josnText.toString());
//                startActivity(intent);
                Intent intent = new Intent(NewQiYeActivity.this, KeHuHomeWebViewActivity.class);
                NewQiYeBean.BodyBean bodyBean = list.get(position);
                String url = bodyBean.getUrl();
//                String replace = url.replace("www", "m");
//                intent.putExtra("url",replace);
                intent.putExtra("url",url);
                intent.putExtra("title","案例");
                startActivity(intent);
            }
        });
        initMessage();
    }

    private void initMessage() {
        showLoading();
        Map<String,Object>map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        map.put("rwdId",value);
        map.put("sort","asc");
        OkhttpUtils.doGet(ApiEngine.FS_API_HOST+"/api/program/getCaseList", map, new Callback() {
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
                        bean = JSONUtils.toObject(string, NewQiYeBean.class);
                        List<NewQiYeBean.BodyBean> body = bean.getBody();
                        list.addAll(body);
                        adapter.notifyDataSetChanged();
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
