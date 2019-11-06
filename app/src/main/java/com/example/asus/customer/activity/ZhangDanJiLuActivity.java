package com.example.asus.customer.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.ZhangdanJiLuAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.ZhangDanJiLuBean;

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

public class ZhangDanJiLuActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.title_relative)
    RelativeLayout mTitleRelative;
    @Bind(R.id.show_popup)
    LinearLayout mShowPopup;
    @Bind(R.id.zhichu)
    TextView mZhichu;
    @Bind(R.id.shouru)
    TextView mShouru;
    @Bind(R.id.zong_money)
    TextView zong_money;
    @Bind(R.id.shou_money)
    TextView shou_money;
    @Bind(R.id.recycleView)
    RecyclerView mRecycleView;
    List<ZhangDanJiLuBean.BodyBean.ListBean> list = new ArrayList<>();
    private ZhangdanJiLuAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_zhang_dan_ji_lu;
    }

    @Override
    public void initData() {
        mTvTitle.setText("账单记录");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new ZhangdanJiLuAdapter(this,list);
        mRecycleView.setAdapter(adapter);
        initMessage();
    }

    private void initMessage() {
        showLoading();
        Map<String,Object>map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        map.put("rwdId",value);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getbillByRwdId", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getbillByRwdId?rwdId=19-225080}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ZhangDanJiLuBean bean = JSONUtils.toObject(string, ZhangDanJiLuBean.class);
                        ZhangDanJiLuBean.BodyBean body = bean.getBody();
                        if(body != null){
                            zong_money.setText(body.getSummoney());
                            shou_money.setText(body.getSummoney());
                            List<ZhangDanJiLuBean.BodyBean.ListBean> bodyList = body.getList();
                            list.addAll(bodyList);
                            adapter.notifyDataSetChanged();
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
