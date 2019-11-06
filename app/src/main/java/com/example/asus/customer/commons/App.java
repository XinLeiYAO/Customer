package com.example.asus.customer.commons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.utils.ImageLoaderUtil;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.NetWorkUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.StringUtils;
import com.example.asus.customer.commons.utils.SystemUtil;
import com.example.asus.customer.commons.utils.ZJson;
import com.example.asus.customer.entity.NewUserInfoBean;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.asus.customer.commons.Constants.MSG_SET_ALIAS;
import static com.example.asus.customer.commons.Constants.jpushname;


/**
 * Created by Administrator on 2017/4/11.
 */
public class App extends Application {
    public static String APP_ID = "4";
    public static boolean is_home = false;
    private List<Activity> activities;
    private static App app;
    public static SharedPreferences sp;
    public static NewUserInfoBean.BodyBean body;
    public static String orderNo;
    public static String starts;
    public static String projectName = "";
    public static int loginStarts = 0;
    public static TokenInfo.BodyBean tokenInfo;
    public static Handler mhandler = new Handler();
    public int count = 0;
    public static String cardNo;
    public static String PN_Onumber;
    public static AlertDialog dialogBuilder;
    public static long time = 0;
    public static boolean is_time = false;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        UMShareAPI.get(this);
        UMConfigure.init(this,"5c808174203657b5a10000ee"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setQQZone("1107716523", "gXFSThqVxLT73QMq");
        PlatformConfig.setWeixin("wx3bd91f8203660a92", "24258518f268c778d8cf403ab63f1c41");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        app = this;
        sp = getSharedPreferences(Constants.IS_SETALIAS, MODE_PRIVATE);
        activities = new ArrayList<>();
        ImageLoaderUtil.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                cardNo = PrefUtils.getValue(App.this, Constants.PHOME);
                if (count == 0) {
                    if (!"".equals(cardNo)) {
                        Map map = new HashMap();

//                        if (App.tokenInfo != null)
//                            map.put("cardNo", App.tokenInfo.getCardNo());
                        map.put("cardNo", cardNo);
                        OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "actionapi/AppHome/OfflineApp", map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                            }
                        });
                    }
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                final String cardNo = PrefUtils.getValue(App.this, Constants.PHOME);
                if (count == 0) {
                    if ("".equals(cardNo)) {
                    } else {
                        Map<String, Object> tokenMap = new HashMap<>();
                        String token = PrefUtils.getValue(App.this, Constants.PASSWORD);
                        tokenMap.put("cardNo", cardNo);
                        tokenMap.put("token", token);
                        OkhttpUtils.doPost(ApiEngine.RS_API_HOST + "actionapi/AN_Home/ShowMyInfo", tokenMap, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, final Response response) throws IOException {
                                String string = response.body().string();

                                NewUserInfoBean info = JSONUtils.toObject(string, NewUserInfoBean.class);
                                if (info.getStatusCode() == 0) {
                                    startLogin(cardNo);
                                } else if (info.getStatusCode() == 104) {

                                }

                            }
                        });
                    }
                }
                count++;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }
        });


        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        第三个参数为SDK调试模式开关，调试模式的行为特性如下：
//
//        输出详细的Bugly SDK的Log；
//        每一条Crash都会被立即上报；
//        自定义日志将会在Logcat中输出。
//        建议在测试阶段建议设置成true，发布时设置为false。
        //BugLy初始化
        CrashReport.initCrashReport(getApplicationContext(), "1c4b43941f", true);


    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private void startLogin(String cardNo) {
        Map map = new HashMap();
        map.put("app_id", "");
        map.put("card_no", cardNo);
        map.put("landing_date", "");
        map.put("offline_date", "");
        map.put("locate_province_now", "");
        map.put("locate_city_now", "");
        map.put("a_equipment", android.os.Build.MODEL);//使用设备
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
        map.put("create_date", "");
        map.put("update_date", "");
        map.put("app_version_number", App.getVersionName());
        map.put("system_version_number", SystemUtil.getSystemVersion());
        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
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
//                Log.e("tag_数据统计_失败", e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
//                Log.e("tag_数据统计", string);
            }
        });

    }

    public static App getApp() {
        return app;
    }

    public App addActivity(Activity activity) {
        activities.add(activity);
        return app;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void finishSingleActivity(Class<?> cls) {
        Activity tempActivity = null;
        for (Activity activity : activities) {
            if (activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }
        killActivity(tempActivity);
    }

    private void killActivity(Activity ac) {
        if (ac != null) {
            ac.finish();
        }
    }

    public void exitApp() {
        int size = activities.size();
        for (int i = 0; i < size; i++) {
            killActivity(activities.get(i));
        }
        //解绑别名
        JPushInterface.deleteAlias(this, MSG_SET_ALIAS);
    }

    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    public static void setAlias(String alias) {
        String isset = sp.getString(jpushname, null);
        if (!StringUtils.isEmpty(isset) && isset.equals("1")) {
        } else {
            // 调用 Handler 来异步设置别名
            if (!StringUtils.isEmpty(alias)) {
                mhandler.sendMessage(mhandler.obtainMessage(MSG_SET_ALIAS, alias));
            } else {
//                Log.e("别名为空", "dsasda");
            }
        }
    }

    public static Context getContext() {
        return getApp().getApplicationContext();
    }

    //获取版本号
    public static String getVersionCode() {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return String.valueOf(info.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取版本名称
    public static String getVersionName() {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
