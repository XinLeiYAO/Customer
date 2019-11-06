package com.example.asus.customer.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.example.asus.customer.activity.DialogActivity;
import com.example.asus.customer.activity.MessageDetailsActivity;
import com.example.asus.customer.activity.TextLoginActivity;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.entity.DanDianInfo;
import com.example.asus.customer.entity.PushInfoNew;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by AAA on 2017/10/23.
 */

public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPushReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                Log.d(TAG, "[JPushReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//                Log.d(TAG, "[JPushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                Log.d(TAG, "[JPushReceiver] 接收到推送下来的通知");
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                String contentstr = bundle.getString(JPushInterface.EXTRA_ALERT);
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//                Log.e(TAG + "接收到的推送数据是", contentstr);
//                Log.e(TAG + "接收到的推送数据是", extras);
//                Log.e(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
                //接受到推送就底部icon加一
//                SetIconMsgNum(context);
                DanDianInfo danDianInfo = JSONUtils.toObject(extras, DanDianInfo.class);

//                Log.e("aaaaaa",App.tokenInfo.getToken());
//                Log.e("aaaaaa",danDianInfo.getSendToken());
                JSONObject jsonObject = new JSONObject(extras);
                if (jsonObject.getString("Type").equals("104")) {
                    String token = PrefUtils.getValue(context, Constants.PASSWORD);

                    if (!danDianInfo.getSendToken().equals(token) || token == null) {
                        if (danDianInfo.getType().equals("104")) {
                            if (App.is_home) {
                                context.startActivity(new Intent(context, DialogActivity.class).putExtra("message", danDianInfo.getStatusMsg()));
                            } else {
                                ShowUtils.Toastshort(context, danDianInfo.getStatusMsg());
                            }

//                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//                            dialogBuilder.setTitle("提示");
//                            dialogBuilder.setMessage(danDianInfo.getStatusMsg());
//                            dialogBuilder.setCancelable(false);
//                            dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Map map = new HashMap();
//                                    map.put("cardNo", App.tokenInfo.getCardNo());
//                                    OkhttpUtils.doPost(ApiEngine.RS_API_HOST+"actionapi/AppHome/OfflineApp", map, new Callback() {
//                                        @Override
//                                        public void onFailure(Call call, IOException e) {
////                                            Log.e("下线", e.getMessage().toString());
//                                        }
//
//                                        @Override
//                                        public void onResponse(Call call, Response response) throws IOException {
//                                            String string = response.body().string();
////                                            Log.e("下线", string);
//                                        }
//                                    });
//                                    App.getApp().exitApp();
//                                    PrefUtils.RemoveValue(context, Constants.IS_LOGIN);
//                                    PrefUtils.RemoveValue(context, Constants.PHOME);
//                                    PrefUtils.RemoveValue(context, Constants.PASSWORD);
//                                    context.startActivity(new Intent(context, TextLoginActivity.class));
//                                    JPushInterface.setAlias(context,"",null);
//
//                                }
//                            });
//                            AlertDialog dialog = App.dialogBuilder;
//                            if (dialog != null && dialog.isShowing()) {
//                                dialog.dismiss();
//                            }
//                            App.dialogBuilder = dialogBuilder.create();
//                            try {
//                                App.dialogBuilder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//
//                                App.dialogBuilder.show();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
////                        AlertDialog alertDialog = dialogBuilder.create();
////                        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
////                        alertDialog.show();
//
//
////                        AlertDialog alertDialog = dialogBuilder.create();
////                        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY);
////                        alertDialog.show();
//
//                            AlertDialog alertDialog = dialogBuilder.create();
////                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
////                            Log.e("aaaaaaaaaa","1");
////                                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY);
////                            } else {
////                            Log.e("aaaaaaaaaa","2");
//                            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
////                            }
//                            alertDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
//                            alertDialog.show();
                        }
                    }
                }


            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "[JPushReceiver] 用户点击打开了通知");

                String json = bundle.getString(JPushInterface.EXTRA_EXTRA);

                Log.d(TAG, "[JPushReceiver] 用户点击打开了通知 = " + json);
                PushInfoNew pushInfoNew = JSONUtils.toObject(json, PushInfoNew.class);

                if(pushInfoNew.getType().equals("31")){
                    Intent msgDetailIntent = new Intent(context, MessageDetailsActivity.class);
                    int id = pushInfoNew.getID();
                    msgDetailIntent.putExtra("id", id+"");
                    msgDetailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(msgDetailIntent);
                }
//                PushInfo info = JSONUtils.toObject(json, PushInfo.class);

//                String parameter = info.getParameter();

//                if (info.getJMP().equals("order")) {
//                    PushOrdersInfo ordersInfo = JSONUtils.toObject(parameter, PushOrdersInfo.class);
//                    Intent ordersIntent = new Intent(context, OrdersDetailActivity.class);
//                    ordersIntent.putExtra(Constants.ACTION_TO_ORDERS_DETAIL_ORDER_ID, ordersInfo.getOrderID());
//                    ordersIntent.putExtra(Constants.ACTION_TO_ORDERS_DETAIL_ORDERS_STATUS, ordersInfo.getOrderState());
//                    ordersIntent.putExtra(Constants.ACTION_TO_ORDERS_DETAIL_PRO_NAME, ordersInfo.getProName());
//                    ordersIntent.putExtra(Constants.ACTION_TO_ORDERS_DETAIL_PRO_ADDRESS, ordersInfo.getProAddr());
//                    ordersIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(ordersIntent);
//                } else if (info.getJMP().equals("message")) {
//                    PushMsgDetailInfo msgDetailInfo = JSONUtils.toObject(parameter, PushMsgDetailInfo.class);
//                    msgDetailIntent.putExtra(Constants.ACTION_TO_MSG_DETAIL_TS_ID, Integer.parseInt(msgDetailInfo.getTs_id()));
//                    msgDetailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(msgDetailIntent);
//                }

//                //打开自定义的Activity
//                Intent i = new Intent(context, TestActivity.class);
//                i.putExtras(bundle);
//                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                context.startActivity(i);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.d(TAG, "[JPushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.w(TAG, "[JPushReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Log.d(TAG, "[JPushReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
//                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
//    public static String MSG_NUM = App.tokenInfo.getCardNo() + "MSG_NUM";
//    public static String msgnum = "msgnum";
//    private void SetIconMsgNum(Context context) {
//        SharedPreferences preferences = context.getSharedPreferences(MSG_NUM, MODE_PRIVATE);
//        int num = preferences.getInt(msgnum, 0);
//        num = num + 1;
//        preferences.edit().putInt(msgnum, num).commit();
//
//    }
}
