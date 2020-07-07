package com.ifenghui.commonlibrary.base.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ifenghui.commonlibrary.base.ui.viewholder.BaseViewHolder;

public abstract class BaseBindAdapter<T,B extends ViewDataBinding> extends BaseAdapter<T> {

    public BaseBindAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(getmContext()), this.getItemLayout(viewType), viewGroup, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (position>=getHeaderCount()&&position<(getHeaderCount()+getCount())){
            B binding = DataBindingUtil.getBinding(holder.itemView);
            this.onBindItem(binding, this.datas.get(position),position - getHeaderCount());
        }else {
            super.onBindViewHolder(holder, position);
        }
    }

    /**
     * 获取item布局资源
     */
    @LayoutRes
    protected abstract int getItemLayout(int viewType);

    /**
     * 更新数据
     */
    protected abstract void onBindItem(B binding, T item,int position);
}
