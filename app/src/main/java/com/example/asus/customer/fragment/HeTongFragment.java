package com.example.asus.customer.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.asus.customer.R;
import com.example.asus.customer.activity.QrLoginActivity;
import com.example.asus.customer.commons.App;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.entity.FindInfo;
import com.example.asus.customer.entity.FindInformationBean;
import com.example.asus.customer.entity.QRresultBean;
import com.example.asus.customer.mvp.contract.FindContract;
import com.example.asus.customer.mvp.presenter.FindPresenter;
import com.example.asus.customer.weight.QRScannerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HeTongFragment extends BaseFragment<FindPresenter> implements FindContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.bili_tv)
    TextView biliTv;
    @Bind(R.id.data_tv)
    TextView dataTv;
    @Bind(R.id.day_et)
    EditText dayEt;
    @Bind(R.id.shigongday_tv)
    TextView shigongdayTv;
    @Bind(R.id.v_pname)
    TextView vPname;
    @Bind(R.id.place_tv)
    TextView placeTv;
    @Bind(R.id.lantu_tv)
    TextView lantuTv;
    @Bind(R.id.shenhe_et)
    EditText shenheEt;
    @Bind(R.id.v_area)
    TextView vArea;
    @Bind(R.id.guanlimoney_et)
    EditText guanlimoneyEt;
    @Bind(R.id.yajinmoney_et)
    EditText yajinmoneyEt;
    @Bind(R.id.watermoney_et)
    EditText watermoneyEt;
    @Bind(R.id.xiemoney_et)
    EditText xiemoneyEt;
    @Bind(R.id.kongtiao_tv)
    TextView kongtiaoTv;
    @Bind(R.id.xiaofang_tv)
    TextView xiaofangTv;
    @Bind(R.id.xianmoney_et)
    EditText xianmoneyEt;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.iv_back)
    TextView ivBack;
    private String ci_clientName;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_he_tong;
    }

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("项目-合同");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeScan();
            }
        });
        bililist = new ArrayList<>();//比例
        bililist.add("6/3/1");
        bililist.add("5/4/1");
        bililist.add("4/5/4");
        bililist.add("4/3/2/1");
        bililist.add("3/3/3/1");
        shigonglist = new ArrayList<>();//施工时间
        shigonglist.add("白天");
        shigonglist.add("晚上");
        shigonglist.add("均可");
        lantulist = new ArrayList<>();//蓝图
        lantulist.add("蓝图");
        lantulist.add("白图");
        kongtiaolist = new ArrayList<>();//空调
        kongtiaolist.add("是");
        kongtiaolist.add("否");
        xiaofanglist = new ArrayList<>();//消防
        xiaofanglist.add("是");
        xiaofanglist.add("否");
        placelist = new ArrayList<>();//区域保护
        placelist.add("是");
        placelist.add("否");

        mPresenter.getProgssData(App.orderNo);
    }

    //    添加数据
    ArrayList<String> bililist, shigonglist, lantulist, kongtiaolist, xiaofanglist, placelist;

    String hetongtime;
    private OptionsPickerView biliPV, shigongPV, lantuPV, kongtiaoPV, xiaofangPV, placePV;
    int bili = 0, shigong = 0, lantu = 0, kongtiao = 0, xiaofang = 0, place = 0;

    //监听
    @OnClick({R.id.iv_back, R.id.data_tv, R.id.bili_tv, R.id.shigongday_tv, R.id.lantu_tv, R.id.kongtiao_tv, R.id.xiaofang_tv, R.id.place_tv, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //比例
            case R.id.bili_tv:
                biliPV = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        biliTv.setText(bililist.get(options1));
                        biliTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        bili = options1;
                    }
                }).build();
                biliPV.setPicker(bililist);
                biliPV.show();
                break;
            //返回按钮
            case R.id.iv_back:

                break;
            //合同签订时间
            case R.id.data_tv:
                TimePickerView timePickerView = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String lhousetime = getDateToString(date, "yyyy/MM/dd");
                        dataTv.setText(lhousetime);
                        dataTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        hetongtime = lhousetime;

                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")
                        .setSubmitText("确定")
                        .setContentSize(16)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setRange(1900, Calendar.YEAR)
                        .isCyclic(true)//是否循环滚动
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                timePickerView.show();
                break;
            //施工时间
            case R.id.shigongday_tv:
                shigongPV = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        shigongdayTv.setText(shigonglist.get(options1));
                        shigongdayTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        shigong = options1;
                    }
                }).build();
                shigongPV.setPicker(shigonglist);
                shigongPV.show();
                break;
            //蓝图
            case R.id.lantu_tv:
                lantuPV = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        lantuTv.setText(lantulist.get(options1));
                        lantuTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        lantu = options1;
                    }
                }).build();
                lantuPV.setPicker(lantulist);
                lantuPV.show();
                break;
            //空调
            case R.id.kongtiao_tv:
                kongtiaoPV = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        kongtiaoTv.setText(kongtiaolist.get(options1));
                        kongtiaoTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        kongtiao = options1;
                    }
                }).build();
                kongtiaoPV.setPicker(kongtiaolist);
                kongtiaoPV.show();
                break;
            //消防
            case R.id.xiaofang_tv:
                xiaofangPV = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        xiaofangTv.setText(xiaofanglist.get(options1));
                        xiaofangTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        xiaofang = options1;
                    }
                }).build();
                xiaofangPV.setPicker(xiaofanglist);
                xiaofangPV.show();
                break;
            //区域保护
            case R.id.place_tv:
                placePV = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        placeTv.setText(placelist.get(options1));
                        placeTv.setTextColor(HeTongFragment.this.getResources().getColor(R.color.colorBlackLight));
                        place = options1;
                    }
                }).build();
                placePV.setPicker(placelist);
                placePV.show();
                break;
            //提交
            case R.id.tv_submit:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("caHtPayProportion", biliTv.getText().toString());
                    jsonObject.put("caHtSignDate", dataTv.getText().toString());
                    jsonObject.put("caHtWorkCycle", dayEt.getText().toString());
                    jsonObject.put("caReqConTime", shigongdayTv.getText().toString());
                    jsonObject.put("caProductProtection", placeTv.getText().toString());
                    jsonObject.put("caBlueprint", lantuTv.getText().toString());
                    //ca_HtAuditingCycle
                    jsonObject.put("caHtAuditingCycle", shenheEt.getText().toString());
                    //ca_HtManagePrice
                    jsonObject.put("caHtManagePrice", guanlimoneyEt.getText().toString());
                    //ca_HtDeposit
                    jsonObject.put("caHtDeposit", yajinmoneyEt.getText().toString());
                    //ca_HtHydropowerPrice
                    jsonObject.put("ca_HtHydropowerPrice", watermoneyEt.getText().toString());
                    //ca_HtBlowdownPrice
                    jsonObject.put("caHtBlowdownPrice", xiemoneyEt.getText().toString());


                    //ca_DesignatedAirCompany
                    String s1 = kongtiaoTv.getText().toString();
                    if (s1.equals("是")) {
                        jsonObject.put("caDesignatedAirCompany", 1);
                    } else if (s1.equals("否")) {
                        jsonObject.put("caDesignatedAirCompany", 2);
                    }
                    String s = xiaofangTv.getText().toString();

                    if (s.equals("是")) {
                        jsonObject.put("caDesignatedFireCompany", 1);
                    } else if (s.equals("否")) {
                        jsonObject.put("caDesignatedFireCompany", 2);
                    }
                    //ca_HtRiskPrice
                    jsonObject.put("caHtRiskPrice", xianmoneyEt.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresenter.getContractData(App.orderNo, jsonObject.toString());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getProgssData(App.orderNo);
    }

    public static String getDateToString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    @Override
    protected FindPresenter onCreatePresenter() {
        return new FindPresenter(this);
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
    public void getProgssData(FindInfo.BodyBean bodyBean) {

        App.projectName=bodyBean.getBaseInformation().getCi_ClientName();
        FindInfo.BodyBean.ContractInfomationPojoBean contractInfomationPojo = bodyBean.getContractInfomationPojo();
        //  //付款比例
        String ca_htPayProportion = contractInfomationPojo.getCa_HtPayProportion();
        biliTv.setText(ca_htPayProportion);
        //合同签订
        String ca_htSignDate = contractInfomationPojo.getCa_HtSignDate();

        dataTv.setText(ca_htSignDate);
        //工期
        String ca_htWorkCycle = contractInfomationPojo.getCa_HtWorkCycle();
        dayEt.setText(ca_htWorkCycle);
        //施工时间
        FindInfo.BodyBean.PropertyInformationBean propertyInformation = bodyBean.getPropertyInformation();
        //ca_ReqConTime
        String ca_reqConTime = propertyInformation.getCa_ReqConTime();
        shigongdayTv.setText(ca_reqConTime);
        //区域保护 ca_ProductProtection
        String ca_productProtection = propertyInformation.getCa_ProductProtection();

        placeTv.setText(ca_productProtection);
        //报审蓝图
        String ca_blueprint = propertyInformation.getCa_Blueprint();

        lantuTv.setText(ca_blueprint);
        //ca_HtAuditingCycle

        // 周期审核 shenheEt  ca_HtAuditingCycle
        String ca_htAuditingCycle = propertyInformation.getCa_HtAuditingCycle();
        shenheEt.setText(ca_htAuditingCycle);
        //管理费
        //guanlimoneyEt ca_HtManagePrice
        String ca_htManagePrice = propertyInformation.getCa_HtManagePrice();
        guanlimoneyEt.setText(ca_htManagePrice);
        //押金  ca_HtDeposit
        //
        String ca_htDeposit = propertyInformation.getCa_HtDeposit();
        yajinmoneyEt.setText(ca_htDeposit);
        //水电费 ca_HtHydropowerPrice
        String ca_htHydropowerPrice = propertyInformation.getCa_HtHydropowerPrice();
        watermoneyEt.setText(ca_htHydropowerPrice);
        //泄水费 xiemoneyEt

        String ca_htBlowdownPrice = propertyInformation.getCa_HtBlowdownPrice();
        xiemoneyEt.setText(ca_htBlowdownPrice);
        //空调 //ca_DesignatedAirCompany
        String ca_designatedAirCompany = propertyInformation.getCa_DesignatedAirCompany();
        if (ca_designatedAirCompany.equals("1")) {
            kongtiaoTv.setText("是");
        } else if (ca_designatedAirCompany.equals("2")) {
            kongtiaoTv.setText("否");
        }

        //消防
        String ca_designatedFireCompany = propertyInformation.getCa_DesignatedFireCompany();
        if (ca_designatedFireCompany.equals("1")) {
            xiaofangTv.setText("是");
        } else if (ca_designatedFireCompany.equals("2")) {
            xiaofangTv.setText("否");
        }
        //工程一切险
        String ca_htRiskPrice = propertyInformation.getCa_HtRiskPrice();
        xianmoneyEt.setText(ca_htRiskPrice);
    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void getfindData(List<FindInformationBean.BodyBean> mlist) {

    }

    @Override
    public void getUpDataProject() {
        mPresenter.getProgssData(App.orderNo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    /**
     * 扫描二维码
     */
    private static final int REQ_CODE_PERMISSION = 0x1111;

    private void QRCodeScan() {//6.0以上的手机需要处理权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
        } else {
            // Have gotten the permission
            startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data != null) {
                            String result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                            QRresultBean info = JSONUtils.toObject(result, QRresultBean.class);
                            String biaoshi = info.getParameter().getLogin_id();
                            if (biaoshi != null) {
                                startActivity(new Intent(getActivity(), QrLoginActivity.class).putExtra("appid", biaoshi));
                            } else {
                                showToast("请扫描正确的二维码！");
                            }
                        }
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            Log.e("RESULT_CANCELED=====", data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
                break;
        }
    }
}
