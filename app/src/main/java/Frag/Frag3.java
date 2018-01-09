package Frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.RootDataBean;
import MyAdapters_List.Huan_MyAdapter;
import Presenter.Data_Presenter;
import Views.IView;
import http.OnSwipeListener;
import zhangtao.bwie.com.demo.Message_even;
import zhangtao.bwie.com.demo.Player_activity;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.SwipeCardCallBack;
import zhangtao.bwie.com.demo.SwipeCardLayoutManager;

/**
 * Created by ZhangTAO on 2017/12/14.
 */

public class Frag3 extends Fragment implements IView{

    private RecyclerView see_recy;
    private Button huan;
    private Data_Presenter presenter;
    private List<RootDataBean.RetBean.ListBean> list;
    private Map<String, String> map;
    private String catalogId = "402834815584e463015584e539330016";
    private int page = 1;
    private Huan_MyAdapter huan_myAdapter;
    private TextView meile;
    private SimpleDraweeView sim_back;
    private SharedPreferences f3_share;
    private int colors = R.color.red_img;
    private RelativeLayout tools;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v3 = inflater.inflate(R.layout.f3, null);
        EventBus.getDefault().register(this);
        f3_share = getContext().getSharedPreferences("color_data", Context.MODE_PRIVATE);
        Fresco.initialize(getContext());
        initView(v3);
        return v3;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int color = f3_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        map = new HashMap<>();
        map.put("catalogId",catalogId);
        map.put("pnum",page+"");
        setPresenter("http://api.svipmovie.com/front/", map);
        huan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page+=1;
                map.put("pnum",page+"");
                setPresenter("http://api.svipmovie.com/front/", map);
            }
        });
    }
    @Subscribe
    public void getMessageEvent(Message_even event) {
        tools.setBackgroundColor(getResources().getColor(event.getColor()));
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }

    private void initView(View v3) {
        see_recy = v3.findViewById(R.id.see_recy);
        tools = v3.findViewById(R.id.toolbar_zt);
        huan = v3.findViewById(R.id.huan);
        meile = v3.findViewById(R.id.meile);
        meile.setVisibility(View.INVISIBLE);
    }
    private void setPresenter(String baseurl, Map<String,String> map) {
        presenter = new Data_Presenter();
        presenter.attach(this);
        presenter.getData(baseurl,map);
    }
    private void setMyAdapter_huan() {
        huan_myAdapter = new Huan_MyAdapter(getContext(),list);
        SwipeCardCallBack cardCallBack = new SwipeCardCallBack(huan_myAdapter,list);
        cardCallBack.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                Huan_MyAdapter.ViewHolder myHolder = (Huan_MyAdapter.ViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);

            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, RootDataBean.RetBean.ListBean t, int direction) {
                viewHolder.itemView.setAlpha(1f);
            }
            @Override
            public void onSwipedClear() {
                see_recy.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setMyAdapter_huan();
                        huan_myAdapter.notifyDataSetChanged();
                    }
                }, 3000L);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(cardCallBack);
        SwipeCardLayoutManager cardManager = new SwipeCardLayoutManager(see_recy, helper);
        see_recy.setLayoutManager(cardManager);
        see_recy.setAdapter(huan_myAdapter);
        helper.attachToRecyclerView(see_recy);
        huan_myAdapter.setItemOnClick(new Huan_MyAdapter.onItemClick() {
            @Override
            public void setItemClicked(View v, int pos) {
                Intent intent = new Intent(getActivity(), Player_activity.class);
                String dataId = list.get(pos).getDataId();
                intent.putExtra("key",dataId);
                getContext().startActivity(intent);
            }
        });
    }
    @Override
    public void onsuccess(Object o) {
        if(o instanceof RootDataBean) {
            RootDataBean beans = (RootDataBean) o;
            if(beans != null) {
                meile.setVisibility(View.VISIBLE);
                RootDataBean.RetBean ret = beans.getRet();
                list = ret.getList();
                setMyAdapter_huan();
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
