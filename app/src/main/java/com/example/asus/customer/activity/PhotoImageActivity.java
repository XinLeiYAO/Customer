package com.example.asus.customer.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.MyViewPagerAdapter;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/20.
 */

public class PhotoImageActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.imgs_viewpager)
    ViewPager imgsViewpager;
    @Bind(R.id.img_browse_back)
    ImageView imgBrowseBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;


    @Override
    public int getLayout() {
        return R.layout.photo_item;
    }

    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);

        List<String> imagee = getIntent().getStringArrayListExtra(Constants.JUMPLIST);
        int intExtra = getIntent().getIntExtra(Constants.INDEXES, 0);
        String stringExtra = getIntent().getStringExtra(Constants.TITLE);
        tvTitle.setText(stringExtra);
        imgsViewpager = (ViewPager) this.findViewById(R.id.imgs_viewpager);
        imgsViewpager.setOffscreenPageLimit(2);
        //imagee.add("http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png");
        PagerAdapter adapter = new MyViewPagerAdapter(this, imagee);
        imgsViewpager.setAdapter(adapter);
        imgsViewpager.setCurrentItem(intExtra);
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
