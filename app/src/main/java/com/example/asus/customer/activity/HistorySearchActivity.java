package com.example.asus.customer.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.Recycler_Item_Adapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.ShowUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.NewsBean;
import com.plattysoft.leonids.ParticleSystem;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 新闻历史
 */
public class HistorySearchActivity extends BaseActivity {
    @Bind(R.id.news_shousuo_recycler)
    RecyclerView newsShousuoRecycler;
    @Bind(R.id.image_erro)
    ImageView imageErro;
    @Bind(R.id.smart_news)
    SmartRefreshLayout smartNews;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    public static int isRead = 0;//是否已读
    public static int isLike = 0;// 0没有事件  1 是点赞  2 是取消点赞
    private int position = 0;//当前点击的item
    private ArrayList<NewsBean.BodyBean> arrayList = new ArrayList();
    private String n_grade;
    private String n_cityId;
    private String content;
    private Recycler_Item_Adapter recycler_item_adapter;
    private int isreFreshLoad = 0;
    private int index;
    private int isDianZan = 0;//是否是点赞  （区分是否加载 点在成功的粒子动画）
    private int isAnimation = 0;//是否在执行动画 （在执行动画的时候不能刷新适配器）
    private int page = 1;
    private Vibrator vibrator;

    @Override
    public int getLayout() {
        return R.layout.activity_history_search;
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 1;
        data();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                NewsBean newsBean = (NewsBean) msg.obj;
                List<NewsBean.BodyBean> body = newsBean.getBody();
                if (page == 1) {
                    arrayList.clear();
                }
                //加载的时候 获取的数据为零
                if (isreFreshLoad == 1 && body.size() == 0) {
                    //页数恢复上一页
                    --page;
                }
                arrayList.addAll(body);
                if (arrayList.size() == 0) {
                    imageErro.setVisibility(View.VISIBLE);
                    imageErro.setImageResource(R.mipmap.wuneirong);
                }
                recycler_item_adapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("浏览历史");
        recycler_item_adapter = new Recycler_Item_Adapter(this, arrayList);
        newsShousuoRecycler.setLayoutManager(new LinearLayoutManager(this));
        newsShousuoRecycler.setAdapter(recycler_item_adapter);
        recycler_item_adapter.setNewsCallBack(new Recycler_Item_Adapter.NewsCallBack() {
            @Override
            public void setOnNewsClick(int position) {
                NewsBean.BodyBean bodyBean = arrayList.get(position);
                int id = bodyBean.getId();
                String n_visitState = bodyBean.getN_visitState();
                String n_titile = bodyBean.getTitle();
                String n_contentPart = bodyBean.getN_contentPart();
                Intent intent = new Intent(HistorySearchActivity.this, News_Wab_Activity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", n_titile);
                intent.putExtra("content", n_contentPart);
                intent.putExtra("html", bodyBean.getJumpUrl());
                intent.putExtra("status", n_visitState + "");
                intent.putExtra("is",  "sousuo");
                startActivity(intent);
            }

            @Override
            public void setOnNewsDianZanClick(View view, int position) {
                if (index == position) {
                    if (isFastClick()) {
                        ShowUtils.Toastshort(HistorySearchActivity.this, "请不要频繁点击");
                        return;
                    }
                }
                vibrator.vibrate(50);
                index = position;
                NewsBean.BodyBean bodyBean = arrayList.get(position);
                int id = bodyBean.getId();
                String cardNo = MySharedPreferences.getInstance().getCardNo();
                HashMap<String, Object> map = new HashMap<>();
                map.put("cardNo", cardNo);
                map.put("id", id + "");
                String n_visitState1 = bodyBean.getN_visitState();
                Integer visitState = Integer.parseInt(n_visitState1);
                int n_visitState = visitState;
                if (n_visitState == 0) {
                    bodyBean.setN_visitState("1");
                    bodyBean.setN_visitNum(bodyBean.getN_visitNum() + 1);
                } else {
                    bodyBean.setN_visitState("0");
                    bodyBean.setN_visitNum(bodyBean.getN_visitNum() - 1);
                }
//                recycler_item_adapter.notifyDataSetChanged();

                isDianZan = visitState;
                isAnimation = 1;
                setAnimation(view);
                dianZan(map, position);


            }
        });

        smartNews.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                isreFreshLoad = 0;
                page = 1;
                data();


            }
        });
        smartNews.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
