package com.bing.lan.layoutview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xmg on 2016/12/29.
 */

public class VerticalLayoutView extends ViewGroup {


    public VerticalLayoutView(Context context) {
        super(context);
    }

    public VerticalLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //AT_MOST
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);

        //测量一下子控件,不然拿不到子控件的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        switch (modeW) {
            case MeasureSpec.AT_MOST:
                int maxWidth = getChildMaxWidth();
                sizeW = maxWidth;
                break;
            case MeasureSpec.EXACTLY:

                break;
        }
        switch (modeH) {
            case MeasureSpec.AT_MOST:
                int sum = getChildSumHeight();
                sizeH = sum;
                break;
            case MeasureSpec.EXACTLY:

                break;
        }
        setMeasuredDimension(sizeW, sizeH);
    }

    private int getChildSumHeight() {
        //遍历子控件
        int sum = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
//            int width = childAt.getWidth();
            int height = childAt.getMeasuredHeight();
            sum += height;
            Log.d("xmg", "height: " + height);
        }
        return sum;
    }

    private int getChildMaxWidth() {
        //遍历子控件,取最大值出来
        int max = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
//            int width = childAt.getWidth();
            int width = childAt.getMeasuredWidth();
//            Log.d("xmg", "getChildMaxWidth: " + width);
            if (max < width) {
                max = width;
            }
        }
        return max;
    }

    //布局相关的方法
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历子控件,将子控件布局在各自的位置上去
        int childCount = getChildCount();
        int totalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int height = childAt.getMeasuredHeight();
            childAt.layout(0,totalHeight,childAt.getMeasuredWidth(),totalHeight+height);
            totalHeight+=height;
        }
    }
}
