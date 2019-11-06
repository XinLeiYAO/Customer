package com.example.asus.customer.activity;

import android.content.Context;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
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
import android.view.inputmethod.InputMethodManager;
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
import com.example.asus.customer.adapter.JiaoLiuShouHouAdapter;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.OssManager;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.GetMessageGongBean;
import com.example.asus.customer.entity.GetMessageHomeBean;
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

public class HomeZijiActivity extends BaseActivity<JiaoLiuTouSuPresenter> implements JiaoLiuContract.JiaoLiuTouSuView {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.edChu)
    EditText edChu;
    @Bind(R.id.edWhu)
    EditText edWei;
    @Bind(R.id.edYang)
    EditText edYang;
    @Bind(R.id.tvStyle)
    TextView tvStyle;
    @Bind(R.id.edCengHight)
    EditText edCengHight;
    @Bind(R.id.gongnnegRecycleView)
    TagFlowLayout gongnengRecycleview;
    @Bind(R.id.jiegouRecycleView)
    RecyclerView jiegouRecycleView;
    @Bind(R.id.wudingRecycleView)
    RecyclerView wudingRecycleView;
    @Bind(R.id.tvQiang)
    TextView tvQiang;
    @Bind(R.id.tvDiao)
    TextView tvDiao;
    @Bind(R.id.tvDi)
    TextView tvDi;
    @Bind(R.id.jibenMessageTitle)
    TextView jibenMessageTitle;
    @Bind(R.id.gongnnegMessageTitle)
    TextView gongnnegMessageTitle;
    @Bind(R.id.yuanshiMessageTitle)
    TextView yuanshiMessageTitle;
    @Bind(R.id.wudingMessageTitle)
    TextView wudingMessageTitle;
    @Bind(R.id.jinzhunMessageTitle)
    TextView jinzhunMessageTitle;
    @Bind(R.id.btnCommit)
    Button btn_commit;
    private OptionsPickerView styleOptionsPickerView;
    private OptionsPickerView floorOptionsPickerView;
    private OptionsPickerView skyOptionsPickerView;
    private OptionsPickerView wallOptionsPickerView;
    private OssManager ossManager;
    private TouSuRecyclerAdapter jiegouAdapter;
    private TouSuRecyclerAdapter wudingAdapter;
    private ArrayList<String> jiegouList = new ArrayList<>();
    private ArrayList<String> getJiegouList = new ArrayList<>();
    private ArrayList<String> wudingList = new ArrayList<>();
    private ArrayList<String> getWudingList = new ArrayList<>();
    private ArrayList<String> gongnengList = new ArrayList();
    private ArrayList<String> gongnengSelectList = new ArrayList();

    public int jiegouTag = 001;
    public final static int wudingTag = 002;
    private TagAdapter tagAdapter;
    int money = 0;
    private int tagXqNum = 0;
    private boolean boolXq = false;
    private int tagJgNum = 0;
    private int tagWdNum = 0;
    private int tagJz = 1;
    private int tagJb = 1;
    private String type;
    private String title;
    private String typeEr;
    private String typeName;
    private String typeNameEr;
    private String huxing;
    private String huxingName;
    private Intent intent;
    private String area;
    private String city;
    private String time;
    private TextView tv_sure;
    private String xvQiuStr = "";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                dismissLoading();
