package com.wu.wucheng.xiaoli_wu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.fragments.Faxian;
import com.wu.wucheng.xiaoli_wu.fragments.Guanxiguanli;
import com.wu.wucheng.xiaoli_wu.fragments.Qingligonglve;
import com.wu.wucheng.xiaoli_wu.fragments.Wode;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    //测试git
    {//加入finish列表
        SysExitUtil.activityList.add(this);
    }

    private RadioGroup controller;
    private Fragment cacheFragment;
    private SharedPreferences sharedPreferences;
    private static final String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        setContentView(R.layout.activity_main);
        Gotowelcome();
        initView();
    }

    private void Gotowelcome() {
        Intent wel = getIntent();
        boolean isWelcome = wel.getBooleanExtra("isWelcome", false);
        String userid = sharedPreferences.getString("userid", "0");
//        //SharedPreferences中设置图片途径
//        String dir="xiaoli"+ File.separator+"picture";
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("dir",dir);
//        editor.clear();
//        editor.commit();
        Log.e(tag, userid);
        //判断是否进入过欢迎页
        if (!isWelcome) {
            startActivity(new Intent(this, Welcome.class));
            finish();
        }
    }

    private void initView() {
        controller = ((RadioGroup) findViewById(R.id.controller));
        controller.setOnCheckedChangeListener(this);
        //获取一个FragmentManager实例
        FragmentManager manager = getSupportFragmentManager();
        //开启一个Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        //事务中添加操作:①添加的位置②Fragment③TAG标记
        cacheFragment = new Guanxiguanli();
        transaction.add(R.id.container, cacheFragment, Guanxiguanli.TAG);
        //提交事务
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.controller_guanxiguanli:
                switchPage(Guanxiguanli.TAG, Guanxiguanli.class);
                break;
            case R.id.controller_qingligonglve:
                switchPage(Qingligonglve.TAG, Qingligonglve.class);
                break;
            case R.id.controller_faxian:
                switchPage(Faxian.TAG, Faxian.class);
                break;
            case R.id.controller_wode:
                switchPage(Wode.TAG, Wode.class);
                break;
        }
    }

    //页面切换的工具方法
    private void switchPage(String tag, Class<? extends Fragment> cls) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (cacheFragment != null) {
            //隐藏显示的Fragment
            transaction.hide(cacheFragment);
        }
        //加载要显示的Fragment
        /**
         * ①根据TAG,去查找Fragment
         * ②找到.则将目标Fragment显示出来
         * ③若未找到,则需要实例化一个Fragment,显示,记录到cacheFragment
         */
        cacheFragment = fragmentManager.findFragmentByTag(tag);
        if (cacheFragment != null) {
            transaction.show(cacheFragment);
        } else {
            try {
                //通过反射,使用无参构造获取一个Fragment的实例
                cacheFragment = cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.container, cacheFragment, tag);
        }
        //提交事务
        transaction.commit();
    }

    private boolean isExite;

    /**
     * 2次点击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下的键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果处于可退出状态
            if (isExite) {
                //调用系统的返回键退出
                return super.onKeyDown(keyCode, event);
            } else {
                //先将退出标识设为可退出状态
                isExite = true;
                Toast.makeText(this,"再按一次返回键退出本应用",Toast.LENGTH_SHORT).show();
                //定时器
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //按过返回键后,如果2秒后没继续按返回,则重置为不可退出状态
                        isExite = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

}
