package com.wu.wucheng.xiaoli_wu.faxian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.MyViewPageAdapter;
import com.wu.wucheng.xiaoli_wu.fragments.DuanxinmobanOne;

import java.util.ArrayList;
import java.util.List;

public class Duanxinmoban extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private static final String tag = Duanxinmoban.class.getSimpleName();
//    private ListView duanxinmobanList;
//    private String blessUrl = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=bless";//短信模板Url
//    private SharedPreferences sharedPreferences;
//    private String userid;
//    private Handler handler;
    private ViewPager duanxinmoban_viewpage;
    private RadioGroup duanxinmoban_radioGroup;
//    private DuanxinmobanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duanxinmoban);
//        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        initView();
//        initData();
    }

//    private void initData() {
//        userid = sharedPreferences.getString("userid", "1");
//        final String blessParameter = "uid=" + userid;
//        new Thread() {
//
//            @Override
//            public void run() {
//                String json = HttpUtils.getStringByConnection(blessUrl, blessParameter);
//                Log.e(tag, json);
//                Message msg = Message.obtain();
//                msg.what = 6;
//                msg.obj = json;
//                handler.sendMessage(msg);
//            }
//        }.start();
//    }

    private void initView() {
        //回退监听
        ((ImageView) findViewById(R.id.duanxinmoban_back)).setOnClickListener(this);
        //初始化ListView
//        duanxinmobanList = ((ListView) findViewById(R.id.duanxinmoban_list));
        //设置适配器
//        adapter = new DuanxinmobanAdapter(this, null);
//        duanxinmobanList.setAdapter(adapter);
//        handler = new Handler(this);
        List<Fragment> data=new ArrayList<>();
        data.add(new DuanxinmobanOne());
        data.add(new DuanxinmobanOne());
        data.add(new DuanxinmobanOne());
        data.add(new DuanxinmobanOne());
        MyViewPageAdapter adapter=new MyViewPageAdapter(getSupportFragmentManager(),data);
        duanxinmoban_viewpage = (ViewPager) findViewById(R.id.duanxinmoban_viewpage);
        duanxinmoban_viewpage.setAdapter(adapter);
        duanxinmoban_viewpage.setOnPageChangeListener(this);
        duanxinmoban_radioGroup = (RadioGroup) findViewById(R.id.duanxinmoban_radioGroup);
        duanxinmoban_radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.duanxinmoban_back:
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
                duanxinmoban_radioGroup.check(R.id.duanxinmoban_rb1);
                break;
            case 1:
                duanxinmoban_radioGroup.check(R.id.duanxinmoban_rb2);
                break;
            case 2:
                duanxinmoban_radioGroup.check(R.id.duanxinmoban_rb3);
                break;
            case 3:
                duanxinmoban_radioGroup.check(R.id.duanxinmoban_rb4);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.duanxinmoban_rb1:
                duanxinmoban_viewpage.setCurrentItem(0);
                break;
            case R.id.duanxinmoban_rb2:
                duanxinmoban_viewpage.setCurrentItem(1);
                break;
            case R.id.duanxinmoban_rb3:
                duanxinmoban_viewpage.setCurrentItem(2);
                break;
            case R.id.duanxinmoban_rb4:
                duanxinmoban_viewpage.setCurrentItem(3);
                break;
        }
    }

//    @Override
//    public boolean handleMessage(Message msg) {
//        switch (msg.what) {
//            case 6:
//                String json = (String) msg.obj;
//                Gson gson = new Gson();
//                DuanxinmobanData duanxinmobanData = gson.fromJson(json, DuanxinmobanData.class);
//                List<DuanxinmobanData.BlessBean> data = duanxinmobanData.getBless();
//                adapter.addRes(data);
//                break;
//        }
//        return true;
//    }
}
