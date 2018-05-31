package com.example.app;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import info.MessageOne;
import info.MessageThree;
import info.MessageTwo;
import webview.CustomWebView;

public class Main2Activity extends BaseActivity {
    private CustomWebView cwv_webview;
    private FrameLayout fl_main;
    private String url = "https://dev.pydp888.com/webview/v2/oneminute/list/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        cwv_webview = (CustomWebView) findViewById(R.id.cwv_webview);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        cwv_webview.webview.setTag(fl_main);
        WebSettings settings = cwv_webview.webview.getSettings();

        settings.setDomStorageEnabled(true);// 开启 DOM storage API 功能
        settings.setAppCacheEnabled(true);// 开启 Application Caches 功能
        // 设置代理
        String ua = settings.getUserAgentString();
        settings.setJavaScriptEnabled(true);
        PackageManager packageManager = Main2Activity.this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(Main2Activity.this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packageInfo.versionCode;
        if (!ua.contains("IWangzhe(android)/")) {
            if (ua.endsWith(";")) {
                settings.setUserAgentString(ua + "" + "IWangzhe(android)/" + versionCode + "," + "a.1.38.2200" + "," + "dev" + ",x5;");
            } else {
                settings.setUserAgentString(ua + ua + "; " + "IWangzhe(android)/" + versionCode + "," + "a.1.38.2200" + "," + "dev" + ",x5;");
            }
        }
        cwv_webview.webview.loadUrl(url);
        //设置不用系统浏览器打开,直接显示在当前Webview
        cwv_webview.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        //一分钟语音关闭音频播放
//        if (url != null && url.contains("/oneminute/list/")) {
//            JSONObject jo = new JSONObject();
//            try {
//                jo.put("visible", 0);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String exec = "javascript:WZWeb.exec('" + "webview.setState" + "'," + jo.toString() + ")";
//            cwv_webview.webview.loadUrl(exec);
//        }
        if (cwv_webview.webview != null) {
            cwv_webview.webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            ((ViewGroup) cwv_webview.webview.getParent()).removeView(cwv_webview.webview);
            cwv_webview.webview.destroy();
            cwv_webview.webview = null;
        }
        super.onDestroy();
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageThree messageEvent) {
        Toast.makeText(Main2Activity.this, "消息：" + messageEvent.getName(), Toast.LENGTH_SHORT).show();
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageTwo messageEvent) {
        Toast.makeText(Main2Activity.this, "消息：" + messageEvent.getName(), Toast.LENGTH_SHORT).show();
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageOne messageEvent) {
        Toast.makeText(Main2Activity.this, "消息：" + messageEvent.getName(), Toast.LENGTH_SHORT).show();
    }
}
