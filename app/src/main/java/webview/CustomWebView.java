package webview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.R;


/**
 * 类：CustomWebView 自定义webview
 * 加载页面时，显示等待效果
 * 加载成功，显示webview
 * 加载失败，显示错误信息，并可再次请求
 * <p>
 * 作者：qxc
 * 日期：2017/3/29
 */
public class CustomWebView extends FrameLayout {
    //上下文
    private Context context;
    //WebView控件
    public WebView webview;
    //进度条
    public ProgressBar pb_progressBar;
    //WebView加载控件容器
    private RelativeLayout rl_load;
    //进度条
    private ImageView iv_progress;
    //文本
    private TextView tv_msg;
    //是否接收到错误信息
    private boolean isReceivedError = false;
    //进度条动画
    private Animation animProgress;

    private String imgUrl;

    public CustomWebView(Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.webview, this);

        //初始化动画
        initAnim();

        //初始化控件
        initView();

        //初始化事件
        initEvent();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.webview, this);

        //初始化动画
        initAnim();

        //初始化控件
        initView();

        //初始化事件
        initEvent();
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        animProgress = AnimationUtils.loadAnimation(context, R.anim.progress);
        LinearInterpolator lin = new LinearInterpolator();
        animProgress.setInterpolator(lin);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //初始化控件
        webview = (WebView) findViewById(R.id.webview);
        pb_progressBar = (ProgressBar) findViewById(R.id.pb_progressBar);
        rl_load = (RelativeLayout) findViewById(R.id.rl_load);
        iv_progress = (ImageView) findViewById(R.id.iv_progress);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
//        //设置样式
//        FontUtils.setFontStyle(context, tv_msg, FontEnum.PingFang);
        //设置动画
        iv_progress.startAnimation(animProgress);
        //设置进度条
        webview.setWebChromeClient(new CustomWebChromeClient(pb_progressBar));
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        if (webview != null) {
            initLongClick(webview);
        }
    }

    /**
     * 重新加载
     */
    public void reload() {
        isReceivedError = false;
        tv_msg.setTextColor(Color.parseColor("#888888"));
        tv_msg.setText("数据加载中...");
        iv_progress.startAnimation(animProgress);
        webview.postDelayed(new Runnable() {
            @Override
            public void run() {
                webview.reload();//重新加载
            }
        }, 10);
    }

    /**
     * 加载失败时
     *
     * @param errorCode   错误码
     * @param description 描述
     * @param failingUrl  加载失败的url
     */
    public void onWebViewReceivedError(final int errorCode, String description, String failingUrl) {
        isReceivedError = true;
        rl_load.setVisibility(View.VISIBLE);
        iv_progress.clearAnimation();
        rl_load.setBackgroundColor(Color.WHITE);
        switch (errorCode) {
            case -5:
                tv_msg.setText("网络连接失败，请检查网络！");
                break;
            default:
                tv_msg.setText("网络连接失败，请检查网络！");
                break;
        }
        tv_msg.setVisibility(View.VISIBLE);
        tv_msg.setTextColor(Color.parseColor("#46a3ff"));
    }

    /**
     * 加载成功时
     */
    public void onWebViewReceivedFinish() {
        //如果没有错误，隐藏页面加载控件
        if (!isReceivedError) {
            rl_load.setVisibility(View.GONE);
        }
        if (animProgress != null) {
            iv_progress.clearAnimation();
        }
    }

    //添加长按事件
    private void initLongClick(final WebView webview) {
        //webview长按点击，直接OnLongClick无法监听到
        webview.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            }
        });
    }

}
