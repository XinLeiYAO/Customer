package com.example.asus.customer.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.ImageAddAdapterJie;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.entity.GuWenShangchuanInfo;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.weight.MyGridView;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.asus.customer.api.ApiEngine.BUCKETNAME;
import static com.example.asus.customer.api.ApiEngine.BUCKETNAME2;
import static com.example.asus.customer.api.ApiEngine.ENDPOINT;
import static com.example.asus.customer.api.ApiEngine.OSS_DATA_BASU;

/**
 * 投诉建议
 */
public class TouSuActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_tousu)
    EditText etTousu;
    @Bind(R.id.btn_commit)
    Button btnCommit;
    @Bind(R.id.jiaoliu_tousu_recycler)
    RecyclerView jiaoliu_tousu_recycler;
    ArrayList<String> list_img;
    private String s;
    private TouSuRecyclerAdapter touSuRecyclerAdapter;
    private OSSAsyncTask<PutObjectResult> task;

    @Override
    public int getLayout() {
        return R.layout.activity_tousu;
    }

    @Override
    public void initData() {
        initOssData();
        ivBack.setVisibility(View.VISIBLE);
        list_img = new ArrayList<>();
        touSuRecyclerAdapter = new TouSuRecyclerAdapter(TouSuActivity.this, list_img);
        jiaoliu_tousu_recycler.setLayoutManager(new GridLayoutManager(this, 3));
        jiaoliu_tousu_recycler.setAdapter(touSuRecyclerAdapter);

        tvTitle.setText("投诉建议");
        ivBack.setOnClickListener(this);
        btnCommit.setOnClickListener(this);

        touSuRecyclerAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (list_img.size() == position) {
                    PictureSelector.create(TouSuActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
                } else {
                    if (list_img.size() != 0 && position != list_img.size()) {
                        photoPreview(list_img.get(position));
                    }
                }

            }

            @Override
            public void setOnClick(int position) {
                if (list_img.size() != 0 && position != list_img.size()) {
                    list_img.remove(position);
                    touSuRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 图片选择结果回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia localMedia : localMedias) {
                        String path = localMedia.getPath();
                        list_img.add(path);
                    }
                    touSuRecyclerAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_commit:
                s = etTousu.getText().toString().trim();
                if (StringUtils.isEmpty(s)) {
                    showToast("请输入投诉或建议内容");
                    return;
                }
                if (s.length() < 10) {
                    showToast("内容少于10个字");
                    return;
                }
//                commitData();
                showLoading();
                upLoad();
                break;
        }
    }


    //递归所用
    int index = 0;
    //    用于拼接imageUrl
    private String ImageUrls = "";

    public String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * 上传图片
     */
    private void upLoad() {
        if (list_img.size() <= 0) {//集合里没有图片
            handler.sendEmptyMessage(12);
            return;
        }

        if (index == list_img.size()) {//递归上传完毕
            handler.sendEmptyMessage(10);
            return;
        }

        String filePath = list_img.get(index);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压缩
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(BUCKETNAME, getCurrentDate() + "/" + imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.IMA_URL + getCurrentDate() + "/" + imageName + ".jpg";
                //上传成功 index加1
                index += 1;
                if (index == list_img.size()) {//递归上传完毕
                    ImageUrls += name;
                } else {
                    ImageUrls += name + ";";
                }
                handler.sendEmptyMessage(11);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                index += 1;
                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (index + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                dismissLoading();
                commitFinalData();
            } else if (msg.what == 11) {
                upLoad();
            } else if (msg.what == 12) {
                ShowUtils.Toastshort(TouSuActivity.this, "请选择图片");
                dismissLoading();
            } else if (msg.what == 13) {
                ShowUtils.Toastshort(TouSuActivity.this, "上传失败");
            }
        }
    };

    /**
     *  初始化oss数据
     */
    private void initOssData() {

        OkhttpUtils.doGet(OSS_DATA_BASU, null, new Callback() {

            private OssManager ossManager;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                OSSBean ossBean = JSONUtils.toObject(string, OSSBean.class);
                //todo
                ossManager = OssManager.getInstance().init(TouSuActivity.this, ENDPOINT, BUCKETNAME2,
                        ossBean.getAccessKeyId(),
                        ossBean.getAccessKeySecret(),
                        ossBean.getSecurityToken(), new OssManager.picResultCallback() {
                            @Override
                            public void getPicData(boolean b, String eTag, String serverCallbackReturnBody) {

                            }
                        });
            }
        });

    }

    /**
     * 提交反馈数据
     */
    private void commitFinalData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("card_no", MySharedPreferences.getInstance().getCardNo());
        map.put("text_content", s);
        map.put("pictures", ImageUrls.toString());

        OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "actionapi/AppAdm/AddComplaint", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            String statusMsg = jsonObject.getString("StatusMsg");
                            if (statusCode == 0) {
                                showLoadDialog(statusMsg);
//                                finish();

                            } else {
                                showToast(statusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 显示提示dialog
     * @param errmsg
     */
    private void showLoadDialog(String errmsg) {
        AlertDialog button = new AlertDialog.Builder(this).setMessage(errmsg).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        }).create();
        button.show();
        button.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00a0ea"));

        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(button);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(getResources().getColor(R.color.cor666));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 照片预览
     */
    public void photoPreview(String url) {
        com.luck.picture.lib.entity.LocalMedia localMedia = new com.luck.picture.lib.entity.LocalMedia();
        localMedia.setPath(url);
        List<LocalMedia> list = new ArrayList<>();
        list.add(localMedia);
        PictureSelector.create(this).externalPicturePreview(0, list);
    }
}
