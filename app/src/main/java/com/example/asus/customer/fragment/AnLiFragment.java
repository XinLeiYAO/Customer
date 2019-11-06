package com.example.asus.customer.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.AnliAdapter;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.entity.AnliListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class AnLiFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    List<AnliListBean.BodyBean> list = new ArrayList<>();
    private AnliAdapter serviceAdapter;

    @Bind(R.id.smart)
    SmartRefreshLayout smart;
    @Bind(R.id.lin_erro)
    LinearLayout linErro;
    private int pagesize = 10;
    private int page = 1;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_service;
    }

    @Override
    protected void FragmentInitData() {
        AnliListData();
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                list.clear();
                page=1;
                AnliListData();
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                page++;
                AnliListData();
            }

        });
        //设置 Header 为 默认刷新 样式
        smart.setRefreshHeader(new ClassicsHeader(getActivity()));
        //设置 Footer 关闭加载
//        smart.setEnableLoadMore(false);
        linErro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AnliListData();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new SpacesItemDecoration(40));
        recyclerView.setLayoutManager(manager);
        serviceAdapter = new AnliAdapter(getActivity(),list);
        recyclerView.setAdapter(serviceAdapter);
    }


    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }

    private void AnliListData() {
        Map<String, Object> map = new HashMap<>();
        map.put("op", "case_app");
        map.put("pagesize", String.valueOf(pagesize));
        map.put("page", String.valueOf(page));
        OkhttpUtils.doGet("https://www.wenes.cn/api.php", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        list.clear();
                        AnliListBean bean = JSONUtils.toObject(string, AnliListBean.class);
                        List<AnliListBean.BodyBean> body = bean.getBody();

                        if (page==1&&body.size() == 0) {
                            linErro.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            linErro.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            if (body.size() != 0){
                                list.addAll(body);
                                serviceAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

            }
        });
    }
}
