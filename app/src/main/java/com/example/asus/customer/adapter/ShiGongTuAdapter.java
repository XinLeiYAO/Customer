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

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.KeHuHomeXiaoGuoBean;

import java.util.List;

public class ShiGongTuAdapter extends RecyclerView.Adapter<ShiGongTuAdapter.ViewHolder>{
    private Context context;
    private List<KeHuHomeXiaoGuoBean.BodyBean.ItemsBean> list;
    public KeHuHomeLiangFangAdapter.ListDataCallBack listDataCallBack;

    public ShiGongTuAdapter(Context context, List<KeHuHomeXiaoGuoBean.BodyBean.ItemsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setListDataCallBack(KeHuHomeLiangFangAdapter.ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.kehu_home_liangfang_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface ListDataCallBack {
        void setOnListDataClick(int position);
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
