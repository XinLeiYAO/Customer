package com.example.asus.customer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.entity.WeixiuTypeBean;
import com.example.asus.customer.entity.WeixiuTypeListBean;

import java.util.List;

public class JvBuWeiXiuAdapter extends RecyclerView.Adapter<JvBuWeiXiuAdapter.ViewHoler> {
    private List<WeixiuTypeListBean.BodyBean> datas;
    public JvBuWeiXiuAdapter(List<WeixiuTypeListBean.BodyBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weixiu, viewGroup, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHoler viewHoler, final int i) {
        viewHoler.cb.setText(datas.get(i).getTypeValue());
        if (datas.get(i).getIs()==1){
            viewHoler.cb.setChecked(true);
        }else{
            viewHoler.cb.setChecked(false);
        }
        if (mItemButtonClickListener != null){
            viewHoler.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 这里利用回调来给RecyclerView设置点击事件

                    mItemButtonClickListener.onItemButtonClick(viewHoler.cb,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        CheckBox cb;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb);
        }
    }

    private ItemButtonClickListener mItemButtonClickListener ;
    public interface ItemButtonClickListener{
        public void onItemButtonClick(CheckBox cb,int position) ;
    }
    public void setOnItemButtonClickListener(ItemButtonClickListener itemButtonClickListener){
        this.mItemButtonClickListener = itemButtonClickListener ;

    }
}
