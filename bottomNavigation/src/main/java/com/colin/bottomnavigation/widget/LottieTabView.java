package com.colin.bottomnavigation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.colin.bottomnavigation.R;
import com.colin.bottomnavigation.utils.DensityUtils;

/**
 *
 */
public class LottieTabView extends ConstraintLayout implements TabAnimView.BethelChangeListener {

    private int mTextNormalColor;
    private int mTextSelectColor;
    private float mTextSize;
    private String mTabName;
    private Drawable mIconNormal;
    private String mAnimationPath;
    private LottieAnimationView mLottieView;
    private TextView mTabNameView;
    private boolean isSelected;
    private boolean isBulge;

    private TabAnimView tabAnimView;

    private int startRun = 1;

    private Context context;

    public LottieTabView(Context context) {
        this(context, null);
    }

    public LottieTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LottieTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        try {
            this.context = context;
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Ui_LottieTabView);
            mTextNormalColor = ta.getColor(R.styleable.Ui_LottieTabView_text_normal_color, Color.BLACK);
            mTextSelectColor = ta.getColor(R.styleable.Ui_LottieTabView_text_selected_color, Color.BLUE);
            mTextSize = ta.getDimension(R.styleable.Ui_LottieTabView_text_size, DensityUtils.dp2px(context, 5));
            mIconNormal = ta.getDrawable(R.styleable.Ui_LottieTabView_icon_normal);
            mAnimationPath = ta.getString(R.styleable.Ui_LottieTabView_lottie_path);
            mTabName = ta.getString(R.styleable.Ui_LottieTabView_tab_name);
            isSelected = ta.getBoolean(R.styleable.Ui_LottieTabView_tab_selected, false);
            isBulge = ta.getBoolean(R.styleable.Ui_LottieTabView_top_bulge, false);
            ta.recycle();
        }catch (Exception e){}
    }

    /**
     * 布局引入
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
            LayoutInflater.from(context).inflate(R.layout.lottie_tab_view, this);
            mLottieView = findViewById(R.id.animation_view);

            mLottieView.setRepeatCount(0);
            mTabNameView = findViewById(R.id.tab_name);
            tabAnimView = findViewById(R.id.tab_anim_view);
            mTabNameView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            mTabNameView.setTextColor(mTextNormalColor);
            mTabNameView.setText(mTabName);
            tabAnimView.addBethelChengeListener(this);
            setSelected(isSelected);
        }catch (Exception e){}
    }

    /**
     * 切换选中状态
     */
    public void setSelected(boolean selected){
       if (selected){
           if (TextUtils.isEmpty(mAnimationPath)) {

           } else {
               if (startRun == 1) {

                   mLottieView.setAnimation(mAnimationPath);
                   mLottieView.playAnimation();
                   if (isBulge) {
                       tabAnimView.startAnim();
                   }
                   mTabNameView.setTextColor(mTextSelectColor);

                   startRun = 2;
               }
           }
       }else {
           startRun = 1;
           mTabNameView.setTextColor(mTextNormalColor);
           mLottieView.clearAnimation();
           if (isBulge) {
               tabAnimView.resetAnim();
           }
           mLottieView.setImageDrawable(mIconNormal);
       }
    }

    /**
     * 做弹起动画
     */
    @Override
    public void bethChange(float value) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mLottieView.getLayoutParams();
        layoutParams.bottomMargin = (int) Math.abs(value);
        mLottieView.setLayoutParams(layoutParams);
    }
}