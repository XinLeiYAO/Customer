package com.example.asus.customer.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.entity.DevelopmentInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DevelopmentAdapter extends SingleBaseAdapter<DevelopmentInfo.BodyBean, DevelopmentAdapter.ViewHodler> {




    public DevelopmentAdapter(Context context, List<DevelopmentInfo.BodyBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.model_development_item_layout;
    }

    @Override
    public ViewHodler initViewHolder() {
        return new ViewHodler();
    }

    @Override
    public void onBindView(int position, DevelopmentInfo.BodyBean data, ViewHodler holder) {
        holder.tvTitleLeft.setText(data.getProjectName());
        holder.tvTitleRight.setText(data.getE_Operate());
        holder.tvOrder.setText(data.getPN_Onumber());
        holder.tvArea.setText(data.getHS_mianji());
        holder.tvDentity.setText(data.getC_Identity()+"：");
        holder.tvWorker.setText(data.getC_Name());
        Glide.with(context).load(data.getZipPath()).into(holder.ivLeftimg);
    }

    public class ViewHodler implements SingleViewHolder {
        @Bind(R.id.tv_title_left)
        TextView tvTitleLeft;
        @Bind(R.id.tv_title_right)
        TextView tvTitleRight;
        @Bind(R.id.iv_leftimg)
        ImageView ivLeftimg;
        @Bind(R.id.tv_order)
        TextView tvOrder;
        @Bind(R.id.tv_area)
        TextView tvArea;
        @Bind(R.id.tv_worker)
        TextView tvWorker;
        @Bind(R.id.tv_dentity)
        TextView tvDentity;
        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
