package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.KeHuHomeLiangFangXianChangActivity;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.NewFuYongAdapter;
import com.example.asus.customer.adapter.NewShouGaoAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.KeHuHomeLiangFangBean;
import com.example.asus.customer.entity.NewJieGouBean;
import com.example.asus.customer.entity.NewShouGaoBean;
import com.example.asus.customer.entity.NoMessagBean;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewShouGaoActivity extends BaseActivity {
    private TextView text_name;
    private ImageView image;
    private LinearLayout no_message;
    private LinearLayout have_message;
    private String type;
    private RecyclerView recyclerView;
    private List<NewShouGaoBean.BodyBean> list = new ArrayList<>();
    private NewShouGaoAdapter adapter;


    @Override
    public int getLayout() {
        return R.layout.activity_new_shou_gao;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = (TextView) findViewById(R.id.tv_title);
        text_name = (TextView) findViewById(R.id.text_name);
        image = (ImageView) findViewById(R.id.have_image);
        no_message = (LinearLayout) findViewById(R.id.no_message);
        have_message = (LinearLayout) findViewById(R.id.have_message);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new NewShouGaoAdapter(this,list);
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new NewShouGaoAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    NewShouGaoBean.BodyBean bodyBean = list.get(i);
                    String catalogName = "手稿图";//标题
                    String filePath = bodyBean.get_$123().get(i).getFilePath();//图片路径
                    jsonArray.put(filePath);
                    jsonContent.put(catalogName);
                }

                Intent intent = new Intent(NewShouGaoActivity.this, ShowImageActivity.class);
                intent.putExtra("title", "详细照片");
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "手稿图");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", josnText.toString());
                startActivity(intent);
            }
        });
        title.setText("手稿图");
        initMessage();
    }

    private void initMessage() {
        HashMap<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        map.put("rwdid", value);
        map.put("catalogID", 123);
        OkhttpUtils.doGet(ApiEngine.FS_API_HOST + "/api/DWorksDetail/WorksDetailArray", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getShouGao?rwdId=12-202079}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                 final NewShouGaoBean bean = JSONUtils.toObject(string, NewShouGaoBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getBody() != null) {
                            NewShouGaoBean.BodyBean body = bean.getBody();
                            list.add(body);
                            adapter.notifyDataSetChanged();
                            no_message.setVisibility(View.GONE);
                        } else {
                            no_message.setVisibility(View.VISIBLE);
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
