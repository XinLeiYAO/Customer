package com.example.asus.customer.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.fragment.YuYueFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.Bind;

public class MyYuYueActivity extends BaseActivity {
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
    @Bind(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @Bind(R.id.home_viewpager)
    ViewPager viewPager;
    //标题
    private String[] mTitles = {"全部", "待处理", "进行中", "已完工"};
    //Fragment集合
    private ArrayList<Fragment> mFagments = new ArrayList<>();
    private MyPagerAdapter adapter;


    @Override
    public int getLayout() {
        return R.layout.activity_my_yu_yue;
    }

    @Override
    public void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText("我的预约");
        initTab();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    private void initTab() {
        //全部
        for (int i = 0; i < mTitles.length; i++) {
            YuYueFragment fragment = new YuYueFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id",i+"");
            fragment.setArguments(bundle);
            mFagments.add(fragment);
        }
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
