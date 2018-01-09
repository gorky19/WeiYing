package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.RootDataBean;
import ImmerSionUtil.ImmersionUtil;
import MyAdapters_List.Chiled_MyAdapter;
import Presenter.Data_Presenter;
import Views.IView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by ZhangTAO on 2017/12/31.
 */

public class Zhuan_chiled extends SwipeBackActivity implements IView{
    private TextView titles;
    private RecyclerView chiled_recy;
    private PullToRefreshLayout zhuan_pull;
    private String catalogId;
    private int page =1;
    private Map<String, String> map;
    private String tit;
    private Data_Presenter presenter;
    private List<RootDataBean.RetBean.ListBean> list;
    private Chiled_MyAdapter chiled_myAdapter;
    private List<RootDataBean.RetBean.ListBean> big_list = new ArrayList<>();
    private ImageView img_back;
    private SharedPreferences zhuan_chiled;
    private int colors = R.color.red_img;
    private RelativeLayout tools;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuan_chiled_act);
        Fresco.initialize(this);
        zhuan_chiled = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        initView();
        int color = zhuan_chiled.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        Intent getintent = getIntent();
        catalogId = getintent.getStringExtra("catalogId");
        tit = getintent.getStringExtra("tit");
        titles.setText(tit);
        Log.d("zzz",catalogId+"---"+tit);
        map = new HashMap<>();
        map.put("catalogId",catalogId);
        map.put("pnum",page+"");
        setPresenter("http://api.svipmovie.com/front/",map);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zhuan_pull.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chiled_myAdapter.notifyDataSetChanged();
                        zhuan_pull.finishRefresh();
                    }
                },1000);
            }
            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=page+1;
                        map.put("pnum",page+"");
                        setPresenter("http://api.svipmovie.com/front/",map);
                        Log.d("zzz",map.toString());
                        zhuan_pull.finishLoadMore();
                    }
                },2000);
            }
        });
        lefthua();
    }
    private void lefthua() {
        setSwipeBackEnable(true);
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置滑动方向
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private void initView() {
        tools = (RelativeLayout) findViewById(R.id.toolbar_zt);
        titles = (TextView) findViewById(R.id.tool_text);
        chiled_recy = (RecyclerView) findViewById(R.id.zhuan_chiledrecy);
        zhuan_pull = (PullToRefreshLayout) findViewById(R.id.zhuan_pull);
        img_back = (ImageView) findViewById(R.id.img_back);
    }
    private void setPresenter(String url,Map<String,String> map) {
        presenter = new Data_Presenter();
        presenter.attach(this);
        presenter.getData(url,map);
    }
    private void setChiled_MyAdapter() {
        chiled_myAdapter = new Chiled_MyAdapter(this,big_list);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        chiled_recy.setLayoutManager(manager);
        chiled_recy.setAdapter(chiled_myAdapter);
        chiled_myAdapter.setItemOnClick(new Chiled_MyAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Intent intent = new Intent(Zhuan_chiled.this, Player_activity.class);
                String dataId = list.get(pos).getDataId();
                intent.putExtra("key",dataId);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onsuccess(Object o) {
        if(o instanceof RootDataBean) {
            RootDataBean beans = (RootDataBean) o;
            if(beans != null) {
                RootDataBean.RetBean ret = beans.getRet();
                list = ret.getList();
                big_list.addAll(list);
                setChiled_MyAdapter();
            }
        }
    }
    @Override
    public void onfailed(Exception e) {
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        Log.d("zzz",e.getMessage().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.detach();
        }
    }
}
