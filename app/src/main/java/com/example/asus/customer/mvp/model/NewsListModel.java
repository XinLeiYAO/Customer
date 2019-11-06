package com.example.asus.customer.mvp.model;


import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.mvp.contract.NewsListContract;
import com.example.asus.customer.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by Administrator on 2017/6/15.
 */
public class NewsListModel implements NewsListContract.Model
{

    @Override
    public Observable<String> getNewsList(String cardNo, int pageIndex, int pageSize)
    {
        return ApiEngine.getInstance().getfindApiService()
                .getFindLoadData(cardNo, pageIndex, pageSize)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getNewsList1(String cardNo, String appId, String postId, String page, String rows) {
        return  ApiEngine.getInstance().getFaXianApiService()
                .getNewsList1(cardNo, appId, postId,page,rows)
                .compose(RxSchedulers.<String>switchThread());
    }

    @Override
    public Observable<String> getNewsListLoadMore(String cardNo, int pageIndex, int pageSize)
    {
        return ApiEngine.getInstance().getfindApiService()
                .getFindLoadData(cardNo, pageIndex, pageSize)
                .compose(RxSchedulers.<String>switchThread());
    }
}
