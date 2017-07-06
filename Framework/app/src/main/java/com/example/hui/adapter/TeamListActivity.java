package com.example.hui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hui.adapter.xlistview.XListView;
import com.example.hui.http.HttpUtils;
import com.example.hui.http.TeamListResult;
import com.example.hui.http.callback.CommonCallBack;
import com.example.hui.ioc.R;
import com.example.hui.ioc.view.ViewById;
import com.example.hui.loading.BaseResult;
import com.example.hui.loading.ShowLoadingView;
import com.example.hui.template.BaseActivity;
import com.example.hui.titlebar.TitleBar;
import com.example.hui.titlebar.navigation.DefaultNavigationBar;
import com.example.hui.titlebar.navigation.OtherNavigationBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.hui.titlebar.navigation.DefaultNavigationBar.*;

/**
 * Created by 曾辉 on 2016/9/8.
 * Email : 240336124@qq.com
 * Description :
 */
public class TeamListActivity extends BaseActivity implements XListView.IXListViewListener {
    @ViewById(R.id.x_list_view)
    private XListView mListView;

    private int mPage = 1;
    private int mPageSize = 8;


    private ArrayList<TeamListResult.Team> mTeams;// 用来累加每一页返回的数据

    private CommonTeamListAdapter mListAdapter;

    @Override
    @ShowLoadingView
    protected void initData() {
        // 请求列表数据
        mTeams = new ArrayList<>();
        requestTeamList();
    }

    /**
     * 请求列表数据
     */
    private void requestTeamList() {
        Map<String, String> params = new HashMap<>();
        params.put("page", mPage + "");
        params.put("pagesize", mPageSize + "");
        HttpUtils.getInstance().post(params, "http://v2.ffu365.com/index.php?m=Api&c=Team&a=teamList",
                new CommonCallBack<TeamListResult>() {
                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onSccuess(TeamListResult listResult) {
                        if (isNetRequestOk(listResult)) {
                            // 服务器数据返回成功
                            showListData(listResult.data);
                        }


                    }
                });
    }

    /**
     * 请求数据成功之后显示列表数据
     */
    private void showListData(TeamListResult.TeamListData data) {
        // mListView.setAdapter(new TeamListAdapter(this,data.list));
        //  1 2  3  24  1
        if (mPage == 1) {
            mTeams.clear();
        }
        mTeams.addAll(data.list);

        // mListView.setAdapter(new TeamListAdapter(this,mTeams));

        if (mListAdapter == null) {
            mListAdapter = new CommonTeamListAdapter(this, mTeams);
            mListView.setAdapter(mListAdapter);
        } else {
            // 通知ListView 数据改变了 只加载改变的数据
            mListAdapter.notifyDataSetChanged();
        }
        // 停止ListView 的刷新状态
        mListView.onLoad();
    }

    @Override
    protected void initView() {
        mListView.setXListViewListener(this);
    }

    @Override
    protected void initTitle() {
        // titleBar.setTitleTv("团队列表");
        //  mTitleBar.setRightTv("使用帮助");
        OtherNavigationBar.Builder builder = new OtherNavigationBar.Builder(this,
                (ViewGroup) ((ViewGroup) getWindow().getDecorView()).getChildAt(0));
        builder.setTitle("团队列表").create();
    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_team_list);
    }

    @Override
    public void onRefresh() {
        // 下拉刷新回调
        mPage = 1;
        requestTeamList();
    }

    @Override
    public void onLoadMore() {
        // 上拉加载回调
        mPage += 1;
        requestTeamList();
    }
}
