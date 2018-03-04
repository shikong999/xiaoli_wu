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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.XiangceAdapter;
import com.wu.wucheng.xiaoli_wu.model.xiangceModel.Fengmian;
import com.wu.wucheng.xiaoli_wu.model.xiangceModel.XiangceData;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class Xiangce extends AppCompatActivity implements View.OnClickListener, Handler.Callback, AdapterView.OnItemClickListener {

    private SharedPreferences sharedPreferences;
    private String xiangceUrl = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=album";
    private GridView xiangceList;
    private Handler handler;
    private XiangceAdapter adapter;
    private static final String tag=Xiangce.class.getSimpleName();
    private String userid;
    private List<Fengmian> fengmianList;
    private String parameter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangce);
        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        initView();
        iniData();
    }

    private void iniData() {
        userid = sharedPreferences.getString("userid", "1");
        parameter = "uid="+userid;
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtils.getStringByConnection(xiangceUrl, parameter);
                Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        //回退键设置监听
        ImageView xiangceBack = ((ImageView) findViewById(R.id.xiangce_back));
        xiangceBack.setOnClickListener(this);
        //发布按钮设置监听
        ((TextView) findViewById(R.id.xiangce_fabu)).setOnClickListener(this);
        //
        xiangceList = ((GridView) findViewById(R.id.xiangce_List));
        String dir = getSharedPreferences("user", MODE_PRIVATE).getString("dir", "");
        adapter = new XiangceAdapter(this, R.layout.xiangce_item, null,dir);
        xiangceList.setAdapter(adapter);
        xiangceList.setOnItemClickListener(this);
        handler = new Handler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiangce_back:
                finish();
                break;
            case R.id.xiangce_fabu:
                startActivity(new Intent(this,XiangceFabu.class));
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 2:
                List<Fengmian> data = new ArrayList<>();
                String json = (String) msg.obj;
                Gson gson = new Gson();
                XiangceData xiangceData = gson.fromJson(json, XiangceData.class);
                fengmianList = xiangceData.getList();
                for (int i = 0; i < fengmianList.size(); i++) {
                    Fengmian fengmian = fengmianList.get(i);
                    Log.e(tag,fengmian.getFengmian());
                    data.add(fengmian);
                }
                adapter.addRes(data);
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"点击--"+position,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,XiangceDetail.class);
        Fengmian fengmian = fengmianList.get(position);
        intent.putExtra("fengmian",fengmian);
        startActivity(intent);
    }
}
