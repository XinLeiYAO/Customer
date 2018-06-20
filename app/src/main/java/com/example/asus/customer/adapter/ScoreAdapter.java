package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/4/19.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHoider> {
     private List<Integer> mlist;
     private Context context;

    public ScoreAdapter(List<Integer> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public ViewHoider onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.scoe_item, null);
        ViewHoider viewHoider=new ViewHoider(inflate);
        return viewHoider;
    }

    @Override
    public void onBindViewHolder(ViewHoider holder, int position) {
         holder.imageView.setImageResource(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHoider extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHoider(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.scre_item);
        }
    }
}
