package com.example.asus.customer.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.mvp.contract.JiaoLiuContract;
import com.example.asus.customer.mvp.presenter.JiaoLiuTouSuPresenter;
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

import static com.example.asus.customer.api.ApiEngine.BUCKETNAME2;
import static com.example.asus.customer.api.ApiEngine.ENDPOINT;

public class JiaoLiuTouSuActivity extends BaseActivity<JiaoLiuTouSuPresenter> implements JiaoLiuContract.JiaoLiuTouSuView{

    @Bind(R.id.jiaoliu_tousu_yijian)
    EditText jiaoliuTousuYijian;
    @Bind(R.id.jiaoliu_tousu_recycler)
    RecyclerView jiaoliuTousuRecycler;
    @Bind(R.id.jiaoliu_tousu_tijiao)
    Button jiaoliuTousuTijiao;
    @Bind(R.id.scroll)
    NestedScrollView scroll;
    @Bind(R.id.tv_title)
    TextView title;
    @Bind(R.id.iv_back)
    ImageView back;
    private TouSuRecyclerAdapter touSuRecyclerAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    private OSSAsyncTask<PutObjectResult> task;
    private String s;
    private OssManager ossManager;

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
                ShowUtils.Toastshort(JiaoLiuTouSuActivity.this, "请选择图片");
                dismissLoading();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(JiaoLiuTouSuActivity.this, "上传失败");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_jiao_liu_tou_su;
    }

    @Override
    public void initData() {
        mPresenter.getOssData();
        title.setText("投诉建议");
        touSuRecyclerAdapter = new TouSuRecyclerAdapter(this, arrayList);
        jiaoliuTousuRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        jiaoliuTousuRecycler.setAdapter(touSuRecyclerAdapter);
        touSuRecyclerAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (arrayList.size() == position) {
                    PictureSelector.create(JiaoLiuTouSuActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(false)// 是否显示拍照按钮 true or false
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code 
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_PICK);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, 6);
                } else {
                    if (arrayList.size() != 0 && position != arrayList.size()) {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < arrayList.size(); i++) {
                            String filePath = arrayList.get(i);
                            jsonArray.put(filePath);
                        }
                        Intent intent = new Intent(JiaoLiuTouSuActivity.this, ShowImageActivity.class);
                        intent.putExtra("title", "投诉建议");
                        intent.putExtra("context", "投诉建议");
                        intent.putExtra("json", jsonArray.toString());
                        startActivity(intent);

                    }
                }
            }

            @Override
            public void setOnClick(int position) {
                if (arrayList.size() != 0 && position != arrayList.size()) {
                    arrayList.remove(position);
                    touSuRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
        //提交
        jiaoliuTousuTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = jiaoliuTousuYijian.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ShowUtils.Toastshort(JiaoLiuTouSuActivity.this, "意见不能为空..");
                    return;
                }
                showLoading();
                upLoad();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                        arrayList.add(path);
                    }
                    touSuRecyclerAdapter.notifyDataSetChanged();
                    break;
            }
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

    private void upLoad() {
        if (arrayList.size() <= 0) {//集合里没有图片
            handler.sendEmptyMessage(12);
            return;
        }

        if (index == arrayList.size()) {//递归上传完毕
            handler.sendEmptyMessage(10);
            return;
        }
        String filePath = arrayList.get(index);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压lica
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME2, getCurrentDate() + "/" + imageName + ".jpg", out.toByteArray());

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
                if (index == arrayList.size()) {//递归上传完毕
                    ImageUrls += name;
                } else {
                    ImageUrls += name + ",";
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


    @Override
    protected JiaoLiuTouSuPresenter onCreatePresenter() {
        return new JiaoLiuTouSuPresenter(this);
    }

    @Override
    public void getTouSuData() {

    }

    @Override
    public void getTouSuDataErro(String erro) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void OssData(OSSBean ossBean) {
        //todo
        ossManager = OssManager.getInstance().init(this, ENDPOINT, BUCKETNAME2,
                ossBean.getAccessKeyId(),
                ossBean.getAccessKeySecret(),
                ossBean.getSecurityToken(), new OssManager.picResultCallback() {
                    @Override
                    public void getPicData(boolean b, String eTag, String serverCallbackReturnBody) {
//                        //todo
//                        //进行请求，把url传给后台
//                        mPresenter.upHeaderPicture("1", App.cardNo, imaUrl + imageName + ".jpg");
                    }
                });
    }

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

    private void commitFinalData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("card_no", MySharedPreferences.getInstance().getCardNo());
        map.put("text_content", s);
        map.put("pictures", ImageUrls.toString());

        OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "actionapi/AppAdm/AddComplaint", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.e("error", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (isFinishing()) {
                                    return;
                                }
                                JSONObject jsonObject = new JSONObject(string);
                                int statusCode = jsonObject.getInt("StatusCode");
                                String statusMsg = jsonObject.getString("StatusMsg");
                                if (statusCode == 0) {
                                    jiaoliuTousuYijian.setText("");
                                    arrayList.clear();
                                    touSuRecyclerAdapter.notifyDataSetChanged();
                                    ImageUrls = "";
                                    ShowUtils.Toastshort(JiaoLiuTouSuActivity.this, "提交成功");
                                    showLoadDialog(statusMsg);
                                    //finish();
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
}
