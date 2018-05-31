package com.example.messagebus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import eventbus.OnMessageEvent;
import info.MessageOne;
import info.MessageThree;
import info.MessageTwo;

public class MainActivity extends Activity {
    private Button bt_post1, bt_post2, bt_post3, bt_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initEvent();
    }

    private void initEvent() {
        bt_post1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.eventManager.post(new MessageOne("单次消息1", 1));
                BaseApplication.eventManager.event(new MessageOne(), new OnMessageEvent() {
                    @Override
                    public void event(Object event) {
                        if (event != null) {
                            Toast.makeText(MainActivity.this, "消息：" + ((MessageOne) event).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        bt_post2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.eventManager.post(new MessageTwo("重复消息2", 2));
            }
        });
        bt_post3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.eventManager.postSticky(new MessageThree("粘性消息", 3));
            }
        });
        bt_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        bt_post1 = (Button) findViewById(R.id.bt_post1);
        bt_post2 = (Button) findViewById(R.id.bt_post2);
        bt_post3 = (Button) findViewById(R.id.bt_post3);
        bt_jump = (Button) findViewById(R.id.bt_jump);
    }
}
