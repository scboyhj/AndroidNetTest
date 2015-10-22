package com.myscroll.code;

import android.content.Context;
import android.widget.ScrollView;

public class MySrollView extends ScrollView {
	MyHoverView hoverView;
	public MySrollView(Context context, MyHoverView hoverView) {
		super(context);
		// TODO Auto-generated constructor stub
		this.hoverView=hoverView;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		hoverView.ChangeScrollPosition(t);
	}
}
