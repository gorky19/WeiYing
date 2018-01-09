package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import Bean.ShouBean;
import DBhelp.DBHelper;
import ImmerSionUtil.ImmersionUtil;
import MyAdapters_List.Shou_MyAdapter;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import zhangtao.bwie.com.demo.gen.ShouBeanDao;

/**
 * Created by ZhangTAO on 2018/1/4.
 */

public class Shou_Act extends SwipeBackActivity implements View.OnClickListener{

    private ImageView backs;
    private TextView delete_all;
    private RecyclerView shou_recy;
    private ShouBeanDao shoubean;
    private List<ShouBean> dbHelper;
    private Shou_MyAdapter shou_myAdapter;
    private SharedPreferences shou_share;
    private int colors = R.color.red_img;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dbHelper = getDBHelper();
            setMyAdapter();
            shou_myAdapter.notifyDataSetChanged();
        }
    };
    private RelativeLayout tools;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shou_act);
        shou_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        Fresco.initialize(this);
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        initView();
        setOnClick();
        int color = shou_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        shoubean = DBHelper.getInstance(this).getShoubean();
        dbHelper = getDBHelper();
        Log.d("zzz","数据库实现："+dbHelper.toString());
        setMyAdapter();
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
    private void setOnClick() {
        backs.setOnClickListener(this);
        delete_all.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shou_back_icon:
                finish();
                break;
            case R.id.delete_All:
                shoubean.deleteAll();
                ler.sendMessage(new Message());
                break;
        }
    }
    private void initView() {
        backs = (ImageView) findViewById(R.id.shou_back_icon);
        delete_all = (TextView) findViewById(R.id.delete_All);
        shou_recy = (RecyclerView) findViewById(R.id.shou_recy);
        tools = (RelativeLayout) findViewById(R.id.toolbar_zt);
    }
    private void setMyAdapter() {
        shou_myAdapter = new Shou_MyAdapter(this,dbHelper);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        shou_recy.setAdapter(shou_myAdapter);
        shou_recy.setLayoutManager(manager);
        shou_myAdapter.setItemOnClick(new Shou_MyAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Intent intent = new Intent(Shou_Act.this, Player_activity.class);
                String dataId = dbHelper.get(pos).getShou_dataId();
                intent.putExtra("key",dataId);
                Log.d("zzz","电影资源"+dataId);
                startActivity(intent);
            }
        });
    }
    public List<ShouBean> getDBHelper() {
        List<ShouBean> shouBeen = shoubean.loadAll();
        return shouBeen;
    }
}
