package com.m520it.www.slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xmg on 2016/12/30.
 */

public class SlideMenu extends ViewGroup {


    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;
    private int mMenuHeight;
    private int mContentWidth;
    private int mContentHeight;
    private float mStartX;
    private float mStartY;

    public SlideMenu(Context context) {
        super(context);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        if(mSumDx<0){
            mSumDx=0;
        }else if(mSumDx>mMenuWidth){
            mSumDx = mMenuWidth;
        }
        mMenuView.layout(-mMenuWidth+mSumDx,0,0+mSumDx,mMenuHeight);
        mContentView.layout(0+mSumDx,0,mContentWidth+mSumDx,mContentHeight);
    }

    int mSumDx = 0;//X方向上的偏移量

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
                mSumDx+=dx;

                mStartX = newX;
                mStartY = newY;

                break;
            case MotionEvent.ACTION_UP:
                //判断,如果菜单view出来的比较多,就全部展示出来,否则全部收进去
                //如果偏移量大于菜单view宽度的一半,就全部出来,否则全部进去
                if(mSumDx>mMenuWidth/2){
                    mSumDx = mMenuWidth;
                }else{
                    mSumDx = 0;
                }
                break;
        }
        //重新请求布局
        requestLayout();

        return true;
    }
}
