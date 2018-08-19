package com.vladimirkondenko.yoyocinema.utils


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import java.util.*

/**
 * Encapsulates data manipulation and common adapter logic.
 */
abstract class BaseAdapter<I, VH : BaseAdapter.BaseViewHolder<I>>(protected var context: Context) : RecyclerView.Adapter<VH>() {

    private val layoutInflater = LayoutInflater.from(context)

    private val viewEventsDisposables = CompositeDisposable()

    val itemClicks = PublishSubject.create<I>()

    var items = ArrayList<I>()
        set(value) {
            val callback = ItemsCallback(this.items, value)
            val result = DiffUtil.calculateDiff(callback)
            this.items.clear()
            this.items.addAll(value)
            result.dispatchUpdatesTo(this)
            notifyDataSetChanged()
        }

    abstract override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VH

    override fun onBindViewHolder(vh: VH, position: Int) {
        val item = items[position]
        vh.bindItem(item)
        viewEventsDisposables += RxView.clicks(vh.view).subscribe { _ -> itemClicks.onNext(item) }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        viewEventsDisposables.clear()
    }

    // Data manipulation methods

    fun getItem(i: Int) = items[i]

    fun addItems(items: List<I>) {
        val callback = ItemsCallback(this.items, items)
        val result = DiffUtil.calculateDiff(callback)
        this.items.addAll(items)
        result.dispatchUpdatesTo(this)
    }

    fun addItem(item: I) {
        if (!items.contains(item)) {
            items.add(item)
            notifyItemInserted(items.indexOf(item))
        }
    }

    fun remove(i: Int) {
        items.removeAt(i)
        notifyItemRemoved(i)
    }

    fun remove(item: I) {
        items.remove(item)
        notifyItemRemoved(items.indexOf(item))
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    protected fun getView(@LayoutRes layoutId: Int, parent: ViewGroup): View {
        return layoutInflater.inflate(layoutId, parent, false)
    }

    abstract class BaseViewHolder<I>(view: View) : RecyclerView.ViewHolder(view) {

        var item: I? = null
            private set

        val view: View
            get() = itemView

        @CallSuper
        open fun bindItem(item: I) {
            this.item = item
        }

    }

}
