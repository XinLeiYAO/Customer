package com.example.asus.customer.api;


import com.example.asus.customer.commons.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ApiEngine {

    private static final String RS_API_HOST = "https://api.dcwzg.com:9191/";

    private Retrofit rsRetrofit;

    private Retrofit esRetrofit;

    public static final String GS_API_HOST="http://192.168.1.192:8808";

    private Retrofit gsRetrofit;
    public static final String ES_API_HOST ="http://app.wenes.cn:8859/";//线上
  // public static final String ES_API_HOST = "http://192.168.1.192:8859/";//测试
  public static final String FID_API_HOST = "http://wpsnew.rxjy.com:9090/";//发现模块数据线上
  //   public static final String FID_API_HOST = "http://test.news.cs/";//发现模块数据测试
  private Retrofit findRetrofit;

  public static final String FS_API_HOST="http://app.wenes.cn:8859/";//线上
  //  public static final String FS_API_HOST="http://192.168.1.192:8867/";//测试
    private Retrofit fsRetrofit;

    private Retrofit phRetrofit;

    public static final String INFORMATION=RS_API_HOST+"actionapi/AN_Home/ShowMyInfo";

//    ：http://na.wenes.com/api/jd/GetImage?rwdid=22-119555
//    测试：http://10.10.3.167:34717/api/jd/GetImage?rwdid=22-119555

 public static final String PH_API_HOST="http://na.wenes.com/";//线上
    //public static final String PH_API_HOST="http://10.10.3.167:34717/";
    public static final String TO_API_HOST="http://api.gc.cs/";//测试
   // public static final String TO_API_HOST="http://api.gc.rx/api";//线上
   private Retrofit toRetrofit;
    //这里更好的保证单例的线程安全
    private volatile static ApiEngine apiEngine;



    private ApiEngine() {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //设置查看类别
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置缓存位置与缓存的大小
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "okHttpCache");
        Cache cache = new Cache(cacheFile, size);

        //添加日志拦截器，并且添加网络缓存
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetWorkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        rsRetrofit = new Retrofit.Builder()
                .baseUrl(RS_API_HOST)
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
        gsRetrofit= new Retrofit.Builder()
                .baseUrl(GS_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fsRetrofit= new Retrofit.Builder()
                .baseUrl(FS_API_HOST)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        phRetrofit= new Retrofit.Builder()
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
        toRetrofit=new Retrofit.Builder()
                .baseUrl(TO_API_HOST)
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

    public ApiService getRsApiService() {

        return rsRetrofit.create(ApiService.class);
    }
    public ApiService getEsApiService() {

        return esRetrofit.create(ApiService.class);
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
}
