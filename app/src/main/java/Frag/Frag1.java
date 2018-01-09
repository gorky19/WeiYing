package Frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.RootBean;
import MyAdapters_List.FatherAdapter;
import Presenter.Data_Presenter;
import Views.IView;
import zhangtao.bwie.com.demo.Message_even;
import zhangtao.bwie.com.demo.Player_activity;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.Select_Activity;
import zhangtao.bwie.com.demo.TranlucentScrollView;

/**
 * Created by ZhangTAO on 2017/12/14.
 */
public class Frag1 extends Fragment implements IView,OnItemClickListener,TranlucentScrollView.OnScrollChangedListener,View.OnClickListener{
    private ConvenientBanner banner;
    private EditText edit_sele;
    private Data_Presenter data_presenter;
    private List<String> imglist = new ArrayList<>();
    private List<RootBean.RetBean.ListBean.ChildListBean> childList;
    private List<RootBean.RetBean.ListBean> father_data = new ArrayList<>();
    private ImageView imageView;
//    private MyRecy myrecy;
    private RecyclerView myrecy;
    private FatherAdapter fatherAdapter;
    private HashMap<String, String> map;
    private RelativeLayout tools;
    private TranlucentScrollView scrollView;
    private List<RootBean.RetBean.ListBean> zt_list;
    private TextView tooltext;
    private float headerHeight;
    private float minHeaderHeight;
    private int colors = R.color.red_img;
    private SharedPreferences f1_share;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imglist.clear();
            father_data.clear();
            for(int i=0;i<childList.size();i++) {
                imglist.add(childList.get(i).getPic());
            }
            for(int i=1;i<zt_list.size()-1;i++) {
                RootBean.RetBean.ListBean listBean = zt_list.get(i);
                father_data.add(listBean);
            }
            getinit();
            setMyadapters();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v1 = inflater.inflate(R.layout.f1, null);
        EventBus.getDefault().register(this);
        Fresco.initialize(getContext());
        initView(v1);
        f1_share = getContext().getSharedPreferences("color_data", Context.MODE_PRIVATE);
        return v1;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int color = f1_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        map = new HashMap<>();
        setPresenter("http://api.svipmovie.com/front/",map);
        setOnCliek();
    }
    @Subscribe
    public void getMessageEvent(Message_even event) {
        tools.setBackgroundColor(getResources().getColor(event.getColor()));
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }

    private void getinit() {
        //开始自动翻页
        banner.setPages(new CBViewHolderCreator<NetWorkLoacl>() {
            @Override
            public NetWorkLoacl createHolder() {
                return new NetWorkLoacl();
            }
        },imglist)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.checked,R.drawable.kong})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置点击监听事件
                .setOnItemClickListener(this)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
    }
    private void setPresenter(String baseurl, Map<String,String> map) {
        data_presenter = new Data_Presenter();
        data_presenter.attach(this);
        data_presenter.getData(baseurl,map);
    }
    private void initView(View v1) {
        banner = v1.findViewById(R.id.banner);
        edit_sele = v1.findViewById(R.id.select_edit);
        myrecy = v1.findViewById(R.id.recy_view);
        tools = v1.findViewById(R.id.toolbar_zt);
        tools.setVisibility(View.INVISIBLE);
        scrollView = v1.findViewById(R.id.scrollview);
        tooltext = v1.findViewById(R.id.tool_text);
        scrollView.setOnScrollChangedListener(this);
    }
    private void setOnCliek() {
        edit_sele.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_edit:
                Intent intent = new Intent(getContext(),Select_Activity.class);
                getContext().startActivity(intent);
                break;
        }
    }
    private void setMyadapters() {
        fatherAdapter = new FatherAdapter(getContext(),father_data);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        myrecy.setLayoutManager(manager);
        myrecy.setAdapter(fatherAdapter);
    }

    @Override
    public void onsuccess(Object o) {
        if(o instanceof RootBean) {
            RootBean bean = (RootBean) o;
            if(bean != null) {
                    zt_list = bean.getRet().getList();
                    childList = zt_list.get(4).getChildList();
                    ler.sendMessage(new Message());
                    }
            }
        }
    @Override
    public void onfailed(Exception e) {

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), Player_activity.class);
        String dataId = childList.get(position).getDataId();
        intent.putExtra("key",dataId);
        getContext().startActivity(intent);
    }

    @Override
    public void onScrollChanged(float alpha) {
        tools.setVisibility(View.VISIBLE);
        tools.setAlpha(alpha);
    }

    public class NetWorkLoacl implements Holder<String> {
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load(data).placeholder(R.drawable.default_320).into(imageView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(data_presenter != null) {
            data_presenter.detach();
        }
    }
}
