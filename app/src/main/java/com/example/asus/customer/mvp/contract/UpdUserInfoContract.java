package com.example.asus.customer.mvp.contract;



import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.ImgBean;
import com.example.asus.customer.entity.OSSBean;

import rx.Observable;

/**
 * Created by AAA on 2017/7/12.
 */

public interface UpdUserInfoContract {

    interface View extends BaseView {

        void responseUpdateData();

        void responseUpdateDataError(String msg);

        void responseUpPicture();

        void responseUpPictureError(String msg);

        void reLogin(String msg);

        void showDialog();

        void hideDialog();

        void OssData(OSSBean ossBean);//初始化oss的回调

        void imageIconData(ImgBean imgBean);
    }

    interface Model extends BaseModel {
        Observable<String> updateUserInfo(
                String token,
                String cardNo,
                String key,
                String value
        );

        Observable<String> upHeaderPicture(
                String type,
                String cardNo,
                String imgUrl
        );
        Observable<String> initOss();//获取用于初始化Oss的key、id等信息
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void updateUserInfo(
                String token,
                String cardNo,
                String key,
                String value
        );

        public abstract void upHeaderPicture(
                String type,
                String cardNo,
                String imgUrl
        );
        public abstract void getOssData();//获取用于初始化Oss的key、id等信息
    }

}
