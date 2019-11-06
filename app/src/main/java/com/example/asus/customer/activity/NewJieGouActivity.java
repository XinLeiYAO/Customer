package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.KeHuHomeLiangFangXianChangActivity;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.KeHuHomeLiangFangAdapter;
import com.example.asus.customer.adapter.NewFuYongAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.KeHuHomeLiangFangBean;
import com.example.asus.customer.entity.NewJieGouBean;
import com.example.asus.customer.entity.NewShouGaoBean;
import com.example.asus.customer.entity.NoMessagBean;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewJieGouActivity extends BaseActivity {

    private TextView title;
    private TextView miaoshu;
    private LinearLayout no_message;
    private RecyclerView recyclerView;
    private String type;
    private List<NewJieGouBean.BodyBean> list = new ArrayList<>();
    private NewFuYongAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_new_jie_gou;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        find();
        adapter = new NewFuYongAdapter(this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new NewFuYongAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    NewJieGouBean.BodyBean bodyBean = list.get(i);
                        String catalogName = "结构图";//标题
                        String filePath = bodyBean.getImageUrl();//图片路径
                        jsonArray.put(filePath);
                        jsonContent.put(catalogName);
                    }

                Intent intent = new Intent(NewJieGouActivity.this, ShowImageActivity.class);
                intent.putExtra("title", "详细照片");
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "结构图");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", josnText.toString());
                startActivity(intent);
            }
        });
        initMessage();
    }

    private void initMessage() {
        list.clear();
        HashMap<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        map.put("rwdId", value);
        //map.put("type", type);
        map.put("CatalogId", "57");
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/maincase/program/getWorksDetailInfoByRwdId", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getMiaoShu();
                //Response{protocol=h2, code=200, message=,
                // url=https://khdapi.wenes.cn/maincase/program/getWorksDetailInfoByRwdId?rwdid=12-202079&type=量房CatalogId=57}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final NewJieGouBean bean = JSONUtils.toObject(string, NewJieGouBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getBody() != null && bean.getBody().size() > 0) {
                            List<NewJieGouBean.BodyBean> body = bean.getBody();
                            list.addAll(body);
                            adapter.notifyDataSetChanged();
                        }else{
                            getMiaoShu();
                        }
                    }
                });
            }
        });
    }

    private void getMiaoShu() {
        no_message.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);

        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/mainCase/getMiaoShuService", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final NoMessagBean bean = JSONUtils.toObject(string, NoMessagBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(bean.getBody() != null && bean.getStatusMsg().contains("成功")){
                            miaoshu.setText(bean.getBody().getMiaoshu());
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
        title = (TextView) findViewById(R.id.tv_title);
        miaoshu = (TextView) findViewById(R.id.miaoshu);
        no_message = (LinearLayout) findViewById(R.id.no_message);
        recyclerView = (RecyclerView) findViewById(R.id.jiegoutu_recycler);
        title.setText("结构图");
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
