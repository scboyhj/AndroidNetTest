package com.myscroll.code;

import com.androidnettest.code.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainAct extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myscroll);
		MyHoverView myHoverView = (MyHoverView) findViewById(R.id.myhover);
		myHoverView.SetTopView(R.id.myhead);

		AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}
}
