package com.example.asus.customer.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.DetailedDetailsActivity;
import com.example.asus.customer.activity.PhotoImageActivity;
import com.example.asus.customer.adapter.ScoreAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.entity.CurreerPhotoDataBean;
import com.example.asus.customer.entity.DesignInfo;
import com.example.asus.customer.entity.MineUserInfo;
import com.example.asus.customer.entity.OptimizationBean;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.Picture;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.entity.RecommendBean;
import com.example.asus.customer.mvp.contract.ProcessContract;
import com.example.asus.customer.mvp.presenter.ProcessPresenter;
import com.example.asus.customer.weight.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/16.
 */

public class ProcessFragment extends BaseFragment<ProcessPresenter> implements ProcessContract.View {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.show_popup)
    RelativeLayout showPopup;
    @Bind(R.id.Designer_listview)
    RecyclerView DesignerListview;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.img2)
    ImageView img2;
    @Bind(R.id.Designer_photo)
    ImageView DesignerPhoto;
    @Bind(R.id.Designer_name)
    TextView DesignerName;
    @Bind(R.id.Designer_flag)
    TextView DesignerFlag;
    @Bind(R.id.ca_property_management_company)
    TextView caPropertyManagementCompany;
    @Bind(R.id.type_stat)
    TextView typeStat;
    @Bind(R.id.ci_type)
    TextView ciType;
    @Bind(R.id.area)
    TextView area;
    @Bind(R.id.begood_at_one)
    TextView begoodAtOne;
    @Bind(R.id.begood_at_two)
    TextView begoodAtTwo;
    @Bind(R.id.begood_at_there)
    TextView begoodAtThere;
    @Bind(R.id.Design_Description)
    TextView DesignDescription;
    @Bind(R.id.DetailedRoomdetails)
    LinearLayout DetailedRoomdetails;
    @Bind(R.id.img3)
    ImageView img3;
    @Bind(R.id.img4)
    ImageView img4;
    @Bind(R.id.img5)
    ImageView img5;
    @Bind(R.id.worker_budget)
    TextView workerBudget;
    @Bind(R.id.company)
    TextView company;
    @Bind(R.id.lookmore_tv)
    TextView lookmoreTv;

    private ArrayList<String> element;
    private ArrayList<String> color;
    private ArrayList<String> textureMateria;

    private List<Integer> mstats;
    private ScoreAdapter scoreAdapter;
    ArrayList<String> original;
    ArrayList<String> flatcolor;
    private Intent intent;
    private ProgssInfo.BodyBean bodyBean;
    @Bind(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;
    private ArrayList<String> one;
    private ArrayList<String> two;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_process;
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
        tvTitle.setText("过程");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        DesignerListview.setLayoutManager(linearLayoutManager);
        mstats = new ArrayList<>();
        scoreAdapter = new ScoreAdapter(mstats, getContext());
        DesignerListview.setAdapter(scoreAdapter);

        intent = new Intent(getContext(), PhotoImageActivity.class);
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                mPresenter.getProgssData(App.body.getOrderNo());
//                mPresenter.loadCurreerPhotoData(App.body.getOrderNo());
//                mPresenter.loadHomedata(App.body.getOrderNo());
//                mPresenter.loadDesignDescription(App.body.getOrderNo());

            }
        });
        initView();
    }
