package com.example.asus.customer.weight;

import android.app.Activity;
import android.content.Intent;

import com.example.asus.customer.activity.GuidancePager;
import com.example.asus.customer.activity.NjjActivity;
import com.example.asus.customer.activity.TextLoginActivity;
import com.example.asus.customer.entity.AdBean;
import com.example.asus.customer.rx.RxBus;

import rx.functions.Action1;

//
// 2019/5/20.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class StartUtils {
    private StartUtils() {
    }

    public int isAd = 0;// == 1 为进入广告详情页

    private static class SingletonHolder {
        private static final StartUtils startBean = new StartUtils();
    }

    public static StartUtils getInstance() {
        return SingletonHolder.startBean;
    }

    private void startActivity(Activity activity, int typeActivity) {//跳转 activity
        isAd = 0;
        switch (typeActivity) {
            case 1:
                Intent intent = new Intent(activity, TextLoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            case 2:
                activity.startActivity(new Intent(activity, NjjActivity.class));
                activity.finish();
                break;
            case 3:
                activity.startActivity(new Intent(activity, GuidancePager.class));
                activity.finish();
                break;
        }
    }

    public void startActivityChangeListener(final Activity activity, final int typeActivity) {//1 登录页 2 自动登录 3引导页
        if (isAd == 0) {
            startActivity(activity, typeActivity);
        } else {
            RxBus instance = RxBus.getInstance();
            instance.toObservable(AdBean.class)
                    .subscribe(new Action1<AdBean>() {
                        @Override
                        public void call(AdBean c) {
                            startActivity(activity, typeActivity);
                        }
                    });
        }


    }
}
