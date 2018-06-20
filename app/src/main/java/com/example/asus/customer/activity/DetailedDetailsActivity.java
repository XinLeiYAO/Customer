package com.example.asus.customer.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.RecyViewAdapter;
import com.example.asus.customer.adapter.VolumePictureAdapter;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.entity.PhotoDetailsBean;
import com.example.asus.customer.entity.ProgssInfo;
import com.example.asus.customer.mvp.contract.DetailedDetailsContract;
import com.example.asus.customer.mvp.presenter.DetailedDetailsPresenter;
import com.example.asus.customer.weight.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by asus on 2018/4/28.
 */

public class DetailedDetailsActivity extends BaseActivity<DetailedDetailsPresenter> implements DetailedDetailsContract.View {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.show_popup)
    RelativeLayout showPopup;
    @Bind(R.id.housing_Situation)
    TextView housingSituation;
    @Bind(R.id.Rent_period)
    TextView RentPeriod;
    @Bind(R.id.Areause)
    TextView Areause;
    @Bind(R.id.Builtarea)
    TextView Builtarea;
    @Bind(R.id.Estimatedamount)
    TextView Estimatedamount;
    @Bind(R.id.Decorationtime)
    TextView Decorationtime;
    @Bind(R.id.Geomanticrequirements)
    TextView Geomanticrequirements;
    @Bind(R.id.Softfurnishings)
    TextView Softfurnishings;
    @Bind(R.id.Intelligentelectricity)
    TextView Intelligentelectricity;
    @Bind(R.id.Useelevator)
    TextView Useelevator;
    @Bind(R.id.Protectivematerial)
    TextView Protectivematerial;
    @Bind(R.id.Constructiontime)
    TextView Constructiontime;
    @Bind(R.id.Designatedcompany)
    TextView Designatedcompany;
    @Bind(R.id.Designatedcontrocompany)
    TextView Designatedcontrocompany;
    @Bind(R.id.Allrequirementsproject)
    TextView Allrequirementsproject;
    @Bind(R.id.Designatedtransportationcompany)
    TextView Designatedtransportationcompany;
    @Bind(R.id.Blueprint)
    TextView Blueprint;
    @Bind(R.id.Qualificationrequirements)
    TextView Qualificationrequirements;
    @Bind(R.id.AirrotectionStation)
    TextView AirrotectionStation;
    @Bind(R.id.Drawingaudit)
    TextView Drawingaudit;
    @Bind(R.id.Volumephoto)
    RecyclerView Volumephoto;
    private  ProgssInfo.BodyBean bodyBean ;
    private ArrayList<PhotoDetailsBean> mlist;
    VolumePictureAdapter recyViewAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_detaileddetails;
    }
//ca_DesignatedFireCompany
    @Override
    public void initData() {

        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("量房详情");
        bodyBean= (ProgssInfo.BodyBean) getIntent().getSerializableExtra(Constants.VectorRoomTransferDetails);
        if(null==bodyBean){

        }else {
            String ca_transactionType = bodyBean.getHousingResourcesInformation().getCa_HousingType();
            housingSituation.setText(ca_transactionType);
            RentPeriod.setText(bodyBean.getHousingResourcesInformation().getCa_RentFreeDate());
            Areause.setText(bodyBean.getHousingResourcesInformation().getCi_Area() + "");
            Builtarea.setText(bodyBean.getHousingResourcesInformation().getCi_BuildingArea() + "");
            Estimatedamount.setText(bodyBean.getDecorateInformation().getCa_DecBudgetPrice() + "");
            Decorationtime.setText(bodyBean.getDecorateInformation().getCa_DecorationDate() + "");
            Geomanticrequirements.setText(bodyBean.getDecorateInformation().getCa_FengShuiRequirements());
            Softfurnishings.setText(bodyBean.getDecorateInformation().getCa_SoftFurniture());
            Intelligentelectricity.setText(bodyBean.getDecorateInformation().getCa_IntelligentWeakCurrent());
            //ca_DesignatedFireCompany
            Useelevator.setText(bodyBean.getPropertyInformation().getCa_DesignatedFireCompany());


            Protectivematerial.setText(bodyBean.getPropertyInformation().getCa_ProtectiveMaterial());
            Constructiontime.setText(bodyBean.getPropertyInformation().getCa_ReqConTime());
            Designatedcompany.setText(bodyBean.getPropertyInformation().getCa_DesignatedAirCompany());
            //ca_DesignatedFireCompany
            if (bodyBean.getPropertyInformation().getCa_DesignatedSinotrans().equals("1")) {
                Designatedcontrocompany.setText("是");
            } else {
                Designatedcontrocompany.setText("否");
            }

            Allrequirementsproject.setText(bodyBean.getPropertyInformation().getCa_PropertyInsurance());

            if (bodyBean.getPropertyInformation().getCa_DesignatedSinotrans().equals("1")) {
                Designatedtransportationcompany.setText("是");
            } else {
                Designatedtransportationcompany.setText("否");
            }

            //ca_Blueprint
            Blueprint.setText(bodyBean.getPropertyInformation().getCa_Blueprint());
            Qualificationrequirements.setText(bodyBean.getPropertyInformation().getCa_Aptitude());
            //ca_Maintenance
            AirrotectionStation.setText(bodyBean.getPropertyInformation().getCa_Maintenance());
            //ca_DrawAudit
            Drawingaudit.setText(bodyBean.getPropertyInformation().getCa_DrawAudit());

            CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this, 2);
            linearLayoutManager.setScrollEnabled(false);
            mlist = new ArrayList<>();
            Volumephoto.setLayoutManager(linearLayoutManager);
            recyViewAdapter = new VolumePictureAdapter(mlist, this, "量房");
            Volumephoto.setAdapter(recyViewAdapter);
            mPresenter.volumeRoomPhooto(App.orderNo);
        }

    }

    @Override
    protected DetailedDetailsPresenter onCreatePresenter() {
        return new DetailedDetailsPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick({R.id.iv_back, R.id.show_popup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.show_popup:
                break;
        }
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
    public void volumeRoomPhooto(List<PhotoDetailsBean> list) {
        this.mlist.clear();
        for (int i = 0; i <list.size() ; i++) {
            String catalogId = list.get(i).getCatalogId();
            if(catalogId.equals("123")||catalogId.equals("109")||catalogId.equals("124")||catalogId.equals("125")){
                this.mlist.add(list.get(i));
            }
        }

       recyViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
      showToast(message);
    }
}
