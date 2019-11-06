package com.example.asus.customer.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.ImgBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.mvp.contract.UpdUserInfoContract;
import com.example.asus.customer.mvp.presenter.UpdUserInfoPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 更新用户信息
 */
public class UpdUserInfoActivity extends BaseActivity<UpdUserInfoPresenter> implements UpdUserInfoContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_keyword)
    TextView tvKeyword;
    @Bind(R.id.et_upd_content)
    EditText etUpdContent;

    private String title;
    private String keyValue;
    private String key;
    private String value;
    private String updContent;

    @Override
    public int getLayout() {
        return R.layout.activity_upd_user_info;
    }

    @Override
    public void initData() {
        initIntent();
        initTitle();
        initUpdData();
    }

    private void initIntent() {
        keyValue = getIntent().getStringExtra(Constants.ACTION_TO_UPD_USER_INFO_KEY_VALUE);
        key = getIntent().getStringExtra(Constants.ACTION_TO_UPD_USER_INFO_KEY);
        value = getIntent().getStringExtra(Constants.ACTION_TO_UPD_USER_INFO_VALUE);
    }

    private void initTitle() {
        tvTitle.setText("昵称");
    }

    private void initUpdData() {
        tvKeyword.setText(keyValue);
        etUpdContent.setHint("请输入" + keyValue);

        etUpdContent.setText(value);
    }

    @Override
    protected UpdUserInfoPresenter onCreatePresenter() {
        return new UpdUserInfoPresenter(this);
    }


    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                updContent = etUpdContent.getText().toString().trim();
                if (updContent.equals("")) {
                    showToast("请输入修改内容");
                    return;
                }
                if (updContent.equals(value)) {
                    showToast("修改内容不能与原内容相同");
                    return;
                }
                String cardNo = PrefUtils.getValue(this, Constants.PHOME);
                String token = PrefUtils.getValue(this, Constants.PASSWORD);

                //修改用户信息
                mPresenter.updateUserInfo(token,cardNo, key, updContent);
                break;
                default:
                    break;
        }
    }

    @Override
    public void responseUpdateData() {
        showToast("修改成功");
        finish();
    }

    @Override
    public void responseUpdateDataError(String msg) {
        showToast(msg);
    }

    @Override
    public void responseUpPicture() {

    }

    @Override
    public void responseUpPictureError(String msg) {

    }

    @Override
    public void reLogin(String msg) {
        showToast(msg);
        PrefUtils.RemoveValue(this, Constants.IS_LOGIN);

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

    @Override
    public void OssData(OSSBean ossBean) {

    }

    @Override
    public void imageIconData(ImgBean imgBean) {

    }
}
