package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.DingDanXiangQingBean;

import java.util.List;

//
// 2019/6/13.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class DingDanXiangQingAdapter extends RecyclerView.Adapter<DingDanXiangQingAdapter.ViewHolder>{
    private Context context;
    private List<DingDanXiangQingBean.BodyBean> list;
    private String tag;
    public DingDanXiangQingAdapter(Context context, List<DingDanXiangQingBean.BodyBean> list,String tag){
        this.context = context;
        this.list = list;
        this.tag = tag;
    }

    public KeHuHomeLiangFangAdapter.ListDataCallBack listDataCallBack;


    public void setListDataCallBack(KeHuHomeLiangFangAdapter.ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    public interface ListDataCallBack {
        void setOnListDataClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dingdanxiangqing_top_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        if(list.size() > 0 && list != null){
            if(tag.equals("666")){
                DingDanXiangQingBean.BodyBean bean = list.get(i);
                holder.name.setVisibility(View.GONE);
                holder.message.setVisibility(View.GONE);
                holder.number.setVisibility(View.VISIBLE);
                holder.number.setText("创建时间："+bean.getCreateTime());
                Glide.with(context).load(bean.getImgUrl()).into(holder.image);
            }else{
                DingDanXiangQingBean.BodyBean bean = list.get(i);
                String name = bean.getClassificationName();
                holder.name.setText(name);
                Glide.with(context).load(bean.getImgUrl()).into(holder.image);
            }
            holder.image.setOnClickListener(new View.OnClickListener() {
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

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView number;
        TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.xiangqing_img);
            name= (TextView) itemView.findViewById(R.id.name);
            number= (TextView)itemView.findViewById(R.id.shuliang);
            message= (TextView)itemView.findViewById(R.id.guige);
        }
    }
}
