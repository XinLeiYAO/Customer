package com.example.asus.customer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;


import java.util.List;


public class ImageAddAdapterJie extends BaseAdapter {

    private List<String> lists;
    Context context;

    public ImageAddAdapterJie(List lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_imageadd, null);
        ImageView iv = convertView.findViewById(R.id.iv_imgadd);
        String picUrl = lists.get(position); //图片路径

        //url等于addImage的时候加载  加号图片
        if(!picUrl.equals("addImage")){
            //代表+号之前的需要正常显示图片
            Glide.with(context).load(picUrl).into(iv);
        }else{
            iv.setImageResource(R.mipmap.xiangji);//最后一个显示加号图片

        }
        return convertView;
    }

}
