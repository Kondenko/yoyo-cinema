package com.vladimirkondenko.yoyocinema.utils

import androidx.recyclerview.widget.DiffUtil


class ItemsCallback<I>(private val oldItemSet: List<I>, private val newItemSet: List<I>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItemSet.size

    override fun getNewListSize() = newItemSet.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemSet[oldItemPosition] == newItemSet[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemSet[oldItemPosition] == newItemSet[newItemPosition]
    }

}
