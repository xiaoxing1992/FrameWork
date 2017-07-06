package com.example.hui.dialog;

import com.example.hui.adapter.xlistview.XListView;
import com.example.hui.dialog.mode.CompanyListResult;
import com.example.hui.http.HttpUtils;
import com.example.hui.http.callback.CommonCallBack;
import com.example.hui.ioc.R;
import com.example.hui.ioc.view.ViewById;
import com.example.hui.template.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曾辉 on 2016/9/12.
 * Email : 240336124@qq.com
 * Description :
 */
public class CompanyListActivity extends BaseActivity implements XListView.IXListViewListener {

    @ViewById(R.id.x_list_view)
    private XListView mListView;

    private int mPage = 1;

    private List<CompanyListResult.DataBean.CompanyBean> mCompanyList;

    private CompanyListAdapter mCompanyAdapter;

    @Override
    protected void initData() {
        mCompanyList = new ArrayList<>();
        // 请求数据
        requestCompanyList();
    }

    private void requestCompanyList() {
        Map<String,String>  params = new HashMap<>();
        params.put("pageNumber",mPage+"");
        params.put("location","长沙");

        HttpUtils.getInstance().post(params, "http://114.55.5.115:8030/varyjia-soa/ws/rest/company/companyHome",
                new CommonCallBack<CompanyListResult>() {
                    @Override
                    public void onFail() {
                        mListView.onLoad();
                    }

                    @Override
                    public void onSccuess(CompanyListResult listResult) {

                        // 显示列表数据

                        // 1.新建Adapter
                        showCompanyList(listResult.getData().getResult());


                        mListView.onLoad();
                    }
                });
    }

    /**
     * 显示公司列表数据
     */
    private void showCompanyList(List<CompanyListResult.DataBean.CompanyBean> list) {
        // 清空逻辑
        if(mPage == 1){
            mCompanyList.clear();
        }

        mCompanyList.addAll(list);

        if(mCompanyAdapter == null){
            mCompanyAdapter = new CompanyListAdapter(this,mCompanyList);
            mListView.setAdapter(mCompanyAdapter);
        }else{
            mCompanyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initView() {
        // 设置上拉刷新下拉加载
        mListView.setXListViewListener(this);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_company_list);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        requestCompanyList();
    }

    @Override
    public void onLoadMore() {
        mPage += 1;
        requestCompanyList();
    }
}
