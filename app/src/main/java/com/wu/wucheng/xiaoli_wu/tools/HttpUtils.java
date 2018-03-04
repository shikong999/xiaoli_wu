package com.wu.wucheng.xiaoli_wu.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.Login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtils {
    private static final String TAG=HttpUtils.class.getSimpleName();

    /**
     * 利用HttpURLConnection获取到指定url的输入流
     *
     * @param strUrl
     */
    public static InputStream getInputStreamByConnection(String strUrl) {
        URL url;
        try {
            url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                return is;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用HttpURLConnection获取到指定url的byte[]
     *
     * @param strUrl
     * @return
     */
    public static byte[] getByteArrayByConnection(String strUrl) {
        InputStream is = getInputStreamByConnection(strUrl);
        byte[] arr = new byte[1024 * 8];
        int len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(arr)) != -1) {
                baos.write(arr, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据url,利用Connection,返回字符串GET
     *
     * @param strUrl
     * @return
     */
    public static String getStringByConnection(String strUrl) {
        URL url;
        try {
            url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] arr = new byte[1024 * 8];
                int len;//每次读到的字节数
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = is.read(arr)) != -1) {
                    baos.write(arr, 0, len);
                }
                return new String(baos.toByteArray());//byte[]-->String
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringByConnection(String strUrl,String parameter){
        URL url = null;
        try {
            url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.connect();
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(parameter.getBytes());
        if (conn.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream is = conn.getInputStream();
            byte[] arr = new byte[1024*8];
            int len;//每次读到的字节数
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((len= is.read(arr))!=-1){
                baos.write(arr, 0, len);
            }
            String result = new String(baos.toByteArray());//byte[]-->String
            Log.e(TAG, result);
            return result;
        }else {
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于获取资源的大小
     *
     * @param strUrl
     * @return
     */
    public static int getContentLengthByConnection(String strUrl) {
        URL url;
        try {
            url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                return conn.getContentLength();//获取资源的大小的方法
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
}
