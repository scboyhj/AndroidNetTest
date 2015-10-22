package com.androidnettest.code;

import android.os.Handler;
import android.os.Message;

interface IMyHandlerMsg {
	void HandlerMyMasg();
}

public class MyHandler extends Handler{

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		((IHandlerResult)msg.obj).HandlerResult();
	}

}
