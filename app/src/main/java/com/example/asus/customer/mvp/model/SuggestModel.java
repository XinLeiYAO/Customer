package com.example.asus.customer.mvp.model;

import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.SuggestContract;
import com.example.asus.customer.rx.RxSchedulers;
import com.luck.picture.lib.entity.LocalMedia;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by AAA on 2017/9/22.
 */

public class SuggestModel implements SuggestContract.Model {
    @Override
    public Observable<String> subSuggestInfo(String the_name, String contact, String service_description, int complaints_type,String contract_no, List<LocalMedia> imgList) {

        List<MultipartBody.Part> list = new ArrayList<>();

        for (LocalMedia info : imgList) {
            //将照片路径转换为File对象
            File file = new File(info.getCompressPath());
            //创建RequestBody ,用于Retrofit2.0上传照片
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // MultipartBody.Part  和后端约定好Key，这里的partName是用image
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file_stream", file.getName(), requestFile);
            list.add(body);
        }

        return ApiEngine.getInstance().getTodApiService()
                .subSuggestInfo(the_name, contact, service_description, complaints_type, contract_no,list)
                .compose(RxSchedulers.<String>switchThread());
    }
}
