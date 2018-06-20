package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;

import rx.Observable;

/**
 * Created by AAA on 2017/7/27.
 */

public interface ForgetPwdContract {

    interface View extends BaseView {

        void responseUpdatePassword();

        void responseUpdatePasswordError(String msg);

        void responseVCodeError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> updatePassword(
                String phoneNum,
                String newPassword,
                String vCode
        );

        Observable<String> getVCode(
                String phoneNum
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void updatePassword(
                String phoneNum,
                String newPassword,
                String vCode
        );

        public abstract void getVCode(
                String phoneNum
        );
    }

}
