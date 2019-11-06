package com.example.asus.customer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.entity.GetBranchCompanyInfoBean;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHoler> {
    List<GetBranchCompanyInfoBean.BodyBean> list;
    public CompanyAdapter(List<GetBranchCompanyInfoBean.BodyBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_company, viewGroup, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, final int i) {
        GetBranchCompanyInfoBean.BodyBean bodyBean = list.get(i);
        viewHoler.tvCompanyName.setText(bodyBean.getRegionName()+"分公司");
        viewHoler.tvCompanyTel.setText(bodyBean.getCompanyTel());
        viewHoler.tvCompanyAdress.setText(bodyBean.getCompanyAdress());
        viewHoler.tvCompanyTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (itemButtonOnClickLister!=null){
            viewHoler.tvCompanyTel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemButtonOnClickLister.onItemClickLister(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView tvCompanyName;
        private TextView tvCompanyTel;
        private TextView tvCompanyAdress;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
             tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvCompanyTel = itemView.findViewById(R.id.tvCompanyTel);
            tvCompanyAdress = itemView.findViewById(R.id.tvCompanyAdress);
        }
    }

    public interface ItemButtonOnClickLister{
        void onItemClickLister(int i);
    }
    private  ItemButtonOnClickLister itemButtonOnClickLister;
    public void setOnItemButtonLister(ItemButtonOnClickLister itemButtonOnClickLister){
        this.itemButtonOnClickLister = itemButtonOnClickLister;
    }
}
