package com.ifenghui.commonlibrary.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = true;
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        try {
            if (noScroll)
                return false;
            else
                return super.onTouchEvent(arg0);
        } catch (Exception e) {

        }
        return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        try {
            if (noScroll)
                return false;
            else
                return super.onInterceptTouchEvent(arg0);
        } catch (Exception e) {

        }
        return super.onInterceptTouchEvent(arg0);
    }
}
