package com.xingyun.webviewwithjssample;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyWebViewActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);
        myWebView=findViewById(R.id.myWebView);

        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAllowContentAccess(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.addJavascriptInterface(new JSInterface(),"test");
        myWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.d("swallow","onJsAlert:"+message);
                return super.onJsAlert(view, url, message, result);
            }
        });
        myWebView.loadUrl("file:///android_asset/index.html");
    }

    //https://www.jianshu.com/p/345f4d8a5cfa
    class JSInterface extends Object{

        @JavascriptInterface
        public void sendToAndroid(String message){
            Log.i("swallow",message);
            Toast.makeText(MyWebViewActivity.this,message,Toast.LENGTH_LONG).show();
        }
    }


}
