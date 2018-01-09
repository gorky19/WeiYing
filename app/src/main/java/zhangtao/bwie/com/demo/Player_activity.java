package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.LishiBean;
import Bean.ShouBean;
import Bean.VideoBean;
import DBhelp.DBHelper;
import Event.EventMassage;
import Frag.Player1;
import Frag.Player2;
import ImmerSionUtil.ImmersionUtil;
import Presenter.Data_Presenter;
import Views.IView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import zhangtao.bwie.com.demo.gen.LishiBeanDao;
import zhangtao.bwie.com.demo.gen.ShouBeanDao;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class Player_activity extends SwipeBackActivity implements IView,View.OnClickListener{
    private TabLayout tablayout;
    private List<String> textlist = new ArrayList<>();
    private List<Fragment> fraglist = new ArrayList<>();
    private HashMap<String, String> map;
    private String key;
    private Data_Presenter data_presenter;
    private VideoBean.RetBean ret;
    private MyViewPager vps;
    private TextView tit_movic;
    private ImageView back_img;
    private JCVideoPlayerStandard jcvideo;
    private boolean setUp;
    private ImageView shou_icons;
    private boolean b;
    private SharedPreferences shou_share;
    private SharedPreferences.Editor edit;
    private boolean flag;
    private ShouBeanDao shoubean;
    private List<ShouBean> dBhelper_data;
    private boolean one;
    private LishiBeanDao lishibean;
    private List<LishiBean> dbHelper_lishi;
    private List<LishiBean> lishiBeanList = new ArrayList<>();
    private int colors = R.color.red_img;
    private SharedPreferences player_share;
    private LishiBean lishi;
    private RelativeLayout tools;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(ret.getHDURL() != null) {
                setUp = jcvideo.setUp(ret.getHDURL(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"");
            }else {
                Toast.makeText(Player_activity.this,"播放地址无效",Toast.LENGTH_SHORT).show();
            }
            Glide.with(Player_activity.this).load(ret.getPic()).into(jcvideo.thumbImageView);
            jcvideo.startButton.performClick();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_layout);
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        player_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        shoubean = DBHelper.getInstance(this).getShoubean();
        lishibean = DBHelper.getInstance(this).getLishibean();
        key = getIntent().getStringExtra("key");
        map = new HashMap<>();
        map.put("mediaId", key);
        initView();
        init();
        setOnclicked();
        getinit();
        int color = player_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        dBhelper_data = getDBhelper_Data();
        for(ShouBean show : dBhelper_data) {
            if(dBhelper_data != null) {
                if(show.getShou_dataId().equals(key)) {
                    shou_icons.setBackground(getResources().getDrawable(R.drawable.shou_zt2));
                    flag = true;
                }
            }else {
                flag = false;
            }
        }
        tablayout.setupWithViewPager(vps);
        setPresenter_Video("http://api.svipmovie.com/front/", map);
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
    private void add_ListBean() {
        lishiBeanList.clear();
        dbHelper_lishi = getDBHelper_Lishi();
        if(dbHelper_lishi == null || dbHelper_lishi.size() == 0) {
            one = true;
        }else {
            for(int i=dbHelper_lishi.size()-1;i>=0;i--) {
                lishiBeanList.add(dbHelper_lishi.get(i));
            }
            for(LishiBean bean : lishiBeanList) {
                if(bean.getKey().equals(key)) {
                    lishi = new LishiBean();
                    lishi = bean;
                    lishibean.delete(lishi);
                    one = true;
                    break;
                }else if(!bean.getKey().equals(key)) {
                    one = true;
                }
            }
        }
        if(one) {
            LishiBean lishi = new LishiBean();
            lishi.setKey(key);
            lishi.setTitles(ret.getTitle());
            lishi.setIcons(ret.getPic());
            lishibean.insert(lishi);
            one = false;
        }
    }
    private List<LishiBean> getDBHelper_Lishi() {
        List<LishiBean> lishiBeen = lishibean.loadAll();
        return lishiBeen;
    }
    private void setOnclicked() {
        back_img.setOnClickListener(this);
        shou_icons.setOnClickListener(this);
    }
    private List<ShouBean> getDBhelper_Data() {
        List<ShouBean> shouBeen = shoubean.loadAll();
        return shouBeen;
    }
    private void setPresenter_Video(String baseurl, Map<String, String> map) {
        data_presenter = new Data_Presenter();
        data_presenter.attach(this);
        data_presenter.getData(baseurl, map);
    }

    private void initView() {
        jcvideo = (JCVideoPlayerStandard) findViewById(R.id.jcvideo);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        vps = (MyViewPager) findViewById(R.id.vps);
        tools = (RelativeLayout) findViewById(R.id.head_tool);
        tit_movic = (TextView) findViewById(R.id.title_movic);
        back_img = (ImageView) findViewById(R.id.back_img);
        shou_icons = (ImageView) findViewById(R.id.shou_icons);
        shou_icons.setBackground(getResources().getDrawable(R.drawable.shou_zt1));
    }

    private void init() {
        textlist.add("简介");
        textlist.add("评论");
        fraglist.add(new Player1());
        fraglist.add(new Player2());
    }

    private void getinit() {
        MyAdapter_tab myAdapter_tab = new MyAdapter_tab(getSupportFragmentManager(), fraglist);
        vps.setAdapter(myAdapter_tab);
    }

    @Override
    public void onsuccess(Object o) {
        if (o instanceof VideoBean) {
            VideoBean bean = (VideoBean) o;
            if (bean != null) {
                ret = bean.getRet();
                add_ListBean();
                tit_movic.setText(ret.getTitle());
                ler.sendMessage(new Message());
            }
        }
    }

    @Override
    public void onfailed(Exception e) {
    }
    @Override
    public void onBackPressed() {
        if(JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.shou_icons:
                if(flag) {
                    shou_icons.setBackground(getResources().getDrawable(R.drawable.shou_zt1));
                    Toast.makeText(Player_activity.this,"取消收藏",Toast.LENGTH_SHORT).show();
                    flag = false;
                    ShouBean shbean = new ShouBean();
                    dBhelper_data = getDBhelper_Data();
                    for(ShouBean show : dBhelper_data) {
                        if(show.getShou_dataId().equals(key)) {
                            shbean = show;
                        }
                    }
                       shoubean.delete(shbean);
                }else if(!flag) {
                    shou_icons.setBackground(getResources().getDrawable(R.drawable.shou_zt2));
                    Toast.makeText(Player_activity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    flag = true;
                    ShouBean shbean = new ShouBean();
                    dBhelper_data = getDBhelper_Data();
                    shbean.setShou_title(ret.getTitle());
                    shbean.setShou_icon(ret.getPic());
                    shbean.setShou_dataId(key);
                    long insert = shoubean.insert(shbean);
                }
                break;
        }
    }
    private class MyAdapter_tab extends FragmentPagerAdapter {
        private List<Fragment> fraglist;
        public MyAdapter_tab(FragmentManager supportFragmentManager, List<Fragment> fraglist) {
            super(supportFragmentManager);
            this.fraglist = fraglist;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return textlist.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("keys",key);
            fraglist.get(position).setArguments(bundle);
            return fraglist.get(position);
        }

        @Override
        public int getCount() {
            return textlist.size();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        key = intent.getStringExtra("key");
        map.put("mediaId", key);
        setPresenter_Video("http://api.svipmovie.com/front/", map);
        EventBus.getDefault().post(new EventMassage(key));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(data_presenter != null) {
            data_presenter.detach();
        }
    }
}
