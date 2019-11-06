package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.QuerysiteListBean;

import java.util.List;

public class FuwuAddressAdapter extends RecyclerView.Adapter<FuwuAddressAdapter.ViewHolder>{
    private Context context;
    private List<QuerysiteListBean.BodyBean> bodyList;
    public FuwuAddressAdapter(Context context, List<QuerysiteListBean.BodyBean> bodyList){
        this.bodyList = bodyList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fuwu_address_item,viewGroup,false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FuwuAddressAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tvSitetext.setText(bodyList.get(i).getSiteText());
        viewHolder.tvUserName.setText(bodyList.get(i).getUserName());
        viewHolder.tvUserPhone.setText(bodyList.get(i).getUserPhone());
        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(i);
                }
            });
        }

        if (mItemButtonClickListener != null){
            viewHolder.tvUpdatesite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemButtonClickListener.onItemButtonClick(i);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return bodyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUserName;
        private TextView tvUserPhone;
        private TextView tvSitetext;
        private TextView tvUpdatesite;
        private LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserPhone = itemView.findViewById(R.id.tvUserPhone);
            tvSitetext = itemView.findViewById(R.id.tvSitetext);
            tvUpdatesite = itemView.findViewById(R.id.tvUpdatesite);
            llItem = itemView.findViewById(R.id.llItem);

        }
    }

    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        public void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

    private ItemButtonClickListener mItemButtonClickListener ;
    public interface ItemButtonClickListener{
        public void onItemButtonClick(int position) ;
    }
    public void setOnItemButtonClickListener(ItemButtonClickListener itemButtonClickListener){
        this.mItemButtonClickListener = itemButtonClickListener ;

    }
}
