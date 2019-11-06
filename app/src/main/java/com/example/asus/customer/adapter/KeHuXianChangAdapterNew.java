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
import com.example.asus.customer.entity.KeHuHomeLiangFangBean;
import com.example.asus.customer.entity.KeHuLiangFangBeanNew;

import java.util.List;

public class KeHuXianChangAdapterNew extends RecyclerView.Adapter<KeHuXianChangAdapterNew.ViewHolder> {
    private Context context;
    private List<KeHuLiangFangBeanNew.BodyBean> data;
    public ListDataCallBack listDataCallBack;



    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    public KeHuXianChangAdapterNew(Context context, List<KeHuLiangFangBeanNew.BodyBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.kehu_home_liangfang_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String catrgoryName = data.get(position).getCatrgoryName();
        holder.kehuHomeLiangfangName.setText(catrgoryName);
        if(data.get(position).getList().size() > 0  && data.get(position).getList() != null){
            KeHuLiangFangBeanNew.BodyBean.ListBean bodyBean = data.get(position).getList().get(0);
            String filePath = bodyBean.getImageUrl();
            RequestOptions options = new RequestOptions().transform(new GlideRoundTransform(context));
            options.placeholder(R.mipmap.zhanweitu);
            Glide.with(context).load(filePath).apply(options).into(holder.kehuHomeLiangfangImage);
            holder.kehuHomeLiangfangFrame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listDataCallBack.setOnListDataClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
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
