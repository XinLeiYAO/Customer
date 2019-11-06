package com.example.asus.customer.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.utils.AccountPop;
import com.example.asus.customer.commons.utils.AutoUtils;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.NetWorkUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.SystemUtil;
import com.example.asus.customer.commons.utils.ZJson;
import com.example.asus.customer.entity.AccountInfo;
import com.example.asus.customer.entity.CheckInfo;
import com.example.asus.customer.entity.CodeLoginBean;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.OddNumbersBean;
import com.example.asus.customer.entity.SaoMaLoginBean;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.mvp.contract.LoginContract;
import com.example.asus.customer.mvp.presenter.LoginPresenter;
import com.example.asus.customer.zbar.CaptureActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.commonsdk.debug.I;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by asus on 2018/5/29.
 */

public class TextLoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private static final int REQUEST_CODE_SCAN = 66;
    @Bind(R.id.login_prompt)
    TextView loginPrompt;
    @Bind(R.id.phone_jiantou)
    ImageView phoneJiantou;
    @Bind(R.id.phone_xian)
    View phoneXian;
    @Bind(R.id.psw_xian)
    View pswXian;
    @Bind(R.id.banben)
    TextView banben;
    //    @Bind(R.id.tv_pwd)
//    TextView tvPwd;
    @Bind(R.id.ed_phone)
    EditText ed_phone;
    @Bind(R.id.ed_vitifycode)
    EditText ed_vitifycode;
    @Bind(R.id.ed_pwd)
    EditText ed_pwd;
    @Bind(R.id.tv_vitifycodeget)
    TextView tv_vitifycodeget;
    @Bind(R.id.tv_forgetpwd)
    TextView tv_forgetpwd;
    @Bind(R.id.tv_saoyisao)
    ImageView tv_saoyisao;
    @Bind(R.id.btn_next)
    Button btn_next;
    @Bind(R.id.rl_veritycode)
    RelativeLayout rl_veritycode;
    @Bind(R.id.rl_phone)
    RelativeLayout rl_phone;
    @Bind(R.id.rl_pwd)
    RelativeLayout rl_pwd;
    @Bind(R.id.ll_toforget)
    LinearLayout ll_toforget;
    @Bind(R.id.code_password_login_text)
    TextView codePasswrodLoginText;//切换密码 和 验证码登录
    @Bind(R.id.code_login_name)
    TextView codeLoginName;
    @Bind(R.id.code_login_code)
    EditText codeLoginCode;//验证码 输入框
    @Bind(R.id.code_login_getcode)
    TextView codeLoginGetcode;
    @Bind(R.id.code_login_relativ)
    RelativeLayout codeLoginRelativ;//验证码 事件
    @Bind(R.id.code_login_xian)
    View codeLoginXian;
    @Bind(R.id.login_psd_line)
    View login_psd_line;
    @Bind(R.id.text_login_top)
    TextView text_login_top;
    @Bind(R.id.forget_line)
    TextView forget_line;
    @Bind(R.id.tv_regist)
    TextView tv_regist;
    @Bind(R.id.tv_xieyi)
    TextView tv_xieyi;
    @Bind(R.id.tv_yinsi)
    TextView tv_yinsi;
    @Bind(R.id.iv_login_back)
    ImageView ivBack;
    private String TAG = "LoginActivity";

    String phonenum, veritycode, pwdnum;
    int logintype = 0;//0：验证码登  1：密码登录  用于区分是 激活状态还是 登录状态
    private int isCode_Password = 2;//1 是密码登录 2 是验证码登录  区分 当前登录的方式

    private ArrayList<AccountInfo> accountList;
    private AccountPop accountPop;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * 发送信息到状态栏
     *
     * @param title
     * @param text
     */
    public void sendSubscribeMsg(String title, String text) {
        NotificationManager manager = (NotificationManager) TextLoginActivity.this.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(TextLoginActivity.this, "chat")
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(TextLoginActivity.this.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance, String title, String text) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) TextLoginActivity.this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        sendSubscribeMsg(title, text);
    }

    /**
     * 关闭软键盘
     */
    public static void hideSoftInput(Activity mContext) {
        View view = mContext.getWindow().peekDecorView();

        if (view != null) {

            InputMethodManager inputmanger = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 初始化edittext 取消焦点
     */
    private void initLine() {
        ed_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    phoneXian.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    phoneXian.setEnabled(false);
                }
            }
        });
        ed_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //此处为得到焦点时
                    pswXian.setEnabled(true);
                } else {
                    //此处为失去焦点时
                    pswXian.setEnabled(false);
                }
            }
        });
    }

    /**
     * 初始化数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {
        banben.setText("版本 V" + App.getVersionName());
        App.is_home = true;
        MySharedPreferences instance = MySharedPreferences.getInstance();
        String userPhone = instance.getUserPhone();
        String arrayPhoneJson = instance.getArrayPhoneJson();
        accountList = JSONUtils.toList(arrayPhoneJson, new TypeToken<List<AccountInfo>>() {
        }.getType());
        rl_veritycode.setVisibility(View.GONE);
//        btn_next.setVisibility(View.GONE);
//        rl_pwd.setVisibility(View.GONE);
//        tv_forgetpwd.setVisibility(View.GONE);
//        ll_toforget.setVisibility(View.GONE);
        ed_phone.addTextChangedListener(new MyEditListener(ed_phone));
        if (userPhone != null) {
            ed_phone.setText(userPhone);
        }
        String phone = getIntent().getStringExtra("phone");
        if(!TextUtils.isEmpty(phone)){
            ed_phone.setText(phone);
        }
        if (accountList == null) {
            accountList = new ArrayList<>();
        }
        accountPop = new AccountPop(this, R.layout.pop_account_layout, AutoUtils.getDisplayWidthValue(720), AutoUtils.getDisplayHeightValue(400));

        accountPop.setOnAccountPopClickListener(new AccountPop.OnAccountPopClickListener() {
            @Override
            public void userAccountChoice(AccountInfo data) {
                ed_phone.setText(data.getAccount());
            }

            @Override
            public void userAccountDel(AccountInfo data, int position) {
                accountList.remove(position);
                String arrayPhone = JSONUtils.toString(accountList);
                MySharedPreferences instance = MySharedPreferences.getInstance();
                instance.setHistory_Phone(arrayPhone);//保存历史账号集合
                accountPop.setAccountList(accountList);
                if (accountList.size() == 0) {
                    phoneJiantou.setVisibility(View.INVISIBLE);
                }
            }


        });

        accountPop.setAccountList(accountList);

        accountPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                phoneJiantou.setBackgroundResource(R.mipmap.xiajiantou);
            }
        });

        phoneJiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput(TextLoginActivity.this);
                phoneJiantou.setBackgroundResource(R.mipmap.shangjiantou);
                //设置默认获取焦点
                accountPop.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                accountPop.showAsDropDown(phoneXian, 0, 0);
                //如果窗口存在，则更新
                accountPop.update();
            }
        });
        initLine();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }

        tv_xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextLoginActivity.this,YinSiActivity.class)
                .putExtra("type","协议")
                );
            }
        });

        tv_yinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextLoginActivity.this,YinSiActivity.class)
                .putExtra("type","隐私")
                );
            }
        });

    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
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
    public void handlerMeaasg(Message msg) {
        super.handlerMeaasg(msg);
//        JPushInterface.setAlias(getApplicationContext(),
//                (String) msg.obj, mAliasCallback);

        JPushInterface.setAliasAndTags(getApplicationContext(),
                (String) msg.obj, null, mAliasCallback);
    }

    /**
     * 推送的监听
     */
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
//                    Log.e(TAG, "设置别名成功");
                    break;
                case 6002:
