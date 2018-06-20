package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.ProjectBean;

import java.util.List;

import rx.Observable;

/**
 * Created by asus on 2018/5/28.
 */

public interface NjjContract {
    interface View extends BaseView {

        void showDialog();

        void hideDialog();

        void showMessage(String message);

        void getProjectStatus(ProjectBean projectBean);

    }
    interface Model extends BaseModel {
        Observable<String> getProjectStatus(
                String rwdid


        );

    }
    abstract class Presenter extends BasePresenter<NjjContract.View, NjjContract.Model> {
        public abstract void getProjectStatus(
                String rwdid
        );
    }
}
