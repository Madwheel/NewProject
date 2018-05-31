package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.MessageThree;
import info.MessageTwo;

public class MainActivity extends BaseActivity {

    private Button bt_post1, bt_post2, bt_post3, bt_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        bt_post1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = "3__7";
                String regx = "^.*(.)\\1{2}.*$";
                Matcher m = null;
                Pattern p = null;
                p = Pattern.compile(regx);
                m = p.matcher(pwd);
                if (m.matches()) {
                    Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "正确", Toast.LENGTH_SHORT).show();
                }
//                EventBus.getDefault().post(new MessageOne("重复消息1", 1));
//                EventBus.getDefault().post(new MessageTwo("重复消息2", 2));
            }
        });
        bt_post2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageTwo("重复消息2", 2));
            }
        });
        bt_post3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageThree("粘性消息", 3));
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
        bt_post3.animate().scaleX(1.2f).scaleY(1.2f).alpha(0.5f).setDuration(1000).start();
    }
}
