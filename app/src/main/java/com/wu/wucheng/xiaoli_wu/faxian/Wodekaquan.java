package com.wu.wucheng.xiaoli_wu.faxian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.MyViewPageAdapter;
import com.wu.wucheng.xiaoli_wu.fragments.WodekaquanLishi;
import com.wu.wucheng.xiaoli_wu.fragments.WodekaquanWo;

import java.util.ArrayList;
import java.util.List;

public class Wodekaquan extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, ViewPager.OnPageChangeListener {

    private ImageView wodekaquan_back;
    private RadioButton wodekaquan_wode;
    private RadioButton wodekaquan_lishi;
    private RadioGroup wodekaquan_daohang;
    private ViewPager wodekaquan_viewpage;
    private FragmentManager supportFragmentManager;
    private List<Fragment> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodekaquan);
        initView();

    }

    private void initView() {
        wodekaquan_back = (ImageView) findViewById(R.id.wodekaquan_back);
        wodekaquan_back.setOnClickListener(this);
        wodekaquan_daohang = (RadioGroup) findViewById(R.id.wodekaquan_daohang);
        wodekaquan_daohang.setOnCheckedChangeListener(this);
        wodekaquan_wode = (RadioButton) findViewById(R.id.wodekaquan_wode);
        wodekaquan_lishi = (RadioButton) findViewById(R.id.wodekaquan_lishi);
        wodekaquan_viewpage = (ViewPager) findViewById(R.id.wodekaquan_viewpage);
        supportFragmentManager = getSupportFragmentManager();
        data=new ArrayList<>();
        data.add(new WodekaquanWo());
        data.add(new WodekaquanLishi());
        FragmentManager fm=getSupportFragmentManager();
        MyViewPageAdapter adapter=new MyViewPageAdapter(fm,data);
        wodekaquan_viewpage.setAdapter(adapter);
        wodekaquan_viewpage.setOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.wodekaquan_wode:
                wodekaquan_viewpage.setCurrentItem(0);
                break;
            case R.id.wodekaquan_lishi:
                wodekaquan_viewpage.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wodekaquan_back:
                finish();
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
                wodekaquan_daohang.check(R.id.wodekaquan_wode);
                break;
            case 1:
                wodekaquan_daohang.check(R.id.wodekaquan_lishi);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
