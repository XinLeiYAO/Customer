package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.OrderStatusMessageBean;

import java.util.List;

public class OrderJinChengAdapter extends RecyclerView.Adapter<OrderJinChengAdapter.ViewHolder>{
    private Context context;
    private List<OrderStatusMessageBean> orderList;
    public OrderJinChengAdapter(Context context,List<OrderStatusMessageBean>orderList){
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_dingdan_item,viewGroup,false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        OrderStatusMessageBean bean = orderList.get(i);
        String statusName = bean.getStatusName();
        holder.name.setText(statusName);
        holder.content.setText(bean.getYanshouren());
//        switch (statusName){
//            case "下单":
//                holder.content.setText("设计师选择个人执行图纸"+bean.getOrderNum()+"张预计完成");
//                break;
//            case "执行":
//                holder.content.setText("订单"+bean.getOrder()+"设计师主案设计师选择自行绘制本图纸");
//                break;
//            case "提交":
//                holder.content.setText("绘图员主案设计师于"+bean.getStatusTime()+"提交图纸之质检中心\n订单"+bean.getOrder()+"预计"+bean.getYujiTime()+"内完成质检");
//                break;
//            case "质检":
//                holder.content.setText("订单"+bean.getOrder()+"已于"+bean.getStatusTime()+"内完成质检，质检结果合格");
//                break;
//            case "验收":
//                holder.content.setText("您的订单已于"+bean.getStatusTime()+"完成验收，验收人:"+bean.getYanshouren()+"您的订单已经完成;工作日假日只为客户您满意，上有老下有小，赏个好评好不好？\n请在评价订单中帮忙点亮5颗星星哦！感谢您的支持");
//                break;
//        }
        holder.time.setText(bean.getStatusTime());
//        if(!TextUtils.isEmpty(bean.getStatusTime())){
//            if(bean.getStatusTime().length() > 11){
//                String statusTime = bean.getStatusTime();
////                String substring = statusTime.substring(5, 10);
//                holder.time.setText(substring+"");
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView time,name,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.textTime);
            name = (TextView) itemView.findViewById(R.id.textName);
            content = (TextView) itemView.findViewById(R.id.textCount);
        }
    }
}
