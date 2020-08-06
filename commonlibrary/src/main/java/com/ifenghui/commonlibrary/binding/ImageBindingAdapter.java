package com.ifenghui.commonlibrary.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.library.GlideImageLoader;

public class ImageBindingAdapter {

    @BindingAdapter("imageDefaultUrl")
    public static void bindDefaultImageUrl(ImageView view, Object imageDefaultUrl) {
        if (view == null) return;
//        if (imageDefaultUrl!=null)
//            Log.e("-----",imageDefaultUrl.toString());
        GlideImageLoader.getInstance().displayWithDrawable(view, imageDefaultUrl).resetTargetSize(view.getWidth(), view.getHeight()).intoTargetView(view);
    }


    @BindingAdapter("imageCircleUrl")
    public static void bindCircleImageUrl(ImageView view, Object imageCircleUrl) {
        if (view == null) return;
        if (imageCircleUrl == null)
            imageCircleUrl = "http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif";
        GlideImageLoader.getInstance().displayCircleWithDrawable(view, imageCircleUrl).intoTargetView(view);
    }


    @BindingAdapter("imageBlurUrl")
    public static void bindBlurImageUrl(ImageView view, Object imageCircleUrl) {
        if (view == null) return;
        GlideImageLoader.getInstance().displayWithBlur(view, imageCircleUrl, 25).intoTargetView(view);
    }

    /**
     * 设置网格样式
     */
    @BindingAdapter("gridespaceCount")
    public static void setGridLayoutManager(RecyclerView recyclerView, int spaceCount) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), spaceCount));
    }
}
