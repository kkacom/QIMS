package com.zhilian.rf_qims.mvp.sample_detail.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.zhilian.rf_qims.R;

/**
 * Created by colin on 2018/2/7 10:13 .
 */

public class WebActivity extends Activity {
	private WebView mWebView;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_web);
		mWebView = findViewById(R.id.web);
		mWebView.loadUrl("file:///android_asset/RFJC-FH-01-B1.html");
	}
}
