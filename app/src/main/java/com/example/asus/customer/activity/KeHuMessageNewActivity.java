package com.example.asus.customer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.adapter.KeHuMessageNewAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseActivity;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.MySharedPreferences;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.entity.KeHuMessageNewBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class KeHuMessageNewActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lin_erro)
    LinearLayout linErro;
    @Bind(R.id.kehuMessage_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.layoutLiangfang)
    LinearLayout layoutLiangfang;
    @Bind(R.id.layoutWuye)
    LinearLayout layoutWuye;
    @Bind(R.id.layoutQiye)
    LinearLayout layoutQiye;
    @Bind(R.id.layoutFangwu)
    LinearLayout layoutFangwu;
    @Bind(R.id.layoutSheji)
    LinearLayout layoutSheji;
    @Bind(R.id.layoutShiGong)
    LinearLayout layoutShiGong;
    @Bind(R.id.layoutXvqiu)
    LinearLayout layoutXvqiu;
    @Bind(R.id.liangfang_rhmcx)
    TextView liangfang_rhmcx;
    @Bind(R.id.liangfang_ydmpz)
    TextView liangfang_ydmpz;
    @Bind(R.id.liangfang_htmkg)
    TextView liangfang_htmkg;
    @Bind(R.id.liangfang_fkzdd)
    TextView liangfang_fkzdd;
    @Bind(R.id.liangfang_qdx)
    TextView liangfang_qdx;
    @Bind(R.id.liangfang_rdx)
    TextView liangfang_rdx;
    @Bind(R.id.liangfang_ydmg)
    TextView liangfang_ydmg;
    @Bind(R.id.liangfang_zlxg)
    TextView liangfang_zlxg;
    @Bind(R.id.liangfang_clxg)
    TextView liangfang_clxg;
    @Bind(R.id.liangfang_plzdd)
    TextView liangfang_plzdd;
    @Bind(R.id.liangfang_chg)
    TextView liangfang_chg;
    @Bind(R.id.liangfang_ctg)
    TextView liangfang_ctg;
    @Bind(R.id.liangfang_chlx)
    TextView liangfang_chlx;
    @Bind(R.id.liangfang_cg)
    TextView liangfang_cg;
    @Bind(R.id.liangfang_mdgd)
    TextView liangfang_mdgd;
    @Bind(R.id.liangfang_sxs)
    TextView liangfang_sxs;
    @Bind(R.id.liangfang_dmgc)
    TextView liangfang_dmgc;
    @Bind(R.id.wuye_wygs)
    TextView wuye_wygs;
    @Bind(R.id.wuye_wydh)
    TextView wuye_wydh;
    @Bind(R.id.wuye_zzyq)
    TextView wuye_zzyq;
    @Bind(R.id.wuye_bhcl)
    TextView wuye_bhcl;
    @Bind(R.id.wuye_dtsy)
    TextView wuye_dtsy;
    @Bind(R.id.wuye_sgsj)
    TextView wuye_sgsj;
    @Bind(R.id.wuye_ktvbdw)
    TextView wuye_ktvbdw;
    @Bind(R.id.wuye_ljysfs)
    TextView wuye_ljysfs;
    @Bind(R.id.wuye_wyglf)
    TextView wuye_wyglf;
    @Bind(R.id.wuye_wyyj)
    TextView wuye_wyyj;
    @Bind(R.id.wuye_sdfy)
    TextView wuye_sdfy;
    @Bind(R.id.wuye_clfhdj)
    TextView wuye_clfhdj;
    @Bind(R.id.wuye_zdbxgs)
    TextView wuye_zdbxgs;
    @Bind(R.id.wuye_gcyqxyq)
    TextView wuye_gcyqxyq;
    @Bind(R.id.wuye_xfwbdw)
    TextView wuye_xfwbdw;
    @Bind(R.id.wuye_wysgyq)
    TextView wuye_wysgyq;
    @Bind(R.id.qiye_qc)
    TextView qiye_qc;
    @Bind(R.id.qiye_lx)
    TextView qiye_lx;
    @Bind(R.id.qiye_cl)
    TextView qiye_cl;
    @Bind(R.id.qiye_dw)
    TextView qiye_dw;
    @Bind(R.id.fangwu_xz)
    TextView fangwu_xz;
    @Bind(R.id.fangwu_mj)
    TextView fangwu_mj;
    @Bind(R.id.fangwu_dz)
    TextView fangwu_dz;
    @Bind(R.id.sheji_fsxq)
    TextView sheji_fsxq;
    @Bind(R.id.sheji_yxfg)
    TextView sheji_yxfg;
    @Bind(R.id.sheji_jssjf)
    TextView sheji_jssjf;
    @Bind(R.id.sheji_sjqf)
    TextView sheji_sjqf;
    @Bind(R.id.shigong_yjkysj)
    TextView shigong_yjkysj;
    @Bind(R.id.shigong_jtkgsj)
    TextView shigong_jtkgsj;
    @Bind(R.id.shigong_jhkgsj)
    TextView shigong_jhkgsj;
    @Bind(R.id.shigong_yjgq)
    TextView shigong_yjgq;
    @Bind(R.id.shigong_hbyq)
    TextView shigong_hbyq;
    @Bind(R.id.shigong_zxys)
    TextView shigong_zxys;
    @Bind(R.id.shigong_zb)
    TextView shigong_zb;
    @Bind(R.id.xvqiu_zxmd)
    TextView xvqiu_zxmd;
    @Bind(R.id.xvqiu_rdxq)
    TextView xvqiu_rdxq;
    @Bind(R.id.xvqiu_xfxq)
    TextView xvqiu_xfxq;
    @Bind(R.id.xvqiu_jjxq)
    TextView xvqiu_jjxq;
    @Bind(R.id.xvqiu_ktgz)
    TextView xvqiu_ktgz;
    @Bind(R.id.xvqiu_zdpp)
    TextView xvqiu_zdpp;
    @Bind(R.id.xvqiu_zxxq)
    TextView xvqiu_zxxq;
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<LinearLayout> layoutList = new ArrayList<>();
    private KeHuMessageNewAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_ke_hu_message_new;
    }

    @Override
    public void initData() {
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("量房资料");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        titleList.add("量房资料");
        titleList.add("物业信息");
        titleList.add("企业");
        titleList.add("房屋状况");
        titleList.add("设计需求");
        titleList.add("施工需求");
        titleList.add("需求");
        adapter = new KeHuMessageNewAdapter(this, titleList);
        recyclerView.setAdapter(adapter);
        adapter.setListDataCallBack(new KeHuMessageNewAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int position) {
                changLayout(position);
                adapter.notifyDataSetChanged();
            }
        });
        layoutList.add(layoutLiangfang);
        layoutList.add(layoutWuye);
        layoutList.add(layoutQiye);
        layoutList.add(layoutFangwu);
        layoutList.add(layoutSheji);
        layoutList.add(layoutShiGong);
        layoutList.add(layoutXvqiu);
        changLayout(0);
        getMessage();
    }

    private void getMessage() {
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(this, Constants.PN_Onumber);
        map.put("rwdId", value);
        map.put("type", "1");
        OkhttpUtils.doGet(ApiEngine.FS_API_HOST + "/order/orderInfo", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    int statusCode = object.getInt("StatusCode");
                    if (statusCode == 0) {
                        KeHuMessageNewBean bean = JSONUtils.toObject(string, KeHuMessageNewBean.class);
                        KeHuMessageNewBean.BodyBean body = bean.getBody();
                        //量房
                        KeHuMessageNewBean.BodyBean.MeasureInformationBean measureInformation = body.getMeasureInformation();
                        //楼盘信息
                        KeHuMessageNewBean.BodyBean.BuildingInformationBean buildingInformation = body.getBuildingInformation();
                        //物业信息
                        KeHuMessageNewBean.BodyBean.PropertyInformationBean propertyInformation = body.getPropertyInformation();
                        //基本信息
                        KeHuMessageNewBean.BodyBean.BaseInformationBean baseInformation = body.getBaseInformation();
                        //公司信息
                        KeHuMessageNewBean.BodyBean.CompanyInformationBean companyInformation = body.getCompanyInformation();
                        //装饰信息
                        KeHuMessageNewBean.BodyBean.DecorateInformationBean decorateInformation = body.getDecorateInformation();
                        //房源信息
                        KeHuMessageNewBean.BodyBean.HousingResourcesInformationBean housingResourcesInformation = body.getHousingResourcesInformation();
                        /*
                         *
                         *
                         * 量房资料
                         *
                         */
                        String ca_houseOrientation = measureInformation.getCa_HouseOrientation();
                        liangfang_rhmcx.setText(Html.fromHtml("入户门朝向：" + "<font color='#000000'>" + isEmpty(ca_houseOrientation) + "</font>"));
                        String ca_isGroundSmooth = measureInformation.getCa_isGroundSmooth();
                        liangfang_ydmpz.setText(Html.fromHtml("原地面平整：" + "<font color='#000000'>" + isEmpty(ca_isGroundSmooth) + "</font>"));
                        String ca_cargoDoorWide = measureInformation.getCa_CargoDoorWide();
                        String ca_cargoDoorHight = measureInformation.getCa_CargoDoorHight();
                        liangfang_htmkg.setText(Html.fromHtml("货梯门宽高：" + "<font color='#000000'>" + isEmpty(ca_cargoDoorWide) + "mm*" + isEmpty(ca_cargoDoorHight) + "mm" + "</font>"));
                        String ca_tuyereMinimumHeight = measureInformation.getCa_TuyereMinimumHeight();
                        liangfang_fkzdd.setText(Html.fromHtml("风口最低点：" + "<font color='#000000'>" + isEmpty(ca_tuyereMinimumHeight) + "</font>"));
                        String ca_strongBoxNum = measureInformation.getCa_StrongBoxNum();
                        liangfang_qdx.setText(Html.fromHtml("强电箱：" + "<font color='#000000'>" + isEmpty(ca_strongBoxNum) + "</font>"));
                        String ca_weakBoxNum = measureInformation.getCa_WeakBoxNum();
                        liangfang_rdx.setText(Html.fromHtml("弱电箱：" + "<font color='#000000'>" + isEmpty(ca_weakBoxNum) + "</font>"));
                        String ca_spaceMaxHeight = measureInformation.getCa_SpaceMaxHeight();
                        liangfang_ydmg.setText(Html.fromHtml("原顶面高：" + "<font color='#000000'>" + isEmpty(ca_spaceMaxHeight) + "</font>"));
                        String ca_mainBeamHeight = measureInformation.getCa_mainBeamHeight();
                        liangfang_zlxg.setText(Html.fromHtml("主梁下高：" + "<font color='#000000'>" + isEmpty(ca_mainBeamHeight) + "</font>"));
                        String ca_secondaryHeight = measureInformation.getCa_SecondaryHeight();
                        liangfang_clxg.setText(Html.fromHtml("次梁下高：" + "<font color='#000000'>" + isEmpty(ca_secondaryHeight) + "</font>"));
                        String ca_minimumSprayHeight = measureInformation.getCa_MinimumSprayHeight();
                        liangfang_plzdd.setText(Html.fromHtml("喷淋最低点：" + "<font color='#000000'>" + isEmpty(ca_minimumSprayHeight) + "</font>"));
                        String ca_windowHight = measureInformation.getCa_WindowHight();
                        liangfang_chg.setText(Html.fromHtml("窗户高：" + "<font color='#000000'>" + isEmpty(ca_windowHight) + "</font>"));
                        String ca_windowsillHight = measureInformation.getCa_windowsillHight();
                        liangfang_ctg.setText(Html.fromHtml("窗台高：" + "<font color='#000000'>" + isEmpty(ca_windowsillHight) + "</font>"));
                        String ca_windowType = measureInformation.getCa_WindowType();
                        liangfang_chlx.setText(Html.fromHtml("窗户类型：" + "<font color='#000000'>" + isEmpty(ca_windowType) + "</font>"));
                        String ca_floorHeight = buildingInformation.getCa_FloorHeight();
                        liangfang_cg.setText(Html.fromHtml("层高：" + "<font color='#000000'>" + isEmpty(ca_floorHeight) + "</font>"));
                        String ca_buildingStructure = measureInformation.getCa_BuildingStructure();
                        liangfang_mdgd.setText(Html.fromHtml("门洞高度：" + "<font color='#000000'>" + isEmpty(ca_buildingStructure) + "</font>"));
                        String ca_waterPath = measureInformation.getCa_WaterPath();
                        String water_empty = isEmpty(ca_waterPath);
                        String isWater = "";
                        if (water_empty.equals("1")) {
                            isWater = "是";
                        } else {
                            isWater = "否";
                        }
                        liangfang_sxs.setText(Html.fromHtml("上下水：" + "<font color='#000000'>" + isEmpty(isWater) + "</font>"));
                        String ca_groundElevation = measureInformation.getCa_GroundElevation();
                        liangfang_dmgc.setText(Html.fromHtml("地面高差：" + "<font color='#000000'>" + isEmpty(ca_groundElevation) + "</font>"));
                        /*
                         *
                         *
                         * 物业信息
                         *
                         */
                        String ca_leadName = propertyInformation.getCa_LeadName();
                        wuye_wygs.setText(Html.fromHtml("物业公司：" + "<font color='#000000'>" + isEmpty(ca_leadName) + "</font>"));
                        String ca_twoManagerTel = propertyInformation.getCa_TwoManagerTel();
                        wuye_wydh.setText(Html.fromHtml("物业电话：" + "<font color='#000000'>" + isEmpty(ca_twoManagerTel) + "</font>"));
                        String ca_aptitude = propertyInformation.getCa_Aptitude();
                        wuye_zzyq.setText(Html.fromHtml("资质要求：" + "<font color='#000000'>" + isEmpty(ca_aptitude) + "</font>"));
                        String ca_protectiveMaterial = propertyInformation.getCa_ProtectiveMaterial();
                        wuye_bhcl.setText(Html.fromHtml("保护材料：" + "<font color='#000000'>" + isEmpty(ca_protectiveMaterial) + "</font>"));
                        String ca_elevator = propertyInformation.getCa_Elevator();
                        wuye_dtsy.setText(Html.fromHtml("电梯使用：" + "<font color='#000000'>" + isEmpty(ca_elevator) + "</font>"));
                        String ca_reqConTime = propertyInformation.getCa_ReqConTime();
                        wuye_sgsj.setText(Html.fromHtml("施工时间：" + "<font color='#000000'>" + isEmpty(ca_reqConTime) + "</font>"));
                        String ca_designatedAirCompany = propertyInformation.getCa_DesignatedAirCompany();
                        if (ca_designatedAirCompany.equals("否")) {
                            wuye_ktvbdw.setText(Html.fromHtml("空调维保单位：" + "<font color='#000000'>" + isEmpty(ca_designatedAirCompany) + "</font>"));
                        } else {
                            String ca_airUnitName = propertyInformation.getCa_AirUnitName();
                            wuye_ktvbdw.setText(Html.fromHtml("空调维保单位：" + "<font color='#000000'>" + isEmpty(ca_airUnitName) + "</font>"));
                        }

                        String ca_garbageTransport = propertyInformation.getCa_GarbageTransport();
                        wuye_ljysfs.setText(Html.fromHtml("垃圾运输方式：" + "<font color='#000000'>" + isEmpty(ca_garbageTransport) + "</font>"));
                        String ca_propertyManagerCost = propertyInformation.getCa_PropertyManagerCost();
                        wuye_wyglf.setText(Html.fromHtml("物业管理费：" + "<font color='#000000'>" + isEmpty(ca_propertyManagerCost) + "</font>"));
                        String ca_propertyCost = propertyInformation.getCa_PropertyCost();
                        wuye_wyyj.setText(Html.fromHtml("物业押金：" + "<font color='#000000'>" + isEmpty(ca_propertyCost) + "</font>"));
                        String ca_hydropowerCost = propertyInformation.getCa_HydropowerCost();
                        wuye_sdfy.setText(Html.fromHtml("水电费用：" + "<font color='#000000'>" + isEmpty(ca_hydropowerCost) + "</font>"));
                        String ca_fireProtectionGrade = propertyInformation.getCa_FireProtectionGrade();
                        if (ca_fireProtectionGrade.equals("无需求") || TextUtils.isEmpty(ca_fireProtectionGrade)) {
                            wuye_clfhdj.setText(Html.fromHtml("材料防火等级：" + "<font color='#000000'>" + isEmpty("无需求") + "</font>"));
                        }else{
                            wuye_clfhdj.setText(Html.fromHtml("材料防火等级：" + "<font color='#000000'>" + isEmpty("有需求") + "</font>"));

                        }
                        String ca_insuranceCompany = propertyInformation.getCa_InsuranceCompany();
                        if (ca_insuranceCompany.equals("否")) {
                            wuye_zdbxgs.setText(Html.fromHtml("指定保险公司：" + "<font color='#000000'>" + isEmpty(ca_insuranceCompany) + "</font>"));
                        } else {
                            String ca_insuranceCompanyName = propertyInformation.getCa_InsuranceCompanyName();
                            wuye_zdbxgs.setText(Html.fromHtml("指定保险公司：" + "<font color='#000000'>" + isEmpty(ca_insuranceCompanyName) + "</font>"));

                        }
                        String ca_propertyInsurance = propertyInformation.getCa_PropertyInsurance();
                        wuye_gcyqxyq.setText(Html.fromHtml("工程一切险要求：" + "<font color='#000000'>" + isEmpty(ca_propertyInsurance) + "</font>"));
                        String ca_designatedFireCompany = propertyInformation.getCa_DesignatedFireCompany();
                        if (ca_designatedFireCompany.equals("否")) {
                            wuye_xfwbdw.setText(Html.fromHtml("消防维保单位：" + "<font color='#000000'>" + isEmpty(ca_designatedFireCompany) + "</font>"));
                        } else {
                            String ca_fireUnitName = propertyInformation.getCa_FireUnitName();
                            wuye_xfwbdw.setText(Html.fromHtml("消防维保单位：" + "<font color='#000000'>" + isEmpty(ca_fireUnitName) + "</font>"));
                        }
                        String ca_specialRequirement = propertyInformation.getCa_SpecialRequirement();
                        wuye_wysgyq.setText(Html.fromHtml("物业施工要求：" + "<font color='#000000'>" + isEmpty(ca_specialRequirement) + "</font>"));

                        /*
                         *
                         *
                         * 企业
                         *
                         */
                        String ci_clientName = baseInformation.getCi_ClientName();
                        qiye_qc.setText(Html.fromHtml("全称：" + "<font color='#000000'>" + isEmpty(ci_clientName) + "</font>"));
                        //String ci_type = baseInformation.getCi_Type();
                        String ca_enterpriseNature = companyInformation.getCa_EnterpriseNature();
                        qiye_lx.setText(Html.fromHtml("类型：" + "<font color='#000000'>" + isEmpty(ca_enterpriseNature    ) + "</font>"));
                        String ca_establishedYear = companyInformation.getCa_EstablishedYear();
                        qiye_cl.setText(Html.fromHtml("成立：" + "<font color='#000000'>" + isEmpty(ca_establishedYear) + "</font>"));
                        String ca_industryPositioning = decorateInformation.getCa_IndustryPositioning();
                        qiye_dw.setText(Html.fromHtml("定位：" + "<font color='#000000'>" + isEmpty(ca_industryPositioning) + "</font>"));
                        /*
                         *
                         *
                         * 房屋状况
                         *
                         */
                        String ca_housingType = housingResourcesInformation.getCa_HousingType();
                        fangwu_xz.setText(Html.fromHtml("现状：" + "<font color='#000000'>" + isEmpty(ca_housingType) + "</font>"));
                        String ci_area = housingResourcesInformation.getCi_Area();
                        fangwu_mj.setText(Html.fromHtml("面积：" + "<font color='#000000'>" + isEmpty(ci_area) + "</font>"));
                        String ci_decorationAddress = decorateInformation.getCi_DecorationAddress();
                        fangwu_dz.setText(Html.fromHtml("量房地址：" + "<font color='#000000'>" + isEmpty(ci_decorationAddress) + "</font>"));
                        /*
                         *
                         *
                         * 设计需求
                         *
                         */
                        String ca_fengShuiRequirements = decorateInformation.getCa_FengShuiRequirements();
                        sheji_fsxq.setText(Html.fromHtml("风水需求：" + "<font color='#000000'>" + isEmpty(ca_fengShuiRequirements) + "</font>"));
                        String ca_intentionalStyle = decorateInformation.getCa_IntentionalStyle();
                        sheji_yxfg.setText(Html.fromHtml("意向风格：" + "<font color='#000000'>" + isEmpty(ca_intentionalStyle) + "</font>"));
                        String ca_designFee = decorateInformation.getCa_DesignFee();
                        if (ca_designFee.equals("否") || TextUtils.isEmpty(ca_designFee)) {
                            sheji_jssjf.setText(Html.fromHtml("接受设计费：" + "<font color='#000000'>" + isEmpty("否") + "</font>"));
                        } else {
                            String ca_designFeeScope = decorateInformation.getCa_DesignFeeScope();
                            sheji_sjqf.setText(Html.fromHtml("设计取费：" + "<font color='#000000'>" + isEmpty(ca_designFeeScope) + "</font>"));
                        }
                        /*
                         *
                         *
                         * 施工需求
                         *
                         */
                        String ca_plannedOpeningTime = decorateInformation.getCa_PlannedOpeningTime();
                        shigong_yjkysj.setText(Html.fromHtml("预计开业时间：" + "<font color='#000000'>" + isEmpty(ca_plannedOpeningTime) + "</font>"));
                        String ca_plannedStartTime = decorateInformation.getCa_PlannedStartTime();
                        shigong_jhkgsj.setText(Html.fromHtml("计划开工时间：" + "<font color='#000000'>" + isEmpty(ca_plannedStartTime) + "</font>"));
                        String ca_specificStartTime = decorateInformation.getCa_SpecificStartTime();
                        String sub_time = ca_specificStartTime.substring(0, 10);
                        shigong_jtkgsj.setText(Html.fromHtml("具体开工时间：" + "<font color='#000000'>" + isEmpty(sub_time) + "</font>"));
                        String ca_htWorkCycle = decorateInformation.getCa_HtWorkCycle();
                        shigong_yjgq.setText(Html.fromHtml("预计工期：" + "<font color='#000000'>" + isEmpty(ca_htWorkCycle) + "</font>"));
                        String ca_spaceRequirement = decorateInformation.getCa_SpaceRequirement();
                        shigong_hbyq.setText(Html.fromHtml("环保要求：" + "<font color='#000000'>" + isEmpty(ca_spaceRequirement) + "</font>"));
                        String ca_decBudgetPrice = decorateInformation.getCa_DecBudgetPrice();
                        shigong_zxys.setText(Html.fromHtml("装修预算：" + "<font color='#000000'>" + isEmpty(ca_decBudgetPrice) + "</font>"));
                        String ca_inviteTenders = decorateInformation.getCa_InviteTenders();
                        shigong_zb.setText(Html.fromHtml("招标：" + "<font color='#000000'>" + isEmpty(ca_inviteTenders) + "</font>"));
                        /*
                         *
                         *
                         * 其他需求
                         *
                         */
                        String ca_aim = decorateInformation.getCa_Aim();
                        xvqiu_zxmd.setText(Html.fromHtml("装修目的：" + "<font color='#000000'>" + isEmpty(ca_aim) + "</font>"));
                        String ca_intelligentWeakCurrent = decorateInformation.getCa_IntelligentWeakCurrent();
                        xvqiu_rdxq.setText(Html.fromHtml("弱电需求：" + "<font color='#000000'>" + isEmpty(ca_intelligentWeakCurrent) + "</font>"));
                        String ca_fireDemand = decorateInformation.getCa_FireDemand();
                        xvqiu_xfxq.setText(Html.fromHtml("消防需求：" + "<font color='#000000'>" + isEmpty(ca_fireDemand) + "</font>"));
                        String ca_softFurniture = decorateInformation.getCa_SoftFurniture();
                        xvqiu_jjxq.setText(Html.fromHtml("家具需求：" + "<font color='#000000'>" + isEmpty(ca_softFurniture) + "</font>"));
                        String ca_airConditioningDemand = decorateInformation.getCa_AirConditioningDemand();
                        xvqiu_ktgz.setText(Html.fromHtml("空调改造：" + "<font color='#000000'>" + isEmpty(ca_airConditioningDemand) + "</font>"));
                        String ca_designatedBrand = decorateInformation.getCa_DesignatedBrand();
                        xvqiu_zdpp.setText(Html.fromHtml("指定品牌：" + "<font color='#000000'>" + isEmpty(ca_designatedBrand) + "</font>"));
                        String ca_specificDecoration = decorateInformation.getCa_SpecificDecoration();
                        xvqiu_zxxq.setText(Html.fromHtml("装修需求：" + "<font color='#000000'>" + isEmpty(ca_specificDecoration) + "</font>"));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String isEmpty(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        } else {
            return "";
        }
    }

    private void changLayout(int position) {
        for (int i = 0; i < layoutList.size(); i++) {
            if (i == position) {
                layoutList.get(i).setVisibility(View.VISIBLE);
            } else {
                layoutList.get(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
