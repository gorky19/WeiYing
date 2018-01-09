package zhangtao.bwie.com.demo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedback;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;

import ImmerSionUtil.ImmersionUtil;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by ZhangTAO on 2018/1/8.
 */

public class Setup_Act extends SwipeBackActivity implements View.OnClickListener{
    private RelativeLayout tuijian;
    private RelativeLayout clean;
    private RelativeLayout about;
    private RelativeLayout managers;
    private TextView cachesize;
    private SharedPreferences setup_share;
    private int colors = R.color.red_img;
    private RelativeLayout tools;
    private AlertDialog alertDialog;
    private View inflate;
    private TextView fuzhi;
    private TextView set_finish;
    private ClipData copy_text;
    private ClipboardManager manager;
    private TextView one;
    private TextView two;
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
        setContentView(R.layout.setup_layout);
        initView();
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        setup_share = getSharedPreferences("color_data", Context.MODE_PRIVATE);
        setOnClick();
        int color = setup_share.getInt("color", colors);
        if(color != colors) {
            initViews(color);
        }else {
            initViews(colors);
        }
        leftanim();
        PgyCrashManager.register(this);
    }
    private void initViews(int color) {
        this.colors = color;
        tools.setBackgroundColor(getResources().getColor(colors));
    }
    private void initView() {
        manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        tools = (RelativeLayout) findViewById(R.id.toolbar_zt);
        tuijian = (RelativeLayout) findViewById(R.id.tuijian_lay);
        clean = (RelativeLayout) findViewById(R.id.cleancache_lay);
        about = (RelativeLayout) findViewById(R.id.about_me_lay);
        managers = (RelativeLayout) findViewById(R.id.manager_return_lay);
        cachesize = (TextView) findViewById(R.id.cachesize);
        String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
        cachesize.setText(totalCacheSize);
    }
    private void leftanim() {
        setSwipeBackEnable(true);
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
    private void setOnClick() {
        tuijian.setOnClickListener(this);
        about.setOnClickListener(this);
        clean.setOnClickListener(this);
        managers.setOnClickListener(this);
    }
    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.create();
        inflate = View.inflate(this, R.layout.tuijian, null);
        fuzhi = inflate.findViewById(R.id.fuzhi);
        set_finish = inflate.findViewById(R.id.setup_finish);
        one = inflate.findViewById(R.id.tui_text_one);
        two = inflate.findViewById(R.id.tui_text_two);
        fuzhi.setOnClickListener(this);
        set_finish.setOnClickListener(this);
        setColorText();
        alertDialog.setView(inflate);
        alertDialog.show();
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
    private void setColorText() {
        fuzhi.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        set_finish.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        one.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        two.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
    }
    private void setColorText2() {
        about_texts.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        csdn_text.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        csdn.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        git_hub.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        git_hub_text.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
        about_finish.setTextColor(getResources().getColor(setup_share.getInt("color",colors)));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fuzhi:
                //创建一个新的文本clip对象
                copy_text = ClipData.newPlainText("sim", "http://github.com/GeekGhost/Ghost");
                //把clip对象放在剪贴板中
                manager.setPrimaryClip(copy_text);
                Toast.makeText(Setup_Act.this, "已复制到粘贴板!",
                        Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                break;
            case R.id.setup_finish:
                alertDialog.dismiss();
                break;
            case R.id.tuijian_lay:
                showAlert();
                break;
            case R.id.cleancache_lay:
                DataCleanManager.clearAllCache(this);
                String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
                cachesize.setText(totalCacheSize);
                Toast.makeText(Setup_Act.this,"已清除缓存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_me_lay:
                showAlert2();
                break;
            case R.id.manager_return_lay:
                PgyFeedbackShakeManager.register(Setup_Act.this);
                PgyFeedback.getInstance().showDialog(Setup_Act.this);
                break;
            case R.id.csdn:
                String csdn_url = "http://blog.csdn.net/Gorky_19?ref=toolbar";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(csdn_url));
                startActivity(intent);
                alertDialog2.dismiss();
                break;
            case R.id.git_hub:
                String git_url ="http://github.com/GeekGhost/Ghost";
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse(git_url));
                startActivity(intent2);
                alertDialog2.dismiss();
                break;
            case R.id.about_finish:
                alertDialog2.dismiss();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        PgyCrashManager.unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
