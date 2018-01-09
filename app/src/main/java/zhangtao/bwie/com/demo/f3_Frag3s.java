package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import Bean.FuliBean;
import ImmerSionUtil.ImmersionUtil;
import MyAdapters_List.Myadapter_xrecy4;
import Presenter.Data_Pre2;
import Views.IView2;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by ZhangTAO on 2017/12/17.
 */

public class f3_Frag3s extends SwipeBackActivity implements IView2,View.OnClickListener{
    private PullToRefreshLayout pull4;
    private RecyclerView recy3;
    private int pager = 1;
    private FloatingActionButton girdbtn;
    private FloatingActionButton listbtn;
    private FloatingActionButton stragglebtn;
    private FloatingActionsMenu plus;
    private int colorzt;
    private ArrayList<String> urllist = new ArrayList<>();
    private Myadapter_xrecy4 myadapter_xrecy4;
    private Data_Pre2 data_pre2;
    private List<FuliBean.ResultsBean> results;
    private List<FuliBean.ResultsBean> results2 = new ArrayList<>();
    private ImageView fuli_back;
    private String baseurl = "";
    private SharedPreferences fuli_share;
    private int colors = R.color.red_img;
    private RelativeLayout tools;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuli_act);
        fuli_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        Fresco.initialize(this);
        initView();
        int color = fuli_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        setPresenters(baseurl,pager);
        setOnClick();
        lefthua();
        pull4.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myadapter_xrecy4.notifyDataSetChanged();
                        pull4.finishRefresh();
                    }
                },1500);
            }
            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pager+=1;
                        setPresenters(baseurl,pager);
                        pull4.finishLoadMore();
                    }
                },2000);
            }
        });
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private void initView() {
        tools = (RelativeLayout) findViewById(R.id.toolbar_zt);
        pull4 = (PullToRefreshLayout) findViewById(R.id.pull_layout_pic4);
        recy3 = (RecyclerView) findViewById(R.id.recy3);
        girdbtn = (FloatingActionButton) findViewById(R.id.action_grid);
        listbtn = (FloatingActionButton) findViewById(R.id.action_list);
        stragglebtn = (FloatingActionButton) findViewById(R.id.action_staggle);
        plus = (FloatingActionsMenu) findViewById(R.id.plus);
        fuli_back = (ImageView) findViewById(R.id.fuli_back);
    }
    private void setOnClick() {
        girdbtn.setOnClickListener(this);
        listbtn.setOnClickListener(this);
        stragglebtn.setOnClickListener(this);
        fuli_back.setOnClickListener(this);
    }
    private void lefthua() {
        setSwipeBackEnable(true);
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置滑动方向
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
    private void setPresenters(String baseurl,int pages) {
        data_pre2 = new Data_Pre2();
        data_pre2.attach(this);
        data_pre2.getData(baseurl,pages);
    }
    private void setAdapters() {
        myadapter_xrecy4 = new Myadapter_xrecy4(this,results2);
        GridLayoutManager manager1 = new GridLayoutManager(this,2);
        recy3.setLayoutManager(manager1);
        recy3.setAdapter(myadapter_xrecy4);
        myadapter_xrecy4.setItemClickeds(new Myadapter_xrecy4.onitemclick() {
            @Override
            public void onItemClicked(View view, int pos) {
                urllist.clear();
                for(int i=0;i<results2.size();i++) {
                    urllist.add(results2.get(i).getUrl());
                }
                Intent intent = new Intent(f3_Frag3s.this, PhoneActivity2.class);
                intent.putExtra("id",pos);
                intent.putExtra("list",urllist);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_grid:
                GridLayoutManager manager1 = new GridLayoutManager(this,2);
                recy3.setLayoutManager(manager1);
                recy3.setAdapter(myadapter_xrecy4);
                myadapter_xrecy4.setItemClickeds(new Myadapter_xrecy4.onitemclick() {
                    @Override
                    public void onItemClicked(View view, int pos) {
                        urllist.clear();
                        for(int i=0;i<results2.size();i++) {
                            urllist.add(results2.get(i).getUrl());
                        }
                        Intent intent = new Intent(f3_Frag3s.this, PhoneActivity2.class);
                        intent.putExtra("id",pos);
                        intent.putExtra("list",urllist);
                        startActivity(intent);
                    }
                });
                plus.collapse();
                break;
            case R.id.action_list:
                LinearLayoutManager manager2 = new LinearLayoutManager(this);
                recy3.setLayoutManager(manager2);
                recy3.setAdapter(myadapter_xrecy4);
                myadapter_xrecy4.setItemClickeds(new Myadapter_xrecy4.onitemclick() {
                    @Override
                    public void onItemClicked(View view, int pos) {
                        urllist.clear();
                        for(int i=0;i<results2.size();i++) {
                            urllist.add(results2.get(i).getUrl());
                        }
                        Intent intent = new Intent(f3_Frag3s.this, PhoneActivity2.class);
                        intent.putExtra("id",pos);
                        intent.putExtra("list",urllist);
                        startActivity(intent);
                    }
                });
                plus.collapse();
                break;
            case R.id.action_staggle:
                StaggeredGridLayoutManager manager3 = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                recy3.setLayoutManager(manager3);
                recy3.setAdapter(myadapter_xrecy4);
                myadapter_xrecy4.setItemClickeds(new Myadapter_xrecy4.onitemclick() {
                    @Override
                    public void onItemClicked(View view, int pos) {
                        urllist.clear();
                        for(int i=0;i<results2.size();i++) {
                            urllist.add(results2.get(i).getUrl());
                        }
                        Intent intent = new Intent(f3_Frag3s.this, PhoneActivity2.class);
                        intent.putExtra("id",pos);
                        intent.putExtra("list",urllist);
                        startActivity(intent);
                    }
                });
                plus.collapse();
                break;
            case R.id.fuli_back:
                finish();
                break;
        }
    }
    @Override
    public void onsuccess2(Object o) {
        if(o instanceof FuliBean) {
            FuliBean bean = (FuliBean) o;
            if(bean != null) {
                results = bean.getResults();
                results2.addAll(results);
                setAdapters();
            }
        }
    }
    @Override
    public void onfailed2(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(data_pre2 != null) {
            data_pre2.detach();
        }
    }
}
