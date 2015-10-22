package com.scroll.code;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.androidnettest.code.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestAct extends Activity {
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testlay);
		Button button = (Button) findViewById(R.id.mybt);
		textView = (TextView) findViewById(R.id.mtv);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent i=new Intent("com.customview.test.ViewAct");
				// startActivity(i);

				// Intent intent = new
				// Intent("com.example.volleystudy.MainActivity_A");

				// Intent intent=new Intent("com.scroll.code.MainAct");

				// intent.addCategory(Intent.CATEGORY_DEFAULT); //
				// com.scroll.code.MainAct

				// ComponentName cn = new ComponentName("com.scroll.code",
				// "MainAct");
				// intent.setComponent(cn);
				// startActivity(intent);

				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub

						// TODO Auto-generated method stub

						URL url;
						try {
							String urlString = "http://www.baidu.com";
							url = new URL(urlString);
							URLConnection connection = url.openConnection();
							InputStream inputStream = connection
									.getInputStream();

							int i = 0;
							byte[] bs = new byte[1024];
							StringBuffer stringBuffer = new StringBuffer();
							while ((i = inputStream.read(bs)) != -1) {

								String string = new String(bs, 0, i);
								stringBuffer.append(string);
							}
							final String resultString = stringBuffer.toString();

							textView.post(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									textView.append(resultString);
								}
							});
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}.start();

			}
		});

	}
}
