package com.myhandlerthread.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnettest.code.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.myhandlerthread.code.LoadThreadFactory2.LisenserToView2;

public class MainAct2 extends Activity {

	@ViewInject(R.id.img)
	ImageView imageView;
	@ViewInject(R.id.mmtv)
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagelay);
		ViewUtils.inject(this);
	}

	@OnClick(R.id.imgbt)
	public void test(View v) {

		new LoadThreadFactory2<Bitmap>().LoadImage(
				"http://pic2.ooopic.com/01/03/51/25b1OOOPIC19.jpg",
				new LisenserToView2<Bitmap>() {

					@Override
					public void onBackToView(Bitmap g) {
						// TODO Auto-generated method stub
						imageView.setImageBitmap(g);
					}

					@Override
					public Bitmap parseInStream(InputStream in) {
						// TODO Auto-generated method stub

						Bitmap bitmap = BitmapFactory.decodeStream(in);
						return bitmap;
					}
				});
	}

	@OnClick(R.id.cycbt)
	public void test2(View v) {

		new LoadThreadFactory2<String>().LoadImage("http://www.baidu.com",
				new LisenserToView2<String>() {

					@Override
					public void onBackToView(String g) {
						// TODO Auto-generated method stub
						textView.setText(g);
					}

					@Override
					public String parseInStream(InputStream in) {
						// TODO Auto-generated method stub

						try {
							StringBuffer buffer = new StringBuffer();

							InputStreamReader inputStreamReader = new InputStreamReader(
									in);

							BufferedReader bufferedReader = new BufferedReader(
									inputStreamReader);

							String str = "";
							while ((str = bufferedReader.readLine()) != null) {
								buffer.append(str);
							}

							return buffer.toString();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
				});
	}

}

class LoadThreadFactory2<T> {

	interface LisenserToView2<G> {
		public void onBackToView(G g);

		public G parseInStream(InputStream in);

	}


	public static Executor executor = Executors.newFixedThreadPool(5);
	Handler handler;
	SendToView2 sendToView;
	LisenserToView2<T> lisenserToView;

	public LoadThreadFactory2() {
		// TODO Auto-generated constructor stub
		sendToView = new SendToView2(new Handler(Looper.getMainLooper()));// .getMainLooper();
	}

	public void LoadImage(final String urlString, final LisenserToView2<T> t) {
		lisenserToView = t;

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

					final T respon = t.parseInStream(inputStream);

					sendToView.executor.execute(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							// imageView.setImageBitmap(bitmap);

							lisenserToView.onBackToView(respon);

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

	public LoadThreadFactory2(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}

}

class SendToView2 {
	Executor executor;
	Handler handler;

	public SendToView2(final Handler handler) {
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
