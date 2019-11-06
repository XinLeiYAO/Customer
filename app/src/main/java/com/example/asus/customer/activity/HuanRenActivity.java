package com.example.asus.customer.activity;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.mvp.contract.JiaoLiuContract;
import com.example.asus.customer.mvp.presenter.JiaoLiuHuanRenPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.asus.customer.api.ApiEngine.BUCKETNAME;

public class HuanRenActivity extends BaseActivity<JiaoLiuHuanRenPresenter> implements JiaoLiuContract.JiaoLiuHuanRenView {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.title_relative)
    RelativeLayout mTitleRelative;
    @Bind(R.id.show_popup)
    LinearLayout mShowPopup;
    @Bind(R.id.jiaoliu_lishi)
    TextView jiaoliuLishi;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @Bind(R.id.jiaoliu_tousu_yijian)
    EditText jiaoliuTousuYijian;
    @Bind(R.id.jiaoliu_tousu_recycler)
    RecyclerView jiaoliuTousuRecycler;
    @Bind(R.id.jiaoliu_tousu_tijiao)
    Button jiaoliuTousuTijiao;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;
    private TagAdapter<String> tagAdapter;
    private ArrayList<String> titleArray = new ArrayList();
    private TouSuRecyclerAdapter touSuRecyclerAdapter;
    private ArrayList<String> imageArray = new ArrayList<>();
    private OSSAsyncTask<PutObjectResult> task;
    private ArrayList<String> titleArray2 = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_huan_ren2;
    }

    @Override
    public void initData() {
        mTvTitle.setText("换设计师");
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败.
                jiaoliuTousuYijian.setText("");
//                arrayList.clear();
//                jiaoLiuShouHouAdapter.notifyDataSetChanged();
                String value = PrefUtils.getValue(HuanRenActivity.this, Constants.PN_Onumber);
                mPresenter.getJiaoLiuHuanRenListDate();
            }
        });
        //设置 Header 为 默认刷新 样式
        smart.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 关闭加载
        smart.setEnableLoadMore(false);

        mPresenter.getOssData();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        mPresenter.getJiaoLiuHuanRenListDate();
        tagAdapter = new TagAdapter<String>(titleArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(HuanRenActivity.this).inflate(R.layout.button, null);
                AutoUtils.auto(textView);
                textView.setBackgroundResource(R.drawable.click);
                textView.setText(s);
                return textView;
            }
        };
        idFlowlayout.setAdapter(tagAdapter);

        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                boolean checked = ((TagView) view).isChecked();
                String s = titleArray.get(position);
                if (checked) {
                    titleArray2.add(s);
                } else {
                    for (int i = 0; i < titleArray2.size(); i++) {

                        String title = titleArray2.get(i);
                        if (title.equals(s)) {
                            titleArray2.remove(i);
                            break;
                        }
                    }

                }
                return true;
            }
        });

        touSuRecyclerAdapter = new TouSuRecyclerAdapter(this, imageArray);
        jiaoliuTousuRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        jiaoliuTousuRecycler.setAdapter(touSuRecyclerAdapter);
        touSuRecyclerAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (imageArray.size() == position) {
                    PictureSelector.create(HuanRenActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_PICK);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, 6);
                } else {
                    if (imageArray.size() != 0 && position != imageArray.size()) {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < imageArray.size(); i++) {
                            String filePath = imageArray.get(i);
                            jsonArray.put(filePath);
                        }
                        Intent intent = new Intent(HuanRenActivity.this, ShowImageActivity.class);
                        intent.putExtra("title", "设计师意见图");
                        intent.putExtra("context", "设计师意见图");
                        intent.putExtra("json", jsonArray.toString());
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void setOnClick(int position) {
                if (imageArray.size() != 0 && position != imageArray.size()) {
                    imageArray.remove(position);
                    touSuRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
        //历史
        jiaoliuLishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HuanRenActivity.this, HuanRenLiShiActivity.class));
            }
        });
        //交流投诉提交
        jiaoliuTousuTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MIN_DELAY_TIME = 3000;
                boolean fastClick = isFastClick();
                if (fastClick) {
                    ShowUtils.Toastshort(HuanRenActivity.this, "请不要频繁点击");
                } else {
                    index = 0;
                    showLoading();
                    upLoad();
                }
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 照片预览
     *
     * @param url
     */
    public void photoPreview(ArrayList<String> url) {
        List<LocalMedia> list = new ArrayList<>();
        for (int i = 0; i < url.size(); i++) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath(url.get(i));
            list.add(localMedia);
        }
        PictureSelector.create(this).externalPicturePreview(0, list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia localMedia : localMedias) {
                        String path = localMedia.getPath();
                        imageArray.add(path);
                    }

