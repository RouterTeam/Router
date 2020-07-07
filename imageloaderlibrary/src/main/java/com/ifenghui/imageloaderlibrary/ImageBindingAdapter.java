package com.ifenghui.imageloaderlibrary;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
//"http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
public class ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView view, String imageUrl){
        if (imageUrl==null)return;
        GlideImageLoader.getInstance().displayImage(view,imageUrl,view,20,null,null);
    }

    @BindingAdapter("imageDefaultUrl")
    public static void bindDefaultImageUrl(ImageView view, String imageDefaultUrl){
        GlideImageLoader.getInstance().displayImage(view,imageDefaultUrl,view,0,null,null);
    }
}
