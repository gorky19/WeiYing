package zhangtao.bwie.com.demo;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by ZhangTAO on 2018/1/8.
 */

public class PgyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        PgyCrashManager.register(this);
    }
}
