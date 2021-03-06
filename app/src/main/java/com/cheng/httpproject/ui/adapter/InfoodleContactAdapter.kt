package com.cheng.httpproject.ui.adapter

import android.content.Context
import android.view.View
import com.cheng.httpproject.model.InfoodleSearchPersonItem
import com.cheng.httpproject.ui.adapter.base.TwoLinesAdapter
import com.cheng.httpproject.ui.adapter.base.TwoLinesViewHolder

class InfoodleContactAdapter(context: Context, items: List<InfoodleSearchPersonItem>):
        TwoLinesAdapter<InfoodleSearchPersonItem>(context, items) {

    override fun bindItemViewHolder(viewHolder: TwoLinesViewHolder, i: Int, item: InfoodleSearchPersonItem) {
        viewHolder.tvLine1.text = item.firstName
        viewHolder.tvLine2.text = item.lastName
        viewHolder.ivChevron.visibility = View.GONE
    }

}