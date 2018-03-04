package com.wu.wucheng.xiaoli_wu.faxian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

public class QinglitixingAdd extends AppCompatActivity implements View.OnClickListener ,Handler.Callback{

    private ImageView qinglitixingAddBack;
    private TextView qinglitixingAddOk;
    private EditText qinglitixingAddTime;
    private static final String tag=QinglitixingAdd.class.getSimpleName();
    private long timeInMillis;
    private EditText qinglitixingAddTitle;
    private EditText qinglitixingAddRepeat;
    private EditText qinglitixingAddRemind;
    private java.lang.String qinglitixingAddUrl="http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=addremind";
    private SharedPreferences sharedPreferences;
    private Handler handler;
    private LinearLayout qinglitixingAddRepeatSelectItem;
    private String repeat="0";//缓存重复信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qinglitixing_add);
        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        initView();
    }

    private void initView() {
        //回退监听
        qinglitixingAddBack = (ImageView) findViewById(R.id.qinglitixing_add_back);
        qinglitixingAddBack.setOnClickListener(this);
        //确定监听
        qinglitixingAddOk = (TextView) findViewById(R.id.qinglitixing_add_ok);
        qinglitixingAddOk.setOnClickListener(this);
        //布局
        //标题
        qinglitixingAddTitle = ((EditText) findViewById(R.id.qinglitixing_add_title));
        //日期
        qinglitixingAddTime = ((EditText) findViewById(R.id.qinglitixing_add_time));
//        qinglitixingAddTime.setText(initStartDateTime);
        qinglitixingAddTime.setOnClickListener(this);
        //提醒
        qinglitixingAddRemind = ((EditText) findViewById(R.id.qinglitixing_add_remind));
        handler = new Handler(this);
        //重复
        qinglitixingAddRepeat = ((EditText) findViewById(R.id.qinglitixing_add_repeat));
        qinglitixingAddRepeat.setOnClickListener(this);
        qinglitixingAddRepeatSelectItem = ((LinearLayout) findViewById(R.id.qinglitixing_add_repeat_select_item));
        //不重复监听
        ((Button) findViewById(R.id.qinglitixing_add_repeat_no)).setOnClickListener(this);
        //每年监听
        ((Button) findViewById(R.id.qinglitixing_add_repeat_year)).setOnClickListener(this);
        //每月监听
        ((Button) findViewById(R.id.qinglitixing_add_repeat_month)).setOnClickListener(this);
        //每周监听
        ((Button) findViewById(R.id.qinglitixing_add_repeat_week)).setOnClickListener(this);
        //每日监听
        ((Button) findViewById(R.id.qinglitixing_add_repeat_day)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (qinglitixingAddRepeatSelectItem.getVisibility()==View.VISIBLE){
            qinglitixingAddRepeatSelectItem.setVisibility(View.GONE);
        }
        switch (v.getId()) {
            case R.id.qinglitixing_add_back:
                finish();
                break;
            //确认添加提醒
            case R.id.qinglitixing_add_ok:
                Toast.makeText(this, "确认添加", Toast.LENGTH_SHORT).show();
                String uid = sharedPreferences.getString("userid", "1");
                String title = qinglitixingAddTitle.getText().toString();
//                String time=timeInMillis+"";
                String time="0";
                String remind=qinglitixingAddRemind.getText().toString();
                final String parameter="uid="+uid+"&title="+title+"&starttime="+time+"&repeat="+ repeat +"&remind="+remind;
                new Thread(){
                    @Override
                    public void run() {
                        String json = HttpUtils.getStringByConnection(qinglitixingAddUrl, parameter);
                        Message msg=Message.obtain();
                        msg.what=5;
                        msg.obj=json;
                        handler.sendMessage(msg);
                    }
                }.start();
                break;
            //添加时间
            case R.id.qinglitixing_add_time:
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this,null);
                dateTimePicKDialog.dateTimePicKDialog(qinglitixingAddTime);
                timeInMillis = DateTimePickDialogUtil.timeInMillis;
                Log.e(tag, timeInMillis +"");
                break;
            //重复:使选择器显示
            case R.id.qinglitixing_add_repeat:
                qinglitixingAddRepeatSelectItem.setVisibility(View.VISIBLE);
                break;
            //重复:选择器
            //不重复
            case R.id.qinglitixing_add_repeat_no:
                qinglitixingAddRepeat.setText("不重复");
                repeat="0";
                break;
            //不重复
            case R.id.qinglitixing_add_repeat_year:
                qinglitixingAddRepeat.setText("每年");
                repeat="1";
                break;
            //不重复
            case R.id.qinglitixing_add_repeat_month:
                qinglitixingAddRepeat.setText("每月");
                repeat="2";
                break;
            //不重复
            case R.id.qinglitixing_add_repeat_week:
                qinglitixingAddRepeat.setText("每周");
                repeat="3";
                break;
            //不重复
            case R.id.qinglitixing_add_repeat_day:
                qinglitixingAddRepeat.setText("每日");
                repeat="4";
                break;

        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 5:
                String json= (String) msg.obj;
                if (json.contains("添加成功")){
                    Toast.makeText(this,"添加成功!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Qingitixing.class));
                    finish();
                }else {
                    Toast.makeText(this,"添加失败!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
