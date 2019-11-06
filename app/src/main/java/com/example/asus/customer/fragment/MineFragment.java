package com.example.asus.customer.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.HeZuoTiJiaoActivity;
import com.example.asus.customer.activity.HuanRenActivity;
import com.example.asus.customer.activity.InformMessageActivity;
import com.example.asus.customer.activity.JiaoLiuTouSuActivity;
import com.example.asus.customer.activity.KeHuMessageNewActivity;
import com.example.asus.customer.activity.MoreHomeActivity;
import com.example.asus.customer.activity.MyDingDanActivity;
import com.example.asus.customer.activity.ProIMWebActivity;
import com.example.asus.customer.activity.QRZhongJianYeActivity;
import com.example.asus.customer.activity.QianYueActivity;
import com.example.asus.customer.activity.SettingsActivity;
import com.example.asus.customer.activity.TextLoginActivity;
import com.example.asus.customer.activity.UserInfoActivity;
import com.example.asus.customer.activity.ZaiCiHeZuoActivity;
import com.example.asus.customer.activity.ZhangDanJiLuActivity;
import com.example.asus.customer.activity.home.FangAnCaiPingActivity;
import com.example.asus.customer.activity.home.FangAnJieGouActivity;
import com.example.asus.customer.activity.home.FangAnSUMoXingActivity;
import com.example.asus.customer.activity.home.FangAnShiGongActivity;
import com.example.asus.customer.activity.home.FangAnShouHuiActivity;
import com.example.asus.customer.activity.home.FangAnXiaoGuoActivity;
import com.example.asus.customer.activity.home.KeHuHomeLiangFangDataActivity;
import com.example.asus.customer.activity.home.KeHuHomeLiangFangXianChangActivity;
import com.example.asus.customer.activity.home.KeHuHomeWebViewActivity;
import com.example.asus.customer.activity.home.ShouCangActivity;
import com.example.asus.customer.adapter.HorAadapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.MsgCount;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.entity.UserInfoBean;
import com.example.asus.customer.entity.UserMessageBean;
import com.example.asus.customer.mvp.contract.MineContract;
import com.example.asus.customer.mvp.presenter.MinePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/4/16.
 * 我
 */

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View, View.OnClickListener {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.show_popup)
    RelativeLayout showPopup;
    @Bind(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @Bind(R.id.rl_integral)
    RelativeLayout rlIntegral;
    @Bind(R.id.rl_settings)
    RelativeLayout rlSettings;
    @Bind(R.id.riv_mine)
    ImageView rivMine;
    @Bind(R.id.iv_add)
    ImageView iv_add;
    @Bind(R.id.tv_mine_name)
    TextView tvMineName;
    @Bind(R.id.tv_mine_card)
    TextView tvMineCard;
    @Bind(R.id.ewmImage)
    ImageView ewmImage;
    @Bind(R.id.tv_messagenum)
    TextView tvMessagenum;
    @Bind(R.id.shoucang_xiaoxi)
    TextView shoucangXiaoxi;
    @Bind(R.id.shoucang)
    RelativeLayout shoucang;
    @Bind(R.id.iv_back)
    ImageView back;
    @Bind(R.id.iv_message)
    TextView iv_message;
    @Bind(R.id.hor_recycleView)
    RecyclerView hor_recycleView;
    @Bind(R.id.liangfang_layout)
    RelativeLayout liangfang_layout;
    @Bind(R.id.xczp_layout)
    RelativeLayout xczp_layout;
    @Bind(R.id.fangan_layout)
    RelativeLayout fangan_layout;
    @Bind(R.id.xgt_layout)
    RelativeLayout xgt_layout;
    @Bind(R.id.sgt_layout)
    RelativeLayout sgt_layout;
    @Bind(R.id.bj_layout)
    RelativeLayout bj_layout;
    @Bind(R.id.hetong_layout)
    RelativeLayout hetong_layout;
    @Bind(R.id.tsjy_layout)
    RelativeLayout tsjy_layout;
    @Bind(R.id.zchz_layout)
    RelativeLayout zchz_layout;
    @Bind(R.id.hsjs_layout)
    RelativeLayout hsjs_layout;
    @Bind(R.id.jgt_layout)
    RelativeLayout jgt_layout;
    @Bind(R.id.sao_yi_sao)
    ImageView sao_yi_sao;
    @Bind(R.id.dingdan_img)
    ImageView dingdan_img;
    @Bind(R.id.cpt_layout)
    RelativeLayout cpt_layout;
    @Bind(R.id.sh_layout)
    RelativeLayout sh_layout;
    @Bind(R.id.smx_layout)
    RelativeLayout smx_layout;
    @Bind(R.id.more_layout)
    RelativeLayout more_layout;
    @Bind(R.id.layout2)
    LinearLayout layout2;
    private PopupWindow window;
    private ArrayList<String> title_list = new ArrayList<>();
    private ArrayList<Integer> title_icon_list = new ArrayList<>();

    @Override
    protected int getFragmentLayout() {
        return R.layout.minefragment;
    }

    @Override
    protected void FragmentInitData() {
        /*RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        requestOptions.placeholder(R.mipmap.icon);
        requestOptions.error(R.mipmap.icon);*/
        if (showPopup != null) {
            //showPopup.setBackgroundColor(getResources().getColor(R.color.colorcjs));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
        //设置状态栏文字颜色及图标为淺色
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        tvTitle.setText("我");
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setImageResource(R.mipmap.liaotian);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProIMWebActivity.class));
                //startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });
        back.setVisibility(View.VISIBLE);
        //back.setImageResource(R.mipmap.liaotian);
        back.setImageResource(R.mipmap.new_shezhi);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingsActivity.class));

            }
        });

        //设置横向我的订单
        //LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        hor_recycleView.setLayoutManager(manager);
        title_list.add("图纸");
        title_list.add("材料");
        title_list.add("工艺");
        //title_list.add("家具");
        title_icon_list.add(R.mipmap.tuzhi_new);
        title_icon_list.add(R.mipmap.cailiao_new);
        title_icon_list.add(R.mipmap.gongyi_new);
        //title_icon_list.add(R.mipmap.jiaju)  ;
        HorAadapter adapter = new HorAadapter(getContext(), title_list, title_icon_list);
        hor_recycleView.setAdapter(adapter);
        adapter.setListDataCallBack(new HorAadapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                if (position == 1) {
                    //直接跳转材料,供应商界面
                    /*startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag",4)
                             .putExtra("name","2")
                            .putExtra("title","材料")
                    );*/
                    ToastUtil.getInstance().toastCentent("暂未开启");
                } else if (position == 0) {
                    startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 0)
                            .putExtra("title", "图纸")
                    );
                }else if(position == 2){
                    ToastUtil.getInstance().toastCentent("暂未开启");
                }
                //ShowPopuWindow(position);
            }
        });
        jumpActivity();
    }

    private void ShowPopuWindow(int position) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.mine_popu, null, false);
        window = new PopupWindow(getActivity());
        window.setContentView(contentView);
        window.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setHeight(130);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        //window.showAsDropDown(hor_recycleView);
        window.showAtLocation(hor_recycleView, Gravity.CENTER, 0, -200);
        RelativeLayout quanbu = (RelativeLayout) contentView.findViewById(R.id.quanbu);
        RelativeLayout jinxingzhong = (RelativeLayout) contentView.findViewById(R.id.jinxingzhong);
        RelativeLayout daiquren = (RelativeLayout) contentView.findViewById(R.id.daiqueren);
        RelativeLayout daipingjia = (RelativeLayout) contentView.findViewById(R.id.daipingjia);
        RelativeLayout gongyingshang = (RelativeLayout) contentView.findViewById(R.id.gongyingshang);
        RelativeLayout daipeisong = (RelativeLayout) contentView.findViewById(R.id.daipeisong);
        if (position == 1) {
            gongyingshang.setVisibility(View.VISIBLE);
            daipeisong.setVisibility(View.VISIBLE);
        } else {
            gongyingshang.setVisibility(View.GONE);
            daipeisong.setVisibility(View.GONE);
        }
        quanbu.setOnClickListener(this);
        jinxingzhong.setOnClickListener(this);
        daiquren.setOnClickListener(this);
        daipingjia.setOnClickListener(this);
        gongyingshang.setOnClickListener(this);
        daipeisong.setOnClickListener(this);
    }

    //popuWindow点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quanbu:
                ToastUtil.getInstance().toastCentent("点击了全部");
                startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 0));
                break;
            case R.id.jinxingzhong:
                ToastUtil.getInstance().toastCentent("点击了进行中");
                startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 1));
                break;
            case R.id.daiqueren:
                ToastUtil.getInstance().toastCentent("点击了待确认");
                startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 2));
                break;
            case R.id.daipingjia:
                ToastUtil.getInstance().toastCentent("点击了待评价");
                startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 3));
                break;
            case R.id.gongyingshang:
                ToastUtil.getInstance().toastCentent("点击了供应商");
                startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 4)
                        .putExtra("name", "2"));
                break;
            case R.id.daipeisong:
                ToastUtil.getInstance().toastCentent("点击了待配送");
                startActivity(new Intent(getActivity(), MyDingDanActivity.class).putExtra("tag", 5)
                        .putExtra("name", "2")
                );
                break;
        }
    }

    //我的项目点击事件
    private void jumpActivity() {
        //量房
        liangfang_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), KeHuHomeLiangFangDataActivity.class).putExtra("title", "量房"));
                startActivity(new Intent(getActivity(), KeHuMessageNewActivity.class));
            }
        });
        //现场照片
        xczp_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KeHuHomeLiangFangXianChangActivity.class));
            }
        });
        //方案
        fangan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "3")
                        .putExtra("name", "方案"));
            }
        });
        //效果图
        xgt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FangAnXiaoGuoActivity.class));
            }
        });
        //施工图
        sgt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FangAnShiGongActivity.class)
                        .putExtra("title", "施工图"));
            }
        });
        //报价
        bj_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoreHomeActivity.class)
                        .putExtra("type", "4")
                        .putExtra("name", "报价"));
            }
        });
        //签约
        hetong_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
