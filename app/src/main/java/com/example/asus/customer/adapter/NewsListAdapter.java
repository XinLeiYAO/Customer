package com.example.asus.customer.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.OptimizationBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/4/20.
 */

public class NewsListAdapter extends SingleBaseAdapter<OptimizationBean.BodyBean, NewsListAdapter.ViewHoider> {


    public NewsListAdapter(Context context, List<OptimizationBean.BodyBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.find_item;
    }

    @Override
    public ViewHoider initViewHolder() {
        return new ViewHoider();
    }

    @Override
    public void onBindView(int position, OptimizationBean.BodyBean data, ViewHoider holder) {
        Glide.with(context).load(data.getMainurl()).into(holder.findImage);
        holder.findIntroduce.setText(data.getProjectname());

    }

    public class ViewHoider implements SingleViewHolder {
        @Bind(R.id.find_image)
        ImageView findImage;
        @Bind(R.id.find_introduce)
        TextView findIntroduce;
        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
