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

import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.NewGongYongAdapter;
import com.example.asus.customer.adapter.NewQiYeAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.AnLiBean;
import com.example.asus.customer.entity.QiYeBean;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LiangFangQiyeActivity extends BaseActivity {
    @Bind(R.id.lin_erro)
    LinearLayout linErro;

    private String str_title;
    private NewQiYeAdapter adapter;
    private List<QiYeBean.BodyBean> list = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_liang_fang_qiye;
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
        adapter = new NewQiYeAdapter(this,list);
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new NewGongYongAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    QiYeBean.BodyBean bodyBean = list.get(i);
                    String catalogName = bodyBean.getCatalogName();//标题
                    String filePath = bodyBean.getFilePath();//图片路径
                    String projectName = bodyBean.getProjectBrief();//描述
                    josnText.put(projectName);
                    jsonArray.put(filePath);
                    jsonContent.put(catalogName);
                }

                Intent intent = new Intent(LiangFangQiyeActivity.this, ShowImageActivity.class);
                intent.putExtra("title", "详细照片");
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", josnText.toString());
                startActivity(intent);
            }
        });
        initMessage();
    }

    private void initMessage() {
        showLoading();
        Map<String,Object> map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        map.put("rwdId",value);
        //http://10.10.13.44:7900/feedback/WorksDetailArray?rwdId=11-223374
        OkhttpUtils.doGet(ApiEngine.GZS_HOST+"/feedback/WorksDetailArray", map, new Callback() {
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
                        QiYeBean bean = JSONUtils.toObject(string, QiYeBean.class);
                        List<QiYeBean.BodyBean> body = bean.getBody();
                        if(body != null && body.size() > 0){
                            list.addAll(body);
                            adapter.notifyDataSetChanged();
                        }else{
                            linErro.setVisibility(View.VISIBLE);
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
