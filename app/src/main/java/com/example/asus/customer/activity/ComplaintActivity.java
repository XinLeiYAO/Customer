package com.example.asus.customer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.ComplantAdapter;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/17.
 */

public class ComplaintActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.complant_grid)
    GridView complantGrid;
    private List<Integer> integers;

    @Override
    public int getLayout() {
        return R.layout.activity_complaint;
    }

    @Override
    public void initData() {
        integers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            integers.add(R.mipmap.aaa);

        }
        ComplantAdapter complantAdapter = new ComplantAdapter(this, integers);
        complantGrid.setAdapter(complantAdapter);
        complantGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == integers.size()) {
                    Toast.makeText(ComplaintActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
