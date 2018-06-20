package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;

import java.util.List;

import rx.Observable;

/**
 * Created by asus on 2018/4/28.
 */

public interface DetailedDetailsContract {

    interface View extends BaseView {

        void showDialog();

        void hideDialog();

          void volumeRoomPhooto(List<PhotoDetailsBean> mlist);

        void showMessage(String message);


    }
    interface Model extends BaseModel {
        Observable<String> volumeRoomPhooto(
                String rwdId


        );
    }

    abstract class Presenter extends BasePresenter<DetailedDetailsContract.View, DetailedDetailsContract.Model> {
        public abstract void volumeRoomPhooto(
                String rwdId
        );
    }
}
