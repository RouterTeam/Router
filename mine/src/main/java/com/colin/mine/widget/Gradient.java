package com.colin.mine.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.colin.mine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * end
 */

public class Gradient extends RelativeLayout {

    private List<ImageView> imageViews;
    private List<Animation> outAnim;//淡出动画
    private List<Animation> inAnim;//淡入动画
    private Context mContext;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int couot;
    private long time=10000;//动画间隔时间


    public Gradient(Context context) {
        this(context, null);
    }

    public Gradient(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    /**
     * 设置图片
     * @param imageViews
     */
    public void setImageViews(List<ImageView> imageViews) {
        this.imageViews = imageViews;
        for (int i = 0; i < imageViews.size(); i++) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1,-1);
            addView(imageViews.get(i),layoutParams);
        }
        createAnim();
        start();

    }

    public void setImageResources(List<Integer> imageResources){
        imageViews=new ArrayList<>();
        for (int i = 0; i < imageResources.size(); i++) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1,-1);
            ImageView inflate = (ImageView) LayoutInflater.from(mContext).inflate(R.layout.item_image_layout, null);
            inflate.setImageResource(imageResources.get(i));
            addView(inflate,layoutParams);
            imageViews.add(inflate);
        }
        createAnim();
        start();
    }

    /**
     * 开启动画
     */
    private void start() {
        final int size = imageViews.size();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int i = couot % size;
                if (couot < size) {
                    if (i == size - 1) {
                        ImageView imageView = imageViews.get(0);
                        imageView.startAnimation(outAnim.get(0));
                        ImageView imageView2 = imageViews.get(size - 1);
                        imageView2.startAnimation(inAnim.get(size - 1));
                    } else {
                        //当前的淡出,下一张淡入
                        ImageView imageView = imageViews.get(size - 1 - i);
                        imageView.startAnimation(outAnim.get(size - 1 - i));
                    }
                } else {
                    if (i == size - 1) {
                        //当显示到最后一张的时候,要跳到第一张
                        ImageView imageView = imageViews.get(0);
                        imageView.startAnimation(outAnim.get(0));

                        ImageView imageView2 = imageViews.get(size - 1);
                        imageView2.startAnimation(inAnim.get(size - 1));


                    } else {
                        //当前的淡出,下一张淡入
                        ImageView imageView = imageViews.get(size - 1 - i);
                        imageView.startAnimation(outAnim.get(size - 1 - i));

                        ImageView imageView2 = imageViews.get(size - 2 - i);
                        imageView2.startAnimation(inAnim.get(size - 2 - i));

                    }
                }
                couot++;
                handler.postDelayed(this, time*2);
            }
        },time);
    }

    /**
     * 创建动画
     */
    private void createAnim() {
        outAnim = new ArrayList<>();
        inAnim = new ArrayList<>();
        for (int i = 0; i < imageViews.size(); i++) {
            Animation zoomOutAwayAnim = createZoomOutAwayAnim();
            zoomOutAwayAnim.setFillAfter(true);
            outAnim.add(zoomOutAwayAnim);

            Animation zoomOutNearAnim = createZoomOutNearAnim();
            zoomOutNearAnim.setFillAfter(true);
            inAnim.add(zoomOutNearAnim);

        }
    }


    /** 创建一个淡出缩小的动画 */
    public Animation createZoomOutAwayAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // 创建一个淡出的动画
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(time);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        // 创建一个缩小的动画
        /*anim = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);*/
        return ret;
    }
    /** 创建一个淡入缩小的动画 */
    public Animation createZoomOutNearAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // 创建一个淡入的动画
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(time);
        anim.setInterpolator(new LinearInterpolator());
        ret.addAnimation(anim);
        // 创建一个缩小的动画
        /*anim = new ScaleAnimation(3, 1, 3, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);*/
        return ret;
    }
}