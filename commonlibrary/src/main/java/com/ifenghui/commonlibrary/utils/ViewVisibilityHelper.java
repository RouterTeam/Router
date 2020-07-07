package com.ifenghui.commonlibrary.utils;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class ViewVisibilityHelper {
    @BindingAdapter("viewVisibility")
    public static void bindViewVisibility(View view, boolean viewVisibility) {
        view.setVisibility(viewVisibility ? View.VISIBLE : View.INVISIBLE);
    }
}
