package com.example.asus.customer.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.FaBiaoMessageActivity;
import com.example.asus.customer.activity.NewDingDanXiangQingActivity;
import com.example.asus.customer.adapter.MyDingDanAdapter;
import com.example.asus.customer.api.ApiEngine;
import com.example.asus.customer.commons.Constants;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;
import com.example.asus.customer.commons.utils.JSONUtils;
import com.example.asus.customer.commons.utils.OkhttpUtils;
import com.example.asus.customer.commons.utils.PrefUtils;
import com.example.asus.customer.commons.utils.ToastUtil;
import com.example.asus.customer.entity.QuanBuBean;

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
// 2019/6/12.
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
public class DaiPeiSong_Fragment extends BaseFragment {
    @Bind(R.id.listView)
    ListView mListView;
    @Bind(R.id.no_img)
    ImageView no_img;
    List<QuanBuBean.BodyBean> list = new ArrayList<>();
    private MyDingDanAdapter adapter;
    private QuanBuBean bean;
    @Override
    protected int getFragmentLayout() {
        return R.layout.daipingjia_fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initMessage();
    }

    @Override
    public void onResume() {
        super.onResume();
        initMessage();
    }

    @Override
    protected void FragmentInitData() {
        adapter = new MyDingDanAdapter(getActivity(), list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (bean != null && bean.getBody() != null) {
//                    QuanBuBean.BodyBean body = bean.getBody().get(position);
//                    int stage = body.getStage();
//                    startActivity(new Intent(getActivity(), FaBiaoMessageActivity.class)
//                            .putExtra("orderno", body.getOrderNo())
//                            .putExtra("type", body.getType())
//                            .putExtra("typeCode", body.getType_code()));
//                }
                startActivity(new Intent(getActivity(), NewDingDanXiangQingActivity.class)
                        .putExtra("orderNo",list.get(position).getOrderNo())
                        .putExtra("statusName",list.get(position).getStatusName())
                        .putExtra("type",list.get(position).getDrwingType()+"")
                        .putExtra("imageName",list.get(position).getImgName()+"")
                        .putExtra("groupId",list.get(position).getGroupId())
                        .putExtra("rwdId",list.get(position).getRwdId())
                );
            }
        });
        //initMessage();
    }

    private void initMessage() {
        showLoading();
        list.clear();
        Map<String, Object> map = new HashMap<>();
        String value = PrefUtils.getValue(getActivity(), Constants.PN_Onumber);
        map.put("rwdId", value);
        map.put("status", "2");
        OkhttpUtils.doGet(ApiEngine.ZHA_HOST + "DesignInstitute/contract/getPlanCollection", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissLoading();
                ToastUtil.getInstance().toastCentent(e.toString());
                no_img.setVisibility(View.VISIBLE);
                //Response{protocol=h2, code=200, message=, url=https://khdapi.wenes.cn/feedback/getByRwdIdOrderList?rwdId=19-225080&stage=0}
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissLoading();
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bean = JSONUtils.toObject(string, QuanBuBean.class);
                        List<QuanBuBean.BodyBean> body = bean.getBody();
                        if (body.size() > 0 && body != null) {
                            list.addAll(body);
                            adapter.notifyDataSetChanged();
                            no_img.setVisibility(View.GONE);
                        } else {
                            no_img.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }
}
