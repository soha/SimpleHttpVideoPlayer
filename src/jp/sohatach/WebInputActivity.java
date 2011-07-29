package jp.sohatach;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	    	
	    	generateDialog(this, url);
	        return true;
	    }

	    /**
	     * 開き方選択ダイアログの表示
	     * @param url 選択されたURL
	     */
	    private void generateDialog(final MyWebViewClient viewClient, final String url) {
	    	final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	        		WebInputActivity.this);
	        // 表示項目の配列
	        final CharSequence[] methods = { "Open as Link", "Play as Video" };
	        // タイトルを設定
	        alertDialogBuilder.setTitle("Please choose how to open.");
	        // 表示項目とリスナの設定
	        alertDialogBuilder.setItems(methods,
	                new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        if(which == 0) {
	                        	//スタックに積み上げるため新たに生成する（戻るキーで戻れるようにするため）
	    						Intent i = new Intent(WebInputActivity.this, WebInputActivity.class);
	    						i.putExtra("web_url", url);
	    						startActivity(i);
	                			//webView.loadUrl(url);
	                        }else{
	                        	viewClient.playVideo(url);
	                        }
	                    }
	                });

	        // ダイアログを表示
	        alertDialogBuilder.create().show();
	    }

		/**
	     * playerにURLを渡し再生する。
	     * @param url 動画ファイルのURL
	     */
	    protected void playVideo(String url) {
	    	Intent i = new Intent(WebInputActivity.this, SimpleHttpVideoPlayerActivity.class);
	    	i.putExtra("video_url", url);
	    	startActivity(i);
		}

	}
}
