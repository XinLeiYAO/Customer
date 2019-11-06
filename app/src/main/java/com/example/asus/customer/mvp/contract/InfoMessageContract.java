package com.example.asus.customer.mvp.contract;


import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.InfoMessageBean;

import rx.Observable;

/**
 * Created by hjh on 2017/11/22.
 */

public interface InfoMessageContract {

    interface View extends BaseView {

        void responseInfoMessage(InfoMessageBean data);

        void responseInfoMessageError(String msg);

        void responseSeeDetails(InfoMessageBean data);

        void responseSeeDetailsError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getInfoMessageList(
                String cardNo
        );
        Observable<String> toSeeDetails(
                String id
        );
    }


    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getInfoMessageList(
                String cardNo
        );
        public abstract void toSeeDetails(
                String id
        );

    }
}
