package com.example.asus.customer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.RightBean;
import com.example.asus.customer.fragment.ServiceFragment;
import com.example.asus.customer.weight.photoview.PhotoViewFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FangandetailActivity extends BaseActivity {
//    @Bind(R.id.iv_back)
//    ImageView ivBack;
//    @Bind(R.id.tv_title)
//    TextView tvTitle;

    @Bind(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @Bind(R.id.home_viewpager)
    ViewPager viewPager;
    private MyPagerAdapter adapter;
    //标题
    private String[] mTitles = {"服务", "案例", "设计师", "工长","建材商","工人"};
    //Fragment集合
    private ArrayList<Fragment> mFagments = new ArrayList<>();
    private RightBean rightBean;
    private List<RightBean.BodyBean> body;

    private void initTab() {
        String fanganlist = getIntent().getStringExtra("fanganlist");
        int item = getIntent().getIntExtra("item", 0);

        rightBean = JSONUtils.toObject(fanganlist, RightBean.class);
        body = rightBean.getBody();
        //全部 （水电图 11 ）（结构图 1 ）（彩平图 6 ）（效果图 9 ）（施工图 10 ）
        for (int i = 0; i < body.size(); i++) {
            PhotoViewFragment fragment = new PhotoViewFragment();
            Bundle bundle = new Bundle();
            if (i==2){
                bundle.putString("id",6+"");
            }else if(i==3){
                bundle.putString("id",9+"");
            }else if(i==4){
                bundle.putString("id",10+"");
            }
            bundle.putString("id",i+"");
            fragment.setArguments(bundle);
            mFagments.add(fragment);
        }
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(item);
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
            return body.get(position).getTwoName();
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_fangandetail;
    }

    @Override
    public void initData() {
//        ivBack.setVisibility(View.GONE);
//        tvTitle.setText("服务");
        initTab();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
