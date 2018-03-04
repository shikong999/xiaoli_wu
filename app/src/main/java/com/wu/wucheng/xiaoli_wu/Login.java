package com.wu.wucheng.xiaoli_wu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.model.User;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener, Handler.Callback {

    private TextView forgetPassword;
    private EditText loginMobile;
    private EditText loginPassword;
    private Button myLogin;
    private String strUrl = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=login";
    private static final String TAG = Login.class.getSimpleName();
    private Handler handler;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        editor = sharedPreferences.edit();
        //忘记密码-下划线
        forgetPassword = ((TextView) findViewById(R.id.forgetPassword));
        forgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        loginMobile = ((EditText) findViewById(R.id.login_mobile));
        loginPassword = ((EditText) findViewById(R.id.login_password));
        //登录按钮
        myLogin = ((Button) findViewById(R.id.bt_login));
        myLogin.setOnClickListener(this);
        ((Button) findViewById(R.id.bt_suibiankankan)).setOnClickListener(this);
        handler = new Handler(this);
        zhuce = (Button) findViewById(R.id.zhuce);
        zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mobile = loginMobile.getText().toString();
        String password = loginPassword.getText().toString();
        //参数
        final String parameter = "mobile=" + mobile + "&password=" + password;
        switch (v.getId()) {
            case R.id.bt_login:
                Toast.makeText(this, "waiting...", Toast.LENGTH_SHORT).show();
                new Thread() {
                    @Override
                    public void run() {
                        String json = HttpUtils.getStringByConnection(strUrl, parameter);
                        Message msg = Message.obtain();
                        msg.obj = json;
                        msg.what = 1;
                        handler.sendMessage(msg);

                    }
                }.start();
                break;
            case R.id.bt_suibiankankan:
                editor.putString("userid", "1");
                editor.clear();
                editor.commit();
                jumpMainActivity();
                break;

            case R.id.zhuce:
                Toast.makeText(this,"注册功能尚未开放",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                String json = (String) msg.obj;
                Gson gson = new Gson();
                User user = gson.fromJson(json, User.class);
                String status = user.getStatus();
                String userid = user.getUserid();
                if (status.equals("1")) {
                    Toast.makeText(this, user.getData(), Toast.LENGTH_SHORT).show();
                    //获取userid
                    //设置SharedPreferences
                    editor.putString("userid", userid);
                    //SharedPreferences中设置图片途径
                    String dir = "xiaoli" + File.separator + "picture";
                    editor.putString("dir", dir);
                    editor.clear();
                    editor.commit();
                    Log.e(TAG, userid);
                    jumpMainActivity();
                } else {
                    Toast.makeText(this, user.getData(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void jumpMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isWelcome", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}
