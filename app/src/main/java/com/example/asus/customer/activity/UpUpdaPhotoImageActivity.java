package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.MyViewPagerAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.mvp.contract.UpdUserInfoContract;
import com.example.asus.customer.mvp.presenter.UpdUserInfoPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/20.
 */

public class UpUpdaPhotoImageActivity extends BaseActivity<UpdUserInfoPresenter> implements UpdUserInfoContract.View {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.imgs_viewpager)
    ViewPager imgsViewpager;
    @Bind(R.id.img_browse_back)
    ImageView imgBrowseBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_text)
    TextView tvText;


    @Override
    public int getLayout() {
        return R.layout.updata_photo_item;
    }

    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);
        List<String> imagee = getIntent().getStringArrayListExtra(Constants.JUMPLIST);
        tvTitle.setText("头像");
        imgsViewpager = (ViewPager) this.findViewById(R.id.imgs_viewpager);
        imgsViewpager.setOffscreenPageLimit(2);
        Log.e("tag",imagee.toString());
        PagerAdapter adapter = new MyViewPagerAdapter(this, imagee);
        imgsViewpager.setAdapter(adapter);
        imgsViewpager.setCurrentItem(0);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(UpUpdaPhotoImageActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .enableCrop(true)// 是否裁剪 true or false
                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
            }
        });
    }

    @Override
    protected UpdUserInfoPresenter onCreatePresenter() {
        return new UpdUserInfoPresenter(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);

                    mPresenter.upHeaderPicture(App.tokenInfo.getToken(), App.tokenInfo.getCardNo(), localMedias.get(0).getCutPath());
                    break;
            }
        }
    }

    @Override
    public void responseUpdateData() {

    }

    @Override
    public void responseUpdateDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseUpPicture() {
     finish();
    }
    @Override
    public void responseUpPictureError(String msg) {
        showToast(msg);
    }

    @Override
    public void reLogin(String msg) {

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
