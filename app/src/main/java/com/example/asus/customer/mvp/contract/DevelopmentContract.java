package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.FindInformationBean;

import java.util.List;

import rx.Observable;

/**
 * Created by asus on 2018/6/4.
 */

public interface DevelopmentContract {

    interface View extends BaseView {

        void showDialog();

        void hideDialog();

        void getProgssData(FindInfo.BodyBean bodyBean);

        void showMessage(String message);
    }
    interface Model extends BaseModel {
        Observable<String> getProgssData(
                String rwdId


        );
    }
    abstract class Presenter extends BasePresenter<DevelopmentContract.View, DevelopmentContract.Model> {
        public abstract void getProgssData(
                String rwdId
        );
    }
}
