package com.example.asus.customer.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.FindActivity;
import com.example.asus.customer.activity.FindMoreActivity;
import com.example.asus.customer.activity.PhotoImageActivity;
import com.example.asus.customer.activity.QrLoginActivity;
import com.example.asus.customer.adapter.VolumePictureAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.OptimizationBean;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.QRresultBean;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.ProcessContract;
import com.example.asus.customer.mvp.presenter.ProcessPresenter;
import com.example.asus.customer.weight.CustomLinearLayoutManager;
import com.example.asus.customer.weight.QRScannerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by asus on 2018/5/26.
 */

public class VolumeRoomFragment extends BaseFragment<ProcessPresenter> implements ProcessContract.View {
    @Bind(R.id.See_More)
    TextView SeeMore;
    @Bind(R.id.recommend_one)
    ImageView recommendOne;
    @Bind(R.id.recommend_two)
    ImageView recommendTwo;
    @Bind(R.id.housing_Situation)
    TextView housingSituation;
    @Bind(R.id.Rent_period)
    TextView RentPeriod;
    @Bind(R.id.Areause)
    TextView Areause;
    @Bind(R.id.Builtarea)
    TextView Builtarea;
    @Bind(R.id.Estimatedamount)
    TextView Estimatedamount;
    @Bind(R.id.Decorationtime)
    TextView Decorationtime;
    @Bind(R.id.Geomanticrequirements)
    TextView Geomanticrequirements;
    @Bind(R.id.Softfurnishings)
    TextView Softfurnishings;
    @Bind(R.id.Intelligentelectricity)
    TextView Intelligentelectricity;
    @Bind(R.id.Volumephoto)
    RecyclerView Volumephoto;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    TextView ivBack;
    @Bind(R.id.xinxi)
    LinearLayout xinxi;
    private ArrayList<PhotoDetailsBean> mlist;
    VolumePictureAdapter recyViewAdapter;
    private ArrayList<String> photoList;
    private OptimizationBean bodyBean;
    private RecommendBean bean;
    private List<OptimizationBean.BodyBean> body;
    private String ci_clientName;

    @Override
    protected int getFragmentLayout() {
        return R.layout.volumeroom_fragment;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("项目-量房");
        photoList = new ArrayList<>();
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(getContext(), 2);
        linearLayoutManager.setScrollEnabled(false);
        mlist = new ArrayList<>();
        Volumephoto.setLayoutManager(linearLayoutManager);
        recyViewAdapter = new VolumePictureAdapter(mlist, getContext(), "量房");
        Volumephoto.setAdapter(recyViewAdapter);


        recommendOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), FindMoreActivity.class).putExtra("projectId", body.get(0).getProjectid()));
            }
        });
        recommendTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FindMoreActivity.class).putExtra("projectId", body.get(1).getProjectid()));
            }
        });
        initdata();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeScan();
            }
        });
    }

    private void initdata() {

        mPresenter.volumeRoomPhooto(App.orderNo);
        mPresenter.getProgssData(App.orderNo);
     //   mPresenter.getOptimizationBean();
//
//        if (App.starts.equals("1")) {
//            xinxi.setVisibility(View.GONE);
//        } else if (App.starts.equals("2")) {
//
//        }

    }

    @Override
    protected ProcessPresenter onCreatePresenter() {
        return new ProcessPresenter(this);
    }

    @OnClick(R.id.See_More)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), FindActivity.class);

        if (bodyBean == null) {
            Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("case", bodyBean);
            intent.putExtra("name",ci_clientName);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void getProgssData(ProgssInfo.BodyBean bodyBean) {

        if (null == bodyBean) {
        } else {
            ci_clientName = bodyBean.getBaseInformation().getCi_ClientName();
            App.projectName=bodyBean.getBaseInformation().getCi_ClientName();
            String ca_transactionType = bodyBean.getHousingResourcesInformation().getCa_HousingType();
            housingSituation.setText(ca_transactionType);
            if (bodyBean.getHousingResourcesInformation().getCa_RentFreeDate().equals("")) {
                RentPeriod.setText("无");
            } else {
                RentPeriod.setText(bodyBean.getHousingResourcesInformation().getCa_RentFreeDate());
            }

            Areause.setText(bodyBean.getHousingResourcesInformation().getCi_Area() + "");
            Builtarea.setText(bodyBean.getHousingResourcesInformation().getCi_BuildingArea() + "");
            Estimatedamount.setText(bodyBean.getDecorateInformation().getCa_DecBudgetPrice() + "");
            Decorationtime.setText(bodyBean.getDecorateInformation().getCa_DecorationDate() + "");
            Geomanticrequirements.setText(bodyBean.getDecorateInformation().getCa_FengShuiRequirements());
            Softfurnishings.setText(bodyBean.getDecorateInformation().getCa_SoftFurniture());
            Intelligentelectricity.setText(bodyBean.getDecorateInformation().getCa_IntelligentWeakCurrent());//
        }
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void loadCustomerInformation(MineUserInfo.BodyBean bodyBean) {

    }

    @Override
    public void getCurrentData(Picture.BodyBean body) {

    }

    @Override
    public void loadDesignDescription(DesignInfo.BodyBean bodyBean) {

    }

    @Override
    public void loadCurreerPhotoData(CurreerPhotoDataBean.BodyBean bodyBean) {

    }

    @Override
    public void volumeRoomPhooto(List<PhotoDetailsBean> mlist) {
        this.mlist.clear();
        for (int i = 0; i < mlist.size(); i++) {
            String catalogId = mlist.get(i).getCatalogId();
            if (catalogId.equals("123") || catalogId.equals("109") || catalogId.equals("124") || catalogId.equals("125")) {
                this.mlist.add(mlist.get(i));
            }
        }
        recyViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void getOptimizationBean(OptimizationBean bodyBean) {
        body = bodyBean.getBody();
        photoList.clear();
        for (OptimizationBean.BodyBean key : body) {
            photoList.add(key.getMainurl());
        }
        OptimizationBean.BodyBean bodyBean1 = body.get(0);
        OptimizationBean.BodyBean bodyBean2 = body.get(1);
        Glide.with(getContext()).load(bodyBean1.getMainurl()).into(recommendOne);
        Glide.with(getContext()).load(bodyBean2.getMainurl()).into(recommendTwo);
        this.bodyBean = bodyBean;
    }

    @Override
    public void getRecommendData(RecommendBean bean) {
        List<RecommendBean.BodyBean> body = bean.getBody();
        photoList.clear();
        for (RecommendBean.BodyBean key : body) {
            photoList.add(key.getMainUrl());
        }
        RecommendBean.BodyBean bodyBean1 = body.get(0);
        RecommendBean.BodyBean bodyBean2 = body.get(1);
        Glide.with(getContext()).load(bodyBean1.getMainUrl()).into(recommendOne);
        Glide.with(getContext()).load(bodyBean2.getMainUrl()).into(recommendTwo);
        this.bean = bean;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 扫描二维码
     */
    private static final int REQ_CODE_PERMISSION = 0x1111;

    private void QRCodeScan() {//6.0以上的手机需要处理权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
        } else {
            // Have gotten the permission
            startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data != null) {
                            String result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                            QRresultBean info = JSONUtils.toObject(result, QRresultBean.class);
                            String biaoshi = info.getParameter().getLogin_id();
                            if (biaoshi != null) {
                                startActivity(new Intent(getActivity(), QrLoginActivity.class).putExtra("appid", biaoshi));
                            } else {
                                showToast("请扫描正确的二维码！");
                            }
                        }
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            Log.e("RESULT_CANCELED=====", data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
                break;
        }
    }
}
