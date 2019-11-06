package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.FangAnXieyiActivity;
import com.example.asus.customer.activity.home.HeTongImageActivity;
import com.example.asus.customer.activity.home.KeHuHomeWebViewActivity;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.UnreadBean;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QianYueActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.title_relative)
    RelativeLayout mTitleRelative;
    @Bind(R.id.show_popup)
    LinearLayout mShowPopup;
    @Bind(R.id.view2)
    ImageView mView2;
    @Bind(R.id.textView2)
    TextView mTextView2;
    @Bind(R.id.kehu_home_yusuan_gongyi_icgo)
    FrameLayout mKehuHomeYusuanGongyiIcgo;
    @Bind(R.id.hetong)
    ImageView mHetong;
    @Bind(R.id.kehu_home_hezuo_hetong_lin)
    RelativeLayout mKehuHomeHezuoHetongLin;
    @Bind(R.id.view)
    ImageView mView;
    @Bind(R.id.textView)
    TextView mTextView;
    @Bind(R.id.kehu_home_yusuan_gongyi_icgo1)
    FrameLayout mKehuHomeYusuanGongyiIcgo1;
    @Bind(R.id.hetong1)
    ImageView mHetong1;
    @Bind(R.id.kehu_home_hezuo_sheji_hetong_lin)
    RelativeLayout mKehuHomeHezuoShejiHetongLin;
    @Bind(R.id.view3)
    ImageView mView3;
    @Bind(R.id.textView3)
    TextView mTextView3;
    @Bind(R.id.kehu_home_yusuan_baojia2_icgo)
    FrameLayout mKehuHomeYusuanBaojia2Icgo;
    @Bind(R.id.hetong_zhaopian)
    ImageView mHetongZhaopian;
    @Bind(R.id.kehu_home_hezuo_hetong_image_lin)
    RelativeLayout mKehuHomeHezuoHetongImageLin;

    @Override
    public int getLayout() {
        return R.layout.activity_qian_yue;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String value = PrefUtils.getValue(QianYueActivity.this, Constants.UnreadData);
        UnreadBean unreadBean = JSONUtils.toObject(value, UnreadBean.class);
        UnreadBean.BodyBean body = unreadBean.getBody();
        //合同照片
        boolean htzp = body.isHTZP();
        if (htzp) {
            mHetongZhaopian.setVisibility(View.VISIBLE);
        } else {
            mHetongZhaopian.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText("签约");
        onClick();
    }

    private void onClick() {
        final String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        mKehuHomeHezuoHetongLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QianYueActivity.this, KeHuHomeWebViewActivity.class)
                        .putExtra("url", ApiEngine.ZAAPP_BASE + "Customer/contractTemplate?rwdId=" + value)
                        .putExtra("title", "合同模板"));
            }
        });
        mKehuHomeHezuoHetongImageLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QianYueActivity.this, HeTongImageActivity.class));

            }
        });
        mKehuHomeHezuoShejiHetongLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QianYueActivity.this, FangAnXieyiActivity.class));

            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
