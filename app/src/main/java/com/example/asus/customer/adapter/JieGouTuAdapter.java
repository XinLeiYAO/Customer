package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.GlideRoundTransform;
import com.example.asus.customer.entity.DingDanXiangQingBean;
import com.example.asus.customer.entity.KeHuHomeXiaoGuoBean;
import com.example.asus.customer.entity.QuanBuBean;
import com.example.asus.customer.entity.ShiGongXiaoGuoBean;

import java.util.List;

public class JieGouTuAdapter extends RecyclerView.Adapter<JieGouTuAdapter.ViewHolder>{
    private Context context;
    private List<QuanBuBean.BodyBean> list;
    public KeHuHomeLiangFangAdapter.ListDataCallBack listDataCallBack;

    public JieGouTuAdapter(Context context, List<QuanBuBean.BodyBean> list) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        if(list.size() > 0 && list != null){
            QuanBuBean.BodyBean bodyBean = list.get(i);
            String preview = bodyBean.getImgUrl();
            RequestOptions options = new RequestOptions().transform(new GlideRoundTransform(context));
            options.placeholder(R.mipmap.zhanweitu);
            Glide.with(context).load(preview).apply(options).into(holder.kehuHomeLiangfangImage);
            holder.kehuHomeLiangfangName.setText(bodyBean.getImgName());
            holder.kehuHomeLiangfangImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listDataCallBack.setOnListDataClick(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
