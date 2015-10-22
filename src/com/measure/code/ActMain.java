package com.measure.code;

import java.util.ArrayList;
import java.util.List;

import com.androidnettest.code.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class ActMain extends Activity {

	@ViewInject(R.id.t1)
	TextView textView1;
	@ViewInject(R.id.t2)
	TextView textView2;
	@ViewInject(R.id.pg)
	ViewPager viewPager;

	@ViewInject(R.id.btline)
	View btLine;

	int btlineWidth;
	int currentindex = 0;

	List<ViewGroup> groups = new ArrayList<ViewGroup>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measurelay);
		ViewUtils.inject(this);
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		btLine.measure(w, h);

		btlineWidth = getWindow().getWindowManager().getDefaultDisplay()
				.getWidth() / 3;
		Log.i("tag", "" + btlineWidth + " "
				+ getWindow().getWindowManager().getDefaultDisplay().getWidth());

		// int w = View.MeasureSpec.makeMeasureSpec(0,
		// View.MeasureSpec.EXACTLY);
		// int h = View.MeasureSpec.makeMeasureSpec(0,
		// View.MeasureSpec.EXACTLY);
		//
		// Log.i("tag", ""+w+" "+h);
		// textView1.measure(w,h);
		// int th = textView1.getMeasuredHeight();
		// int tw = textView1.getMeasuredHeight();
		// Log.i("tag", ""+th+" "+tw);
		// textView2.setText("" + th + "  " + tw);

		ViewGroup group1 = (ViewGroup) LayoutInflater.from(
				getApplicationContext()).inflate(R.layout.lay1, null);
		ViewGroup group2 = (ViewGroup) LayoutInflater.from(
				getApplicationContext()).inflate(R.layout.lay2, null);
		ViewGroup group3 = (ViewGroup) LayoutInflater.from(
				getApplicationContext()).inflate(R.layout.lay3, null);
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);
		viewPager.setAdapter(new MyPgAdapter());
		viewPager.setOnPageChangeListener(new MyListener());
		viewPager.setOffscreenPageLimit(2);
	}

	class MyListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub

			Animation animation = new TranslateAnimation(currentindex
					* btlineWidth, arg0 * btlineWidth, 0, 0);
			animation.setFillAfter(true);
			animation.setDuration(200);
			btLine.startAnimation(animation);

			currentindex = arg0;

		}
	}

	class MyFA extends FragmentPagerAdapter {

		public MyFA(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	class MyPgAdapter extends PagerAdapter {
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(groups.get(position));

			Log.i("tag", "destroyItem:" + position);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(groups.get(position));
			Log.i("tag", "instantiateItem:" + position);
			return groups.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		// textView2.setText("" + textView1.getHeight() + "  "
		// + textView1.getWidth());

	}

	@OnClick(R.id.mbt)
	public void test(View v) {

	}

}
