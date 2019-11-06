package com.example.asus.customer.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.ChuZuActivity;
import com.example.asus.customer.activity.JuBuWeiXiuActivity;
import com.example.asus.customer.activity.MyYuYueActivity;
import com.example.asus.customer.activity.QRZhongJianYeActivity;
import com.example.asus.customer.activity.TextLoginActivity;
import com.example.asus.customer.activity.WebActivity;
import com.example.asus.customer.activity.ZhuangxiuYuyueActivity;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.BannerImgListBean;
import com.example.asus.customer.entity.HomeDataBean;
import com.example.asus.customer.entity.ObjectDataBean;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.mvp.contract.KeHuHomeContract;
import com.example.asus.customer.mvp.presenter.KeHuHomePresenter;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class KehuNewHomeFragment extends BaseFragment<KeHuHomePresenter> implements KeHuHomeContract.KeHuHomeView {
    @Bind(R.id.newHome_banner)
    XBanner newHome_banner;
    @Bind(R.id.jiahzuang_layout)
    LinearLayout jiazhuangLayout;
    @Bind(R.id.gongzhuang_layout)
    LinearLayout gongzhuangLayout;
    @Bind(R.id.weixiu_layout)
    LinearLayout weixiuLayout;
    @Bind(R.id.chuzu_layout)
    LinearLayout chuzu_layout;
    @Bind(R.id.newHome_saoyisao)
    ImageView saoyisao;
    @Bind(R.id.ivBottom)
    ImageView ivBottom;
    @Bind(R.id.tvMessage)
    TextView tvMessage;
    private ArrayList<String> imageList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();
    private String cardNo = "";
    private String href;

    @Override
    protected int getFragmentLayout() {
        return R.layout.kehu_new_fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        cardNo = App.cardNo;
    }


    @Override
    protected void FragmentInitData() {
        cardNo = App.cardNo;
//        imageList.add(R.mipmap.shouye_lunbo);
//        imageList.add(R.mipmap.shouye_lunbo);
//        titleList.add("");
//        titleList.add("");

        newHome_banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imageList.get(position)).into((ImageView) view);
            }
        });
        newHome_banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                ToastUtil.getInstance().toastCentent("你点击了" + position);
            }
        });

        jiazhuangLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                startActivity(new Intent(getActivity(), ZhuangxiuYuyueActivity.class)
                        .putExtra("title", "家装装修")
                        .putExtra("tag", "0")
                );

            }
        });

        gongzhuangLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                startActivity(new Intent(getActivity(), ZhuangxiuYuyueActivity.class)
                        .putExtra("title", "办公装修")
                        .putExtra("tag", "0")
                );
            }
        });

        weixiuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (!TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), JuBuWeiXiuActivity.class)
                            .putExtra("title", "局部维修")
                            .putExtra("tag", "0")
                    );
//                    ToastUtil.getInstance().toastCentent("功能开发中");

                } else {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                }
            }
        });
        chuzu_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (!TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), ChuZuActivity.class)
                            .putExtra("title", "出租")
                            .putExtra("tag", "0")
                    );
//                    ToastUtil.getInstance().toastCentent("功能开发中");

                } else {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                }
            }
        });

        saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (!TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), QRZhongJianYeActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                }
            }
        });
        tvMessage.setSelected(true);
        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyYuYueActivity.class));
            }
        });
        ivBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title", "浏览").putExtra("href", href));
            }
        });
        bannerImageList();
        homeData();
    }

    @Override
    public void onResume() {
        super.onResume();
        newHome_banner.startAutoPlay();
        cardNo = App.cardNo;

    }

    @Override
    public void onStop() {
        super.onStop();
        newHome_banner.stopAutoPlay();
    }

    @Override
    protected KeHuHomePresenter onCreatePresenter() {
        return new KeHuHomePresenter(this);
    }

    @Override
    public void getObjectData(ObjectDataBean objectDataBean) {
        ObjectDataBean.BodyBean body = objectDataBean.getBody();
        String ci_clientName = body.getCi_ClientName();
        String ci_designerCard = body.getCi_DesignerCard();
        String ci_designerName = body.getCi_DesignerName();
        int ci_stage = body.getCi_Stage();
        PrefUtils.putValue(getActivity(), Constants.ci_stage, ci_stage + "");
        PrefUtils.putValue(getActivity(), Constants.ci_designerName, ci_designerName);
        PrefUtils.putValue(getActivity(), Constants.ci_designerCard, ci_designerCard);
        PrefUtils.putValue(getActivity(), Constants.ci_clientName, ci_clientName);
        PrefUtils.putValue(getActivity(), Constants.designerPhone, body.getDesignerPhone());
    }

    @Override
    public void getObjectDataErro(String erro) {

    }

    @Override
    public void getUnreadData(UnreadBean unreadBean) {
        String str = JSONUtils.toString(unreadBean);
        PrefUtils.putValue(getActivity(), Constants.UnreadData, str);
    }

    @Override
    public void getUnreadDataErro(String erro) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }


    private void bannerImageList() {
        Map<String, Object> map = new HashMap<>();
        map.put("cityId", "0");
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "app/carouselpic/list", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                Log.e("app/carouselpic/list",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                Log.e("bannerImageList",string);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BannerImgListBean  bean = JSONUtils.toObject(string, BannerImgListBean.class);
                        List<BannerImgListBean.BodyBean> body = bean.getBody();
                        for (int i = 0;i<body.size();i++){
                            imageList.add(body.get(i).getPicUrl());
                            titleList.add("");
                        }
                        newHome_banner.setData(imageList, titleList);
//                        newHome_banner.notify();

                    }
                });
            }
        });
    }

    private void homeData() {
        Map<String, Object> map = new HashMap<>();
        map.put("cityId", "0");
        map.put("phone", MySharedPreferences.getInstance().getUserPhone());
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/app/home", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                Log.e("app/home",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                Log.e("homeData",string);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HomeDataBean bean = JSONUtils.toObject(string, HomeDataBean.class);
                        if(bean.getStatusCode()==1){
                            List<HomeDataBean.BodyBean.BottomCarouseBean> bottomCarouse = bean.getBody().getBottomCarouse();
                            List<HomeDataBean.BodyBean.TopCarouseBean> topCarouse = bean.getBody().getTopCarouse();
                            int pending = bean.getBody().getPending();
                            int processed = bean.getBody().getProcessed();

                            for (int i = 0;i<topCarouse.size();i++){
                                imageList.add(topCarouse.get(i).getPicUrl());
                                titleList.add("");
                            }
                            newHome_banner.setData(imageList, titleList);

                            RequestOptions options = new RequestOptions();
                            options.placeholder(R.mipmap.home_bottom_img);
                            options.error(R.mipmap.home_bottom_img);

                            FragmentActivity activity = getActivity();
                            if (activity != null) {
                                Glide.with(activity).load(bottomCarouse.get(0).getPicUrl()).apply(options).into(ivBottom);
                            }
                            href = bottomCarouse.get(0).getHref();
                            tvMessage.setText("我的预约：待处理"+pending+"单，进行中"+processed+"单，查看详情。");
                        }


                    }
                });
            }
        });
    }
}
