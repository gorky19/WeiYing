package Frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import Bean.LishiBean;
import DBhelp.DBHelper;
import MyAdapters_List.MyAdapter_Lishi;
import de.hdodenhof.circleimageview.CircleImageView;
import zhangtao.bwie.com.demo.Lishi_Act;
import zhangtao.bwie.com.demo.Message_even;
import zhangtao.bwie.com.demo.Player_activity;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.Setup_Act;
import zhangtao.bwie.com.demo.Shou_Act;
import zhangtao.bwie.com.demo.gen.LishiBeanDao;

/**
 * Created by ZhangTAO on 2017/12/14.
 */

public class Frag4 extends Fragment implements View.OnClickListener{
    private RelativeLayout zhuti_rela;
    private RelativeLayout shoucang;
    private RelativeLayout lishi;
    private RelativeLayout huancun;
    private RecyclerView lishi_zt_recy;
    private LishiBeanDao lishibean;
    private List<LishiBean> dbHelper_data;
    private List<LishiBean> lishiBeanList = new ArrayList<>();
    private MyAdapter_Lishi myAdapter_lishi;
    private TextView recy_text;
    private SharedPreferences f4_share;
    private int colors = R.color.red_img;
    private CircleImageView blue;
    private CircleImageView red;
    private CircleImageView yellow;
    private CircleImageView green;
    private CircleImageView black;
    private CircleImageView zise;
    private CircleImageView oragea;
    private CircleImageView are;
    private CircleImageView pink;
    private RelativeLayout tools;
    private RelativeLayout theme;
    private AlertDialog alertDialog;
    private View inflate;
    private TextView cancel;
    private TextView finish;
    private SharedPreferences.Editor edit;
    private ImageView setup;
    private Handler ler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setZT_LishiAdapter();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v4 = inflater.inflate(R.layout.f4, null);
        EventBus.getDefault().register(this);
        initView(v4);
        f4_share = getContext().getSharedPreferences("color_data", Context.MODE_PRIVATE);
        edit = f4_share.edit();
        return v4;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClick();
        int color = f4_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
    }
    @Subscribe
    public void getMessageEvent(Message_even event) {
        tools.setBackgroundColor(getResources().getColor(event.getColor()));
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private List<LishiBean> getDBHelper_Data() {
        List<LishiBean> lishiBeen = lishibean.loadAll();
        return lishiBeen;
    }

    private void onClick() {
        zhuti_rela.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        lishi.setOnClickListener(this);
        huancun.setOnClickListener(this);
        theme.setOnClickListener(this);
        setup.setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        setRecy();
    }
    private void initView(View v4) {
        zhuti_rela = v4.findViewById(R.id.zhuti);
        shoucang = v4.findViewById(R.id.shoucang);
        lishi = v4.findViewById(R.id.lishi);
        tools = v4.findViewById(R.id.toolbar_zt);
        huancun = v4.findViewById(R.id.huancun);
        lishi_zt_recy = v4.findViewById(R.id.lishi_recy);
        recy_text = v4.findViewById(R.id.recy_xian);
        theme = v4.findViewById(R.id.zhuti);
        setup = v4.findViewById(R.id.setup);
    }
    private void setZT_LishiAdapter() {
        myAdapter_lishi = new MyAdapter_Lishi(getActivity(),lishiBeanList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(manager.HORIZONTAL);
        lishi_zt_recy.setAdapter(myAdapter_lishi);
        lishi_zt_recy.setLayoutManager(manager);
        myAdapter_lishi.setItemClickeds(new MyAdapter_Lishi.onitemclick() {
            @Override
            public void onItemClicked(View view, int pos) {
                Intent intent = new Intent(getContext(), Player_activity.class);
                String dataId = lishiBeanList.get(pos).getKey();
                intent.putExtra("key",dataId);
                startActivity(intent);
            }
        });
    }
    private void setRecy() {
        lishiBeanList.clear();
        lishibean = DBHelper.getInstance(getContext()).getLishibean();
        dbHelper_data = getDBHelper_Data();
        if(dbHelper_data == null || dbHelper_data.size() == 0) {
            lishi_zt_recy.setVisibility(View.GONE);
            recy_text.setVisibility(View.GONE);
        }else {
            lishi_zt_recy.setVisibility(View.VISIBLE);
            recy_text.setVisibility(View.VISIBLE);
            for(int i=dbHelper_data.size()-1;i>=0;i--) {
                lishiBeanList.add(dbHelper_data.get(i));
            }
        }
        setZT_LishiAdapter();
    }
    @Override
    public void onPause() {
        super.onPause();

    }
    private void setColorText() {
        finish.setTextColor(getResources().getColor(f4_share.getInt("color",colors)));
        cancel.setTextColor(getResources().getColor(f4_share.getInt("color",colors)));
    }
    private void showThemes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        alertDialog = builder.create();
        inflate = View.inflate(getContext(), R.layout.theme_zt, null);
        blue = inflate.findViewById(R.id.blue_img);
        red = inflate.findViewById(R.id.red_img);
        yellow = inflate.findViewById(R.id.yellow_img);
        green = inflate.findViewById(R.id.green_img);
        black = inflate.findViewById(R.id.black_img);
        zise = inflate.findViewById(R.id.zise_img);
        oragea = inflate.findViewById(R.id.oragea_img);
        are = inflate.findViewById(R.id.are_img);
        pink = inflate.findViewById(R.id.pink_img);
        cancel = inflate.findViewById(R.id.cancel);
        finish = inflate.findViewById(R.id.finish);
        blue.setOnClickListener(this);
        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        black.setOnClickListener(this);
        zise.setOnClickListener(this);
        oragea.setOnClickListener(this);
        are.setOnClickListener(this);
        pink.setOnClickListener(this);
        cancel.setOnClickListener(this);
        finish.setOnClickListener(this);
        alertDialog.setView(inflate);
        setColorText();
        alertDialog.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuti:
                showThemes();
                break;
            case R.id.shoucang:
                Intent intent = new Intent(getContext(), Shou_Act.class);
                startActivity(intent);
                break;
            case R.id.setup:
                Intent setup_intent = new Intent(getContext(), Setup_Act.class);
                startActivity(setup_intent);
                break;
            case R.id.lishi:
                Intent intent2 = new Intent(getContext(), Lishi_Act.class);
                startActivity(intent2);
                break;
            case R.id.huancun:
                Toast.makeText(getContext(),"敬请期待！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.blue_img:
                edit.putInt("color",R.color.blue_img);
                edit.commit();
                setColorText();
                break;
            case R.id.red_img:
                edit.putInt("color",R.color.red_img);
                edit.commit();
                setColorText();
                break;
            case R.id.yellow_img:
                edit.putInt("color",R.color.yellow_img);
                edit.commit();
                setColorText();
                break;
            case R.id.green_img:
                edit.putInt("color",R.color.green_img);
                edit.commit();
                setColorText();
                break;
            case R.id.black_img:
                edit.putInt("color",R.color.black_img);
                edit.commit();
                setColorText();
                break;
            case R.id.zise_img:
                edit.putInt("color",R.color.zise_img);
                edit.commit();
                setColorText();
                break;
            case R.id.oragea_img:
                edit.putInt("color",R.color.orage_img);
                edit.commit();
                setColorText();
                break;
            case R.id.are_img:
                edit.putInt("color",R.color.arg_img);
                edit.commit();
                setColorText();
                break;
            case R.id.pink_img:
                edit.putInt("color",R.color.pink_img);
                edit.commit();
                setColorText();
                break;
            case R.id.cancel:
                alertDialog.dismiss();
                break;
            case R.id.finish:
                initViews(f4_share.getInt("color",colors));
                alertDialog.dismiss();
                EventBus.getDefault().post(new Message_even(colors));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
