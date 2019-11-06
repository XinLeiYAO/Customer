package com.example.asus.customer.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.SuggestAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.mvp.contract.SuggestContract;
import com.example.asus.customer.mvp.presenter.SuggestPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by asus on 2018/6/11.
 */

public class SuggestActivity extends BaseActivity<SuggestPresenter> implements SuggestContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_suggest)
    EditText etSuggest;
    @Bind(R.id.gv_suggest)
    GridView gvSuggest;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.submit)
    Button submit;


    private List<LocalMedia> imgList;

    private SuggestAdapter mAdapter;


    private int type = 1;

    @Override
    public int getLayout() {
        return R.layout.activity_suggest;
    }

    @Override
    public void initData() {
        initTitle();
        initPicture();
    }

    private void initTitle() {
        tvTitle.setText("投诉");
    }

    private void initPicture() {
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgList = new ArrayList<>();

        mAdapter = new SuggestAdapter(this, imgList);

        gvSuggest.setAdapter(mAdapter);
        gvSuggest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == imgList.size() - 1) {
                    if (imgList.size() != 0) {
                        imgList.remove(imgList.size() - 1);
                    }
                    // 进入相册 以下是例子：用不到的api可以不写
                    PictureSelector.create(SuggestActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .maxSelectNum(5)// 最大图片选择数量 int
                            .minSelectNum(1)// 最小选择数量 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .compress(true)// 是否压缩 true or fals
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .selectionMedia(imgList)
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
                } else {
                    imgList.remove(imgList.size() - 1);
                    PictureSelector.create(SuggestActivity.this).externalPicturePreview(position, imgList);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (imgList != null) {
            if (imgList.size() > 0) {
                if (!(imgList.get(imgList.size() - 1).getCompressPath() == null)) {
                    imgList.add(new LocalMedia());
                }
            } else {
                imgList.add(new LocalMedia());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    imgList.clear();
                    imgList.addAll(localMedias);
                    imgList.add(new LocalMedia());
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    @Override
    protected SuggestPresenter onCreatePresenter() {
        return new SuggestPresenter(this);
    }


    @OnClick({R.id.iv_back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.submit:

                String content = etSuggest.getText().toString().trim();
                if (content.equals("")) {
                    showToast("请输入投诉内容");
                    return;
                }
                imgList.remove(imgList.size() - 1);
                String u_name = null;
                String phone = null;

                if (App.body != null) {
                    u_name = App.body.getU_name();
                    phone = App.body.getPhone();
                }
                if (App.body != null) {

                }
                mPresenter.subSuggestInfo(u_name, phone, content, 0, App.orderNo, imgList);
                break;
        }
    }

    @Override
    public void responseSuggestData() {
        finish();
    }

    @Override
    public void responseSuggestDataError(String msg) {
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
}
