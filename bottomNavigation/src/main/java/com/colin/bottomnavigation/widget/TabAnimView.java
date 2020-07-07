package com.colin.bottomnavigation.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.colin.bottomnavigation.R;
import com.colin.bottomnavigation.utils.DensityUtils;
import com.colin.skinlibrary.SkinManager;

import skin.support.widget.SkinCompatSupportable;

public class TabAnimView extends View implements SkinCompatSupportable {

    private static final String TAG = "TabAnimView";

    private static int COLOR_BG_WHITE = Color.parseColor("#FFFFFF");
    private static int COLOR_BG_BITMAP = Color.parseColor("#A3A3A3");
    private BethelChangeListener bethelChangeListener;
    private int width;
    private int height;
    //内容绘制大小
    private int contentSize;
    /**
     * 基准线 外部线距离顶上得距离
     */
    private int lineToTop;
    private RectF rectF;
    private Bitmap bpSel;
    private int bitmapPadding;
    private Paint bitmapPaint;
    private Paint bitmapBgPaint;
    private Paint paintBg;
    private Paint paintBgStroke;

    /**
     * anim
     */
    private int startValue = 0;
    private int halfValue = -55;
    private int endValue = -35;
    private static final int ANIM_TIME = 300;
    private int value = 0;
    private ValueAnimator animator;


    public TabAnimView(Context context) {
        super(context);
        init(context);
    }

    public TabAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //绘制内容区域
        contentSize = DensityUtils.dp2px(context, 50);
        //线距离顶部距离
        lineToTop = DensityUtils.dp2px(context, 50);
        bitmapPadding = DensityUtils.dp2px(context, 5);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        bitmapBgPaint = new Paint();
//        bitmapBgPaint.setColor(COLOR_BG_BITMAP);
        bitmapBgPaint.setAntiAlias(true);
        bitmapBgPaint.setStyle(Paint.Style.FILL);

        paintBg = new Paint();
//        paintBg.setColor(COLOR_BG_WHITE);
        paintBg.setAntiAlias(true);
        paintBg.setStyle(Paint.Style.FILL);

        paintBgStroke = new Paint();
        paintBgStroke.setColor(context.getResources().getColor(R.color.ui_A3A3A3));
        paintBgStroke.setStrokeWidth(context.getResources().getDimension(R.dimen.line_height));
        paintBgStroke.setAntiAlias(true);
        paintBgStroke.setStyle(Paint.Style.STROKE);

        halfValue = -DensityUtils.dp2px(context, 35);
        endValue = -DensityUtils.dp2px(context, 25);
        changeSkinBg();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF = new RectF((((float) width - (float) contentSize) / 2),
                lineToTop + value,
                (float) width - ((float) width - (float) contentSize) / 2,
                height + value);

        if (value < 0) {
            Path path = new Path();
            //绘制第一段圆弧
            path.moveTo(rectF.left - 20, lineToTop);
            float firstCubicHight = ((float) lineToTop - rectF.top) / 8;
            float end = (float) lineToTop - firstCubicHight;
            path.cubicTo(
                    rectF.left - 20,
                    (float) lineToTop - firstCubicHight / 16,
                    rectF.left,
                    (float) lineToTop - firstCubicHight / 12,
                    rectF.left + 10,
                    end
            );

            //绘制第二段圆弧
            path.quadTo(
                    rectF.left + contentSize / 2,
                    rectF.top * 0.9f,
                    rectF.right - 10,
                    end
            );
            //绘制第三段圆弧，和第一段对称
            path.cubicTo(
                    rectF.right,
                    (float) lineToTop - firstCubicHight / 12,
                    rectF.right + 10,
                    (float) lineToTop - firstCubicHight / 16,
                    rectF.right + 20,
                    lineToTop
            );

            Path pathBg = new Path();
            pathBg.addPath(path);
            pathBg.lineTo(rectF.right + 20, height);
            pathBg.lineTo(rectF.left - 20, height);
            pathBg.lineTo(rectF.left - 20, lineToTop);
            canvas.drawPath(pathBg, paintBg);//绘制白色背景

            canvas.drawPath(path, paintBgStroke);//绘制曲线

        }

        if (bpSel != null) {
            int bpSize = height - lineToTop;

            rectF = new RectF((((float) width - bpSize) / 2) + bitmapPadding,
                    (float) lineToTop + (float) value * 0.6f + bitmapPadding,
                    (float) width - ((float) width - bpSize) / 2 - bitmapPadding,
                    (float) lineToTop + bpSize + (float) value * 0.6f - bitmapPadding);
            canvas.drawOval(rectF, bitmapBgPaint);

            canvas.drawBitmap(bpSel,
                    new Rect(0, 0, bpSel.getWidth(), bpSel.getHeight()),
                    new RectF(rectF.left + bitmapPadding,
                            rectF.top + bitmapPadding,
                            rectF.right - bitmapPadding,
                            rectF.bottom - bitmapPadding),
                    bitmapPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (width == 0) {
            width = getMeasuredWidth();
        }
        if (height == 0) {
            height = getMeasuredHeight();
        }
    }

    private void initAnim() {
        value = startValue;
        animator = ValueAnimator.ofInt(startValue, halfValue, endValue);
//        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                value = (int) valueAnimator.getAnimatedValue();
                if (bethelChangeListener != null)
                    bethelChangeListener.bethChange(value * 0.6f);
                invalidate();
            }
        });
        animator.setDuration(ANIM_TIME);
        animator.start();
    }

    public void startAnim() {
        initAnim();
    }

    public void resetAnim() {
        if (animator != null && animator.isRunning()) {
            animator.end();
        }
        value = 0;
        if (bethelChangeListener != null)
            bethelChangeListener.bethChange(value * 0.4f);
        invalidate();
    }

    public void setBitmapRes(int drawableRes) {
        bpSel = BitmapFactory.decodeResource(getContext().getResources(), drawableRes);
        invalidate();
    }


    public void addBethelChengeListener(BethelChangeListener l) {
        this.bethelChangeListener = l;
    }

    @Override
    public void applySkin() {
        changeSkinBg();
        invalidate();
    }


    private void changeSkinBg() {
        if (SkinManager.checkIsDefaultMode()) {
            COLOR_BG_WHITE = Color.parseColor("#FFFFFF");
        } else {
            COLOR_BG_WHITE = Color.parseColor("#1D1D1E");
        }
        if (paintBg != null)
            paintBg.setColor(COLOR_BG_WHITE);
        if (bitmapBgPaint != null)
            bitmapBgPaint.setColor(COLOR_BG_BITMAP);
    }

    public interface BethelChangeListener {
        void bethChange(float value);
    }
}
