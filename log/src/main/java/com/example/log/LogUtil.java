package com.example.log;

import android.util.Log;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/16
 */

public class LogUtil {

    private static final String TAG = "WZLOG";
    //Log开关
    private static final boolean DEBUG = true;

    //通用TAG输出
    public static void d(String message) {
        if (DEBUG) Log.d(TAG, message);
    }

    public static void e(String message) {
        if (DEBUG) Log.e(TAG, message);
    }

    public static void i(String message) {
        if (DEBUG) Log.i(TAG, message);
    }

    public static void v(String message) {
        if (DEBUG) Log.v(TAG, message);
    }

    public static void w(String message) {
        if (DEBUG) Log.w(TAG, message);
    }

    public static void wtf(String message) {
        if (DEBUG) Log.wtf(TAG, message);
    }

    public static void println(String message) {
        if (DEBUG) Log.println(Log.INFO, TAG, message);
    }

    //自定义TAG输出
    public static void d(String tag, String message) {
        if (DEBUG) Log.d(tag, message);
    }

    public static void e(String tag, String message) {
        if (DEBUG) Log.e(tag, message);
    }

    public static void i(String tag, String message) {
        if (DEBUG) Log.i(tag, message);
    }

    public static void v(String tag, String message) {
        if (DEBUG) Log.v(tag, message);
    }

    public static void w(String tag, String message) {
        if (DEBUG) Log.w(tag, message);
    }

    public static void wtf(String tag, String message) {
        if (DEBUG) Log.wtf(tag, message);
    }

    public static void println(String tag, String message) {
        if (DEBUG) Log.println(Log.INFO, tag, message);
    }
}
