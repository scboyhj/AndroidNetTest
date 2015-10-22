package com.measure.code;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import com.androidnettest.code.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class FragMainAct extends FragmentActivity {

	@ViewInject(R.id.pg)
	ViewPager pager;
	List<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.fraglay);
		ViewUtils.inject(this);
		F1 f1 = new F1();
		F2 f2 = new F2();
		F3 f3 = new F3();
		fragments.add(f1);
		fragments.add(f2);
		fragments.add(f3);

		pager.setAdapter(new FgAdapter(getSupportFragmentManager()));
		pager.setOffscreenPageLimit(2);
	}

	class FgAdapter extends FragmentPagerAdapter {
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			Log.i("tag", "destroyItem:" + position + "");
			super.destroyItem(container, position, object);
		}

		public FgAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Log.i("tag", "getItem:" + arg0 + "");
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	}
}
