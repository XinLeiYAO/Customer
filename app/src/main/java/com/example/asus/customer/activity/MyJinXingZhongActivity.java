package com.example.asus.customer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.UserMessageBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyJinXingZhongActivity extends BaseActivity {

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
    @Bind(R.id.zhidian)
    Button zhidian;
    @Bind(R.id.finishTime)
    TextView mFinishTime;
    String time;

    @Override
    public int getLayout() {
        return R.layout.activity_my_jin_xing_zhong;
    }


    @Override
    public void initData() {
        time = getIntent().getStringExtra("time");
        String title = getIntent().getStringExtra("title");
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText(title + "-进行中");
        mIvAdd.setVisibility(View.VISIBLE);
        mIvAdd.setImageResource(R.mipmap.liaotian);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyJinXingZhongActivity.this, ProIMWebActivity.class));
                //startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
        if (!TextUtils.isEmpty(time)) {
            String substring = time.substring(0, 10);
            mFinishTime.setText("预计完成时间：" + substring);
        }
        zhidian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHomephone("18503899942");
            }
        });
    }

    /**
     * 打电话事件
     *
     * @param phone
     */
    private void getHomephone(String phone) {

        /**直接拨号打电话***/
      /*  try {
            Intent Intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
            startActivity(Intent);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /**跳转至拨号界面手动拨出电话**/
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getImMessage();
    }

    private void getImMessage() {
        Map map = new HashMap();
        String value = PrefUtils.getValue(MyJinXingZhongActivity.this, Constants.PN_Onumber);
        //map.put("userName", value);
        OkhttpUtils.doGet("https://chat.wenes.cn/index/getMsgCountFromRedis?userName=" + value, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0) {
                                UserMessageBean bean = JSONUtils.toObject(string, UserMessageBean.class);
                                int count = bean.getBody().getCount();
                                if (count != 0) {
                                    mIvMessage.setVisibility(View.VISIBLE);
                                    mIvMessage.setText(count + "");
                                } else {
                                    mIvMessage.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
}
