package zhangtao.bwie.com.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ZhangTAO on 2017/12/14.
 */

public class MyAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> flist;
    public MyAdapter(FragmentManager supportFragmentManager, List<Fragment> flist) {
        super(supportFragmentManager);
        this.flist = flist;
    }

    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }
}
