package com.wu.wucheng.xiaoli_wu.faxian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.SysExitUtil;
import com.wu.wucheng.xiaoli_wu.adapters.MyViewPageAdapter;
import com.wu.wucheng.xiaoli_wu.fragments.GuanhuaihuatiJingpin;
import com.wu.wucheng.xiaoli_wu.fragments.GuanhuaihuatiZuijin;

import java.util.ArrayList;
import java.util.List;

public class Guanhuaihuati extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ImageView guanhuaihuati_back;
    private RadioButton guanhuaihuati_zuijin;
    private RadioButton guanhuaihuati_jingpin;
    private RadioGroup guanhuaihuati_daohang;
    private ViewPager guanhuaihuati_viewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanhuaihuati);
        initView();
    }

    private void initView() {
        guanhuaihuati_back = (ImageView) findViewById(R.id.guanhuaihuati_back);
        guanhuaihuati_back.setOnClickListener(this);
        guanhuaihuati_zuijin = (RadioButton) findViewById(R.id.guanhuaihuati_zuijin);
        guanhuaihuati_jingpin = (RadioButton) findViewById(R.id.guanhuaihuati_jingpin);
        guanhuaihuati_daohang = (RadioGroup) findViewById(R.id.guanhuaihuati_daohang);
        guanhuaihuati_daohang.setOnCheckedChangeListener(this);
        guanhuaihuati_viewpage = (ViewPager) findViewById(R.id.guanhuaihuati_viewpage);
        List<Fragment> data=new ArrayList<>();
        data.add(new GuanhuaihuatiZuijin());
        data.add(new GuanhuaihuatiZuijin());
        MyViewPageAdapter adapter=new MyViewPageAdapter(getSupportFragmentManager(),data);
        guanhuaihuati_viewpage.setAdapter(adapter);
        guanhuaihuati_viewpage.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guanhuaihuati_back:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.guanhuaihuati_zuijin:
                guanhuaihuati_viewpage.setCurrentItem(0);
                break;
            case R.id.guanhuaihuati_jingpin:
                guanhuaihuati_viewpage.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                guanhuaihuati_daohang.check(R.id.guanhuaihuati_zuijin);
                break;
            case 1:
                guanhuaihuati_daohang.check(R.id.guanhuaihuati_jingpin);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
