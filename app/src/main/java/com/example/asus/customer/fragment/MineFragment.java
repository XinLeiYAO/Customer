package com.example.asus.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.SettingsActivity;
import com.example.asus.customer.activity.UpdUserInfoActivity;
import com.example.asus.customer.activity.UserInfoActivity;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.UserInfoBean;
import com.example.asus.customer.mvp.contract.MineContract;
import com.example.asus.customer.mvp.presenter.MinePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/4/16.
 */

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.show_popup)
    RelativeLayout showPopup;
    @Bind(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @Bind(R.id.rl_integral)
    RelativeLayout rlIntegral;
    @Bind(R.id.rl_settings)
    RelativeLayout rlSettings;
    @Bind(R.id.riv_mine)
    ImageView rivMine;
    @Bind(R.id.tv_mine_name)
    TextView tvMineName;
    @Bind(R.id.tv_mine_card)
    TextView tvMineCard;
    @Bind(R.id.ewmImage)
    ImageView ewmImage;

    @Override
    protected int getFragmentLayout() {
        return R.layout.minefragment;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("我");
        String name = App.body.getBaseinfo().getName();
        String account = App.tokenInfo.getAccount();
        if (name != null) {
            tvMineName.setText(name);
        }
        tvMineCard.setText(account);

    }

    @Override
    public void onResume() {
        super.onResume();
        initUserData();
        //  mPresenter.loadCustomerInformation(App.orderNo);
    }

    @Override
    protected MinePresenter onCreatePresenter() {
        return new MinePresenter(this);
    }

    @OnClick({R.id.rl_user_info, R.id.rl_integral, R.id.rl_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_user_info:
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                break;
            case R.id.rl_integral:
                break;
            case R.id.rl_settings:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
        }
    }

    private void initUserData() {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("cardNo", App.tokenInfo.getCardNo());
        hashMap.put("token", App.tokenInfo.getToken());
        OkhttpUtils.doPost(ApiEngine.INFORMATION, hashMap, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowUtils.Toastshort(getContext(), e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("tag", string);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UserInfoBean userInfoBean = JSONUtils.toObject(string, UserInfoBean.class);
                        if (userInfoBean.getStatusCode() == 0) {

                            List<UserInfoBean.BodyBean> body = userInfoBean.getBody();
                            UserInfoBean.BodyBean bodyBean = body.get(0);
                            tvMineName.setText(bodyBean.getU_name() == null ? "昵称" : bodyBean.getU_name());
                            tvMineCard.setText("账号：" + bodyBean.getCard_no());
                            RequestOptions options = new RequestOptions();
                            options.placeholder(R.mipmap.userimage);
                            options.error(R.mipmap.userimage);
                            Glide.with(getActivity()).load(bodyBean.getImage()).apply(options).into(rivMine);
                        } else {
                            showToast(userInfoBean.getStatusMsg());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
    public void loadCustomerInformation(MineUserInfo.BodyBean bodyBean) {
        //rivMine
        String name = bodyBean.getName();
        String xueli = bodyBean.getXueli();
        String mavatar = bodyBean.getMavatar();
        Glide.with(this).load(mavatar).into(rivMine);
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
