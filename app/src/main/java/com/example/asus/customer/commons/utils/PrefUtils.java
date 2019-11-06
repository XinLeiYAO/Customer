package com.example.asus.customer.commons.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PrefUtils
{
    private static final String SHARED_PREFS_NAME = "DaiBan_Pref";
    private static final String PREF_FIRST_USE = "first_use";

    public static boolean isFirstUse(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(
                SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(PREF_FIRST_USE, true);
    }

    public static void setFirstUse(Context context, boolean value)
    {
        SharedPreferences preferences = context.getSharedPreferences(
                SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(PREF_FIRST_USE, value);
        editor.commit();
    }

    /**
     * 移除SharedPreference
     *
     * @param context
     * @param key
     */
    public static final void RemoveValue(Context context, String key)
    {
        Editor editor = getSharedPreference(context).edit();
        editor.remove(key);
        boolean result = editor.commit();
        if (!result)
        {
//            Log.e("移除Shared", "save " + key + " failed");
        }
    }

    /**
     * 获取Preference设置
     */
    public static final SharedPreferences getSharedPreference(Context context)
    {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getPreferences(Context context, String name)
    {
        SharedPreferences pre = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return pre;
    }

    /**
     * 获取SharedPreference 值
     *
     * @param context
     * @param key
     * @return
     */
    public static final String getValue(Context context, String key)
    {
        return getSharedPreference(context).getString(key, "");
    }

    public static final Boolean getBooleanValue(Context context, String key)
    {
        return getSharedPreference(context).getBoolean(key, false);
    }

    public static final void putBooleanValue(Context context, String key, boolean bl)
    {
        Editor edit = getSharedPreference(context).edit();
        edit.putBoolean(key, bl);
        edit.commit();
    }

    public static final int getIntValue(Context context, String key)
    {
        return getSharedPreference(context).getInt(key, -1);
    }

    public static final long getLongValue(Context context, String key, long default_data)
    {
        return getSharedPreference(context).getLong(key, default_data);
    }

    public static final boolean putLongValue(Context context, String key, Long value)
    {
        Editor editor = getSharedPreference(context).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static final Boolean hasValue(Context context, String key)
    {
        return getSharedPreference(context).contains(key);
    }

    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putValue(Context context, String key,
                                         String value)
    {
        value = value == null ? "" : value;
        Editor editor = getSharedPreference(context).edit();

        editor.putString(key, value);
        boolean result = editor.commit();
        if (!result)
        {
            return false;
        }
        return true;
    }


    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putIntValue(Context context, String key, int value)
    {
        Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        boolean result = editor.commit();
        if (!result)
        {
            return false;
        }
        return true;
    }

    /**
     * 得到SharedPreference 值
     * @param context
     * @param key
     * @return
     */
      public static  int getIntFlag(Context context, String key){
          SharedPreferences sharedPreference = getSharedPreference(context);
          int anInt = sharedPreference.getInt(key, 0);
          return anInt;
      }

}
