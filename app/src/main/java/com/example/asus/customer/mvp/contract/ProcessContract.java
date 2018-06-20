package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.OptimizationBean;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;

import java.util.List;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public interface ProcessContract {

    interface View extends BaseView {

        void showDialog();

        void hideDialog();

        void getProgssData(ProgssInfo.BodyBean bodyBean);

        void showMessage(String message);

        void loadCustomerInformation(MineUserInfo.BodyBean bodyBean);


        void getCurrentData(Picture.BodyBean body);
        void loadDesignDescription(DesignInfo.BodyBean bodyBean);

        void loadCurreerPhotoData(CurreerPhotoDataBean.BodyBean bodyBean);
        void volumeRoomPhooto(List<PhotoDetailsBean> mlist);

        void getOptimizationBean(OptimizationBean bean);

        void getRecommendData(RecommendBean bean);
    }
    interface Model extends BaseModel {
        Observable<String> getProgssData(
                String rwdId


        );
        Observable<String> loadCustomerInformation(
                String cardNo


        );
        Observable<String> loadHomedata(
                String rwdId


        );

        Observable<String> loadDesignDescription(
                String rwdId


        );
        Observable<String> loadCurreerPhotoData(
                String orderNo


        );
        Observable<String> volumeRoomPhooto(
                String rwdId


        );
        Observable<String> getOptimizationBean(

        );
        Observable<String> getFindData(
                String tb_diqu,
                String ci_Type,
                String ca_IntentionalStyle,
                String ci_Area,
                String tb_ca_DecBudgetPrice


        );
        Observable<String> getRecommendData(
                String rwdId
        );

    }
    abstract class Presenter extends BasePresenter<ProcessContract.View, ProcessContract.Model> {
        public abstract void getProgssData(
                String rwdId
        );
        public abstract void loadCustomerInformation(
                String cardNo
        );
        public abstract void loadHomedata(
                String rwdId
        );
        public abstract void loadDesignDescription(
                String rwdId
        );
        public abstract void loadCurreerPhotoData(
                String orderNo
        );
        public abstract void volumeRoomPhooto(
                String rwdId
        );
        public abstract void getOptimizationBean(

        );
        public abstract void getFindData(
                String tb_diqu,
                String ci_Type,
                String ca_IntentionalStyle,
                String ci_Area,
                String tb_ca_DecBudgetPrice
        );
        public abstract void getRecommendData(
                String rwdId
        );
    }
}