//                    Log.e(TAG, "设置别名失败");
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(Constants.MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
//                    Log.e(TAG, "设置别名失败" + code);
                    break;
            }
        }
    };

    /**
     * 登录
     *
     * @param cardNo
     */
    private void startLogin(String cardNo) {

        Map map = new HashMap();
        map.put("app_id", "");
        map.put("card_no", cardNo);
        map.put("landing_date", "");
        map.put("offline_date", "");
        map.put("locate_province_now", "");
        map.put("locate_city_now", "");
        map.put("a_equipment", Build.MODEL);//使用设备
        switch (NetWorkUtils.getAPNType(this)) {
            case 0:
                map.put("network_status", "");//网络状态
                break;
            case 1:
                map.put("network_status", "WIFI");//网络状态
                break;
            case 2:
                map.put("network_status", "2G");//网络状态
                break;
            case 3:
                map.put("network_status", "3G");//网络状态
                break;
            case 4:
                map.put("network_status", "4G");//网络状态
                break;
            default:
                break;
        }
        map.put("a_ip ", "");//IP地址
        map.put("id", "");
        map.put("flag", "");
        map.put("name", "");
        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        map.put("app_version_number", App.getVersionName());
        map.put("system_version_number", SystemUtil.getSystemVersion());
        map.put("mac_address", id);
        String toJSONMap = ZJson.toJSONMap(map);
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/json");

        Request request = new Request.Builder()
                .url(ApiEngine.RS_API_HOST + "actionapi/AppHome/AddlandingMessage")
                .post(RequestBody.create(MEDIA_TYPE_TEXT, toJSONMap))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
            }
        });

    }

    /**
     * |
     * 登录成功的回调
     *
     * @param bodyBean
     */
    @Override
    public void toLandingSuccess(NewUserInfoBean.BodyBean bodyBean) {
        //  loginPrompt.setVisibility(View.GONE);
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);

        startLogin(cardNo);
        App.body = bodyBean;
        //绑定推送别名
        String phone = null;
        if (App.body != null) {
            phone = App.body.getPhone();
        }
        mHandler.sendMessage(mHandler.obtainMessage(Constants.MSG_SET_ALIAS, phone));
        PrefUtils.putBooleanValue(this, Constants.IS_LOGIN, true);