//                initDialog();
                upLoadWuDing();
            } else if (msg.what == 11) {
                upLoadJieGou();
            } else if (msg.what == 12 || msg.what == 9) {
                ShowUtils.Toastshort(HomeZijiActivity.this, "请选择图片");
                dismissLoading();
            } else if (msg.what == 13) {
                dismissLoading();
                ShowUtils.Toastshort(HomeZijiActivity.this, "上传失败");
            } else if (msg.what == 7) {
                upLoadWuDing();
            } else if (msg.what == 8) {
                dismissLoading();
//                initDialog();
                initBottomDialog();

            }

        }
    };
    private String address;
    private String cityName;
    private String tag;
    private String id;
    private List<String> xuQiuNames;
    private Set<Integer> set = new HashSet<>();
    private String TAG = "motejia";
    private String url;
    private String messaegPhone;
    private String customerName;
    private TextView tvSitetext;
    String userPhone =null;
    String userName =null;
    String siteText =null;
    String siteid =null;
    private String Leixing;
    private String Huxing;
    @Override
    public int getLayout() {
        return R.layout.activity_home_ziji;
    }

    @Override
    public void initData() {
        addActivity(this);
        initText();
        initEditListen();
        mPresenter.getOssData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("自己量房");
        type = getIntent().getStringExtra("type");

        title = getIntent().getStringExtra("title");
        siteText = getIntent().getStringExtra("siteText");
        Leixing = getIntent().getStringExtra("Leixing");
        Huxing = getIntent().getStringExtra("Huxing");
        siteid = getIntent().getStringExtra("siteid");
        getIntentMessage();
        initTypeMessage(type);
        initStyle();
        initWallAndSkyAndFloor();
        if (tag.equals("1")) {
            initMessage();

        }
        tvStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeInput();
                styleOptionsPickerView.show();
            }
        });
        tvQiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeInput();
                wallOptionsPickerView.show();
            }
        });
        tvDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeInput();
                floorOptionsPickerView.show();
            }
        });
        tvDiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeInput();
                skyOptionsPickerView.show();
            }
        });
        //结构
        jiegouAdapter = new TouSuRecyclerAdapter(this, jiegouList);
        jiegouRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        jiegouRecycleView.setAdapter(jiegouAdapter);
        jiegouAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (jiegouList.size() == position) {
                    PictureSelector.create(HomeZijiActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(jiegouTag);//结果回调onActivityResult code 
                }
            }

            @Override
            public void setOnClick(int position) {
                if (jiegouList.size() != 0 && position != jiegouList.size()) {
                    jiegouList.remove(position);
                    if (tag.equals("1") && position <= getJiegouList.size() - 1) {
                        getJiegouList.remove(position);
                    }
                } else {
                    jiegouList.clear();
                }
                countMoneyJieGou();
                jiegouAdapter.notifyDataSetChanged();
            }
        });
        //屋顶
        wudingAdapter = new TouSuRecyclerAdapter(this, wudingList);
        wudingRecycleView.setLayoutManager(new GridLayoutManager(this, 4));
        wudingRecycleView.setAdapter(wudingAdapter);
        wudingAdapter.setListDataCallBack(new TouSuRecyclerAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (wudingList.size() == position) {
                    PictureSelector.create(HomeZijiActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                            .imageSpanCount(3)// 每行显示个数 int
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                            .enableCrop(false)// 是否裁剪 true or false
                            .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                            .scaleEnabled(false)// 裁剪是否可放大缩小图片 true or false
                            .isCamera(true)// 是否显示拍照按钮 true or false
                            .forResult(wudingTag);//结果回调onActivityResult code 
                }
            }

            @Override
            public void setOnClick(int position) {
                if (wudingList.size() != 0 && position != wudingList.size()) {
                    wudingList.remove(position);
                    if (tag.equals("1") && position <= getWudingList.size() - 1) {
                        getWudingList.remove(position);
                    }
                } else {
                    wudingList.clear();
                }
                countMoneyWuding();
                wudingAdapter.notifyDataSetChanged();
            }
        });
        //功能
        tagAdapter = new TagAdapter<String>(gongnengList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(HomeZijiActivity.this).inflate(R.layout.button, null);
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

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edChu.getText().toString()) && TextUtils.isEmpty(edYang.getText().toString()) && TextUtils.isEmpty(edWei.getText().toString()) && TextUtils.isEmpty(edCengHight.getText().toString()) && tvStyle.getText().toString().equals("请选择")) {
                    toastShow("请完善基本信息");
                    return;
                }
