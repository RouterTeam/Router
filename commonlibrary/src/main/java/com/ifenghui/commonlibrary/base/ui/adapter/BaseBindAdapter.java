package com.ifenghui.commonlibrary.base.ui.adapter;

import android.content.Context;
import android.util.Log;
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
    int viewholdercount=0;
    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(getmContext()), this.getItemLayout(viewType), viewGroup, false);
        onViewHolderInit(binding);
        viewholdercount++;
        return new BaseViewHolder(binding.getRoot());
    }
    int bindviewholdercount=0;
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindviewholdercount++;
        if (position>=getHeaderCount()&&position<(getHeaderCount()+getCount())){
            B binding = DataBindingUtil.getBinding(holder.itemView);
            int itemPosition=position - getHeaderCount();
            this.onBindItem(binding, this.datas.get(itemPosition),itemPosition);
        }else {
            super.onBindViewHolder(holder, position);
        }
    }

    /**
     * viewholder  init 数据使用
     */
    protected void onViewHolderInit(B binding){}

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
