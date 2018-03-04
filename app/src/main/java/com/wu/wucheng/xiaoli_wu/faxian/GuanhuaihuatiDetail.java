package com.wu.wucheng.xiaoli_wu.faxian;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.SysExitUtil;
import com.wu.wucheng.xiaoli_wu.model.GuanhuaihuatiDetailModel;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;
import com.wu.wucheng.xiaoli_wu.tools.SDCardHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GuanhuaihuatiDetail extends AppCompatActivity implements View.OnClickListener,Handler.Callback {

    private static final String tag=GuanhuaihuatiDetail.class.getSimpleName();
    private String conid;
    private ImageView guanhuaihuati_detail_back;
    private TextView guanhuaihuati_detail_title;
    private ImageView guanhuaihuati_detail_img;
    private TextView guanhuaihuati_detail_content;
    private String showtopicURL="http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=showtopic";
    private Handler handler;
    private String dir;
    private String absFilePath;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanhuaihuati_detail);
        sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        dir = sharedPreferences.getString("dir", "");
        conid = getIntent().getStringExtra("conid");
        Log.e(tag,"conid="+conid);
        initView();
        iniData();
    }

    private void iniData() {
        final String showtopicParameter="conid="+conid;
new Thread(){
    @Override
    public void run() {
        String json = HttpUtils.getStringByConnection(showtopicURL, showtopicParameter);
        Message msg=Message.obtain();
        msg.what=8;
        msg.obj=json;
        handler.sendMessage(msg);
    }
}.start();
    }

    private void initView() {
        guanhuaihuati_detail_back = (ImageView) findViewById(R.id.guanhuaihuati_detail_back);
        guanhuaihuati_detail_back.setOnClickListener(this);
        guanhuaihuati_detail_title = (TextView) findViewById(R.id.guanhuaihuati_detail_title);
        guanhuaihuati_detail_img = (ImageView) findViewById(R.id.guanhuaihuati_detail_img);
        guanhuaihuati_detail_content = (TextView) findViewById(R.id.guanhuaihuati_detail_content);
        handler = new Handler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guanhuaihuati_detail_back:
                finish();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 8:
                String json= (String) msg.obj;
                Gson gson=new Gson();
                GuanhuaihuatiDetailModel guanhuaihuatiDetailModel = gson.fromJson(json, GuanhuaihuatiDetailModel.class);
                List<GuanhuaihuatiDetailModel.ShowtopicBean> showtopic = guanhuaihuatiDetailModel.getShowtopic();
                GuanhuaihuatiDetailModel.ShowtopicBean data = showtopic.get(0);
                String title = data.getTitle();
                guanhuaihuati_detail_title.setText(title);
                String content = data.getContent();
                guanhuaihuati_detail_content.setText(content);
                String fengmian = data.getFengmian();
                String url="http://www.1000phone.net:8088/qfxl/"+fengmian;
                //下载图片
                int idx = url.lastIndexOf("/");
                final String filename = url.substring(idx + 1);
                absFilePath = SDCardHelper.getSDCardPath() + File.separator + dir
                        + File.separator + filename;
                File file = new File(absFilePath);
                if (!file.exists()) {
                    Log.e(tag, absFilePath + "不存在");
                    new fengmianDown(guanhuaihuati_detail_img, filename).execute(url);
                } else {
                    byte[] bytes = SDCardHelper.loadFileFromSDCard(absFilePath);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    guanhuaihuati_detail_img.setImageBitmap(bitmap);
                }
                break;
        }
        return true;

    }
    private class fengmianDown extends AsyncTask<String, Void, byte[]> {
        private ImageView xiangceImg;
        private String imgPath;

        private String filename;

        public fengmianDown(ImageView xiangceImg, String filename) {
            this.filename = filename;
            this.xiangceImg = xiangceImg;
        }

        @Override
        protected byte[] doInBackground(String... params) {
            imgPath = params[0];
            InputStream inputStream = HttpUtils.getInputStreamByConnection(params[0]);
            //***********************************
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] arr = new byte[8 * 1024];
            int len;
            try {
                while ((len = inputStream.read(arr)) != -1) {
                    baos.write(arr, 0, len);
                    baos.flush();
                }
                Log.e(tag, "连接成功");
                byte[] data = baos.toByteArray();
                baos.close();
                SDCardHelper.saveFileToSDCard(data, dir, filename);
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] data) {
            super.onPostExecute(data);
            if (imgPath.equals(xiangceImg.getTag())) {
                byte[] bytes = SDCardHelper.loadFileFromSDCard(absFilePath);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                xiangceImg.setImageBitmap(bitmap);
            }
        }
    }
}
