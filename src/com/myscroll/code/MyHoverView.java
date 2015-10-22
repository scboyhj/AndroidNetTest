package com.myscroll.code;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MyHoverView extends FrameLayout {

	int topHeight = 0;
	ViewGroup headView;
	ViewGroup contentView;
	MySrollView mySrollView;

	public MyHoverView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void SetTopView(final int id) {
		post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				View fv = getChildAt(0);
				removeAllViews();

				mySrollView = new MySrollView(getContext(), MyHoverView.this);
				mySrollView.addView(fv);
				addView(mySrollView);

				headView = (ViewGroup) findViewById(id);
				contentView = (ViewGroup) headView.getChildAt(0);
				topHeight = headView.getTop();

			}
		});
	}

	public void ChangeScrollPosition(int pos) {

		if (headView != null) {

			if (pos > topHeight && contentView.getParent() == headView) {
				headView.removeView(contentView);
				addView(contentView);
			} else if (pos < topHeight && contentView.getParent() == MyHoverView.this) {

				removeView(contentView);
				headView.addView(contentView);

			}

		}
	}

}
