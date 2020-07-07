package com.colin.skinlibrary.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;

import com.colin.skinlibrary.R;
import com.colin.skinlibrary.SkinManager;

import skin.support.widget.SkinCompatImageView;

public class SkinImageView extends SkinCompatImageView {
    private Context mContext;
    private int filterColor;
    public boolean isNeedGray = false;
    public SkinImageView(Context context) {
        this(context,null);
    }

    public SkinImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SkinImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs){
        mContext=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinImageView);
        isNeedGray = typedArray.getBoolean(R.styleable.SkinImageView_isNeedGray, false);
        filterColor = typedArray.getColor(R.styleable.SkinImageView_filterColor, 0x50000000);
        applyCoverSkin();
    }

    @Override
    public void applySkin() {
        super.applySkin();
        applyCoverSkin();
    }

    /**
     *
     */
    private void applyCoverSkin(){
        if (SkinManager.isGragyMode()&&isNeedGray){
            addGrayCover();
        }else if (SkinManager.checkIsDefaultMode()){
            clearColorFilter();
            if (getBackground() != null) {
                getBackground().clearColorFilter();
            }
        }else {
            setColorFilter(filterColor);
        }
    }

    /**
     * 置灰
     */
    private void addGrayCover(){
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f);//饱和度
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        setColorFilter(filter);
        if (getBackground() != null) {
            getBackground().setColorFilter(filter);
        }
    }
}
