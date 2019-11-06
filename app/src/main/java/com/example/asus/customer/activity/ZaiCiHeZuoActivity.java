package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.MyExpandableListJiaiLiuHeZuoView;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.JiaoLiuHeZuoBean;
import com.example.asus.customer.mvp.contract.JiaoLiuContract;
import com.example.asus.customer.mvp.presenter.JiaoLiuHeZuoPresenter;
import com.example.asus.customer.weight.MyExpandableListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZaiCiHeZuoActivity extends BaseActivity<JiaoLiuHeZuoPresenter>implements JiaoLiuContract.JiaoLiuHeZuoView {

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
    @Bind(R.id.expandable)
    MyExpandableListView expandable;
    @Bind(R.id.zaicihezuo_click)
    LinearLayout zaicihezuoClick;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;
    private ArrayList<JiaoLiuHeZuoBean.BodyBean> arrayList = new ArrayList<>();
    private MyExpandableListJiaiLiuHeZuoView myExpandableListJiaiLiuHeZuoView;
    @Override
    public int getLayout() {
        return R.layout.activity_zai_ci_he_zuo;
    }

    @Override
    public void initData() {
        mTvTitle.setText("再次合作");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                String value = PrefUtils.getValue(ZaiCiHeZuoActivity.this, Constants.PN_Onumber);
                mPresenter.getJiaoLiuHeZuoDate(value);
            }
        });
        //设置 Header 为 默认刷新 样式
        smart.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 关闭加载
        smart.setEnableLoadMore(false);
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        mPresenter.getJiaoLiuHeZuoDate(value);
        myExpandableListJiaiLiuHeZuoView = new MyExpandableListJiaiLiuHeZuoView(arrayList, this);
        expandable.setAdapter(myExpandableListJiaiLiuHeZuoView);
        //设置 属性 GroupIndicator 去掉默认向下的箭头
        expandable.setGroupIndicator(null);
        zaicihezuoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZaiCiHeZuoActivity.this, HeZuoTiJiaoActivity.class));
            }
        });
    }

    @Override
    protected JiaoLiuHeZuoPresenter onCreatePresenter() {
        return new JiaoLiuHeZuoPresenter(this);
    }


    @Override
    public void getHeZuoDate(JiaoLiuHeZuoBean json) {
        arrayList.clear();
        arrayList.addAll(json.getBody());
        myExpandableListJiaiLiuHeZuoView.notifyDataSetChanged();
    }

    @Override
    public void getHeZuoDateErro(String erro) {
        ShowUtils.Toastshort(this, erro);

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
