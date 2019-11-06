package com.example.asus.customer.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.home.KeHuHomeWebViewActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.CheckPermissionsUitl;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.IconInfo;
import com.example.asus.customer.entity.ObjectDataBean;
import com.example.asus.customer.entity.ProjectBean;
import com.example.asus.customer.fragment.DevelopmentFragment;
import com.example.asus.customer.fragment.FailFragment;
import com.example.asus.customer.fragment.FuWuFragment;
import com.example.asus.customer.fragment.HeTongFragment;
import com.example.asus.customer.fragment.KehuHomeFragment;
import com.example.asus.customer.fragment.KehuNewHomeFragment;
import com.example.asus.customer.fragment.MineFragment;
import com.example.asus.customer.fragment.MineNewFragment;
import com.example.asus.customer.fragment.MoreFragment;
import com.example.asus.customer.fragment.NewMoreFragment;
import com.example.asus.customer.fragment.NewsFragment;
import com.example.asus.customer.fragment.ProgrammeFragment;
import com.example.asus.customer.fragment.ServiceFragment;
import com.example.asus.customer.fragment.VolumeRoomFragment;
import com.example.asus.customer.mvp.contract.NjjContract;
import com.example.asus.customer.mvp.presenter.NjjPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/16.
 * 瑞祥客户  主 activity
 */

public class NjjActivity extends BaseActivity<NjjPresenter> implements NjjContract.View {
    @Bind(R.id.iv_tab_home)
    ImageView ivTabHome;
    @Bind(R.id.tv_tab_home)
    TextView tvTabHome;
    @Bind(R.id.iv_new_home)
    ImageView iv_new_home;
    @Bind(R.id.tv_new_home)
    TextView tv_new_home;
    @Bind(R.id.rl_tab_home)
    RelativeLayout rlTabHome;
    @Bind(R.id.new_home)
    RelativeLayout newHome;
    @Bind(R.id.iv_tab_wallet)
    ImageView ivTabWallet;
    @Bind(R.id.tv_tab_wallet)
    TextView tvTabWallet;
    @Bind(R.id.rl_tab_wallet)
    RelativeLayout rlTabWallet;
    @Bind(R.id.iv_tab_find)
    ImageView ivTabFind;
    @Bind(R.id.tv_tab_find)
    TextView tvTabFind;
    @Bind(R.id.rl_tab_find)
    RelativeLayout rlTabFind;
    @Bind(R.id.iv_tab_mine)
    ImageView ivTabMine;
    @Bind(R.id.tv_tab_mine)
    TextView tvTabMine;
    @Bind(R.id.rl_tab_mine)
    RelativeLayout rlTabMine;
    @Bind(R.id.tv_msgnum)
    TextView tvMsgnum;
    @Bind(R.id.lin)
    LinearLayout lin;
    @Bind(R.id.image_yindao)
    ImageView imageYindao;
    @Bind(R.id.is_yindao)
    ImageView isYindao;
    @Bind(R.id.yindao_relative)
    RelativeLayout yindaoRelative;
    private int[] iconNormal = new int[]{
            R.mipmap.shouye_gray,
            R.mipmap.fuwu_gray,
            R.mipmap.xiangmu_gray,
            R.mipmap.dingdan_gray,
            R.mipmap.my_gray
    };

    private int[] iconPressed = new int[]{
            R.mipmap.shouye_blue,
            R.mipmap.fuwu_blue,
            R.mipmap.xiangmu_blue,
            R.mipmap.dingdan_blue,
            R.mipmap.my_blue
    };
    //指定Fragment的坐标
    private final int HOME_NEW_FRAGMENT = 0;
    private final int HOME_FRAGMENT = 1;
    private final int MORE_FRAGMENT = 2;
    private final int FIND_FRAGMENT = 3;
    private final int MINE_FRAGMENT = 4;

    private Fragment current;
    //Tab图标的集合
    private List<IconInfo> iconList;

