package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.NewJieGouBean;

import java.util.List;

public class NewYiXiangAdapter extends RecyclerView.Adapter<NewYiXiangAdapter.ViewHolder>  {
    private Context context;
    public ListDataCallBack listDataCallBack;
    private List<String>list;
    private String type;


    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    public NewYiXiangAdapter(Context context, List<String> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.kehu_home_liangfang_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i)).into(viewHolder.kehuHomeLiangfangImage);
        viewHolder.kehuHomeLiangfangName.setText(type);
        viewHolder.kehuHomeLiangfangImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataCallBack.setOnListDataClick(i);
            }
        });
    }

    public interface ListDataCallBack {
        void setOnListDataClick(int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout kehuHomeLiangfangFrame;
        private ImageView kehuHomeLiangfangImage;
        private RelativeLayout kehuHomeLiangfangLin;
        private TextView kehuHomeLiangfangName;

        public ViewHolder(View itemView) {
            super(itemView);
            kehuHomeLiangfangFrame = (FrameLayout) itemView.findViewById(R.id.kehu_home_liangfang_frame);
            kehuHomeLiangfangImage = (ImageView) itemView.findViewById(R.id.kehu_home_liangfang_image);
            kehuHomeLiangfangLin = (RelativeLayout) itemView.findViewById(R.id.kehu_home_liangfang_lin);
            kehuHomeLiangfangName = (TextView) itemView.findViewById(R.id.kehu_home_liangfang_name);
        }
    }
}
