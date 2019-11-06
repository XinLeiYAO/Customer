package com.example.asus.customer.mvp.contract;




import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.VersionInfo;

import rx.Observable;

/**
 * Created by AAA on 2017/10/20.
 */

public interface MainContract {

    interface View extends BaseView {

        void responseVersionData(VersionInfo.Version data);

        void responseVersionDataError(String msg);

    }

    interface Model extends BaseModel {

        Observable<String> getVersionInfo(
                int version
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getVersionInfo(
                int version
        );

    }

}
