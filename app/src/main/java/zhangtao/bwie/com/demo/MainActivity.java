package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedback;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import Frag.Frag1;
import Frag.Frag2;
import Frag.Frag3;
import Frag.Frag4;
import ImmerSionUtil.ImmersionUtil;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private NoScrollViewPager vps;
    private JPTabBar jpbar;
    private List<Fragment> flist = new ArrayList<>();
    private MyAdapter myAdapter;
    private RelativeLayout fuli_lay;
    private AlertDialog alertDialog;
    private RelativeLayout themes;
    private CircleImageView blue;
    private CircleImageView red;
    private CircleImageView yellow;
    private CircleImageView green;
    private CircleImageView black;
    private CircleImageView zise;
    private CircleImageView oragea;
    private CircleImageView are;
    private CircleImageView pink;
    private LinearLayout id_menu;
    private int colors = R.color.red_img;
    private TextView cancel;
    private TextView finish;
    private SharedPreferences color_share;
    private SharedPreferences.Editor edit;
    private View inflate;
    private RelativeLayout shou;
    private RelativeLayout about;
    private RelativeLayout share;
    private RelativeLayout message;
    private RelativeLayout set;
    private View inflate2;
    private TextView csdn;
    private TextView csdn_text;
    private TextView git_hub_text;
    private TextView git_hub;
    private TextView about_finish;
    private ImageView me_icons;
    private TextView about_texts;
    private AlertDialog alertDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        color_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        edit = color_share.edit();
        initView();
        setOnClick();
        int color = color_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        myAdapter = new MyAdapter(getSupportFragmentManager(),flist);
        vps.setAdapter(myAdapter);
        vps.setScroll(false);
        jpbar.setContainer(vps);
        PgyCrashManager.register(this);
    }
    private void initView() {
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        flist.add(new Frag1());
        flist.add(new Frag2());
        flist.add(new Frag3());
        flist.add(new Frag4());
        jpbar = (JPTabBar) findViewById(R.id.jpbar);
        fuli_lay = (RelativeLayout) findViewById(R.id.fuli);
        themes = (RelativeLayout) findViewById(R.id.themes);
        id_menu = (LinearLayout) findViewById(R.id.id_menu);
        shou = (RelativeLayout) findViewById(R.id.shou);
        vps = (NoScrollViewPager) findViewById(R.id.vps);
        about = (RelativeLayout) findViewById(R.id.about);
        share = (RelativeLayout) findViewById(R.id.share);
        message = (RelativeLayout) findViewById(R.id.massage);
        set = (RelativeLayout) findViewById(R.id.set);
    }
    @Subscribe
    public void getMessageEvent(Message_even event) {
        id_menu.setBackgroundColor(getResources().getColor(event.getColor()));
    }
    private void showThemes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.create();
        inflate = View.inflate(this, R.layout.theme_zt, null);
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
    private void setOnClick() {
        fuli_lay.setOnClickListener(this);
        themes.setOnClickListener(this);
        shou.setOnClickListener(this);
        set.setOnClickListener(this);
        message.setOnClickListener(this);
        about.setOnClickListener(this);
        share.setOnClickListener(this);
    }
    private void setColorText2() {
        about_texts.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
        csdn_text.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
        csdn.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
        git_hub.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
        git_hub_text.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
        about_finish.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
    }
    private void showAlert2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog2 = builder.create();
        inflate2 = View.inflate(this, R.layout.about_me_lay, null);
        csdn = inflate2.findViewById(R.id.csdn);
        csdn_text = inflate2.findViewById(R.id.csdn_text);
        git_hub_text = inflate2.findViewById(R.id.git_hub_text);
        git_hub = inflate2.findViewById(R.id.git_hub);
        about_finish = inflate2.findViewById(R.id.about_finish);
        me_icons = inflate2.findViewById(R.id.me_icons);
        about_texts = inflate2.findViewById(R.id.about_texts);
        about_finish = inflate2.findViewById(R.id.about_finish);
        csdn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        git_hub.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        csdn.getPaint().setAntiAlias(true);
        git_hub.getPaint().setAntiAlias(true);
        csdn.setOnClickListener(this);
        git_hub.setOnClickListener(this);
        about_finish.setOnClickListener(this);
        setColorText2();
        alertDialog2.setView(inflate2);
        alertDialog2.show();
    }
    private void initViews(int color) {
        this.colors = color;
        id_menu.setBackgroundColor(getResources().getColor(colors));
    }
    private void setColorText() {
        finish.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
        cancel.setTextColor(getResources().getColor(color_share.getInt("color",colors)));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fuli:
                Intent intent = new Intent(MainActivity.this, f3_Frag3s.class);
                startActivity(intent);
                break;
            case R.id.shou:
                Intent intent2 = new Intent(MainActivity.this, Shou_Act.class);
                startActivity(intent2);
                break;
            case R.id.about:
                showAlert2();
                break;
            case R.id.share:
                Intent intent_share=new Intent(Intent.ACTION_SEND);
                intent_share.putExtra(Intent.EXTRA_TEXT,"发现一个看片神器"+"\n\n"+"https://www.pgyer.com/ztwei");
                intent_share.setType("text/plain");
                startActivity(Intent.createChooser(intent_share,"share"));
                break;
            case R.id.set:
                Intent intent3 = new Intent(MainActivity.this, Setup_Act.class);
                startActivity(intent3);
                break;
            case R.id.massage:
                PgyFeedbackShakeManager.register(MainActivity.this);
                PgyFeedback.getInstance().showDialog(MainActivity.this);
                break;
            case R.id.themes:
                showThemes();
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
                initViews(color_share.getInt("color",colors));
                alertDialog.dismiss();
                EventBus.getDefault().post(new Message_even(colors));
                break;
            case R.id.csdn:
                String csdn_url = "http://blog.csdn.net/Gorky_19?ref=toolbar";
                Intent intents = new Intent();
                intents.setAction(Intent.ACTION_VIEW);
                intents.setData(Uri.parse(csdn_url));
                startActivity(intents);
                alertDialog2.dismiss();
                break;
            case R.id.git_hub:
                String git_url ="http://github.com/GeekGhost/Ghost";
                Intent intentss = new Intent();
                intentss.setAction(Intent.ACTION_VIEW);
                intentss.setData(Uri.parse(git_url));
                startActivity(intentss);
                alertDialog2.dismiss();
                break;
            case R.id.about_finish:
                alertDialog2.dismiss();
                break;
        }
    }
    @Titles
    private static final String[] titles = {"精选","专题","发现","我的"};
    @SeleIcons
    private static final int[] selecticons = {R.drawable.found_select,R.drawable.special_select,R.drawable.fancy_select,R.drawable.my_select};
    @NorIcons
    private static final int[] noricons = {R.drawable.found,R.drawable.special,R.drawable.fancy,R.drawable.my};

    @Override
    protected void onPause() {
        super.onPause();
        PgyFeedbackShakeManager.unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
