package com.colin.skinlibrary.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatTextView;

import com.colin.skinlibrary.R;
import com.colin.skinlibrary.SkinManager;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

import static com.colin.skinlibrary.SkinManager.isGragyMode;

public class SkinTextView extends AppCompatTextView implements SkinCompatSupportable {
    public int mTextStyle = 0;//0 默认  1首页底栏
    public boolean isNeedGray = false;
    private SkinCompatTextHelper mTextHelper;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    public SkinTextView(Context context) {
        this(context, null);
    }

    public SkinTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);

    }

    public SkinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
        mTextHelper = SkinCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attrs, defStyleAttr);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        if (attrs!=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinImageView);
            isNeedGray = typedArray.getBoolean(R.styleable.SkinImageView_isNeedGray, false);
        }
        applyCoverSkin();
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.onSetTextAppearance(context, resId);
        }
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(
            @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        if (mTextHelper != null) {
            mTextHelper.onSetCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(
            @DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if (mTextHelper != null) {
            mTextHelper.onSetCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
        if (mTextHelper != null) {
            mTextHelper.applySkin();
        }
        applyCoverSkin();
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        applyCoverSkin();
    }

    public void applyCoverSkin() {
        try {
            if (SkinManager.isGragyMode()&&isNeedGray) {
                setTextColor(Color.GRAY);
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0f);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

                if (getBackground() != null) {
                    getBackground().setColorFilter(filter);
                }

                if (getCompoundDrawables().length > 0) {
                    for (Drawable d : getCompoundDrawables()) {
                        if (d != null) {
                            d.setColorFilter(filter);
                        }
                    }
                }
            } else {
                if (getBackground() != null) {
                    getBackground().clearColorFilter();
                }
                if (getCompoundDrawables().length > 0) {
                    for (Drawable d : getCompoundDrawables()) {
                        if (d != null) {
                            d.clearColorFilter();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
