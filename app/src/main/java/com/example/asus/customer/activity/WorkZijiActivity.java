package com.example.asus.customer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.asus.customer.R;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.GetMessageGongBean;
import com.example.asus.customer.entity.GetYuYueMessageBean;
import com.example.asus.customer.entity.OSSBean;
import com.example.asus.customer.entity.xvqiuMessageBean;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.asus.customer.api.ApiEngine.BUCKETNAME2;
import static com.example.asus.customer.api.ApiEngine.ENDPOINT;

public class WorkZijiActivity extends BaseActivity<JiaoLiuTouSuPresenter> implements JiaoLiuContract.JiaoLiuTouSuView {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tvStyle)
    TextView tvStyle;
    @Bind(R.id.gongnnegRecycleView)
    TagFlowLayout gongnengRecycleview;
    @Bind(R.id.huxingRecycleView)
    RecyclerView huxingRecycleView;
    @Bind(R.id.xianchangRecycleView)
    RecyclerView xianchangRecycleView;
    @Bind(R.id.dianxiangRecycleView)
    RecyclerView dianxiangRecycleView;
    @Bind(R.id.shuiguanRecycleview)
    RecyclerView shuiguanRecycleview;
    @Bind(R.id.btnCommit)
    Button btn_commit;
    @Bind(R.id.ed_ydmg)
    EditText ed_ydmg;
    @Bind(R.id.ed_zlxg)
    EditText ed_zlxg;
    @Bind(R.id.ed_clxg)
    EditText ed_clxg;
    private ArrayList<String> gongnengList = new ArrayList();
    private ArrayList<String> gongnengSelectList = new ArrayList();
    private ArrayList<String> huxingList = new ArrayList();
    private ArrayList<String> getHuxingList = new ArrayList();
    private ArrayList<String> xianchangList = new ArrayList();
    private ArrayList<String> getXianchangList = new ArrayList();
    private ArrayList<String> dianxiangList = new ArrayList();
    private ArrayList<String> getDianxiangList = new ArrayList();
    private ArrayList<String> shuiguanList = new ArrayList();
    private ArrayList<String> getShuiguanList = new ArrayList();
    private int huxingTag = 1;
    private int xianchangTag = 2;
    private int dianxiangTag = 3;
    private int shuiguanTag = 4;


    private OssManager ossManager;
    private OptionsPickerView styleOptionsPickerView;
    private TagAdapter tagAdapter;
    private TouSuRecyclerAdapter huxingAdapter;
    private TouSuRecyclerAdapter xianchangAdapter;
    private TouSuRecyclerAdapter dianxiangAdapter;
    private TouSuRecyclerAdapter shuiguanAdapter;
    private String type;
    private String typeEr;
    private String typeName;
    private String typeNameEr;
    private String huxing;
    private String huxingName;
    private Intent intent;
    private String area;
    private String city;
    private String time;
    private String address;
    private String cityName;
    private int huxingIndex = 0;
    private String huXingStr = "";
    private String getHuXingStr = "";
    private int xianchangIndex = 0;
    private String xianchangStr = "";
    private String getXianchangStr = "";
    private int dianxiangIndex = 0;
    private String dianxiangStr = "";
    private String getDianxiangStr = "";
    private int shuigunaIndex = 0;
    private String shuiguanStr = "";
    private String getShuiguanStr = "";
    private String xvQiuStr = "";
    private int money = 0;
    private int tagXqNum = 0;
    private boolean boolXq = false;
    private int tagHxNum = 0;
    private int tagXcNum = 0;
    private int tagDxNum = 0;
    private int tagSgNum = 0;
    private int tagJzNum = 0;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {//户型完成，开始上传现场
                upLoadXianChang();
            } else if (msg.what == 11) {//上传户型
                upLoadHuXing();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(WorkZijiActivity.this, "上传失败");
            } else if (msg.what == 8) {//上传现场
                upLoadXianChang();
            } else if (msg.what == 9) {//现场完成,开始上传电箱
                upLoadDianXiang();
            } else if (msg.what == 7) {//电箱上传完成
                upLoadShuiGuan();
            } else if (msg.what == 6) { //上传电箱
                upLoadDianXiang();
            } else if (msg.what == 1) { //上传水管
                upLoadShuiGuan();
            } else if (msg.what == 2) {//水管上传完毕
//                initDialog();
                initBottomDialog();
                dismissLoading();
            }

        }
    };
    private TextView tv_sure;
    private String tag;
    private String id;
    private List<String> xuQiuNames;
    private Set<Integer> set = new HashSet<>();
    private String url;
    private String messaegPhone;
    private String customerName;

    String userPhone =null;
    String userName =null;
    String siteText =null;
    String siteid =null;
    private String Leixing;
    private String Huxing;
    private String title;
    private TextView tvSitetext;
    @Override
    public int getLayout() {
        return R.layout.activity_work_ziji;
    }

    @Override
    public void initData() {
        addActivity(this);
        initStyle();
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        siteText = getIntent().getStringExtra("siteText");
        Leixing = getIntent().getStringExtra("Leixing");
        Huxing = getIntent().getStringExtra("Huxing");
        initTypeMessage(type);
        getIntentMessage();
        if (tag.equals("1")) {
            initMessage();

            siteText = getIntent().getStringExtra("siteText");
            siteid = getIntent().getStringExtra("siteId");
        }
        mPresenter.getOssData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("自己量房");
        tvStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                closeInput();
                styleOptionsPickerView.show();
            }
        });
        //功能需求
        tagAdapter = new TagAdapter<String>(gongnengList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(WorkZijiActivity.this).inflate(R.layout.button, null);
                AutoUtils.auto(textView);
                textView.setBackgroundResource(R.drawable.click);
                textView.setText(s);
                return textView;
            }
        };
        gongnengRecycleview.setAdapter(tagAdapter);
        gongnengRecycleview.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                boolean checked = ((TagView) view).isChecked();

                String s = gongnengList.get(position);
                if (checked) {
                    gongnengSelectList.add(s);
                } else {
                    for (int i = 0; i < gongnengSelectList.size(); i++) {
                        String title = gongnengSelectList.get(i);
                        if (title.equals(s)) {
                            gongnengSelectList.remove(i);
                            break;
                        }
                    }

                }
                countMoneyXvqiu();
                tagXqNum++;
                return true;
            }
        });

        //户型图纸
        huxingAdapter = new TouSuRecyclerAdapter(this, huxingList);
        huxingRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        huxingRecycleView.setAdapter(huxingAdapter);
        huxingAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (huxingList.size() == position) {
                    PictureSelector.create(WorkZijiActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(huxingTag);//结果回调onActivityResult code 
                }
            }

            @Override
            public void setOnClick(int position) {
                if (huxingList.size() != 0 && position != huxingList.size()) {
                    huxingList.remove(position);
                    if (tag.equals("1") && position <= getHuxingList.size() - 1) {
                        getHuxingList.remove(position);
                    }
                } else {
                    huxingList.clear();
                }
                countHuXing();
                huxingAdapter.notifyDataSetChanged();
            }
        });

        //现场环拍
        xianchangAdapter = new TouSuRecyclerAdapter(this, xianchangList);
        xianchangRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        xianchangRecycleView.setAdapter(xianchangAdapter);
        xianchangAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (xianchangList.size() == position) {
                    PictureSelector.create(WorkZijiActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(xianchangTag);//结果回调onActivityResult code 
                }
            }

            @Override
            public void setOnClick(int position) {
                if (xianchangList.size() != 0 && position != xianchangList.size()) {
                    xianchangList.remove(position);
                    if (tag.equals("1") && position <= getXianchangList.size() - 1) {
                        getXianchangList.remove(position);
                    }
                } else {
                    xianchangList.clear();
                }
                countXianChang();
                xianchangAdapter.notifyDataSetChanged();
            }
        });

        //电箱位置
        dianxiangAdapter = new TouSuRecyclerAdapter(this, dianxiangList);
        dianxiangRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        dianxiangRecycleView.setAdapter(dianxiangAdapter);
        dianxiangAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (dianxiangList.size() == position) {
                    PictureSelector.create(WorkZijiActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(dianxiangTag);//结果回调onActivityResult code 
                }
            }

            @Override
            public void setOnClick(int position) {
                if (dianxiangList.size() != 0 && position != dianxiangList.size()) {
                    dianxiangList.remove(position);
                    if (tag.equals("1") && position <= getDianxiangList.size() - 1) {
                        getDianxiangList.remove(position);
                    }
                } else {
                    dianxiangList.clear();
                }
                countDianXiang();
                dianxiangAdapter.notifyDataSetChanged();
            }
        });

        //水管
        shuiguanAdapter = new TouSuRecyclerAdapter(this, shuiguanList);
        shuiguanRecycleview.setLayoutManager(new GridLayoutManager(this, 4));
        shuiguanRecycleview.setAdapter(shuiguanAdapter);
        shuiguanAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (shuiguanList.size() == position) {
                    PictureSelector.create(WorkZijiActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(shuiguanTag);//结果回调onActivityResult code 
                }
            }

            @Override
            public void setOnClick(int position) {
                if (shuiguanList.size() != 0 && position != shuiguanList.size()) {
                    shuiguanList.remove(position);
                    if (tag.equals("1") && position <= getShuiguanList.size() - 1) {
                        getShuiguanList.remove(position);
                    }
                } else {
                    shuiguanList.clear();
                }
                countShuiGuan();
                shuiguanAdapter.notifyDataSetChanged();
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < gongnengSelectList.size(); i++) {
                    String s = gongnengSelectList.get(i);
                    if (i == gongnengSelectList.size() - 1) {
                        xvQiuStr += s;
                    } else {
                        xvQiuStr += s + ",";
                    }
                }

                if (tag.equals("1")) {
                    for (int i = 0; i < getHuxingList.size(); i++) {
                        String imgString = getHuxingList.get(i);
                        for (int j = 0; j < huxingList.size(); j++) {
                            if (imgString.equals(huxingList.get(j))) {
                                huxingList.remove(j);
                            }
                        }
                    }

                    for (int i = 0; i < getXianchangList.size(); i++) {
                        String imgString = getXianchangList.get(i);
                        for (int j = 0; j < xianchangList.size(); j++) {
                            if (imgString.equals(xianchangList.get(j))) {
                                xianchangList.remove(j);
                            }
                        }
                    }


                    for (int i = 0; i < getDianxiangList.size(); i++) {
                        String imgString = getDianxiangList.get(i);
                        for (int j = 0; j < dianxiangList.size(); j++) {
                            if (imgString.equals(dianxiangList.get(j))) {
                                dianxiangList.remove(j);
                            }
                        }
                    }

                    for (int i = 0; i < getShuiguanList.size(); i++) {
                        String imgString = getShuiguanList.get(i);
                        for (int j = 0; j < shuiguanList.size(); j++) {
                            if (imgString.equals(shuiguanList.get(j))) {
                                shuiguanList.remove(j);
                            }
                        }
                    }
                }
                upLoadHuXing();
            }
        });
        ed_ydmg.addTextChangedListener(new MyListen());
        ed_zlxg.addTextChangedListener(new MyListen());
        ed_clxg.addTextChangedListener(new MyListen());
    }


    private void getIntentMessage() {
        Intent intent = getIntent();
        typeEr = intent.getStringExtra("typeEr");
        typeName = intent.getStringExtra("typeName");
        typeNameEr = intent.getStringExtra("typeNameEr");
        huxing = intent.getStringExtra("huxing");
        huxingName = intent.getStringExtra("huxingName");
        area = intent.getStringExtra("area");
        city = intent.getStringExtra("city");
        time = intent.getStringExtra("time");
        address = intent.getStringExtra("address");
        cityName = intent.getStringExtra("cityName");
        tag = intent.getStringExtra("tag");
        id = intent.getStringExtra("id");
    }

    private void initTypeMessage(String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("workType", type);
        OkhttpUtils.doGet(ApiEngine.FS_API_HOST + "/api/program/getLibraryListByType", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.getInstance().toastCentent("网络失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            xvqiuMessageBean bean = JSONUtils.toObject(string, xvqiuMessageBean.class);
                            if (bean.getStatusCode() == 0) {
                                List<String> body = bean.getBody();
                                gongnengList.addAll(body);
                                if (tag.equals("1") && xuQiuNames != null && xuQiuNames.size() > 0) {
                                    for (int i = 0; i < gongnengList.size(); i++) {
                                        String gongneng = gongnengList.get(i);
                                        for (int j = 0; j < xuQiuNames.size(); j++) {
                                            if (gongneng.equals(xuQiuNames.get(j))) {
                                                set.add(i);
                                            }
                                        }
                                    }
                                }
                                tagAdapter.setSelectedList(set);
                                tagAdapter.notifyDataChanged();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initStyle() {
        final ArrayList<String> styleList = new ArrayList<>();
        styleList.add("现代简约");
        styleList.add("简约风格");
        styleList.add("新中式");
        styleList.add("LOFT");
        styleList.add("工业风格");
        styleList.add("中式风格");
        styleList.add("港式风格");
        styleList.add("日式风格");
        styleList.add("田园风格");
        styleList.add("东南亚");
        styleList.add("后现代");
        styleList.add("地中海");
        styleList.add("其他");
        //创建选择器
        styleOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvStyle.setText(styleList.get(options1));
//                countMoneyJiben();
            }
        }).build();
        styleOptionsPickerView.setPicker(styleList);
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
        ossManager = OssManager.getInstance().init(this, ENDPOINT, BUCKETNAME2,
                ossBean.getAccessKeyId(),
                ossBean.getAccessKeySecret(),
                ossBean.getSecurityToken(), new OssManager.picResultCallback() {
                    @Override
                    public void getPicData(boolean b, String eTag, String serverCallbackReturnBody) {
                    }
                });
    }

    private void upLoadHuXing() {
        showLoading();

        if (huxingIndex == huxingList.size()) {//递归上传完毕
            handler.sendEmptyMessage(10);
            return;
        }
        String filePath = huxingList.get(huxingIndex);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压lica
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME, imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask<PutObjectResult> task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.OSS_UPURL + imageName + ".jpg";
                //上传成功 index加1
                huxingIndex += 1;
                if (huxingIndex == huxingList.size()) {//递归上传完毕
                    huXingStr += name;
                } else {
                    huXingStr += name + ",";
                }
                handler.sendEmptyMessage(11);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                huxingIndex += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (huxingIndex + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private void upLoadXianChang() {
        showLoading();

        if (xianchangIndex == xianchangList.size()) {//递归上传完毕
            handler.sendEmptyMessage(9);
            return;
        }
        String filePath = xianchangList.get(xianchangIndex);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压lica
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME, imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask<PutObjectResult> task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.OSS_UPURL + imageName + ".jpg";
                //上传成功 index加1
                xianchangIndex += 1;
                if (xianchangIndex == xianchangList.size()) {//递归上传完毕
                    xianchangStr += name;
                } else {
                    xianchangStr += name + ",";
                }
                handler.sendEmptyMessage(8);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                xianchangIndex += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (xianchangIndex + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private void upLoadDianXiang() {
        showLoading();

        if (dianxiangIndex == dianxiangList.size()) {//递归上传完毕
            handler.sendEmptyMessage(7);
            return;
        }
        String filePath = dianxiangList.get(dianxiangIndex);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压lica
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME, imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask<PutObjectResult> task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.OSS_UPURL + imageName + ".jpg";
                //上传成功 index加1
                dianxiangIndex += 1;
                if (dianxiangIndex == dianxiangList.size()) {//递归上传完毕
                    dianxiangStr += name;
                } else {
                    dianxiangStr += name + ",";
                }
                handler.sendEmptyMessage(6);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                dianxiangIndex += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (dianxiangIndex + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private void upLoadShuiGuan() {
        showLoading();

        if (shuigunaIndex == shuiguanList.size()) {//递归上传完毕
            handler.sendEmptyMessage(2);
            return;
        }
        String filePath = shuiguanList.get(shuigunaIndex);
        OSS oss = OssManager.getInstance().getOss();
        final long imageName = System.currentTimeMillis();
        //进行图片压lica
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
        PutObjectRequest put = new PutObjectRequest(ApiEngine.BUCKETNAME, imageName + ".jpg", out.toByteArray());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask<PutObjectResult> task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String name = ApiEngine.OSS_UPURL + imageName + ".jpg";
                //上传成功 index加1
                shuigunaIndex += 1;
                if (shuigunaIndex == shuiguanList.size()) {//递归上传完毕
                    shuiguanStr += name;
                } else {
                    shuiguanStr += name + ",";
                }
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                shuigunaIndex += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (shuigunaIndex + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private void initDialog() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_yuyue, null);
        AutoUtils.auto(inflate);
        final EditText ed_name = inflate.findViewById(R.id.edName);
        final EditText ed_phone = inflate.findViewById(R.id.edPhone);
        TextView tv_cancel = inflate.findViewById(R.id.tvCancel);
        tv_sure = inflate.findViewById(R.id.tvSure);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(WorkZijiActivity.this, R.style.newPassword);
        builder.setCancelable(false);// 设置点击屏幕Dialog不消失
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        //获取当前屏幕的宽高
        int width = display.getWidth();
        int height = display.getHeight();
        //设置dialog的宽高为屏幕的宽高
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
        alertDialog.setContentView(inflate, layoutParams);

        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        if (alertDialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
//                arrayList.addAll(getMessageList);
            }
        });
        if (tag.equals("1")) {
            ed_phone.setEnabled(false);
            ed_phone.setText(messaegPhone + "");
            ed_name.setText(customerName + "");
        }
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ed_name.getText().toString()) && TextUtils.isEmpty(ed_phone.getText().toString())) {
                    toastShow("姓名手机号不可为空");
                    return;
                }
                String cardNo = App.cardNo;
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(WorkZijiActivity.this, TextLoginActivity.class)
                            .putExtra("phone", ed_phone.getText().toString())
                    );

                } else {
                    tv_sure.setEnabled(false);
//                    saveMessage(ed_name.getText().toString(), ed_phone.getText().toString());
                }
            }
        });
    }

    private void saveMessage() {
        Map<String, Object> map = new HashMap<>();
        if (tag.equals("1")) {
            url = "/customerApp/updateCustomerAppUser";
            map.put("id", id);
            for (int i = 0; i < getHuxingList.size(); i++) {
                if (i == getHuxingList.size() - 1) {
                    getHuXingStr += getHuxingList.get(i);
                } else {
                    getHuXingStr += getHuxingList.get(i) + ",";
                }
            }
            if (huxingList.size() == 0) {
                map.put("huXingImg", getHuXingStr);
            } else {
                map.put("huXingImg", huXingStr + "," + getHuXingStr);
            }

            Log.e("motejia", "saveMessage: ===========" + huXingStr + "===============" + getHuXingStr);

            for (int i = 0; i < getXianchangList.size(); i++) {
                if (i == getXianchangList.size() - 1) {
                    getXianchangStr += getXianchangList.get(i);
                } else {
                    getXianchangStr += getXianchangList.get(i) + ",";
                }
            }
            if (xianchangList.size() == 0) {
                map.put("huanPaiImg", getXianchangStr);
            } else {

                map.put("huanPaiImg", xianchangStr + "," + getXianchangStr);
            }


            for (int i = 0; i < getDianxiangList.size(); i++) {
                if (i == getDianxiangList.size() - 1) {
                    getDianxiangStr += getDianxiangList.get(i);
                } else {
                    getDianxiangStr += getDianxiangList.get(i) + ",";
                }
            }
            if (dianxiangList.size() == 0) {
                map.put("dianXiangImg", getDianxiangStr);

            } else {

                map.put("dianXiangImg", dianxiangStr + "," + getDianxiangStr);
            }

            for (int i = 0; i < getShuiguanList.size(); i++) {
                if (i == getShuiguanList.size() - 1) {
                    getShuiguanStr += getShuiguanList.get(i);
                } else {
                    getShuiguanStr += getShuiguanList.get(i) + ",";
                }
            }
            if (shuiguanList.size() == 0) {
                map.put("shuiGuanImg", getShuiguanStr);

            } else {

                map.put("shuiGuanImg", shuiguanStr + "," + getShuiguanStr);
            }
        } else {
            url = "/customerApp/saveCustomerAppUser";
            map.put("huXingImg", huXingStr);
            map.put("huanPaiImg", xianchangStr);
            map.put("dianXiangImg", dianxiangStr);
            map.put("shuiGuanImg", shuiguanStr);

        }
        map.put("userPhone", userPhone);
        map.put("userName", userName);
        map.put("siteText", siteText);
        map.put("siteId", siteid);
        map.put("area", area);
        //维修，暂无用
        map.put("budget", "");
//        map.put("cityName", cityName);
//        map.put("cityId", city);
        map.put("type", "公装");
        map.put("leiXingYi", type);
        map.put("leiXingEr", typeEr);
        map.put("visitTime", time);
        map.put("remarks", "");
        map.put("stage", 1);
        map.put("leiXingYiName", typeName);
        map.put("leiXingErName", typeNameEr);
        map.put("fengGe", tvStyle.getText().toString());
        map.put("xuQiuName", xvQiuStr);
        map.put("yuanDing", ed_ydmg.getText().toString().trim());
        map.put("zhuLiang", ed_zlxg.getText().toString().trim());
        map.put("ciLiang", ed_clxg.getText().toString().trim());
        map.put("mode", "自己量房");
        Log.e("motejia", "saveMessage: ===========" + xvQiuStr);
        map.put("loggedPersonPhone", MySharedPreferences.getInstance().getUserPhone());
        OkhttpUtils.doPost(ApiEngine.GZS_HOST + url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toastShow("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CheckInfo checkInfo = JSONUtils.toObject(string, CheckInfo.class);

                            if (checkInfo.getStatusCode() == 1) {
                                toastShow("预约成功");
                                //finish();
                                finishActivity();
                            } else {
                                toastShow("" + checkInfo.getStatusMsg());
                                tv_sure.setEnabled(true);
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
    }

    public void toastShow(String str) {
        ToastUtil.getInstance().toastCentent(str);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == huxingTag) {
                // 图片选择结果回调
                List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : localMedias) {
                    String path = localMedia.getPath();
                    huxingList.add(path);
                }
                countHuXing();
                tagHxNum++;
                huxingAdapter.notifyDataSetChanged();
            }
            if (requestCode == xianchangTag) {
                // 图片选择结果回调
                List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : localMedias) {
                    String path = localMedia.getPath();
                    xianchangList.add(path);
                }
                countXianChang();
                tagXcNum++;
                xianchangAdapter.notifyDataSetChanged();
            }
            if (requestCode == dianxiangTag) {
                // 图片选择结果回调
                List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : localMedias) {
                    String path = localMedia.getPath();
                    dianxiangList.add(path);
                }
                countDianXiang();
                tagDxNum++;
                dianxiangAdapter.notifyDataSetChanged();
            }
            if (requestCode == shuiguanTag) {
                // 图片选择结果回调
                List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : localMedias) {
                    String path = localMedia.getPath();
                    shuiguanList.add(path);
                }
                countShuiGuan();
                tagSgNum++;
                shuiguanAdapter.notifyDataSetChanged();
            }
            if (requestCode == 5){
                Bundle bundle = data.getExtras();
                String siteremark =null;
                if(bundle!=null){
                    userPhone=bundle.getString("userPhone");
                    userName=bundle.getString("userName");
                    siteremark=bundle.getString("siteremark");
                    siteid=bundle.getString("siteid");
                    siteText =bundle.getString("siteText");
                    tvSitetext.setText(siteText);
                }
            }
        }
    }

    private void countMoneyXvqiu() {
        int xvqiuNum = 0;
        if (tagXqNum == 0 || boolXq) {
            if (gongnengSelectList.size() > 0) {
                xvqiuNum = 100;
                boolXq = false;
            }
        }
        if (gongnengSelectList.size() <= 0) {
            boolXq = true;
            xvqiuNum = -100;
        }

        money += xvqiuNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }


    private void countHuXing() {
        int huxingNum = 0;
        if (tagHxNum == 0) {
            if (huxingList.size() > 0) {
                huxingNum = 200;
            }
        }
        if (huxingList.size() > 0) {

        } else {
            tagHxNum = 0;
            huxingNum = -200;
        }
        money += huxingNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }

    private void countXianChang() {
        int wudingNum = 0;
        if (tagXcNum == 0) {
            if (xianchangList.size() > 0) {
                wudingNum = 200;
            }
        }
        if (xianchangList.size() > 0) {

        } else {
            tagXcNum = 0;
            wudingNum = -200;
        }
        money += wudingNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }

    private void countDianXiang() {
        int wudingNum = 0;
        if (tagDxNum == 0) {
            if (dianxiangList.size() > 0) {
                wudingNum = 100;
            }
        }
        if (dianxiangList.size() > 0) {

        } else {
            tagDxNum = 0;
            wudingNum = -100;
        }
        money += wudingNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }

    private void countShuiGuan() {
        int wudingNum = 0;
        if (tagSgNum == 0) {
            if (shuiguanList.size() > 0) {
                wudingNum = 100;
            }
        }
        if (shuiguanList.size() > 0) {

        } else {
            tagSgNum = 0;
            wudingNum = -100;
        }
        money += wudingNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }

    private void countJingZhun() {
        int vMoney = 0;
        if (!TextUtils.isEmpty(ed_ydmg.getText().toString()) && !TextUtils.isEmpty(ed_zlxg.getText().toString()) && !TextUtils.isEmpty(ed_clxg.getText().toString())) {
            if (tagJzNum == 0) {
                vMoney = 300;
                tagJzNum = 1;
            }
        } else {
            if (tagJzNum == 1) {
                vMoney = -300;
                tagJzNum = 0;
            }
        }
        money += vMoney;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }

    class MyListen implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            countJingZhun();
        }
    }


    private void initMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getCustomerAppUser", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.getInstance().toastCentent("网络请求失败");
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GetMessageGongBean bean = JSONUtils.toObject(string, GetMessageGongBean.class);
                            GetMessageGongBean.BodyBean body = bean.getBody();
                            //手机号
                            messaegPhone = body.getPhone();
                            //姓名
                            customerName = body.getCustomerName();
                            //风格
                            tvStyle.setText("" + body.getFengGe());
                            //功能
                            xuQiuNames = body.getXuQiuNames();
                            gongnengSelectList.addAll(xuQiuNames);
                            if (tag.equals("1") && gongnengList != null) {
                                if (gongnengList.size() > 0) {
                                    for (int i = 0; i < gongnengList.size(); i++) {
                                        String gongneng = gongnengList.get(i);
                                        for (int j = 0; j < xuQiuNames.size(); j++) {
                                            if (gongneng.equals(xuQiuNames.get(j))) {
                                                //tagAdapter.setSelected(i,"");
                                                set.add(i);
                                            }
                                        }
                                    }
                                }
                                tagAdapter.setSelectedList(set);
                                tagAdapter.notifyDataChanged();
                            }
                            if (gongnengSelectList.size() > 0 && gongnengSelectList != null) {
                                countMoneyXvqiu();
                                tagXqNum++;
                            }


                            //户型
                            List<String> huXingImgs = body.getHuXingImgs();
                            if (huXingImgs != null) {
                                if (huXingImgs.size() > 0) {
                                    huxingList.addAll(huXingImgs);
                                    getHuxingList.addAll(huXingImgs);
                                    huxingAdapter.notifyDataSetChanged();
                                    countHuXing();
                                    tagHxNum++;
                                }

                            }


                            //现场
                            List<String> huanPaiImgs = body.getHuanPaiImgs();
                            if (huanPaiImgs != null) {
                                if (huanPaiImgs.size() > 0) {
                                    xianchangList.addAll(huanPaiImgs);
                                    getXianchangList.addAll(huanPaiImgs);
                                    xianchangAdapter.notifyDataSetChanged();
                                    countXianChang();
                                    tagXcNum++;
                                }
                            }


                            //电箱
                            List<String> dianXiangImgs = body.getDianXiangImgs();
                            if (dianXiangImgs != null) {
                                if (dianXiangImgs.size() > 0) {

                                    dianxiangList.addAll(dianXiangImgs);
                                    getDianxiangList.addAll(dianXiangImgs);
                                    dianxiangAdapter.notifyDataSetChanged();
                                    countDianXiang();
                                    tagDxNum++;
                                }
                            }


                            //水管
                            List<String> shuiGuanImgs = body.getShuiGuanImgs();
                            if (shuiGuanImgs != null) {
                                if (shuiGuanImgs.size() > 0) {

                                    shuiguanList.addAll(shuiGuanImgs);
                                    getShuiguanList.addAll(shuiGuanImgs);
                                    shuiguanAdapter.notifyDataSetChanged();
                                    countShuiGuan();
                                    tagSgNum++;
                                }
                            }


                            //原顶面高
                            String yuanDing = body.getYuanDing();
                            if (!TextUtils.isEmpty(yuanDing)) {
                                ed_ydmg.setText(yuanDing+"mm");
                            }

                            //主梁下高
                            if (!TextUtils.isEmpty(body.getZhuLiang())) {

                                ed_zlxg.setText(body.getZhuLiang()+"mm");
                            }

                            //次梁下高
                            if (!TextUtils.isEmpty(body.getCiLiang())) {
                                ed_clxg.setText(body.getCiLiang()+"mm");
                            }


                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    private void initBottomDialog() {
        View inflate = getLayoutInflater().inflate(R.layout.new_yuyue_layout, null);
        AutoUtils.auto(inflate);
        ImageView close = (ImageView) inflate.findViewById(R.id.close_img);
        TextView address = (TextView) inflate.findViewById(R.id.textAddress);
        RelativeLayout ll_youhui = inflate.findViewById(R.id.ll_youhui);
        ll_youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().toastCentent("暂未开放优惠券功能");
            }
        });
        tvSitetext = (TextView) inflate.findViewById(R.id.tvSitetext);
        if (!TextUtils.isEmpty(siteText)){
            tvSitetext.setText(siteText);
        }
        TextView tvSaveCustomerAppUser = (TextView) inflate.findViewById(R.id.tvSaveCustomerAppUser);
        RelativeLayout addressLayout = (RelativeLayout) inflate.findViewById(R.id.addressLayout);
        final PopupWindow window = new PopupWindow(this);
        window.setContentView(inflate);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置高度
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setFocusable(true);
        //设置弹出窗体需要软键盘，
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        //再设置模式，和Activity的一样，覆盖。
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        window.setBackgroundDrawable(dw);

        window.setAnimationStyle(R.style.popwin_anim_style);
        window.showAtLocation(tvTitle, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        switch (title) {
            case "家装装修":
                address.setText(Leixing + " " + Huxing);
                break;
            case "办公装修":
                address.setText(Leixing);
                break;
            case "局部维修":

                break;
        }
        addressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WorkZijiActivity.this,FuwuAddressActivity.class),5);
            }
        });
        tvSaveCustomerAppUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSitetext.getText().toString().equals("请选择服务地址")){
                    toastShow("请选择服务地址");
                    return;
                }
                saveMessage();
            }
        });
    }
}