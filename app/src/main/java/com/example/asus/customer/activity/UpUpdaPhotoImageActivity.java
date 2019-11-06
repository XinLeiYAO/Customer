package com.example.asus.customer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.MyViewPagerAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.TimeZoneUtils;
import com.example.asus.customer.entity.ImgBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.mvp.contract.UpdUserInfoContract;
import com.example.asus.customer.mvp.presenter.UpdUserInfoPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private OssManager ossManager;
    private String imageName;


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
        PagerAdapter adapter = new MyViewPagerAdapter(this, imagee);
        imgsViewpager.setAdapter(adapter);
        imgsViewpager.setCurrentItem(0);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getOssData();
                PictureSelector.create(UpUpdaPhotoImageActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .enableCrop(false)// 是否裁剪 true or false
                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
            }
        });
    }


    //阿里云oss域名
    String ENDPOINT="https://oss-cn-beijing.aliyuncs.com";
    //bucketname仓库
    String BUCKETNAME="holding01";
    String imaUrl="https://holding01.oss-cn-beijing.aliyuncs.com/rs/";
    /**
     * oss初始化信息请求回调
     */
    @Override
    public void OssData(OSSBean ossBean) {
        //todo
        ossManager = OssManager.getInstance().init(this, ENDPOINT, BUCKETNAME,
                ossBean.getAccessKeyId(),
                ossBean.getAccessKeySecret(),
                ossBean.getSecurityToken(), new OssManager.picResultCallback() {
                    @Override
                    public void getPicData(boolean b, String eTag, String serverCallbackReturnBody) {
                        //todo
                        //进行请求，把url传给后台
                        mPresenter.upHeaderPicture("1",App.cardNo,imaUrl+ imageName +".jpg");
                    }
                });

    }


    @Override
    public void imageIconData(ImgBean imgBean) {
        finish();
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

            List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);//处理
            if(localMedias.size()!=0){
                //图片的路径
                String imaUrl = localMedias.get(0).getPath();
                if(ossManager!=null && imaUrl!=null) {
                    imageName = String.valueOf(System.currentTimeMillis());
                    //进行图片压缩
                    Bitmap bitmap = BitmapFactory.decodeFile(imaUrl);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);
                    //上传到阿里云
                    ossManager.upload("rs/"+imageName, out.toByteArray());
                }
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片选择结果回调
//                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
//                    mPresenter.upHeaderPicture(App.tokenInfo.getToken(), App.tokenInfo.getCardNo(), localMedias.get(0).getCutPath());
//                    break;
//            }
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


/*    *//**
     * 上传图片后的回调
     * @param imgBean
     *//*
    @Override
    public void responseImgData(ImgBean imgBean) {
    }*/
}
