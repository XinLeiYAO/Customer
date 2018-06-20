package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;

import java.util.List;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public interface CurrentContract {
    interface View extends BaseView{

        void showDialog();

        void hideDialog();

        void getCurrentData(Picture.BodyBean Body);

        void showMessage(String message);

        void loadDesignDescription(DesignInfo.BodyBean bodyBean);

        void loadCurreerPhotoData(CurreerPhotoDataBean.BodyBean bodyBean);
        void getProgssData(ProgssInfo.BodyBean bodyBean);
        void getRecommendData(RecommendBean bean);
    }
    interface Model extends BaseModel{
        Observable<String> loadHomedata(
                String orderNo


        );
        Observable<String> loadDesignDescription(
                String rwdId


        );
        Observable<String> loadCurreerPhotoData(
                String orderNo


        );
        Observable<String> getProgssData(
                String rwdId


        );
        Observable<String> getRecommendData(
                String rwdId
        );
    }


    abstract class Presenter extends BasePresenter<CurrentContract.View, CurrentContract.Model> {
        public abstract void loadHomedata(
                String rwdId
        );
        public abstract void loadDesignDescription(
                String rwdId
        );
        public abstract void loadCurreerPhotoData(
                String orderNo
        );
        public abstract void getProgssData(
                String rwdId
        );
        public abstract void getRecommendData(
                String rwdId
        );
    }
}
