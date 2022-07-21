package com.example.swosti_webview_application.ui;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.swosti_webview_application.R;
import com.example.swosti_webview_application.databinding.WebviewFragmentBinding;

public class WebviewFragment extends Fragment {
    private WebView mWebView;
    private WebviewViewModel mViewModel;
    private static final String TAG = "WebviewFragment";
    private WebviewFragmentBinding mBinding;

    public static WebviewFragment newInstance() {
        return new WebviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(WebviewViewModel.class);
        mBinding = WebviewFragmentBinding.inflate(inflater,container,false);
        mWebView = mBinding.webView;
        loadWebView();
        runThread();
        return mBinding.getRoot();
    }

    private void runThread() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.spinKit.setVisibility(View.GONE);
                mBinding.webView.setVisibility(View.VISIBLE);
            }
        },2500);
    }

    private void loadWebView() {
        mWebView.loadUrl("https://swosti.net/loancalculator");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished: Load complete "+url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "onPageStarted: "+url);
            }
        });
    }

}