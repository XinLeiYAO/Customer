package com.example.asus.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.DingDanXiangQingAdapter;
import com.example.asus.customer.adapter.KeHuHomeLiangFangAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.DingDanXiangQingBean;
import com.example.asus.customer.entity.DingDanXiangQingNewBean;
import com.example.asus.customer.entity.QiYeBean;

import org.json.JSONArray;

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

public class DingDanXiangQingActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_message)
    TextView ivMessage;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.title_relative)
    RelativeLayout titleRelative;
    @Bind(R.id.show_popup)
    LinearLayout showPopup;
    @Bind(R.id.zhangdanId)
    TextView zhangdanId;
    @Bind(R.id.status)
    TextView status;
    @Bind(R.id.shuliang)
    TextView shuliang;
    @Bind(R.id.top_recycleView)
    RecyclerView topRecycleView;
    @Bind(R.id.more)
    TextView more;
    @Bind(R.id.gongyingshangId)
    TextView gongyingshangId;
    @Bind(R.id.finish_time)
    TextView finishTime;
    @Bind(R.id.bottom_recycleView)
    RecyclerView bottomRecycleView;
    @Bind(R.id.kefu_img)
    ImageView kefuImg;
    @Bind(R.id.dianhua_img)
    ImageView dianhuaImg;
    @Bind(R.id.tell_kefu)
    RelativeLayout tell_kefu;
    @Bind(R.id.tell_phone)
    RelativeLayout tell_phone;
    @Bind(R.id.no_img)
    ImageView no_img;
    List<DingDanXiangQingBean.BodyBean> list = new ArrayList<>();
    List<DingDanXiangQingBean.BodyBean> other_list = new ArrayList<>();
    private String orderNo = "";
    private DingDanXiangQingAdapter adapter;
    private String danhao = "";
    private String tag = "";
    private String title = "";

    @Override
    public int getLayout() {
        return R.layout.activity_ding_dan_xiang_qing;
    }

    @Override
    public void initData() {
        setWindowStatusBarColor(this,R.color.colorWhite);
        Intent intent = getIntent();
        orderNo = intent.getStringExtra("orderNo");
        //订单编号
        danhao = intent.getStringExtra("danhao");
        if (!TextUtils.isEmpty(danhao)) {
            zhangdanId.setText("订单编号：" + danhao);
        }
        tag = intent.getStringExtra("stage");
        title = intent.getStringExtra("type");

        if (!TextUtils.isEmpty(tag)) {
            if (tag.equals("0")) {
                status.setText("进行中");
                status.setTextColor(Color.parseColor("#33cc66"));
                more.setVisibility(View.GONE);
            } else {
                status.setText("完成");
                status.setTextColor(Color.parseColor("#999999"));
                more.setVisibility(View.VISIBLE);
            }
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivAdd.setVisibility(View.VISIBLE);
        ivAdd.setImageResource(R.mipmap.liaotian);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DingDanXiangQingActivity.this, ProIMWebActivity.class));
            }
        });
        tvTitle.setText("订单详情");
        //上面的RecycleView
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        topRecycleView.setLayoutManager(manager);
        adapter = new DingDanXiangQingAdapter(this, list, "1");
        topRecycleView.setAdapter(adapter);
        adapter.setListDataCallBack(new KeHuHomeLiangFangAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    DingDanXiangQingBean.BodyBean bodyBean = list.get(i);
                    String catalogName = bodyBean.getClassificationName();//标题
                    String filePath = bodyBean.getImgUrl();//图片路径
                    String projectName = "";//描述
                    josnText.put(projectName);
                    jsonArray.put(filePath);
                    jsonContent.put(catalogName);
                }

                Intent intent = new Intent(DingDanXiangQingActivity.this, ShowImageActivity.class);
                intent.putExtra("title", "详细照片");
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", josnText.toString());
                startActivity(intent);
            }
        });
        initMessage();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (other_list.size() > 0 && other_list != null) {
                    list.clear();
                    list.addAll(other_list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        //联系客服
        tell_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getHomephone();
                startActivity(new Intent(DingDanXiangQingActivity.this, ProIMWebActivity.class));

            }
        });
        //拨打电话
        tell_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHomephone();
            }
        });

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

    private void initMessage() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(DingDanXiangQingActivity.this, Constants.PN_Onumber);
        String typeCode = "";
        if (!TextUtils.isEmpty(title)) {
            if (title.equals("彩平图")) {
                typeCode = "90";
            } else if (title.equals("结构图")) {
                typeCode = "57";
            } else {
                typeCode = "80";
            }
            if (title.equals("手绘图") || title.equals("鸟瞰图") || title.equals("平面图") || title.equals("彩平图")) {
                map.put("rwdId", value);
                map.put("CatalogId", typeCode);
            } else  {
                map.put("orderNo", danhao);
            }
        } else {
            map.put("orderNo", danhao);
        }
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getByRwdIdOrderListInfo", map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        dismissLoading();
                        ToastUtil.getInstance().toastCentent(e.toString());
                        //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getByRwdIdOrderListInfo?orderNo=1}
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        dismissLoading();
                        final String string = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DingDanXiangQingBean bean = null;
                                if (title.equals("手绘图") || title.equals("鸟瞰图") || title.equals("平面图") || title.equals("彩平图")) {
                                    DingDanXiangQingNewBean bean1 = JSONUtils.toObject(string,DingDanXiangQingNewBean.class);
                                    String imageUrl = bean1.getBody().get(0).getImageUrl();
                                    DingDanXiangQingBean.BodyBean bodyBean = new DingDanXiangQingBean.BodyBean();
                                    bodyBean.setImgUrl(imageUrl);
                                    list.add(bodyBean);
                                    more.setVisibility(View.GONE);
                                    shuliang.setText("数量：" + bean1.getBody().size());
                                    zhangdanId.setText("订单编号："+bean1.getBody().get(0).getRwdId());
                                }else{
                                    bean = JSONUtils.toObject(string, DingDanXiangQingBean.class);
                                    List<DingDanXiangQingBean.BodyBean> body = bean.getBody();
                                    if (body != null) {
                                        other_list.addAll(body);
                                        if (body.size() <= 3) {
                                            list.addAll(body);
                                            more.setVisibility(View.GONE);
                                        } else {
                                            more.setVisibility(View.VISIBLE);
                                            list.add(body.get(0));
                                            list.add(body.get(1));
                                            list.add(body.get(2));
                                        }
                                        shuliang.setText("数量：" + body.size());
                                        zhangdanId.setText("订单编号："+body.get(0).getOrderNo());
                                    } else {
                                        no_img.setVisibility(View.VISIBLE);
                                    }
                                }

                                adapter.notifyDataSetChanged();
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
    public void getHomephone() {
        /**跳转至拨号界面手动拨出电话**/
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + "18503899942");
        intent.setData(data);
        startActivity(intent);
    }
}
