package com.example.asus.customer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHoler> {
    private List<ServiceListBean> list;
    public ServiceAdapter(List<ServiceListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service, viewGroup, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.tvTitle.setText(list.get(i).getTitle());
        viewHoler.tvContent1.setText(list.get(i).getContent1());
        viewHoler.tvContent2.setText(list.get(i).getContent2());
        viewHoler.image.setBackgroundResource(list.get(i).getImgUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent1;
        TextView tvContent2;
        RelativeLayout image;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
             tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent1 = itemView.findViewById(R.id.tvContent1);
            tvContent2 = itemView.findViewById(R.id.tvContent2);
            image = itemView.findViewById(R.id.rlimage);
        }
    }
}