    //碎片的集合
    private List<Fragment> fragmentList;
    //    private FindFragment findFragment;
    //    private MineFragment mineFragment;
    private MineNewFragment mineFragment;
    private DevelopmentFragment developmentFragment;
    //检测权限列表
    private String[] requestPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE
    };
    private NewMoreFragment kehuxiangmu;
    private KehuHomeFragment kehudingdan;
    private FuWuFragment kehuFuwu;
    private KehuNewHomeFragment kehushouye;

    @Override
    public int getLayout() {
        return R.layout.njj_activity;
    }

    private int is_yindao = 0;

    @Override
    public void initData() {
//        isYindao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (is_yindao == 0) {
//                    is_yindao = 1;
//                    isYindao.setImageResource(R.mipmap.yuan_xuanzhong);
//                } else if (is_yindao == 1) {
//                    is_yindao = 0;
//                    isYindao.setImageResource(R.mipmap.yuan_weixuanzhong);
//                }
//            }
//        });
//        final SharedPreferences shared = getSharedPreferences("is", MODE_PRIVATE);
//        boolean isHomeImage = shared.getBoolean("is HomeImage", true);
//        if (isHomeImage) {
//            imageYindao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    yindaoRelative.setVisibility(View.INVISIBLE);
//                    if (is_yindao == 1) {
//                        SharedPreferences.Editor edit = shared.edit();
//                        edit.putBoolean("isHomeImage", false);
//                        edit.commit();
//                    }
//                }
//            });
//
//        } else {
//            yindaoRelative.setVisibility(View.INVISIBLE);
//        }


//        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
//        mPresenter.getLoginTime(value);

        App.is_home = true;
//        String isLogin = PrefUtils.getValue(this, "isLogin");//是否是手动登录
//        if (!(TextUtils.isEmpty(isLogin)) && isLogin.equals("1")) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                initPopupWindow();
//                            }
//                        });
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }


        initFragment();
        initIcon();
        //加载默认显示碎片
        showFragment(fragmentList.get(HOME_NEW_FRAGMENT), HOME_NEW_FRAGMENT);
        //权限检测
        CheckPermissionsUitl.checkPermissions(this, requestPermissions);
        fullScreen(this);

        if(mineFragment != null){
            mineFragment.setOnButtonClick(new MineNewFragment.OnButtonClick() {
                @Override
                public void onClick(View view) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        //setWindowStatusBarColor(this, R.color.white);
                        setWindowStatusBarColor(NjjActivity.this, R.color.colorWhite);
                        showFragment(fragmentList.get(FIND_FRAGMENT), FIND_FRAGMENT);
                }
            });
        }
    }

    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏为透明，否则在部分手机上会呈现系统默认的浅灰色
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以考虑设置为透明色
                //window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                //attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 初始化提示弹窗
     */
    private void initPopupWindow() {
        PrefUtils.putValue(this, "isLogin", "0");
        View view = LayoutInflater.from(this).inflate(R.layout.guang_gao_pop, null);
        Button yes = view.findViewById(R.id.guanggao_yes);
        ImageView close = view.findViewById(R.id.guang_gao_close);
        AutoUtils.auto(view);
        final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(lin, Gravity.CENTER, 0, 0);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = PrefUtils.getValue(NjjActivity.this, Constants.PN_Onumber);
                startActivity(new Intent(NjjActivity.this, KeHuHomeWebViewActivity.class)
                        .putExtra("url", "https://zaapp.rxjy.com/Customer/AboutUs?rawId=" + value)
                        .putExtra("title", "活动"));
                popupWindow.dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    /**
     * 初始化各个界面
     */
    private void initFragment() {

        fragmentList = new ArrayList<>();

        if (kehushouye == null) {
            kehushouye = new KehuNewHomeFragment();
        }

        if (kehudingdan == null) {
            kehudingdan = new KehuHomeFragment();
            //kehuHomeFragment = new NewDingDanFragment();
        }

        if (kehuxiangmu == null) {
            kehuxiangmu = new NewMoreFragment();
        }
        if (mineFragment == null) {
            //我
            mineFragment = new MineNewFragment();
        }
        /*if (moreFragment == null) {
            //瑞祥装饰
            moreFragment = new MoreFragment();
        }*/
        if (kehuFuwu == null) {
            //服务
            kehuFuwu = new FuWuFragment();
        }
        //首页
        fragmentList.add(kehushouye);
        //服务
        fragmentList.add(kehuFuwu);
        //项目
        fragmentList.add(kehuxiangmu);
        //fragmentList.add(moreFragment);
        //订单
        fragmentList.add(kehudingdan);
        //我的
        fragmentList.add(mineFragment);

    }

    private void initIcon() {
        //初始化iconList数据集合
        iconList = new ArrayList<>();
        //将图标添加到集合中
        iconList.add(new IconInfo(iv_new_home, tv_new_home));
        iconList.add(new IconInfo(ivTabHome, tvTabHome));
        iconList.add(new IconInfo(ivTabWallet, tvTabWallet));
        iconList.add(new IconInfo(ivTabFind, tvTabFind));
        iconList.add(new IconInfo(ivTabMine, tvTabMine));
    }

    @Override
    protected NjjPresenter onCreatePresenter() {
        return new NjjPresenter(this);
    }

    /**
     * 显示指定Fragment界面的方法
     *
     * @param fragment
     * @param position
     */
    private void showFragment(Fragment fragment, int position) {
        JumpFragment(fragment);
        setIcon(position);
    }

    /**
     * 加载指定Fragment的方法
     *
     * @param fragment
     */
    private void JumpFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction action = manager.beginTransaction();
        if (!fragment.isAdded()) {
            action.add(R.id.fl_main, fragment);
        }
        if (current != null) {
            action.hide(current);
        }
        action.show(fragment);
        action.commit();
        current = fragment;
    }

    /**
     * 设置图标点击效果
     *
     * @param position
     */
    private void setIcon(int position) {
        for (int i = 0; i < iconList.size(); i++) {
            iconList.get(i).getImageView().setImageResource(iconNormal[i]);
            iconList.get(i).getTextView().setTextColor(this.getResources().getColor(R.color.colorGrayDark));
        }
        iconList.get(position).getImageView().setImageResource(iconPressed[position]);
        iconList.get(position).getTextView().setTextColor(this.getResources().getColor(R.color.colorBlack));
    }


    @OnClick({R.id.rl_tab_home, R.id.rl_tab_wallet, R.id.rl_tab_find, R.id.rl_tab_mine, R.id.new_home})
    public void onViewClicked(View view) {
        String cardNo = MySharedPreferences.getInstance().getCardNo();
        switch (view.getId()) {
            case R.id.new_home:
//                setWindowStatusBarColor(this, R.color.colorcjs);
                //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                fullScreen(this);
                showFragment(fragmentList.get(HOME_NEW_FRAGMENT), HOME_NEW_FRAGMENT);
                break;
            case R.id.rl_tab_home:
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(NjjActivity.this, TextLoginActivity.class));
                } else {
                    setWindowStatusBarColor(this, R.color.colorWhite);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    showFragment(fragmentList.get(HOME_FRAGMENT), HOME_FRAGMENT);
                }
                break;
            case R.id.rl_tab_wallet:
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(NjjActivity.this, TextLoginActivity.class));
                } else {
                    setWindowStatusBarColor(this, R.color.colorWhite);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    showFragment(fragmentList.get(MORE_FRAGMENT), MORE_FRAGMENT);
                }
                break;
            case R.id.rl_tab_find:
                if (TextUtils.isEmpty(cardNo)) {
                    startActivity(new Intent(NjjActivity.this, TextLoginActivity.class));
                } else {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    //setWindowStatusBarColor(this, R.color.white);
                    setWindowStatusBarColor(this, R.color.colorWhite);
                    showFragment(fragmentList.get(FIND_FRAGMENT), FIND_FRAGMENT);
                }
                break;
            case R.id.rl_tab_mine:
                    setWindowStatusBarColor(this, R.color.colorWhite);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                SharedPreferences preferences = getSharedPreferences(MSG_NUM, MODE_PRIVATE);