//        startActivity(new Intent(this, NjjActivity.class));
        finish();
    }

    @Override
    public void showmessage(String message) {
        btn_next.setEnabled(true);
        dismissLoading();
        showLoadDialog(message);
    }


    /**
     * 无用
     *
     * @param checkInfo
     */
    @Override
    public void getCheckInfo(CheckInfo checkInfo) {
        switch (checkInfo.getStatusCode()) {
            case 0://用户-存在
                //    getphone = data.getBody().getPhone();
                btn_next.setText("登录");
                logintype = 0;
                btn_next.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.VISIBLE);
                tv_forgetpwd.setVisibility(View.VISIBLE);
//                ll_toforget.setVisibility(View.VISIBLE);
                rl_veritycode.setVisibility(View.GONE);
                ed_pwd.requestFocus();//默认方式获得焦点
                codePasswrodLoginText.setVisibility(View.VISIBLE);
                btn_next.setText("登录");
//                tvPwd.setText("密码");
                break;
            case 1://用户不存在,获取验证码注册
                logintype = 1;
                rl_veritycode.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.VISIBLE);
                tv_forgetpwd.setVisibility(View.GONE);
//                ll_toforget.setVisibility(View.GONE);
                codePasswrodLoginText.setVisibility(View.GONE);
                btn_next.setText("激活");
//                tvPwd.setText("设置密码");
                //loginPrompt.setVisibility(View.GONE);
                break;
            default:
//                loginPrompt.setText(checkInfo.getStatusMsg());
//                loginPrompt.setVisibility(View.VISIBLE);
                showLoadDialog(checkInfo.getStatusMsg());
                rl_veritycode.setVisibility(View.GONE);
                btn_next.setVisibility(View.GONE);
                rl_pwd.setVisibility(View.GONE);
                tv_forgetpwd.setVisibility(View.GONE);
