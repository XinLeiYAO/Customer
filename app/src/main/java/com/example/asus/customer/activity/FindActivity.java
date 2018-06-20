package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.NewsListAdapter;
import com.example.asus.customer.adapter.RecommendAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.OptimizationBean;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.ProcessContract;
import com.example.asus.customer.mvp.presenter.ProcessPresenter;
import com.example.asus.customer.weight.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/16.
 */

public class FindActivity extends BaseActivity<ProcessPresenter> implements ProcessContract.View, AdapterView.OnItemClickListener, XListView.IXListViewListener {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.xlv_find)
    ListView xlvFind;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    private List<FindInformationBean.BodyBean> newsList;
    private NewsListAdapter mAdapter;
    @Bind(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private List<OptimizationBean.BodyBean> body;
    private List<RecommendBean.BodyBean> body1;
    private RecommendAdapter recommenAdapter;


    @Override
    public int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void initData() {
        initArrayList();
        ivBack.setVisibility(View.VISIBLE);
        int i = Integer.parseInt(App.starts);
        if (i==1||i==2) {
            mAdapter = new NewsListAdapter(this, body);
            xlvFind.setAdapter(mAdapter);
        } else  {
            recommenAdapter = new RecommendAdapter(this, body1);
            xlvFind.setAdapter(recommenAdapter);


        }
        initView();
    }

    private void initArrayList() {
        body = new ArrayList<>();
        body1 = new ArrayList<>();
    }

    @Override
    protected ProcessPresenter onCreatePresenter() {
        return new ProcessPresenter(this);
    }

    private void initView() {
        xlvFind.setOnItemClickListener(this);
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(App.starts.equals("1")||App.starts.equals("2")){
                    mPresenter.getOptimizationBean();
                }else {
                    mPresenter.getRecommendData(App.orderNo);
                }
            }
        });
        xlvFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (App.starts.equals("1") || App.starts.equals("2")) {
                    startActivity(new Intent(FindActivity.this, FindMoreActivity.class).putExtra("projectId", body.get(position).getProjectid()));
                } else {
                    startActivity(new Intent(FindActivity.this, FindMoreActivity.class).putExtra("projectId", body1.get(position).getProjectId()));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
          if(App.starts.equals("1")||App.starts.equals("2")){
             mPresenter.getOptimizationBean();
          }else {
            mPresenter.getRecommendData(App.orderNo);
          }
          mPresenter.getProgssData(App.orderNo);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void getProgssData(ProgssInfo.BodyBean bodyBean) {
        if (null == bodyBean) {
            tvTitle.setText("项目");
        } else {
          String  ci_clientName = bodyBean.getBaseInformation().getCi_ClientName();
            if (ci_clientName.length() > 6) {
                String substring = ci_clientName.substring(0, 6);
                tvTitle.setText(substring + "-项目");
            } else {
                tvTitle.setText(ci_clientName + "-项目");
            }
        }
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void loadCustomerInformation(MineUserInfo.BodyBean bodyBean) {

    }

    @Override
    public void getCurrentData(Picture.BodyBean body) {

    }

    @Override
    public void loadDesignDescription(DesignInfo.BodyBean bodyBean) {

    }

    @Override
    public void loadCurreerPhotoData(CurreerPhotoDataBean.BodyBean bodyBean) {

    }

    @Override
    public void volumeRoomPhooto(List<PhotoDetailsBean> mlist) {

    }

    @Override
    public void getOptimizationBean(OptimizationBean bean) {

        body.clear();
        body.addAll(bean.getBody());
        mAdapter.notifyDataSetChanged();
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }

    @Override
    public void getRecommendData(RecommendBean bean) {

        body1.clear();
        body1.addAll(bean.getBody());
        recommenAdapter.notifyDataSetChanged();
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }
}
