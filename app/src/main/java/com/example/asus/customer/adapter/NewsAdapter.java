package com.example.asus.customer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.commons.utils.GlideRoundTransform;
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
//        RoundedCorners roundedCorners= new RoundedCorners(12);
//        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options= new RequestOptions().transform(new GlideRoundTransform(context));
        if (data.getType()==4||data.getType()==5){
            holder.reOne.setVisibility(View.GONE);
            holder.linZong.setVisibility(View.GONE);
            holder.linFive.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.getCover()).apply(options).into(holder.imageFive);
            holder.tittleFive.setText(data.getName());
            holder.laiYuanFive.setText(data.getSpare1());
            holder.riQiFive.setText(data.getArticleDate());
            if (data.getType()==4){
                holder.zhangShuFive.setText(data.getPictureNum());
                holder.ivBofangFive.setVisibility(View.GONE);
            }else if (data.getType()==5){
                holder.zhangShuFive.setText(data.getVideoTime());
                holder.ivBofangFive.setVisibility(View.VISIBLE);
            }
        }else {
            holder.tvTitle.setText(data.getName());
            holder.tvContect.setText(data.getSummary());
            Log.e("========",data.getSpare1());
            holder.tvSpare.setText(data.getSpare1());
            holder.tvViews.setText(data.getView()+"");
            holder.lvTitle.setText(data.getName());
            holder.tvLike.setText(data.getArticleDate());
            holder.tvSpareZero.setText(data.getSpare1());
            holder.tvLikeZero.setText(data.getArticleDate());

            Glide.with(context).load(data.getCover()).apply(options).into(holder.ivImg);
//            holder.tvTimers.setText(data.getReleaseDate());
            if(data.getType()==0){
                holder.linThree.setVisibility(View.GONE);
                holder.linZero.setVisibility(View.VISIBLE);
                holder.llZero.setVisibility(View.VISIBLE);
                holder.llThere.setVisibility(View.GONE);
                Glide.with(context).load(data.getCover()).apply(options).into(holder.ivImg);
            }else if(data.getType()==3){
                holder.linZero.setVisibility(View.GONE);
                holder.linThree.setVisibility(View.VISIBLE);
                holder.llZero.setVisibility(View.GONE);
                holder.llThere.setVisibility(View.VISIBLE);
                Glide.with(context).load(data.getContentPic().get(0)).apply(options).into(holder.mvImgone);
                Glide.with(context).load(data.getContentPic().get(1)).apply(options).into(holder.mvImgtwo);
                Glide.with(context).load(data.getContentPic().get(2)).apply(options).into(holder.mvImgthere);

            }
        }

    }

    class ViewHolder implements SingleViewHolder {
        @Bind(R.id.iv_news_list)
        ImageView ivNewsList;
        @Bind(R.id.tv_news_list_title)
        TextView tvNewsListTitle;
        @Bind(R.id.tv_news_list_department)
        TextView tvNewsListDepartment;
        //        @Bind(R.id.tv_news_list_great)
//        TextView tvNewsListGreat;
//        @Bind(R.id.tv_news_list_read)
//        TextView tvNewsListRead;
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
        //type=5
        @Bind(R.id.lin_five)
        LinearLayout linFive;
        @Bind(R.id.tittle_five)
        TextView tittleFive;
        @Bind(R.id.zhangshu_five)
        TextView zhangShuFive;
        @Bind(R.id.laiyuan_five)
        TextView laiYuanFive;
        @Bind(R.id.riqi_five)
        TextView riQiFive;
        @Bind(R.id.image_five)
        ImageView imageFive;
        @Bind(R.id.iv_bofang_five)
        ImageView ivBofangFive;

        @Bind(R.id.re_one)
        RelativeLayout reOne;
        @Bind(R.id.lin_zong)
        LinearLayout linZong;
        @Bind(R.id.lin_zero)
        LinearLayout linZero;
        @Bind(R.id.lin_three)
        LinearLayout linThree;
        //zero
        @Bind(R.id.tv_spare_zero)
        TextView tvSpareZero;
        @Bind(R.id.tv_like_zero)
        TextView tvLikeZero;
        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
