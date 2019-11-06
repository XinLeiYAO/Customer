package com.example.asus.customer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.entity.GeProInfoOrderItemsByAPPClientBean;
import com.example.asus.customer.entity.QuanBuBean;

import java.util.List;

//
// 2019/6/12.
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
public class MyCailiaoDingDanAdapter extends BaseAdapter {
    private Context context;
    List<GeProInfoOrderItemsByAPPClientBean.BodyBean> list;

    public MyCailiaoDingDanAdapter(Context context,List<GeProInfoOrderItemsByAPPClientBean.BodyBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mydingdan_item, null);
            AutoUtils.auto(convertView);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        GeProInfoOrderItemsByAPPClientBean.BodyBean bean = list.get(position);
        vh.title.setText("材料 > " + bean.getOrderType());
        String orderTime = bean.getItemsFlow().get(0).getTime();
        vh.time.setText("下单时间：" + orderTime);
        vh.type.setText("送达日期：" + bean.getItemsFlow().get(0).getTime());
        String statusName = bean.getOrderState();
        switch (statusName) {
            case "待下单":
                vh.img.setImageResource(R.mipmap.daixiadan);
                break;
            case "待接单":
            case "待分单":
            case "绘图中":
            case "待审批":
                vh.img.setImageResource(R.mipmap.jinxingzhong);
                break;
            case "客待验":
                vh.img.setImageResource(R.mipmap.daiyanshou);
                break;
            case "待评价":
                vh.img.setImageResource(R.mipmap.daipingjia);
                break;
        }
        return convertView;
    }


    class ViewHolder {

        private final TextView title;
        private final TextView time;
        private final TextView type;
        private final TextView status;
        private final ImageView img;

        public ViewHolder(View v) {
            title = (TextView) v.findViewById(R.id.dingdan_title);
            time = (TextView) v.findViewById(R.id.dingdan_time);
            type = (TextView) v.findViewById(R.id.dingdan_type);
            status = (TextView) v.findViewById(R.id.dingdan_status);
            img = (ImageView) v.findViewById(R.id.dingdan_img);
        }
    }
}
