package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.NewsRedAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.InfoMessageBean;
import com.example.asus.customer.mvp.contract.MessageTwoContract;
import com.example.asus.customer.mvp.presenter.MessageTwoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/5/18.
 */

public class AdnewsActivity extends BaseActivity<MessageTwoPresenter> implements MessageTwoContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.ll_message)
    ListView llMessage;
    @Bind(R.id.title_relative)
    RelativeLayout title_relative;
    @Bind(R.id.tv_right)
    TextView tvRight;
    private List<InfoMessageBean.BodyBean> list;
    NewsRedAdapter newsRedAdapter;
    private String groupid;

    @Override
    public int getLayout() {
        return R.layout.activity_messagetwo;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);
        groupid = intent.getStringExtra("groupid");
        mPresenter.getInfoMessageTwoList(App.tokenInfo.getCardNo(), groupid);
        ShowListview();
    }

    private void ShowListview() {
        list = new ArrayList<>();
        newsRedAdapter = new NewsRedAdapter(this, list);
        llMessage.setAdapter(newsRedAdapter);
        llMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //    mPresenter.toSeeDetails(list.get(position).getId()+"");

                startActivity(new Intent(AdnewsActivity.this, MessageDetailsActivity.class).putExtra("id", list.get(position).getId() + ""));

            }
        });
    }

    @Override
    protected MessageTwoPresenter onCreatePresenter() {
        return new MessageTwoPresenter(this);
    }

    @Override
    public void responseInfoMessageTwo(InfoMessageBean data) {
        list.clear();
        list.addAll(data.getBody());
        newsRedAdapter.notifyDataSetChanged();
    }

    @Override
    public void responseInfoMessageTwoError(String msg) {

    }

    @Override
    public void responseSeeDetails(InfoMessageBean data) {

    }

    @Override
    public void responseSeeDetailsError(String msg) {

    }

    @Override
    public void setMessage(String statusMsg) {
        ShowUtils.Toastshort(this, statusMsg);
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
        mPresenter.getInfoMessageTwoList(cardNo, groupid);
    }

    @Override
    public void setMessageErro(String erro) {
        ShowUtils.Toastshort(this, erro);
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                String cardNo = PrefUtils.getValue(this, Constants.PHOME);
                mPresenter.setMessage(cardNo, groupid);
                break;
        }
    }
}
