package com.example.asus.customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.UserInfoBean;
import com.example.asus.customer.mvp.contract.UpdUserInfoContract;
import com.example.asus.customer.mvp.presenter.UpdUserInfoPresenter;
import com.example.asus.customer.weight.RoundAngleImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserInfoActivity extends BaseActivity<UpdUserInfoPresenter> implements UpdUserInfoContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.riv_head_photo)
    RoundAngleImageView rivHeadPhoto;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_gender)
    TextView tvGender;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_mailbox)
    TextView tvMailbox;
    @Bind(R.id.tv_area)
    TextView tvArea;

    public static final String TITLE = "基本信息";
    @Bind(R.id.tv_ewm)
    ImageView tvEwm;

    private DatePicker picker;

    //性别
    List<String> sexList;
    //性别
    private OptionsPickerView sexPicker;

    private String mSex;

    private String time;

    private String key;

    private String path;
    private String image;
    private Dialog alertDialog;
    private String ewm;

    @Override
    public int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initData() {
        initTitle();
        initUpData();
        initSexData();
    }

    private void initTitle() {
        tvTitle.setText(TITLE);
    }

    private void initUpData() {
        //初始化时间选择器
        picker = new DatePicker(this);
        picker.setRange(1900, 2100);
    }

    private void initSexData() {

        sexList = new ArrayList<>();

        sexList.add("男");
        sexList.add("女");

        sexPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                key = "sex";
                tvGender.setText(sexList.get(options1));
                mSex = sexList.get(options1);
                String cardNo = App.tokenInfo.getCardNo();
                String token = App.tokenInfo.getToken();
                mPresenter.updateUserInfo(cardNo, token, "sex", mSex);
            }
        }).build();

        sexPicker.setPicker(sexList);

    }

    private void initUserInfo() {
        String cardNo = App.tokenInfo.getCardNo();
        String token = App.tokenInfo.getToken();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("cardNo",cardNo);
        hashMap.put("token",token);
        OkhttpUtils.doPost(ApiEngine.INFORMATION, hashMap, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ShowUtils.Toastshort(UserInfoActivity.this, e.getMessage());
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UserInfoBean userInfoBean = JSONUtils.toObject(string, UserInfoBean.class);
                        if (userInfoBean.getStatusCode() == 0) {
                            List<UserInfoBean.BodyBean> body = userInfoBean.getBody();
                            UserInfoBean.BodyBean bodyBean = body.get(0);
                            tvName.setText(bodyBean.getU_name() == null ? "点击添加" : bodyBean.getU_name());
                            tvGender.setText(bodyBean.getSex() == null ? "点击添加" : bodyBean.getSex());
                            tvBirthday.setText(bodyBean.getBirthday_txt() == "" ? "点击添加" : bodyBean.getBirthday_txt());
                            tvPhone.setText(bodyBean.getPhone() == null ? "点击添加" : bodyBean.getPhone());
                            tvMailbox.setText(bodyBean.getEmail() == null ? "点击添加" : bodyBean.getEmail());


                            image = bodyBean.getImage();
                            ewm = bodyBean.getEwm();
                            RequestOptions options = new RequestOptions();
                            options.placeholder(R.mipmap.userimage);
                            options.error(R.mipmap.userimage);
                            Glide.with(UserInfoActivity.this).load(bodyBean.getImage()).apply(options).into(rivHeadPhoto);

                        } else {
                            showToast(userInfoBean.getStatusMsg());
                        }
                    }
                });
            }
        });


    }

    private void startUpdUserInfo(String keyValue, String key, String value) {
        Intent nickName = new Intent(this, UpdUserInfoActivity.class);
        nickName.putExtra(Constants.ACTION_TO_UPD_USER_INFO_KEY_VALUE, keyValue);
        nickName.putExtra(Constants.ACTION_TO_UPD_USER_INFO_KEY, key);
        nickName.putExtra(Constants.ACTION_TO_UPD_USER_INFO_VALUE, value == null ? "" : value);
        startActivity(nickName);
    }

    @Override
    protected UpdUserInfoPresenter onCreatePresenter() {
        return new UpdUserInfoPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        initUserInfo();
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    @OnClick({R.id.iv_back, R.id.riv_head_photo, R.id.Fullname, R.id.tv_gender_sex, R.id.lv_birthday, R.id.tv_phone, R.id.lv_mailbox, R.id.tv_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.riv_head_photo:
                // 进入相册 以下是例子：用不到的api可以不写
//                PictureSelector.create(this)
//                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                        .imageSpanCount(3)// 每行显示个数 int
//                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
//                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
//                        .enableCrop(true)// 是否裁剪 true or false
//                        .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
//                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
                ArrayList<String> mlist=new ArrayList<>();
                mlist.add(image);
              startActivity(new Intent(this, UpUpdaPhotoImageActivity.class).putStringArrayListExtra(Constants.JUMPLIST,mlist));
                break;
            case R.id.Fullname:
                startUpdUserInfo("姓名", "u_name", tvName.getText().toString());
                break;
            case R.id.tv_gender_sex:
                sexPicker.show();
                break;
            case R.id.lv_birthday:
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        key = "birthday";
                        time = year + "-" + month + "-" + day;
                        tvBirthday.setText(time);
                        mPresenter.updateUserInfo(App.tokenInfo.getToken(), App.tokenInfo.getCardNo(), "birthday", time);
                    }
                });
                picker.show();
                break;
            case R.id.tv_phone:
                break;
            case R.id.lv_mailbox:
                startUpdUserInfo("邮箱", "email",tvMailbox.getText().toString());
                break;
            case R.id.tv_area:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    path = localMedias.get(0).getCutPath();
//                    mPresenter.upHeaderPicture(App.token, App.cardNo, path);
                    break;
            }
        }
    }

    @Override
    public void responseUpdateData() {
//        switch (key) {
//            case "birthday":
//                App.baseInfo.setBirthday(time);
//                break;
//            case "sex":
//                App.baseInfo.setSex(mSex);
//                break;
//        }
        showToast("上传成功");
    }

    @Override
    public void responseUpdateDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseUpPicture() {
//        App.baseInfo.setImage(path);
//        RequestOptions options = new RequestOptions();
//        options.placeholder(R.mipmap.head_portrait_icon);
//        options.error(R.mipmap.head_portrait_icon);
//        Glide.with(this).load(App.baseInfo.getImage()).apply(options).into(rivHeadPhoto);
    }

    @Override
    public void responseUpPictureError(String msg) {
        showToast(msg);
    }

    @Override
    public void reLogin(String msg) {
        showToast(msg);
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);
//        PrefUtils.RemoveValue(this, Constants.CARD_NO);
//        PrefUtils.RemoveValue(this, Constants.TOKEN);
        startActivity(new Intent(this, TextLoginActivity.class));
        finish();
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @OnClick(R.id.rl_user_ewm)
    public void onViewClicked() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,R.style.newPassword);
        View inflate = getLayoutInflater().inflate(R.layout.dialog_erweima, null);
        ImageView imageView= (ImageView) inflate.findViewById(R.id.user_ewm);
        Glide.with(this).load(ewm).into(imageView);
        AutoUtils.setSize(this, false, 720, 1280);
        AutoUtils.auto(inflate);
        dialog.setView(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = dialog.create();
        alertDialog.show();
    }
}
