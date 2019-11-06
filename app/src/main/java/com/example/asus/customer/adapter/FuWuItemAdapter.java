package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;

public class FuWuItemAdapter extends RecyclerView.Adapter<FuWuItemAdapter.ViewHolder>{
    private Context context ;
    public FuWuItemAdapter(Context context ){
        this.context =context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fuwu_item_item,viewGroup,false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imgae);
        }
    }
}
