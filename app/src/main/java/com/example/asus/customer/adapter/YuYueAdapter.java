package com.example.asus.customer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.YuYueListBean;

import java.util.List;

public class YuYueAdapter extends RecyclerView.Adapter<YuYueAdapter.ViewHolder>{
    private Context context;
    private List<YuYueListBean.BodyBean> list;
    public ListDataCallBack listDataCallBack;

    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    public YuYueAdapter(Context context,List<YuYueListBean.BodyBean>list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.yuyue_item,viewGroup,false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int i) {
        YuYueListBean.BodyBean bean = list.get(i);
        Log.e("YUYUE",i+"");
        holder.name.setText(bean.getCustomerName()+" "+bean.getPhone()+" >");
        holder.address.setText(bean.getAddress());
        holder.time.setText(bean.getVisitTime());
        holder.xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataCallBack.xiangqingOnClick(i);
            }
        });
        holder.wangong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataCallBack.wangongOnClick(i);
            }
        });
        holder.genggai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataCallBack.genggaiOnClick(i);
            }
        });
        String stage = bean.getStage();
        if (!TextUtils.isEmpty(stage)){
            if(stage.equals("1")){
                holder.status_img.setImageResource(R.mipmap.daichuli_img);
                holder.genggai.setVisibility(View.VISIBLE);
                holder.wangong.setText("确认完工");
            }else if(stage.equals("2")){
                holder.status_img.setImageResource(R.mipmap.jinxingzhong);
                holder.genggai.setVisibility(View.VISIBLE);
                holder.wangong.setText("确认完工");
            }else if(stage.equals("3")){
                holder.status_img.setImageResource(R.mipmap.yiwangong_img);
                holder.genggai.setVisibility(View.GONE);
                holder.wangong.setText("再次预约");
            }else{
                holder.status_img.setImageResource(R.mipmap.daichuli_img);
                holder.genggai.setVisibility(View.VISIBLE);
                holder.wangong.setText("确认完工");
            }
        }else{
            holder.status_img.setImageResource(R.mipmap.daichuli_img);
            holder.genggai.setVisibility(View.VISIBLE);
            holder.wangong.setText("确认完工");
        }
        String repairType = bean.getType();
        if (repairType.equals("维修")){
            holder.tvTypeName.setText("维修类型");
            holder.jiazhuangType.setText(bean.getRepairType());
        }else{
            holder.tvTypeName.setText("家装类型");
            holder.jiazhuangType.setText(bean.getLeiXingYiName()+" "+bean.getLeiXingErName()+" "+bean.getArea());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ListDataCallBack {
        void xiangqingOnClick(int position);
        void wangongOnClick(int position);
        void genggaiOnClick(int position);

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,jiazhuangType,address,time,wangong,genggai,xiangqing,tvTypeName;
        ImageView status_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.namePhone);
            jiazhuangType = (TextView) itemView.findViewById(R.id.jiazhuangText);
            address = (TextView) itemView.findViewById(R.id.addressText);
            time = (TextView) itemView.findViewById(R.id.timeText);
            genggai = (TextView) itemView.findViewById(R.id.genggai_text);
            xiangqing = (TextView) itemView.findViewById(R.id.xiangqing_text);
            wangong = (TextView) itemView.findViewById(R.id.wangong_text);
            status_img = (ImageView)itemView.findViewById(R.id.status_img);
            tvTypeName = (TextView)itemView.findViewById(R.id.tvTypeName);

        }
    }
}
