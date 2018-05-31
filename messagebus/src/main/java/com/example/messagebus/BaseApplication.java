package com.example.messagebus;

import android.app.Application;

import eventbus.EventManager;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/02
 */

public class BaseApplication extends Application {
    public static EventManager eventManager;

    @Override
    public void onCreate() {
        eventManager = EventManager.getEventManager();
        super.onCreate();
    }
}
