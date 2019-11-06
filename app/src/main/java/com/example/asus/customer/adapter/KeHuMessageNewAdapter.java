package com.example.asus.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;

import java.util.ArrayList;

public class KeHuMessageNewAdapter extends RecyclerView.Adapter<KeHuMessageNewAdapter.ViewHolder> {

    public ListDataCallBack listDataCallBack;
    private int selectPosition = 0;


    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    private ArrayList<String> list;
    private Context context;

    public KeHuMessageNewAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.kehu_message_title, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (list.size() > 0 && list != null) {
            viewHolder.title.setText(list.get(i));
            if (i == selectPosition) {
                viewHolder.title.setTextColor(Color.parseColor("#00a0ea"));
            } else {
                viewHolder.title.setTextColor(Color.parseColor("#000000"));
            }
        }
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = i;
                listDataCallBack.setOnListDataClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ListDataCallBack {

        //图片的点击事件
        void setOnListDataClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textTitle);
        }
    }
}

