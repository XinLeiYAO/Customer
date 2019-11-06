package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.FangAnShiGongActivity;
import com.example.asus.customer.activity.home.ShowImageActivity;
import com.example.asus.customer.adapter.JieGouTuAdapter;
import com.example.asus.customer.adapter.JieGouTuNewAdapter;
import com.example.asus.customer.adapter.KeHuHomeLiangFangAdapter;
import com.example.asus.customer.adapter.OrderJinChengAdapter;
import com.example.asus.customer.adapter.TouSuRecyclerAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.DesignerMessageBean;
import com.example.asus.customer.entity.OrderMessageNewBean;
import com.example.asus.customer.entity.OrderStatusMessageBean;
import com.example.asus.customer.entity.OrderprocessInfoBean;
import com.example.asus.customer.entity.QuanBuBean;
import com.example.asus.customer.entity.ShiGongXiaoGuoBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewDingDanXiangQingActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.head_saoyisao)
    ImageView ivSaoyisao;
    @Bind(R.id.iv_add)
    ImageView iv_add;
    @Bind(R.id.againDesign)
    TextView tvAgainDesign;
    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    @Bind(R.id.tv_order_type)
    TextView tvOrderType;
    @Bind(R.id.orderjinchengRecycleview)
    RecyclerView orderRecycleView;
    @Bind(R.id.orderMessageRecycleView)
    RecyclerView orderMessageRecycleView;
    @Bind(R.id.status_img)
    ImageView status_img;
    @Bind(R.id.design_image)
    ImageView design_img;
    @Bind(R.id.design_name)
    TextView design_name;
    @Bind(R.id.design_dengji)
    TextView design_dengji;
    @Bind(R.id.design_money)
    TextView design_money;
    @Bind(R.id.design_phone)
    TextView design_phone;
    @Bind(R.id.manyiCommit)
    TextView manyiCommit;
    @Bind(R.id.llBottomContent)
    LinearLayout llBottomContent;

    private JieGouTuNewAdapter adapter;
    List<QuanBuBean.BodyBean> list = new ArrayList<>();
    private String orderNo;
    private String statusName;
    private String drawType;
    private String imageName;
    private String groupId;
    private String rwdId;
    private List<OrderStatusMessageBean> orderList = new ArrayList<>();
    private String[] statusOrder = new String[]{"下单", "执行", "提交", "质检", "验收"};
    private List<String> timeList = new ArrayList<>();
    private OrderprocessInfoBean.BodyBean body;
    private OrderJinChengAdapter jinChengAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_new_ding_dan_xiang_qing;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        orderNo = intent.getStringExtra("orderNo");
        //订单编号
        tvOrderNum.setText("" + orderNo);
        statusName = intent.getStringExtra("statusName");
        drawType = intent.getStringExtra("type");
        //图纸类型
        imageName = intent.getStringExtra("imageName");
        groupId = intent.getStringExtra("groupId");
        rwdId = intent.getStringExtra("rwdId");
        tvOrderType.setText(imageName);
        switch (statusName) {
            case "待下单":
                llBottomContent.setVisibility(View.GONE);
                status_img.setImageResource(R.mipmap.xiadan_img);
                break;
            case "待接单":
            case "待分单":
                status_img.setImageResource(R.mipmap.zhixing_img);
                break;
            case "绘图中":
                status_img.setImageResource(R.mipmap.tijiao_img);
                break;
            case "待审批":
                status_img.setImageResource(R.mipmap.zhijian_img);
                break;
            case "待验收":
            case "待评价":
                status_img.setImageResource(R.mipmap.yanshou_img);
                break;
        }
        tvAgainDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewDingDanXiangQingActivity.this, AgainDesignActivity.class));
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("订单详情");
        orderRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        jinChengAdapter = new OrderJinChengAdapter(this, orderList);
        orderRecycleView.setAdapter(jinChengAdapter);
        orderMessageRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new JieGouTuNewAdapter(this, list);
        adapter.setListDataCallBack(new KeHuHomeLiangFangAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                JSONArray jsonArray = new JSONArray();
                JSONArray jsonContent = new JSONArray();
                JSONArray josnText = new JSONArray();
                for (int j = 0; j < list.size(); j++) {
                    QuanBuBean.BodyBean bodyBean = list.get(j);
                    String remark = bodyBean.getImgName();
                    josnText.put(remark);
                    String catalogName = bodyBean.getImgName();//标题
                    String filePath = bodyBean.getImgUrl();//图片路径
                    jsonArray.put(filePath);
                    jsonContent.put(catalogName);
                }

                Intent intent = new Intent(NewDingDanXiangQingActivity.this, ShowImageActivity.class);

                intent.putExtra("title", imageName);
                intent.putExtra("json", jsonArray.toString());
                intent.putExtra("context", "");
                intent.putExtra("index", position);
                intent.putExtra("jsonContent", jsonContent.toString());
                intent.putExtra("text", josnText.toString());
                startActivity(intent);
            }
        });
        orderMessageRecycleView.setAdapter(adapter);
        initMessage();
        initDesinger();

        manyiCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderAcceptanceByOrderType();
            }
        });
    }

    private void initDesinger() {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("rwdId", rwdId);
        OkhttpUtils.doGet(ApiEngine.ZHA_HOST + "DesignInstitute/group/selectShopInfo", map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
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
                            dismissLoading();
                            DesignerMessageBean bean = JSONUtils.toObject(string, DesignerMessageBean.class);
                            if (bean.getStatusCode() == 0) {
                                DesignerMessageBean.BodyBean bodyBean = bean.getBody();
                                //设计师照片
                                Glide.with(NewDingDanXiangQingActivity.this).load(bodyBean.getGroupSmallImg()).into(design_img);
                                //设计师名字
                                design_name.setText(bodyBean.getGroupName());
                                //设计师等级
                                String typeName = bodyBean.getShop_type_name();
                                if (!TextUtils.isEmpty(typeName) && !typeName.equals("null")) {
                                    design_dengji.setText(typeName);
                                }
                                //设计师资费
                                design_money.setText(bodyBean.getCity_name());
                                //设计师电话
                                design_phone.setText(bodyBean.getSellCount() + "");
                            } else {
                                ToastUtil.getInstance().toastCentent(bean.getStatusMsg() + "");
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
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("orderStage", drawType);
        OkhttpUtils.doGet(ApiEngine.ZHA_HOST + "DesignInstitute/contract/getOrderprocessInfo", map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
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
                            dismissLoading();
                            OrderprocessInfoBean bean = JSONUtils.toObject(string, OrderprocessInfoBean.class);
                            if (bean.getStatusCode() == 0) {
                                body = bean.getBody();

                                timeList.add("" + body.getXdTime());
                                timeList.add("" + body.getJdTime());
                                timeList.add("" + body.getTjTime());
                                timeList.add("" + body.getSpTime());
                                timeList.add("" + body.getYsTime());


                                //订单进程
                                switch (statusName) {
                                    case "待下单":
                                        initOrdering(1);
                                        break;
                                    case "待接单":
                                    case "待分单":
                                        initOrdering(2);
                                        break;
                                    case "绘图中":
                                        initOrdering(3);
                                        break;
                                    case "待审批":
                                        initOrdering(4);
                                        break;
                                    case "待验收":
                                    case "客待验":
                                    case "待评价":
                                        initOrdering(5);
                                        break;
                                }

//                                //图纸信息
                                String imageList = body.getImageList();
                                if (imageList != null && imageList.length() > 0) {
                                    String[] splitAddress = imageList.split(",");
                                    for (int i = 0; i < splitAddress.length; i++) {
                                        QuanBuBean.BodyBean bodyBean1 = new QuanBuBean.BodyBean();
                                        bodyBean1.setImgUrl(splitAddress[i]);
                                        bodyBean1.setImgName(imageName);
                                        list.add(bodyBean1);
                                    }
                                    adapter.notifyDataSetChanged();
                                }
//
//                                initDesinger(body.getCreatorCard());
                            } else {
                                ToastUtil.getInstance().toastCentent(bean.getStatusMsg() + "");
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initOrdering(int tag) {
        for (int i = 0; i < statusOrder.length; i++) {
            if (i < tag) {
                OrderStatusMessageBean bean = new OrderStatusMessageBean();
                bean.setStatusName("" + statusOrder[i]);
                bean.setStatusTime(timeList.get(i));
                bean.setOrder(orderNo + "");
                bean.setYanshouren(body.getOList().get(i) + "");
//                bean.setYujiTime(body.getYjspTime() + "");
//                bean.setOrderNum(body.getDrawNum() + "");
                orderList.add(bean);
            }
        }
        jinChengAdapter.notifyDataSetChanged();
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    private void OrderAcceptanceByOrderType() {
        String imgurl = "";
        for (int i = 0; i < list.size(); i++) {
            imgurl += list.get(i).getImgUrl() + ",";
        }
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        String name = PrefUtils.getValue(this, "name");
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        map.put("rwdid", "114-754");
//        map.put("rwdid", rwdId);
        map.put("spOrYs", 2);
        map.put("submitter", name);
        map.put("submitterCard", value);
        map.put("drawType", drawType);
        map.put("url", imgurl);
        map.put("edition", App.getVersionName());
        OkhttpUtils.doGet(ApiEngine.ZHA_HOST + "DesignInstitute/contract/OrderAcceptanceByOrderType", map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                        ToastUtil.getInstance().toastCentent("网络请求失败");

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoading();
                        DesignerMessageBean bean = JSONUtils.toObject(string, DesignerMessageBean.class);
                        if (bean.getStatusCode() == 0) {
                            finish();
                        }
                        ToastUtil.getInstance().toastCentent(bean.getStatusMsg() + "");

                    }
                });
            }
        });
    }
}
