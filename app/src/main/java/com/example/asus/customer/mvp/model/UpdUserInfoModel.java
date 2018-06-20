package com.example.asus.customer.mvp.model;

import android.util.Log;


import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.UpdUserInfoContract;
import com.example.asus.customer.rx.RxSchedulers;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;


/**
 * Created by AAA on 2017/7/28.
 */

public class UpdUserInfoModel implements UpdUserInfoContract.Model {
    @Override
    public Observable<String> updateUserInfo(String token, String cardNo, String key, String value) {
        return ApiEngine.getInstance().getRsApiService()
                .updateUserInfo(token, cardNo, key, value)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> upHeaderPicture(String token, String cardNo, String picturePath) {

        //将照片路径转换为File对象
        File file = new File(picturePath);
        //创建RequestBody ,用于Retrofit2.0上传照片
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData("file_stream", file.getName(), requestFile);
        Log.e("tag",body.toString());
        return ApiEngine.getInstance().getRsApiService()
                .upHeaderPicture(token, cardNo, body)
                .compose(RxSchedulers.<String>switchThread());
    }
}