//                ll_toforget.setVisibility(View.GONE);
                break;
        }
    }


    @OnClick({R.id.btn_next, R.id.tv_vitifycodeget, R.id.tv_forgetpwd, R.id.tv_saoyisao, R.id.code_password_login_text, R.id.code_login_getcode, R.id.tv_regist,R.id.iv_login_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.code_login_getcode://验证码登录  获取验证码
                mPresenter.getLoginCode(phonenum, "10001"); //postId 无所谓随便填(修改10001)
                break;
            case R.id.code_password_login_text://验证码 和 密码登录切换
                //不需要 判断 切换密码的时候 手机号是否输入完成  是否存在这个账号  是否显示密码输入框  （切换 登录状态方式 只有在账号输入完成并检测存在的时候在辉显示）。
                if (isCode_Password == 1) {
                    codeLoginCode.setText("");
                    //切换为 验证码登录
                    codePasswrodLoginText.setText("账号密码登录");
                    isCode_Password = 2;
                    text_login_top.setText("手机快捷登录");
                    rl_pwd.setVisibility(View.GONE);//隐藏密码    （没有隐藏输入验证码是因为 切换登录方式 只会在激活完成后显示，只显示密码的时候。）
                    login_psd_line.setVisibility(View.GONE);
                    codeLoginRelativ.setVisibility(View.VISIBLE);
                    codeLoginXian.setVisibility(View.VISIBLE);
                    forget_line.setVisibility(View.GONE);
                    tv_forgetpwd.setVisibility(View.GONE);
                } else if (isCode_Password == 2) {
                    ed_pwd.setText("");
                    //切换为 密码的登录
                    codePasswrodLoginText.setText("手机快捷登录");
                    text_login_top.setText("账号密码登录");
                    isCode_Password = 1;
                    rl_pwd.setVisibility(View.VISIBLE);
                    login_psd_line.setVisibility(View.VISIBLE);
                    forget_line.setVisibility(View.VISIBLE);
                    tv_forgetpwd.setVisibility(View.VISIBLE);
                    codeLoginRelativ.setVisibility(View.GONE);
                    codeLoginXian.setVisibility(View.GONE);

                }
                break;
            case R.id.btn_next:
                //TODO 在此判断 当前是 密码登录还是验证码登录
                //todo  基本 已完成 获取到验证码 后 登陆下  成功的回调  数据怎么保存  和 密码登录一样？
//                startActivity(new Intent(this, SupervisionMainActivity.class));
                pwdnum = ed_pwd.getText().toString();
                if (isCode_Password == 1) {//密码登录
                    Log.e(TAG, "onClick: ===========" + pwdnum + "===========" + phonenum);
                    veritycode = ed_vitifycode.getText().toString();
                    switch (logintype) {
                        case 1:
                            if (!phonenum.isEmpty() && !veritycode.isEmpty() && !pwdnum.isEmpty()) {
                                //请求登录
                                btn_next.setEnabled(false);
                                mPresenter.tokenLogin(phonenum, pwdnum, veritycode, "4");
                            } else if (phonenum.isEmpty()) {
                                ShowUtils.Toastshort(this, "请输入手机号！");
                            } else if (veritycode.isEmpty()) {
                                ShowUtils.Toastshort(this, "请输入验证码！");
                            } else if (pwdnum.isEmpty()) {
                                ShowUtils.Toastshort(this, "请输入密码！");
                            }
                            break;
                        case 0:
                            if (!phonenum.isEmpty() && !pwdnum.isEmpty()) {
                                //请求登录
                                btn_next.setEnabled(false);
                                mPresenter.tokenLogin(phonenum, pwdnum, "", "4");
                            } else if (phonenum.isEmpty()) {
                                ShowUtils.Toastshort(this, "请输入手机号！");
                            } else if (pwdnum.isEmpty()) {
                                ShowUtils.Toastshort(this, "请输入密码！");
                            }
                            break;
                    }
                } else if (isCode_Password == 2) {//验证码登录
                    String code = codeLoginCode.getText().toString();
                    if (!TextUtils.isEmpty(phonenum) && !TextUtils.isEmpty(code)) {
                        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
                        btn_next.setEnabled(false);
                        mPresenter.getCode_Login(phonenum, "", code, "4", "1");//验证码登录
                    } else if (TextUtils.isEmpty(phonenum)) {
                        ShowUtils.Toastshort(this, "请输入手机号！");
                    } else if (TextUtils.isEmpty(code)) {
                        ShowUtils.Toastshort(this, "请输入验证码！");
                    }
                }

                break;
            case R.id.tv_vitifycodeget://获取验证码 用于激活
                mPresenter.getTokenByCode(phonenum, "4");
                break;
            case R.id.tv_forgetpwd:
                startActivity(new Intent(this, ForgetPwdActivity.class).putExtra("phone", ed_phone.getText().toString()));
                break;
            case R.id.tv_saoyisao:
                //跳转到扫描登录
                goScan();
                break;
            case R.id.tv_regist:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.iv_login_back:
                finish();
                break;
        }
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    //相机
    private static final int PERMISSIONS_REQUEST_CODE_TAKE_PHOTO = 1;

    //二维码登录获取用户信息
    @Override
    public void getOddNumData(OddNumbersBean.BodyBean body) {

        PrefUtils.putValue(this, Constants.PHOME, body.getCard_no());
        PrefUtils.putValue(this, Constants.PASSWORD, body.getToken());
        PrefUtils.putValue(this, Constants.APPID, body.getApp_id());
        PrefUtils.putValue(this, Constants.MIMA, ed_pwd.getText().toString());

        //    loginPrompt.setVisibility(View.GONE);
        App.tokenInfo = new TokenInfo.BodyBean(body.getCard_no(), "", body.getToken(), body.getApp_id());
        App.loginStarts = 1;
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
        String token = PrefUtils.getValue(this, Constants.PASSWORD);
        mPresenter.getOddNumData2(body.getApp_id());
        mPresenter.landLogin(cardNo, token);
    }

    //密码登录获取信息
    @Override
    public void getOddNumData2(String body) {
        App.PN_Onumber = body;
        PrefUtils.putValue(this, Constants.PN_Onumber, body);
        App.loginStarts = 1;
//        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
//        String token = PrefUtils.getValue(this, Constants.PASSWORD);
//        mPresenter.landLogin(cardNo, token);
    }

    /**
     * 开始发送验证
     *
     * @param statusMsg
     */
    @Override
    public void getLoginCode(String statusMsg) {
        new Thread() {//开始发送验证码  用于登录
            @Override
            public void run() {
                for (int i = 60; i >= 0; --i) {
                    Message msg = codeLoginHandler.obtainMessage();
                    msg.arg1 = i;
                    codeLoginHandler.sendMessage(msg);
                    SystemClock.sleep(1000);     //  1s
                }
            }
        }.start();
        Toast.makeText(this, statusMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getLoginCodeErro(String statusMsg) {
        ShowUtils.Toastshort(this, statusMsg);
    }

    /**
     * 登录成功回调
     *
     * @param codeLoginBean
     */
    @Override
    public void getCode_Login(CodeLoginBean codeLoginBean) {
        CodeLoginBean.BodyBean body = codeLoginBean.getBody();
        MySharedPreferences instance = MySharedPreferences.getInstance();
        instance.setUserPhone(ed_phone.getText().toString());

        String arrayPhoneJson = instance.getArrayPhoneJson();//获取历史账号集合
        ArrayList<AccountInfo> arrayPhoneList = JSONUtils.toList(arrayPhoneJson, new TypeToken<List<AccountInfo>>() {
        }.getType());
        if (arrayPhoneList != null) {
            int i = 0;
            for (i = 0; i < arrayPhoneList.size(); i++) {
                if (arrayPhoneList.get(i).getAccount().equals(body.getAccount())) {
                    break;
                }
            }
            if (i == arrayPhoneList.size()) {
                arrayPhoneList.add(new AccountInfo(body.getAccount()));
            }
        } else {
            arrayPhoneList = new ArrayList<AccountInfo>();
            arrayPhoneList.add(new AccountInfo(body.getAccount()));
        }
        String arrayPhone = JSONUtils.toString(arrayPhoneList);
        instance.setHistory_Phone(arrayPhone);//保存历史账号集合
        //   loginPrompt.setVisibility(View.GONE);
        PrefUtils.putValue(this, Constants.PHOME, body.getCardNo());
        PrefUtils.putValue(this, Constants.PASSWORD, body.getToken());
        PrefUtils.putValue(this, Constants.PHONENUM, body.getAccount());
        PrefUtils.putValue(this, Constants.APPID, body.getAppId());
        PrefUtils.putValue(this, Constants.MIMA, ed_pwd.getText().toString());
        PrefUtils.putValue(this, "isLogin", "1");
        TokenInfo.BodyBean bodyBean = new TokenInfo.BodyBean(body.getCardNo(), body.getAccount(), body.getToken(), body.getAppId());
        App.tokenInfo = bodyBean;
        App.setAlias(body.getAccount());
        mPresenter.getOddNumData2(body.getAppId());
        App.loginStarts = 1;
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
        String token = PrefUtils.getValue(this, Constants.PASSWORD);
        mPresenter.landLogin(cardNo, token);
    }

    /**
     * 获取数据失败的回调
     *
     * @param erro
     */
    @Override
    public void getCode_LoginErro(String erro) {
        dismissLoading();
        btn_next.setEnabled(true);
        boolean networkConnected = NetWorkUtils.isNetworkConnected(TextLoginActivity.this);
        if (!networkConnected) {
            showLoadDialog("网络异常");
        } else {
            showLoadDialog(erro);
        }
    }

    /**
     * 扫码成功的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                switch (resultCode) {
                    case RESULT_OK:
                        Bundle bundle = data.getExtras();
                        String dataStringExtra = bundle.getString(CaptureActivity.EXTRA_STRING);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(dataStringExtra);
                            String event = jsonObject.getString("event");
                            if (!TextUtils.isEmpty(event)) {
                                if (event.equals("扫码免登陆")) {
                                    Gson gson = new Gson();
                                    SaoMaLoginBean saoMaLoginBean = gson.fromJson(dataStringExtra, SaoMaLoginBean.class);
                                    SaoMaLoginBean.ParameterBean parameter = saoMaLoginBean.getParameter();
                                    ShowUtils.Toastshort(this, "正在登陆，请稍等...");
                                    //获取用户信息
                                    mPresenter.getOddNumData(parameter.getId());
                                } else {
                                    Toast.makeText(application, "扫码登陆失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(application, "扫码登陆失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //or do sth
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                        }
                        break;
                }
                break;
        }
    }

    /**
     * @param checkInfo
     */
    @Override
    public void getTokenByCodeData(CheckInfo checkInfo) {
        switch (checkInfo.getStatusCode()) {
            case 0://发送成功，，，倒计时。。。
                updateTime();
                Toast.makeText(this, checkInfo.getStatusMsg(), Toast.LENGTH_SHORT).show();
                break;
            default://发送失败
                Toast.makeText(this, checkInfo.getStatusMsg(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 倒计时
     */
    private void updateTime() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 60; i >= 0; --i) {
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    SystemClock.sleep(1000);     //  1s
                }
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int time = msg.arg1;
            if (tv_vitifycodeget != null) {
                tv_vitifycodeget.setText(time + "s后重发");
                tv_vitifycodeget.setEnabled(false);
                tv_vitifycodeget.setBackgroundColor(getResources().getColor(R.color.colorline));
                if (time == 0) {
                    tv_vitifycodeget.setText("验证码");
                    tv_vitifycodeget.setEnabled(true);
                    tv_vitifycodeget.setBackgroundColor(getResources().getColor(R.color.colorred));
                }
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler codeLoginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int time = msg.arg1;
            if (codeLoginGetcode != null) {
                codeLoginGetcode.setText(time + "s后重发");
                codeLoginGetcode.setEnabled(false);
                codeLoginGetcode.setBackgroundColor(getResources().getColor(R.color.colorline));
                if (time == 0) {
                    codeLoginGetcode.setText("验证码");
                    codeLoginGetcode.setEnabled(true);
                    codeLoginGetcode.setBackgroundColor(getResources().getColor(R.color.colorred));
                }
            }
        }
    };

    /**
     * 获取token成功的回调
     *
     * @param bodyBean
     */
    @Override
    public void getTokenInfoData(TokenInfo.BodyBean bodyBean) {

        MySharedPreferences instance = MySharedPreferences.getInstance();
        instance.setUserPhone(ed_phone.getText().toString());

        String arrayPhoneJson = instance.getArrayPhoneJson();//获取历史账号集合
        ArrayList<AccountInfo> arrayPhoneList = JSONUtils.toList(arrayPhoneJson, new TypeToken<List<AccountInfo>>() {
        }.getType());
        if (arrayPhoneList != null) {
            int i = 0;
            for (i = 0; i < arrayPhoneList.size(); i++) {
                if (arrayPhoneList.get(i).getAccount().equals(bodyBean.getAccount())) {
                    break;
                }
            }
            if (i == arrayPhoneList.size()) {
                arrayPhoneList.add(new AccountInfo(bodyBean.getAccount()));
            }
        } else {
            arrayPhoneList = new ArrayList<AccountInfo>();
            arrayPhoneList.add(new AccountInfo(bodyBean.getAccount()));
        }
        String arrayPhone = JSONUtils.toString(arrayPhoneList);
        instance.setHistory_Phone(arrayPhone);//保存历史账号集合
        //   loginPrompt.setVisibility(View.GONE);
        PrefUtils.putValue(this, Constants.PHOME, bodyBean.getCardNo());
        MySharedPreferences.getInstance().setCardNo(bodyBean.getCardNo());
        PrefUtils.putValue(this, Constants.PASSWORD, bodyBean.getToken());
        PrefUtils.putValue(this, Constants.PHONENUM, bodyBean.getAccount());
        PrefUtils.putValue(this, Constants.APPID, bodyBean.getAppId());
        PrefUtils.putValue(this, Constants.MIMA, ed_pwd.getText().toString());
        PrefUtils.putValue(this, "isLogin", "1");
        App.tokenInfo = bodyBean;
        App.setAlias(bodyBean.getAccount());
        String cardNo = PrefUtils.getValue(this, Constants.PHOME);
        String token = PrefUtils.getValue(this, Constants.PASSWORD);
        mPresenter.landLogin(cardNo, token);
        mPresenter.getOddNumData2(bodyBean.getAppId());
    }


    @Override
    public void showLoadMessage(String msg) {
        dismissLoading();
        btn_next.setEnabled(true);
        if(msg.equals("无项目")){
            App.PN_Onumber = "";
            PrefUtils.putValue(this, Constants.PN_Onumber, "");
            App.loginStarts = 1;
        }
        boolean networkConnected = NetWorkUtils.isNetworkConnected(TextLoginActivity.this);
        if (!networkConnected) {
            showLoadDialog("网络异常");
        } else {
            showLoadDialog("数据解析失败");
        }

    }

    /**
     * edittext对个数的监听器,无用
     */
    private class MyEditListener implements TextWatcher {

        private EditText edittext;

        public MyEditListener(EditText edittext) {
            super();
            this.edittext = edittext;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            int lengths = arg0.length();
            switch (edittext.getId()) {
                case R.id.ed_phone:
                    phonenum = ed_phone.getText().toString();
//                    if (lengths == 11) {//11手机号位请求判断
//                        //mPresenter.getCheckIsVerity(phonenum);
//                        //mPresenter.getCheckUserInfo(phonenum, "4");
//                    } else {
//                        rl_veritycode.setVisibility(View.GONE);
//                        btn_next.setVisibility(View.GONE);
//                        rl_pwd.setVisibility(View.GONE);
//                        tv_forgetpwd.setVisibility(View.GONE);
////                        ll_toforget.setVisibility(View.GONE);
//                        codePasswrodLoginText.setVisibility(View.GONE);
//                    }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            // TODO Auto-generated method stub
        }
    }

    /**
     * 显示 提示dialog
     *
     * @param errmsg
     */
    private void showLoadDialog(String errmsg) {
        AlertDialog button = new AlertDialog.Builder(this).setMessage(errmsg).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create();
        button.show();
        button.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);

        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(button);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(getResources().getColor(R.color.cor666));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        btn_next.setEnabled(true);
        return super.onKeyDown(keyCode, event);
    }

}
