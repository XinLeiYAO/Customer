package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.FaxianMessageBean;

import java.util.List;

public class PopuwindowAdapter extends RecyclerView.Adapter<PopuwindowAdapter.ViewHolder> {
    private Context context;
    private List<FaxianMessageBean.BodyBean> list;

    public PopuwindowAdapter(Context context, List<FaxianMessageBean.BodyBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.popuwindow_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        if (list.size() > 0 && list != null) {
            FaxianMessageBean.BodyBean bean = list.get(i);
            if (!TextUtils.isEmpty(bean.getUserName())) {
                holder.name.setText(bean.getUserName());
            }
            holder.message.setText(bean.getContent());
            if (!TextUtils.isEmpty(bean.getImage())) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.circleCrop();
                requestOptions.placeholder(R.mipmap.ic_launcher);
                requestOptions.error(R.mipmap.ic_launcher);
                Glide.with(context).load(bean.getImage()).apply(requestOptions).into(holder.head);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView head;
        TextView name;
        TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head = (ImageView) itemView.findViewById(R.id.head_image);
            name = (TextView) itemView.findViewById(R.id.text_name);
            message = (TextView) itemView.findViewById(R.id.text_message);
        }
    }
}
