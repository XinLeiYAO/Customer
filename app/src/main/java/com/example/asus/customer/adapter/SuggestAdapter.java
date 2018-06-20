package com.example.asus.customer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AAA on 2017/9/14.
 */

public class SuggestAdapter extends BaseAdapter {

    private Context context;
    private List<LocalMedia> dataList;

    public SuggestAdapter(Context context, List<LocalMedia> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LocalMedia info = dataList.get(position);

        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item_suggest, null);
            AutoUtils.auto(convertView);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //保证最后一个item为相册入口
        if (parent.getChildCount() == position) {
            if (position == (getCount() - 1)) {
                holder.ivRoutingSub.setImageResource(R.mipmap.suggest_up_icon);
                holder.ivRoutingSub.setScaleType(ImageView.ScaleType.CENTER);
            } else {
                Glide.with(context).load(info.getCompressPath()).into(holder.ivRoutingSub);
                holder.ivRoutingSub.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_list_item_suggest)
        ImageView ivRoutingSub;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
