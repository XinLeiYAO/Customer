package com.example.asus.customer.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.AddCustomerActivity;
import com.example.asus.customer.activity.CustomerMangerActivity;
import com.example.asus.customer.activity.MyDingDanActivity;
import com.example.asus.customer.activity.MyYuYueActivity;
import com.example.asus.customer.activity.QRZhongJianYeActivity;
import com.example.asus.customer.activity.RegistActivity;
import com.example.asus.customer.activity.SettingsActivity;
import com.example.asus.customer.activity.ShareActivity;
import com.example.asus.customer.activity.TextLoginActivity;
import com.example.asus.customer.activity.UserInfoActivity;
import com.example.asus.customer.activity.WebActivity;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.entity.UserInfoBean;
import com.example.asus.customer.mvp.contract.MineContract;
import com.example.asus.customer.mvp.presenter.MinePresenter;

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

public class MineNewFragment extends BaseFragment<MinePresenter> implements MineContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.head_saoyisao)
    ImageView ivSaoyisao;
    @Bind(R.id.iv_add)
    ImageView iv_add;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvNumber)
    TextView tvNumber;
    @Bind(R.id.ivUserImg)
    ImageView ivUserImg;
    @Bind(R.id.lrkh_img)
    ImageView lrkh_img;
    @Bind(R.id.khgl_img)
    ImageView khgl_img;
    @Bind(R.id.tjts_img)
    ImageView tjts_img;
    @Bind(R.id.allLayout)
    LinearLayout allLayout;
    @Bind(R.id.dingdanLayout)
    RelativeLayout dingdanLayout;
    @Bind(R.id.xvqiuLayout)
    RelativeLayout xvqiuLayout;
    @Bind(R.id.tixianLayout)
    RelativeLayout tixianLayout;
    @Bind(R.id.rlShouyi)
    RelativeLayout rlShouyi;
    @Bind(R.id.userInfoLayout)
    LinearLayout userInfoLayout;
    @Bind(R.id.xvqiu_num)
    TextView xvqiuNum;
    @Bind(R.id.dingdan_num)
    TextView dingdanNum;

    private String cardNo = "";
    private OnButtonClick onButtonClick;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        cardNo = App.cardNo;
    }

    @Override
    public void onResume() {
        super.onResume();
        cardNo = MySharedPreferences.getInstance().getCardNo();
        if (!TextUtils.isEmpty(cardNo)) {
            initUserData();
            initOrderNum();
            initYuyueNum();
        } else {
            String userPhone = MySharedPreferences.getInstance().getUserPhone();
            if (!TextUtils.isEmpty(userPhone)) {
                tvName.setText("测试客户");
                tvNumber.setText("账号：" + userPhone);
                initYuyueNum();
            } else {
                tvName.setText("登录客户端");
                tvNumber.setText("享订单化装修服务");
                xvqiuNum.setText("——");
            }
            dingdanNum.setText("——");
            ivUserImg.setImageResource(R.mipmap.my_no_login);
        }
    }

    private void initOrderNum() {
        Map<String, Object> map = new HashMap<>();
        String rwdid = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdId", rwdid);
        OkhttpUtils.doGet(ApiEngine.ZHA_HOST + "DesignInstitute/contract/getPlanCollectionLength", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtil.getInstance().toastCentent("网络请求失败");

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    int statusCode = object.getInt("StatusCode");
                    if (statusCode == 0) {
                        final int body = object.getInt("Body");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dingdanNum.setText(body + "");
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initYuyueNum() {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", MySharedPreferences.getInstance().getUserPhone());
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/customerApp/getPhoneNumber", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtil.getInstance().toastCentent("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    int statusCode = object.getInt("StatusCode");
                    if (statusCode == 1) {
                        final int body = object.getInt("Body");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                xvqiuNum.setText(body + "");
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.mine_new_fragment;
    }

    @Override
    protected void FragmentInitData() {
        cardNo = App.cardNo;
        tvTitle.setVisibility(View.GONE);
        //设置
        ivBack.setImageResource(R.mipmap.my_shezhi);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    startActivity(new Intent(getContext(), SettingsActivity.class));
                }
            }
        });

        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                }
            }
        });
        //扫一扫
        ivSaoyisao.setVisibility(View.VISIBLE);
        ivSaoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), QRZhongJianYeActivity.class));
                }
            }
        });
        //闹钟样式
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setImageResource(R.mipmap.my_naozhong);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), LoginNewActivity.class));
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    ToastUtil.getInstance().toastCentent("功能开发中");
                }
            }
        });
        //整个Activity点击事件
        allLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                }
            }
        });

        //订单
        dingdanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (TextUtils.isEmpty(cardNo)) {

                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    if (onButtonClick != null) {
                        onButtonClick.onClick(dingdanLayout);
                    }
//                    startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 0)
//                            .putExtra("title", "订单"));
                }
            }
        });
        //预约
        xvqiuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MyYuYueActivity.class));
                }
            }
        });
        //提现
        tixianLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title", "提现"));
            }
        });
        //我的收益
        rlShouyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title", "我的收益"));
            }
        });
        lrkh_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddCustomerActivity.class));
            }
        });
        khgl_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CustomerMangerActivity.class));
            }
        });
        //用户头部
        userInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cardNo = App.cardNo;
