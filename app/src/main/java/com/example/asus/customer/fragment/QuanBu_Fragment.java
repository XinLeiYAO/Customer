package com.example.asus.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.DingDanIngActivity;
import com.example.asus.customer.activity.DingDanXiangQingActivity;
import com.example.asus.customer.activity.FaBiaoMessageActivity;
import com.example.asus.customer.activity.MyDaiQueRenActivity;
import com.example.asus.customer.activity.NewDingDanXiangQingActivity;
import com.example.asus.customer.adapter.MyCailiaoDingDanAdapter;
import com.example.asus.customer.adapter.MyDingDanAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.GeProInfoOrderItemsByAPPClientBean;
import com.example.asus.customer.entity.QuanBuBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
public class QuanBu_Fragment extends BaseFragment {

    @Bind(R.id.listView)
    ListView mListView;
    @Bind(R.id.no_img)
    ImageView no_img;
    List<QuanBuBean.BodyBean> list = new ArrayList<>();
    List<GeProInfoOrderItemsByAPPClientBean.BodyBean> list1 = new ArrayList<>();
    private MyDingDanAdapter adapter;
    private MyCailiaoDingDanAdapter adapter1;
    private int type;
    private int status;


    @Override
    protected int getFragmentLayout() {
        return R.layout.quanbu_ftagment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initMessage();
    }


    @Override
    public void onResume() {
        super.onResume();
        initMessage();
    }

    @Override
    protected void FragmentInitData() {


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(bean != null && bean.getBody() != null){
//                    QuanBuBean.BodyBean body = bean.getBody().get(position);
//                    int stage = body.getStage();
//                    if(stage == 0){
//                        //进行中
//                        startActivity(new Intent(getActivity(), DingDanIngActivity.class)
//                                .putExtra("orderNo","1")
//                                .putExtra("danhao",body.getOrderNo())
//                                .putExtra("stage","0"));
//                    }else if(stage == 1){
//                        startActivity(new Intent(getActivity(), MyDaiQueRenActivity.class)
//                                .putExtra("orderNo","1")
//                                .putExtra("danhao",body.getOrderNo())
//                                .putExtra("stage","2")
//                                .putExtra("orderno",body.getOrderNo())
//                                .putExtra("type",body.getType_code()));
//                    }else if(stage == 2){
//                        startActivity(new Intent(getActivity(), FaBiaoMessageActivity.class)
//                                .putExtra("orderno", body.getOrderNo())
//                                .putExtra("type", body.getType())
//                                .putExtra("typeCode", body.getType_code())
//                        );
//                    }else{
//                        startActivity(new Intent(getActivity(), DingDanXiangQingActivity.class)
//                                .putExtra("orderNo","1")
//                                .putExtra("stage","4")
//                                .putExtra("danhao",body.getOrderNo())
//                                .putExtra("type",body.getType())
//                        );
//                    }
//                }
//                startActivity(new Intent(getActivity(), DingDanXiangQingActivity.class)
                if (type!=1)
                startActivity(new Intent(getActivity(), NewDingDanXiangQingActivity.class)
                        .putExtra("orderNo", list.get(position).getOrderNo())
                        .putExtra("statusName", list.get(position).getStatusName())
                        .putExtra("type", String.valueOf(list.get(position).getDrwingType()))
                        .putExtra("imageName", list.get(position).getImgName())
                        .putExtra("groupId", list.get(position).getGroupId())
                        .putExtra("rwdId", list.get(position).getRwdId())
                );
            }
        });

        //initMessage();
    }

    private void initMessage() {

        Bundle arguments = getArguments();
        final int type = (int) arguments.get("type");
        int status = (int) arguments.get("status");

        if (type==1){
            adapter1 = new MyCailiaoDingDanAdapter(getActivity(), list1);
            mListView.setAdapter(adapter1);
        }else{
            adapter = new MyDingDanAdapter(getActivity(), list);
            mListView.setAdapter(adapter);
        }


        String url = ApiEngine.ZHA_HOST+"DesignInstitute/contract/getPlanCollection";
        if (type == 1) {
            url = "https://gapi.rxjy.com//MAT/api/ThirdParty/GeProInfoOrderItemsByAPPClient";
        }

        showLoading();
        list.clear();
        list1.clear();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdId", value);
//        map.put("rwdId", "18-234088");
        if (type == 1){
            map.put("state", status);
        }else{
            map.put("status", status+1);
        }
        OkhttpUtils.doGet(url, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                        ToastUtil.getInstance().toastCentent(e.toString());
                        no_img.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        QuanBuBean  bean = JSONUtils.toObject(string, QuanBuBean.class);
                        GeProInfoOrderItemsByAPPClientBean bean1 = JSONUtils.toObject(string, GeProInfoOrderItemsByAPPClientBean.class);
                        List<QuanBuBean.BodyBean> body = bean.getBody();
                        List<GeProInfoOrderItemsByAPPClientBean.BodyBean> body1 = bean1.getBody();
                        if (type==1){
                            if (body1.size() > 0) {
                                list1.addAll(body1);
                                adapter1.notifyDataSetChanged();
                                no_img.setVisibility(View.GONE);
                            } else {
                                no_img.setVisibility(View.VISIBLE);
                            }
                        }else{
                            if (body.size() > 0) {
                                list.addAll(body);
                                adapter.notifyDataSetChanged();
                                no_img.setVisibility(View.GONE);
                            } else {
                                no_img.setVisibility(View.VISIBLE);
                            }
                        }

                    }
                });
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

}
