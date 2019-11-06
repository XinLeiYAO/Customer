package com.example.asus.customer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.GetYuYueMessageBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YuYueXiangQingActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.typeStr)
    TextView typeStr;
    @Bind(R.id.liangfangType)
    TextView liangfangType;
    @Bind(R.id.addressStr)
    TextView addressStr;
    @Bind(R.id.timeStr)
    TextView timeStr;
    @Bind(R.id.beizhuStr)
    TextView beizhuStr;
    @Bind(R.id.zhuangxiuType)
    TextView zhuangxiuType;

    @Override
    public int getLayout() {
        return R.layout.activity_yu_yue_xiang_qing;
    }

    @Override
    public void initData() {
        mTvTitle.setText("预约详情");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String id = getIntent().getStringExtra("id");
        getYuYueMessage(id);
    }

    private void getYuYueMessage(String id) {
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
                            GetYuYueMessageBean bean = JSONUtils.toObject(string, GetYuYueMessageBean.class);
                            GetYuYueMessageBean.BodyBean body = bean.getBody();
                            //标题
                            String type = body.getType();
                            mTvTitle.setText(type + "装修");
                            //地址
                            String address = body.getAddress();
                            addressStr.setText(address + "");
                            //时间
                            String visitTime = body.getVisitTime();
                            timeStr.setText(visitTime + "");
                            //类型
                            if (type.equals("家装")) {

                                typeStr.setText(body.getLeiXingYiName() + " " + body.getLeiXingErName() + " " + body.getHuXingName() + " " + body.getArea() + " m²");
                            }else if (type.equals("维修")){
                                zhuangxiuType.setText("维修类型");
                                typeStr.setText(body.getRepairType());
                            }else{
                                typeStr.setText(body.getLeiXingYiName() + " " + body.getLeiXingErName() + " " + body.getArea() + " m²");

                            }
                            String remarks = body.getRemarks();
                            if (!TextUtils.isEmpty(remarks)&&!remarks.equals("null")){
                                //备注
                                beizhuStr.setText(remarks);
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
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