//                    imageAddAdapter = new ImageAddAdapterJie(list_img, TouSuActivity.this);
//                    gvImg.setAdapter(imageAddAdapter);
                    touSuRecyclerAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    //递归所用
    int index = 0;
    //用于拼接imageUrl
    private String upImageUrl = "";

    private void upLoad() {
        if (imageArray.size() <= 0) {//集合里没有图片
            handler.sendEmptyMessage(12);
            return;
        }

        if (index == imageArray.size()) {//递归上传完毕
            handler.sendEmptyMessage(10);
            return;
        }

        String filePath = imageArray.get(index);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压缩
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME, "ProjectBusiness/Images/" + imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.OSS_UPURL + "ProjectBusiness/Images/" + imageName + ".jpg";

                //上传成功 index加1
                index += 1;
                if (index == imageArray.size()) {//递归上传完毕
                    upImageUrl += name;
                } else {
                    upImageUrl += name + ",";
                }
                handler.sendEmptyMessage(11);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                index += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (index + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                dismissLoading();
                String content = jiaoliuTousuYijian.getText().toString();
                String value = PrefUtils.getValue(HuanRenActivity.this, Constants.PN_Onumber);
                JSONObject jsonObject = new JSONObject();
                JSONObject o = new JSONObject();

                try {
                    StringBuffer sb = new StringBuffer();

                    //转json
                    for (int i = 0; i < titleArray2.size(); i++) {
                        sb.append(titleArray2.get(i) + ",");
                    }
                    String designerId = PrefUtils.getValue(HuanRenActivity.this, Constants.ci_designerCard);
                    String designerName = PrefUtils.getValue(HuanRenActivity.this, Constants.ci_designerName);
                    o.put("reason", sb.toString());
                    o.put("content", content);
                    o.put("designerId", designerId);
                    o.put("designerName", designerName);
                    jsonObject.put("content", o.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("rwdid", value);
                map.put("feedbackType", "SJS");
                map.put("imgUrl", upImageUrl);
                map.put("content", o.toString());
                mPresenter.getJiaoLiuHuanRenDate(map);

                upImageUrl = "";
                titleArray2.clear();
                //清空当前页 重新请求
                mPresenter.getJiaoLiuHuanRenListDate();
                imageArray.clear();
                jiaoliuTousuYijian.setText("");
                touSuRecyclerAdapter.notifyDataSetChanged();
            } else if (msg.what == 11) {
                upLoad();
            } else if (msg.what == 12) {
                ShowUtils.Toastshort(HuanRenActivity.this, "请选择图片");
                dismissLoading();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(HuanRenActivity.this, "上传失败");
            }

        }
    };

    @Override
    protected JiaoLiuHuanRenPresenter onCreatePresenter() {
        return new JiaoLiuHuanRenPresenter(this);
    }

    @Override
    public void getHuanRenDate(String msg) {
        ShowUtils.Toastshort(HuanRenActivity.this, msg);
        if(msg.contains("成功")){
            finish();
        }

    }

    @Override
    public void getHuanRenDateErro(String erro) {
        ShowUtils.Toastshort(HuanRenActivity.this, erro);

    }

    @Override
    public void OssData(OSSBean ossBean) {

    }

    @Override
    public void getHuanRenListData(JSONArray array) {
        titleArray.clear();
        for (int i = 0; i < array.length(); i++) {
            try {
                String string = array.getString(i);
                titleArray.add(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tagAdapter.notifyDataChanged();
    }

    @Override
    public void getHuanRenListDataErro(String erro) {
        ShowUtils.Toastshort(HuanRenActivity.this, erro);

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
