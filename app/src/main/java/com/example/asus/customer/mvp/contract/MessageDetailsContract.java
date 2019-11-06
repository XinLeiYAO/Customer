package com.example.asus.customer.mvp.contract;


import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.MessageDetailsBean;

import rx.Observable;

/**
 * Created by hjh on 2018/3/9.
 */

public interface MessageDetailsContract {

    interface View extends BaseView {

        void responseInfoMessageD(MessageDetailsBean data);

        void responseInfoMessageDError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getInfoMessageDList(
                String Id
        );
    }


    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getInfoMessageDList(
                String Id
        );
    }
}
