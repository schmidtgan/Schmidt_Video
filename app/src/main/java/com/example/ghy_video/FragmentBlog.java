package com.example.ghy_video;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.ghy_video.base.BaseFragment;

/**
 * Created by Administrator on 2017/10/21.
 */

public class FragmentBlog extends BaseFragment {
    private WebView mWebView;
    private ProgressBar mProgressBar;

    private static final int MAX_VALUE = 100;
    private static final String QQ_URL= "https://www.baidu.com";
    @Override
    protected void initView() {
    mWebView = bindViewId(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        mProgressBar = bindViewId(R.id.pb_progress);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        mProgressBar.setMax(MAX_VALUE);
        mWebView.loadUrl(QQ_URL);
        mWebView.setWebChromeClient(mWebChromeClient);
    }
    private WebChromeClient mWebChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == MAX_VALUE){
                mProgressBar.setVisibility(view.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    };
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }
}
