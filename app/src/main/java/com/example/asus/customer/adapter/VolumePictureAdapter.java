package com.example.asus.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.PhotoImageActivity;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.entity.PhotoDetailsBean;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by asus on 2018/4/28.
 */

public class VolumePictureAdapter extends RecyclerView.Adapter<VolumePictureAdapter.ViewHoider> {

    private ArrayList<PhotoDetailsBean> mlist;
    private Context context;
    private String title;

    public VolumePictureAdapter(ArrayList<PhotoDetailsBean> mlist, Context context, String title) {
        this.mlist = mlist;
        this.context = context;
        this.title = title;
    }

    @Override
    public ViewHoider onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.volumepicture_item, null);
        ViewHoider viewHoider = new ViewHoider(inflate);
        return viewHoider;
    }

    @Override
    public void onBindViewHolder(ViewHoider holder, final int position) {
        Glide.with(context).load(mlist.get(position).getImageUrl()).into(holder.volumPictureImage);
        holder.volumPictureTitle.setText(mlist.get(position).getCatalogName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PhotoImageActivity.class);
                intent.putExtra(Constants.INDEXES,position);
                intent.putExtra(Constants.TITLE,mlist.get(position).getCatalogName());
                ArrayList<String> list = new ArrayList<>();
                list.add( mlist.get(position).getImageUrl());
                intent.putStringArrayListExtra(Constants.JUMPLIST,list);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHoider extends RecyclerView.ViewHolder {

        ImageView volumPictureImage;
        TextView volumPictureTitle;
        public ViewHoider(View itemView) {
            super(itemView);
            volumPictureImage=itemView.findViewById(R.id.volumPicture_image);
            volumPictureTitle=itemView.findViewById(R.id.volumPicture_title);
        }
    }
}
