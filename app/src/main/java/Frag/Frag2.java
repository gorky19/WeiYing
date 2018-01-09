package Frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.RootBean;
import MyAdapters_List.Zhuan_MyAdapter;
import Presenter.Data_Presenter;
import Views.IView;
import zhangtao.bwie.com.demo.Message_even;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.Zhuan_chiled;

/**
 * Created by ZhangTAO on 2017/12/14.
 */

public class Frag2 extends Fragment implements IView{

    private RecyclerView zhuan_recy;
    private Data_Presenter presenter;
    private List<String> morelist = new ArrayList<>();
    private List<RootBean.RetBean.ListBean> list = new ArrayList<>();
    private List<String> zhuan_title = new ArrayList<>();
    private List<RootBean.RetBean.ListBean.ChildListBean> childList = new ArrayList<>();
    private Map<String, String> map;
    private List<String> zhuan_img = new ArrayList<>();
    private String catalogId;
    private SharedPreferences f2_share;
    private int colors = R.color.red_img;
    private RelativeLayout tools;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            zhuan_title.clear();
            zhuan_img.clear();
            morelist.clear();
            for(int i=2;i<=4;i++) {
                zhuan_img.add(list.get(i).getChildList().get(0).getPic());
            }
            for(int i=7;i<list.size();i++) {
                zhuan_img.add(list.get(i).getChildList().get(0).getPic());
            }
            for(int i=2;i<=4;i++) {
                zhuan_title.add(list.get(i).getTitle());
            }
            for(int i=7;i<list.size();i++) {
                zhuan_title.add(list.get(i).getTitle());
            }
            for(int i=2;i<=4;i++) {
                morelist.add(list.get(i).getMoreURL());
            }
            for(int i=7;i<list.size();i++) {
                morelist.add(list.get(i).getMoreURL());
            }
            setAdapters();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v2 = inflater.inflate(R.layout.f2, null);
        EventBus.getDefault().register(this);
        Fresco.initialize(getContext());
        initView(v2);
        f2_share = getContext().getSharedPreferences("color_data", Context.MODE_PRIVATE);
        return v2;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Subscribe
    public void getMessageEvent(Message_even event) {
        tools.setBackgroundColor(getResources().getColor(event.getColor()));
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int color = f2_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        map = new HashMap<>();
        setPresenter("http://api.svipmovie.com/front/",map);
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private void initView(View v2) {
        zhuan_recy = v2.findViewById(R.id.zhuan_recy);
        tools = v2.findViewById(R.id.toolbar_zt);
    }
    public void setPresenter(String url, Map<String,String> map) {
        presenter = new Data_Presenter();
        presenter.attach(this);
        presenter.getData(url,map);
    }
    private void setAdapters() {
        Zhuan_MyAdapter zhuan_myAdapter = new Zhuan_MyAdapter(getActivity(),zhuan_title,zhuan_img);
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        zhuan_recy.setAdapter(zhuan_myAdapter);
        zhuan_recy.setLayoutManager(manager);
        zhuan_myAdapter.setItemOnClick(new Zhuan_MyAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Log.d("zzz","ID"+pos);
                String more = morelist.get(pos);
                catalogId = more.substring(more.lastIndexOf("catalogId") + 10, more.indexOf("&"));
                String tit = zhuan_title.get(pos);
                Intent intent = new Intent(getContext(), Zhuan_chiled.class);
                intent.putExtra("catalogId",catalogId);
                Log.d("zzz","catalogId:"+catalogId);
                intent.putExtra("tit",tit);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onsuccess(Object o) {
        if(o instanceof RootBean) {
            RootBean bean = (RootBean) o;
            if(bean != null) {
                RootBean.RetBean ret = bean.getRet();
                list = ret.getList();
                ler.sendMessage(new Message());
            }
        }
    }
    @Override
    public void onfailed(Exception e) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(presenter != null) {
            presenter.detach();
        }
    }
}