//                if (gongnengSelectList.size() <= 0) {
//                    toastShow("请添加需求");
//                    return;
//                }
//                if (jiegouList.size() <= 0) {
//                    toastShow("请上传原始结构图");
//                    return;
//                }
//                if (wudingList.size() <= 0) {
//                    toastShow("请上传屋顶照片");
//                    return;
//                }
                for (int i = 0; i < gongnengSelectList.size(); i++) {
                    String s = gongnengSelectList.get(i);
                    if (i == gongnengSelectList.size() - 1) {
                        xvQiuStr += s;
                    } else {
                        xvQiuStr += s + ",";
                    }
                }

                if (tag.equals("1")) {
                    for (int i = 0; i < getJiegouList.size(); i++) {
                        String imgString = getJiegouList.get(i);
                        for (int j = 0; j < jiegouList.size(); j++) {
                            if (imgString.equals(jiegouList.get(j))) {
                                jiegouList.remove(j);
                            }
                        }
                    }

                    for (int i = 0; i < getWudingList.size(); i++) {
                        String imgString = getWudingList.get(i);
                        for (int j = 0; j < wudingList.size(); j++) {
                            if (imgString.equals(wudingList.get(j))) {
                                wudingList.remove(j);
                            }
                        }
                    }
                }

                upLoadJieGou();
            }
        });
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

    private void initEditListen() {
        edCengHight.addTextChangedListener(new MyListen());
        edChu.addTextChangedListener(new MyListen());
        edWei.addTextChangedListener(new MyListen());
        edYang.addTextChangedListener(new MyListen());
    }

    private void initText() {
        String jiben = "" + "<font color='#000000'>基本信息（上传即可获得</font><font color='#ff0000'>200</font><font color='#000000'>元)</font>";
        jibenMessageTitle.setText(Html.fromHtml(jiben));
        String yuanshi = "" + "<font color='#000000'>原始结构图（上传即可获得</font><font color='#ff0000'>100</font><font color='#000000'>元)</font>";
        yuanshiMessageTitle.setText(Html.fromHtml(yuanshi));
        String gongneng = "" + "<font color='#000000'>功能需求（多选）（上传即可获得</font><font color='#ff0000'>100</font><font color='#000000'>元)</font>";
        gongnnegMessageTitle.setText(Html.fromHtml(gongneng));
        String wuding = "" + "<font color='#000000'>屋顶照片（上传即可获得</font><font color='#ff0000'>100</font><font color='#000000'>元)</font>";
        wudingMessageTitle.setText(Html.fromHtml(wuding));
        String jingzhun = "" + "<font color='#000000'>精准信息（上传即可获得</font><font color='#ff0000'>500</font><font color='#000000'>元)</font>";
        jinzhunMessageTitle.setText(Html.fromHtml(jingzhun));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == jiegouTag) {
                // 图片选择结果回调
                List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : localMedias) {
                    String path = localMedia.getPath();
                    jiegouList.add(path);
                }
                countMoneyJieGou();
                tagJgNum++;
                jiegouAdapter.notifyDataSetChanged();
            }
            if (requestCode == wudingTag) {
                // 图片选择结果回调
                List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : localMedias) {
                    String path = localMedia.getPath();
                    wudingList.add(path);
                }
                countMoneyWuding();
                tagWdNum++;
                wudingAdapter.notifyDataSetChanged();
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

    private void initWallAndSkyAndFloor() {
        //墙面
        final ArrayList<String> wallList = new ArrayList<>();
        wallList.add("乳胶漆");
        wallList.add("墙面装饰");
        wallList.add("墙砖");
        //创建选择器
        wallOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvQiang.setText(wallList.get(options1));
                countMoneyJingZhun();
            }
        }).build();
        wallOptionsPickerView.setPicker(wallList);
        //吊顶
        final ArrayList<String> DiaoList = new ArrayList<>();
        DiaoList.add("是");
        DiaoList.add("否");
        //创建选择器
        skyOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvDiao.setText(DiaoList.get(options1));
                countMoneyJingZhun();
            }
        }).build();
        skyOptionsPickerView.setPicker(DiaoList);
        //地面
        final ArrayList<String> DiList = new ArrayList<>();
        DiList.add("木地板");
        DiList.add("地砖");
        DiList.add("木纹砖");
        //创建选择器
        floorOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvDi.setText(DiList.get(options1));
                countMoneyJingZhun();
            }
        }).build();
        floorOptionsPickerView.setPicker(DiList);
    }

    public void closeInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edChu.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edWei.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edYang.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edCengHight.getWindowToken(), 0);
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
                countMoneyJiben();
            }
        }).build();
        styleOptionsPickerView.setPicker(styleList);
    }

    @Override
    protected JiaoLiuTouSuPresenter onCreatePresenter() {
        return new JiaoLiuTouSuPresenter(this);
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
            countMoneyJiben();
        }
    }

    private void initDialog() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_yuyue, null);
        AutoUtils.auto(inflate);
        final EditText ed_name = inflate.findViewById(R.id.edName);
        final EditText ed_phone = inflate.findViewById(R.id.edPhone);
        TextView tv_cancel = inflate.findViewById(R.id.tvCancel);
        tv_sure = inflate.findViewById(R.id.tvSure);


        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(HomeZijiActivity.this, R.style.newPassword);
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
                jiegouList.addAll(getJiegouList);
                wudingList.addAll(getWudingList);
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
                    startActivity(new Intent(HomeZijiActivity.this, TextLoginActivity.class)
                            .putExtra("phone", ed_phone.getText().toString())
                    );

                } else {
                    tv_sure.setEnabled(false);
//                    saveMessage(ed_name.getText().toString(), ed_phone.getText().toString());

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
                startActivityForResult(new Intent(HomeZijiActivity.this,FuwuAddressActivity.class),5);
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

    public void toastShow(String str) {
        ToastUtil.getInstance().toastCentent(str);
    }

    private int index = 0;
    private String jiegouString = "";
    private String getJiegouString = "";

    private void upLoadJieGou() {
        showLoading();
//        if (jiegouList.size() <= 0) {//集合里没有图片
//            handler.sendEmptyMessage(12);
//            return;
//        }

        if (index == jiegouList.size()) {//递归上传完毕
            handler.sendEmptyMessage(10);
            return;
        }
        String filePath = jiegouList.get(index);
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
                index += 1;
                if (index == jiegouList.size()) {//递归上传完毕
                    jiegouString += name;
                } else {
                    jiegouString += name + ",";
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

    private int indexWuding = 0;
    private String wudingString = "";
    private String getWudingString = "";

    private void upLoadWuDing() {
        showLoading();
//        if (wudingList.size() <= 0) {//集合里没有图片
//            handler.sendEmptyMessage(9);
//            return;
//        }

        if (indexWuding == wudingList.size()) {//递归上传完毕
            handler.sendEmptyMessage(8);
            return;
        }
        String filePath = wudingList.get(indexWuding);
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
                indexWuding += 1;
                if (indexWuding == wudingList.size()) {//递归上传完毕
                    wudingString += name;
                } else {
                    wudingString += name + ",";
                }
                handler.sendEmptyMessage(7);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                indexWuding += 1;
//                handler.sendEmptyMessage(11);
                Message message = new Message();
                message.what = 13;
                message.obj = "第" + (indexWuding + 1) + "张图片上传失败";
                handler.sendMessage(message);
            }
        });
    }

    private void saveMessage() {
        Map<String, Object> map = new HashMap<>();
        if (tag.equals("1")) {
            url = "/customerApp/updateCustomerAppUser";
            map.put("id", id);
            for (int i = 0; i < getJiegouList.size(); i++) {
                if (i == getJiegouList.size() - 1) {
                    getJiegouString += getJiegouList.get(i);
                } else {
                    getJiegouString += getJiegouList.get(i) + ",";
                }
            }
            if (jiegouList.size() == 0) {
                map.put("yuanShiImg", getJiegouString);

            } else {

                map.put("yuanShiImg", jiegouString + "," + getJiegouString);
            }


            for (int i = 0; i < getWudingList.size(); i++) {
                if (i == getWudingList.size() - 1) {
                    getWudingString += getWudingList.get(i);
                } else {
                    getWudingString += getWudingList.get(i) + ",";
                }
            }
            if (wudingList.size() == 0) {
                map.put("wuDingImg", getWudingString);
            } else {

                map.put("wuDingImg", wudingString + "," + getWudingString);
            }

        } else {
            url = "/customerApp/saveCustomerAppUser";
            map.put("yuanShiImg", jiegouString);
            map.put("wuDingImg", wudingString);
        }
        map.put("userPhone", userPhone);
        map.put("userName", userName);
        map.put("siteText", siteText);
        map.put("siteId", siteid);
        map.put("area", area);
        map.put("budget", "");
        map.put("cityName", cityName);
        map.put("cityId", city);
        map.put("type", "家装");
        map.put("leiXingYi", type);
        map.put("leiXingEr", typeEr);
        map.put("visitTime", time);
        map.put("remarks", "");
        map.put("stage", 1);
        map.put("huXing", huxing);
        map.put("leiXingYiName", typeName);
        map.put("leiXingErName", typeNameEr);
        map.put("huXingName", huxingName);
        map.put("fengGe", tvStyle.getText().toString());
        map.put("xuQiuName", xvQiuStr);
        map.put("juTiBuJu", edChu.getText().toString());
        map.put("juTiBuJuYi", edWei.getText().toString());
        map.put("juTiBuJuEr", edYang.getText().toString());
        map.put("cengGao", edCengHight.getText().toString());
        map.put("mode", "自己量房");

        if (tvQiang.equals("请选择")) {
            map.put("qiangMian", "");
        } else {
            map.put("qiangMian", tvQiang.getText().toString());
        }
        if (tvDi.equals("请选择")) {
            map.put("diMian", "");
        } else {
            map.put("diMian", tvDi.getText().toString());
        }
        if (tvDiao.equals("请选择")) {
            map.put("dingMian", "");
        } else {
            map.put("dingMian", tvDiao.getText().toString());
        }
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
//                                finish();
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
                            GetMessageHomeBean bean = JSONUtils.toObject(string, GetMessageHomeBean.class);
                            GetMessageHomeBean.BodyBean body = bean.getBody();
                            //手机号
                            messaegPhone = body.getPhone();
                            //姓名
                            customerName = body.getCustomerName();
                            //风格
                            tvStyle.setText("" + body.getFengGe());

                            //厨，卫，阳
                            if (!TextUtils.isEmpty(body.getJuTiBuJu())) {
                                edChu.setText("" + body.getJuTiBuJu());
                            }
                            if (!TextUtils.isEmpty(body.getJuTiBuJuYi())) {
                                edWei.setText("" + body.getJuTiBuJuYi());

                            }
                            if (!TextUtils.isEmpty(body.getJuTiBuJuEr())) {
                                edYang.setText("" + body.getJuTiBuJuEr());
                            }

                            //层高
                            if (!TextUtils.isEmpty(body.getCengGao())) {
                                edCengHight.setText(body.getCengGao() + "");
                            }

                            //原始结构图
                            List<String> yuanShiImgs = body.getYuanShiImgs();
                            if (yuanShiImgs != null) {
                                if (yuanShiImgs.size() > 0) {

                                    jiegouList.addAll(yuanShiImgs);
                                    getJiegouList.addAll(yuanShiImgs);
                                    jiegouAdapter.notifyDataSetChanged();
                                    countMoneyJieGou();
                                    tagJgNum++;
                                }
                            }


                            //屋顶照片
                            List<String> wuDingImgs = body.getWuDingImgs();
                            if (wuDingImgs != null) {
                                if (wuDingImgs.size() > 0) {

                                    getWudingList.addAll(wuDingImgs);
                                    wudingAdapter.notifyDataSetChanged();
                                    countMoneyWuding();
                                    tagWdNum++;
                                    wudingList.addAll(wuDingImgs);
                                }
                            }


                            //墙面
                            tvQiang.setText(body.getQiangMian() + "");
                            //吊顶
                            tvDiao.setText(body.getDingMian());
                            //地面
                            tvDi.setText(body.getDiMian());

                            //功能需求
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

                            countMoneyJiben();
                            countMoneyJingZhun();

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void countMoneyJiben() {
        int vMoney = 0;
        if (!TextUtils.isEmpty(edChu.getText().toString()) && !TextUtils.isEmpty(edYang.getText().toString()) && !TextUtils.isEmpty(edWei.getText().toString()) && !TextUtils.isEmpty(edCengHight.getText().toString()) && !tvStyle.getText().toString().equals("请选择")) {
            if (tagJb == 1) {
                vMoney = 200;
                tagJb = 2;
            }
        } else {
            if (tagJb == 2) {
                vMoney = -200;
                tagJb = 1;
            }
        }
        money += vMoney;
        btn_commit.setText("（已省" + money + "元）立即提交");
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

    private void countMoneyJieGou() {
        int jiegouNum = 0;
        if (tagJgNum == 0) {
            if (jiegouList.size() > 0) {
                jiegouNum = 100;
            }
        }
        if (jiegouList.size() > 0) {

        } else {
            tagJgNum = 0;
            jiegouNum = -100;
        }
        money += jiegouNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
    }

    private void countMoneyWuding() {
        int wudingNum = 0;
        if (tagWdNum == 0) {
            if (wudingList.size() > 0) {
                wudingNum = 100;
            }
        }
        if (wudingList.size() > 0) {

        } else {
            tagWdNum = 0;
            wudingNum = -100;
        }
        money += wudingNum;
        btn_commit.setText("（已省" + money + "元）立即提交");

    }

    private void countMoneyJingZhun() {
        int jzNum = 0;
        if (!tvQiang.getText().toString().equals("请选择") && !tvDiao.getText().toString().equals("请选择") && !tvDi.getText().toString().equals("请选择")) {
            if (tagJz == 1) {
                jzNum = 500;
                tagJz = 2;
            } else {
                jzNum = 0;
            }
        } else {
            if (tvQiang.getText().toString().equals("请选择") && tvDiao.getText().toString().equals("请选择") && tvDi.getText().toString().equals("请选择")) {
                jzNum = 0;
                tagJz = 1;
            }
        }
        money += jzNum;
        btn_commit.setText("（已省" + money + "元）立即提交");
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
}