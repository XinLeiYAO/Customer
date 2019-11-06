package com.example.asus.customer.activity;

import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.CustomerManagerAdapter;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.weight.sliderecyclerview.InventoryAdapter;
import com.example.asus.customer.weight.sliderecyclerview.SlideRecyclerView;
import com.example.asus.customer.weight.sliderecyclerview.bean.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;

public class CustomerMangerActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.recyclerView)
    SlideRecyclerView recycler_view_list;

    private List<Inventory> mInventories;
    private InventoryAdapter mInventoryAdapter;
    @Override
    public int getLayout() {
        return R.layout.activity_customermanager;
    }

    @Override
    public void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("客户管理");
        recycler_view_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_inset));
        recycler_view_list.addItemDecoration(itemDecoration);
        mInventories = new ArrayList<>();
        Inventory inventory;
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            inventory = new Inventory();
            inventory.setItemDesc("测试数据" + i);
            inventory.setQuantity(random.nextInt(100000));
            inventory.setItemCode("有效");
            inventory.setDate("已接通");
            inventory.setVolume(random.nextFloat());
            mInventories.add(inventory);
        }
        mInventoryAdapter = new InventoryAdapter(this, mInventories);
        recycler_view_list.setAdapter(mInventoryAdapter);
        mInventoryAdapter.setOnDeleteClickListener(new InventoryAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {
                mInventories.remove(position);
                mInventoryAdapter.notifyDataSetChanged();
                recycler_view_list.closeMenu();
            }
        });


    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

}
