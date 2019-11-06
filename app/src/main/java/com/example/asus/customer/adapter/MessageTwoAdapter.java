package com.example.asus.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.SingleBaseAdapter;
import com.example.asus.customer.commons.base.SingleViewHolder;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.entity.InfoMessageBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjh on 2018/3/9.
 */

public class MessageTwoAdapter extends SingleBaseAdapter<InfoMessageBean.BodyBean, MessageTwoAdapter.ViewHolder> {


    public MessageTwoAdapter(Context context, List<InfoMessageBean.BodyBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_messageer;
    }

    @Override
    public ViewHolder initViewHolder() {
        return new ViewHolder();
    }

    @Override
    public void onBindView(int position, final InfoMessageBean.BodyBean data, ViewHolder holder) {
        String time = data.getCreate_date();
        if (!StringUtils.isEmpty(time)) {
            holder.lvTime.setText(time);
        }
        String state = data.getState() + "";
        switch (state) {
            case "0":
                holder.imageState.setVisibility(View.VISIBLE);
                holder.lvTime.setTextColor(Color.parseColor("#FF4143"));
                holder.tvTitle.setTextColor(context.getResources().getColor(R.color.text_black));
                holder.content.setTextColor(context.getResources().getColor(R.color.text_black));
                break;
            case "1":
                holder.imageState.setVisibility(View.GONE);
                holder.lvTime.setTextColor(Color.parseColor("#14C54F"));
                holder.tvTitle.setTextColor(context.getResources().getColor(R.color.kehuhome_text2));
                holder.content.setTextColor(context.getResources().getColor(R.color.kehuhome_text2));
                break;
        }
        String title = (String) data.getTitle();
        String txt = (String) data.getTxt();
//        state // 0 weidu 1 yidu
        holder.tvTitle.setText(TextUtils.isEmpty(title) ? "无" : title + "");
        holder.tvContent.setText(TextUtils.isEmpty(txt) ? "无" : txt + "");
        // holder.lvDate.setText(data.);

//        holder.rlDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //进入详情
//                if (data.getType().equals("1") || data.getType().equals("2")) {
//                    context.startActivity(new Intent(context, IdentityInfoNewActivity.class));
//                } else if (data.getType().equals("3")) {//入职资料
//                    context.startActivity(new Intent(context, ZThreeActivity.class));
//                } else {
//                    context.startActivity(new Intent(context, MessageDetailsActivity.class).putExtra("id", data.getId() + ""));
//                }
//            }
//        });
    }

    class ViewHolder implements SingleViewHolder {

        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.rl_details)
        RelativeLayout rlDetails;
        @Bind(R.id.lv_time)
        TextView lvTime;
        @Bind(R.id.lv_Date)
        TextView lvDate;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.image_state)
        ImageView imageState;

        @Override
        public void onInFlate(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
