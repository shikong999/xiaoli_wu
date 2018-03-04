package com.wu.wucheng.xiaoli_wu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        delay();
    }


    private void delay() {
        //定时跳转
        userid = sharedPreferences.getString("userid", "0");
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (userid.equals("0")) {
                    startActivity(new Intent(Welcome.this, Login.class));

                } else {
                    Intent intent = new Intent(Welcome.this, MainActivity.class);
                    intent.putExtra("isWelcome",true);
                    startActivity(intent);
                }
                    Welcome.this.finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }
}
