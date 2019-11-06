package com.example.asus.customer.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.ServiceAdapter;
import com.example.asus.customer.adapter.ServiceListBean;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class ServiceFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    List<ServiceListBean> list = new ArrayList<>();
    String[] titles = {"量房", "方案", "预算", "材料", "工人", "项目经理", "施工"};
    String[] contents1 = {"上门量房,资料系统呈现", "一图一院,团队多对一", "装修预算≠报价落地", "厂家直供，无中间商", "挂牌上岗，培训严格", "资质认证，证书齐全", "全程订单化，现场监控"};
    String[] contents2 = {"量房资料免费送", "设计师+专项设计院", "后期绝无增项", "成本价配送上门", "工艺升级", "高效专业", "保质保量"};
    int[] imgUrl = {R.mipmap.service_menu1, R.mipmap.service_menu2, R.mipmap.service_menu3, R.mipmap.service_menu4, R.mipmap.service_menu5, R.mipmap.service_menu6, R.mipmap.service_menu7};

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_service;
    }

    @Override
    protected void FragmentInitData() {
//        Bundle bundle = getArguments();
//        String id = (String) bundle.get("id");
//        if (id.equals("0")) {
            initData();
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(new SpacesItemDecoration(40));
            recyclerView.setLayoutManager(manager);
            ServiceAdapter serviceAdapter = new ServiceAdapter(list);
            recyclerView.setAdapter(serviceAdapter);
//        }

    }

    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            ServiceListBean listBean = new ServiceListBean();
            listBean.setTitle(titles[i]);
            listBean.setContent1(contents1[i]);
            listBean.setContent2(contents2[i]);
            listBean.setImgUrl(imgUrl[i]);
            list.add(listBean);
        }

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
}
