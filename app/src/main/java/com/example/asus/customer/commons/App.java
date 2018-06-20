package com.example.asus.customer.commons;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.asus.customer.commons.utils.ImageLoaderUtil;
import com.example.asus.customer.entity.TokenInfo;
import com.example.asus.customer.entity.UserInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/4/11.
 */
public class App extends Application {
    private List<Activity> activities;
    private static App app;
    public static UserInfo.BodyBean body;
    public static String orderNo;
    public static String starts;
    public static String projectName;

    public static TokenInfo.BodyBean tokenInfo;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        activities = new ArrayList<>();
        ImageLoaderUtil.init(this);
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
        //   JPushInterface.deleteAlias(this, Constants.MSG_SET_ALIAS);
    }

    public static Context getContext() {
        return getApp().getApplicationContext();
    }
}
