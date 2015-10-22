package com.myhandlerthread.code;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.androidnettest.code.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

public class MainAct extends Activity {

	@ViewInject(R.id.img)
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagelay);
		ViewUtils.inject(this);
	}

	@OnClick(R.id.imgbt)
	public void test(View v) {
		new LoadThreadFactory().LoadImage("http://pic14.nipic.com/20110522/7411759_164157418126_2.jpg", imageView);
	}

}

class LoadThreadFactory {

	public static Executor executor = Executors.newFixedThreadPool(5);
	Handler handler;
	SendToView sendToView;

	public LoadThreadFactory() {
		// TODO Auto-generated constructor stub
		sendToView = new SendToView(new Handler(Looper.getMainLooper()));// .getMainLooper();
	}

	public void LoadImage(final String urlString, final ImageView imageView) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				URL url;
				try {
					url = new URL(urlString);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					InputStream inputStream = connection.getInputStream();

					final Bitmap bitmap = BitmapFactory
							.decodeStream(inputStream);
					sendToView.executor.execute(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							imageView.setImageBitmap(bitmap);
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		executor.execute(runnable);
	}

	public LoadThreadFactory(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}

}

class SendToView {
	Executor executor;
	Handler handler;

	public SendToView(final Handler handler) {
		// TODO Auto-generated constructor stub
		executor = new Executor() {

			@Override
			public void execute(Runnable command) {
				// TODO Auto-generated method stub
				handler.post(command);
			}
		};
	}

}
