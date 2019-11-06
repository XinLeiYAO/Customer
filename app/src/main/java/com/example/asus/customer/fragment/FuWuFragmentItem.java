package com.example.asus.customer.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.FuWuItemAdapter;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;

public class FuWuFragmentItem extends BaseFragment {
    @Bind(R.id.fuwu_item_recycleView)
    RecyclerView recyclerView;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fuwu_item;
    }

    @Override
    protected void FragmentInitData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        FuWuItemAdapter adapter = new FuWuItemAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
