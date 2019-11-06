package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.ZhangDanJiLuBean;

import java.util.List;

//
// 2019/6/11.
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
public class ZhangdanJiLuAdapter extends RecyclerView.Adapter<ZhangdanJiLuAdapter.ViewHolder> {
    private Context context;
    private List<ZhangDanJiLuBean.BodyBean.ListBean> list;

    public ZhangdanJiLuAdapter(Context context, List<ZhangDanJiLuBean.BodyBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhangdanjilu_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        int period = list.get(i).getPeriod();
        if (period == 1) {
            holder.name.setText("施工-预款");
        }else if(period == 2){
            holder.name.setText("施工-二期款");
        }else if(period == 3){
            holder.name.setText("施工-三期款");
        }else if(period == 4){
            holder.name.setText("施工-尾款");
        }
        holder.time.setText(list.get(i).getUpdateTime());
        holder.money.setText(list.get(i).getReceived());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView time;
        private TextView money;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            money = (TextView) itemView.findViewById(R.id.money);
        }
    }
}
