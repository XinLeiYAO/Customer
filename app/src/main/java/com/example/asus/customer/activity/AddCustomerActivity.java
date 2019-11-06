package com.example.asus.customer.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;

public class AddCustomerActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Override
    public int getLayout() {
        return R.layout.activity_addcustomer;
    }

    @Override
    public void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText("录入客户");
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
