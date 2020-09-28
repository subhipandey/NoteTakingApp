package com.subhipandey.android.notetakingapp

import androidx.recyclerview.widget.RecyclerView

interface ItemTouchHelpListener {
    open fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int) {

    }
}