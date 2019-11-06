package com.example.asus.customer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.entity.InfoMessageBean;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjh on 2018/3/8.
 */
public class MessageNewAdapter extends SingleBaseAdapter<InfoMessageBean.BodyBean, MessageNewAdapter.ViewHolder> {


    public MessageNewAdapter(Context context, List<InfoMessageBean.BodyBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_messagenew;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, InfoMessageBean.BodyBean data, ViewHolder holder) {
        String group_name = data.getGroup_name();
        holder.tvTitle.setText(group_name);
        String time = data.getCreate_date();
        if (!StringUtils.isEmpty(time) && time.length() >= 10) {
            time = time.substring(0, 10);
            holder.tvTime.setText(time);
        }
        String txt = (String) data.getTxt();
        holder.tvContent.setText(TextUtils.isEmpty(txt) ? "暂无" + (group_name.equals("事件") ? "" : group_name) + "事件" : txt);
        String notice_count = (String) data.getNotice_count();
        if (!StringUtils.isEmpty(notice_count) && !notice_count.equals("0")) {
            if (notice_count.length() >= 3) {
                holder.tvMsgnum.setBackgroundResource(R.mipmap.xiaoxi_3);
            } else if (notice_count.length() >= 2) {
                holder.tvMsgnum.setBackgroundResource(R.mipmap.xiaoxi_2);
            } else {
                holder.tvMsgnum.setBackgroundResource(R.mipmap.xiaoxi_1);
            }
            if (notice_count.length() >= 3) {
                holder.tvMsgnum.setText(notice_count + "+");
            } else {
                holder.tvMsgnum.setText(notice_count);
            }
            holder.tvMsgnum.setVisibility(View.VISIBLE);
        } else {
            holder.tvMsgnum.setVisibility(View.GONE);
        }
        Glide.with(context).load(data.getImage()).into(holder.ivImg);
    }

    class ViewHolder implements SingleViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.iv_img)
        ImageView ivImg;
        @Bind(R.id.tv_msgnum)
        TextView tvMsgnum;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}