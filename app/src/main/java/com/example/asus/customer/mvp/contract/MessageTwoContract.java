package com.example.asus.customer.mvp.contract;


import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.InfoMessageBean;

import rx.Observable;

/**
 * Created by hjh on 2018/3/9.
 */

public interface MessageTwoContract {


    interface View extends BaseView {

        void responseInfoMessageTwo(InfoMessageBean data);

        void responseInfoMessageTwoError(String msg);

        void responseSeeDetails(InfoMessageBean data);

        void responseSeeDetailsError(String msg);

        void setMessage(String statusMsg);//一键已读

        void setMessageErro(String erro);//一键已读

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {
        Observable<String> getInfoMessageTwoList(
                String cardNo,
                String Group
        );
        Observable<String> toSeeDetails(
                String id
        );
        Observable<String> setMessage(
                String CardNo,
                String Group
        );
    }


    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getInfoMessageTwoList(
                String cardNo,
                String Group
        );
        public abstract void toSeeDetails(
                String id
        );
        public abstract void setMessage(
                String CardNo,
                String Group
        );
    }
}
