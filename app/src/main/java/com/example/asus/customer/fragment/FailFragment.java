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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.QrLoginActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.AntiShake;
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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by asus on 2018/7/17.
 */

public class FailFragment extends BaseFragment<ProcessPresenter> implements ProcessContract.View {
    @Bind(R.id.iv_back)
    TextView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.show_popup)
    RelativeLayout showPopup;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void FragmentInitData() {
        mPresenter.getProgssData(App.orderNo);
    }

    @Override
    protected ProcessPresenter onCreatePresenter() {
        return new ProcessPresenter(this);
    }

    private static final int REQ_CODE_PERMISSION = 0x1111;

    /**
     * 相机权限
     */
    private void QRCodeScan() {//6.0以上的手机需要处理权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
        } else {
            // Have gotten the permission
            //startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
        }
    }

    /**
     * 扫一扫返回的数据  回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            /*case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data != null) {
                            try {
                                String result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                                QRresultBean info = JSONUtils.toObject(result, QRresultBean.class);
                                String biaoshi = info.getParameter().getLogin_id();
                                if (biaoshi != null) {
                                    startActivity(new Intent(getActivity(), QrLoginActivity.class).putExtra("appid", biaoshi));
                                } else {
                                    showToast("请扫描正确的二维码！");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
//                            Log.e("RESULT_CANCELED=====", data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
                break;*/
        }
    }

    /**
     * 解除butterknife绑定
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        if(new AntiShake().check(ivBack.getId())) return;
        QRCodeScan();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void getProgssData(ProgssInfo.BodyBean bodyBean) {
        if (null == bodyBean) {
        } else {
            String ci_clientName = bodyBean.getBaseInformation().getCi_ClientName();
            if (ci_clientName.length() > 4) {
                String substring = ci_clientName.substring(0, 4);
                tvTitle.setText(substring );
            } else {
                tvTitle.setText(ci_clientName);
            }
            App.projectName=bodyBean.getBaseInformation().getCi_ClientName();
        }
    }

    @Override
    public void showMessage(String message) {

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

    }

    @Override
    public void getOptimizationBean(OptimizationBean bean) {

    }

    @Override
    public void getRecommendData(RecommendBean bean) {

    }
}
