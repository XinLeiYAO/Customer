package com.example.asus.customer.activity.home;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.CompanyAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.entity.BannerImgListBean;
import com.example.asus.customer.entity.GetBranchCompanyInfoBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CompanyActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    List<GetBranchCompanyInfoBean.BodyBean> list = new ArrayList<>();
    private CompanyAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_company;
    }

    @Override
    public void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("公司地址");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new CompanyAdapter(list);
        adapter.setOnItemButtonLister(new CompanyAdapter.ItemButtonOnClickLister() {
            @Override
            public void onItemClickLister(final int i) {

                new MaterialDialog.Builder(CompanyActivity.this)
                        .title("拔打电话")
                        .content(list.get(i).getCompanyTel())// 内容
                        .positiveText("拨打")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + list.get(i).getCompanyTel()));
                                //开启打电话的意图
                                startActivity(intent);

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
        });
        recyclerView.setAdapter(adapter);
        GetBranchCompanyInfo();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }

    private void GetBranchCompanyInfo() {
        Map<String, Object> map = new HashMap<>();
//        ApiEngine.GZS_HOST +
        OkhttpUtils.doGet("http://192.168.1.65:9003/actionapi/TZManage/GetBranchCompanyInfo", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("GetBranchCompanyInfo", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                Log.e("GetBranchCompanyInfo", string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GetBranchCompanyInfoBean bean = JSONUtils.toObject(string, GetBranchCompanyInfoBean.class);
                        List<GetBranchCompanyInfoBean.BodyBean> body = bean.getBody();
                        list.addAll(body);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
