package com.m520it.www.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xmg on 2016/12/29.
 */

public class MyFragment extends Fragment {

    public static String KEY = "position";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item, container, false);
        TextView tv = (TextView) inflate.findViewById(R.id.tv);
        Bundle arguments = getArguments();
        int anInt = arguments.getInt(KEY);
        tv.setText("引导页"+anInt);
        return inflate;
    }
}
