package com.example.asus.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.JiaoLiuActivity;
import com.example.asus.customer.activity.MoreBannerActivity;
import com.example.asus.customer.activity.MoreHomeActivity;
import com.example.asus.customer.activity.ProIMWebActivity;
import com.example.asus.customer.activity.home.SheJiShiActivity;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AntiShake;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.entity.UserMessageBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/5/26.
 * 更多
 */

public class MoreFragment extends BaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.auspic_more)
    ImageView auspicMore;
    @Bind(R.id.Designer_more)
    LinearLayout DesignerMore;
    @Bind(R.id.auspic)
    LinearLayout auspic;
    @Bind(R.id.complaint_more)
    LinearLayout complaintMore;
    @Bind(R.id.anli)
    LinearLayout anli;
    @Bind(R.id.liangfang)
    LinearLayout liangfang;
    @Bind(R.id.fangan)
    LinearLayout fangan;
    @Bind(R.id.yusuan)
    LinearLayout yusuan;
    @Bind(R.id.hezuo)
    LinearLayout hezuo;
    @Bind(R.id.anli_xiaoxi)
    ImageView anliXiaoxi;
    @Bind(R.id.anli_shejishi)
    ImageView anliShejishi;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.iv_message)
    TextView iv_message;

    @Override
    protected int getFragmentLayout() {
        return R.layout.more_fragment;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("瑞祥装饰");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.mipmap.liaotian);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProIMWebActivity.class));
            }
        });

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            unread();//获取未读消息
        }
        getImMessage();//获取聊天消息
    }

    /**
     * 初始化消息
     */
    private void xiaoXiData() {
        String value = PrefUtils.getValue(getActivity(), Constants.UnreadData);
        UnreadBean unreadBean = JSONUtils.toObject(value, UnreadBean.class);
        UnreadBean.BodyBean body = unreadBean.getBody();
        boolean al = body.isAL();//案例
        boolean tsjs = body.isTSJS();//设计师
        if (al) {
            anliXiaoxi.setVisibility(View.VISIBLE);
        } else {
            anliXiaoxi.setVisibility(View.GONE);
        }
        if (tsjs) {
            anliShejishi.setVisibility(View.VISIBLE);
        } else {
            anliShejishi.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        unread();//获取未读消息
        getImMessage();
    }

    private void getImMessage() {
        Map map = new HashMap();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        //map.put("userName", value);
        OkhttpUtils.doGet("https://chat.wenes.cn/index/getMsgCountFromRedis?userName="+value, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if(statusCode == 0){
                                UserMessageBean bean = JSONUtils.toObject(string, UserMessageBean.class);
                                int count = bean.getBody().getCount();
                                if(count != 0){
                                    iv_message.setVisibility(View.VISIBLE);
                                    iv_message.setText(count+"");
                                }else{
                                    iv_message.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 获取未读消息
     */
    private void unread() {
        Map map = new HashMap();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdId", value);
        OkhttpUtils.doGet(ApiEngine.FS_API_HOST + "/api/customer/getUnRead", map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0) {
                                UnreadBean unreadBean = JSONUtils.toObject(string, UnreadBean.class);
                                String str = JSONUtils.toString(unreadBean);
                                PrefUtils.putValue(getActivity(), Constants.UnreadData, str);
                                xiaoXiData();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private AntiShake antiShake = new AntiShake();

    @OnClick({R.id.tv_title,
            R.id.auspic_more,
            R.id.Designer_more,
            R.id.auspic,
            R.id.complaint_more,
            R.id.shejiyuan_lin,
            R.id.jiaju_lin,
            R.id.ruodian_lin,
            R.id.ruanzhuang_lin,
            R.id.kongtiao_lin,
            R.id.xiaofang_lin,
            R.id.gangjiegou_lin,
            R.id.lvzhi_lin,
            R.id.baojie_lin,
            R.id.anli,
            R.id.liangfang,
            R.id.fangan,
            R.id.yusuan,
            R.id.hezuo})
    public void onViewClicked(View view) {
        if (antiShake.check(view.getId() + "-1")) return;
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.auspic_more:
                break;
            case R.id.Designer_more:
                startActivity(new Intent(getActivity(), SheJiShiActivity.class));
                break;
            case R.id.auspic://瑞祥装饰
                startActivity(new Intent(getActivity(), MoreBannerActivity.class));
                break;
            case R.id.complaint_more:
                startActivity(new Intent(getActivity(), JiaoLiuActivity.class));
                break;
            case R.id.anli://案例
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "1")
                        .putExtra("name", "案例"));
                break;
            case R.id.liangfang://量房
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "2")
                        .putExtra("name", "量房"));
                break;
            case R.id.fangan://方案
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "3")
                        .putExtra("name", "方案"));
                break;
            case R.id.yusuan://预算
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "4")
                        .putExtra("name", "预算"));
                break;
            case R.id.hezuo://合作
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "5")
                        .putExtra("name", "合作"));
                break;
            case R.id.jiaju_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","家具"));
                break;
            case R.id.ruodian_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","弱电"));
                break;
            case R.id.shejiyuan_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","设计院"));
                break;
            case R.id.ruanzhuang_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","软装"));
                break;
            case R.id.kongtiao_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","空调"));
                break;
            case R.id.xiaofang_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","消防"));
                break;
            case R.id.gangjiegou_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","钢结构"));
                break;
            case R.id.lvzhi_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","绿植"));
                break;
            case R.id.baojie_lin:
                ShowUtils.Toastshort(getActivity(), "模块暂未开放，敬请期待");
//                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","保洁"));
                break;

        }
    }
}
