package com.bing.lan.viewpagerdemo;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xmg on 2016/12/29.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private String[] mStrings;

    public ViewPagerAdapter(String[] resIds) {
        this.mStrings = resIds;
    }

    //设置有多少页
    @Override
    public int getCount() {
        return mStrings.length;
    }


    //基本固定
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
//        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        通过打气筒,准备一个view出来,将其返回出去
//        View.inflate()
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.item,
                container, false);
        TextView tv = (TextView) inflate.findViewById(R.id.tv);
        tv.setText(mStrings[position]);
        if(position%2==0){
            tv.setBackgroundColor(Color.BLUE);
        }else{
            tv.setBackgroundColor(Color.YELLOW);
        }
        //返回之前还需要将打气筒生成的要展示出来的view,添加到VIewPager中
        container.addView(inflate);

        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