//                startActivity(new Intent(getActivity(), KeHuHomeWebViewActivity.class)
//                        .putExtra("url", ApiEngine.ZAAPP_BASE + "Customer/contractTemplate?rwdId=" + value)
//                        .putExtra("title", "合同模板"));
                startActivity(new Intent(getActivity(), QianYueActivity.class));
            }
        });
        //交流投诉
        tsjy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JiaoLiuTouSuActivity.class));
            }
        });
        //再次合作
        zchz_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), HeZuoTiJiaoActivity.class));
                startActivity(new Intent(getActivity(), ZaiCiHeZuoActivity.class));
            }
        });
        //换设计师
        hsjs_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HuanRenActivity.class));

            }
        });
        //扫一扫
        sao_yi_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QRZhongJianYeActivity.class));
            }
        });
        //账单详情
        dingdan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ZhangDanJiLuActivity.class));

            }
        });
        //结构图
        jgt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FangAnJieGouActivity.class));
            }
        });
        //彩平图
        cpt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FangAnCaiPingActivity.class));
            }
        });
        //水电图,原先手绘
        sh_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), FangAnShouHuiActivity.class));
                startActivity(new Intent(getActivity(), FangAnShiGongActivity.class)
                        .putExtra("title", "水电图")
                );
            }
        });
        //su模型
        smx_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FangAnSUMoXingActivity.class));
            }
        });
        //更多
        more_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //smx_layout.setVisibility(View.VISIBLE);
                bj_layout.setVisibility(View.VISIBLE);
                more_layout.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
            HashMap<String, String> map = new HashMap<>();
            map.put("rwdId", value);
            mPresenter.getUnreadData(map);//获取未读消息
            getImMessage();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        /*ivKefu.setVisibility(View.VISIBLE);
        ivKefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProIMWebActivity.class);
                startActivity(intent);
            }
        });*/
        initUserData();
        initUserMessageData();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        HashMap<String, String> map = new HashMap<>();
        map.put("rwdId", value);
        mPresenter.getUnreadData(map);//获取未读消息
        //  mPresenter.loadCustomerInformation(App.orderNo);
        getImMessage();
    }


    private void getImMessage() {
        Map map = new HashMap();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        //map.put("userName", value);
        OkhttpUtils.doGet("https://chat.wenes.cn/index/getMsgCountFromRedis?userName=" + value, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 0) {
                                UserMessageBean bean = JSONUtils.toObject(string, UserMessageBean.class);
                                int count = bean.getBody().getCount();
                                if (count != 0) {
                                    iv_message.setVisibility(View.VISIBLE);
                                    iv_message.setText(count + "");
                                } else {
                                    iv_message.setVisibility(View.GONE);
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
    protected MinePresenter onCreatePresenter() {
        return new MinePresenter(this);
    }

    @OnClick({R.id.rl_user_info, R.id.rl_integral, R.id.rl_settings, R.id.rl_wallet, R.id.rl_jifen, R.id.shoucang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_user_info:  // 用户信息
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                break;
            case R.id.rl_integral:
                startActivity(new Intent(getContext(), InformMessageActivity.class));
                break;
            case R.id.rl_settings:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
            case R.id.rl_wallet:
                showMessage("开发中，敬请期待！");
                break;
            case R.id.rl_jifen:
                showMessage("开发中，敬请期待！");
                break;
            case R.id.shoucang:  // 收藏案例
                startActivity(new Intent(getActivity(), ShouCangActivity.class));
                break;
        }
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
                            ShowUtils.Toastshort(getContext(), e.getMessage());
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
                                if (tvMineName != null) {
                                    tvMineName.setText(bodyBean.getU_name() == null ? "昵称" : bodyBean.getU_name());
                                }
                                if (tvMineCard != null) {
                                    tvMineCard.setText("账号：" + bodyBean.getA_account());
                                }
                                RequestOptions options = new RequestOptions();
                                options.placeholder(R.mipmap.userimage);
                                options.error(R.mipmap.userimage);
                                options.centerCrop();
                                options.circleCrop();

                                FragmentActivity activity = getActivity();
                                if (activity != null) {
                                    Glide.with(activity).load(bodyBean.getImage()).apply(options).into(rivMine);
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
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void loadCustomerInformation(MineUserInfo.BodyBean bodyBean) {
        //rivMine
        String name = bodyBean.getName();
        String xueli = bodyBean.getXueli();
        String mavatar = bodyBean.getMavatar();
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.userimage);
        options.error(R.mipmap.userimage);
        options.centerCrop();
        options.circleCrop();
        Glide.with(this).load(mavatar).apply(options).into(rivMine);
    }

    @Override
    public void showMessage(String message) {
        //showToast(message);
    }

    /**
     * 未读信息获取成功
     */
    @Override
    public void getUnreadData(UnreadBean unreadBean) {
        String str = JSONUtils.toString(unreadBean);
        PrefUtils.putValue(getActivity(), Constants.UnreadData, str);
    }

    /**
     * 未读信息获取失败
     *
     * @param erro
     */
    @Override
    public void getUnreadDataErro(String erro) {
        //ShowUtils.Toastlong(getActivity(), erro);
    }

    /**
     * 初始化用户未读信息
     */
    private void initUserMessageData() {
        Map<String, Object> map = new HashMap<>();
        String cardNo = "";
        if (App.tokenInfo != null) {
            cardNo = App.tokenInfo.getCardNo();
        }

        map.put("CardNo", TextUtils.isEmpty(cardNo) ? PrefUtils.getValue(getActivity(), Constants.PHOME) : cardNo);
        OkhttpUtils.doGet(ApiEngine.MSGCOUNT, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(e.getMessage());
                        }
                    });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("tag", string);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            MsgCount msgCount = JSONUtils.toObject(string, MsgCount.class);
                            if (msgCount.getStatusCode() == 0) {
                                int count = msgCount.getBody().getCount();
                                if (count > 0) {
                                    if (count > 99) {
                                        tvMessagenum.setBackgroundResource(R.mipmap.xiaoxi_3);
                                    } else if (count >= 10) {
                                        tvMessagenum.setBackgroundResource(R.mipmap.xiaoxi_2);
                                    } else {
                                        tvMessagenum.setBackgroundResource(R.mipmap.xiaoxi_1);
                                    }
                                    if (count > 99) {
                                        tvMessagenum.setText(count + "+");
                                    } else {
                                        tvMessagenum.setText(count + "");
                                    }
                                    tvMessagenum.setVisibility(View.VISIBLE);
                                } else {
                                    tvMessagenum.setVisibility(View.GONE);
                                }
                            } else {
                                showToast(msgCount.getStatusMsg() + "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });
    }


}
