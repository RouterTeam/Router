package com.ifenghui.imageloaderlibrary;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

//"http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
public class ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView view, String imageUrl){
        if (view==null)return;
//        GlideImageLoader.getInstance().displayImage(view,imageUrl,view,20,null,null);
    }

    @BindingAdapter("imageDefaultUrl")
    public static void bindDefaultImageUrl(ImageView view, Object imageDefaultUrl){
        if (view==null)return;
//        imageDefaultUrl="http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
        GlideImageLoader.getInstance().displayWithDrable(view,imageDefaultUrl).resetTargetSize(view.getWidth(),view.getHeight()).intoTargetView(view);
    }


    @BindingAdapter("imageCircleUrl")
    public static void bindCircleImageUrl(ImageView view, Object imageCircleUrl){
        int defaultSrc=R.mipmap.header;
        if (view==null)return;
        if (imageCircleUrl==null)
            imageCircleUrl="http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
        GlideImageLoader.getInstance().displayCircleWithDrable(view,imageCircleUrl==null ? defaultSrc : imageCircleUrl).intoTargetView(view);
//        GlideImageLoader.getInstance().displayWithBlurRound(view,imageCircleUrl==null ? defaultSrc : imageCircleUrl,20,1000).intoTargetView(view);
    }
}
