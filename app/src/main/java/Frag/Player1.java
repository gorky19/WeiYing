package Frag;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.VideoBean;
import Event.EventMassage;
import MyAdapters_List.MyAdapter_jianjie;
import Presenter.Data_Presenter;
import Views.IView;
import zhangtao.bwie.com.demo.Player_activity;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class Player1 extends Fragment implements IView,View.OnClickListener{

    private TextView daoyan;
    private TextView acter;
    private TextView zhankai;
    private TextView xiangqing;
    private RecyclerView more_data;
    private Data_Presenter data_presenter;
    private String keys;
    private VideoBean.RetBean ret;
    private boolean flag = true;
    private List<VideoBean.RetBean.ListBean> jianli;
    private List<VideoBean.RetBean.ListBean.ChildListBean> childList;
    private MyAdapter_jianjie myAdapter;
    private HashMap<String, String> map;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                for(int i=0;i<jianli.size();i++) {
                    childList = jianli.get(i).getChildList();
                }
            setAdapter_jianjie();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View player1 = inflater.inflate(R.layout.movic_f1, null);
        EventBus.getDefault().register(this);
        Fresco.initialize(getContext());
        initView(player1);
        return player1;
    }

    private void initView(View player1) {
        daoyan = player1.findViewById(R.id.daoyan);
        acter = player1.findViewById(R.id.acter);
        zhankai = player1.findViewById(R.id.zhankai);
        xiangqing = player1.findViewById(R.id.xiangqing);
        more_data = player1.findViewById(R.id.more_data);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = new HashMap<>();
        Bundle arguments = getArguments();
        keys = arguments.getString("keys");
        map.put("mediaId", keys);
        setPresenter_movic("http://api.svipmovie.com/front/", map);
        setOnClicked();
    }
    @Subscribe
    public void getEventMessage(EventMassage event) {
        String key = event.getKey();
        map.put("mediaId", key);
        setPresenter_movic("http://api.svipmovie.com/front/", map);
        setOnClicked();
    }
    private void setPresenter_movic(String baseurl, Map<String,String> map) {
        data_presenter = new Data_Presenter();
        data_presenter.attach(this);
        data_presenter.getData(baseurl,map);
    }
    private void setAdapter_jianjie() {
        myAdapter = new MyAdapter_jianjie(getActivity(),childList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        more_data.setAdapter(myAdapter);
        more_data.setLayoutManager(gridLayoutManager);
        myAdapter.setOnItemClicked(new MyAdapter_jianjie.OnItemClick() {
            @Override
            public void setItemClick(View v, int pos) {
                Toast.makeText(getContext(),"哈哈哈哈或",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Player_activity.class);
                String dataId = childList.get(pos).getDataId();
                intent.putExtra("key",dataId);
                Log.d("zzz","电影资源"+dataId);
                getContext().startActivity(intent);
            }
        });
    }
    private void setOnClicked() {
        zhankai.setOnClickListener(this);
    }
    @Override
    public void onsuccess(Object o) {
        if(o instanceof VideoBean) {
            VideoBean bean = (VideoBean) o;
            if(bean != null) {
                ret = bean.getRet();
                daoyan.setText("导演:"+ret.getDirector());
                acter.setText("演员"+ret.getActors());
                xiangqing.setText("剧情介绍:"+ret.getDescription());
                jianli = ret.getList();
                    ler.sendMessage(new Message());
            }
        }
    }
    @Override
    public void onfailed(Exception e) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhankai:
                if(flag) {
                    flag = false;
                    zhankai.setText("收回");
                    xiangqing.setVisibility(View.VISIBLE);
                }else {
                    flag = true;
                    zhankai.setText("展开");
                    xiangqing.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(data_presenter != null) {
            data_presenter.detach();
        }
        EventBus.getDefault().unregister(this);
    }
}
