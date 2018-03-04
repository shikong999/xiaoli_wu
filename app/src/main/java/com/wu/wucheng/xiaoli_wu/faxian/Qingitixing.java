package com.wu.wucheng.xiaoli_wu.faxian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.SysExitUtil;
import com.wu.wucheng.xiaoli_wu.adapters.QinglitixingAdapterCOPY;
import com.wu.wucheng.xiaoli_wu.model.qinglitixingModel.Remind;
import com.wu.wucheng.xiaoli_wu.model.qinglitixingModel.RemindItem;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.util.List;

public class Qingitixing extends AppCompatActivity implements View.OnClickListener ,Handler.Callback{
    {
        SysExitUtil.activityList.add(this);
    }
    private ListView qinglitixingListView;
    private SharedPreferences sharedPreferences;
    private String userid;

    private String qingitixingUrl="http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=remind";
    private Handler handler;
    private static final String tag=Qingitixing.class.getSimpleName();
    private QinglitixingAdapterCOPY adapter;
    private String json;
    //    private QinglitixingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qingitixing);
        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        initView();
        initData();
    }

    private void initData() {
        userid = sharedPreferences.getString("userid", "1");
        final String parameter = "uid="+userid;
        //下载json
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtils.getStringByConnection(qingitixingUrl, parameter);

                Message msg = Message.obtain();
                msg.what = 3;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        handler = new Handler(this);
        //回退键监听
        ((ImageView) findViewById(R.id.qinglitixing_back)).setOnClickListener(this);
        //添加监听
        ((ImageView) findViewById(R.id.qinglitixing_add)).setOnClickListener(this);
        //初始化listView
        qinglitixingListView = ((ListView) findViewById(R.id.qinglitixing_List));
//        adapter = new QinglitixingAdapter(this, R.layout.qinglitixing_item,null);
        adapter = new QinglitixingAdapterCOPY(this, null,userid,handler);
        qinglitixingListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qinglitixing_back:
                finish();
                break;
            case R.id.qinglitixing_add:
//                Toast.makeText(this,"添加",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,QinglitixingAdd.class));
                finish();
                break;
        }
    }

    //handler获取json
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 3:
                json = (String) msg.obj;
                Log.e(tag,json);
                //gson解析
                Gson gson=new Gson();
                Remind remind = gson.fromJson(json, Remind.class);
                List<RemindItem> data = remind.getRemindlist();
                adapter.updateRes(data);
                break;
            case 4:
                Toast.makeText(this,"删除成功!",Toast.LENGTH_SHORT).show();
                initData();
                adapter.updateRes(getData());
                break;
        }
        return true;
    }

    private List<RemindItem> getData() {
        Gson gson=new Gson();
        Remind remind = gson.fromJson(json, Remind.class);
        List<RemindItem> data = remind.getRemindlist();
        return data;
    }
}
