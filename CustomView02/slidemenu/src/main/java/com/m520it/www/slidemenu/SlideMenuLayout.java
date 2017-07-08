package com.bing.lan.slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xmg on 2016/12/30.
 */

public class SlideMenuLayout extends ViewGroup {


    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;
    private int mMenuHeight;
    private int mContentWidth;
    private int mContentHeight;
    private float mStartX;
    private float mStartY;
    private int mScrollX;//当前控件锁滚动到的X轴位置
    private Scroller mScroller;

    public SlideMenuLayout(Context context) {
        super(context);
    }

    public SlideMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //先测量一下子控件,拿它们的宽高
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //开始去获得宽高
        mMenuView = getChildAt(0);
        mContentView = getChildAt(1);
        mMenuWidth = mMenuView.getMeasuredWidth();
        mMenuHeight = mMenuView.getMeasuredHeight();
        mContentWidth = mContentView.getMeasuredWidth();
        mContentHeight = mContentView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //边界
//        if(mSumDx<0){
//            mSumDx=0;
//        }else if(mSumDx>mMenuWidth){
//            mSumDx = mMenuWidth;
//        }
//        mMenuView.layout(-mMenuWidth+mSumDx,0,0+mSumDx,mMenuHeight);
//        mContentView.layout(0+mSumDx,0,mContentWidth+mSumDx,mContentHeight);
        mMenuView.layout(-mMenuWidth,0,0,mMenuHeight);
        mContentView.layout(0,0,mContentWidth,mContentHeight);
    }

//    int mSumDx = 0;//X方向上的偏移量

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();

        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float newX = event.getX();
                float newY = event.getY();

                //计算偏移量
                float dx = newX - mStartX;
                float dy = newY - mStartY;
                //scrollBy、scrollTo方法中的坐标系是反的
                scrollBy((int) -dx,0);
                //获得当前滚动到的这个位置坐标
                mScrollX = getScrollX();
                Log.d("xmg", "onTouchEvent: scrollX: " + mScrollX);
                //判断是否超过边界
                if(mScrollX >0){
                    //超过就使用scrollTo方法滚动边界处
                    scrollTo(0,0);
                }else if(mScrollX <-mMenuWidth){
                    scrollTo(-mMenuWidth,0);
                }
                mStartX = newX;
                mStartY = newY;

                break;
            case MotionEvent.ACTION_UP:
                //根据当前滚动到的位置来进行判断,是否要收进去或完全展示出来
                mScrollX = getScrollX();
                //如果当前位置大于-菜单view的宽度的一半,就认为要收进去,否则,就全部展示出来
                if(mScrollX>-mMenuWidth/2){
                    //说明:scrollTo方法是不具备动画效果的,我们采用Scroller来做动画
//                    mScrollX + ? = 0;
                    //? = 0 - mScrollX;
                    mScroller.startScroll(mScrollX,0,0-mScrollX,0);
//                    scrollTo(0,0);
                }else{
                    //说明:scrollTo方法是不具备动画效果的,我们采用Scroller来做动画
//                    mScrollX + ? = -mMenuWidth;
                    mScroller.startScroll(mScrollX,0,-mMenuWidth-mScrollX,0);
//                    scrollTo(-mMenuWidth,0);
                }
                //重绘
                invalidate();
                break;
        }

        return true;
    }

//    float startX2 = 0;
//    float startY2 = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //判断一下,如果移动时,是左右移动,就拦截,return true
        int eventAction = ev.getAction();

        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float newX = ev.getX();
                float newY = ev.getY();

//                float dx = newX - mStartX;
                float dx = newX - mStartX;
                if(Math.abs(dx)>1){
                    //认为是左右移动,就进行拦截
                    return true;
                }

//                startX = newX;
//                startY = newY;

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    //重绘的时候,Scroller中会有250毫秒时间执行动画
    //Scroller 会在250毫秒根据我们传递到构造方法里的起点和偏移量
    //100  250  101 102 350
    //计算出当前时间控件应该在的位置   scrollTo()

    //计算滚动的方法
    @Override
    public void computeScroll() {
        super.computeScroll();
        //每次重绘都会调用该方法,
        // 此时,我就可在该方法中去计算当前控件在Scroller动画中所处的位置
        //必须要加对应的判断,不然地柜调用停不下来
        if(mScroller.computeScrollOffset()){
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            //通过 scrollTo()方法让控件真正的动起来
            scrollTo(currX,currY);
            //让控件重新绘制
            invalidate();
        }

    }
}
