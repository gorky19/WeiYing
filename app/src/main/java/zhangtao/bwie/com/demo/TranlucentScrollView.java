package zhangtao.bwie.com.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by ZhangTAO on 2017/12/29.
 */

public class TranlucentScrollView extends ScrollView{
    //渐变的视图
//    private View transView;
//    //渐变颜色
//    private int transColor = Color.WHITE;
//    //渐变视图高度
//    private int transViewHeight = 0;
//    //渐变结束位置
//    private int transEndY = 0;
    private OnScrollChangedListener mscroll;
    public TranlucentScrollView(Context context) {
        super(context);
    }
    public TranlucentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TranlucentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mscroll != null) {
            int scrollY = getScrollY();
            int scroll_height = getContext().getResources().getDisplayMetrics().heightPixels;
            if(scrollY <= scroll_height / 2.8f) {
                //alpha=滑出去的高度/(screen_height/3f)
                mscroll.onScrollChanged(0+scrollY / (scroll_height / 3.2f));
            }
        }
    }
    /**
     * 设置渐变视图
     *
     * @param transView       渐变的视图
     * @param transColor      渐变颜色
     * @param transViewHeight 渐变视图高度
     * @param transEndY       渐变结束位置
     */
//    public void setTransView(View transView, @ColorInt int transColor, int transViewHeight, int transEndY) {
//        this.transView = transView;
//        //初始视图-透明
//        this.transView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, 1));
//        this.transViewHeight = transViewHeight;
//        this.transEndY = transEndY;
//        this.transColor = transColor;
//        if (transViewHeight > transEndY) {
//            throw new RuntimeException("transViewHeight 不得大于 transEndY .. ");
//        }
//    }
/**
 * 获取透明度
 **/
//private int getTransAlpha() {
//    float scrollY = getScrollY();
//    float viewOffsetY = transEndY - transViewHeight;
//    float offset = 1 - Math.max((viewOffsetY - scrollY) / viewOffsetY, 0f);
//    //透明度
//    return Math.abs((int) (offset * 255));
//}
    public void setOnScrollChangedListener(OnScrollChangedListener onScroll) {
        this.mscroll = onScroll;
    }
    public interface OnScrollChangedListener {
        void onScrollChanged(float alpha);
    }
}
