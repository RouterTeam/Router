package com.ifenghui.commonlibrary.base.ui.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<M> extends RecyclerView.ViewHolder {
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup viewGroup, int resId) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(resId, viewGroup, false));
    }

    public void setData(M data, int position) {

    }


    protected <T extends View> T findView(int id) {
        T view = (T) itemView.findViewById(id);
        return view;
    }

    public Context getContext() {
        return itemView.getContext();
    }

}
