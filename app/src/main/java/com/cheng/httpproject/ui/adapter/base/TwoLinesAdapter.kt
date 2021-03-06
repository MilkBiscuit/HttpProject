package com.cheng.httpproject.ui.adapter.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cheng.httpproject.R

abstract class TwoLinesAdapter<T>(protected val context: Context, listData: List<T>)
    : RecyclerView.Adapter<TwoLinesViewHolder>() {

    var items: List<T> = listData
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLinesViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_two_lines_with_chevron, parent, false)

        return TwoLinesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TwoLinesViewHolder, i: Int) {
        val item = items[i]

        bindItemViewHolder(viewHolder, i, item)
    }

    abstract fun bindItemViewHolder(viewHolder: TwoLinesViewHolder, i: Int, item: T)

}