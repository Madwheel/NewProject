package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import info.MessageOne;
import info.MessageThree;
import info.MessageTwo;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/02
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageThree messageEvent) {
        Toast.makeText(this, "消息：" + messageEvent.getName(), Toast.LENGTH_SHORT).show();
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageTwo messageEvent) {
        Toast.makeText(this, "消息：" + messageEvent.getName(), Toast.LENGTH_SHORT).show();
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageOne messageEvent) {
        Toast.makeText(this, "消息：" + messageEvent.getName(), Toast.LENGTH_SHORT).show();
    }
}
