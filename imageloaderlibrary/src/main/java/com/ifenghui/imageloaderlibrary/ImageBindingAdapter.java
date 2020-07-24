package com.ifenghui.imageloaderlibrary;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

//"http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
public class ImageBindingAdapter {
    private static RequestOptions defaultOptions = new RequestOptions().placeholder(R.mipmap.item_default).error(R.mipmap.item_default).diskCacheStrategy(DiskCacheStrategy.DATA).useAnimationPool(true);

    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView view, String imageUrl){
        if (view==null)return;
//        GlideImageLoader.getInstance().displayImage(view,imageUrl,view,20,null,null);
    }

    @BindingAdapter("imageDefaultUrl")
    public static void bindDefaultImageUrl(ImageView view, Object imageDefaultUrl){
        if (view==null)return;
//        GlideImageLoader.getInstance().displayWithBitmap(view,imageDefaultUrl).resetTargetSize(view.getWidth(),view.getHeight()).intoTargetView(view);
        GlideImageLoader.getInstance().displayWithBlur(view,imageDefaultUrl,20).intoTargetView(view);
    }


    @BindingAdapter("imageCircleUrl")
    public static void bindCircleImageUrl(ImageView view, Object imageCircleUrl){
        int defaultSrc=R.mipmap.header;
        if (view==null)return;
        GlideImageLoader.getInstance().displayWithBlurRound(view,imageCircleUrl==null ? defaultSrc : imageCircleUrl,20,1000).intoTargetView(view);
    }
}
