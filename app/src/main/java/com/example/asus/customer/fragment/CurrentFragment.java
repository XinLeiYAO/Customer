package com.example.asus.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.PhotoImageActivity;
import com.example.asus.customer.adapter.RecyViewAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.CurrentContract;
import com.example.asus.customer.mvp.presenter.CurrentPresenter;
import com.example.asus.customer.weight.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/16.
 */

public class CurrentFragment extends BaseFragment<CurrentPresenter> implements CurrentContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    ArrayList<String> original;
    ArrayList<String> flatcolor;
    @Bind(R.id.design_sheet)
    TextView designSheet;
    @Bind(R.id.Designsheet)
    RecyclerView Designsheet;
    @Bind(R.id.prototype_text)
    TextView prototypeText;
    @Bind(R.id.Archetypediagram)
    RecyclerView Archetypediagram;
    @Bind(R.id.DesignDescription)
    TextView DesignDescription;
    @Bind(R.id.element_text)
    TextView elementText;
    @Bind(R.id.element_list)
    RecyclerView elementList;
    @Bind(R.id.color_text)
    TextView colorText;
    @Bind(R.id.color_list)
    RecyclerView colorList;
    @Bind(R.id.textureMateria_text)
    TextView textureMateriaText;
    @Bind(R.id.textureMateria_list)
    RecyclerView textureMateriaList;
    @Bind(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private RecyViewAdapter originalAdapter;
    private RecyViewAdapter flatcolorlAdapter;
    private RecyViewAdapter elementAdapter;
    private RecyViewAdapter colorAdapter;
    private RecyViewAdapter textureMaterialAdapter;
    private ArrayList<String> element;
    private ArrayList<String> color;
    private ArrayList<String> textureMateria;
    private ArrayList<String> one;
    private ArrayList<String> two;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_current;
    }

    @Override
    protected void FragmentInitData() {
        one=new ArrayList<>();
        two=new ArrayList<>();

        original = new ArrayList<>();
        flatcolor = new ArrayList<>();
        element = new ArrayList<String>();
        color = new ArrayList<String>();
        textureMateria = new ArrayList<>();
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(getContext(), 2);
        linearLayoutManager.setScrollEnabled(false);
        Designsheet.setLayoutManager(linearLayoutManager);
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), 2);
        manager.setScrollEnabled(false);
        Archetypediagram.setLayoutManager(manager);
        CustomLinearLayoutManager elementmanager = new CustomLinearLayoutManager(getContext(), 2);
        manager.setScrollEnabled(false);
        elementList.setLayoutManager(elementmanager);
        CustomLinearLayoutManager colortmanager = new CustomLinearLayoutManager(getContext(), 2);
        manager.setScrollEnabled(false);
        colorList.setLayoutManager(colortmanager);
        CustomLinearLayoutManager textureMateriatmanager = new CustomLinearLayoutManager(getContext(), 2);
        manager.setScrollEnabled(false);
        textureMateriaList.setLayoutManager(textureMateriatmanager);

        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                        mPresenter.loadHomedata(App.orderNo);
