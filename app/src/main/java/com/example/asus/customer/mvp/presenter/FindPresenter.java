package com.example.asus.customer.mvp.presenter;

import android.util.Log;

import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.ProjectBean;
import com.example.asus.customer.fragment.ProgrammeFragment;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.mvp.contract.FindContract;
import com.example.asus.customer.mvp.model.CurrentModel;
import com.example.asus.customer.mvp.model.FindModel;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by asus on 2018/4/17.
 */

public class  FindPresenter  extends FindContract.Presenter{
    private String TAG="CurrentPresenter";

    public  FindPresenter(FindContract.View mView) {
        this.mView = mView;
        mModel = new FindModel();
    }
    @Override
    public void getProgssData(String rwdId) {
        Subscription subscribe = mModel.getProgssData(rwdId)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        FindInfo picture = JSONUtils.toObject(s, FindInfo.class);
                        if(picture.getStatusCode()==0){
                            mView.getProgssData(picture.getBody());
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getFindData(String tb_diqu, String ci_Type, String ca_IntentionalStyle, String ci_Area, String tb_ca_DecBudgetPrice) {
        Subscription subscribe = mModel.getFindData(tb_diqu,ci_Type,ca_IntentionalStyle,ci_Area,tb_ca_DecBudgetPrice)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                        onCompleted();
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                      //  Log.e("find",s);

                        FindInformationBean picture = JSONUtils.toObject(s, FindInformationBean.class);
                        if(picture.getStatusCode()==0){
                            mView.getfindData(picture.getBody());
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getContractData(String rwdId, String ca) {
        Subscription subscribe = mModel.getContractData(rwdId,ca)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        ProjectBean picture = JSONUtils.toObject(s, ProjectBean.class);
                        if(picture.getStatusCode()==0){
                            mView.getUpDataProject();
                        }else {
                            mView.showMessage(picture.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

//    @Override
//    public void getFindData(String projectType, String diquId, String area) {
//        Subscription subscribe = mModel.getFindData(projectType,diquId,area)
//                .subscribe(new Subscriber<String>() {
//
//                    @Override
//                    public void onStart() {
//                        mView.showDialog();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mView.hideDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG,e.getMessage());
//                        onCompleted();
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                      //  Log.e("find",s);
//
//                        FindInformationBean picture = JSONUtils.toObject(s, FindInformationBean.class);
//                        if(picture.getStatusCode()==0){
//                            Log.e("find",s);
//                            mView.getfindData(picture.getBody());
//                        }else {
//                            mView.showMessage(picture.getStatusMsg());
//                        }
////                        if(picture.getStatusCode()==0){
////                            mView.getProgssData(picture.getBody());
////                        }else {
////                            mView.showMessage(picture.getStatusMsg());
////                        }
//                    }
//                });
//        addSubscribe(subscribe);
    //}
}
