package com.scroll.code;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	 private MyHoveringScrollView mScrollView;
	  
     public MyScrollView(Context context, MyHoveringScrollView scrollView) {
         super(context);
         mScrollView = scrollView;
     }


     @Override
     protected void onScrollChanged(int l, int t, int oldl, int oldt) {
         super.onScrollChanged(l, t, oldl, oldt);
         mScrollView.onScroll(t);
   
     }
}
