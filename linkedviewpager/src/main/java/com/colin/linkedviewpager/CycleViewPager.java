package com.colin.linkedviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

public class CycleViewPager extends ViewPager {
    public CycleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CycleViewPager(Context context) {
        super(context);
    }

    FixedSpeedScroller mScroller = null;

    public FixedSpeedScroller getmScroller() {
        return mScroller;
    }

    public void controlViewPagerSpeed(int duration, Interpolator interpolator) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(), interpolator);
            field.set(this, scroller);
            scroller.setmDuration(duration);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 滑动距离及坐标 归还父控件焦点
    private float xDistance, yDistance, xLast, yLast;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if ((xDistance < yDistance)) {
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else {
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
