package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.entity.AnliListBean;
import com.example.asus.customer.entity.ShejiShiListBean;
import com.example.asus.customer.weight.GlidRoundUtils;

import java.util.List;

public class ShejiShiAdapter extends RecyclerView.Adapter<ShejiShiAdapter.ViewHoler> {
    private List<ShejiShiListBean.BodyBean> list;
    private Context context;
    public ShejiShiAdapter(Context context, List<ShejiShiListBean.BodyBean> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shejishi_list, viewGroup, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.tvName.setText(list.get(i).getName());
        viewHoler.tvYsGrade.setText(list.get(i).getYsGrade());
        viewHoler.tvWorkYear.setText(list.get(i).getWorkYear()+"年以上|"+list.get(i).getCompletionProjectCount()+"作品");
        viewHoler.tvBigType.setText(list.get(i).getBigType());
        viewHoler.tvStyle.setText(list.get(i).getStyle());
        Glide.with(context)
                .load(list.get(i).getImage())
                .apply(RequestOptions.bitmapTransform(new GlidRoundUtils(5)))
                .into(viewHoler.ivThumb);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivThumb;
        TextView tvYsGrade;
        TextView tvWorkYear;
        TextView tvBigType;
        TextView tvStyle;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            ivThumb = itemView.findViewById(R.id.ivThumb);
            tvName = itemView.findViewById(R.id.tvName);
            tvYsGrade = itemView.findViewById(R.id.tvYsGrade);
            tvWorkYear = itemView.findViewById(R.id.tvWorkYear);
            tvBigType = itemView.findViewById(R.id.tvBigType);
            tvStyle = itemView.findViewById(R.id.tvStyle);
        }
    }
}
