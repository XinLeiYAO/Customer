package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.entity.UserInfo;

import java.util.Map;

import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public interface MineContract {
    interface View extends BaseView {

        void showDialog();

        void hideDialog();

        void loadCustomerInformation(MineUserInfo.BodyBean bodyBean);

        void showMessage(String message);

        void getUnreadData(UnreadBean unreadBean);

        void getUnreadDataErro(String erro);

    }

    interface Model extends BaseModel {
        Observable<String> loadCustomerInformation(
                String cardNo
        );

        Observable<String> getUnreadData(
                Map<String, String> map);
    }

    abstract class Presenter extends BasePresenter<MineContract.View, MineContract.Model> {
        public abstract void loadCustomerInformation(
                String cardNo
        );

        public abstract void getUnreadData(
                Map<String, String> map
        );
    }
}
