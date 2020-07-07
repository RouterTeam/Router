package com.ifenghui.commonlibrary.base.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ifenghui.commonlibrary.base.ui.interf.ItemView;
import com.ifenghui.commonlibrary.base.ui.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
//    public OnItemClickListener mOnItemClickListener;

    protected ArrayList<ItemView> headers = new ArrayList<>();
    protected ArrayList<ItemView> footers = new ArrayList<>();
    protected List<T> datas = new ArrayList<>();

    public Context getmContext() {
        return mContext;
    }

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 填充数据
     */
    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 添加头部view
     */
    public void addHeaderView(ItemView view) {
        if (view == null) return;
        int position = headers.indexOf(view);
        if (-1 == position) {
            headers.add(view);
            notifyItemInserted(getHeaderCount() - 1);
        }
    }

    /**
     * 添加底部view
     */
    public void addFooterView(ItemView view) {
        if (view == null) return;
        int position = footers.indexOf(view);
        if (-1 == position) {
            footers.add(view);
            notifyItemInserted(getFooterCount() - 1);
        }
    }

    /**
     * 移除头部view
     */
    public void removeHeaderView(ItemView view) {
        if (view == null) return;
        if (getHeaderCount() == 0) {
            return;
        }
        int count = getHeaderCount();
        int position = headers.indexOf(view);
        if (-1 == position) {
            headers.remove(view);
            notifyItemRangeRemoved(count - 1, count);
        }
    }


    /**
     * 移除底部view
     */
    public void removeFooterView(ItemView view) {
        if (view == null) return;
        if (getFooterCount() == 0) {
            return;
        }
        int count = getFooterCount();
        int position = footers.indexOf(view);
        if (-1 == position) {
            footers.remove(view);
            notifyItemRangeRemoved(count - 1, count);
        }
    }

    /**
     * 获取header Count
     */
    public int getHeaderCount() {
        return headers != null ? headers.size() : 0;
    }

    /**
     * 获取footer Count
     */
    public int getFooterCount() {
        return footers != null ? footers.size() : 0;
    }


    /**
     * 判定是否是header
     */
    private boolean isHeader(int position) {
        return position < getHeaderCount();
    }

    /**
     * 判定是否是footer
     */
    protected boolean isFooter(int position) {
        int countBeforeFooter = getCount() + getHeaderCount();
        return position >= countBeforeFooter;
    }

    /**
     * 获取data count
     */
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    /**
     * 获取item count
     */
    @Override
    public int getItemCount() {
        return getHeaderCount() + getCount() + getFooterCount();
    }

    /**
     * 获取item data
     */
    public T getItem(int i) {
        if (datas == null || datas.size() == 0) {
            return null;
        }
        if (i < 0 || i >= datas.size()) {
            return null;
        }
        return datas.get(i);
    }

    @Override
    public int getItemViewType(int position) {

        if (position < getHeaderCount()) return headers.get(position).hashCode();
        int i = position - getHeaderCount() - getCount();
        return (i >= 0) ? footers.get(i).hashCode() : getItemType(position - getHeaderCount());
    }

    /**
     * 自定义ItemViewType
     */
    public int getItemType(int position) {
        return 0;
    }

    /**
     * 自定义网格布局item view占据的比例
     */
    public int getCustomerSpanSize(int position) {
        return 1;
    }

    /**
     * 实现瀑布流布局的头部和底部沾满整行或列
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams && (isHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition()))) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 实现网格布局的头部和底部沾满整行或列
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isHeader(position) || isFooter(position) ? gridManager.getSpanCount() : getCustomerSpanSize(position - getHeaderCount());
                }
            });
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = createSpViewByType(parent, viewType);
        return (view != null) ? new BaseViewHolder(view) : OncreateViewHolder(parent, viewType);
    }

    /**
     * 外部实现返回对应的viewholder
     */
    abstract public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.itemView.setId(position);
        if (getHeaderCount() != 0 && position < getHeaderCount()) {
            headers.get(position).onBindView(holder.itemView);
            return;
        }
        int i = position - getHeaderCount() - getCount();
        if (getFooterCount() != 0 && i >= 0) {
            footers.get(i).onBindView(holder.itemView);
            return;
        }
        holder.setData(getItem(position - getHeaderCount()), position);
    }

    /**
     * 创建 header和footer view
     */
    private View createSpViewByType(ViewGroup parent, int viewType) {
        for (ItemView headerView : headers) {
            if (headerView.hashCode() == viewType) {
                View view = headerView.onCreateView(parent);
                StaggeredGridLayoutManager.LayoutParams layoutParams;
                if (view.getLayoutParams() != null)
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(view.getLayoutParams());
                else
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(true);
                view.setLayoutParams(layoutParams);
                return view;
            }
        }
        for (ItemView footerview : footers) {
            if (footerview.hashCode() == viewType) {
                View view = footerview.onCreateView(parent);
                StaggeredGridLayoutManager.LayoutParams layoutParams;
                if (view.getLayoutParams() != null)
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(view.getLayoutParams());
                else
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(true);
                view.setLayoutParams(layoutParams);
                return view;
            }
        }
        return null;
    }
}
