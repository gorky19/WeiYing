package Frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.CommentaryBean;
import MyAdapters_List.MyAdapter_pingjia;
import Presenter.Data_Presenter;
import Views.IView;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class Player2 extends Fragment implements IView{
    private RecyclerView pingjia_recy;
    private Data_Presenter data_presenter;
    private CommentaryBean.RetBean ret;
    private String keys;
    private List<CommentaryBean.RetBean.ListBean> pingjia;
    private TextView unfind_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View player2 = inflater.inflate(R.layout.movic_f2,null);
        Fresco.initialize(getContext());
        pingjia_recy = player2.findViewById(R.id.pingjia_recy);
        unfind_text = player2.findViewById(R.id.unfind_text);
        unfind_text.setVisibility(View.INVISIBLE);
        pingjia_recy.setVisibility(View.VISIBLE);
        return player2;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HashMap<String, String> map = new HashMap<>();
        Bundle arguments = getArguments();
        keys = arguments.getString("keys");
        Log.d("zzz","数据来了："+keys.toString());
        map.put("mediaId", keys);
        setPresenter_movic("http://api.svipmovie.com/front/",map);
    }
    private void setPresenter_movic(String baseurl, Map<String,String> map) {
        data_presenter = new Data_Presenter();
        data_presenter.attach(this);
        data_presenter.getData(baseurl,map);
    }
    private void setAdapter_jianjie() {

        MyAdapter_pingjia myAdapter = new MyAdapter_pingjia(getActivity(),pingjia);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        pingjia_recy.setLayoutManager(manager);
        pingjia_recy.setAdapter(myAdapter);

    }
    @Override
    public void onsuccess(Object o) {
        if(o instanceof CommentaryBean) {
            CommentaryBean bean = (CommentaryBean) o;
            if(bean != null) {
                ret = bean.getRet();
                pingjia = ret.getList();
                setAdapter_jianjie();
            }else {
                pingjia_recy.setVisibility(View.INVISIBLE);
                unfind_text.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onfailed(Exception e) {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(data_presenter != null) {
            data_presenter.detach();
        }
    }
}
