package com.scroll.code;

import com.androidnettest.code.R;

import android.app.Activity;
import android.os.Bundle;

public class MainAct extends Activity {
	MyHoveringScrollView view_hover;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.mylayout);
	    view_hover = (MyHoveringScrollView) findViewById(R.id.view_hover);
	    view_hover.setTopView(R.id.top);
	}
}