//                arrayList.clear();
                isreFreshLoad = 1;
                ++page;
                data();
            }
        });

        //设置 Header 为 原始 样式
        smartNews.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 原始 样式
        smartNews.setRefreshFooter(new ClassicsFooter(this));
        data();

    }

    public void data(){
        if (page == 1) {
            arrayList.clear();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("cardNo", App.cardNo);
        map.put("pageSize", 10);
        map.put("page", page);
        OkhttpUtils.doGet(ApiEngine.RS_NEWS_HOST + "api/appNews/appNewsContent/findHistoryAppNewsList", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("新闻列表获取失败", "onError: " + e.getMessage());
                //JieMianUtils.loadShiBai(HistorySearchActivity.this, mSmart, mImg, mTv);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("data", string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(string);
                            int statusCode = jsonObject.getInt("StatusCode");
                            if (statusCode == 1) {
                                NewsBean newsBean = JSONUtils.toObject(string, NewsBean.class);
                                List<NewsBean.BodyBean> body = newsBean.getBody();
                                if(body.size() < 10 && body != null){
                                    smartNews.finishLoadMoreWithNoMoreData();
                                }
                                Message message = new Message();
                                message.what = 10;
                                message.obj = newsBean;
                                handler.sendMessage(message);
                            } else {
                                if (isreFreshLoad == 0) {
                                    imageErro.setVisibility(View.VISIBLE);
                                    imageErro.setImageResource(R.mipmap.jiazaishibai);
                                }
                                ShowUtils.Toastshort(HistorySearchActivity.this, jsonObject.getString("StatusMsg"));
//                                btnEnd.setEnabled(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    //点赞动效
    private void setAnimation(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1, 0.4f, 1);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1, 0.4f, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animator2);
        animatorSet.setDuration(500)
                .start();//动画 缩放
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimation = 0;
                if (isDianZan == 0) {
                    //缩放动画结束后 开始粒子动画
                    new ParticleSystem(HistorySearchActivity.this, 200, R.mipmap.dianzan_big_yes, 550)
                            .setScaleRange(0.1f, 0.2f)//粒子的大小
                            .setSpeedRange(0.05f, 0.1f)//粒子的速度
                            .setFadeOut(550)//渐变
                            .oneShot(view, 80, new DecelerateInterpolator());//一次性发射器  80颗粒子，内插器，其中变化率快速开始然后减速。
                }
                recycler_item_adapter.notifyDataSetChanged();
            }
        });
    }
    private void dianzanQuxiao(int position) {
        if (arrayList != null && arrayList.size() != 0) {
            NewsBean.BodyBean bodyBean = arrayList.get(position);
            String n_visitState1 = bodyBean.getN_visitState();
            Integer visitState = Integer.parseInt(n_visitState1);
            int n_visitState = visitState;
            if (n_visitState == 0) {
                bodyBean.setN_visitState("1");
                int i = bodyBean.getN_visitNum() + 1;
                bodyBean.setN_visitNum(i);
            } else {
                bodyBean.setN_visitState("0");
                int i = bodyBean.getN_visitNum() - 1;
                bodyBean.setN_visitNum(i);
            }
            isDianZan = visitState;
            if (isAnimation != 1) {
                recycler_item_adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 点赞
     *
     * @param map
     * @param position
     */
    private void dianZan(HashMap<String, Object> map, final int position) {
        OkhttpUtils.doPost(ApiEngine.RS_NEWS_HOST + "api/appNews/appNewsContent/saveNewsPraise", map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dianzanQuxiao(position);
                            }
                        });
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String statusCode = jsonObject.getString("StatusCode");
                    if (!"1".equals(statusCode)) {
                        dianzanQuxiao(position);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
