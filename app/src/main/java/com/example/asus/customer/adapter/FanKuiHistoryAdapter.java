package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.NewHistoryBean;

import java.util.List;

public class FanKuiHistoryAdapter extends RecyclerView.Adapter<FanKuiHistoryAdapter.ViewHolder> {
    private Context context;
    private List<NewHistoryBean.BodyBean> list;

    public FanKuiHistoryAdapter(Context context, List<NewHistoryBean.BodyBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fankui_history_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        NewHistoryBean.BodyBean bean = list.get(i);
        if (!TextUtils.isEmpty(bean.getCreateTime())) {
            holder.data.setText(bean.getCreateTime());
        } else {
            holder.data.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(bean.getReason())) {
            holder.message.setText(bean.getReason());
        } else {
            holder.message.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(bean.getReasonType())) {
            holder.name.setText(bean.getReasonType());
        }else{
            holder.name.setVisibility(View.INVISIBLE);
        }
        String opinion = bean.getOpinion();
        if (opinion.equals("yes")) {
            holder.yes.setVisibility(View.VISIBLE);
            holder.no.setVisibility(View.GONE);
        } else {
            holder.no.setVisibility(View.VISIBLE);
            holder.yes.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView data;
        private TextView name;
        private TextView message;
        private TextView yes;
        private TextView no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.text_data);
            name = itemView.findViewById(R.id.text_name);
            message = itemView.findViewById(R.id.text_message);
            yes = itemView.findViewById(R.id.text_yes);
            no = itemView.findViewById(R.id.text_no);
        }
    }
}
