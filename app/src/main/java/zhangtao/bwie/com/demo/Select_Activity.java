package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.Catatory;
import Bean.RootBean;
import Bean.SelectBean;
import DBhelp.DBHelper;
import ImmerSionUtil.ImmersionUtil;
import MyAdapters_List.SelectAdapter;
import MyAdapters_List.Tui_MyAdapter;
import Presenter.Data_Pre;
import Presenter.Data_Pre3;
import Views.IView2;
import Views.IView3;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import zhangtao.bwie.com.demo.gen.CatatoryDao;

/**
 * Created by ZhangTAO on 2018/1/2.
 */

public class Select_Activity extends SwipeBackActivity implements IView3,View.OnClickListener, IView2{

    private FlowLayout flows;
    private List<String> textlist = new ArrayList<>();
    private RelativeLayout zong_lay;
    private RelativeLayout tui_lay;
    private RecyclerView tui_recy;
    private List<SelectBean.RetBean.ListBean> list;
    private Map<String, String> map;
    private int pages = 1;
    private EditText search_edit;
    private TextView search_text;
    private CatatoryDao dao;
    private ImageView xiao_img;
    private String keyword;
    private boolean flage = true;
    private List<Catatory> dbHelperData;
    private ImageView dele_icons;
    private AlertDialog alertDialog;
    private TextView null_data;
    private RelativeLayout flow_lay;
    private MyRecy myrecy;
    private RelativeLayout my_lay;
    private RelativeLayout select_lay;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private List<RootBean.RetBean.ListBean.ChildListBean> childList;
    private Data_Pre data_pre;
    private PullToRefreshLayout search_pull;
    private List<RootBean.RetBean.ListBean> tui_list;
    private Tui_MyAdapter tui_myAdapter;
    private Map<String, String> map2;
    private Data_Pre3 data_pre3;
    private SharedPreferences select_share;
    private int colors = R.color.red_img;
    private RelativeLayout tools;
    private Catatory catatory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        setContentView(R.layout.select_act);
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        select_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        int color = select_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        dao = DBHelper.getInstance(this).getDao();
        add_text();
        flows.setFlowLayout(textlist, new FlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(String content) {
                map.put("keyword",content);
                setPresenter("http://api.svipmovie.com/front/",map);
                dbHelperData = getDBHelperData();
                if(dbHelperData == null || dbHelperData.size() == 0) {
                }else {
                    for(Catatory cata : dbHelperData) {
                        if(cata.getLishi_text().equals(content)) {
                            catatory = new Catatory();
                            catatory = cata;
                            dao.delete(catatory);
                        }
                    }
                    Catatory catatorys = new Catatory();
                    catatorys.setLishi_text(content);
                    long insert = dao.insert(catatorys);
                }
            }
        });
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(search_edit.getText().toString().trim().equals("")) {
                    xiao_img.setVisibility(View.INVISIBLE);
                    search_text.setText("取消");
                }else {
                    xiao_img.setVisibility(View.VISIBLE);
                    search_text.setText("搜索");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        map = new HashMap<>();
        map2 = new HashMap<>();
        map.put("pnum",pages+"");
        setOnClick();
        setPresenter2("http://api.svipmovie.com/front/",map2);
        search_pull.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                ler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tui_myAdapter.notifyDataSetChanged();
                        search_pull.finishRefresh();
                    }
                },1500);
            }
            @Override
            public void loadMore() {
                pages+=1;
                map.put("pnum",pages+"");
                setPresenter("http://api.svipmovie.com/front/",map);
                search_pull.finishLoadMore();
            }
        });
        lefthua();
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private void lefthua() {
        setSwipeBackEnable(true);
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置滑动方向
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    private void add_text() {
        dbHelperData = getDBHelperData();
        if(dbHelperData == null || dbHelperData.size() == 0) {
        }else {
            for(int i = dbHelperData.size()-1;i>=0;i--) {
                textlist.add(dbHelperData.get(i).getLishi_text());
            }
        }
    }
    private List<Catatory> getDBHelperData() {
        List<Catatory> data_list = dao.loadAll();
        return data_list;
    }
    private void setOnClick() {
        search_text.setOnClickListener(this);
        xiao_img.setOnClickListener(this);
        dele_icons.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_text:
                Toast.makeText(Select_Activity.this,"搜索了",Toast.LENGTH_SHORT).show();
                if(search_text.getText().toString().trim().equals("搜索")) {
                    keyword = search_edit.getText().toString().trim();
                    dbHelperData = getDBHelperData();
                    for(Catatory cata : dbHelperData) {
                        if(cata.getLishi_text().equals(keyword)) {
                            catatory = new Catatory();
                            catatory = cata;
                            dao.delete(catatory);
                            flage = true;
                        }else {
                            flage = true;
                        }
                    }
                    if(flage) {
                            Catatory catatorys = new Catatory();
                            map.put("keyword",keyword);
                            setPresenter("http://api.svipmovie.com/front/",map);
                            catatorys.setLishi_text(keyword);
                            long insert = dao.insert(catatorys);
                    }
                        add_text();
                }else {
                    finish();
                }
                break;
            case R.id.xiao_img:
                search_edit.setText("");
                xiao_img.setVisibility(View.INVISIBLE);
                break;
            case R.id.delete_lishi:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确认清空历史搜索吗？");
                builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.deleteAll();
                        flows.setVisibility(View.GONE);
                        flow_lay.setVisibility(View.GONE);
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }
    private void initView() {
        tools = (RelativeLayout) findViewById(R.id.toolbar_zt);
        flows = (FlowLayout) findViewById(R.id.flow);
        zong_lay = (RelativeLayout) findViewById(R.id.zong_lay);
        tui_lay = (RelativeLayout) findViewById(R.id.tuijian_layout);
        tui_recy = (RecyclerView) findViewById(R.id.tui_recy);
        search_edit = (EditText) findViewById(R.id.home_search_edit);
        search_text = (TextView) findViewById(R.id.search_text);
        tui_lay.setVisibility(View.INVISIBLE);
        xiao_img = (ImageView) findViewById(R.id.xiao_img);
        xiao_img.setVisibility(View.INVISIBLE);
        dele_icons = (ImageView) findViewById(R.id.delete_lishi);
        search_text.setText("取消");
        null_data = (TextView) findViewById(R.id.null_data);
        null_data.setVisibility(View.INVISIBLE);
        flow_lay = (RelativeLayout) findViewById(R.id.flow_lay);
        select_lay = (RelativeLayout) findViewById(R.id.select_layout);
        my_lay = (RelativeLayout) findViewById(R.id.my_lay);
        myrecy = (MyRecy) findViewById(R.id.my_recy);
        search_pull = (PullToRefreshLayout) findViewById(R.id.search_pull);
    }
    private void setPresenter(String baseurl, Map<String,String> map) {
        data_pre3 = new Data_Pre3();
        data_pre3.attach(this);
        data_pre3.getData(baseurl,map);
    }
    private void set_SelectAdapter() {
        SelectAdapter selectAdapter = new SelectAdapter(this,list);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        tui_recy.setLayoutManager(manager);
        tui_recy.setAdapter(selectAdapter);
        selectAdapter.setItemOnClick(new SelectAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Intent intent = new Intent(Select_Activity.this, Player_activity.class);
                String dataId = list.get(pos).getDataId();
                intent.putExtra("key",dataId);
                startActivity(intent);
            }
        });
    }
    private void setPresenter2(String baseurl, Map<String,String> map) {
        data_pre = new Data_Pre();
        data_pre.attach(this);
        data_pre.getData(baseurl,map);
    }
    private void set_SelectAdapter2() {
        tui_myAdapter = new Tui_MyAdapter(this,childList);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        myrecy.setLayoutManager(manager);
        myrecy.setAdapter(tui_myAdapter);
        tui_myAdapter.setItemOnClick(new Tui_MyAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Intent intent = new Intent(Select_Activity.this, Player_activity.class);
                String dataId = childList.get(pos).getDataId();
                intent.putExtra("key",dataId);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onsuccess3(SelectBean bean) {
        if(bean != null) {
            null_data.setVisibility(View.INVISIBLE);
            zong_lay.setVisibility(View.INVISIBLE);
            tui_lay.setVisibility(View.VISIBLE);
            flow_lay.setVisibility(View.INVISIBLE);
            my_lay.setVisibility(View.INVISIBLE);
            Log.d("zzz","搜索接口："+bean.toString());
            SelectBean.RetBean ret = bean.getRet();
            list = ret.getList();
            if(list.size() == 0 || list == null) {
                null_data.setVisibility(View.VISIBLE);
            }
            set_SelectAdapter();
        }else {
            null_data.setVisibility(View.VISIBLE);
            zong_lay.setVisibility(View.INVISIBLE);
            tui_lay.setVisibility(View.VISIBLE);
            flow_lay.setVisibility(View.INVISIBLE);
            my_lay.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onfailed3(Exception e) {

    }
    @Override
    public void onsuccess2(Object o) {
        null_data.setVisibility(View.INVISIBLE);
        if(o instanceof RootBean) {
            null_data.setVisibility(View.INVISIBLE);
            RootBean bean = (RootBean) o;
            if(bean != null) {
                RootBean.RetBean ret = bean.getRet();
                tui_list = ret.getList();
                childList = tui_list.get(8).getChildList();
                set_SelectAdapter2();
            }
        }
    }

    @Override
    public void onfailed2(Exception e) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(data_pre != null) {
            data_pre.detach();
        }
        if(data_pre3 != null) {
            data_pre3.detach();
        }
    }
}
