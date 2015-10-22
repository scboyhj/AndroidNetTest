package com.androidnettest.code;

import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.netmanage.code.NetManage;

interface IHandlerResult {

	void HandlerResult();
}

public class MainActivity extends BaseActivity implements IDealResult,
		IHandlerResult {

	Button button;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.bt);
		textView = (TextView) findViewById(R.id.tv);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String urlString = "http://www.baidu.com";
				NetManage.GetInstance().SendInfo(urlString, MainActivity.this);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void SendToHandler(String string) {

	}

	String infoString = "";

	@Override
	public void DealWithResultInfo(String infoString) {
		// TODO Auto-generated method stub
		this.infoString = infoString;
		NotifyHanlderDeal(this);

	}

	@Override
	public void HandlerResult() {
		// TODO Auto-generated method stub
		textView.setText(infoString);
	}
}
