package com.example.asus.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.FanKuiBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.mvp.contract.JiaoLiuContract;
import com.example.asus.customer.mvp.presenter.JiaoLiuTouSuPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

public class FaBiaoMessageActivity extends BaseActivity<JiaoLiuTouSuPresenter> implements JiaoLiuContract.JiaoLiuTouSuView{

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
    @Bind(R.id.you_select)
    TextView mYouSelect;
    @Bind(R.id.you_Noselect)
    TextView mYouNoselect;
    @Bind(R.id.cha_select)
    TextView mChaSelect;
    @Bind(R.id.cha_Noselect)
    TextView mChaNoselect;
    @Bind(R.id.recycleView_top)
    RecyclerView mRecycleViewTop;
    @Bind(R.id.pingjia_edit)
    EditText mPingjiaEdit;
    @Bind(R.id.recycleView_bottom)
    RecyclerView mRecycleViewBottom;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private TouSuRecyclerAdapter touSuRecyclerAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    private OSSAsyncTask<PutObjectResult> task;
    private TagAdapter<String> tagAdapter;
    private ArrayList<String> titleArray = new ArrayList();
    private ArrayList<String> titleArray2 = new ArrayList<>();
    private String IsYouCha = "优";
    //递归所用
    int index = 0;
    //    用于拼接imageUrl
    private String ImageUrls = "";
    private String typeCode;
    private String type;
    private String orderno;
    private OssManager ossManager;

    @Override
    public int getLayout() {
        return R.layout.activity_fa_biao_message;
    }

    @Override
    public void initData() {
        setWindowStatusBarColor(this,R.color.colorWhite);
        mPresenter.getOssData();
        Intent intent = getIntent();
        typeCode = intent.getStringExtra("typeCode");
        type = intent.getStringExtra("type");
        orderno = intent.getStringExtra("orderno");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText("发表评价");
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText("发布");
        isYouCha();
        //选择图片
        touSuRecyclerAdapter = new TouSuRecyclerAdapter(this, arrayList);
        mRecycleViewBottom.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycleViewBottom.setAdapter(touSuRecyclerAdapter);
        touSuRecyclerAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (arrayList.size() == position) {
                    PictureSelector.create(FaBiaoMessageActivity.this)
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
                        Intent intent = new Intent(FaBiaoMessageActivity.this, ShowImageActivity.class);
                        intent.putExtra("title", "待评价");
                        intent.putExtra("context", "待评价");
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
        //发布
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s  = mPingjiaEdit.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ShowUtils.Toastshort(FaBiaoMessageActivity.this, "意见不能为空..");
                    return;
                }
                showLoading();
                //上传图片
                upLoad();
            }
        });

        tagAdapter = new TagAdapter<String>(titleArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(FaBiaoMessageActivity.this).inflate(R.layout.button, null);
                AutoUtils.auto(textView);
                textView.setBackgroundResource(R.drawable.click);
                textView.setText(s);
                return textView;
            }
        };
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
        idFlowlayout.setAdapter(tagAdapter);
        initMessage();
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param colorResId
     */
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected JiaoLiuTouSuPresenter onCreatePresenter() {
        return new JiaoLiuTouSuPresenter(this);
    }

    private void initMessage() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("code", "pj");
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/geLibrary", map1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FanKuiBean bean = JSONUtils.toObject(string, FanKuiBean.class);
                        List<String> body = bean.getBody();
                        if(body.size() > 0 && body != null){
                            titleArray.addAll(body);
                            tagAdapter.notifyDataChanged();
                        }
                    }
                });
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                //传递完毕
                dismissLoading();
                commitFinalData();
            } else if (msg.what == 11) {
                upLoad();
            } else if (msg.what == 12) {
                ShowUtils.Toastshort(FaBiaoMessageActivity.this, "请选择图片");
                dismissLoading();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(FaBiaoMessageActivity.this, "上传失败");
            }

        }
    };

    private void commitFinalData() {
        JSONObject object = new JSONObject();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < titleArray2.size(); i++) {
            if (i+1 == titleArray2.size()) {
                sb.append(titleArray2.get(i));
            } else {
                sb.append(titleArray2.get(i) + ";");
            }
        }
        try {
            object.put("rwdId",value);
            object.put("orderNo",orderno);
            object.put("type",type);
            object.put("typeCode",typeCode);
            object.put("state",IsYouCha);
            object.put("impression",sb.toString());
            object.put("msg",mPingjiaEdit.getText().toString());
            object.put("url",ImageUrls);
            object.put("flag","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkhttpUtils.post(ApiEngine.GZS_HOST + "/mainCase/saveEvaluate", object.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(string);
                            String statusMsg = object.getString("StatusMsg");
                            if(statusMsg.contains("成功")){
                                finish();
                            }
                            ToastUtil.getInstance().toastCentent(statusMsg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

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
        //进行图片压缩
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(BUCKETNAME2, getCurrentDate() + "/" + imageName + ".jpg", out.toByteArray());

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


    /**
     * 优差选择
     */
    private void isYouCha() {
        mYouNoselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouNoselect.setVisibility(View.GONE);
                mYouSelect.setVisibility(View.VISIBLE);
                mChaSelect.setVisibility(View.GONE);
                mChaNoselect.setVisibility(View.VISIBLE);
                IsYouCha = "优";
            }
        });
        mChaNoselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouNoselect.setVisibility(View.VISIBLE);
                mYouSelect.setVisibility(View.GONE);
                mChaSelect.setVisibility(View.VISIBLE);
                mChaNoselect.setVisibility(View.GONE);
                IsYouCha = "差";
            }
        });
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
}
