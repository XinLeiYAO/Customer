package com.example.asus.customer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.MyYuYueActivity;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.Bind;

public class FuWuFragment extends BaseFragment {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @Bind(R.id.home_viewpager)
    ViewPager viewPager;
    private MyPagerAdapter adapter;
    //标题
    private String[] mTitles = {"服务", "案例", "设计师", "工长", "建材商", "工人"};
    //Fragment集合
    private ArrayList<Fragment> mFagments = new ArrayList<>();

    @Override
    protected int getFragmentLayout() {
        return R.layout.fuwu_layout;
    }

    @Override
    protected void FragmentInitData() {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("服务");
        initTab();
    }


    private void initTab() {
        //全部
//        for (int i = 0; i < mTitles.length; i++) {
            ServiceFragment fragment = new ServiceFragment();
            AnLiFragment fragment1 = new AnLiFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("id", i + "");
//            fragment.setArguments(bundle);
            mFagments.add(fragment);
            mFagments.add(new AnLiFragment());
            mFagments.add(new ShejiShiFragment());
            mFagments.add(new GongzhangFragment());
            mFagments.add(new ServiceFragment());
            mFagments.add(new GongrenFragment());

//        }
        adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
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

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
