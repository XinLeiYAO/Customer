package com.example.asus.customer.api;


import android.util.Log;

import com.example.asus.customer.commons.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ApiEngine {
    //============================================正式===================================================

//
//    public static final String FS_API_HOST = "https://zaapi.rxjy.com";//主案 阿里云*
//    public static final String RS_API_HOST = "https://piapi.rxjy.com/";//阿里云* 大院
//    public static final String GZS_HOST = "https://khdapi.wenes.cn";//工作室 使用
//    public static final String ZHA_HOST = "http://zhapi.rxjy.com/";//主案 曹俊波
////
//    public static final String RS_NEWS_HOST = "https://tapp.rxjy.com/";   //新闻评论接口



//=============================================测试==================================================
//    public static final String FS_API_HOST = "http://192.168.1.194:8081";//主案 阿里云*
//    public static final String RS_API_HOST = "http://192.168.1.23:8082/";//阿里云* 大院
//    public static final String GZS_HOST = "http://10.10.13.44:7900";//工作室   测试
//    public static final String ZHA_HOST = "http://10.10.16.116:8204/";//主案 曹俊波
//
//    public static final String RS_NEWS_HOST = "http://192.168.1.19:8906/";   //新闻评论接口

//=================================jiyuhua=========================================================
    public static final String FS_API_HOST = "http://192.168.1.194:8081";//主案 阿里云*
    public static final String RS_API_HOST = "http://192.168.1.23:8082/";//阿里云* 大院
    public static final String GZS_HOST = "http://192.168.1.193:7900/";//工作室   测试
    public static final String ZHA_HOST = "http://10.10.16.116:8204/";//主案 曹俊波

    public static final String RS_NEWS_HOST = "http://192.168.1.19:8906/";   //新闻评论接口


    //=============================================无用的或无测试线的==================================================
    public static final String ZRA_HOST = "https://zrapi.rxjy.com/";//主案 人事
    public static final String FID_API_HOST = "http://wpsnew.rxjy.com:9090/";//发现模块数据线上  //不用了？？
    public static final String RUIYAN = "https://tsharp.rxjy.com/";//阿里云* 没用上
    private static final String RX_API_HOST_NEW = "https://tsharp.rxjy.com/";//阿里云*  以前的发现页  没用上
    public static final String PH_API_HOST = "http://na.wenes.com/";//线上 //没用上?
    public static final String TO_API_HOST = "http://api.gc.rx/api/";//线上  //没用上?
    public static final String GS_API_HOST = "http://192.168.1.192:8808"; //没用上
    public static final String ES_API_HOST = "https://zhapi.rxjy.com";//主案 阿里云*
    public static final String ZAAPP_BASE = "https://zaapp.rxjy.com/";//客户首页预算 webview域名
    public static final String KEHU = "https://pi.rxjy.com/";//客户 阿里*
    public static final String SW_API_HOST = "https://sappapi.rxjy.com/";//牛经纪
    public static final String webUrl = "https://vip.niujingji.cn/";//web页面url


    public static String LOGINURL = RS_API_HOST + "actionapi/AppLogin/Login ";//新登陆

    public static final String INFORMATION = RS_API_HOST + "actionapi/AN_Home/ShowMyInfo";
    //获取消息未读数量
    public static String MSGCOUNT = RS_API_HOST + "actionapi/JiGuang/GetAppNoticeNoReadCount";


    //阿里云oss初始化域名
//    public static final String OSS_DATA_BASU="http://39.107.226.191/";
    public static final String OSS_DATA_BASU = "https://apposs.rxjy.com";
    //阿里云oss域名
    public static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";

    public static final String BUCKETNAME = "wenes01";
    public static final String OSS_UPURL = "https://wenes01.oss-cn-beijing.aliyuncs.com/";
    public static final String BUCKETNAME2 = "holding01";
    public static final String IMA_URL = "https://holding01.oss-cn-beijing.aliyuncs.com/";

    //这里更好的保证单例的线程安全
    private volatile static ApiEngine apiEngine;
    private Retrofit toRetrofit;
    private Retrofit fsRetrofit;
    private Retrofit phRetrofit;
    private Retrofit fxRetrofit;
    private Retrofit findRetrofit;
    private Retrofit OssRetrofit;
    private Retrofit GZS_Retrofit;
    private Retrofit swRetrofit;
    private Retrofit rsRetrofit;
    private Retrofit rsNewsRetrofit;
    private Retrofit esRetrofit;
    private Retrofit gsRetrofit;

    public static class UrlPahtIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            Log.e("request_url",request.url()+"   -=--=-=-=");

            return chain.proceed(request);
        }
    }

    private ApiEngine() {

        //日志拦截器
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //设置查看类别
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        //设置缓存位置与缓存的大小
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "okHttpCache");
        Cache cache = new Cache(cacheFile, size);


        //添加日志拦截器，并且添加网络缓存
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetWorkInterceptor())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new UrlPahtIntercepter())
                .cache(cache)
                .build();

        rsRetrofit = new Retrofit.Builder()
                .baseUrl(RS_API_HOST)
                .client(client)

                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        rsNewsRetrofit = new Retrofit.Builder()
                .baseUrl(RS_NEWS_HOST)
                .client(client)

                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        esRetrofit = new Retrofit.Builder()
                .baseUrl(ES_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GZS_Retrofit = new Retrofit.Builder()
                .baseUrl(GZS_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        gsRetrofit = new Retrofit.Builder()
                .baseUrl(GS_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fsRetrofit = new Retrofit.Builder()
                .baseUrl(FS_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        phRetrofit = new Retrofit.Builder()
                .baseUrl(PH_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        findRetrofit = new Retrofit.Builder()
                .baseUrl(FID_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        toRetrofit = new Retrofit.Builder()
                .baseUrl(TO_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fxRetrofit = new Retrofit.Builder()
                .baseUrl(RX_API_HOST_NEW)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        OssRetrofit = new Retrofit.Builder()
                .baseUrl(OSS_DATA_BASU)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        swRetrofit = new Retrofit.Builder()
                .baseUrl(SW_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    //将网络请求对象封装为单例模式
    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    //oss初始化信息
    public ApiService getOssData() {
        return OssRetrofit.create(ApiService.class);
    }

    public ApiService getSwApiService() {
        return swRetrofit.create(ApiService.class);
    }

    public ApiService getRsApiService() {

        return rsRetrofit.create(ApiService.class);
    }

    public ApiService getRsNewsApiService() {

        return rsNewsRetrofit.create(ApiService.class);
    }

    public ApiService getEsApiService() {

        return esRetrofit.create(ApiService.class);
    }

    public ApiService getGZSApiService() {

        return GZS_Retrofit.create(ApiService.class);
    }

    public ApiService getGsApiService() {

        return gsRetrofit.create(ApiService.class);
    }

    public ApiService getFsApiService() {

        return fsRetrofit.create(ApiService.class);
    }

    public ApiService getPhApiService() {

        return phRetrofit.create(ApiService.class);
    }

    public ApiService getfindApiService() {
        return findRetrofit.create(ApiService.class);
    }

    public ApiService getTodApiService() {
        return toRetrofit.create(ApiService.class);

    }

    public ApiService getFaXianApiService() {
        return fxRetrofit.create(ApiService.class);
    }
}
