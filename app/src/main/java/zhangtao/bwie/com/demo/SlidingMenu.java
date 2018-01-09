package zhangtao.bwie.com.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by ZhangTAO on 2017/12/30.
 */

public class SlidingMenu extends HorizontalScrollView{
    /**
     * 屏幕宽度
     */
    private int mScreenWidth;

    /**
     * dp
     */
    private int mMenuRightPadding = 35;

    /**
     * 菜单的宽度
     */
    private int mMenuWidth;
    private int mHalfMenuWidth;

    private boolean once;

    private boolean isOpen;
    private ViewGroup menu;
    private ViewGroup contents;
    private float rotationY;
    private float num = 10f;

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }
    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu,defStyle,0);
        int indexCount = a.getIndexCount();
        for(int i=0;i<indexCount;i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    //默认40
                    a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,35,
                            getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 显示的设置一个宽度
         */
        if(!once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) wrapper.getChildAt(0);
            contents = (ViewGroup) wrapper.getChildAt(1);
            //dp to px
            mMenuRightPadding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,mMenuRightPadding,contents.getResources().getDisplayMetrics()
            );
            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mHalfMenuWidth = mMenuWidth / 2;
            menu.getLayoutParams().width = mMenuWidth;
            contents.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed) {
            //将菜单隐藏
            this.scrollTo(mMenuWidth,0);
            once = true;
        }
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if(isOpen)
            return ;
            this.smoothScrollTo(0, 0);
            isOpen = true;

    }
    /**
     * 关闭菜单
     */
    public void closeMenu()
    {
        if (isOpen)
        {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

    /**
     * 切换菜单状态
     */
    public void toggle()
    {
        if (isOpen)
        {
            closeMenu();
        } else
        {
            openMenu();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if(scrollX > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth,0);
                    isOpen = false;
                }else {
                    this.smoothScrollTo(0,0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //菜单已显示的宽度
        float scale = l * 1.0f / mMenuWidth;
        //内容区域缩放比例
        float rightScale = 0.8f + scale * 0.2f;
        //菜单的缩放比例
        float leftScale = 1+0.7f * scale;
        //菜单的透明度比例
        float aplha = 0.6f + 0.4f * (1-scale);
        //菜单的x方向偏移量：
        //倾斜量
        rotationY =  num - scale * 10f;
        Log.d("zzz",rotationY+"");
        //菜单动画
        ViewHelper.setPivotX(menu,-185);
        ViewHelper.setScaleX(menu, leftScale);
        ViewHelper.setScaleY(menu, leftScale);
        ViewHelper.setAlpha(menu, aplha);
        //内容动画
        ViewHelper.setPivotX(contents, 0);
        ViewHelper.setPivotY(contents, contents.getHeight() / 2);
        ViewHelper.setScaleX(contents, rightScale);
        ViewHelper.setScaleY(contents, rightScale);
        ViewHelper.setRotationY(contents,-rotationY);
//        if(rotationY>0) {
//            ViewHelper.setRotationY(contents,-rotationY);
//        }else if(rotationY==0){
//            ViewHelper.setRotationY(contents,rotationY);
//        }

    }
}
