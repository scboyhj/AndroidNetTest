package com.myviewtest.code;

import android.content.Context;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyView extends LinearLayout {

	ScrollView scrollView;
	FrameLayout frameLayout;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		// scrollView = new ScrollView(context, attrs);
		// frameLayout = new FrameLayout(getContext());
		// frameLayout.addView(scrollView, ViewGroup.LayoutParams.MATCH_PARENT,
		// ViewGroup.LayoutParams.MATCH_PARENT);
		setOrientation(LinearLayout.VERTICAL);
		initview();
	}

	private void initview() {
		// TODO Auto-generated method stub
		// TextView textView=new TextView(getContext());
		// textView.setId(-1);
		// addView(textView, -1);
		// Button button=new Button(getContext());
		// button.setId(0);
		// addView(button, 0);
		//
		// Button button2=new Button(getContext());
		// button2.setText("button2");
		// button2.setId(-2);
		// addView(button2, -2);

		// super.addView(frameLayout, -1);

		Button textView = new Button(getContext());
		textView.setId(3);
		textView.setText("Index=0");
		super.addView(textView, 0);
		
		Button button = new Button(getContext());
		button.setId(0);
		button.setText("Index=-1");
		super.addView(button, -1);
		
		Button button3 = new Button(getContext());
		button3.setId(3);
		button3.setText("Index=-1");
		super.addView(button3, -1);
		
		Button button2 = new Button(getContext());
		button2.setText("button2");
		button2.setId(1);
		button2.setText("Index=1");
		super.addView(button2, 1);

	}

}
