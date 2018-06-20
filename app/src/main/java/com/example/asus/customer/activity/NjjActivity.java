package com.example.asus.customer.activity;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.customer.R;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.CheckPermissionsUitl;
import com.example.asus.customer.entity.IconInfo;
import com.example.asus.customer.entity.ProjectBean;
import com.example.asus.customer.fragment.CurrentFragment;
import com.example.asus.customer.fragment.DevelopmentFragment;
import com.example.asus.customer.fragment.FindFragment;
import com.example.asus.customer.fragment.HeTongFragment;
import com.example.asus.customer.fragment.MineFragment;
import com.example.asus.customer.fragment.MoreFragment;
import com.example.asus.customer.fragment.ProgrammeFragment;
import com.example.asus.customer.fragment.VolumeRoomFragment;
import com.example.asus.customer.mvp.contract.NjjContract;
import com.example.asus.customer.mvp.presenter.NjjPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/16.
 */

public class NjjActivity extends BaseActivity<NjjPresenter> implements NjjContract.View {


    @Bind(R.id.iv_tab_home)
    ImageView ivTabHome;
    @Bind(R.id.tv_tab_home)
    TextView tvTabHome;
    @Bind(R.id.rl_tab_home)
    RelativeLayout rlTabHome;
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
    private int[] iconNormal = new int[]{
            R.mipmap.ic_tab_hostpage,
            R.mipmap.ic_tab_more,
            R.mipmap.discoverybash,
            R.mipmap.mine_ash
    };

    private int[] iconPressed = new int[]{
            R.mipmap.ic_homenblue,
            R.mipmap.ic_morenblue,
            R.mipmap.discoveryblue,
            R.mipmap.min_blue
    };
    //指定Fragment的坐标
    private final int HOME_FRAGMENT = 0;
    private final int MORE_FRAGMENT = 1;
    private final int FIND_FRAGMENT = 2;
    private final int MINE_FRAGMENT = 3;

    private Fragment current;
    //Tab图标的集合
    private List<IconInfo> iconList;

    //碎片的集合
    private List<Fragment> fragmentList;
    private FindFragment findFragment;
    private MineFragment mineFragment;
    private DevelopmentFragment developmentFragment;
    //检测权限列表
    private String[] requestPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE
    };
    private VolumeRoomFragment volumeRoomFragment;
    private ProgrammeFragment programmeFragment;
    private HeTongFragment heTongFragment;
    private MoreFragment moreFragment;

    @Override
    public int getLayout() {
        return R.layout.njj_activity;
    }

    @Override
    public void initData() {

        mPresenter.getProjectStatus(App.orderNo);
    }

    private void initFragment(String start) {
        App.starts = start;
        fragmentList = new ArrayList<>();
        if (App.starts.equals("1") || App.starts.equals("2")) {//量房
            if (volumeRoomFragment == null) {
                volumeRoomFragment = new VolumeRoomFragment();
                Log.e("tag", "==========");
            }
            fragmentList.add(volumeRoomFragment);
        } else if (App.starts.equals("3")) {//方案
            if (programmeFragment == null) {
                programmeFragment = new ProgrammeFragment();
                Log.e("tag", "++++++++++++");
            }
            fragmentList.add(programmeFragment);
        } else if (App.starts.equals("4")) {//预算
            if (developmentFragment == null) {
                developmentFragment = new DevelopmentFragment();

            }
            fragmentList.add(developmentFragment);
        } else if (App.starts.equals("5")) {//合同
            if (heTongFragment == null) {
                heTongFragment = new HeTongFragment();
                Log.e("tag", "---------------");

            }
            fragmentList.add(heTongFragment);
        }
        if (findFragment == null) {
            findFragment = new FindFragment();
        }
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        if (moreFragment == null) {
            moreFragment = new MoreFragment();
        }
        fragmentList.add(moreFragment);
        fragmentList.add(findFragment);
        fragmentList.add(mineFragment);
    }

    private void initIcon() {
        //初始化iconList数据集合
        iconList = new ArrayList<>();
        //将图标添加到集合中
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
        iconList.get(position).getTextView().setTextColor(this.getResources().getColor(R.color.colorPrimary));
    }

    @OnClick({R.id.rl_tab_home, R.id.rl_tab_wallet, R.id.rl_tab_find, R.id.rl_tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_tab_home:
                showFragment(fragmentList.get(HOME_FRAGMENT), HOME_FRAGMENT);
                break;
            case R.id.rl_tab_wallet:
                showFragment(fragmentList.get(MORE_FRAGMENT), MORE_FRAGMENT);
                break;
            case R.id.rl_tab_find:
                showFragment(fragmentList.get(FIND_FRAGMENT), FIND_FRAGMENT);
                break;
            case R.id.rl_tab_mine:
                showFragment(fragmentList.get(MINE_FRAGMENT), MINE_FRAGMENT);
                break;
        }
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
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void getProjectStatus(ProjectBean projectBean) {
        App.starts = projectBean.getStatusMsg();
        initFragment(projectBean.getStatusMsg());
        initIcon();
        //加载默认显示碎片
        showFragment(fragmentList.get(HOME_FRAGMENT), HOME_FRAGMENT);
        //权限检测
        CheckPermissionsUitl.checkPermissions(this, requestPermissions);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime=0;
    private void exit() {


        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(),
                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }
        else{
                finish();
                System.exit(0);
            }
        }
    }

