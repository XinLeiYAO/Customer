package com.example.asus.customer.commons.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by hjh on 2017/11/7.
 */

public class ShowUtils {

    /**
     * 短吐司
     * @param context
     * @param str
     */
    public static void Toastshort(Context context,String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     * 长吐司
     * @param context
     * @param str
     */
    public static void Toastlong(Context context,String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