//                preferences.edit().putInt(msgnum, 0).commit();
                    ShowMsgNum(0);
                    showFragment(fragmentList.get(MINE_FRAGMENT), MINE_FRAGMENT);
                break;
        }
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
     * 显示dialog
     */
    @Override
    public void showDialog() {
        showLoading();
    }

    /**
     * 隐藏dialog
     */
    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void showMessage(String message) {
        fragmentList = new ArrayList<>();

        if (kehushouye == null) {
            //新首页
            kehushouye = new KehuNewHomeFragment();
        }

        if (kehudingdan == null) {
            //首页
            kehudingdan = new KehuHomeFragment();
            //kehuHomeFragment = new NewDingDanFragment();
        }

        if (kehuxiangmu == null) {
            kehuxiangmu = new NewMoreFragment();
        }


        if (mineFragment == null) {
            mineFragment = new MineNewFragment();
        }
//        if (moreFragment == null) {
//            moreFragment = new MoreFragment();
//        }
        if (kehuFuwu == null) {
            kehuFuwu = new FuWuFragment();
        }
        //首页
        fragmentList.add(kehushouye);
        //服务
        fragmentList.add(kehuFuwu);
        //项目
        fragmentList.add(kehudingdan);
        //fragmentList.add(moreFragment);
        //订单
        fragmentList.add(kehuxiangmu);
        //我的
        fragmentList.add(mineFragment);
        initIcon();
        //加载默认显示碎片
        showFragment(fragmentList.get(HOME_NEW_FRAGMENT), HOME_NEW_FRAGMENT);
        //权限检测
        CheckPermissionsUitl.checkPermissions(this, requestPermissions);
    }

    @Override
    public void getProjectStatus(ProjectBean projectBean) {
        App.starts = projectBean.getStatusMsg();
    }

    @Override
    public void getLoginTime() {

    }

    /**
     * 登录超时
     */
    @Override
    public void getLoginTimeErro(String erro) {

    }

    /**
     * 返回键按两次才允许退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;

    /**
     * 退出App
     */
    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(),
                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 显示未读消息
     */
    public void ShowMsgNum(int num) {
        if (num > 0) {
            tvMsgnum.setVisibility(View.VISIBLE);
        } else {
            tvMsgnum.setVisibility(View.GONE);
        }
    }
}

