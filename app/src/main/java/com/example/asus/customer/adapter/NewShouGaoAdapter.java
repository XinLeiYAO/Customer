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
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.GlideRoundTransform;
import com.example.asus.customer.entity.NewJieGouBean;
import com.example.asus.customer.entity.NewShouGaoBean;

import java.util.List;

public class NewShouGaoAdapter extends RecyclerView.Adapter<NewShouGaoAdapter.ViewHolder> {
    private Context context;
    public ListDataCallBack listDataCallBack;
    private List<NewShouGaoBean.BodyBean> list;


    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    public NewShouGaoAdapter(Context context, List<NewShouGaoBean.BodyBean> list) {
        this.context = context;
        this.list = list;
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
            String imageUrl = list.get(i).get_$123().get(i).getFilePath();
//             = list.get(i).getFilePath();
            holder.kehuHomeLiangfangName.setText("量房手绘");
            RequestOptions options = new RequestOptions().transform(new GlideRoundTransform(context));
            options.placeholder(R.mipmap.zhanweitu);
            Glide.with(context).load(imageUrl).apply(options).into(holder.kehuHomeLiangfangImage);
            holder.kehuHomeLiangfangFrame.setOnClickListener(new View.OnClickListener() {
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
