package com.example.asus.customer.mvp.contract;

import com.example.asus.customer.commons.base.BaseModel;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.base.BaseView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import rx.Observable;

/**
 * Created by AAA on 2017/9/22.
 */

public interface SuggestContract {

    interface View extends BaseView {

        void responseSuggestData();

        void responseSuggestDataError(String msg);

        void showDialog();

        void hideDialog();

    }

    interface Model extends BaseModel {

        Observable<String> subSuggestInfo(
                String the_name,
                String contact,
                String service_description,
                int complaints_type,
                String contract_no,
                List<LocalMedia> imgList
        );

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void subSuggestInfo(
                String the_name,
                String contact,
                String service_description,
                int complaints_type,
                String contract_no,
                List<LocalMedia> imgList
        );

    }

}
