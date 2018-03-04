package com.wu.wucheng.xiaoli_wu.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.model.GuanhuaihuatiModel;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;
import com.wu.wucheng.xiaoli_wu.tools.SDCardHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Wucheng on 2016/3/23.
 */
public class GuanhuaihuatiZuijinAdapter extends MyBaseAdapter<GuanhuaihuatiModel.TopiclistBean> {
    private static final String tag = GuanhuaihuatiZuijinAdapter.class.getSimpleName();
    private String dir;
    private String absFilePath;
    public GuanhuaihuatiZuijinAdapter(Context context, int layoutRes, List<GuanhuaihuatiModel.TopiclistBean> data,String dir) {
        super(context, layoutRes, data);
        this.dir=dir;
    }

    @Override
    public void bindData(ViewHolder holder, GuanhuaihuatiModel.TopiclistBean topiclistBean) {
        ImageView GuanhuaihuatiImg = (ImageView) holder.getView(R.id.guanhuaihuati_img);
        TextView GuanhuaihuatiTitle = (TextView) holder.getView(R.id.guanhuaihuati_title);
        GuanhuaihuatiTitle.setText(topiclistBean.getTitle());
        //*************
        final String url = "http://www.1000phone.net:8088/qfxl/" + topiclistBean.getFengmian();
        GuanhuaihuatiImg.setImageResource(R.mipmap.ic_launcher);
        GuanhuaihuatiImg.setTag(url);
        int idx = url.lastIndexOf("/");
        final String filename = url.substring(idx + 1);
        absFilePath = SDCardHelper.getSDCardPath() + File.separator + dir
                + File.separator + filename;
        Log.e(tag, absFilePath);
        File file = new File(absFilePath);
        if (!file.exists()) {
            Log.e(tag, absFilePath + "不存在");
            new fengmianDown(GuanhuaihuatiImg, filename).execute(url);
        } else {
            byte[] bytes = SDCardHelper.loadFileFromSDCard(absFilePath);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            GuanhuaihuatiImg.setImageBitmap(bitmap);
        }
        //********
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