//查看更多
    private void initView() {
        lookmoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HeTongFragment.class));
            }
        });
    }

    @Override
    protected ProcessPresenter onCreatePresenter() {
        return new ProcessPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
      //  Log.e("lrj", App.body.getOrderNo());
//        mPresenter.getProgssData(App.body.getOrderNo());
//        mPresenter.loadCurreerPhotoData(App.body.getOrderNo());
//        mPresenter.loadHomedata(App.body.getOrderNo());
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
    public void getProgssData(ProgssInfo.BodyBean bodyBean) {
        this.bodyBean = bodyBean;
        mPresenter.loadCustomerInformation(bodyBean.getBaseInformation().getCi_DesignerCard());
        ProgssInfo.BodyBean.BaseInformationBean baseInformation = bodyBean.getBaseInformation();
//        address.setText(baseInformation.getCi_OfficeAddress());
        ciType.setText(baseInformation.getCi_OfficeAddress());
        caPropertyManagementCompany.setText(baseInformation.getCi_ClientName());
        //HousingResourcesInformationBean
        ProgssInfo.BodyBean.HousingResourcesInformationBean housingResourcesInformation = bodyBean.getHousingResourcesInformation();
        typeStat.setText(housingResourcesInformation.getCa_AvailabilityStatus());
        String ci_area = housingResourcesInformation.getCi_BuildingArea();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ci_area);
        spannableStringBuilder.append("m2");
        area.setText(spannableStringBuilder);
        swiperereshlayout.setRefreshing(false);//设置不刷新

    }

    @Override
    public void showMessage(String message) {
        showToast(message);
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }

    @Override
    public void loadCustomerInformation(MineUserInfo.BodyBean bodyBean) {
        mstats.clear();
        Glide.with(this).load(bodyBean.getMavatar()).into(DesignerPhoto);
        DesignerName.setText(bodyBean.getName());
        DesignerFlag.setText(bodyBean.getDdeclaration());
        String mstar = bodyBean.getMstar();
        for (int i = 0; i < Integer.parseInt(mstar); i++) {
            mstats.add(R.mipmap.star);
        }
        scoreAdapter.notifyDataSetChanged();
        String goodType = bodyBean.getGoodType();
        if (!goodType.isEmpty()) {
            String[] split = goodType.split(":");
            if (!split[0].isEmpty()) {
                begoodAtOne.setVisibility(View.VISIBLE);
                begoodAtOne.setText(split[0]);
            }
            if (!split[1].isEmpty()) {
                begoodAtTwo.setVisibility(View.VISIBLE);
                begoodAtTwo.setText(split[1]);
            }
            if (!split[2].isEmpty()) {

            }

        }
        swiperereshlayout.setRefreshing(false);//设置不刷新

    }

    @Override
    public void getCurrentData(Picture.BodyBean body) {
        original.clear();
        flatcolor.clear();
        List<Picture.BodyBean.CgBean> cg = body.getCg();
        List<Picture.BodyBean.CpBean> cp = body.getCp();
        if (cg != null) {
            for (Picture.BodyBean.CgBean string : cg) {
                original.add("http://img0.wenes.cn/Upload/Project/" + string.getImgthumbnail());
                one.add("http://img0.wenes.cn/Upload/Project/" + string.getImageUrl());
            }
        }
        if (cp != null) {
            for (Picture.BodyBean.CpBean string : cp) {
                flatcolor.add("http://img0.wenes.cn/Upload/Project/" + string.getImgthumbnail());
                two.add("http://img0.wenes.cn/Upload/Project/" + string.getImageUrl());
            }
        }
        Glide.with(getContext()).load(original.get(0)).into(img);
        Glide.with(getContext()).load(flatcolor.get(0)).into(img2);
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
        element.clear();
        color.clear();
        textureMateria.clear();
        List<CurreerPhotoDataBean.BodyBean.CzBean> cz = bodyBean.getCz();
        List<CurreerPhotoDataBean.BodyBean.ScBean> sc = bodyBean.getSc();
        List<CurreerPhotoDataBean.BodyBean.YsBean> ys = bodyBean.getYs();
        if (cz != null) {
            for (CurreerPhotoDataBean.BodyBean.CzBean string : cz) {
                element.add(string.getClassD());
                Glide.with(getContext()).load(element.get(0)).into(img3);
            }
        }
        if (sc != null) {
            for (CurreerPhotoDataBean.BodyBean.ScBean string : sc) {
                color.add(string.getClassD());
                Glide.with(getContext()).load(color.get(0)).into(img4);
            }
        }
        if (ys != null) {
            for (CurreerPhotoDataBean.BodyBean.YsBean string : ys) {
                textureMateria.add(string.getClassD());
                Glide.with(getContext()).load(textureMateria.get(0)).into(img5);
            }

        }
        swiperereshlayout.setRefreshing(false);//设置不刷新
    }

    @Override
    public void volumeRoomPhooto(List<PhotoDetailsBean> mlist) {

    }

    @Override
    public void getOptimizationBean(OptimizationBean bean) {

    }

    @Override
    public void getRecommendData(RecommendBean bean) {

    }


    @OnClick({R.id.img, R.id.img2, R.id.img3, R.id.img4, R.id.img5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:

                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "初稿");
                intent.putStringArrayListExtra(Constants.JUMPLIST, one);
                startActivity(intent);
                break;
            case R.id.img2:
                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "彩平");
                intent.putStringArrayListExtra(Constants.JUMPLIST, two);
                startActivity(intent);
                break;
            case R.id.img3:
                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "元素");
                intent.putStringArrayListExtra(Constants.JUMPLIST, element);
                startActivity(intent);
                break;
            case R.id.img4:
                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "色彩");
                intent.putStringArrayListExtra(Constants.JUMPLIST, color);
                startActivity(intent);
                break;
            case R.id.img5:
                intent.putExtra(Constants.INDEXES, 0);
                intent.putExtra(Constants.TITLE, "材质");
                intent.putStringArrayListExtra(Constants.JUMPLIST, textureMateria);
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.DetailedRoomdetails)
    public void onViewClicked() {

        startActivity(new Intent(getContext(), DetailedDetailsActivity.class).putExtra(Constants.VectorRoomTransferDetails, bodyBean));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
