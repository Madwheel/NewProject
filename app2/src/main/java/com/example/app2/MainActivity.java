package com.example.app2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import numberrain.NumberRainActivity;

public class MainActivity extends Activity {
    private Button bt, bt_jump,bt_jump1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        bt = (Button) findViewById(R.id.bt);
        bt_jump = (Button) findViewById(R.id.bt_jump);
        bt_jump1 = (Button) findViewById(R.id.bt_jump1);
    }

    private void initEvent() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    ComponentName cn = new ComponentName("com.example.log", "com.example.log.MainActivity");
                    intent.setComponent(cn);
                    startActivity(intent);
                } catch (Exception ex) {
                }
            }
        });
        bt_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        bt_jump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumberRainActivity.class);
                startActivity(intent);
            }
        });
    }

}
