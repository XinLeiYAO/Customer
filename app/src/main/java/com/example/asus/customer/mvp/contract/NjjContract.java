package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.ObjectDataBean;
import com.example.asus.customer.entity.ProjectBean;
import com.example.asus.customer.entity.VersionInfo;

import java.util.List;
import java.util.Map;

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

        void getLoginTime();

        void getLoginTimeErro(String erro);

//        void getObjectData(ObjectDataBean objectDataBean);
//
//        void getObjectDataErro(String erro);


    }

    interface Model extends BaseModel {
        Observable<String> getProjectStatus(
                String rwdid


        );

        Observable<String> getLoginTime(
                String rwdid
        );

//        Observable<String> getObjectData(
//                Map<String, String> map
//        );
    }

    abstract class Presenter extends BasePresenter<NjjContract.View, NjjContract.Model> {
        public abstract void getProjectStatus(
                String rwdid
        );

        public abstract void getLoginTime(
                String rwdid
        );
//        public abstract void getObjectData(
//                Map<String, String> map
//        );
    }
}
