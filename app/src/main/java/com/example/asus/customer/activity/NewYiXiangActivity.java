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
import com.example.asus.customer.activity.home.KeHuHomeLiangFangXianChangActivity;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.NewFuYongAdapter;
import com.example.asus.customer.adapter.NewYiXiangAdapter;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.entity.KeHuHomeLiangFangBean;
import com.example.asus.customer.entity.NewShouGaoBean;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class NewYiXiangActivity extends BaseActivity {

    private String type;
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_new_yi_xiang;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        find();
        list.add("https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1559021530KXpX4BfKzS.jpg!w600");
        list.add("https://wenes01.oss-cn-beijing.aliyuncs.com/wenesImg/1559021530KXpX4BfKzS.jpg!w600");
        NewYiXiangAdapter adapter = new NewYiXiangAdapter(this, list, type);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new NewYiXiangAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {

                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();

                String catalogName = type;//标题
                String filePath = list.get(position);//图片路径
                jsonArray.put(filePath);
                jsonContent.put(catalogName);


                Intent intent = new Intent(NewYiXiangActivity.this, ShowImageActivity.class);
                intent.putExtra("title", ""+type);
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", ""+type);
                startActivity(intent);
            }
        });
    }

    public void find() {
        ImageView back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = (TextView) findViewById(R.id.tv_title);
        TextView miaoshu = (TextView) findViewById(R.id.miaoshu);
        LinearLayout no_message = (LinearLayout) findViewById(R.id.no_message);
        recyclerView = (RecyclerView) findViewById(R.id.jiegoutu_recycler);
        title.setText(type);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
