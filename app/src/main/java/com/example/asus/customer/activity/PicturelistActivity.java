package com.example.asus.customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.PictrueAdapter;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/19.
 */

public class PicturelistActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.picture_list)
    ListView pictureList;

    @Override
    public int getLayout() {
        return R.layout.activity_picturelist;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        ArrayList<String> mlist = intent.getStringArrayListExtra(Constants.PICTURELIST);


        PictrueAdapter pictrueAdapter = new PictrueAdapter(this, mlist);
        pictureList.setAdapter(pictrueAdapter);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
