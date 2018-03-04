package com.wu.wucheng.xiaoli_wu.wode;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.faxian.DateTimePickDialogUtil;
import com.wu.wucheng.xiaoli_wu.model.WodeInfoModel;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.util.Calendar;
import java.util.List;

public class WodeInfo extends AppCompatActivity implements View.OnClickListener, Handler.Callback {

    private ImageView wode_info_back;
    private String url = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=profile";
    private SharedPreferences sharedPreferences;
    private ImageView wode_info_touxiang;
    private TextView wode_info_nicheng;
    private EditText wode_info_birthday;
    private TextView wode_info_xingzuo;
    private TextView wode_info_age;
    private TextView wode_info_sex;
    private TextView wode_info_address;
    private TextView wode_info_biaoqian_edit;
    private Handler handler;
    private static final String tag = WodeInfo.class.getSimpleName();
    private Button wode_info_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_info);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        initView();
        initData();
    }

    private void initData() {
        //获取uid
        String userid = sharedPreferences.getString("userid", "1");
        //post参数
        final String parameter = "uid=" + userid;
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtils.getStringByConnection(url, parameter);
                Message msg = Message.obtain();
                msg.what = 9;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        wode_info_back = (ImageView) findViewById(R.id.wode_info_back);
        wode_info_back.setOnClickListener(this);
        wode_info_touxiang = (ImageView) findViewById(R.id.wode_info_touxiang);
        wode_info_nicheng = (TextView) findViewById(R.id.wode_info_nicheng);
        wode_info_nicheng.setOnClickListener(this);
        wode_info_birthday = (EditText) findViewById(R.id.wode_info_birthday);
        wode_info_birthday.setOnClickListener(this);
        wode_info_xingzuo = (TextView) findViewById(R.id.wode_info_xingzuo);
        wode_info_xingzuo.setOnClickListener(this);
        wode_info_age = (TextView) findViewById(R.id.wode_info_age);
        wode_info_sex = (TextView) findViewById(R.id.wode_info_sex);
        wode_info_sex.setOnClickListener(this);
        wode_info_address = (TextView) findViewById(R.id.wode_info_address);
        wode_info_address.setOnClickListener(this);
        wode_info_biaoqian_edit = (TextView) findViewById(R.id.wode_info_biaoqian_edit);
        wode_info_biaoqian_edit.setOnClickListener(this);

        //线程间通信
        handler = new Handler(this);
        wode_info_edit = (Button) findViewById(R.id.wode_info_edit);
        wode_info_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wode_info_back:
                finish();
                break;
            case R.id.wode_info_edit:
                Toast.makeText(this, "提交信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wode_info_nicheng:
                new AlertDialog.Builder(this)
                        .setTitle("昵称")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(new EditText(this))
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定",null)
                .show();
                break;
            case R.id.wode_info_birthday:
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this,null);
                dateTimePicKDialog.dateTimePicKDialog(wode_info_birthday);
                break;
            case R.id.wode_info_xingzuo:
                new AlertDialog.Builder(this)
                        .setTitle("请选择星座")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(new String[]{"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座"
                                        , "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"}, 0,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.wode_info_sex:
                new AlertDialog.Builder(this)
                        .setTitle("请选择性别")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(new String[]{"男", "女"}, 0,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.wode_info_address:
                new AlertDialog.Builder(this)
                        .setTitle("收货地址")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(new EditText(this))
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定",null)
                        .show();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 9:
                String json = (String) msg.obj;
                //解析json
                Gson gson = new Gson();
                WodeInfoModel wodeInfoModel = gson.fromJson(json, WodeInfoModel.class);
                //获取用户信息list
                List<WodeInfoModel.UserinfoBean> userinfo = wodeInfoModel.getUserinfo();
                //获取用户信息
                WodeInfoModel.UserinfoBean userinfoBean = userinfo.get(0);
                //获取印象
                List<WodeInfoModel.ImpresslistBean> impresslist = wodeInfoModel.getImpresslist();
                //获取星座列表
                List<WodeInfoModel.XzlistBean> xzlist = wodeInfoModel.getXzlist();
                //添加显示信息
                //昵称
                wode_info_nicheng.setText(userinfoBean.getNickname());
                //生日
                String Uyear = userinfoBean.getYear();
                String Umonth = userinfoBean.getMonth();
                String Uday = userinfoBean.getDay();
                if (!Uyear.equals("0") && !Umonth.equals("0") && !Uday.equals("0")) {
                    wode_info_birthday.setText(Uyear + "/" + Umonth + "/" + Uday);
                }
                //星座
                String xingzuo = userinfoBean.getXingzuo();
                for (int i = 0; i < xzlist.size(); i++) {
                    WodeInfoModel.XzlistBean xzlistBean = xzlist.get(i);
                    if (xzlistBean.getId().equals(xingzuo)) {
                        wode_info_xingzuo.setText(xzlistBean.getName());
                    }
                }
                //年龄
                if (!Uyear.equals("0")) {
                    //获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    Log.e(tag, "年份--" + year);
                    //计算年龄
                    int cYear = year - Integer.parseInt(Uyear);
                    wode_info_age.setText("" + cYear);

                }
                //性别
                if (userinfoBean.getSex().equals("0")) {
                    wode_info_sex.setText("男");
                } else {
                    wode_info_sex.setText("女");
                }
                //收货地址
                String address = userinfoBean.getAddress();
                if (!address.equals("")) {
                    wode_info_address.setText(address);
                }
                break;
        }
        return true;
    }
}
