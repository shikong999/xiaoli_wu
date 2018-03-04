package com.wu.wucheng.xiaoli_wu.wode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.Login;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.SysExitUtil;

public class Wodeshezhi extends AppCompatActivity implements View.OnClickListener {

    private ImageView wode_setting_back;
    private LinearLayout wode_setting_anquan;
    private LinearLayout wode_setting_tongzhi;
    private Button wode_setting_exit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setContentView(R.layout.activity_wodeshezhi);
        initView();
    }

    private void initView() {
        wode_setting_back = (ImageView) findViewById(R.id.wode_setting_back);
        wode_setting_back.setOnClickListener(this);
        wode_setting_anquan = (LinearLayout) findViewById(R.id.wode_setting_anquan);
        wode_setting_anquan.setOnClickListener(this);
        wode_setting_tongzhi = (LinearLayout) findViewById(R.id.wode_setting_tongzhi);
        wode_setting_tongzhi.setOnClickListener(this);
        wode_setting_exit = (Button) findViewById(R.id.wode_setting_exit);
        wode_setting_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wode_setting_back:
                finish();
                break;
            case R.id.wode_setting_anquan:
                Toast.makeText(this, "安全设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wode_setting_tongzhi:
                Toast.makeText(this, "通知设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wode_setting_exit:
                dialog();
                break;
        }
    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认退出登录吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putString("userid", "0");
                editor.clear();
                editor.commit();
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setClass(Wodeshezhi.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Wodeshezhi.this.finish();
                SysExitUtil.exit();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }
}
