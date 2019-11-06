package com.example.asus.customer.api;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebSettings;

import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.utils.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by leimh on 2016/11/6.
 * 用于对网络数据缓存，需要进行缓存的请求内容需要response头中 Cache-Control max-age 设置缓存时间
 */

public class NetWorkInterceptor implements Interceptor {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //无网络时强制使用缓存
        if (!NetUtil.isConnected(App.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        request = request.newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", getUserAgent()).build();

        Response response = chain.proceed(request);

        Response cacheResponse;

        if (NetUtil.isConnected(App.getContext())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            cacheResponse = response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置超时为3周
            int maxStale = 60 * 60 * 24 * 21;
            //无网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            cacheResponse = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheControl)
                    .build();
        }

        return cacheResponse;
    }

    private static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(App.getContext());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
