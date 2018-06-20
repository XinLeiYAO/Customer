package com.example.asus.customer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.entity.NewsListInfo;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/12.
 */
public class NewsAdapter extends SingleBaseAdapter<NewsListInfo.BodyBean.ListBean, NewsAdapter.ViewHolder> {



    public NewsAdapter(Context context, List<NewsListInfo.BodyBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_news_list;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, NewsListInfo.BodyBean.ListBean data, ViewHolder holder) {

        holder.tvTitle.setText(data.getName());
        holder.tvContect.setText(data.getSummary());
        holder.tvSpare.setText(data.getSpare1());
        holder.tvViews.setText(data.getView()+"");
        holder.lvTitle.setText(data.getName());
        Glide.with(context).load(data.getCover()).into(holder.ivImg);
        holder.tvTimers.setText(data.getReleaseDate());
        if(data.getType()==0){
            holder.llZero.setVisibility(View.VISIBLE);
            holder.llThere.setVisibility(View.GONE);
            Glide.with(context).load(data.getCover()).into(holder.ivImg);
        }else if(data.getType()==3){
            holder.llZero.setVisibility(View.GONE);
            holder.llThere.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.getContentPic().get(0)).into(holder.mvImgone);
            Glide.with(context).load(data.getContentPic().get(1)).into(holder.mvImgtwo);
            Glide.with(context).load(data.getContentPic().get(2)).into(holder.mvImgthere);

        }
    }

    class ViewHolder implements SingleViewHolder {

        @Bind(R.id.iv_img)
        ImageView ivImg;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_contect)
        TextView tvContect;
        @Bind(R.id.ll_zero)
        LinearLayout llZero;
        @Bind(R.id.mv_imgone)
        ImageView mvImgone;
        @Bind(R.id.mv_imgtwo)
        ImageView mvImgtwo;
        @Bind(R.id.mv_imgthere)
        ImageView mvImgthere;
        @Bind(R.id.ll_there)
        LinearLayout llThere;
        @Bind(R.id.tv_spare)
        TextView tvSpare;
        @Bind(R.id.tv_like)
        TextView tvLike;
        @Bind(R.id.iv_like)
        ImageView ivLike;
        @Bind(R.id.tv_views)
        TextView tvViews;
        @Bind(R.id.iv_viewse)
        ImageView ivViewse;
        @Bind(R.id.tv_timers)
        TextView tvTimers;
        @Bind(R.id.lv_title)
        TextView lvTitle;
        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
