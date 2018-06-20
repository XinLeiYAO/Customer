package com.example.asus.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.PhotoImageActivity;
import com.example.asus.customer.commons.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/4/20.
 */

public class RecyViewAdapter extends RecyclerView.Adapter<RecyViewAdapter.ViewHoider> implements View.OnClickListener {
    private ArrayList<String> mlist;
    private Context context;
    private String title;

    public RecyViewAdapter(ArrayList<String> mlist, Context context, String title) {
        this.mlist = mlist;
        this.context = context;
        this.title = title;
    }

    @Override
    public ViewHoider onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.picture_list_item, null);
        inflate.setOnClickListener(this);

        ViewHoider viewHoider = new ViewHoider(inflate);
        return viewHoider;
    }

    @Override
    public void onBindViewHolder(ViewHoider holder, final int position) {
        Glide.with(context).load(mlist.get(position)).into(holder.imageView);
        holder.itemView.setTag(position);
        if(title.equals("出稿")||title.equals("彩平")){

        }else {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotoImageActivity.class);
                intent.putExtra(Constants.INDEXES, position);
                intent.putExtra(Constants.TITLE, title);
                intent.putStringArrayListExtra(Constants.JUMPLIST, mlist);
                context.startActivity(intent);
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class ViewHoider extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHoider(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.picture_list_item);
        }
    }

    public interface onClick {
        void setItem(View v, int possiton);

    }

    private onClick item = null;

    @Override
    public void onClick(View view) {
        if (item != null) {
            item.setItem(view, (int) view.getTag());
        }
    }

    public void setListener(onClick item) {
        this.item = item;
    }
}
