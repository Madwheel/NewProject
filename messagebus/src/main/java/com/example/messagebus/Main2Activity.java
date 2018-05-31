package com.example.messagebus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import eventbus.OnMessageEvent;
import info.MessageOne;
import info.MessageThree;
import info.MessageTwo;

public class Main2Activity extends Activity {
    private Button bt_obtain1, bt_obtain2, bt_obtain3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initEvent();
    }

    private void initEvent() {
        bt_obtain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.eventManager.eventOnly(new MessageOne(), new OnMessageEvent() {
                    @Override
                    public void event(Object event) {
                        if (event != null) {
                            Toast.makeText(Main2Activity.this, "消息：" + ((MessageOne) event).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        bt_obtain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.eventManager.event(new MessageTwo(), new OnMessageEvent() {
                    @Override
                    public void event(Object event) {
                        if (event != null) {
                            Toast.makeText(Main2Activity.this, "消息：" + ((MessageTwo) event).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        bt_obtain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.eventManager.event(new MessageThree(), new OnMessageEvent() {
                    @Override
                    public void event(Object event) {
                        if (event != null) {
                            Toast.makeText(Main2Activity.this, "消息：" + ((MessageThree) event).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        bt_obtain1 = (Button) findViewById(R.id.bt_obtain1);
        bt_obtain2 = (Button) findViewById(R.id.bt_obtain2);
        bt_obtain3 = (Button) findViewById(R.id.bt_obtain3);
    }
}
