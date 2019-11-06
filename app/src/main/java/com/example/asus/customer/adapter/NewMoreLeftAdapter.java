package com.example.asus.customer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.MoreLeftBean;

import java.util.List;

//
// 2019/6/14.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class NewMoreLeftAdapter extends RecyclerView.Adapter<NewMoreLeftAdapter.ViewHolder>{
    private Context context;
    private List<MoreLeftBean.BodyBean> left_list;
    public ListDataCallBack listDataCallBack;
    public int selected = 0;

    public NewMoreLeftAdapter(Context context, List<MoreLeftBean.BodyBean> left_list){
        this.context = context;
        this.left_list = left_list;
    }

    public void setListDataCallBack(ListDataCallBack listDataCallBack) {
        this.listDataCallBack = listDataCallBack;
    }

    public interface ListDataCallBack {
        void setOnListDataClick(int group,int child);
        void setOnFindWorker(int group,View v);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_left_item, viewGroup, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        if(left_list.size() > 0 && left_list != null){
            MoreLeftBean.BodyBean bean = left_list.get(i);
            holder.title.setText(bean.getOneName());
        }
        if(selected == i){
//            holder.biaoqian.setVisibility(View.VISIBLE);
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.title.setTextColor(context.getResources().getColor(R.color.black));
        }else{
//            holder.biaoqian.setVisibility(View.GONE);
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorBG));
            holder.title.setTextColor(context.getResources().getColor(R.color.colorGray));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataCallBack.setOnFindWorker(i,holder.biaoqian);
                selected = i;
            }
        });
    }




    @Override
    public int getItemCount() {
        return left_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView biaoqian;
        TextView title;
        RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = (RelativeLayout) itemView.findViewById(R.id.layout);
            biaoqian = (TextView) itemView.findViewById(R.id.biaoqian);
            title = (TextView) itemView.findViewById(R.id.text_name);
        }
    }
}
