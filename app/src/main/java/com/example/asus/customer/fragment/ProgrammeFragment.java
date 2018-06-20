package com.example.asus.customer.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.FindActivity;
import com.example.asus.customer.activity.FindMoreActivity;
import com.example.asus.customer.activity.PhotoImageActivity;
import com.example.asus.customer.activity.QrLoginActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.OptimizationBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.QRresultBean;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.mvp.presenter.CurrentPresenter;
import com.example.asus.customer.weight.QRScannerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.asus.customer.commons.App.body;

/**
 * Created by asus on 2018/5/26.
 */

public class ProgrammeFragment extends BaseFragment<CurrentPresenter> implements CurrentContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.See_more)
    TextView SeeMore;
    @Bind(R.id.excellent_one)
    ImageView excellentOne;
    @Bind(R.id.excellent_two)
    ImageView excellentTwo;
    @Bind(R.id.first_draft)
    ImageView firstDraft;
    @Bind(R.id.Color_flat)
    ImageView ColorFlat;
    @Bind(R.id.DesignDescription)
    TextView DesignDescription;
    ArrayList<String> original;
    ArrayList<String> flatcolor;
    @Bind(R.id.iv_back)
    TextView ivBack;
    private Intent intent;
    private ArrayList<String> photoList;
    private RecommendBean bean;
    private List<RecommendBean.BodyBean> body;
    private String ci_clientName;

    @Override
    protected int getFragmentLayout() {
        return R.layout.programme_fragment;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("项目-方案");
        initArray();
        initData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeScan();
            }
        });
    }

    private void initData() {
        mPresenter.loadHomedata(App.orderNo);
        mPresenter.loadDesignDescription(App.orderNo);
        mPresenter.getRecommendData(App.orderNo);
        mPresenter.getProgssData(App.orderNo);
    }

    private void initArray() {
        original = new ArrayList<>();
        flatcolor = new ArrayList<>();
        intent = new Intent(getContext(), PhotoImageActivity.class);
        photoList = new ArrayList<>();
    }

    @Override
    protected CurrentPresenter onCreatePresenter() {
        return new CurrentPresenter(this);
    }


    @OnClick({R.id.See_more, R.id.excellent_one, R.id.excellent_two, R.id.first_draft, R.id.Color_flat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.See_more:
                if (body.size()==0) {
                    Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
                } else {
                       if(bean.getBody()!=null) {

                           Intent intent = new Intent(getContext(), FindActivity.class);
                           intent.putExtra("case", bean);
                           intent.putExtra("name",ci_clientName);
                           startActivity(intent);
                       }else {
                           Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
                       }
                }
                break;
            case R.id.excellent_one:
                if (body.size() == 1) {


                    String projectId = bean.getBody().get(0).getProjectId();
                    startActivity(new Intent(getContext(), FindMoreActivity.class).putExtra("projectId", projectId));
                }
                break;
            case R.id.excellent_two:
                if (body.size() == 2) {

                    String projectId = bean.getBody().get(1).getProjectId();
                    startActivity(new Intent(getContext(), FindMoreActivity.class).putExtra("projectId", projectId));
                }
                break;
            case R.id.first_draft:

                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "初稿");
                intent.putStringArrayListExtra(Constants.JUMPLIST, original);
                startActivity(intent);
                break;
            case R.id.Color_flat:
                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "彩平");
                intent.putStringArrayListExtra(Constants.JUMPLIST, flatcolor);
                startActivity(intent);
                break;
        }
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
    public void getCurrentData(Picture.BodyBean Body) {
        List<Picture.BodyBean.CpBean> cp = Body.getCp();
        List<Picture.BodyBean.YsBean> ys = Body.getYs();
        if (ys != null) {
            for (Picture.BodyBean.YsBean string : ys) {
                original.add("http://img0.wenes.cn/Upload/Project/" + string.getImageUrl());
            }
        }
        if (cp != null) {
            for (Picture.BodyBean.CpBean string : cp) {
                flatcolor.add("http://img0.wenes.cn/Upload/Project/" + string.getImageUrl());
            }
        }
        Glide.with(getContext()).load(original.get(0)).into(firstDraft);
        Glide.with(getContext()).load(flatcolor.get(0)).into(ColorFlat);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void loadDesignDescription(DesignInfo.BodyBean bodyBean) {
        if (null != bodyBean) {
            DesignDescription.setText(bodyBean.getProjectBrief());
        }
    }

    @Override
    public void loadCurreerPhotoData(CurreerPhotoDataBean.BodyBean bodyBean) {

    }

    @Override
    public void getProgssData(ProgssInfo.BodyBean bodyBean) {
        ci_clientName = bodyBean.getBaseInformation().getCi_ClientName();
        App.projectName=bodyBean.getBaseInformation().getCi_ClientName();
    }

    @Override
    public void getRecommendData(RecommendBean bean) {
        this.bean = bean;
        body = bean.getBody();
        photoList.clear();
        for (RecommendBean.BodyBean key : body) {
            photoList.add(key.getMainUrl());
        }
        int size = body.size();
        if (size == 1) {
            Glide.with(getContext()).load(body.get(0).getMainUrl()).into(excellentOne);
        }
        if (size == 2) {
            Glide.with(getContext()).load(body.get(1).getMainUrl()).into(excellentTwo);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

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
