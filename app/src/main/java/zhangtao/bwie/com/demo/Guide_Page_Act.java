package zhangtao.bwie.com.demo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import ImmerSionUtil.ImmersionUtil;

/**
 * Created by ZhangTAO on 2017/12/29.
 */

public class Guide_Page_Act extends AppCompatActivity{
    private ImageView img;
    private SharedPreferences guide_share;
    private SharedPreferences.Editor guide_edit;
    private int time = 3;
    private Handler ler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_act);
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        guide_share = getSharedPreferences("datas", Context.MODE_PRIVATE);
        guide_edit = guide_share.edit();
        img = (ImageView) findViewById(R.id.imgss);
        ObjectAnimator animX = ObjectAnimator.ofFloat(img, "scaleX", 1f, 1.3f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(img, "scaleY", 1f, 1.3f);
        animX.setDuration(3000);
        animY.setDuration(3000);
        animX.start();
        animY.start();
        ler.postDelayed(runs,0);
        if(guide_share.getInt("key",0)%5 == 0) {
            img.setBackgroundResource(R.drawable.back1);
        }else if(guide_share.getInt("key",0)%5 == 1) {
            img.setBackgroundResource(R.drawable.back2);
        }else if(guide_share.getInt("key",0)%5 == 2) {
            img.setBackgroundResource(R.drawable.back3);
        }else if(guide_share.getInt("key",0)%5 == 3) {
            img.setBackgroundResource(R.drawable.back4);
        }else if(guide_share.getInt("key",0)%5 == 4) {
            img.setBackgroundResource(R.drawable.back5);
        }
        guide_edit.putInt("key",guide_share.getInt("key",0)+1);
        guide_edit.commit();
    }
    Runnable runs = new Runnable() {
        @Override
        public void run() {
            time--;
            ler.postDelayed(this,1200);
            if(time == 0) {
                Intent intent = new Intent(Guide_Page_Act.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
