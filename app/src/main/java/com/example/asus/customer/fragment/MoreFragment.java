package com.example.asus.customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.customer.R;
import com.example.asus.customer.activity.FindActivity;
import com.example.asus.customer.activity.MoreBannerActivity;
import com.example.asus.customer.activity.SuggestActivity;
import com.example.asus.customer.activity.WebActivity;
import com.example.asus.customer.commons.base.BaseFragment;
import com.example.asus.customer.commons.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asus on 2018/5/26.
 */

public class MoreFragment extends BaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.auspic_more)
    ImageView auspicMore;
    @Bind(R.id.Designer_more)
    LinearLayout DesignerMore;
    @Bind(R.id.auspic)
    LinearLayout auspic;
    @Bind(R.id.complaint_more)
    LinearLayout complaintMore;
    @Bind(R.id.scene_more)
    LinearLayout sceneMore;
    @Bind(R.id.element_more)
    LinearLayout elementMore;
    @Bind(R.id.programme_more)
    LinearLayout programmeMore;
    @Bind(R.id.budget_more)
    LinearLayout budgetMore;
    @Bind(R.id.cooperation_more)
    LinearLayout cooperationMore;
    @Bind(R.id.material_more)
    LinearLayout materialMore;
    @Bind(R.id.technology_more)
    LinearLayout technologyMore;
    @Bind(R.id.construction_more)
    LinearLayout constructionMore;

    @Override
    protected int getFragmentLayout() {
        return R.layout.more_fragment;
}

    @Override
    protected void FragmentInitData() {
        tvTitle.setText("瑞祥装饰");
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_title, R.id.auspic_more, R.id.Designer_more, R.id.auspic, R.id.complaint_more, R.id.scene_more, R.id.element_more, R.id.programme_more, R.id.budget_more, R.id.cooperation_more, R.id.material_more, R.id.technology_more, R.id.construction_more,R.id.case_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.auspic_more:
                startActivity(new Intent(getActivity(), MoreBannerActivity.class));
                break;
            case R.id.Designer_more:
               // startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","设计师"));
                break;
            case R.id.auspic:
                startActivity(new Intent(getActivity(), MoreBannerActivity.class));
                break;
            case R.id.complaint_more:
               startActivity(new Intent(getActivity(), SuggestActivity.class));
                break;
            case R.id.scene_more:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","现场"));
                break;
            case R.id.element_more:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","元素"));
                break;
            case R.id.programme_more:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","方案"));
                break;
            case R.id.budget_more:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","预算"));
                break;
            case R.id.cooperation_more:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","合作"));
                break;
            case R.id.material_more:
                //startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","设计师"));
                break;
            case R.id.technology_more:
               // startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","设计师"));
                break;
            case R.id.construction_more:
              //  startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","设计师"));
                break;

            case R.id.case_more:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("title","案例"));
                break;
        }
    }
}
