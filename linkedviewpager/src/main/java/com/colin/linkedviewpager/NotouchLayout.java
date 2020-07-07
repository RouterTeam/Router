package com.colin.linkedviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class NotouchLayout extends RelativeLayout {

    public NotouchLayout(Context context) {
        super(context);
    }

    public NotouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
    	return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	return true;
    }

}