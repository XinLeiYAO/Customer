package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.entity.AnliListBean;
import com.example.asus.customer.fragment.AnLiFragment;
import com.example.asus.customer.weight.GlidRoundUtils;

import java.util.List;

public class AnliAdapter extends RecyclerView.Adapter<AnliAdapter.ViewHoler> {
    private List<AnliListBean.BodyBean> list;
    private Context context;
    public AnliAdapter(Context context, List<AnliListBean.BodyBean> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_anli_list, viewGroup, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.tvTitle.setText(list.get(i).getTitle());
        viewHoler.tvLx.setText(list.get(i).getLx());
        Glide.with(context)
                .load(list.get(i).getThumb())
                .apply(RequestOptions.bitmapTransform(new GlidRoundUtils(5)))
                .into(viewHoler.ivThumb);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivThumb;
        TextView tvLx;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            ivThumb = itemView.findViewById(R.id.ivThumb);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvLx = itemView.findViewById(R.id.tvLx);
        }
    }
}
