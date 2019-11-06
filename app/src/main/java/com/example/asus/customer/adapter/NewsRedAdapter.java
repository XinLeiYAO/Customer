package com.example.asus.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.entity.InfoMessageBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/5/18.
 */

public class NewsRedAdapter extends SingleBaseAdapter<InfoMessageBean.BodyBean, NewsRedAdapter.ViewHolder> {


    public NewsRedAdapter(Context context, List<InfoMessageBean.BodyBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_xiaoxi;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, InfoMessageBean.BodyBean data, ViewHolder holder) {
        holder.tvTasktitle.setText(data.getTitle() + "");
        holder.tvTime.setText(data.getCreate_date());
        holder.tvPerson.setText("内容 : " + data.getTxt());
        holder.zongliang.setText(data.getTask_num() + "");
        holder.allowance.setText(data.getTask_balance() + "");
        holder.tvReward.setText(data.getReward_money() + "");
        String state = (String) data.getState();
        if (state != null) {
            if ("0".equals(state)) {
//            holder.tvTasktitle.setTextColor(R.color.xiaoxi_title1);
                holder.tvReward.setTextColor(Color.parseColor("#FF4143"));
                holder.tvTime.setTextColor(Color.parseColor("#FF4143"));
                holder.tvTasktitle.setTextColor(context.getResources().getColor(R.color.text_black));
                holder.content.setTextColor(context.getResources().getColor(R.color.text_black));
                holder.xiaoxiGongdian.setVisibility(View.VISIBLE);
            } else if ("1".equals(state)) {
//            holder.tvTasktitle.setTextColor(R.color.xiaoxi_title2);
                holder.tvReward.setTextColor(Color.parseColor("#14C54F"));
                holder.tvTime.setTextColor(Color.parseColor("#14C54F"));
                holder.tvTasktitle.setTextColor(context.getResources().getColor(R.color.kehuhome_text2));
                holder.content.setTextColor(context.getResources().getColor(R.color.kehuhome_text2));
                holder.xiaoxiGongdian.setVisibility(View.GONE);

            }
        }
    }

    public class ViewHolder implements SingleViewHolder {
        @Bind(R.id.tv_tasktitle)
        TextView tvTasktitle;
        @Bind(R.id.allowance)
        TextView allowance;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_person)
        TextView tvPerson;
        @Bind(R.id.tv_reward)
        TextView tvReward;
        @Bind(R.id.ll_view)
        LinearLayout llView;
        @Bind(R.id.zongliang)
        TextView zongliang;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.xiaoxi_gongdian)
        ImageView xiaoxiGongdian;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
