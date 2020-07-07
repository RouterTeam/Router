package com.colin.bottomnavigation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.colin.bottomnavigation.R;

public class LottieBottomNavigation extends ConstraintLayout implements View.OnClickListener {
    private Context context;
    private int currentSelectedIndex = 0;//默认选中第一个
    private ViewPager mViewPager;
    private LottieTabView mTabHome, mTabMag, mTabBook, mTabmine;
    public LottieBottomNavigation(Context context) {
        this(context,null);
    }

    public LottieBottomNavigation(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LottieBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context=context;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(context).inflate(R.layout.lottie_bottom_nav_layout,this);
        mTabHome = findViewById(R.id.tab_home);
        mTabMag = findViewById(R.id.tab_mag);
        mTabBook = findViewById(R.id.tab_book);
        mTabmine = findViewById(R.id.tab_mine);
        bindListeners();
    }


    /**
     * 切换选中状态
     */
    public void setCurrentIndex(int i) {
        if (this.currentSelectedIndex == i) return;
        this.currentSelectedIndex = i;
        resetTabStatus(mTabHome, this.currentSelectedIndex == 0);
        resetTabStatus(mTabMag, this.currentSelectedIndex == 1);
        resetTabStatus(mTabBook, this.currentSelectedIndex == 2);
        resetTabStatus(mTabmine, this.currentSelectedIndex == 3);
        if (mViewPager!=null)
            mViewPager.setCurrentItem(i,false);
    }

    /**
     * 绑定事件
     */
    private void bindListeners() {
        mTabHome.setOnClickListener(this);
        mTabMag.setOnClickListener(this);
        mTabBook.setOnClickListener(this);
        mTabmine.setOnClickListener(this);
    }

    public void setSwitchViewPager(ViewPager viewPager){
        this.mViewPager=viewPager;
        this.mViewPager.setOffscreenPageLimit(4);
    }

    private void resetTabStatus(LottieTabView mTabView, boolean selected) {
        if (mTabView != null)
            mTabView.setSelected(selected);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tab_home) {
            setCurrentIndex(0);
        } else if (id == R.id.tab_mag) {
            setCurrentIndex(1);
        } else if (id == R.id.tab_book) {
            setCurrentIndex(2);
        } else if (id == R.id.tab_mine) {
            setCurrentIndex(3);
        }
    }
}
