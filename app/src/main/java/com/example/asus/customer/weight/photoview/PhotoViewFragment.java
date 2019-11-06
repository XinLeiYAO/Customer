package com.example.asus.customer.weight.photoview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.customer.R;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.PlanCollectionByOrderTypeBean;
import com.example.asus.customer.entity.QuanBuBean;
import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;
import com.komi.slider.position.SliderPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import uk.co.senab.photoview.PhotoViewAttacher;


public class PhotoViewFragment extends BaseFragment {
    @Bind(R.id.ViewPager)
    VerticalViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private SliderConfig mConfig;
    private ArrayList<ImageView> mList;
    @Bind(R.id.text)
    TextView textView;
//    private int imageIds[] = {R.mipmap.banner_img1, R.mipmap.banner_img1, R.mipmap.banner_img1};
//    private List<PlanCollectionByOrderTypeBean.BodyBean> imgList = new ArrayList<>();
    private String[] imgUrl = {};

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_photoview;
    }

    @Override
    protected void FragmentInitData() {
        initView();
        initShowphoto();
        initData();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photoview);
////        initSilder();
//        initView();
//        initShowphoto();
//    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    private void initShowphoto() {
        //Bundle bundle = getIntent().getExtras();
//        Intent intent = getIntent();
//        int i = intent.getIntExtra("showphoto",0);
//        setSelect(0);//通过传值获取点击图片位置,从而显示当前图片
    }
    //实现界面上下滑动退出界面效果
//    private void initSilder() {
//        mConfig = new SliderConfig.Builder()
//                .secondaryColor(Color.TRANSPARENT)
//                .position(SliderPosition.VERTICAL)  //设置上下滑动
//                .edge(false)  //是否允许有滑动边界值,默认是有的true
//                .build();
//        SliderUtils.attachActivity(getActivity(), mConfig);
//    }

    private void initView() {
//        viewPager=(VerticalViewPager)findViewById(R.id.ViewPager);
//        textView=(TextView)findViewById(R.id.text);

        mList = new ArrayList<ImageView>();

        pagerAdapter = new PagerAdapter() {

            // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
            @Override
            public int getCount() {
                return imgUrl.length;

                //return Integer.MAX_VALUE;    返回一个比较大的值，目的是为了实现无限轮播
            }

            // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
            // 我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getActivity());
//                imageView.setImageResource(imageIds[position]);
//                imageView.setImageResource(imageIds[position]);
                RequestOptions options = new RequestOptions();
//                options.placeholder(R.mipmap.home_bottom_img);
//                options.error(R.mipmap.home_bottom_img);

                FragmentActivity activity = getActivity();
                if (activity != null) {
                    Glide.with(activity).load(imgUrl[position]).apply(options).into(imageView);
                }
                //使图片具有放缩功能
//                PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
//                mAttacher.update();

                container.addView(imageView);
                mList.add(imageView);
                return imageView;
            }

            //PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mList.get(position));
            }


        };

        viewPager.setAdapter(pagerAdapter);

        //设置滑动监听事件
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            //滑动到第几张图片的调用的方法，position当前显示图片位置
//            @Override
//            public void onPageSelected(int position) {
//                switch (position){
//                    case 0:
//                        setSelect(0);
//                        textView.setText("1/3");
//                        break;
//                    case 1:
//                        setSelect(1);
//                        textView.setText("2/3");
//                        break;
//                    case 2:
//                        textView.setText("3/3");
//                        setSelect(2);
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    //启动查看图片时，显示你点击图片
    public void setSelect(int i) {
        switch (i) {
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                break;
            case 2:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private void initData() {
        Bundle arguments = getArguments();
        String id = (String) arguments.get("id");
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdId", value);
        map.put("orderType", id);
        OkhttpUtils.doGet(ApiEngine.ZHA_HOST + "DesignInstitute/contract/getPlanCollectionByOrderType", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PlanCollectionByOrderTypeBean bean = JSONUtils.toObject(string, PlanCollectionByOrderTypeBean.class);
//                        imgList.clear();
                        List<PlanCollectionByOrderTypeBean.BodyBean> body = bean.getBody();
                        String imgUrls = body.get(0).getImgUrl();
                        imgUrl = imgUrls.split(",");
                        textView.setText(1+"/"+imgUrl.length);
//                        imgList.addAll(body);
                        pagerAdapter.notifyDataSetChanged();

                    }
                });

            }
        });
    }
}
