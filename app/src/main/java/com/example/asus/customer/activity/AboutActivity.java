package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.CompanyActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;

public class AboutActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_vision_name)
    TextView tvVisionName;
    @Bind(R.id.llCompany)
    RelativeLayout llCompany;
    @Override
    public int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initData() {
        tvTitle.setText("关于");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvVisionName.setText(App.getVersionName());
        llCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, CompanyActivity.class));
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
