package com.wu.wucheng.xiaoli_wu.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wu.wucheng.xiaoli_wu.MainActivity;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.MyViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Qingligonglve extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener  {
    public static final String TAG = Qingligonglve.class.getSimpleName();
    private RadioButton qingligonglve_zuijin;
    private RadioButton qingligonglve_jingpin;
    private RadioGroup qingligonglve_daohang;
    private ViewPager qingligonglve_viewpage;

    public Qingligonglve() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qingligonglve, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        qingligonglve_zuijin = (RadioButton) view.findViewById(R.id.qingligonglve_zuijin);
        qingligonglve_jingpin = (RadioButton) view.findViewById(R.id.qingligonglve_jingpin);
        qingligonglve_daohang = (RadioGroup) view.findViewById(R.id.qingligonglve_daohang);
        qingligonglve_viewpage = (ViewPager) view.findViewById(R.id.qingligonglve_viewpage);
        List<Fragment> data=new ArrayList<>();
        data.add(new GuanhuaihuatiZuijin());
        data.add(new GuanhuaihuatiZuijin());
        MyViewPageAdapter adapter=new MyViewPageAdapter(getFragmentManager(),data);
        qingligonglve_viewpage.setAdapter(adapter);
        qingligonglve_viewpage.setOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.qingligonglve_zuijin:
                qingligonglve_viewpage.setCurrentItem(0);
                break;
            case R.id.qingligonglve_jingpin:
                qingligonglve_viewpage.setCurrentItem(1);
                break;
        }
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                qingligonglve_daohang.check(R.id.qingligonglve_zuijin);
                break;
            case 1:
                qingligonglve_daohang.check(R.id.qingligonglve_jingpin);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
