package com.example.asus.customer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;

public class AgainDesignActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.head_saoyisao)
    ImageView ivSaoyisao;
    @Bind(R.id.iv_add)
    ImageView iv_add;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    public int getLayout() {
        return R.layout.activity_again_design;
    }

    @Override
    public void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("重新设计");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setTextColor(getResources().getColor(R.color.colorcjs));
        tvRight.setText("提交");
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
