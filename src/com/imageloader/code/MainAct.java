package com.imageloader.code;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnettest.code.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainAct extends Activity {

	@ViewInject(R.id.img)
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// ImageLoaderConfiguration configuration=new
		// ImageLoaderConfiguration.Builder(getApplicationContext()).

		ImageLoader.getInstance().displayImage("", imageView);
		setContentView(R.layout.imagelay);
		ViewUtils.inject(this);

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}
		};

		// Executors.newCachedThreadPool();
		Executor executor;
		ImageLoader.getInstance().displayImage("", imageView);

		
		ImageLoader.getInstance().loadImage("", null);

		
		ImageLoader.getInstance().loadImageSync("");
		
		ExecutorService executorService = Executors.newCachedThreadPool();

		// executorService.submit(task)();

		ImageRequest imageRequest;

		StringRequest stringRequest = new StringRequest("",
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub

					}
				}, null);

		Request<String> request = new Request<String>("", null) {

			@Override
			protected Response<String> parseNetworkResponse(
					NetworkResponse response) {
				// TODO Auto-generated method stub

				return null;
			}

			@Override
			protected void deliverResponse(String response) {
				// TODO Auto-generated method stub

			}
		};

		RequestQueue requestQueue = Volley
				.newRequestQueue(getApplicationContext());
		requestQueue.add(request);
		Request<Bitmap> request2 = new Request<Bitmap>("", null) {

			@Override
			protected Response<Bitmap> parseNetworkResponse(
					NetworkResponse response) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void deliverResponse(Bitmap response) {
				// TODO Auto-generated method stub

			}

		};

	}

	Bitmap bitmap;
	Handler handler = new Handler();

	@OnClick(R.id.imgbt)
	public void test(View v) {

		new Thread() {
			public void run() {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 4;
				bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.g6, options);
				// Bitmap.
				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						imageView.setImageBitmap(bitmap);
					}
				});
			};
		}.start();

	}

	@OnClick(R.id.cycbt)
	public void test2(View v) {

		if (!bitmap.isRecycled()) {
			bitmap.recycle();

			Log.i("Log", "in isRecycled");
			System.gc();
			bitmap = null;
			// Bitmap bitmap = ((BitmapDrawable) (imageView.getDrawable()))
			// .getBitmap();
			// bitmap.recycle();

			// imageView.setImageBitmap(null);

			imageView = null;

			Log.i("Log", "in isRecycled2");
			System.gc();

			// invali

		}

	}
}
