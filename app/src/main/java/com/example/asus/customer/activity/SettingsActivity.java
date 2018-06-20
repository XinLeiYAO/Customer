package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.PrefUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/17.
 */

public class SettingsActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.show_popup)
    RelativeLayout showPopup;
    @Bind(R.id.rl_upd_password)
    RelativeLayout rlUpdPassword;
    @Bind(R.id.rl_help_center)
    RelativeLayout rlHelpCenter;
    @Bind(R.id.rl_reward_punish_record)
    RelativeLayout rlRewardPunishRecord;
    @Bind(R.id.rl_vision)
    RelativeLayout rlVision;
    @Bind(R.id.btn_quit)
    Button btnQuit;

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick({R.id.iv_back, R.id.show_popup, R.id.rl_upd_password, R.id.rl_help_center, R.id.rl_reward_punish_record, R.id.rl_vision})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.show_popup:
                break;
            case R.id.rl_upd_password:
                break;
            case R.id.rl_help_center:
                break;
            case R.id.rl_reward_punish_record:
                startActivity(new Intent(SettingsActivity.this, ComplaintActivity.class));
                break;
            case R.id.rl_vision:
                break;
        }
    }


    @OnClick(R.id.btn_quit)
    public void onViewClicked() {

        App.getApp().exitApp();
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
        PrefUtils.RemoveValue(this, Constants.PHOME);
        PrefUtils.RemoveValue(this, Constants.PASSWORD);
        startActivity(new Intent(this, TextLoginActivity.class));
    }
}
