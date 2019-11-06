package com.example.asus.customer.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.Bind;

public class ShareActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tvShareWeixin)
    TextView tvShareWeixin;
    @Bind(R.id.tvPengyouquan)
    TextView tvPengyouquan;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.btnTx)
    Button btnTx;
    private String phone;

    @Override
    public int getLayout() {
        return R.layout.activity_share;
    }

    @Override
    public void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTitle.setText("推荐同事");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("邀请记录");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShareActivity.this, WebActivity.class).putExtra("title", "邀请记录"));
            }
        });
        tvShareWeixin.setOnClickListener(this);
        tvPengyouquan.setOnClickListener(this);
        btnTx.setOnClickListener(this);
        phone = PrefUtils.getValue(this, Constants.PHONENUM);
    }

    private void weixin() {
        UMImage thumb = new UMImage(this, R.mipmap.kh_icon);
//        UMWeb web = new UMWeb(ApiEngine.KEHU + "AppGroup/AppForeign/AppNewsIfream?" + "id=" + -1 + "&card_no=" + App.cardNo);
        UMWeb web = new UMWeb(ApiEngine.webUrl + "five/invitation/" + phone + ".html");
        web.setTitle(getString(R.string.shareTitle));
        web.setThumb(thumb);
        web.setDescription(getString(R.string.shareContent));
        new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
//                        .withText("测试数据")
                .withMedia(web)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private void sharePenyouquan() {
        UMImage thumb = new UMImage(this, R.mipmap.kh_icon);
        UMWeb web = new UMWeb(ApiEngine.webUrl + "five/invitation/" + phone + ".html");
        web.setTitle(getString(R.string.shareTitle));
        web.setThumb(thumb);
        web.setDescription(getString(R.string.shareContent));
        new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
//                        .withText("测试数据")
                .withMedia(web)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ShowUtils.Toastshort(ShareActivity.this, "分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ShowUtils.Toastshort(ShareActivity.this, t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ShowUtils.Toastshort(ShareActivity.this, "分享取消");
        }
    };

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvShareWeixin:
                weixin();
                break;
            case R.id.tvPengyouquan:
                sharePenyouquan();
                break;
            case R.id.btnTx:
                startActivity(new Intent(ShareActivity.this, WebActivity.class).putExtra("title", "分享提现"));
                break;
        }
    }
}
