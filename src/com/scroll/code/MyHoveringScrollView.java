package com.scroll.code;

import java.util.LinkedHashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MyHoveringScrollView extends FrameLayout {

	ViewGroup mContentView; 
	ViewGroup mTopView; 
	View mTopContent; 
	int mTopViewTop; 

	public MyHoveringScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		

		
	}
	
	public void init() {
        post(new Runnable() {
            @Override
            public void run() {
                mContentView = (ViewGroup) getChildAt(0);
                removeAllViews();
  
                MyScrollView scrollView = new MyScrollView(getContext(), MyHoveringScrollView.this);
                scrollView.addView(mContentView);
                addView(scrollView);
  
            }
        });
    }
	public void setTopView(final int id) {
        post(new Runnable() {
            @Override
            public void run() {
            	
            	
                mContentView = (ViewGroup) getChildAt(0);
                removeAllViews();
  
                MyScrollView scrollView = new MyScrollView(getContext(), MyHoveringScrollView.this);
                scrollView.addView(mContentView);
                addView(scrollView);
            	
                mTopView = (ViewGroup) findViewById(id);
  
                int height = mTopView.getChildAt(0).getMeasuredHeight();
                ViewGroup.LayoutParams params = mTopView.getLayoutParams();
                params.height = height;
               // mTopView.setLayoutParams(params);
                mTopViewTop = mTopView.getTop();
                mTopContent = mTopView.getChildAt(0);
  
            }
        });
    }
	public void onScroll(final int scrollY) {
        post(new Runnable() {
            @Override
            public void run() {
                if (mTopView == null
                        ) return;
                Log.i("t", "--->"+"scrollY:"+scrollY+"  mTopViewTop:"+mTopViewTop);
                if (scrollY >= mTopViewTop
                        && mTopContent.getParent() == mTopView) {
                    mTopView.removeView(mTopContent);
                    addView(mTopContent);
                } else if (scrollY < mTopViewTop
                        && mTopContent.getParent() == MyHoveringScrollView.this) {
                    removeView(mTopContent);
                    mTopView.addView(mTopContent);
                }
  
            }
        });
    }

}
