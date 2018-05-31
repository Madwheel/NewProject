package webview;

import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;


/**
 * 类：CustomWebChromeClient
 * 作者： qxc
 * 日期：2018/1/19.
 */
public class CustomWebChromeClient extends WebChromeClient {
    private ProgressBar progressBar;

    /**
     * 构造函数
     */
    public CustomWebChromeClient(){
    }

    /**
     * 构造函数
     * @param progressBar 进度条
     */
    public CustomWebChromeClient(ProgressBar progressBar){
        this.progressBar = progressBar;
    }

    /**
     * 设置进度
     * @param progress 进度（0-100）
     */
    public void setProgress(int progress){
        if(progressBar==null){
            return;
        }
        Log.i("Progress","Progress: "+progress);
        //隐藏进度条
        if (progress == 100) {
            progressBar.setProgress(progress);
            //延时0.2秒后消失
            progressBar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            },200);
        }
        //显示进度
        else {
            if (progressBar.getVisibility() == View.GONE){
                progressBar.setVisibility(View.VISIBLE);
            }
            progressBar.setProgress(progress);
        }
    }

    @Override
    public void onProgressChanged(WebView webView, int progress) {
        setProgress(progress);
        super.onProgressChanged(webView, progress);
    }
}
