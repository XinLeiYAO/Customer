package com.example.asus.customer.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;

import java.util.ArrayList;

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
public class HorAadapter extends RecyclerView.Adapter<HorAadapter.ViewHolder>{
    private Context context;
    public ListDataCallBack listDataCallBack;
    private ArrayList<String> title;
    ArrayList<Integer> icon;

    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }
    public interface ListDataCallBack {
        void setOnListDataClick(int position);
    }

    public HorAadapter(Context context, ArrayList<String> title, ArrayList<Integer> icon){
        this.context = context;
        this.title = title;
        this.icon = icon;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hor_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataCallBack.setOnListDataClick(i);
            }
        });
        holder.text.setText(title.get(i));
        holder.img.setImageResource(icon.get(i));
    }


    @Override
    public int getItemCount() {
        return title.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ImageView img;
        private LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            text = (TextView) itemView.findViewById(R.id.both_text);
            img = (ImageView) itemView.findViewById(R.id.both_img);
        }
    }
}
