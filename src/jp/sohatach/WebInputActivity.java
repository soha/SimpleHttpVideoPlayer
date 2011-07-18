package jp.sohatach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebInputActivity extends Activity {
	private WebView webView;
	private String web_url;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_input);
		
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new MyWebViewClient());
		
		Intent i = getIntent();
		if(i != null) {
			web_url = i.getExtras().getString("web_url");
			webView.loadUrl(web_url);
		}
	}

	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	
	    	//Intent i = new Intent();
	    	Intent i = new Intent(WebInputActivity.this, SimpleHttpVideoPlayerActivity.class);
	    	i.putExtra("video_url", url);
	    	//setResult(SimpleHttpVideoPlayerActivity.RESULT_OK, i);
	    	//finish();
	    	startActivity(i);
	    	
	        return true;
	    }
	}
}
