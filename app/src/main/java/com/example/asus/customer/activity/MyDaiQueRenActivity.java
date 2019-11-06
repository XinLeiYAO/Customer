package com.example.asus.customer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.KeHuHomeLiangFangXianChangActivity;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.DaiQueRenTuZhiAdapter;
import com.example.asus.customer.adapter.DingDanXiangQingAdapter;
import com.example.asus.customer.adapter.KeHuHomeLiangFangAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.DingDanXiangQingBean;
import com.example.asus.customer.entity.FanKuiBean;
import com.example.asus.customer.entity.KeHuHomeLiangFangBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyDaiQueRenActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.title_relative)
    RelativeLayout mTitleRelative;
    @Bind(R.id.show_popup)
    LinearLayout mShowPopup;
    @Bind(R.id.Content_Layout)
    LinearLayout mContentLayout;
    @Bind(R.id.zhangdanId)
    TextView mZhangdanId;
    @Bind(R.id.status)
    TextView mStatus;
    @Bind(R.id.shuliang)
    TextView mShuliang;
    @Bind(R.id.top_recycleView)
    RecyclerView mTopRecycleView;
    @Bind(R.id.you_select)
    TextView mYouSelect;
    @Bind(R.id.you_Noselect)
    TextView mYouNoselect;
    @Bind(R.id.cha_select)
    TextView mChaSelect;
    @Bind(R.id.cha_Noselect)
    TextView mChaNoselect;
    @Bind(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;
    @Bind(R.id.pingjia_edit)
    EditText mPingjiaEdit;
    @Bind(R.id.jiaoliu_tousu_tijiao)
    Button mJiaoliuTousuTijiao;
    @Bind(R.id.tell_kefu)
    RelativeLayout tell_kefu;
    @Bind(R.id.tell_phone)
    RelativeLayout tell_phone;
    @Bind(R.id.kefu_layout)
    LinearLayout kefu_layout;
    private String orderNo;
    List<DingDanXiangQingBean.BodyBean> list = new ArrayList<>();
    //订单Adpter
    //private DingDanXiangQingAdapter adapter;
    private TagAdapter<String> tagAdapter;
    private ArrayList<String> titleArray = new ArrayList();
    private ArrayList<String> titleArray2 = new ArrayList<>();
    private String IsOption = "";
    private String danhao;
    private String type;
    private String orderno;
    private String typeName;
    private String imageUrl;
    private String creatTime;
    private DaiQueRenTuZhiAdapter adapter;
    private String title;

    @Override
    public int getLayout() {
        return R.layout.activity_my_dai_que_ren;
    }

    @Override
    public void initData() {
        setWindowStatusBarColor(this,R.color.colorWhite);
        Intent intent = getIntent();
        //判断哪调过来的 666就不是订单
        orderNo = intent.getStringExtra("orderNo");
        if (!orderNo.equals("1")) {
            //111为进展-彩平，鸟瞰等跳过来的
            if(orderNo.equals("111")){
                kefu_layout.setVisibility(View.GONE);
                mZhangdanId.setVisibility(View.GONE);
                mShuliang.setVisibility(View.GONE);
            }else{
                String time = intent.getStringExtra("time");
                kefu_layout.setVisibility(View.GONE);
                //mZhangdanId.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(time)) {
                    mZhangdanId.setText(""+time);
                }
                mShuliang.setVisibility(View.GONE);
            }

        }else{
            if (!TextUtils.isEmpty(orderno)) {
                mZhangdanId.setText("订单编号：" + orderno);
            }
        }
        //orderno = danhao
        orderno = intent.getStringExtra("orderno");
        danhao = intent.getStringExtra("danhao");

        //typeCode
        type = intent.getStringExtra("type");
        //typeName名字
        typeName = intent.getStringExtra("typeName");
        imageUrl = intent.getStringExtra("imgUrl");
        creatTime = intent.getStringExtra("creatTime");
        title = intent.getStringExtra("title");
        if (!TextUtils.isEmpty(typeName)) {
            if (typeName.equals("手绘图") || typeName.equals("鸟瞰图") || typeName.equals("平面图") || typeName.equals("彩平图")
                    || typeName.equals("效果图") || typeName.equals("施工图") || typeName.equals("水电图")) {
                mTvRight.setVisibility(View.VISIBLE);
                mTvRight.setText("历史记录");
                mTvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MyDaiQueRenActivity.this, NewHistoryActivity.class)
                                .putExtra("typeName", typeName)
                                .putExtra("type", type)
                                .putExtra("orderno", orderno));
                    }
                });
            }
        }
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title + "-待确认");
        } else {
            mTvTitle.setText("待确认");
        }
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mTopRecycleView.setLayoutManager(manager);
        adapter = new DaiQueRenTuZhiAdapter(this, list);
        adapter.setListDataCallBack(new KeHuHomeLiangFangAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {

                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                final DingDanXiangQingBean.BodyBean bodyBean = list.get(position);
                String remark = "";
                josnText.put(remark);
                String catalogName = bodyBean.getImgType();//标题
                String filePath = bodyBean.getImgUrl();//图片路径
                jsonArray.put(filePath);
                jsonContent.put(catalogName);


                Intent intent = new Intent(MyDaiQueRenActivity.this, ShowImageActivity.class);
                if (!TextUtils.isEmpty(typeName)) {
                    intent.putExtra("title", typeName);
                } else {
                    intent.putExtra("title", "图片详情");
                }
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", josnText.toString());
                startActivity(intent);
            }
        });
        mTopRecycleView.setAdapter(adapter);
        //订单适配器
        //LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mTopRecycleView.setLayoutManager(manager);
        //adapter = new DingDanXiangQingAdapter(this, list,orderNo);
        //mTopRecycleView.setAdapter(adapter);
        if (!TextUtils.isEmpty(imageUrl)) {
            mShuliang.setText("数量：1");
            DingDanXiangQingBean.BodyBean bodyBean = new DingDanXiangQingBean.BodyBean();
            bodyBean.setImgUrl(imageUrl);
            if (!TextUtils.isEmpty(creatTime)) {
                bodyBean.setCreateTime(creatTime);
            }
            if (!TextUtils.isEmpty(title)) {
                bodyBean.setImgType(title);
            }
            list.add(bodyBean);
            adapter.notifyDataSetChanged();
        }
        initMessage();
        //优差选择
        isYouCha();

        tagAdapter = new TagAdapter<String>(titleArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater.from(MyDaiQueRenActivity.this).inflate(R.layout.button, null);
                AutoUtils.auto(textView);
                textView.setBackgroundResource(R.drawable.click);
                textView.setText(s);
                return textView;
            }
        };
        mIdFlowlayout.setAdapter(tagAdapter);
        mIdFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
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
        mJiaoliuTousuTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = mPingjiaEdit.getText().toString().trim();
                if (titleArray2.size() == 0) {
                    ToastUtil.getInstance().toastCentent("请选择标签");
                } else if (!TextUtils.isEmpty(trim)) {
                    Commit();
                } else {
                    ToastUtil.getInstance().toastCentent("请输入评价信息");
                }
            }
        });

        tell_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getHomephone("18503899942");
                startActivity(new Intent(MyDaiQueRenActivity.this, ProIMWebActivity.class));
            }
        });
        tell_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHomephone("18503899942");
            }
        });
    }


    //提交评价
    private void Commit() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < titleArray2.size(); i++) {
            if (i + 1 == titleArray2.size()) {
                sb.append(titleArray2.get(i));
            } else {
                sb.append(titleArray2.get(i) + ";");
            }
        }
        JSONObject object = new JSONObject();

        try {
            object.put("rwdid", value);
            object.put("orderNo", orderno);
            object.put("type", type);
            object.put("opinion", IsOption);
            if (IsOption.equals("yes"))
                object.put("reasonType", "");
            else if (IsOption.equals("no"))
                object.put("reasonType", sb.toString());
            object.put("reason", mPingjiaEdit.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkhttpUtils.post(ApiEngine.GZS_HOST + "/customer/customerNeed/saveFeedBack", object.toString(), new Callback() {
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
                            if (statusMsg.contains("成功")) {
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

    //YES和NO选择
    private void isYouCha() {
        mYouNoselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouNoselect.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.GONE);
                mChaSelect.setVisibility(View.GONE);
                mYouSelect.setVisibility(View.VISIBLE);
                mChaNoselect.setVisibility(View.VISIBLE);
                IsOption = "yes";

                ShowDialog();

            }
        });
        mChaNoselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouSelect.setVisibility(View.GONE);
                mChaNoselect.setVisibility(View.GONE);

                mYouNoselect.setVisibility(View.VISIBLE);
                mChaSelect.setVisibility(View.VISIBLE);
                mContentLayout.setVisibility(View.VISIBLE);
                IsOption = "no";
            }
        });
        mYouSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
    }

    //二次确认
    private void ShowDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("温馨提示").setMessage("确认后将进入下一阶段");
        dialog.setCancelable(true);
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Commit();
            }
        });
        dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();
    }

    //初始化数据
    private void initMessage() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderno);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getByRwdIdOrderListInfo", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
                //Response{protocol=http/1.1, code=200, message=, url=http://10.10.13.44:7900/feedback/getByRwdIdOrderListInfo?orderNo=12-202079-D-1}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DingDanXiangQingBean bean = JSONUtils.toObject(string, DingDanXiangQingBean.class);
                        List<DingDanXiangQingBean.BodyBean> body = bean.getBody();
                        if (body.size() > 0 && body != null) {
                            mShuliang.setText("数量：" + body.size());
                            list.addAll(body);
                            if (orderNo.equals("1")) {
                                mZhangdanId.setText("订单编号：" + body.get(0).getOrderNo());
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        Map<String, Object> map1 = new HashMap<>();
        map1.put("code", "fk");
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
                        if (body.size() > 0 && body != null) {
                            titleArray.addAll(body);
                            tagAdapter.notifyDataChanged();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    //打电话事件
    public void getHomephone(String s) {
        /**跳转至拨号界面手动拨出电话**/
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + s);
        intent.setData(data);
        startActivity(intent);
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

}
