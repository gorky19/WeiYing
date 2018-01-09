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

import Bean.LishiBean;
import DBhelp.DBHelper;
import ImmerSionUtil.ImmersionUtil;
import MyAdapters_List.Lishi_MyAdapter;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import zhangtao.bwie.com.demo.gen.LishiBeanDao;

/**
 * Created by ZhangTAO on 2018/1/4.
 */

public class Lishi_Act extends SwipeBackActivity implements View.OnClickListener{
    private ImageView lishi_back;
    private TextView lishi_delete;
    private RecyclerView lishi_recy;
    private LishiBeanDao lishibean;
    private List<LishiBean> lishi;
    private Lishi_MyAdapter lishi_myAdapter;
    private SharedPreferences lishi_share;
    private int colors = R.color.red_img;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            lishi = getDBHelper();
            setMyadapter();
            lishi_myAdapter.notifyDataSetChanged();
        }
    };
    private RelativeLayout tools;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lishi_act);
        Fresco.initialize(this);
        lishi_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        initView();
        setOnClick();
        lefthua();
        int color = lishi_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        lishibean = DBHelper.getInstance(this).getLishibean();
        lishi = getDBHelper();
        setMyadapter();
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private void setMyadapter() {
        lishi_myAdapter = new Lishi_MyAdapter(this, lishi);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        lishi_recy.setLayoutManager(manager);
        lishi_recy.setAdapter(lishi_myAdapter);
        lishi_myAdapter.setItemOnClick(new Lishi_MyAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Intent intent = new Intent(Lishi_Act.this, Player_activity.class);
                String dataId = lishi.get(pos).getKey();
                intent.putExtra("key",dataId);
                Log.d("zzz","电影资源"+dataId);
                startActivity(intent);
            }
        });
    }
    private List<LishiBean> getDBHelper() {
        List<LishiBean> lishiBeen = lishibean.loadAll();
        return lishiBeen;
    }
    private void lefthua() {
        setSwipeBackEnable(true);
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        //设置滑动方向
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
    private void setOnClick() {
        lishi_back.setOnClickListener(this);
        lishi_delete.setOnClickListener(this);
    }
    private void initView() {
        lishi_back = (ImageView) findViewById(R.id.lishi_back_icons);
        lishi_delete = (TextView) findViewById(R.id.lishi_delete_All);
        lishi_recy = (RecyclerView) findViewById(R.id.llishi_recy);
        tools = (RelativeLayout) findViewById(R.id.toolbar_zt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lishi_back_icons:
                finish();
                break;
            case R.id.lishi_delete_All:
                lishibean.deleteAll();
                ler.sendMessage(new Message());
                break;
        }
    }
}
