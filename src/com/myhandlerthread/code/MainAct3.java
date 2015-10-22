package com.myhandlerthread.code;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.androidnettest.code.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainAct3 extends Activity {
	RequestQueue queue;
	@ViewInject(R.id.img)
	ImageView imageView;
	@ViewInject(R.id.mmtv)
	TextView textView;
	Handler handler = new Handler();

	@ViewInject(R.id.refreshView)
	PullToRefreshListView pullToRefreshListView;

	private ImageRequest imageRequest;
	private ImageRequest imageRequest2;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagelay);
		ViewUtils.inject(this);
		queue = Volley.newRequestQueue(getApplicationContext());
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(getApplicationContext());

		ImageLoader.getInstance().init(configuration);
		// pullToRefreshListView = (PullToRefreshScrollView)
		// findViewById(R.id.refreshView);
		pullToRefreshListView.getLoadingLayoutProxy().setLastUpdatedLabel(
				"setLastUpdatedLabel");
		pullToRefreshListView.getLoadingLayoutProxy()
				.setRefreshingLabel("正在加载");// ("setLastUpdatedLabel");
		pullToRefreshListView.getLoadingLayoutProxy().setPullLabel("下拉刷新");// ("setLastUpdatedLabel");
		pullToRefreshListView.getLoadingLayoutProxy().setReleaseLabel("下拉刷新");// ("setRefreshingLabel");//("setPullLabel");

		// DateUtils.

		pullToRefreshListView.setMode(Mode.BOTH);
		imageRequest = new ImageRequest(
				"http://pic35.nipic.com/20131114/13610382_233444193301_2.jpg",
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						// TODO Auto-generated method stub

						imageView.setImageBitmap(response);
						pullToRefreshListView.onRefreshComplete();
					}
				}, 0, 0, Config.RGB_565, null);

		imageRequest2 = new ImageRequest(
				"http://pic37.nipic.com/20140209/8821914_163234218136_2.jpg",
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						// TODO Auto-generated method stub

						String label = DateUtils.formatDateTime(
								getApplicationContext(),
								System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);
						pullToRefreshListView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(label);
						imageView.setImageBitmap(response);
						pullToRefreshListView.onRefreshComplete();
					}
				}, 0, 0, Config.RGB_565, null);

		// pullToRefreshListView
		// .setOnRefreshListener(new OnRefreshListener<ScrollView>() {
		//
		// @Override
		// public void onRefresh(
		// PullToRefreshBase<ScrollView> refreshView) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// });
		pullToRefreshListView
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						queue.add(imageRequest2);
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						queue.add(imageRequest);
					}
				});

	}

	// @OnClick(R.id.imgbt)
	// public void test(View v) {
	//
	// new LoadThreadFactory2<Bitmap>().LoadImage(
	// "http://pic2.ooopic.com/01/03/51/25b1OOOPIC19.jpg",
	// new LisenserToView2<Bitmap>() {
	//
	// @Override
	// public void onBackToView(Bitmap g) {
	// // TODO Auto-generated method stub
	// imageView.setImageBitmap(g);
	// }
	//
	// @Override
	// public Bitmap parseInStream(InputStream in) {
	// // TODO Auto-generated method stub
	//
	// Bitmap bitmap = BitmapFactory.decodeStream(in);
	// return bitmap;
	// }
	// });
	// }

	@OnClick(R.id.cycbt)
	public void test2(View v) {
		// MyTask myTask = new
		// MyTask("http://img3.3lian.com/2013/s1/20/d/57.jpg");
		// myTask.run();
		Bitmap bitmap = ImageLoader.getInstance().loadImageSync(
				"http://www.5068.com/uploads/allimg/130330/1152423Z0-0.jpg");
		imageView.setImageBitmap(bitmap);
		// ImageLoader.getInstance().loadImage(
		// "http://www.5068.com/uploads/allimg/130330/1152423Z0-0.jpg",
		// new ImageLoadingListener() {
		//
		// @Override
		// public void onLoadingStarted(String imageUri, View view) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onLoadingFailed(String imageUri, View view,
		// FailReason failReason) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onLoadingComplete(String imageUri, View view,
		// Bitmap loadedImage) {
		// // TODO Auto-generated method stub
		// imageView.setImageBitmap(loadedImage);
		// }
		//
		// @Override
		// public void onLoadingCancelled(String imageUri, View view) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

	}

	class MyTask implements Runnable {
		String urlString;

		public MyTask(String s) {
			// TODO Auto-generated constructor stub
			urlString = s;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			URL url;
			try {
				url = new URL(urlString);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				InputStream inputStream = connection.getInputStream();
				final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				handler.post(new Runnable() {

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
	}

}
