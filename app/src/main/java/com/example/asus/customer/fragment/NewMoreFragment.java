package com.example.asus.customer.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.DingDanXiangQingActivity;
import com.example.asus.customer.activity.FaBiaoMessageActivity;
import com.example.asus.customer.activity.FangandetailActivity;
import com.example.asus.customer.activity.KeHuMessageNewActivity;
import com.example.asus.customer.activity.LiangFangQiyeActivity;
import com.example.asus.customer.activity.MyDaiQueRenActivity;
import com.example.asus.customer.activity.MyJinXingZhongActivity;
import com.example.asus.customer.activity.MyNeedActivity;
import com.example.asus.customer.activity.NewBothActivity;
import com.example.asus.customer.activity.NewQiYeActivity;
import com.example.asus.customer.activity.NewShouGaoActivity;
import com.example.asus.customer.activity.ProIMWebActivity;
import com.example.asus.customer.activity.QRZhongJianYeActivity;
import com.example.asus.customer.activity.home.FangAnShiGongActivity;
import com.example.asus.customer.activity.home.KeHuHomeLiangFangXianChangActivity;
import com.example.asus.customer.activity.home.KeHuHomeWebViewActivity;
import com.example.asus.customer.adapter.NewMoreLeftAdapter;
import com.example.asus.customer.adapter.RightAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.JunXingZhongBean;
import com.example.asus.customer.entity.MoreLeftBean;
import com.example.asus.customer.entity.NewBothBean;
import com.example.asus.customer.entity.ObjectDataBean;
import com.example.asus.customer.entity.RightBean;
import com.example.asus.customer.entity.TwoStatusBean;
import com.example.asus.customer.entity.UnreadBean;
import com.example.asus.customer.mvp.contract.KeHuHomeContract;
import com.example.asus.customer.mvp.presenter.KeHuHomePresenter;
import com.example.asus.customer.weight.photoview.PhotoViewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//
// 2019/6/13.
//   ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
//     ┃　　　┃ 神兽保佑　　　　　　　　
//     ┃　　　┃ 代码无BUG！
//     ┃　　　┗━━━┓
//     ┃　　　　　　　┣┓
//     ┃　　　　　　　┏┛
//     ┗┓┓┏━┳┓┏┛
//       ┃┫┫　┃┫┫
//       ┗┻┛　┗┻┛
public class NewMoreFragment extends BaseFragment<KeHuHomePresenter> implements KeHuHomeContract.KeHuHomeView {
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_back1)
    ImageView mIvBack1;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.right_img)
    ImageView right_img;
    @Bind(R.id.iv_message)
    TextView mIvMessage;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.rightTitle)
    TextView mTvRightTitle;
    @Bind(R.id.title_relative)
    RelativeLayout mTitleRelative;
    @Bind(R.id.show_popup)
    LinearLayout mShowPopup;
    @Bind(R.id.left_recycleView)
    RecyclerView mLeftRecycleView;
    @Bind(R.id.right_recycleView)
    RecyclerView mRightRecycleView;
    private NewMoreLeftAdapter left_adapter;
    List<MoreLeftBean.BodyBean> left_list = new ArrayList<>();
    List<RightBean.BodyBean> right_list = new ArrayList<>();
    private MoreLeftBean bean;
    private RightAdapter right_adapter;
    private RightBean rightBean;
    private boolean isFirst = true;
    private String fanganlist;


    @Override
    protected int getFragmentLayout() {
        return R.layout.new_more_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        HashMap<String, String> map = new HashMap<>();
        map.put("rwdId", value);
        mPresenter.getUnreadData(map);//获取未读消息
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initMessage();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        HashMap<String, String> map = new HashMap<>();
        map.put("rwdId", value);
        mPresenter.getUnreadData(map);//获取未读消息

    }

    @Override
    protected void FragmentInitData() {
        //获取屏幕宽度
//        WindowManager m = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        m.getDefaultDisplay().getMetrics(outMetrics);
//
//        //int width = outMetrics.widthPixels - DensityTool.dp2px(getActivity(), 10f) * 2; //乘以2是因为左右两侧的宽度
//        //计算宽高
//        //设置宽度为屏幕宽的百分之七十
//        int width = (int) ((int) outMetrics.widthPixels);
//        ////根据要求要设置成770*482比例的高度
//        int height = (int) (width / 750f * 260);
//
//        //设置图片参数
//        ViewGroup.LayoutParams layoutParams = right_img.getLayoutParams();
//        layoutParams.width = width;
//        layoutParams.height = height;
//        right_img.setLayoutParams(layoutParams);


        mTvTitle.setText("项目");
        mIvBack.setVisibility(View.GONE);
        mIvAdd.setVisibility(View.GONE);
        mIvAdd.setImageResource(R.mipmap.liaotian);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProIMWebActivity.class));
            }
        });
        mIvBack.setVisibility(View.GONE);
        mIvBack1.setVisibility(View.GONE);
        mIvBack1.setImageResource(R.mipmap.scan_pc);
        mIvBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QRZhongJianYeActivity.class));
            }
        });
        LinearLayoutManager left_manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLeftRecycleView.setLayoutManager(left_manager);
        left_adapter = new NewMoreLeftAdapter(getActivity(), left_list);
        mLeftRecycleView.setAdapter(left_adapter);
        left_adapter.setListDataCallBack(new NewMoreLeftAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int group, int child) {

            }

            @Override
            public void setOnFindWorker(final int group, View v) {
                left_adapter.notifyDataSetChanged();
                if (bean.getBody().size() > 0 && bean.getBody() != null) {
                    initRightMessage(bean.getBody().get(group).getOneCode());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Glide.with(getActivity()).load(bean.getBody().get(group).getUrl()).into(right_img);
                            mTvRightTitle.setText(bean.getBody().get(group).getOneName());
                        }
                    });
                }
            }
        });
        mRightRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        right_adapter = new RightAdapter(getActivity(), right_list);
        mRightRecycleView.setAdapter(right_adapter);
        right_adapter.setListDataCallBack(new RightAdapter.ListDataCallBack() {
            @Override
            public void setOnListDataClick(int group, int child) {

            }

            @Override
            public void setOnFindWorker(int group, View v, String title) {
                if (rightBean.getBody().get(group).getOneName().equals("量房")) {
                    //量房-资料
                    if (title.equals("资料")) {
                        //startActivity(new Intent(getActivity(), KeHuHomeLiangFangDataActivity.class).putExtra("title", "量房"));
                        startActivity(new Intent(getActivity(), KeHuMessageNewActivity.class));

                    }
                    //量房-现场
                    if (title.equals("现场")) {
                        startActivity(new Intent(getActivity(), KeHuHomeLiangFangXianChangActivity.class));
                    }
                    //量房-瑞祥
                    if (title.equals("瑞祥")) {
                        //startActivity(new Intent(getActivity(), NewQiYeActivity.class).putExtra("title", "瑞祥"));
                        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
                        startActivity(new Intent(getActivity(), KeHuHomeWebViewActivity.class)
                                //.putExtra("url", "https://zaapp.rxjy.com/Customer/AboutUs?rawId=" + value)
                                .putExtra("url", "https://zarxsj.rxjy.com/mobile-temporary/#/Customer/AboutUs")
                                .putExtra("title", "瑞祥装饰"));
                    }
                    //量房-案例
                    if (title.equals("案例")) {
                        startActivity(new Intent(getActivity(), NewQiYeActivity.class).putExtra("title", "案例"));
                    }
                    if (title.equals("需求")) {
                             ToastUtil.getInstance().toastCentent("请到PC端查看 ");
                    }
                } else {
                    if (title.equals("现场")) {
                        //其他-现场
                        ToastUtil.getInstance().toastCentent("请到PC端查看 ");
                    }
                }
                if(title.equals("设计产品")){
                    ToastUtil.getInstance().toastCentent("请到PC端查看 ");
                }

                //量房，方案-企业
                if (title.equals("企业")) {
                    startActivity(new Intent(getActivity(), LiangFangQiyeActivity.class).putExtra("title", "企业"));
                }
                //方案-手稿
//                if (title.equals("手稿")) {
//                    startActivity(new Intent(getActivity(), NewShouGaoActivity.class).putExtra("type", rightBean.getBody().get(group).getTwoName()));
//
//                }
                //方案-结构图
//                if (title.equals("结构图")) {
                    //st artActivity(new Intent(getActivity(), FangAnJieGouActivity.class).putExtra("type",rightBean.getBody().get(group).getTwoName()));
//                    startActivity(new Intent(getActivity(), NewJieGouActivity.class).putExtra("type", rightBean.getBody().get(group).getTwoName()));
//                    startActivity(new Intent(getActivity(), FangAnShiGongActivity.class)
                    startActivity(new Intent(getActivity(), FangandetailActivity.class)
                            .putExtra("title", title)
                            .putExtra("fanganlist",fanganlist)
                            .putExtra("item",group)
                    );
//                }
                //方案-意向，元素，现在暂无
                if (title.equals("意向图") || title.equals("元素图")) {
                    //startActivity(new Intent(getActivity(), NewYiXiangActivity.class).putExtra("type", rightBean.getBody().get(group).getTwoName()));
                    ToastUtil.getInstance().toastCentent("暂未开启");
                }
                //方案-效果，施工，水电
//                if (title.equals("效果图") || title.equals("施工图") || title.equals("水电图")) {
//                    //startActivity(new Intent(getActivity(), NewBothActivity.class).putExtra("typeCode",rightBean.getBody().get(group).getTwoCode()));
////                    if (title.equals("效果图")) {
////                        getTuzhi("xgt", title);
////                    } else if (title.equals("施工图")) {
////                        getTuzhi("sgt", title);
////                    } else if (title.equals("水电图")) {
////                        getTuzhi("sdt", title);
////                    }
//                    startActivity(new Intent(getActivity(), FangAnShiGongActivity.class)
//                            .putExtra("title", title));
//                }
                //方案-手绘，鸟瞰，平面，彩平
//                if (title.equals("手绘图") || title.equals("鸟瞰图") || title.equals("平面图") || title.equals("彩平图")) {
////                    initStatus(group, title);
//                    startActivity(new Intent(getActivity(), FangAnShiGongActivity.class)
//                            .putExtra("title", title));
//                }
                //预算-材料，报价
                if (rightBean.getBody().get(group).getOneName().equals("预算")) {
                    if (title.equals("报价")) {
                        ToastUtil.getInstance().toastCentent("请到PC端查看");
                    }
                    if (title.equals("材料") || title.equals("工艺")) {
                        ToastUtil.getInstance().toastCentent("暂未开启");
                    }
                } else {
                    //其他的材料
                    if (title.equals("材料") || title.equals("工艺")) {
                        ToastUtil.getInstance().toastCentent("请到PC端查看");
                    }
                }
                //签约-施工
                if (title.equals("施工")) {
//                    final String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
//                    startActivity(new Intent(getActivity(), KeHuHomeWebViewActivity.class)
//                            .putExtra("url", ApiEngine.ZAAPP_BASE + "Customer/contractTemplate?rwdId=" + value)
//                            .putExtra("title", "合同模板"));
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
                //签约-设计
                if (title.equals("设计")) {
//                    startActivity(new Intent(getActivity(), FangAnXieyiActivity.class));
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
                if (title.equals("人员")) {
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
                if (title.equals("摄像头")) {
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
                if (rightBean.getBody().get(group).getOneName().equals("准备")) {
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
                if (rightBean.getBody().get(group).getOneName().equals("在施")) {
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
                if (rightBean.getBody().get(group).getOneName().equals("竣工")) {
                    ToastUtil.getInstance().toastCentent("请到PC端查看");
                }
            }
        });
        initMessage();
    }

    //获取图纸数据
    private void getTuzhi(final String twoCode, final String title) {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdid", value);
        //12-202079  sgt
        if (rightBean.getBody().size() > 0 && rightBean.getBody() != null) {
            map.put("typeCode", twoCode);
        }


        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getTuZhi", map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                dismissLoading();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("app/carouselpic/list",e.toString());
                        //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getTuZhi?rwdid=105-351&typeCode=sgt}
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NewBothBean bean = JSONUtils.toObject(string, NewBothBean.class);
                        //startActivity(new Intent(getActivity(), MyNeedActivity.class).putExtra("type", rightBean.getBody().get(i).getTwoName()));
                        if (bean.getBody().size() > 0 && bean.getBody() != null) {
                            //订单列表
                            startActivity(new Intent(getActivity(), NewBothActivity.class).putExtra("typeCode", twoCode));
                        } else {
                            //我需要
                            startActivity(new Intent(getActivity(), MyNeedActivity.class).putExtra("type", twoCode).putExtra("title", title));
                        }
                    }
                });
            }
        });
    }

    //二级导航查询对应的状态
    private void initStatus(final int i, final String title) {
        showLoading();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdid", value);
        if (rightBean.getBody().size() > 0 && rightBean.getBody() != null) {
            //对参数进行编码
            String version = rightBean.getBody().get(i).getTwoCode();
            //对中文参数进行编码
            //version = URLEncoder.encode(version);
            map.put("type_code", version);
            if (title.equals("彩平图")) {
                map.put("CatalogId", "90");
            } else if (title.equals("结构图")) {
                map.put("CatalogId", "57");
            } else {
                map.put("CatalogId", "80");
            }
        }

        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/feedback/getDemand", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
//                ToastUtil.getInstance().toastCentent(e.toString());
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getDemand?rwdId=12-202079&CatalogId=57&type_code=sht}
                Log.e("/feedback/getDemand",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(string);
                            JSONObject body = object.getJSONObject("Body");
                            int stage = body.getInt("stage");
                            TwoStatusBean bean = null;
                            JunXingZhongBean bean1 = null;
                            if (stage == 2) {
                                bean1 = JSONUtils.toObject(string, JunXingZhongBean.class);
                            } else {
                                bean = JSONUtils.toObject(string, TwoStatusBean.class);
                            }

                            if (stage == 1) {
                                //我需要
                                startActivity(new Intent(getActivity(), MyNeedActivity.class)
                                        .putExtra("type", rightBean.getBody().get(i).getTwoCode())
                                        .putExtra("title", title)
                                );
                            } else if (stage == 2) {
                                //进行中
                                long l = System.currentTimeMillis();

                                startActivity(new Intent(getActivity(), MyJinXingZhongActivity.class)
                                        .putExtra("time", bean1.getBody().getData().getExpectTime() + "")
                                        .putExtra("title", title)
                                );
                            } else if (stage == 3) {
                                //待确认
                                List<TwoStatusBean.BodyBean.DataBean> data = bean.getBody().getData();
                                startActivity(new Intent(getActivity(), MyDaiQueRenActivity.class)
                                        .putExtra("orderNo", "666")
                                        .putExtra("stage", "2")
                                        .putExtra("orderno", data.get(0).getRwdId())
                                        .putExtra("type", rightBean.getBody().get(i).getTwoCode())
                                        .putExtra("typeName", rightBean.getBody().get(i).getTwoName())
                                        .putExtra("imgUrl", data.get(0).getImageUrl())
                                        .putExtra("creatTime", data.get(0).getCreateTime())
                                        .putExtra("title", title)
                                        .putExtra("time", data.get(0).getCreateTime())
                                );
                            } else if (stage == 4) {
                                //待评价
                                String typeCode = "";
//                                if (title.equals("彩平图")) {
//                                    typeCode = "cpt";
//                                }
//                                else
                                    if (title.equals("结构图")) {
                                    typeCode = "jgt";
                                } else if (title.equals("鸟瞰图")) {
                                    typeCode = "nkt";
                                } else if (title.equals("手绘图")) {
                                    typeCode = "sht";
                                }
                                //List<TwoStatusBean.BodyBean.DataBean> data = bean.getBody().getData();
                                startActivity(new Intent(getActivity(), FaBiaoMessageActivity.class)
                                        .putExtra("type", title)
                                        .putExtra("typeCode", typeCode)
                                );
                            } else if (stage == 5) {
                                //完成
                                startActivity(new Intent(getActivity(), DingDanXiangQingActivity.class)
                                        .putExtra("type", title));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private void initRightMessage(String str) {
        //right_list.clear();
        showLoading();
        Map<String, Object> map = new HashMap<>();
        map.put("code", str);
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/mainCase/getTreeTwo", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
//                ToastUtil.getInstance().toastCentent(e.toString());
                Log.e("/mainCase/getTreeTwo",e.toString());
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/mainCase/getTreeTwo?code=lf}
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/mainCase/getTreeTwo?code=lf}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                fanganlist = response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rightBean = JSONUtils.toObject(fanganlist, RightBean.class);
                        List<RightBean.BodyBean> body = rightBean.getBody();
                        right_list.clear();
                        right_list.addAll(body);
                        right_adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initMessage() {
        //left_list.clear();
        showLoading();
        //请求左侧列表
        Map<String, Object> map = new HashMap<>();
        OkhttpUtils.doGet(ApiEngine.GZS_HOST + "/mainCase/getTreeOne", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bean = JSONUtils.toObject(string, MoreLeftBean.class);
                        List<MoreLeftBean.BodyBean> body = bean.getBody();

                        left_list.clear();
                        left_list.addAll(body);
                        left_adapter.notifyDataSetChanged();
                        if (isFirst) {
                            initRightMessage(body.get(0).getOneCode());
                        }
                        isFirst = false;
//                        Glide.with(getActivity()).load(bean.getBody().get(0).getUrl()).into(right_img);
                        mTvRightTitle.setText(bean.getBody().get(0).getOneName());
                    }
                });
            }
        });
    }

    @Override
    protected KeHuHomePresenter onCreatePresenter() {
        return new KeHuHomePresenter(this);
    }

    @Override
    public void getObjectData(ObjectDataBean objectDataBean) {
        ObjectDataBean.BodyBean body = objectDataBean.getBody();
        String ci_clientName = body.getCi_ClientName();
        String ci_designerCard = body.getCi_DesignerCard();
        String ci_designerName = body.getCi_DesignerName();
        int ci_stage = body.getCi_Stage();
        PrefUtils.putValue(getActivity(), Constants.ci_stage, ci_stage + "");
        PrefUtils.putValue(getActivity(), Constants.ci_designerName, ci_designerName);
        PrefUtils.putValue(getActivity(), Constants.ci_designerCard, ci_designerCard);
        PrefUtils.putValue(getActivity(), Constants.ci_clientName, ci_clientName);
        PrefUtils.putValue(getActivity(), Constants.designerPhone, body.getDesignerPhone());
    }

    @Override
    public void getObjectDataErro(String erro) {

    }

    @Override
    public void getUnreadData(UnreadBean unreadBean) {
        String str = JSONUtils.toString(unreadBean);
        PrefUtils.putValue(getActivity(), Constants.UnreadData, str);
    }

    @Override
    public void getUnreadDataErro(String erro) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
