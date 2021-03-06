package zhangtao.bwie.com.demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class MyRecy extends RecyclerView{
    public MyRecy(Context context) {
        super(context);
    }
    public MyRecy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MyRecy(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(expandSpec, heightSpec);
    }
}
