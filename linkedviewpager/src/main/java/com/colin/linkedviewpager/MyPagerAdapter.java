package com.colin.linkedviewpager;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {

	private ArrayList<View> mPageViews;

	public MyPagerAdapter(ArrayList<View> mPageViews) {
		super();
		this.mPageViews = mPageViews;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO Auto-generated method stub
//		((ViewPager) arg0).removeView(mPageViews.get(arg1));
	}

	@Override
	public Object instantiateItem(View arg0, int position) {

		//对ViewPager页号求模取出View列表中要显示的项
		position %= mPageViews.size();
		if (position < 0) {
			position += mPageViews.size();
		}
		View view = mPageViews.get(position);
		//如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp = view.getParent();
		if (vp != null) {
			((ViewGroup)vp).removeView(view);
		}
		((ViewPager) arg0).addView(view);
		//add listeners here if necessary
		return view;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishUpdate(View arg0) {
		// TODO Auto-generated method stub

	}

}