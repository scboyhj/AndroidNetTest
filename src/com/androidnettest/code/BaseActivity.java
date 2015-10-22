package com.androidnettest.code;

import android.os.Message;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends ActionBarActivity {

	MyHandler handler;

	public BaseActivity() {
		// TODO Auto-generated constructor stub
		handler = new MyHandler();
	}

	public void NotifyHanlderDeal(IHandlerResult handlerResult) {
		Message message = handler.obtainMessage();
		message.obj = handlerResult;
		message.sendToTarget();

	}
}