//                        mPresenter.loadCurreerPhotoData(App.body.getOrderNo());
//                        mPresenter.getProgssData(App.body.getOrderNo());
//                        mPresenter.loadDesignDescription(App.body.getOrderNo());

            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    protected CurrentPresenter onCreatePresenter() {
        return new CurrentPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
//
//        mPresenter.loadHomedata(App.body.getOrderNo());
//        mPresenter.loadCurreerPhotoData(App.body.getOrderNo());
//        mPresenter.getProgssData(App.body.getOrderNo());
//        mPresenter.loadDesignDescription(App.body.getOrderNo());
    }

    @Override
    public void showDialog() {
        showLoading();
    }

    @Override
    public void hideDialog() {
        dismissLoading();
    }

    @Override
    public void getCurrentData(Picture.BodyBean Body) {
        original.clear();
        flatcolor.clear();
        one.clear();
        two.clear();
        List<Picture.BodyBean.CgBean> cg = Body.getCg();
        List<Picture.BodyBean.CpBean> cp = Body.getCp();

        if (cg != null) {
            for (Picture.BodyBean.CgBean string : cg) {
                original.add("http://img0.wenes.cn/Upload/Project/" + string.getImgthumbnail());
                one.add("http://img0.wenes.cn/Upload/Project/" + string.getImageUrl());
            }
        }
        if (cp != null) {
            for (Picture.BodyBean.CpBean string : cp) {
                //string.getImageUrl();
                flatcolor.add("http://img0.wenes.cn/Upload/Project/" + string.getImgthumbnail());
                two.add("http://img0.wenes.cn/Upload/Project/" + string.getImageUrl());
            }
        }


        originalAdapter = new RecyViewAdapter(original, getContext(), designSheet.getText().toString());
        flatcolorlAdapter = new RecyViewAdapter(flatcolor, getContext(), prototypeText.getText().toString());

        Designsheet.setAdapter(originalAdapter);
        Archetypediagram.setAdapter(flatcolorlAdapter);
        swiperereshlayout.setRefreshing(false);//设置不刷新
//接口回掉
        originalAdapter.setListener(new RecyViewAdapter.onClick() {
            @Override
            public void setItem(View v, int possiton) {
                Intent intent = new Intent(getContext(), PhotoImageActivity.class);
                intent.putExtra(Constants.INDEXES, possiton);
                intent.putExtra(Constants.TITLE, "初稿");
                intent.putStringArrayListExtra(Constants.JUMPLIST, one);
                startActivity(intent);
            }
        });
        flatcolorlAdapter .setListener(new RecyViewAdapter.onClick() {
            @Override
            public void setItem(View v, int possiton) {
                Intent intent = new Intent(getContext(), PhotoImageActivity.class);
                intent.putExtra(Constants.INDEXES, possiton);
                intent.putExtra(Constants.TITLE, "彩屏");
                intent.putStringArrayListExtra(Constants.JUMPLIST, two);
                startActivity(intent);
            }
        });

    }


    @Override
    public void showMessage(String message) {
        showToast(message);
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }

    @Override
    public void loadDesignDescription(DesignInfo.BodyBean bodyBean) {
        if (null != bodyBean) {
            DesignDescription.setText(bodyBean.getProjectBrief());
        }
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }

    @Override
    public void loadCurreerPhotoData(CurreerPhotoDataBean.BodyBean bodyBean) {
        //元素、色彩、材质图片信息

        element.clear();
        color.clear();
        textureMateria.clear();
        List<CurreerPhotoDataBean.BodyBean.CzBean> cz = bodyBean.getCz();
        List<CurreerPhotoDataBean.BodyBean.ScBean> sc = bodyBean.getSc();
        List<CurreerPhotoDataBean.BodyBean.YsBean> ys = bodyBean.getYs();
        if (cz != null) {
            for (CurreerPhotoDataBean.BodyBean.CzBean string : cz) {
                element.add(string.getClassD());
            }
        }
        if (sc != null) {
            for (CurreerPhotoDataBean.BodyBean.ScBean string : sc) {
                color.add(string.getClassD());
            }
        }
        if (ys != null) {
            for (CurreerPhotoDataBean.BodyBean.YsBean string : ys) {
                textureMateria.add(string.getClassD());
            }
            elementAdapter = new RecyViewAdapter(element, getContext(), "元素");
            colorAdapter = new RecyViewAdapter(color, getContext(), "色彩");
            textureMaterialAdapter = new RecyViewAdapter(textureMateria, getContext(), "材质");
            elementList.setAdapter(elementAdapter);
            colorList.setAdapter(colorAdapter);
            textureMateriaList.setAdapter(textureMaterialAdapter);
        }
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }

    @Override
    public void getProgssData(ProgssInfo.BodyBean bodyBean) {
        if(bodyBean==null){
            tvTitle.setText("首页");
        }else {
            String ci_clientName = bodyBean.getBaseInformation().getCi_ClientName();
            tvTitle.setText(ci_clientName);
            swiperereshlayout.setRefreshing(false);//设置不刷新
        }

    }

    @Override
    public void getRecommendData(RecommendBean bean) {

    }

    @OnClick(R.id.DesignDescription)
    public void onViewClicked() {
    }
}
