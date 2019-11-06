package com.example.asus.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;

public class DingDanIngActivity extends BaseActivity {
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
    @Bind(R.id.zhangdanId)
    TextView zhangdanId;
    @Bind(R.id.tell_kefu)
    RelativeLayout tell_kefu;
    @Bind(R.id.tell_phone)
    RelativeLayout tell_phone;

    @Override
    public int getLayout() {
        return R.layout.activity_ding_dan_ing;
    }

    @Override
    public void initData() {
        setWindowStatusBarColor(this,R.color.colorWhite);
        Intent intent = getIntent();
        String danhao = intent.getStringExtra("danhao");
        Log.e("motejia", "initData: =================" + danhao);
        if (!TextUtils.isEmpty(danhao)) {
            zhangdanId.setText("订单编号：" + danhao);
        }
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText("订单详情");
        //联系客服
        tell_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DingDanIngActivity.this, ProIMWebActivity.class));
            }
        });
        //拨打电话
        tell_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHomephone("18503899942");
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
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
