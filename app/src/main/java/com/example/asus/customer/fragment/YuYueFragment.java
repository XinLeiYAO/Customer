package com.example.asus.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.JuBuWeiXiuActivity;
import com.example.asus.customer.activity.YuYueXiangQingActivity;
import com.example.asus.customer.activity.ZhuangxiuYuyueActivity;
import com.example.asus.customer.adapter.YuYueAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.CityNewBean;
import com.example.asus.customer.entity.QuerysiteListBean;
import com.example.asus.customer.entity.UpdateStageByphoneBean;
import com.example.asus.customer.entity.YuYueListBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YuYueFragment extends BaseFragment {
    @Bind(R.id.yuyueRecyCleView)
    RecyclerView recyclerView;
    private YuYueAdapter adapter;
    private List<YuYueListBean.BodyBean>list = new ArrayList<>();
    private String id;

    @Override
    protected int getFragmentLayout() {
        return R.layout.yuyue_fragment;
    }

    @Override
    protected void FragmentInitData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adapter = new YuYueAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new YuYueAdapter.ListDataCallBack() {
            @Override
            public void xiangqingOnClick(int position) {
                startActivity(new Intent(getActivity(), YuYueXiangQingActivity.class)
                .putExtra("id",list.get(position).getId()+""));
            }

            @Override
            public void wangongOnClick(final int position) {
                if (list.get(position).getStage().equals("3")){
                    if (list.get(position).getType().equals("家装")){
                        startActivity(new Intent(getActivity(), ZhuangxiuYuyueActivity.class)
                                .putExtra("title", "家装装修")
                                .putExtra("tag", "0"));
                    }else if (list.get(position).getType().equals("维修")){
                        startActivity(new Intent(getActivity(), JuBuWeiXiuActivity.class)
                                .putExtra("title", "局部维修")
                                .putExtra("tag", "0")
                        );
                    } else{
                        startActivity(new Intent(getActivity(), ZhuangxiuYuyueActivity.class)
                                .putExtra("title", "办公装修")
                                .putExtra("tag", "0")
                        );
                    }
                    return;
                }

                new MaterialDialog.Builder(getActivity())
                        .title("确认")// 标题
                        .content("确认已完工?")// 内容
                        .positiveText("确认")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                // TODO
                                updateStageByphone(position);

                            }
                        })
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                // TODO

                            }
                        })
                        .stackingBehavior(StackingBehavior.ADAPTIVE) // 默认值：自适应
                        .show();// 显示对话框


            }

            @Override
            public void genggaiOnClick(int position) {
                if (list.get(position).getType().equals("维修")){
                    startActivity(new Intent(getActivity(), JuBuWeiXiuActivity.class)
                            .putExtra("title", "局部维修")
                            .putExtra("tag","1")
                            .putExtra("id",list.get(position).getId()+"")
                            .putExtra("repairType",list.get(position).getRepairType())
                            .putExtra("siteText",list.get(position).getSiteText())
                            .putExtra("siteId",list.get(position).getSiteId())
                    );
                }else{
                    startActivity(new Intent(getActivity(), ZhuangxiuYuyueActivity.class)
                            .putExtra("title", "家装装修")
                            .putExtra("tag","1")
                            .putExtra("id",list.get(position).getId()+"")
                            .putExtra("type1",list.get(position).getLeiXingYiName()+"")
                            .putExtra("type2",list.get(position).getLeiXingErName()+"")
                            .putExtra("siteText",list.get(position).getSiteText())
                            .putExtra("siteId",list.get(position).getSiteId())
                    );
                }

            }
        });
    }

    private void updateStageByphone(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", list.get(position).getId());
        map.put("stage", "3");
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "customerApp/updateStageByphone", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.getInstance().toastCentent("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UpdateStageByphoneBean bean = JSONUtils.toObject(string, UpdateStageByphoneBean.class);
                        if(bean.getStatusCode() == 1){
                            //成功
                            if(!TextUtils.isEmpty(id)){
                                getList(id);
                            }
                        }
                        ToastUtil.getInstance().toastCentent(bean.getStatusMsg());
                    }
                });

            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            Bundle arguments = getArguments();
            id = arguments.getString("id");
            if (!TextUtils.isEmpty(id)) {
                getList(id);

            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(id)){
            getList(id);
        }
    }

    private void getList(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", MySharedPreferences.getInstance().getUserPhone());
        map.put("stage", id);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getPhoneList", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.getInstance().toastCentent("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            YuYueListBean bean = JSONUtils.toObject(string, YuYueListBean.class);
                            if(bean.getStatusCode() == 1){
                                List<YuYueListBean.BodyBean> body = bean.getBody();
                                if(body != null && body.size() > 0){
                                    list.clear();
                                    list.addAll(body);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
