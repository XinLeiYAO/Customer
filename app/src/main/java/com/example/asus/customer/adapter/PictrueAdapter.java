package com.example.asus.customer.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/4/19.
 */

public class PictrueAdapter extends SingleBaseAdapter<String, PictrueAdapter.ViewHolder> {


    public PictrueAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.picture_list_item;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, String data, ViewHolder holder) {

        Glide.with(context).load(data).into(holder.pictureListItem);
    }

    public class ViewHolder implements SingleViewHolder {

        @Bind(R.id.picture_list_item)
        ImageView pictureListItem;
        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
