package com.ifenghui.commonlibrary.utils

import androidx.databinding.ObservableList
import androidx.databinding.ObservableList.OnListChangedCallback
import androidx.recyclerview.widget.RecyclerView

class ObservableListUtil {
    companion object {
        fun <T : ObservableList<*>?> getListChangedCallback(adapter: RecyclerView.Adapter<*>?): OnListChangedCallback<T> {
            return object : OnListChangedCallback<T>() {
                override fun onChanged(sender: T) {
                    adapter?.notifyDataSetChanged()
                }

                override fun onItemRangeRemoved(sender: T, positionStart: Int, itemCount: Int) {
                    adapter?.notifyItemRangeRemoved(positionStart, itemCount)
                }

                override fun onItemRangeMoved(sender: T, fromPosition: Int, toPosition: Int, itemCount: Int) {
                    if (itemCount == 1) {
                        adapter?.notifyItemMoved(fromPosition, toPosition)
                    } else {
                        adapter?.notifyDataSetChanged()
                    }
                }

                override fun onItemRangeInserted(sender: T, positionStart: Int, itemCount: Int) {
                    adapter?.notifyItemRangeInserted(positionStart, itemCount)
                }

                override fun onItemRangeChanged(sender: T, positionStart: Int, itemCount: Int) {
                    adapter?.notifyItemRangeChanged(positionStart, itemCount)
                }

            }
        }
    }
}