//                if (TextUtils.isEmpty(cardNo)) {
//                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
//                } else {
//                    startActivity(new Intent(getContext(), UserInfoActivity.class));
//                }
            }
        });
        ivUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    startActivity(new Intent(getContext(), UserInfoActivity.class));
                }
            }
        });
        tjts_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNo = App.cardNo;
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(getActivity(), TextLoginActivity.class));
                } else {
                    startActivity(new Intent(getContext(), ShareActivity.class));
                }
            }
        });
    }


    @Override
    protected MinePresenter onCreatePresenter() {
        return new MinePresenter(this);
    }

    /**
     * 初始化用户信息
     */
    private void initUserData() {
        Map<String, Object> hashMap = new HashMap<>();
        String cardNo = PrefUtils.getValue(getActivity(), Constants.PHOME);
        String token = PrefUtils.getValue(getActivity(), Constants.PASSWORD);
        hashMap.put("cardNo", cardNo);
        hashMap.put("token", token);
        OkhttpUtils.doPost(ApiEngine.INFORMATION, hashMap, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ShowUtils.Toastshort(getContext(), e.getMessage());
                        }
                    });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UserInfoBean userInfoBean = JSONUtils.toObject(string, UserInfoBean.class);
                            if (userInfoBean.getStatusCode() == 0) {

                                List<UserInfoBean.BodyBean> body = userInfoBean.getBody();
                                UserInfoBean.BodyBean bodyBean = body.get(0);
                                if (tvName != null) {
                                    tvName.setText(bodyBean.getU_name() == null ? "昵称" : bodyBean.getU_name());
                                }
                                if (tvNumber != null) {
                                    tvNumber.setText("账号：" + bodyBean.getA_account());
                                }
                                RequestOptions options = new RequestOptions();
                                options.placeholder(R.mipmap.userimage);
                                options.error(R.mipmap.userimage);
                                options.centerCrop();
                                options.circleCrop();

                                FragmentActivity activity = getActivity();
                                if (activity != null) {
                                    Glide.with(activity).load(bodyBean.getImage()).apply(options).into(ivUserImg);
                                }
                            } else if (userInfoBean.getStatusCode() == 104) {

                                showToast("您的账号在其他账号登陆，请重新登陆");
                                Map map = new HashMap();
                                String cardNo = PrefUtils.getValue(getActivity(), Constants.PHOME);

                                map.put("cardNo", cardNo);
                                OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "actionapi/AppHome/OfflineApp", map, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Log.e("下线", e.getMessage().toString());
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String string = response.body().string();
                                        Log.e("下线", string);
                                    }
                                });
                                App.getApp().exitApp();
                                PrefUtils.RemoveValue(getActivity(), Constants.IS_LOGIN);
                                PrefUtils.RemoveValue(getActivity(), Constants.PHOME);
                                PrefUtils.RemoveValue(getActivity(), Constants.PASSWORD);
                                PrefUtils.RemoveValue(getActivity(), Constants.PN_Onumber);
                                startActivity(new Intent(getActivity(), TextLoginActivity.class));
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void loadCustomerInformation(MineUserInfo.BodyBean bodyBean) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void getUnreadData(UnreadBean unreadBean) {

    }

    @Override
    public void getUnreadDataErro(String erro) {

    }


    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public interface OnButtonClick {
        public void onClick(View view);
    }

}