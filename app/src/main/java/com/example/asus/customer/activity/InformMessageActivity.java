package com.example.asus.customer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.MessageNewAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.entity.InfoMessageBean;
import com.example.asus.customer.mvp.contract.InfoMessageContract;
import com.example.asus.customer.mvp.presenter.InfoMessagePresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/7/23.
 * 我 --  消息   界面
 */

public class InformMessageActivity extends BaseActivity<InfoMessagePresenter> implements InfoMessageContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.lv_infomessage)
    ListView lv_infomessage;
    @Bind(R.id.title_relative)
    RelativeLayout title_relative;
    private List<InfoMessageBean.BodyBean> body;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_informmessage;
    }

    @Override
    public void initData() {
        tvTitle.setText("消息");
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
        //设置状态栏文字颜色及图标为深色
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        tvTitle.setTextColor(Color.parseColor("#000000"));
//        ivBack.setImageResource(R.mipmap.ic_backblack);


    }

    @Override
    protected void onResume() {
        super.onResume();
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
        //获取信息列表
        mPresenter.getInfoMessageList(cardNo);
    }

    @Override
    protected InfoMessagePresenter onCreatePresenter() {
        return new InfoMessagePresenter(this);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    MessageNewAdapter messageNewAdapter;

    @Override
    public void responseInfoMessage(final InfoMessageBean data) {

//        informMessageAdapter=new InformMessageAdapter(this,data.getBody());
        body = data.getBody();
        messageNewAdapter = new MessageNewAdapter(this, body);
        lv_infomessage.setAdapter(messageNewAdapter);
        lv_infomessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!body.get(position).getGroup_name().equals("红包")) {
                    if (!StringUtils.isEmpty(data.getBody().get(position).getGroup()))
                        startActivity(new Intent(InformMessageActivity.this, MessageTwoActivity.class)
                                .putExtra("groupid", data.getBody().get(position).getGroup())
                                .putExtra("title", data.getBody().get(position).getGroup_name() + "列表"));
                    ;
                } else {
                    if (!StringUtils.isEmpty(data.getBody().get(position).getGroup())) ;
                    startActivity(new Intent(InformMessageActivity.this, AdnewsActivity.class)
                            .putExtra("groupid", data.getBody().get(position).getGroup())
                            .putExtra("title", data.getBody().get(position).getGroup_name() + "列表"));
                }
            }
        });
    }

    @Override
    public void responseInfoMessageError(String msg) {

    }

    @Override
    public void responseSeeDetails(InfoMessageBean data) {
//        informMessageAdapter.notifyDataSetChanged();
        messageNewAdapter.notifyDataSetChanged();
    }

    @Override
    public void responseSeeDetailsError(String msg) {

    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }
}
