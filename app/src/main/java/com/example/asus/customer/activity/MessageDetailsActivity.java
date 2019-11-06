package com.example.asus.customer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.entity.MessageDetailsBean;
import com.example.asus.customer.mvp.contract.MessageDetailsContract;
import com.example.asus.customer.mvp.presenter.MessageDetailsPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hjh on 2018/3/9.
 */

public class MessageDetailsActivity extends BaseActivity<MessageDetailsPresenter> implements MessageDetailsContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_moneynum)
    TextView tvMoneynum;
    @Bind(R.id.tv_person)
    TextView tvPerson;
    @Bind(R.id.title_relative)
    RelativeLayout title_relative;


    @Override
    public int getLayout() {
        return R.layout.activity_messagethree;
    }

    @Override
    public void initData() {
        tvTitle.setText("消息详情");
        ivBack.setVisibility(View.VISIBLE);


//        if (title_relative != null) {
//            title_relative.setBackgroundColor(getResources().getColor(R.color.white));
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            //设置修改状态栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
//            window.setStatusBarColor(getResources().getColor(R.color.white));
//        }
//        //设置状态栏文字颜色及图标为深色
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        tvTitle.setTextColor(Color.parseColor("#000000"));
//        ivBack.setImageResource(R.mipmap.ic_backblack);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mPresenter.getInfoMessageDList(id);
    }

    @Override
    protected MessageDetailsPresenter onCreatePresenter() {
        return new MessageDetailsPresenter(this);
    }

    @Override
    public void responseInfoMessageD(MessageDetailsBean data) {
        tvTitles.setText(data.getBody().getTitle());
        tvContent.setText(data.getBody().getTxt());
        tvTime.setText(data.getBody().getCreate_date());
        if (!StringUtils.isEmpty(data.getBody().getInitiator_name())) {
            tvPerson.setText(data.getBody().getInitiator_name());
            tvPerson.setVisibility(View.VISIBLE);
        } else {
            tvPerson.setVisibility(View.GONE);
        }
        if (!StringUtils.isEmpty(data.getBody().getReward_money())) {
            int money = Integer.parseInt(data.getBody().getReward_money());
            tvMoneynum.setText(data.getBody().getReward_money());
            tvMoneynum.setVisibility(View.VISIBLE);
            tvMoney.setVisibility(View.VISIBLE);
            if (money == 0) {
                tvMoneynum.setVisibility(View.GONE);
                tvMoney.setVisibility(View.GONE);
            } else if (money > 0) {
                tvMoneynum.setTextColor(this.getResources().getColor(R.color.colorPrimarys));
            } else {
                tvMoneynum.setTextColor(this.getResources().getColor(R.color.colorRed));
            }
        } else {
            tvMoneynum.setVisibility(View.GONE);
            tvMoney.setVisibility(View.GONE);
        }
    }

    @Override
    public void responseInfoMessageDError(String msg) {
        showToast(msg);
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}