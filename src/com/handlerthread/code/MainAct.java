package com.handlerthread.code;

import com.androidnettest.code.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

class t extends TextView{

	public t(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}


public class MainAct extends Activity {

	@OnClick(R.id.bt)
	public void test(View v) {
		handler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("tag", "handlerPost id->"
						+ Thread.currentThread().getId());
				handler.sendEmptyMessage(1);
				handler.sendEmptyMessage(2);
			}
		});
	}

	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.handlerlay);

		ViewUtils.inject(this);
		
		TextView textView = null;
		textView.measure(1, 1);
		
		
		Log.i("tag", "UIThread id->" + Thread.currentThread().getId());
		HandlerThread handlerThread = new HandlerThread("ht");

		handlerThread.start();
		Log.i("tag", "handlerThread id->" + handlerThread.getId());

		handler = new Handler(handlerThread.getLooper(), new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				Log.i("tag", "receive message.whatA=" + msg.what
						+ "UIThread id->" + Thread.currentThread().getId());
				if (msg.what == 1) {
					return true;// 不再向外层传递
				} else {
					return false; // 外层的handleMessage() 继续执行
				}

			}
		}) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("receive message.whatB=" + msg.what
						+ "UIThread id->" + Thread.currentThread().getId());
			}
		};
	}
}
