package com.wu.wucheng.xiaoli_wu.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.os.StatFs;

/**
 * 用于操作SDCard的工具类
 *
 * @author Administrator
 */
public class SDCardHelper {
    /**
     * 判断sdcard是否已经成功挂载
     *
     * @return
     */
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取Sdcard的绝对路径(根目录),不同的手机/模拟器的路径可能不同
     *
     * @return
     */
    public static String getSDCardPath() {
        if (isSDCardMounted()) {
            // Environment.getExternalStorageDirectory()返回File对象
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取SDCard的总大小(MB)
     *
     * @return
     */
    public static long getSDCardSize() {
        if (isSDCardMounted()) {
            StatFs sf = new StatFs(getSDCardPath());// 根据传入的sdcard的根目录,构建一个工具类对象
            int size = sf.getBlockSize();// 单个房子的建筑面积byte
            int count = sf.getBlockCount();// 该楼房的总套数
            return size * count / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取sdcard的"剩余"空间的大小(MB)
     *
     * @return
     */
    public static long getSDCardFreeSize() {
        if (isSDCardMounted()) {
            StatFs sf = new StatFs(getSDCardPath());// 根据传入的sdcard的根目录,构建一个工具类对象
            int size = sf.getBlockSize();// 单个房子的建筑面积byte
            int count = sf.getFreeBlocks();// 获取剩余的"块数"
            return size * count / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取sdcard的"可用"空间的大小(MB)
     *
     * @return
     */
    public static long getSDCardAvailableSize() {
        if (isSDCardMounted()) {
            StatFs sf = new StatFs(getSDCardPath());// 根据传入的sdcard的根目录,构建一个工具类对象
            int size = sf.getBlockSize();// 单个房子的建筑面积byte
            int count = sf.getAvailableBlocks();// 获取可利用的"块数"
            return size * count / 1024 / 1024;
        }
        return 0;
    }

    /**
     * @param data     数据
     * @param dir      目标(相对)路径
     * @param filename 目标文件名
     * @return
     */
    public static boolean saveFileToSDCard(byte[] data, String dir,
                                           String filename) {
        if (isSDCardMounted()) {
            // getSDCardPath():sdcard的根目录,dir是相对路径
            File folder = new File(getSDCardPath() + File.separator + dir);
            if (!folder.exists()) {
                folder.mkdirs();// 如果目标路径不存在,需要自己建立一个目录
            }
            File targetFile = new File(folder, filename);// 要写的目标文件
            try {
                FileOutputStream fos = new FileOutputStream(targetFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(data);// 写出数据
                bos.close();
                return true;// 成功写出后返回true
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 已知文件的绝对路径，从sdcard中获取到该文件，返回byte[]
     *
     * @param absFilePath
     * @return
     */
    public static byte[] loadFileFromSDCard(String absFilePath) {
        if (isSDCardMounted()) {
            File srcFile = new File(absFilePath);
            if (!srcFile.exists()) {// 如果文件不存在,则直接返回null,不再继续读取
                return null;
            }
            try {
                FileInputStream fis = new FileInputStream(absFilePath);
                BufferedInputStream bis = new BufferedInputStream(fis);
                int i;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((i = bis.read()) != -1) {
                    baos.write(i);
                }
                bis.close();
                return baos.toByteArray();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 重载的方法 根据相对路径返回byte数组
     *
     * @param relativePath (sdcard根目录下的)相对路径
     * @param fileName     文件名
     * @return
     */
    public static byte[] loadFileFromSDCard(String relativePath, String fileName) {
        String absFilePath = getSDCardPath() + File.separator + relativePath
                + File.separator + fileName;
        return loadFileFromSDCard(absFilePath);
    }

    /**
     * 删除指定文件
     *
     * @param relativePath (sdcard根目录下的)相对路径
     * @param fileName     文件名
     * @return
     */
    public static boolean deleteFileFromSDCardByRelativePath(
            String relativePath, String fileName) {
        if (isSDCardMounted()) {
            String absPath = getSDCardPath() + File.separator + relativePath + File.separator + fileName;
            File f = new File(absPath);
            if (f.exists()) {
                return f.delete();//如果存在就删除
            }
        }
        return false;
    }
}
