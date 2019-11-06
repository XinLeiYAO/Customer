package com.example.asus.customer.mvp.presenter;

import android.util.Log;


import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.NewsListInfo;
import com.example.asus.customer.mvp.contract.NewsListContract;
import com.example.asus.customer.mvp.model.NewsListModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/6/15.
 */
public class NewsListPresenter extends NewsListContract.Presenter {

    public static final String TAG = "NewsListPresenter";

    private NewsListContract.View mView;

    public NewsListPresenter(NewsListContract.View mView) {
        this.mView = mView;
        mModel = new NewsListModel();
    }

    //cardNo=01012167
    //appId=0c5c9c88-5775-4a70-bafd-849115ef4d94
    //postId=8
    //page=1
    //rows=10

    @Override
    public void getNewsList1(String cardNo, String appId, String postId,String page,String rows) {
        Subscription subscribe = mModel.getNewsList1(cardNo, appId, postId,page,rows)
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
                        Log.e(TAG, "获取新闻列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("xinwen",s.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int statusCode =jsonObject .getInt("StatusCode");
                            if (statusCode == 0) {
                                NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                                NewsListInfo.BodyBean data = info.getBody();
                                List<NewsListInfo.BodyBean.TopListBean> bannerList = data.getTopList();
                                List<NewsListInfo.BodyBean.ListBean> newsList = data.getList();
                                mView.responseBannerData(bannerList);
                                mView.responseNewsListData(newsList);
                            }else{
                                String statusMsg = jsonObject.getString("StatusMsg");
                                mView.responseNewsListDataError(statusMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        addSubscribe(subscribe);
    }


    @Override
    public void getNewsList(String cardNo, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.getNewsList(cardNo, pageIndex, pageSize)
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
                        Log.e(TAG, "获取新闻列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("xinwen",s);
                        NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                        if (info.getStatusCode() == 0) {
                            NewsListInfo.BodyBean data = info.getBody();
                            List<NewsListInfo.BodyBean.TopListBean> bannerList = data.getTopList();
                            List<NewsListInfo.BodyBean.ListBean> newsList = data.getList();
                            mView.responseBannerData(bannerList);
                            mView.responseNewsListData(newsList);
                        } else {
                            mView.responseNewsListDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void getNewsListLoadMore(String cardNo, int pageIndex, int pageSize) {
        Subscription subscribe = mModel.getNewsListLoadMore(cardNo, pageIndex, pageSize)
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
                        Log.e(TAG, "获取新闻列表失败 = " + e.toString());
                        onCompleted();
                    }

                    @Override
                    public void onNext(String s) {
                        NewsListInfo info = JSONUtils.toObject(s, NewsListInfo.class);
                        if (info.getStatusCode() == 0) {
                            NewsListInfo.BodyBean data = info.getBody();
                            List<NewsListInfo.BodyBean.TopListBean> bannerList = data.getTopList();
                            List<NewsListInfo.BodyBean.ListBean> newsList = data.getList();
                            mView.responseBannerData(bannerList);
                            mView.responseNewsListLoadMoreData(newsList);
                        } else {
                            mView.responseNewsListLoadMoreDataError(info.getStatusMsg());
                        }
                    }
                });
        addSubscribe(subscribe);
    }
}
