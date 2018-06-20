package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.ProgssInfo;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by asus on 2018/4/17.
 */

public interface FindContract {
    interface View extends BaseView {

        void showDialog();

        void hideDialog();
        void getProgssData(FindInfo.BodyBean bodyBean);

        void showMessage(String message);

        void getfindData(List<FindInformationBean.BodyBean> mlist);

        void getUpDataProject();

    }

    interface Model extends BaseModel {
        Observable<String> getProgssData(
                String rwdId


        );

        Observable<String> getFindData(
                String tb_diqu,
                String ci_Type,
                String ca_IntentionalStyle,
                String ci_Area,
                String tb_ca_DecBudgetPrice


        );
        Observable<String> getContractData(
                String rwdId,
                String ca

        );

    }

    abstract class Presenter extends BasePresenter<FindContract.View, FindContract.Model> {
        public abstract void getProgssData(
                String rwdId
        );

        public abstract void getFindData(
                String tb_diqu,
                String ci_Type,
                String ca_IntentionalStyle,
                String ci_Area,
                String tb_ca_DecBudgetPrice
        );
        public abstract void getContractData(
                String rwdId,
                String ca
        );
    }
}
