package com.wu.wucheng.xiaoli_wu;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wucheng on 2016/3/25.
 */
public class SysExitUtil {
    //建立一个public static的list用来放activity
    public static List activityList = new ArrayList();


    //finish所有list中的activity
    public static void exit() {
        int siz = activityList.size();
        for (int i = 0; i < siz; i++) {
            if (activityList.get(i) != null) {
                ((Activity) activityList.get(i)).finish();
            }
        }
    }
}

