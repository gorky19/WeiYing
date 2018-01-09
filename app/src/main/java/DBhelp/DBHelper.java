package DBhelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import zhangtao.bwie.com.demo.gen.CatatoryDao;
import zhangtao.bwie.com.demo.gen.DaoMaster;
import zhangtao.bwie.com.demo.gen.DaoSession;
import zhangtao.bwie.com.demo.gen.LishiBeanDao;
import zhangtao.bwie.com.demo.gen.ShouBeanDao;

/**
 * Created by ZhangTAO on 2017/12/8.
 */

public class DBHelper {
    private static volatile DBHelper instance;
    private final CatatoryDao dao;
    private final ShouBeanDao shoubean;
    private final LishiBeanDao lishibean;

    private DBHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster
                .DevOpenHelper(context, "data_lishi", null);
        DaoMaster.DevOpenHelper helper2 = new DaoMaster
                .DevOpenHelper(context, "shou_data", null);
        DaoMaster.DevOpenHelper helper3 = new DaoMaster
                .DevOpenHelper(context, "lishi_data", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        SQLiteDatabase db2 = helper2.getWritableDatabase();
        SQLiteDatabase db3 = helper3.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoMaster daoMaster2 = new DaoMaster(db2);
        DaoMaster daoMaster3 = new DaoMaster(db3);
        DaoSession daoSession = daoMaster.newSession();
        DaoSession daoSession2 = daoMaster2.newSession();
        DaoSession daoSession3 = daoMaster3.newSession();
        dao = daoSession.getCatatoryDao();
        shoubean = daoSession2.getShouBeanDao();
        lishibean = daoSession3.getLishiBeanDao();
    }
    public static DBHelper getInstance(Context context) {
        if(instance == null) {
            synchronized (DBHelper.class) {
                if(null == instance) {
                    instance = new DBHelper(context);
                }
            }
        }
            return instance;
    }
    public CatatoryDao getDao() {
        return dao;
    }
    public ShouBeanDao getShoubean() {
        return shoubean;
    }
    public LishiBeanDao getLishibean() {
        return lishibean;
    }
}
